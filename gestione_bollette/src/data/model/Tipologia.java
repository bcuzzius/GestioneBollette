package data.model;

public class Tipologia {
	private long key;
	private String nomeTipologia;

	public Tipologia(String tipologia) {
		this.nomeTipologia = tipologia;
	}

	public Tipologia(long key, String nomeTipologia) {
		this.key = key;
		this.nomeTipologia = nomeTipologia;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getNomeTipologia() {
		return nomeTipologia;
	}

	public void setNomeTipologia(String tipologia) {
		this.nomeTipologia = tipologia;
	}

	@Override
	public String toString() {
		return nomeTipologia;
	}

}
