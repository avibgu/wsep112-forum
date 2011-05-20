<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>
	<head>
		<style type="text/css">
			
	    	
	    </style>
	</head>
	<body>
	<form>
		<p align="center"><br><b><big><p align="center">Please choose one of the forums:
		</p></big></b><ul style="list-style: none;">
		
			<%
				for(ForumInfo forum: forums_list){
					
			%>
			<li style="text-align: center; font-size: 24px; white-space: normal">
			
				<a style="text-align: center;" href="forum?id=<%=forum.getForumId()%>&window=threads"><%=forum.getName()%>
					</a>
				
				
			</li>		
			<% } %>	
			
		
		</ul>
</form>
	</body>

</html>
