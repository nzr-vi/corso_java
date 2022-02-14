package relation.presentation;

import jakarta.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import controller.AutoreController;
import controller.AutoreLibroController;
import controller.LibroController;

public abstract class RelationServlet extends HttpServlet {
	
	protected AutoreLibroController ctrl;
	
	protected int[] retriveIds(String idToSplit) {
		return Arrays.stream(idToSplit.split(" "))
			.mapToInt(s->Integer.parseInt(s))
			.toArray();
	}
	
	protected RelationServlet() {
		try {
			this.ctrl = AutoreLibroController.getController();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
