

<%@page import="java.util.Date"%>
<jsp:useBean id="notification" scope="request" type="java.lang.String" />

<html>
	
	<body>
	
		<%
			Date date = new Date();
		%>
		
		</br>	<%=notification%>
		
		</br>	<%=date.getTime()%>
			
	</body>
	
</html>