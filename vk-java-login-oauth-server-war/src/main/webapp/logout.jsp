<%System.out.println(">>> logout.jsp");%>
<%System.out.println("Logout: request.getContextPath() = " + request.getContextPath());%>
<%System.out.println("Logout: Session invalidating and redirecting...");%>

<%request.getSession().invalidate();%>
<%System.out.println("Logout: Session invalidated");%>

<%response.sendRedirect(request.getContextPath());%>
<%System.out.println("Logout: redirected.");%>
