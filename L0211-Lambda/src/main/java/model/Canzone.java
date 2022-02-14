package model;

public class Canzone {
	
	private int id;
	private String titolo;
	private String cantante;

	public Canzone() {
	}

	
	public Canzone(int id, String titolo, String cantante) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.cantante = cantante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	
}
