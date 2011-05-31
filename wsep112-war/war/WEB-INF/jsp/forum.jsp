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
			body { background:#555  url("http://www.reka.us//rekaim/Fantasy/This_Chosen_Tree_Wallpaper_by_P0RG.jpg") no-repeat fixed center;
		    font: bold 20px COMIC SANS MS;;
	    	font-size: large;}
	    	h1{
	    		color: #555;
	    	}
	    </style>
	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		<style>
			body {
			  font-family: COMIC SANS MS;
			  font-size: 20px;
			  font-style: bold;
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
			
			function loadForumsList()
			{
				$.get('forumsList', function(data) {
					$('#windowToLoad').html(data);
				});
			}
			
			function loadThreadsList(forumId)
			{
				$.get('threadsList', {id : forumId}, 
						function(data) {
							$('#windowToLoad').html(data);
				});
			}
			
			function loadAddedThread(forumId)
			{	
				alert($('#threadTitle'));
			
				$.get('threadsList', {id : forumId,  FillThreadDetails: "FillThreadDetails"}, 
						function(data) {
							$('#windowToLoad').html(data);
				})
				.Error(function(){alert("error");});
			}			
			
			function addThread()
			{
				$.get('addThread',function(data) {
						$('#windowToLoad').html(data);
				});
			}
			
			function loadPostsList(threadId)
			{
				$.get('postsList', {id : threadId}, 
						function(data) {
							$('#windowToLoad').html(data);
				});
			}
			
			function loadFriendsList()
			{
				$.get('friends', function(data) {
						$('#friendLoad').html(data);
				});
			}
			
			$(document).ready(function()
			{
				setTimeout('checkNotifications()', 1000);
				loadForumsList();
			});
		</script>
    
	</head>
	
	<body bgcolor="#CC99FF" style="height: 275px; ">
		<h1 align="center" style="color: white">	Forum Web Client	</h1>
		<table style="height: 197px; ">
			<tr>
				<td width="150" valign="top" style="height: 143px; ">
					<table>
					<div id="friendLoad"></div>
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
							
							<tr>		<input type="submit" value="Add" style=" height: 28px; width: 80px;  background-color: Black; font-size: 65%; color: White; font-family: COMIC SANS MS;">	</tr>
							
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

							<tr>		<input type="submit" value="Remove" style=" height: 28px; width: 80px;  background-color: Black; font-size: 65%; color: White; font-family: COMIC SANS MS;">	</tr>
							
						</form>

						<br>
						<br>

						<form name="LogoutForm" action="login" method="get">
			
							<tr>		<input type="submit" value="Logout" name="logoutButton" style=" height: 28px; width: 80px;  background-color: Black; font-size: 65%; color: White; font-family: COMIC SANS MS;">	</tr>
							
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