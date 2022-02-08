package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Canzone;

public class CanzoneDAOImpl implements CanzoneDAO{

	private Connection conn = null;
	private Statement stat = null;
	private ResultSet rs = null;
	
	private Connessione db = new Connessione();
	
	@Override
	public void addCanzone(Canzone c) throws SQLException {
		this.conn = this.db.connetti();
		this.stat = this.conn.createStatement();
		String query = "INSERT INTO canzoniGen (titolo, cantante, genere, album) VALUES ("
				+ "'" + c.getTitolo()+"', "
				+ "'" + c.getCantante()+"', "
				+ "'" + c.getGenere()+"', "
				+ "'" + c.getAlbum()+"')";
		this.stat.executeUpdate(query);
		
		System.out.println("Hai aggiunto la canzone");
	}

	@Override
	public void updCanzone(Canzone c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delCanzone(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Canzone getCanzione(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Canzone> getCanzoni() throws SQLException {
		List<Canzone> canzoni = new ArrayList<>();
		this.conn = this.db.connetti();
		this.stat = this.conn.createStatement();
		this.rs = this.stat.executeQuery(GET_ALL);
		while(this.rs.next()) {
			Canzone c = new Canzone();
			c.setId(rs.getInt("id"));
			c.setTitolo(rs.getString("titolo"));
			c.setAlbum(rs.getString("album"));
			c.setCantante(rs.getString("cantante"));
			c.setGenere(rs.getString("genere"));
			canzoni.add(c);
		}
		return canzoni;
	}

	

}
