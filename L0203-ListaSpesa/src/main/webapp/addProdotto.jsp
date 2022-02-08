<%@ include file="header.jsp"%>

	<form class="new-task-form" method="post" action="aggiungiProdotto">
		<input id="new-task-input" type="text" name="nome">
		<input id="new-task-input" type="number" step= 0.01 name="prezzo"> 
		<input id="new-task-input" type="text" name="reparto">
		<input id="new-task-submit" type="submit" value="Aggiungi!">
	</form>
		
<%@ include file="footer.jsp"%>