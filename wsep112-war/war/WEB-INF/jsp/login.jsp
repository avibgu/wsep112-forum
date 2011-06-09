<jsp:useBean id="error" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<head>

		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
				
		<title>Forum Web Client</title>
		
		<link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
			<%@ include file="../styles/styles.css" %>
		</style>

	</head>
	
	<body class="pageStyle">
	
		<h1 align="center"><b>Forum Web Client</b></h1>
	
		<form name="loginForm" action="login" method="post">
			
			<br>
			
			<p align="center">Please login:</p>
			
			<p align="center" style="color: red">	<%=error%>	</p>
			
			<table align="center" style="width: 359px; ">
				
				<tr><td>	Username:	</td><td style="width: 197px; ">	<input type="text" name="username" />		</td></tr>
				<tr></tr>
				<tr><td>	Password:	</td><td>	<input type="password" name="password"/>	</td></tr>
			
			</table>
			
			<br>
			
			<p align="center"><input src="http://www.jobcanvas.com/images/buttons/login_button.png" type="image" alt="submit" value="Login"/> or <a href="reg">Register to the Forum</a></p>
			
		</form>
		
	</body>
	
</html>