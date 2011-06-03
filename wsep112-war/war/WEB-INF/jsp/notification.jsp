
<jsp:useBean id="notification" scope="request" type="java.lang.String" />
<jsp:useBean id="date" scope="request" type="java.lang.String" />

<html>
	
	<body>
		
		<div id="date">	<%=date%>	</div>
		<div id="notificationContent" style="color:orange;"><%=notification%></div>

	</body>
	
</html>