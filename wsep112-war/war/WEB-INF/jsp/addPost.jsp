<%@ page import="java.lang.String" %>
<jsp:useBean id="ThreadId" scope="session" type="java.lang.String" />

<html>
	
	<body>
	
		<p align="center">
			<a style="text-align: center;" onclick="loadPostsList(<%=ThreadId%>)"  href="#"><img src="http://png-5.findicons.com/files/icons/756/ginux/16/backward.png"> Back </a>
		</p>
		
		<h3 align="center">	Add Post	</h3>
		
		<p align="center">	Enter the Post details:	</p>

		<table align="center">
			
			<tr>
				<td>	Title:	</td>
				<td>	<input type="text" id="postTitle" size="25" style="width: 420px; "/>	</td>
			</tr>
			
			<tr></tr>
			
			<tr>
				<td valign="top">	Body:	</td>
				<td> <TEXTAREA id="postBody" COLS= 50 ROWS="20"></TEXTAREA>	</td>
			</tr>
			
		</table>
		
		<br>
		
		<div align="center">
			<button onclick="loadAddedPosts(<%=ThreadId%>)" class="buttonsStyle">
				<img src="http://png-5.findicons.com/files/icons/1035/human_o2/16/document_new.png"> Add Post
			</button>
		</div>
		
	</body>
	
</html>