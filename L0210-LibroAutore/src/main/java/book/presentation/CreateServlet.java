package book.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;
import exception.LibreriaException;

@WebServlet("/book/create")
public class CreateServlet extends BookServlet {
	
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
			if (this.ctrl.createLibro(request.getParameter("titolo"),
					Double.parseDouble(request.getParameter("prezzo")),
					Integer.parseInt(request.getParameter("pagine")),
					Integer.parseInt(request.getParameter("editore_id"))))
				request.setAttribute("message", "Book created successfully!");
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
