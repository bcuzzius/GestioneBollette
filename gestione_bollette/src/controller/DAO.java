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
	private static final String url = "jdbc:sqlite:databaseBollette.db";

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
}
