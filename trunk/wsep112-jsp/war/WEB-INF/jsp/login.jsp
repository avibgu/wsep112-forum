<jsp:useBean id="error" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<h1 align="center">Forum Web Client</h1>
	
		<form name="loginForm" action="login" method="post">
			
			<p align="center">	Login to the Forum:
			
			<p align="center" style="color: red">	<%=error%>	</p>
			
			<table align="center">
				
				<tr><td>	Username:	</td><td>	<input type="text" name="username" />		</td></tr>
				<tr><td>	Password:	</td><td>	<input type="password" name="password" />	</td></tr>
			
			</table>
			
			<p align="center">	<input type="submit" value="Login" />
			or <a href="reg">Register to the Forum</a>
		</form>
		
	</body>
	
</html>