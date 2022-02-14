package autor.presentation;

import jakarta.servlet.http.HttpServlet;
import java.sql.SQLException;

import controller.AutoreController;
import controller.LibroController;

public abstract class AutorServlet extends HttpServlet {
	
	protected AutoreController ctrl;
	
	protected AutorServlet() {
		try {
			this.ctrl = AutoreController.getController();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
