
<%@ include file="header.jsp"%>

	<h1>Login Page</h1>
	<br>

	<form action="login" method="post">
		<label>Email:</label><input type="text" name="email" /> <br>
		<label>Password:</label><input type="password" name="password" /> <br>
		<input type="submit" value="login" />
	</form>

<%@ include file="footer.jsp"%>
