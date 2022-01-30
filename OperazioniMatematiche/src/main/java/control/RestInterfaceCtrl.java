package control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/ctrl")
public class RestInterfaceCtrl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private final EquationInterpreter interpreter;
	
	public RestInterfaceCtrl() {
		super();
		this.interpreter = new EquationInterpreter();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().append("Hello from ctrl");		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		JSONArray jsArray = new JSONArray();
		
		String body = req.getReader().lines()
			.collect(Collectors.joining(System.lineSeparator()));
		JSONObject receivedJson = new JSONObject(body);	
		jsArray.put(receivedJson);

		// dire al browser che gli restituiamo un json
		resp.setContentType("application/json");		
		
		// restituire i dati con il writer
		resp.getWriter().append(jsArray.toString());		
	}
}
