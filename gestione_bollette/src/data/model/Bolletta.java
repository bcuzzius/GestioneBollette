package data.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Bolletta {
	private long key;
	public static Set<Bolletta> bolletteSet = new HashSet<Bolletta>();
	private String commento;
	private String indirizzo;
	private Tipologia tipologia;
	private double cifra;
	private LocalDate dataEmissione;
	private LocalDate dataScadenza;
	private LocalDate dataPagamento;

	public Bolletta(String commento, String indirizzo, Tipologia tipologia, double cifra, LocalDate dataEmissione,
			LocalDate dataScadenza, LocalDate dataPagamento) {
		this.commento = commento;
		this.indirizzo = indirizzo;
		this.tipologia = tipologia;
		this.cifra = cifra;
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
		this.dataPagamento = dataPagamento;
	}

	public Bolletta() {}
	
	
	
	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public static Set<Bolletta> getBolletteSet() {
		return bolletteSet;
	}

	public static void setBolletteSet(Set<Bolletta> bolletteSet) {
		Bolletta.bolletteSet = bolletteSet;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}

	public double getCifra() {
		return cifra;
	}

	public void setCifra(double cifra) {
		this.cifra = cifra;
	}

	public LocalDate getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String[] toTab() {
		if (dataPagamento != null)
			return new String[] { commento, indirizzo, tipologia.toString(), cifra + "",
					dataEmissione.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALY)),
					dataScadenza.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALY)),
					dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALY)) };
		else {
			return new String[] { commento, indirizzo, tipologia.toString(), cifra + "",
					dataEmissione.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALY)),
					dataScadenza.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALY)), "DA PAGARE" };
		}
	}
}
