package relation.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import exception.EmptyQueryResulException;
import exception.LibreriaException;

@WebServlet("/relation/delete")
public class DeleteServlet extends RelationServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int[] ids = this.retriveIds(request.getParameter("id"));
			if (this.ctrl.delete(ids[0],ids[1])) 
				request.setAttribute("message", "AutoreLibro #" + ids[0] 
						+ " " + ids[1] + " have been successfully deleted");
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
