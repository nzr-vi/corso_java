package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {

	private final String DB_URL = "jdbc:mysql://localhost:3306/generation";
	private final String USER = "app_generation";
	private final String PWD = "generation_2022";
	
	private Connection connection = null;
	
	public Connection connetti() {
		
		try {
			Class.forName("con.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(this.connection==null) {
			try {
				this.connection = DriverManager.getConnection(DB_URL,USER,PWD);
				System.out.println("Siamo connessi");
			} catch (SQLException e) {
				System.err.println("Errore di connessione\n"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		Connessione c = new Connessione();
		c.connetti();
	}
}
