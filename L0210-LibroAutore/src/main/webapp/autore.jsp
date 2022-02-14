<%@page import="model.Autore"%>
<%@page import="java.util.List"%>

<%@include file="header.jsp" %>

	<table>
	<tr> 
		<th>ID</th>	
		<th>Nome</th>
		<th>Cognome</th>
		<th>Nazionalità</th>	
	</tr>
 
	<%
 		List<Autore> lista = (List<Autore>) request.getAttribute("listaAutori");
		if(lista!=null){
			for(Autore p : lista){
				out.print("<tr>");
				out.print("<td>" + p.getId() + 	 "</td>");
				out.print("<td>" + p.getNome() + "</td>");
				out.print("<td>" + p.getCognome() + "</td>");
				out.print("<td>" + p.getNazionalita() + "</td>");
 
				out.print("<td>" + 
							"<form method = \"post\" action=\"cancellaAutore?deleteId=" + p.getId() + "\">" +
							"<input type=\"submit\">Elimina</input>" +
							"</form> </td>");
				out.print("<td>" + 
						"<form method = \"get\" action=\"modificaAutore\">"+
						"<input type=\"text\" hidden name=\"modificaId\" value=\""+p.getId()+ "\" >" +
						"<input type=\"submit\">Modifica</input>" +
						"</form> </td>");
				out.print("</tr>");
			}
		}else{
			out.print("<br> Non ci sono autori in lista <br>");
		}
 
 
	%>
 
	</table>
 
	<% 
	String msg=(String)request.getAttribute("avvisoMessaggio");
	if(msg!=null){
		out.print("<script>alert(\""+msg+"\");</script>");
	}
	%>
 
</body>
</html>