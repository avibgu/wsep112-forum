<jsp:useBean id="error" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
			<style type="text/css">
			body {
				background:#555  url("http://www.reka.us//rekaim/Fantasy/This_Chosen_Tree_Wallpaper_by_P0RG.jpg") no-repeat fixed center;
			}
	    </style>
	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		<style>
			body {
			  font-family: COMIC SANS MS;
			  font-size: 20px;
			  font-style: bold;
			  font-weight: 400;
			  text-shadow: none;
			  text-decoration: none;
			  text-transform: none;
			  letter-spacing: 0em;
			  word-spacing: 0em;
			  line-height: 1.2;
			}
		</style>
	</head>
	
	<body bgcolor="#555">
	
		<h1 align="center" style="color: white"><b>Forum Web Client</b></h1>
	
		<form name="loginForm" action="login" method="post">
			
			<p align="center" style="color: white"><br>Please login:
			
			<br><p align="center" style="color: red">	<%=error%>	</p><br>
			
			<table align="center" style="width: 359px; ">
				
				<tr><td style="color: White; ">	Username:	</td><td style="width: 197px; ">	<input type="text" name="username" />		</td></tr>
				<tr><td style="color: White; ">	<br>Password:	</td><td>	<br><input type="password" name="password" style="width: 151px; "/>	</td></tr>
			
			</table>
			
			<p align="center"><br><input type="submit" value="Login" style=" height: 32px; width: 80px;  background-color: Black; font-size: 70%; color: White;"/>
			<br><br><td style="color: White; ">or <a href="reg">Register to the Forum</a>
		</form>
		
	</body>
	
</html>