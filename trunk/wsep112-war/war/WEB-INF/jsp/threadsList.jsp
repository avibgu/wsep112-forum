<%@ page import="common.forum.items.ThreadInfo" %>
<jsp:useBean id="threads_list" scope="request" type="java.util.List<common.forum.items.ThreadInfo>" />



<html>
<head>

		<style type="text/css">
			body { background:#CC99FF  url('flower.jpg') no-repeat fixed center;
		    font:italic bold 12px/30px Georgia, serif;
	    	font-size: large;}
	    	h1{
	    		color: #660066;
	    	}
	    </style></head>
	<body>
		<form name="loginForm" action="forum" method="get">
		<br><br><ul style="list-style: none;">
		
			<%
				for(ThreadInfo thread: threads_list){
					
			%>
			<li style="text-align: center; font-size: 24px; white-space: normal; line-height: normal; text-transform: none; font-style: normal; display: list-item">
				<a href="forum?id=<%=thread.getThread_id()%>&window=posts"><%=thread.getTitle()%></a>
				
			</li>		
			
			<% } %>	
			<p align="center">	<br><br><input type="submit" value="Add Thread" name=AddThreadButton style=" height: 42px; width: 128px;  background-color: #9999FF; font-size: 100% " />
				
		</ul>

	</body>

</html>
