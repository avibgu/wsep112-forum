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
					
		<tr><td>	New password:	</td><td><br>	<input type="password" id="pass" style="width: 90px; "/>	</td></tr><br>
		<button onclick="changePassword()" class="buttonsStyle" style="width: 95px;" ><img src="http://png-3.findicons.com/files/icons/2222/gloss_basic/16/arrow_undo.png"> Change </button>
	
</body>
</html>