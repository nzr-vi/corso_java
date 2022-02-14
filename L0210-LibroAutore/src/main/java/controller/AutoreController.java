package controller;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exception.EmptyQueryResulException;
import model.Autore;
import model.InputFormData;
import model.Libro;
import repository.Database;
import util.IBeanIDFormatter;
import util.IBeanTableRowFormatter;

public class AutoreController {

	private static AutoreController instance;
	 
	private Database db;
	private final IBeanTableRowFormatter<Autore> rowFormatter;
	private final IBeanIDFormatter<Autore> idFormatter;
	
	public AutoreController() throws ClassNotFoundException, SQLException {
		this.db = Database.getDatabase();
		this.rowFormatter = (lib)->
			"<tr><td>" + lib.getId() + "</td>" +
				"<td>" + lib.getNome() + "</td>" +
				"<td>" + lib.getCognome() + "</td>" + 
				"<td>" + lib.getNazionalita() + "</td>";
		this.idFormatter = (aut)->Integer.toString(aut.getId());
	}
 
	public static AutoreController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new AutoreController();
		}
		return instance;
	}	
 
	public List<Autore> getAllLibri() throws SQLException{
		return db.getAllAutori();
	}

	public Autore getAutore(int id) throws SQLException, EmptyQueryResulException{
		return db.getAutoreById(id);
	}

	public Map<String,InputFormData> getDefaultDataMap(){
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("nome", InputFormDataFactory.create("nome"));
		data.put("cognome", InputFormDataFactory.create("cognome"));
		data.put("nazionalita", InputFormDataFactory.create("nazionalita"));
		return data;
	}
	public Map<String,InputFormData> getDataMap(Autore l){
		if(l == null)
			throw new NullPointerException();
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("id", InputFormDataFactory.create("number", l.getId()));
		data.put("nome", InputFormDataFactory.create(l.getNome()));
		data.put("cognome", InputFormDataFactory.create(l.getCognome()));
		data.put("nazionalita", InputFormDataFactory.create(l.getNazionalita()));
		return data;
	}
	
	public IBeanTableRowFormatter<Autore> getRowFormatter() {
		return rowFormatter;
	}
	
	public int updateAutore(int id, String nome, String cognome, String nazionalita) throws SQLException{
		Autore daAggiornare=new Autore();
		
		daAggiornare.setId(id);
		daAggiornare.setNome(nome);
		daAggiornare.setCognome(cognome);
		daAggiornare.setNazionalita(nazionalita);
		
		return db.updateAutore(daAggiornare);
	}

	public boolean deleteAutore(int id) throws SQLException, EmptyQueryResulException {
		return db.deleteAutore(db.getAutoreById(id))==1;
	}
	
	public boolean createAutore(String nome, String cognome, String nazionalita) throws SQLException{
		Autore daAggiornare=new Autore();
		
		daAggiornare.setNome(nome);
		daAggiornare.setCognome(cognome);
		daAggiornare.setNazionalita(nazionalita);
		
		return db.createAutore(daAggiornare);
	}

	public IBeanIDFormatter<Autore> getIdFormatter() {
		return idFormatter;
	}
}
