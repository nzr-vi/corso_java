<%@page import="model.Prodotto"%>
<%@page import="java.util.List"%>

<%@ include file="header.jsp"%>
<%@ include file="authCheck.jsp"%>
<br>
<a href="addProdotto.jsp">Aggiungi Prodotto</a>
<hr>
<table>

	<tr>
		<th>Nome</th>
		<th>Prezzo</th>
		<th>Reparto</th>
		<th>Azioni</th>
	</tr>

	<% 	List<Prodotto> lista =(List<Prodotto>)request.getAttribute("lista");
		if(lista==null)
			request.getRequestDispatcher("listaSpesa").forward(request, response);
	%>
	
	<% for (Prodotto p : lista){ %>
	<tr>
		<th><%= p.getNome()%></th>
		<th><%= p.getPrezzo()%></th>
		<th><%= p.getCategoria()%></th>
		<th>
			<form method="post" action="removeProdotto?id=
					<%=p.getId()%>">
				<input type="submit"><i class="fa fa-trash"></i>
			</form>
		</th>
	</tr>
	<% } %>

</table>
<br>
<a href="totale_spesa.jsp">checkout</a>

<%@ include file="footer.jsp"%>