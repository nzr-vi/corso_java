package book.presentation;

import jakarta.servlet.http.HttpServlet;
import java.sql.SQLException;

import controller.LibroController;

public abstract class BookServlet extends HttpServlet {
	
	protected LibroController ctrl;
	
	public BookServlet() {
		try {
			this.ctrl = LibroController.getController();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
