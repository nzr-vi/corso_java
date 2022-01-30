<%@page import="model.Libro"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Ciao Allen<br>Si ricomincia</h1>
<%
	String s = "Gli spazi son nemici degli informatici";
	String[] init = {"do","re","mi","fa","sol","la","si"};
	List<String> note = new ArrayList<>(
			Stream.of(init).collect(Collectors.toList()));
	
	Libro l = new Libro("titolo brut");
	
	out.print("<ul>");
	
	for(int i =0; i<note.size(); i++)
		out.print("<li> nota: "+note.get(i)+"</li>");

	out.print("</ul>");
	
	out.print(l.getTitle());
%>
<h2><%=s%></h2>

<script src="dino.js"></script>
</body>
</html>