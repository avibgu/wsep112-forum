<%@ page import="java.lang.String" %>
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
	    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
		<style>
			body {
			  font-family: 'Wire One', serif;
			  font-size: 25px;
			  font-style: normal;
			  font-weight: 400;
			  text-shadow: none;
			  text-decoration: none;
			  text-transform: none;
			  letter-spacing: 0em;
			  word-spacing: 0em;
			  line-height: 1.2;
			}
		</style>
	    <script type='text/javascript' src='http://code.jquery.com/jquery-latest.js'></script>
		<script>
			function checkNotifications()
			{
				$.get('notifications', function(data) {
					$('#notifications').html(data);
				});
				setTimeout('checkNotifications()', 10000);
			}
			
			function check()
			{
				$.get('forumsList', function(data) {
					$('#windowToLoad').html(data);
				});
				setTimeout('check()', 10000);
			}
			
			$(document).ready(function()
			{
			    setTimeout('check()', 1000);
				setTimeout('checkNotifications()', 1000);
			});
		</script>
    
	</head>
	
	<body bgcolor="#CC99FF" >
		<h1 align="center" style="color: white">	Forum Web Client	</h1>
		<table>
			<tr>
				<td width="150" valign="top">
					<table>
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
							
							<tr>		<input type="submit" value="Add Friend" style=" height: 28px; width: 95px;  background-color: Navy; font-size: 65%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif">	</tr>
							
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

							<tr>		<input type="submit" value="Remove Friend" style=" height: 28px; width: 95px;  background-color: Navy; font-size: 65%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif">	</tr>
							
						</form>

						<br>
						<br>

						<form name="LogoutForm" action="login" method="get">
			
							<tr>		<input type="submit" value="Logout" name="logoutButton" style=" height: 28px; width: 95px;  background-color: Navy; font-size: 65%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif">	</tr>
							
						</form>
						<div id="notifications"></div>
					</table>
				</td>
				<td width="750" valign="top" align="center">
					<div id="windowToLoad"></div>
				</td>
			</tr>
		</table>
		
	</body>
	
</html>