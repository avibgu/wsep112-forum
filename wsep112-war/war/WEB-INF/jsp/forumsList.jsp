<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>

	<body>

		<ul>
		
			<%
				for(ForumInfo forum: forums_list){
					
			%>
			<li>
				<a href="forum?id=<%=forum.getForumId()%>&window=threads"><%=forum.getName()%></a>
				
			</li>		
			<% } %>	
			
		
		</ul>

	</body>

</html>
