package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.EmptyQueryResulException;
import model.Autore;
import model.AutoreLibro;
import model.Libro;
import util.IIdentificable;

public class Database {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/generation";
	private static final String DB_USER = "app_generation";
	private static final String DB_PASSWORD = "generation_2022";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static Database instance;

	private Connection con;
	
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
	
	public boolean createAutoreLibro(AutoreLibro al) throws SQLException{
		String sql="INSERT INTO generation.autore_libro "
				+ " (libro_id, autore_id)"
				+ " VALUES(?, ?);";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setInt(index++, al.getAlLibroID());
		istruzione.setInt(index, al.getAlAutoreId());
		
		return istruzione.executeUpdate()==1;
	}
	
	public boolean createAutore(Autore autore) throws SQLException{
		String sql="INSERT INTO generation.autore "
				+ " (nome, cognome, nazionalita)"
				+ " VALUES(?, ?, ?);";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setString(index++, autore.getNome());
		istruzione.setString(index++, autore.getCognome());
		istruzione.setString(index, autore.getNazionalita());
		
		return istruzione.executeUpdate()==1;
	}
	
	public boolean createLibro(Libro libro) throws SQLException{
		String sql="INSERT INTO generation.libro "
				+ " (titolo, prezzo, pagine, editore_id)"
				+ " VALUES(?, ?, ?, ?);";

		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setString(index++, libro.getTitolo());
		istruzione.setDouble(index++, libro.getPrezzo());
		istruzione.setInt(index++, libro.getPagine());
		istruzione.setInt(index, libro.getEditore_id());
		
		return istruzione.executeUpdate()==1;
	}
	
	public int updateAutore(Autore autore) throws SQLException{
		String sql="UPDATE generation.autore "
				+ " SET nome=?, cognome=?, nazionalita=?"
				+ " WHERE id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setString(index++, autore.getNome());
		istruzione.setString(index++, autore.getCognome());
		istruzione.setString(index++, autore.getNazionalita());

		istruzione.setInt(index, autore.getId());
		
		return istruzione.executeUpdate();
		
	}
	
	public int updateAutoreLibro(AutoreLibro old ,AutoreLibro al) throws SQLException{
		String sql="UPDATE generation.autore_libro "
				+ " SET libro_id=?, autore_id=?"
				+ " WHERE  libro_id=? AND autore_id=?;";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setInt(index++, al.getAlLibroID());
		istruzione.setInt(index++, al.getAlAutoreId());
		istruzione.setInt(index++, old.getAlLibroID());
		istruzione.setInt(index, old.getAlAutoreId());

		return istruzione.executeUpdate();		
	}
	
	public int updateLibro(Libro libro) throws SQLException{
		String sql="UPDATE generation.libro "
				+ " SET titolo=?, prezzo=?, pagine=?"
				+ " WHERE id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		
		int index = 1;
		
		istruzione.setString(index++, libro.getTitolo());
		istruzione.setDouble(index++, libro.getPrezzo());
		istruzione.setInt(index++, libro.getPagine());

		istruzione.setInt(index, libro.getId());
		
		return istruzione.executeUpdate();
		
	}
	
	private int delete(String db, IIdentificable element) throws SQLException {
		String sql="DELETE FROM generation."+db+" WHERE id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, element.getId());
		
		return istruzione.executeUpdate();
	}
	
	public int deleteAutore(Autore autore) throws SQLException{
		return this.delete("autore", autore);
	}
	
	public int deleteLibro(Libro libro) throws SQLException{
		return this.delete("libro", libro);
	}
	
	public int deleteAutoreLibro(AutoreLibro element) throws SQLException {
		String sql="DELETE FROM generation.autore_libro WHERE libro_id=? AND autore_id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, element.getAlLibroID());
		istruzione.setInt(2, element.getAlAutoreId());
		
		return istruzione.executeUpdate();
	}
		
	private AutoreLibro loadAutoreLibroRS(ResultSet risultatiQuery) throws SQLException {
		AutoreLibro rstu=new AutoreLibro();
		rstu.setAlAutoreId(risultatiQuery.getInt("autore_id"));
		rstu.setAlLibroID(risultatiQuery.getInt("libro_id"));
		rstu.setaCognome(risultatiQuery.getString("cognome"));
		rstu.setlTitolo(risultatiQuery.getString("titolo"));
		rstu.setlPrezzo(risultatiQuery.getDouble("prezzo"));
		return rstu;
	}
	
	private Libro loadLibroRS(ResultSet risultatiQuery) throws SQLException {
		Libro rstu=new Libro();
		rstu.setId(risultatiQuery.getInt("id"));
		rstu.setTitolo(risultatiQuery.getString("titolo"));
		rstu.setPagine(risultatiQuery.getInt("pagine"));
		rstu.setPrezzo(risultatiQuery.getDouble("prezzo"));
		rstu.setEditore_id(risultatiQuery.getInt("editore_id"));
		return rstu;
	}
	
