package controller;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exception.EmptyQueryResulException;
import model.Autore;
import model.AutoreLibro;
import model.InputFormData;
import repository.Database;
import util.IBeanIDFormatter;
import util.IBeanTableRowFormatter;

public class AutoreLibroController {

	private static AutoreLibroController instance;
	private final IBeanTableRowFormatter<AutoreLibro> rowFormatter;
	private final IBeanIDFormatter<AutoreLibro> idFormatter;
	
	private Database db;
 
	private AutoreLibroController() throws ClassNotFoundException, SQLException {
		db = Database.getDatabase();
		this.rowFormatter = (al)->
		"<tr>"
			+"<td>" + al.getAlLibroID() + "</td>"
			+"<td>" + al.getlTitolo() + "</td>"
			+"<td>" + al.getAlAutoreId() + "</td>"
			+"<td>" + al.getaCognome() + "</td>";
		this.idFormatter = (aut)->Integer.toString(aut.getAlLibroID())+" "+Integer.toString(aut.getAlAutoreId());
	}
 
	public static AutoreLibroController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new AutoreLibroController();
		}
		return instance;
	}	
 
	public List<AutoreLibro> getAll() throws SQLException{
		return db.getAllAutoriLibri();
	}

	public AutoreLibro get(int lid, int aid) throws SQLException, EmptyQueryResulException{
		return db.getAutoreLibroById(lid,aid);
	}

	public Map<String,InputFormData> getDefaultDataMap(){
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("libro_id", InputFormDataFactory.create("libro_id"));
		data.put("autore_id", InputFormDataFactory.create("autore_id"));
		return data;
	}
	
	public Map<String,InputFormData> getDataMap(AutoreLibro l){
		if(l == null)
			throw new NullPointerException();
		Map<String,InputFormData> data = new LinkedHashMap<>();
		data.put("libro_id", InputFormDataFactory.create("number", l.getAlLibroID()));
		data.put("autore_id", InputFormDataFactory.create("number", l.getAlAutoreId()));
		return data;
	}
	
	public IBeanTableRowFormatter<AutoreLibro> getRowFormatter() {
		return rowFormatter;
	}
	
	public int update(int old_aid, int old_lid, int aid, int lid) throws SQLException{
		AutoreLibro into=new AutoreLibro();
		into.setAlLibroID(lid);
		into.setAlAutoreId(aid);
		AutoreLibro old = new AutoreLibro();
		old.setAlLibroID(old_lid);
		old.setAlAutoreId(old_aid);
		return db.updateAutoreLibro(old,into);
	}

	public boolean delete(int lid, int aid) throws SQLException, EmptyQueryResulException {
		return db.deleteAutoreLibro(db.getAutoreLibroById(lid,aid))==1;
	}
	
	public boolean create(int aid, int lid) throws SQLException{
		AutoreLibro daAggiornare=new AutoreLibro();
		
		daAggiornare.setAlAutoreId(aid);
		daAggiornare.setAlLibroID(lid);
		
		return db.createAutoreLibro(daAggiornare);
	}

	public IBeanIDFormatter<AutoreLibro> getIdFormatter() {
		return idFormatter;
	}
}