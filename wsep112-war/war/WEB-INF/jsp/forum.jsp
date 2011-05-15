<jsp:useBean id="window" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<%
			java.lang.String name = "Avi"; //TODO
		%>
	
		<h1 align="center">	Forum Web Client	</h1>

		<table>
		
			<tr>
				
				<td width="150">
				
					<table width="150">
					
						<h4>	Hello <%=	name	%>,		</h4>
					
						<h5>	Your Friends:				</h5>
					
						<tr>
							<jsp:include page="friendsList.jsp"/>
						</tr>
						
						<br/>
						
						
						<form name="addFriendForm" action="forum" method="post">
			
							<h5>	Add Friend:	</h5>
							
							<tr>	<input type="text" name="addFriendName" />	</tr>	<br/>

							<input type="submit" value="Add Friend" />
							
						</form>
						
						<form name="removeFriendForm" action="forum" method="post">
			
							<h5>	Remove Friend:	</h5>
							
							<tr>	<input type="text" name="removeFriendName" />	</tr>	<br/>

							<input type="submit" value="Remove Friend" />
							
						</form>

						<br/>
						<br/>
						
						<form name="LogoutForm" action="login" method="get">
			
							<input type="submit" value="Logout"/>
							
						</form>
						
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