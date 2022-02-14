package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.UtenteBannatoException;
import model.Studente;
import repository.Database;

public class StudenteCtrl {

	public static StudenteCtrl instance;

	public static StudenteCtrl getController() throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new StudenteCtrl();
		return instance;
	}

	private Database db;

	private StudenteCtrl() throws ClassNotFoundException, SQLException {
		this.db = Database.getDatabase();
	}

	public boolean insertStudente(String nome, String cognome, String genere,
			String indirizzo, String citta, String provincia, String regione,
			String email, Date dataNascita) throws SQLException {
		Studente st = new Studente();
		
		st.setNome(nome);
		st.setCognome(cognome);
		st.setGenere(genere);
		st.setIndirizzo(indirizzo);
		st.setCitta(citta);
		st.setProvincia(provincia);
		st.setRegione(regione);
		st.setEmail(email);
		st.setData_nascita(dataNascita);
		
		return this.db.insertStudente(st);
	}

	public boolean updateStudente(String nome, String cognome, String genere,
			String indirizzo, String citta, String provincia, String regione,
			String email, Date dataNascita) throws SQLException {
		Studente st = new Studente();
		
		st.setNome(nome);
		st.setCognome(cognome);
		st.setGenere(genere);
		st.setIndirizzo(indirizzo);
		st.setCitta(citta);
		st.setProvincia(provincia);
		st.setRegione(regione);
		st.setEmail(email);
		st.setData_nascita(dataNascita);
		
		return this.db.updateStudente(st);
	}

	public boolean deleteStudente(int id) throws SQLException {
		Studente toDel = new Studente();
		toDel.setId(id);
		return this.db.deleteStudente(toDel);
	}

	public Studente getStudentById(int id) throws SQLException {
		return this.db.getStudentById(id);
	}

	public List<Studente> getAllStudent() throws SQLException {
		return this.db.getAllStudent();
	}
}
