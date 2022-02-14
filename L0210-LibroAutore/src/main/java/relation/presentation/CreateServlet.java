package relation.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;
import exception.LibreriaException;

@WebServlet("/relation/create")
public class CreateServlet extends RelationServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		request.setAttribute("item_data", this.ctrl.getDefaultDataMap());
		request.getRequestDispatcher("/create.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (this.ctrl.create(
					Integer.parseInt(request.getParameter("autore_id")),
					Integer.parseInt(request.getParameter("libro_id"))))
				request.setAttribute("message", "Autore created successfully!");
			else
				request.setAttribute("post-error", "failed to create parameter");
		} catch (NullPointerException | NumberFormatException e) {
			request.setAttribute("post-error", "parameter is null or invalid");
		} catch (SQLException e) {
			request.setAttribute("post-error", e.getMessage());
		}
		new ListServlet().doGet(request, response);
	}
}
