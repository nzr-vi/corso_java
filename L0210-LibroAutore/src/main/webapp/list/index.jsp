<%@page import="util.IBeanIDFormatter"%>
<%@page import="util.IIdentificable"%>
<%@page import="util.IBeanTableRowFormatter"%>
<%@page import="util.Caster"%>
<%@page import="model.Libro"%>
<%@page import="java.util.List"%>

<%@include file="../header.jsp" %>
<%@include file="../innerMenu.html"%>

<%
	String[] columnTitles = (String[]) request.getAttribute("titles");
	IBeanTableRowFormatter<Object> formatter = 
			(IBeanTableRowFormatter<Object>) request.getAttribute("formatter");
	IBeanIDFormatter<Object> identifier = 
			(IBeanIDFormatter<Object>) request.getAttribute("identifier");
	String deleteServlet = (String) request.getAttribute("delete");
	String modifyServlet = (String) request.getAttribute("modify");
	String createServlet = (String) request.getAttribute("create");
%>

	<table>
	<tr> 
	<%
		for(String title : columnTitles)
			out.print("<th>"+title+"</th>");
	%>
	</tr>
 
	<%
		List<Object> itemList 
			= (List<Object>) request.getAttribute("list");
		if(itemList!=null){
			for(Object p : itemList){
				
				out.print(formatter.toHtml(p));
 
				out.print("<td>" + 
					"<form method = \"post\" action=\""+deleteServlet+"\">" +
					"<input type=\"text\" hidden name=\"id\" value=\""+identifier.getFormattedID(p)+ "\" >" +
					"<input type=\"submit\" value=\"Elimina\"></input>" +
					"</form> </td>");
				
				out.print("<td>" + 
					"<form method = \"get\" action=\""+modifyServlet+"\">"+
					"<input type=\"text\" hidden name=\"id\" value=\""+identifier.getFormattedID(p)+ "\" >" +
					"<input type=\"submit\" value=\"Modifica\"></input>" +
					"</form> </td>");
				out.print("</tr>");
			}
		}else{
			out.print("<br> Non ci sono libri in lista <br>");
		}
 
 
	%>
 
	</table>
	
	<form method = "get" action="<%=createServlet%>">
		<input type="submit" value="Aggiungi"></input>
	</form>
	
 
	<% 
		String msg=(String)request.getAttribute("avvisoMessaggio");
		if(msg!=null){
			out.print("<script>alert(\""+msg+"\");</script>");
		}
	%>
 
<%@include file="../footer.html" %>