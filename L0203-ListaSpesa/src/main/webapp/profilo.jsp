

	<%@page import="model.Prodotto"%>
	<%@page import="java.util.List"%>

	<%@ include file="header.jsp"%>
	<%@ include file="authCheck.jsp"%>
	<h1>Profilo Utente</h1>
	
	<%
		String nome = (String) request.getAttribute("nome");
		if(nome == null)
			request.getRequestDispatcher("login").forward(request,response);
		String cognome = (String) request.getAttribute("cognome");
		out.print("Ciao " + nome + " " + cognome + ", benvenuto nel nostro sito!<br>");
		
		out.print("Autenticato con: " + session_mail_id);
	%>

	<a href="lista.jsp">shopping cart</a>
	<%@ include file="footer.jsp"%>
