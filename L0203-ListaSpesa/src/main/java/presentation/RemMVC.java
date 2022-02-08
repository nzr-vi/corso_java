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

import dal.Database;
import dal.PersonaDAO;
import exceptions.NoLoginException;

@WebServlet("/removeProdotto")
public class RemMVC extends LoggedServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void post(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {

		int id = Integer.parseInt( (String) request.getParameter("id"));
		List<Prodotto> lista = user.getShoppingCart();
		
		for (Prodotto p : lista) {
			if (p.getId() == id) {
				lista.remove(p);
				break;
			}
		}
		request.setAttribute("lista",lista);
		request.getRequestDispatcher("lista.jsp").forward(request, response);
	}
}