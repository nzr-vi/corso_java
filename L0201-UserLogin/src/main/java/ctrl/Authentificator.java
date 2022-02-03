package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import dal.Database;
import dal.UserDAO;

@WebServlet("/api/auth")
public class Authentificator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Authentificator() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = (String)request.getAttribute("name");
		String pwd = (String)request.getAttribute("pwd");
	
		Optional<UserDAO> user = Database.getInstance().getUserList()
				.stream().filter(u->u.getEmail().equals(email)).findFirst();
		
		if(user.isPresent()) {
			UserDAO checkedUser = user.get();
			if(checkedUser.getPwd().equals(pwd)) {
				if(checkedUser.isLogged()) {
					checkedUser.setLogged(true);
					request.getSession().setAttribute("user", checkedUser);
					request.setAttribute("name", checkedUser.getNome());
					request.setAttribute("surname", checkedUser.getCognome());
					request.getRequestDispatcher("profilo.jsp").forward(request, response);					
					return;
				}
				else request.setAttribute("error", "user already logged in!");
			}
			else request.setAttribute("error", "wrong username or password");				
		}
		else request.setAttribute("error", "no such user");
		
		request.getRequestDispatcher("profilo.jsp").forward(request, response);		
	}
}
