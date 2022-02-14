package model;

import util.IIdentificable;

public class Autore implements IIdentificable{
	private int id;
	private String nome;
	private String cognome;
	private String nazionalita;
	
	public Autore() {
		// TODO Auto-generated constructor stub
	}
	
	public Autore(int id, String nome, String cognome, String nazionalita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	
}
