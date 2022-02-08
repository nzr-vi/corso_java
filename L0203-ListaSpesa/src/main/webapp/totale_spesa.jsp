
<%@ include file="header.jsp"%>

<% 
	String total = (String)request.getAttribute("total");
	if(total == null)
		request.getRequestDispatcher("checkout").forward(request, response);
		//response.sendRedirect("lista.jsp");
		
	Object restVal = request.getAttribute("rest");
	boolean hasRest = restVal!=null, hasToPayMore = false;
	double rest = hasRest?((double)restVal):0;
%>

<h1>Checkout</h1>
<h3>Total:<%=total%></h3>
<br>
	<form class="new-task-form" method="post" action="checkout">
		<%
			if(hasRest){
				%>
				 	<input type="hidden" name="rest" value="<%=-rest%>">
				<%
			}
		%>
		<label>Importo:</label>
		<input id="new-task-input" type="number" step= 0.01 name="paid">
		<br>
		<input id="new-task-submit" type="submit" value="PAGA!!">
	</form>

<hr>
<%
	if(hasRest){
		if(rest>0){%>
			<h2>payment successful</h2>
			<h3>here's your rest: <%=rest%>></h3><br>
			<a href="lista.jsp">go to list</a>
<% 		}
		else{%>			
			<h3>Add: <%=-rest%> to complete the payment</h3><br>
			<a href="lista.jsp">abort and go to list</a>
<%}}%>

<%@ include file="footer.jsp"%>
