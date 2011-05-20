<%@ page import="java.lang.String" %>
<jsp:useBean id="window" scope="request" type="java.lang.String" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />
<jsp:useBean id="users_to_add" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_remove" scope="request" type="java.util.Vector<java.lang.String>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
			<style type="text/css">
			body { background:#CC99FF  url("http://www.wallcoo.com/cartoon/abstract_colors_1920x1200_1112/wallpapers/1440x900/abstract_color_background_picture_8015.jpg") no-repeat fixed center;
		    font:italic bold 12px/30px Georgia, serif;
	    	font-size: large;}
	    	h1{
	    		color: #660066;
	    	}
	    </style>
	</head>
	
	<body bgcolor="#CC99FF">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.3.min.js">
		</script>
		<h1 align="center" style="color: white">	Forum Web Client	</h1>
		<td style="text-align: center; width: 923px" width="900">
					<%
						if (window.equals("forums")){
					%>
							<jsp:include page="forumsList.jsp" />
					<%
						}
						else if (window.equals("posts")){
					%>
							<jsp:include page="postsList.jsp" />
					<%
						}
						else if (window.equals("threads")){
					%>
							<jsp:include page="threadsList.jsp" />
					<%
						}
						else if (window.equals("addThread")){
						%>
							<jsp:include page="addThread.jsp" />
						<%
						}
						else if (window.equals("addPost")){
						%>
							<jsp:include page="addPost.jsp" />
						<%
						}
						else if (window.equals("editPost")){
						%>
							<jsp:include page="editPost.jsp" />
						<%
						}
					%>

				</td><table align="left">
		
			<tr align="center">
				
				<td width="150">
				
					<table width="150">
					
						<tr><h4>	Hello <%=username%>,					</h4></tr>
					
						<tr><h5>	Your Friends:							</h5></tr>

						<tr>		<jsp:include page="friendsList.jsp" />	</tr>
						
						<form name="addFriendForm" action="forum" method="post">
			
							<tr><h5>	Add Friend:									</h5></tr>
							
							<select name="addFriendName"  onChange="value = this.options[this.selectedIndex].text">
							<%
								for(String user: users_to_add){
							%>
							   <option value=<%=user%>><%=user%></option>
						    <%
								}
							%>
						    </select>
							
							<tr>		<input type="submit" value="Add Friend">	</tr>
							
						</form>
						
						<br>
						
						<form name="removeFriendForm" action="forum" method="post">
			
							<tr><h5>	Remove Friend:									</h5></tr>
							
							<select name="removeFriendName"  onChange="value = this.options[this.selectedIndex].text">
							<%
								for(String user: users_to_remove){
							%>
							   <option value=<%=user%>><%=user%></option>
						    <%
								}
							%>
						    </select>

							<tr>		<input type="submit" value="Remove Friend">	</tr>
							
						</form>

						<br>
						<br>

						<form name="LogoutForm" action="login" method="get">
			
							<tr>		<input type="submit" value="Logout" name="logoutButton">	</tr>
							
						</form>
						
						<br>

						<tr>	<jsp:include page="notification.jsp" />	</tr>
						
					</table>
					
				</td>	
				
			</tr>
		
		</table>

				<script type="text/javascript">

		</script>
		
	</body>
	
</html>