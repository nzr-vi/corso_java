package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dal.PersonaDAO;
import model.Prodotto;

public class Database {
	
	private static Database instance;
	
	private final static String DB_URL = "jdbc:mysql://localhost:3306/generation?";
	private final static String DB_USER = "root";
	private final static String DB_PASSWORD = "root";
	private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private Connection con;

	private Database() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
	}
	
	public List<PersonaDAO> getListaPersone() throws SQLException{
		PreparedStatement pst = con.prepareStatement("SELECT * FROM UTENTI;");
		ResultSet listaRisultati = pst.executeQuery();
		
		List<PersonaDAO> listaPersone = new ArrayList<>();
		while(listaRisultati.next())
		{
			PersonaDAO p = new PersonaDAO();
			p.setNome(listaRisultati.getString("NOME"));
			p.setCognome(listaRisultati.getString("COGNOME"));
			p.setEmail(listaRisultati.getString("EMAIL"));
			p.setPassword(listaRisultati.getString("PASSWORD"));
			listaPersone.add(p);
		}
		return listaPersone;
	}
	
}
