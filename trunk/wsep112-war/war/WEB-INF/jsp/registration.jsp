<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" scope="request" type="java.lang.String" />

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>

	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		
		<style type="text/css">
			<%@ include file="../styles/styles.css" %>
		</style>
 
	</head>
	
	<body class="pageStyle">
	
		<h1 align="center" style="color: white">Forum Web Client</h1>
	
		<form name="RegistrationForm" action="reg" method="post">

			
			<p align="center">Enter the following information for registration:</p>
			
			<p align="center" style="color: red">	<%=error%>	</p>
				
			<table align="center">
				
				<tr><td>	First Name:	</td><td>	<input type="text" name="firstName" />		</td></tr>
				<tr></tr>
				<tr><td>	<br>Last Name:	</td><td>	<input type="text" name="lastName" />		</td></tr>
				<tr></tr>
				<tr><td>	<br>Username:	</td><td>	<input type="text" name="username" />		</td></tr>
				<tr></tr>
				<tr><td>	<br>Password:	</td><td>	<input type="password" name="password" />	</td></tr>
				<tr></tr>
				<tr><td>	<br>EMail:		</td><td>	<input type="text" name="email" />			</td></tr>

				</table>
				<br>
				<table border="1" align="center">
				<tr> <th>License Agreement:</th></tr>
				<tr> <td class="licenseStyle"> IMPORTANT! PLEASE READ CAREFULLY! BY COPYING, OR USING OUR FORUM(S)IN ANY MANNER, YOU AGREE TO BE BOUND BY THE TERMS OF THIS AGREEMENT AND PRIVACY POLICY.
				     IF YOU DO NOT AGREE WITH OUR END USER LICENSE AGREEMENT, PLEASE DO NOT PROCEED WITH THE REGISTRATION PROCESS AND LEAVE OUR SITES IMMEDIATELY AND DO NOT ACCESS OR USE OUR FORUM(S).
 			    </td></tr>
 			    </table>
 			    <p align="center"> <td class="licenseStyle"> <input type="checkbox" value="I agree"> I agree<br></td></p>
			
			<p align="center">
				<input type="submit" value="Register"/> or <a href="login">Login to the Forum</a>
			</p>

		</form>
		
	</body>
	
</html>