<%
	String firstName = request.getParameter( "firstName" );
	String lastName = request.getParameter( "lastName" );
	String username = request.getParameter( "username" );
	String password = request.getParameter( "password" );
	String email = request.getParameter( "email" );

	session.setAttribute( "theFirstName", firstName );
	session.setAttribute( "theLastName", lastName );
	session.setAttribute( "theUsername", username );
	session.setAttribute( "thePassword", password );
	session.setAttribute( "theEmail", email );
%>
<html>

	<body bgcolor="#BBF07F">

		<p align="center">	Information Saved..

		<table align="center">

			<tr><td>	First Name:	</td><td>	<%=firstName%>	</td></tr>
			<tr><td>	Last Name:	</td><td>	<%=lastName%>	</td></tr>
			<tr><td>	Username:	</td><td>	<%=username%>	</td></tr>
			<tr><td>	Password:	</td><td>	<%=password%>	</td></tr>
			<tr><td>	EMail:		</td><td>	<%=email%>		</td></tr>
		
		</table>

	</body>

</html>