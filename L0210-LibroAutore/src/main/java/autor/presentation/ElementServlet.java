package autor.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;

@WebServlet("/autore/element")
public class ElementServlet extends AutorServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("item_data", this.ctrl.getDataMap(this.ctrl.getAutore(id)));
			request.getRequestDispatcher("/modify/index.jsp").forward(request, response);
			return;
		} catch (NullPointerException | EmptyQueryResulException e) {
			request.setAttribute("get-error", "parameter id is null or invalid");
		} catch (SQLException e) {
			request.setAttribute("get-error", e.getMessage());
		}
		new ListServlet().doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			int rowUpdated = this.ctrl.updateAutore(id, request.getParameter("nome"),
					request.getParameter("cognome"), request.getParameter("nazionalita"));

			if (rowUpdated == 1) {
				
				request.setAttribute("message", "Book #" + id + " have been successfully updated");
				ListServlet ls = new ListServlet();
				ls.doGet(request, response);
				return;
			}
			else if (rowUpdated == 0)
				request.setAttribute("post-error", "no book have been updated");
			else
				request.setAttribute("post-error", "unknow error, update failed");

		} catch (NumberFormatException | NullPointerException e) {
			request.setAttribute("post-error", "parameter id is null or invalid");
		} catch (SQLException e) {
			request.setAttribute("post-error", e.getMessage());
		}
			request.getRequestDispatcher("/modify/index.jsp").forward(request, response);
	}

}
