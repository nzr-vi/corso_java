package model;

import util.IIdentificable;

public class Libro implements IIdentificable{
	private int id;
	private String titolo;
	private double prezzo;
	private int pagine;
	private int editore_id;
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}
	
	public Libro(int id, String titolo, double price, int pagine) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.prezzo = price;
		this.pagine = pagine;
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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getPagine() {
		return pagine;
	}
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}

	public int getEditore_id() {
		return editore_id;
	}

	public void setEditore_id(int editore_id) {
		this.editore_id = editore_id;
	}
	
}
