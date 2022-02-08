package model;

import java.sql.Date;

public class Studente {
	
	private int id;
	private String nome;
	private String cognome;
	private String genere;
	private String indirizzo;
	private String citta;
	private String provincia;
	private String regione;
	private String email;
	private Date data_nascita;
	private Date ins;
	private boolean login;

	
	public Studente() {
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


	public String getGenere() {
		return genere;
	}


	public void setGenere(String genere) {
		this.genere = genere;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getRegione() {
		return regione;
	}


	public void setRegione(String regione) {
		this.regione = regione;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getData_nascita() {
		return data_nascita;
	}


	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}


	public Date getIns() {
		return ins;
	}


	public void setIns(Date ins) {
		this.ins = ins;
	}


	public boolean isLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}
	
}
