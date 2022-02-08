<%@ include file="header.jsp"%>

	<h1>Login error!</h1>

	<h1>
	<%=request.getAttribute("error").toString()%>
	</h1>

<%@ include file="footer.jsp"%>
