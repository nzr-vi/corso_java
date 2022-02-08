package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoggedServlet;

import java.io.IOException;

import javax.security.auth.login.AccountException;

import dal.Database;
import dal.PersonaDAO;
import exceptions.NoLoginException;

@WebServlet("/logout")
public class LogoutCtrl extends LoggedServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void get(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {
		if (user.isLogin()) 
		{
			user.setLogin(false);
			request.getSession().setAttribute("email", null);
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}