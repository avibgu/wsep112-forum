<%@ page import="java.lang.String" %>
<jsp:useBean id="titlePost" scope="request" type="java.lang.String" />
<jsp:useBean id="bodyPost" scope="request" type="java.lang.String" />
<jsp:useBean id="ThreadId" scope="session" type="java.lang.String" />

<html>
	
	<body>
	
		<p align="center">
			<a style="text-align: center;" onclick="loadPostsList(<%=ThreadId%>)"  href="#"><img src="http://png-5.findicons.com/files/icons/756/ginux/16/backward.png"> Back </a>
		</p>
		
		<h1 align="center">	Edit Post	</h1>
		
		<p align="center">	Edit the post details:	</p>

		<table align="center">
			
			<tr>
				<td>	Title:	</td>
				<td>	<TEXTAREA id="postTitleEdit" COLS= 50 ROWS="1"><%=titlePost %></TEXTAREA>	</td>
			</tr>
			
			<tr></tr>
			
			<tr>
				<td valign="top">	Body:	</td>
				<td> <TEXTAREA id="postBodyEdit" COLS= 50 ROWS="18"><%=bodyPost %></TEXTAREA>	</td>
			</tr>
			
		</table>
		
		<br>
		
		<div align="center">
			<button onclick="loadEditListPosts(<%=ThreadId%>)" class="buttonsStyle"><img src="http://png.findicons.com/files/icons/77/icandy_junior_toolbar/16/write.png"> Change </button>
		</div>
		
	</body>
	
</html>