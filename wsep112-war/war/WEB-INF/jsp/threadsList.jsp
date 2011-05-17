<%@ page import="common.forum.items.ThreadInfo" %>
<jsp:useBean id="threads_list" scope="request" type="java.util.List<common.forum.items.ThreadInfo>" />



<html>

	<body>
		<form name="loginForm" action="forum?window=addThread" method="get">
		<ul style="list-style: none;">
		
			<%
				for(ThreadInfo thread: threads_list){
					
			%>
			<li>
				<a href="forum?id=<%=thread.getThread_id()%>&window=posts"><%=thread.getTitle()%></a>
			</li>		
			
			<% } %>	
			<p align="center">	<input type="submit" value="Add Thread" name=AddThreadButton />
				
		</ul>

	</body>

</html>
