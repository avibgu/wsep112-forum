<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>
	<head>
		<style type="text/css">
			
	    	
	    </style>
	</head>
	<body>
	<form style=" width: 948px; height: 215px">
		<p style="width: 343px; "><br><b><big><p style="width: 439px; text-align: right"> Please choose one of the forums:
		</p></big></b><ul style="width: 100%; list-style-type: none; text-align: center; list-style-image: none">
		
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
