<%
	String name = request.getParameter( "username" );
	String password = request.getParameter( "password" );

	session.setAttribute( "theName", name );
	session.setAttribute( "thepassword", password );
%>
<html>

	<body>
	
		<p align="center">	Information Saved..
	
	</body>

</html>