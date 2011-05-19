<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
		<style type="text/css">
			body { background:#CC99FF  url('flower.jpg') no-repeat fixed center;
		    font:italic bold 12px/30px Georgia, serif;
	    	font-size: large;}
	    	h1{
	    		color: #660066;
	    	}
	    </style>
 
	</head>
	
	<body bgcolor="#CC99FF">
	
		<h1 align="center">Forum Web Client</h1>
	
		<form name="RegistrationForm" action="reg" method="post">
		
			<p align="center"><br>Enter the following information for registration:<br><br>
				
			<table align="center">
				
				<tr><td>	First Name:	</td><td>	<input type="text" name="firstName" />		</td></tr>
				<tr><td>	<br>Last Name:	</td><td>	<br><input type="text" name="lastName" />		</td></tr>
				<tr><td>	<br>Username:	</td><td>	<br><input type="text" name="username" />		</td></tr>
				<tr><td>	<br>Password:	</td><td>	<br><input type="password" name="password" />	</td></tr>
				<tr><td>	<br>EMail:		</td><td>	<br><input type="text" name="email" />			</td></tr>
			
			</table>
			
			<br><p align="center">	<input type="submit" value="Register" style=" height: 42px; width: 128px;  background-color: #9999FF; font-size: 100% " />
			<br><br>or <a href="login">Login to the Forum</a>

		</form>
		
	</body>
	
</html>