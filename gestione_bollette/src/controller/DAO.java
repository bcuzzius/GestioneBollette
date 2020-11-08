package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import data.model.Bolletta;
import data.model.Tipologia;

public class DAO {
	private static final String url = "jdbc:sqlite:db_bollette.db";

	private static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Bolletta> getBollette() {
		ArrayList<Tipologia> tipologieList = getTipologie();
		ArrayList<Bolletta> out = new ArrayList<Bolletta>();
		try (Connection connessione = getConnection()) {
			String sql = "SELECT * FROM bollette";
			PreparedStatement ps = connessione.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int numero = rs.getInt("tipologia");
				Bolletta b = new Bolletta();
				b.setKey(rs.getLong("key"));
				b.setCommento(rs.getString("commento"));
				b.setIndirizzo(rs.getString("indirizzo"));
				b.setTipologia(tipologieList.stream().filter(tipo -> tipo.getKey() == numero).findFirst().get());
				b.setCifra(rs.getDouble("cifra"));
				b.setDataEmissione(LocalDate.parse(rs.getString("dataEmissione")));
				b.setDataScadenza(LocalDate.parse(rs.getString("dataScadenza")));
				if (rs.getString("dataPagamento") != null)
					b.setDataPagamento(LocalDate.parse(rs.getString("dataPagamento")));
				out.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static ArrayList<Tipologia> getTipologie() {
		ArrayList<Tipologia> out = new ArrayList<Tipologia>();
		try (Connection connessione = getConnection()) {
			String sql = "Select * FROM tipologie";
			PreparedStatement ps = connessione.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				out.add(new Tipologia(rs.getLong("key"), rs.getString("nomeTipologia")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static void addBolletta(Bolletta bolletta) {
		String sql = "";
		try (Connection connessione = getConnection()) {

			if (bolletta.getDataPagamento() == null)
				sql = "INSERT INTO bollette(commento, indirizzo, tipologia, cifra, dataEmissione, dataScadenza) values(?, ?, ?, ?, ?, ?)";
			else
				sql = "INSERT INTO bollette(commento, indirizzo, tipologia, cifra, dataEmissione, dataScadenza, dataPagamento) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connessione.prepareStatement(sql);
			ps.setString(1, bolletta.getCommento());
			ps.setString(2, bolletta.getIndirizzo());
			ps.setLong(3, bolletta.getTipologia().getKey());
			ps.setDouble(4, bolletta.getCifra());
			ps.setString(5, bolletta.getDataEmissione().toString());
			ps.setString(6, bolletta.getDataScadenza().toString());
			if (bolletta.getDataPagamento() != null)
				ps.setString(7, bolletta.getDataPagamento().toString());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addTipologia(Tipologia t) {
		try (Connection connessione = getConnection()) {
			PreparedStatement ps = connessione.prepareStatement("INSERT INTO tipologie (nomeTipologia) values (?)");
			ps.setString(1, t.getNomeTipologia());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Bolletta> getUnpaidBollette() {
		ArrayList<Bolletta> out = new ArrayList<Bolletta>();
		ArrayList<Tipologia> tipologieList = getTipologie();
		try (Connection connessione = getConnection()) {
			PreparedStatement ps = connessione.prepareStatement("Select * FROM bollette WHERE dataPagamento IS NULL");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int numero = rs.getInt("tipologia");
				out.add(new Bolletta(rs.getLong("key"), rs.getString("commento"), rs.getString("indirizzo"),
						tipologieList.stream().filter(tipo -> tipo.getKey() == numero).findFirst().get(),
						rs.getDouble("cifra"), LocalDate.parse(rs.getDate("dataEmissione").toString()),
						LocalDate.parse(rs.getDate("dataScadenza").toString()), null));
			}
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addPagamentoBolletta(Bolletta bolletta, LocalDate data) {
		long key = bolletta.getKey();
		try (Connection connessione = getConnection()) {
			PreparedStatement ps = connessione.prepareStatement("UPDATE bollette SET dataPagamento=? WHERE key=? ");
			ps.setString(1, data.toString());
			ps.setLong(2, key);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Bolletta getBolletta(long key) {
		return getBollette().stream().filter(bolletta -> bolletta.getKey() == key).findAny().get();
	}
}
