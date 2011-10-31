<%System.out.println(">>> logout.jsp: begin");%>

<%
request.getSession().invalidate();
response.sendRedirect(request.getContextPath());
%>
