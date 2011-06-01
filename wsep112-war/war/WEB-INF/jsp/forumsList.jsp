<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>

	<body>

		<p align="center">	Please choose one of the forums:	</p>
		
		<form>
				<%
					for(ForumInfo forum: forums_list){	
				%>

				<div align="center">
					<a href="#" onclick="loadThreadsList(<%=forum.getForumId()%>)"><%=forum.getName()%></a>
				</div>
				
				<%
					}
				%>
			
		</form>

	</body>
	
</html>
