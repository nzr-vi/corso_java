package autor.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;
import exception.LibreriaException;

@WebServlet("/autore/delete")
public class DeleteServlet extends AutorServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if (this.ctrl.deleteAutore(id)) 
				request.setAttribute("message", "Autore #" + id + " have been successfully deleted");
			else 
				request.setAttribute("post-error", "unknow error, delete failed");
		} catch (EmptyQueryResulException | NumberFormatException | NullPointerException e) {
			request.setAttribute("post-error", "parameter id is null or invalid");
		} catch (SQLException e) {
			request.setAttribute("post-error", e.getMessage());
		} 
		new ListServlet().doGet(request, response);
	}
}
