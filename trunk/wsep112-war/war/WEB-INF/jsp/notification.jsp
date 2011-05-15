    
<%@ page import="threads.NotificationsThread" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="java.lang.Thread" %>

<!-- <jsp:useBean id="notificationThread" scope="session" class="threads.NotificationsThread"/> -->

<html>
	
	<body>
	
		<%
			NotificationsThread t = new NotificationsThread();

			new Thread(t).start();
		
			while (true){
		%>
				<p><%=t.i %></p>
		<%
				try {
					
					Thread.sleep(2000);
				}
				catch (Exception e) { }
			}
		%>
		
	</body>
	
</html>