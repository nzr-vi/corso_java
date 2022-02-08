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

@WebServlet("/listaSpesa")
public class ListaMVC extends LoggedServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void get(PersonaDAO user, HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {
     
			List<Prodotto> lista = user.getShoppingCart();
			request.setAttribute("lista", lista); 			
			request.getRequestDispatcher("lista.jsp").forward(request, response);
	}

}