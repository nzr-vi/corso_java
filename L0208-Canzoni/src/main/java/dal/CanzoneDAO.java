package dal;

import java.sql.SQLException;
import java.util.List;

import model.Canzone;

public interface CanzoneDAO {

	String GET_ALL = "SELECT * FROM canzoni";
	
	//update:	modificano il DB e ritornano un intero (righe interessate dalla richiesta)
	void addCanzone(Canzone c) throws SQLException;
	void updCanzone(Canzone c) throws SQLException;
	void delCanzone(int id) throws SQLException;
	
	//query: 	non modificano il DB e ritornano un resultSet
	Canzone getCanzione(int id) throws SQLException;
	List<Canzone> getCanzoni() throws SQLException;
}
