package repository;

import java.sql.Connection;
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

public class Database {

	private static Database instance;
	
	private Connection con;
	
	private final static String DB_URL = "jdbc:mysql://localhost:3306/mydatabasename?";
	private final static String DB_USER = "app_generation";
	private final static String DB_PASSWORD = "generation_2022";
	private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private Database() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}
	
	public static Database getDatabase() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public boolean insertStudente(Studente st) throws SQLException {
		String sql = "insert into studente"
				+ "(nome, cognome, genere, indirizzo, citta, provincia, regione, email, data_nascita) values"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement istruzione = this.con.prepareStatement(sql);
		
		istruzione.setString(1, st.getNome());
		istruzione.setString(2, st.getCognome());
		istruzione.setString(3, st.getGenere());
		istruzione.setString(4, st.getIndirizzo());
		istruzione.setString(5, st.getCitta());
		istruzione.setString(6, st.getProvincia());
		istruzione.setString(7, st.getRegione());
		istruzione.setString(8, st.getEmail());
		istruzione.setDate(9, st.getData_nascita());
		
		int numRow = istruzione.executeUpdate();
		
		return numRow == 1;
	}
}
