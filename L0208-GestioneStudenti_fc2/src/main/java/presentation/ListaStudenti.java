package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import controller.StudenteCtrl;

@WebServlet("/listaStudenti")
public class ListaStudenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudenteCtrl controller;
	
	public ListaStudenti() throws ClassNotFoundException, SQLException {
		super();
		controller = StudenteCtrl.getController();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("list", controller.getAllStudent());
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("lista.jsp").forward(request, response);
	}

}
