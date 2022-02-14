package autor.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;
import exception.LibreriaException;

@WebServlet("/autore/list")
public class ListServlet extends AutorServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("list", this.ctrl.getAllLibri());
			request.setAttribute("titles", new String[] { "ID", "Nome", "Cognome", "Nazionalita" });
			request.setAttribute("delete", "delete");
			request.setAttribute("modify", "element");
			request.setAttribute("create", "create");
			request.setAttribute("formatter", this.ctrl.getRowFormatter());
			request.setAttribute("identifier", this.ctrl.getIdFormatter());
		} catch (SQLException e) {
			request.setAttribute("get-error", e.getMessage());
		} finally {
			request.getRequestDispatcher("/list/index.jsp").forward(request, response);
		}
	}
}
