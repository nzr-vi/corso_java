package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoggedServlet;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.login.AccountException;

import dal.Database;
import dal.PersonaDAO;
import exceptions.NoLoginException;

@WebServlet("/login")
public class LoginCtrl extends LoggedServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void get(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {
		
		request.setAttribute("nome", user.getNome());
		request.setAttribute("cognome", user.getCognome());
		request.getRequestDispatcher("profilo.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Database db = Database.getDatabase();
		Map<String,PersonaDAO> map = db.getPersone();
		if(map.containsKey(email)) {
			PersonaDAO user = map.get(email);
			if (user.getPassword().equals(password)) {
				if (user.isLogin()) {
					// PERSONA GIà AUTENTICATA
				} else {
					user.setLogin(true);
					req.getSession().setAttribute("email", user.getEmail());
					req.setAttribute("nome", user.getNome());
					req.setAttribute("cognome", user.getCognome());
					req.getRequestDispatcher("profilo.jsp").forward(req, res);
					return;
				}
			} 
		}
		res.sendRedirect("login.jsp");
	}
}
