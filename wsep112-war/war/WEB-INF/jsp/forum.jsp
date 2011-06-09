<%@ page import="java.lang.String" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="username" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		
		<title>Forum Web Client</title>
		
	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		
		<style type="text/css">
			<%@ include file="../styles/styles.css" %>
		</style>
		
	    <script type='text/javascript' src='http://code.jquery.com/jquery-latest.js'></script>
		<script>
			<%@ include file="../scripts/scripts.js" %>
		</script>
    
	</head>
	
	<body class="pageStyle">

		<div align="left" id="notifications" class="notificationsStyle"></div>

		<div class="titlestyle">	Forum Web Client					</div>

		<div class="friendsStyle">	
			
			
			<% int date = new Date().getHours();
			   String ans="Hello"; 
			   if (date >= 5 && date < 12)
			   		ans = "Good morning ";   
			   else if (date >= 12 & date < 18)
			   		ans = "Good afternoon";
			   else if (date >= 18 & date < 22)
			   		ans = "Good evening";
			   else
			   		ans = "Good night";
			%>
			<div>	<%=ans%> <%=username%>,	</div>
			
			<div>	<br>					</div>
			
			<div id="friendLoad">			</div>
			

			<div id="changePassword"> </div>
			<br>
			<div>
				<form name="LogoutForm" action="login" method="get">
					<input src="http://www.jmsconsultants.name/client/lgp/images/header/logout_button.gif" type="image" value="Logout" alt="submit" name="logoutButton"/>
				
				</form>
			</div>
			
		</div>

		<div id="spareWindow" class="spareStyle">	<br>	</div>	

		<div id="windowToLoad" class="windowStyle"></div>

	</body>
	
</html>