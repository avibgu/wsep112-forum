<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" scope="request" type="java.lang.String" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">

</head>
<body style="width: 215px; ">
		<div id="changePassError">
			<p style="color: red; font-size: medium;">	<%=error%>	</p>
		</div>
		<br>			
		<tr><td>	New password:	</td><td>	<input type="password" id="pass"/>	</td></tr>
		<button onclick="changePassword()" class="buttonsStyle"> Change </button>
	
</body>
</html>