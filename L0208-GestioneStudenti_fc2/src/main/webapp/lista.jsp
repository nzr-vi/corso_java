<%@page import="model.Studente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

	<table>
	<tr> 
		<th>ID</th>	
		<th>Nome</th>
		<th>Cognome</th>
		<th>Genere</th>
		<th>Indirizzo</th>
		<th>Provincia</th>
		<th>Regione</th>
		<th>Data Nascita</th>	
		<th>Data Inserimento</th>	
	</tr>
	
	<%
 		List<Studente> lista = (List<Studente>) request.getAttribute("list");
		if(lista!=null){
			for(Studente p : lista){
				out.print("<tr>");
				out.print("<th>" + p.getId() + "</th>");
				out.print("<th>" + p.getNome() + "</th>");
				out.print("<th>" + p.getCognome() + "</th>");
				out.print("<th>" + p.getGenere() + "</th>");
				out.print("<th>" + p.getIndirizzo() + "</th>");
				out.print("<th>" + p.getProvincia() + "</th>");
				out.print("<th>" + p.getRegione() + "</th>");
				out.print("<th>" + p.getEmail() + "</th>");
				out.print("<th>" + p.getData_nascita() + "</th>");
				out.print("<th>" + p.getIns() + "</th>");
				out.print("<th>" + 
					"<form method = \"post\" action=\"removeProdotto?id=" + p.getId() + "\">" +
					"<input type=\"submit\"><i class=\" fa fa-trash \"></i></input>" +
					"</form>"
					+ "</th>");
				out.print("</tr>");
			}
		}
		
		
	%>

	</table>
	
</body>
</html>