<%@page import="java.util.Map.Entry"%>
<%@page import="model.InputFormData"%>
<%@page import="java.util.Map"%>
<%@include file="../header.jsp" %>
<%@include file="../innerMenu.html"%>


	<%
		String avviso = (String)request.getAttribute("avvisoMessaggio");
		if(avviso != null){

			%>
				<h1>BO</h1>
			<%
			//TO DO fai alert
		}
		else{
	%>

	<form method="post" action="element">
		<%
			Map<String,InputFormData> datas = 
				(Map<String,InputFormData>) request.getAttribute("item_data");
			for(Entry<String,InputFormData> pair : datas.entrySet()){
				out.print("<label>"+pair.getKey()+":</label>"
					+"<input type=\""+pair.getValue().getType()
					+"\" name=\""+pair.getKey()+"\" value=\""
					+pair.getValue().getValue()+"\"><br>");
			}
		%>
		
		<input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
		<input type="submit" value="Salva">
	</form>
	
	<%} %>
 
	<% 
		String msg=(String)request.getAttribute("avvisoMessaggio");
		if(msg!=null){
			out.print("<script>alert(\""+msg+"\");</script>");
		}
	%>
 
<%@include file="../footer.html" %>