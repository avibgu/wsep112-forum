<%
	String username = request.getParameter( "username" );
	String password = request.getParameter( "password" );

	session.setAttribute( "theUsername", username );
	session.setAttribute( "thePassword", password );
%>
<html>

	<body bgcolor="#BBF07F">
	
		<p align="center">	Information Saved..
		
		<p align="center">	Username = <%=username%>
		
		<p align="center">	Password = <%=password%>
	
	</body>

</html>