<%@ page import="java.lang.String" %>
<jsp:useBean id="username" scope="request" type="java.lang.String" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		
		<title>Forum Web Client</title>
		
	    <link  href="http://fonts.googleapis.com/css?family=Architects+Daughter:regular" rel="stylesheet" type="text/css" >
		
		<style type="text/css">
			<%@ include file="../styles.css" %>
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
				alert($('#threadTitle').val());
				alert($('#threadBody').val());
				alert(forumId);
				$.get('threadsList', {idOfForum : forumId, title: $('#threadTitle').val(), body: $('#threadBody').val()}, 
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
	
	<body class="pageStyle">

		<div align="left" id="notifications" class="notificationsStyle"></div>

		<div class="titlestyle">	Forum Web Client					</div>

		<div class="friendsStyle">	

			<div>	Hello <%=username%>,	</div>
			
			<div>	<br>					</div>
			
			<div>	Your Friends:			</div>
			
			<div id="friendLoad">			</div>
			
			<div>	<br>					</div>
			<div>	<br>					</div>

			<div>
				<form name="LogoutForm" action="login" method="get">

					<input type="submit" value="Logout" name="logoutButton"/>
				
				</form>
			</div>
			
		</div>

		<div id="windowToLoad" class="windowStyle"></div>			
			
	</body>
	
</html>