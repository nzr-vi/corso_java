<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP Tests</title>
</head>
<body>
	<h1>TEST</h1>
	<form action="utente.jsp">
		<label>nome:</label><input type="text" name="nome"> <label>cognome:</label><input
			type="text" name="cognome"> <input type="submit">
	</form>

	<form action="redirect.jsp">
		<input type="submit">
	</form>
	
	<form action="divisione.jsp">
		Numero1:<input type="text" name = "num1"/><br>
		Numero2:<input type="text" name = "num2"/>
		<input type="submit" value = "Dividi|"/>
	</form>

</body>
</html>