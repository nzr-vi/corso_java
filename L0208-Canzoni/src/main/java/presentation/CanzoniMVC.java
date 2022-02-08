package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.CanzoniCtrl;
import exception.ParametersException;

@WebServlet("/canzoni")
public class CanzoniMVC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CanzoniCtrl ctrl;
	

    public CanzoniMVC() {
        super();
        this.ctrl = new CanzoniCtrl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	private String getParameter(HttpServletRequest request, String key) throws ParametersException{
		
		if(request.getParameter("titolo")==null)
			throw new ParametersException("missing '"+key+"'");
		return request.getParameter("titolo");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			this.ctrl.addCanzone(
					this.getParameter(request, "titolo"),
					this.getParameter(request, "cantante"),
					this.getParameter(request, "genere"),
					this.getParameter(request, "album"));
		} catch (ParametersException e) {
			e.printStackTrace();
		}
		
	}

}
