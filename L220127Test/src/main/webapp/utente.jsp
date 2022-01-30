<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	String nome = request.getParameter("nome");
	String cognome = request.getParameter("cognome");
	out.print(nome+" "+cognome);
	
	response.sendRedirect("index.jsp");
%>

</body>
</html>