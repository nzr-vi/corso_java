package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoggedServlet;
import model.Prodotto;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountException;

import dal.PersonaDAO;
import exceptions.NoLoginException;

@WebServlet("/checkout")
public class CheckoutCtrl extends LoggedServlet {
	private static final long serialVersionUID = 1L;
       
	private double calculateCheckout(PersonaDAO user) {	
		return user.getShoppingCart().stream()
				.collect(Collectors.summingDouble(p->p.getPrezzo()));
	}
	
	@Override
	protected void get(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {
		request.setAttribute("total", String.format("%.2f",this.calculateCheckout(user)));
		request.getRequestDispatcher("totale_spesa.jsp").forward(request, response);
	}
	
	@Override
	protected void post(PersonaDAO user, HttpServletRequest request, HttpServletResponse response)
			throws NoLoginException, AccountException, ServletException, IOException {
		
		Object prev_rest = request.getParameter("rest");
			
		double total = prev_rest==null? this.calculateCheckout(user):- Double.parseDouble(prev_rest.toString());
		
		var importo = Double.parseDouble((String)request.getParameter("paid"));
		
		if(importo==total) {
			user.getShoppingCart().clear();
		}
		else{
			double rest = importo-total;
			request.setAttribute("rest", rest);
			if(rest>0)
				user.getShoppingCart().clear();
			this.get(user, request, response);
		}
		
	}

}
