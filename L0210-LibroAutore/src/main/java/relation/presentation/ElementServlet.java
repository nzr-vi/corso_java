package relation.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;

@WebServlet("/relation/element")
public class ElementServlet extends RelationServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int[] ids = this.retriveIds(request.getParameter("id"));
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("item_data", this.ctrl.getDataMap(this.ctrl.get(ids[0],ids[1])));
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

			int[] ids = this.retriveIds(request.getParameter("id"));
			int lid = Integer.parseInt(request.getParameter("libro_id"));
			int aid = Integer.parseInt(request.getParameter("autore_id"));
			int rowUpdated = this.ctrl.update(ids[1],ids[0],aid,lid);

			if (rowUpdated == 1) {
				
				request.setAttribute("message", "Book #" + request.getParameter("id") + " have been successfully updated");
				new ListServlet().doGet(request, response);
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
