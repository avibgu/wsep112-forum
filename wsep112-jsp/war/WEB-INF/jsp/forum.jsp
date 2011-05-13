<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<%
			java.lang.String name = "Avi";
		%>
	
		<h1 align="center">	Forum Web Client	</h1>

		<table>
		
			<tr>
				
				<td width="150">
				
					<table width="150">
					
						<h4>	Hello <%=	name	%>,		</h4>
					
						<h5>	Your Friends:				</h5>
					
						<tr>
							<ul>
								<li>Friend1</li>
								<li>Friend2</li>
								<li>Friend3</li>
							</ul>
						</tr>
						
						<tr>	<button name="removeFriendButton" type="button">	Remove Friend	</button>	</tr>
						
						<br/>
						<br/>
						
						<h5>	Add Friend:	</h5>
						
						<tr>	<input type="text" name="friendName" />	</tr>	<br/>
						
						<tr>	<button name="addFriendButton" type="button">		Add	Friend	</button>	</tr>
						
					
					</table>
					
				</td>
				
				<td>

					<jsp:include page="forumsList.jsp"/>
					
				</td>
				
			</tr>
		
		</table>
		
	</body>
	
</html>