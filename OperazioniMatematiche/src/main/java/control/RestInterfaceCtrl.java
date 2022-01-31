package control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.EquationException;
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

		String body = req.getReader().lines()
			.collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObj = new JSONObject(body);	
		String equation = jsonObj.getString("equation");
		try {
			this.interpreter.setEquation(equation.substring(0,equation.length()-2).replace(',', '.'));
			this.interpreter.interpret();
			jsonObj.put("result", this.interpreter.getEquationResult().toString());
			
			List<String> flowSequence = this.interpreter.getExecutionFlow();
			if(flowSequence.size()>0) 
				for(int i = 0; i<flowSequence.size(); i++) 
					jsonObj.put("# "+(i+1), flowSequence.get(i));
			
		} catch (EquationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// dire al browser che gli restituiamo un json
		resp.setContentType("application/json");		
		
		// restituire i dati con il writer
		resp.getWriter().append(jsonObj.toString());		
	}
}
