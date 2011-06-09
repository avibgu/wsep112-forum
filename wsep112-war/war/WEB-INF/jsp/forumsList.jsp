<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>

	<body>

		<br>
		
		<p align="center">	Please choose one of the forums:	</p>

	<%
		for(ForumInfo forum: forums_list){	
	%>

		<div align="center">
			<a href="#" onclick="loadThreadsList(<%=forum.getForumId()%>)"><img src="http://png-5.findicons.com/files/icons/1670/smashy_the_alien/32/contact.png"><%=forum.getName()%> </a>
		</div>
		
		<br>
	
	<%
		}
	%>

	</body>
	
</html>
