<%@ page import="common.forum.items.ThreadInfo" %>
<jsp:useBean id="threads_list" scope="request" type="java.util.List<common.forum.items.ThreadInfo>" />



<html>

	<body>

		<ul>
		
			<%
				for(ThreadInfo thread: threads_list){
					
			%>
			<li>
				<a href="forum?id=<%=thread.getThread_id()%>&window=posts"><%=thread.getTitle()%></a>
			</li>		
			<% } %>	
			
		
		</ul>

	</body>

</html>
