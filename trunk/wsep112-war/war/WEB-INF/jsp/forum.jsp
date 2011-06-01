<%@ page import="java.lang.String" %>
<jsp:useBean id="username" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		
		<title>Forum Web Client</title>
		
	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		<style>
			body {
			  background:#555  url("http://www.reka.us//rekaim/Fantasy/This_Chosen_Tree_Wallpaper_by_P0RG.jpg") no-repeat fixed center;
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
			  color: white;
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
			
			function addFriend()
			{ 
								
				$.get('friends',{Addfriend : $('#addFriendName').val()},
				 function(data) {
						
						$('#friendLoad').html(data);
				});
			}
			
			function removeFriend()
			{
				
				$.get('friends',{Removefriend : $('#removeFriendName').val()},
				 function(data) {
						$('#friendLoad').html(data);
				});
			}
			
			$(document).ready(function()
			{
				setTimeout('checkNotifications()', 1000);
				loadForumsList();
				loadFriendsList();
			});
		</script>
    
	</head>
	
	<body>

		<div align="left" id="notifications"></div>

		<div>	<h1 align="center">	Forum Web Client	</h1>	</div>

		<div>	<table>
			
					<tr>
						<td width="150" valign="top">
							
							<table>
							
								<tr>
										<div>	Hello <%=username%>,	</div>
										<div>	Your Friends:			</div>
								</tr>
								
		
								<tr>	<div id="friendLoad"></div> </tr>
		
								
								</br>
		
								</br>
		
								<form name="LogoutForm" action="login" method="get">
					
									<tr>	<input type="submit" value="Logout" name="logoutButton">	</tr>
									
								</form>
								
							</table>
							
						</td>
						
						<td width="650" valign="top" align="center">
							<div id="windowToLoad"></div>
						</td>
						
					</tr>
					
				</table>
		</div>
			
	</body>
	
</html>