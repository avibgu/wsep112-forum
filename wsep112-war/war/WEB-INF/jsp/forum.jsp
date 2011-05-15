<jsp:useBean id="window" scope="request" type="java.lang.String" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<h1 align="center">	Forum Web Client	</h1>

		<table>
		
			<tr>
				
				<td width="150">
				
					<table width="150">
					
						<tr><h4>	Hello <%=username%>,					</h4></tr>
					
						<tr><h5>	Your Friends:							</h5></tr>

						<tr>		<jsp:include page="friendsList.jsp"/>	</tr>
						
						<form name="addFriendForm" action="forum" method="post">
			
							<tr><h5>	Add Friend:									</h5></tr>
							
							<tr>		<input type="text" name="addFriendName" />	</tr>

							<tr>		<input type="submit" value="Add Friend" />	</tr>
							
						</form>
						
						</br>
						
						<form name="removeFriendForm" action="forum" method="post">
			
							<tr><h5>	Remove Friend:									</h5></tr>
							
							<tr>		<input type="text" name="removeFriendName" />	</tr>

							<tr>		<input type="submit" value="Remove Friend" />	</tr>
							
						</form>

						</br>
						</br>

						<form name="LogoutForm" action="login" method="get" >
			
							<tr>		<input type="submit" value="Logout" name="logoutButton"/>	</tr>
							
						</form>
						
						</br>

						<tr></tr>
						
					</table>
					
				</td>
				
				<td>

					<%
						if (window.equals("forums")){
					%>
							<jsp:include page="forumsList.jsp"/>
					<%
						}
						else if (window.equals("posts")){
					%>
							<jsp:include page="postsList.jsp"/>
					<%
						}
						else if (window.equals("threads")){
					%>
							<jsp:include page="threadsList.jsp"/>
					<%
						}
					%>

				</td>
				
			</tr>
		
		</table>
		
	</body>
	
</html>