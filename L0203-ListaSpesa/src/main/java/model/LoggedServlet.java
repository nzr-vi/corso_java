package model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.AccountException;

import controller.CartCtrl;
import dal.PersonaDAO;
import exceptions.NoLoginException;

public abstract class LoggedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected CartCtrl ctrl;
	
	protected LoggedServlet() {
        super();
        ctrl = new CartCtrl();
    }

    protected void get(PersonaDAO user, HttpServletRequest request, HttpServletResponse response) throws 
    	NoLoginException, AccountException, ServletException, IOException {}
    protected void post(PersonaDAO user, HttpServletRequest request, HttpServletResponse response) throws 
	    NoLoginException, AccountException, ServletException, IOException {}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {		
			PersonaDAO user = ctrl.getPerson(request.getSession());
			this.get(user, request, response);
		}catch (NoLoginException e) {
			request.getRequestDispatcher("login.jsp");
		}
		catch (AccountException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("login_error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PersonaDAO user = ctrl.getPerson(request.getSession());
			this.post(user, request, response);
		}catch (NoLoginException e) {
			request.getRequestDispatcher("login.jsp");
		}
		catch (AccountException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("login_error.jsp");
		}
	}

}
