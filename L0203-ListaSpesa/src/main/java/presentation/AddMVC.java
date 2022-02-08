package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoggedServlet;
import model.Prodotto;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.AccountException;

import controller.CartCtrl;
import dal.Database;
import dal.PersonaDAO;
import exceptions.NoLoginException;

@WebServlet("/aggiungiProdotto")
public class AddMVC extends LoggedServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void post(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {

		CartCtrl.addProduct(user, request.getParameter("nome"),
				Double.parseDouble(request.getParameter("prezzo")),
				request.getParameter("reparto"));

		request.setAttribute("lista", user.getShoppingCart());
		request.getRequestDispatcher("lista.jsp").forward(request, response);
	}
}
