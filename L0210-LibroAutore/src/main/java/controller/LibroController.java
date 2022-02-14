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

public class LibroController {

	private static LibroController instance;
	 
	private Database db;
	private IBeanTableRowFormatter<Libro> rowFormatter;
	private final IBeanIDFormatter<Libro> idFormatter;
	
	public LibroController() throws ClassNotFoundException, SQLException {
		this.db = Database.getDatabase();
		this.rowFormatter = (lib)->
			"<tr><td>" + lib.getId() + "</td>" +
				"<td>" + lib.getTitolo() + "</td>" +
				"<td>" + lib.getPrezzo() + "</td>" + 
				"<td>" + lib.getPagine() + "</td>";
		this.idFormatter = (lib)->Integer.toString(lib.getId());
	}
 
	public static LibroController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new LibroController();
		}
		return instance;
	}	
 
	public List<Libro> getAllLibri() throws SQLException{
		return db.getAllLibri();
	}

	public Libro getLibro(int id) throws SQLException, EmptyQueryResulException{
		return db.getLibroById(id);
	}

	public Map<String,InputFormData> getDefaultDataMap(){
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("titolo", InputFormDataFactory.create("titolo"));
		data.put("pagine", InputFormDataFactory.create("number", 0));
		data.put("prezzo", InputFormDataFactory.create("number\" step=\"0.01", 0));
		data.put("editore_id", InputFormDataFactory.create("editore", 0));
		return data;
	}
	public Map<String,InputFormData> getDataMap(Libro l){
		if(l == null)
			throw new NullPointerException();
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("id", InputFormDataFactory.create("number", l.getId()));
		data.put("titolo", InputFormDataFactory.create(l.getTitolo()));
		data.put("pagine", InputFormDataFactory.create("number", l.getPagine()));
		data.put("prezzo", InputFormDataFactory.create("number\" step=\"0.01", l.getPrezzo()));
		//data.put("editore", InputFormDataFactory.create("number", l.getEditore_id()));
		return data;
	}
	
	public IBeanTableRowFormatter<Libro> getRowFormatter() {
		return rowFormatter;
	}
	
	public int updateLibro(int id, String titolo, double prezzo, int pagine) throws SQLException{
		Libro daAggiornare=new Libro();
		
		daAggiornare.setId(id);
		daAggiornare.setTitolo(titolo);
		daAggiornare.setPrezzo(prezzo);
		daAggiornare.setPagine(pagine);
		
		return db.updateLibro(daAggiornare);
	}

	public boolean deleteLibro(int id) throws SQLException, EmptyQueryResulException {
		return db.deleteLibro(db.getLibroById(id))==1;
	}
	
	public boolean createLibro(String titolo, double prezzo, int pagine, int editore_id) throws SQLException{
		Libro daAggiornare=new Libro();
		
		daAggiornare.setTitolo(titolo);
		daAggiornare.setPrezzo(prezzo);
		daAggiornare.setPagine(pagine);
		daAggiornare.setEditore_id(editore_id);
		
		return db.createLibro(daAggiornare);
	}

	public IBeanIDFormatter<Libro> getIdFormatter() {
		return idFormatter;
	}
}
