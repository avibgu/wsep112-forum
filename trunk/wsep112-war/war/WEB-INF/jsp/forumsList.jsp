<%@ page import="common.forum.items.ForumInfo" %>
<jsp:useBean id="forums_list" scope="request" type="java.util.List<common.forum.items.ForumInfo>" />

<html>

	<body>

		<form style=" width: 948px; height: 215px">
			<p style="width: 343px; "><br><b><big><p style="width: 500px; text-align: center"> Please choose one of the forums:
			</p></big></b><ul style="width: 100%; list-style-type: none; text-align: center; list-style-image: none">
			
				<%
					for(ForumInfo forum: forums_list){
						
				%>
				<li style="text-align: center; font-size: 24px; white-space: normal">
				
					<a style="text-align: center;" onclick="loadThreadsList(<%=forum.getForumId()%>)"  href="#">
					<%=forum.getName()%></a>

				</li>		
				<% } %>	
				
			
			</ul>
		</form>

	</body>
	
</html>
