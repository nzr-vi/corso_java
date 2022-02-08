<%
		String session_mail_id = (String)request.getSession().getAttribute("email");
		if(session_mail_id == null){
			response.sendRedirect("login.jsp");	
			return;
		}
%>