	private Autore loadAutoreRS(ResultSet risultatiQuery) throws SQLException {
		Autore rstu=new Autore();
		rstu.setId(risultatiQuery.getInt("id"));
		rstu.setNome(risultatiQuery.getString("nome"));
		rstu.setCognome(risultatiQuery.getString("cognome"));
		rstu.setNazionalita(risultatiQuery.getString("nazionalita"));
		return rstu;
	}
	
	public AutoreLibro getAutoreLibroById(int lib_id, int aut_id) throws SQLException, EmptyQueryResulException{
		String sql="SELECT al.autore_id, al.libro_id, a.cognome, l.titolo, l.prezzo"
				+ " FROM generation.autore_libro al"
				+ " JOIN generation.autore a ON (a.id = al.autore_id)"
				+ " JOIN generation.libro l ON (l.id = al.libro_id)"
				+ " WHERE a.id=? AND l.id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, aut_id);
		istruzione.setInt(2, lib_id);
		
		//executeQuery si usa per le SELECT
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		if(risultatiQuery.next()) 
			return this.loadAutoreLibroRS(risultatiQuery);
				
		throw new EmptyQueryResulException(sql);
	}
	
	public Autore getAutoreById(int id) throws SQLException, EmptyQueryResulException{
		String sql="SELECT id, nome, cognome, nazionalita"
				+ " FROM generation.autore "
				+ " WHERE id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, id);
		
		//executeQuery si usa per le SELECT
		ResultSet risultatiQuery=istruzione.executeQuery();
		if(risultatiQuery.next()) 
			return this.loadAutoreRS(risultatiQuery);
	
		throw new EmptyQueryResulException(sql);
	}
	
	public Libro getLibroById(int id) throws SQLException, EmptyQueryResulException{
		String sql="SELECT id, titolo, prezzo, pagine, editore_id"
				+ " FROM generation.libro "
				+ " WHERE id=? ; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, id);
		
		//executeQuery si usa per le SELECT
		ResultSet risultatiQuery=istruzione.executeQuery();
		if(risultatiQuery.next()) 
			return this.loadLibroRS(risultatiQuery);
		
		throw new EmptyQueryResulException(sql);
	}
	
	public List<Autore> getAllAutori() throws SQLException{
		String sql="SELECT * FROM generation.autore ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		List<Autore> listaLibri=new ArrayList<>();
		
		while(risultatiQuery.next()) 
			listaLibri.add(this.loadAutoreRS(risultatiQuery));
		
		return listaLibri;
	}

	public List<Libro> getAllLibri() throws SQLException{
	
		String sql="SELECT * FROM generation.libro ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);		
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		List<Libro> listaLibri=new ArrayList<>();
		
		while(risultatiQuery.next())	
			listaLibri.add(this.loadLibroRS(risultatiQuery));
		
		return listaLibri;
	}
	
	public List<AutoreLibro> getAllAutoriLibri() throws SQLException{
		
		String sql="SELECT al.autore_id, al.libro_id, a.cognome, l.titolo, l.prezzo "
				+ "FROM generation.autore_libro al "
				+ "JOIN generation.autore a ON (a.id = al.autore_id) "
				+ "JOIN generation.libro l ON (l.id = al.libro_id)";
		
		PreparedStatement istruzione= con.prepareStatement(sql);	
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		List<AutoreLibro> listaAutoreLibro=new ArrayList<>();
		
		while(risultatiQuery.next())
			listaAutoreLibro.add(this.loadAutoreLibroRS(risultatiQuery));
		
		return listaAutoreLibro;
	}

	public boolean isLibroIDPresent(int id) throws SQLException{		
		String sql="SELECT id FROM generation.libro a WHERE a.nome = ?; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		istruzione.setInt(1, id);
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		return risultatiQuery.next();	
	}
	
	public boolean isAutoreIDPresent(int id) throws SQLException{		
		String sql="SELECT id FROM generation.autore a WHERE id = ?; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		istruzione.setInt(1, id);
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		return risultatiQuery.next();	
	}
	
	public int getEditorId(String editore) throws EmptyQueryResulException, SQLException {
		String sql="SELECT id FROM generation.editore a WHERE a.nome = ?; ";
		
		PreparedStatement istruzione= con.prepareStatement(sql);
		istruzione.setString(1, editore);
		
		ResultSet risultatiQuery=istruzione.executeQuery();
		
		if(!risultatiQuery.next()) 
			throw new EmptyQueryResulException(sql);
		
		return risultatiQuery.getInt("id");	
	}
}
