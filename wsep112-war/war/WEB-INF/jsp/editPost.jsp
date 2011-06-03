<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="titlePost" scope="request" type="java.lang.String" />
<jsp:useBean id="bodyPost" scope="request" type="java.lang.String" />

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<h1 style="font-size: large;" align="center">Edit Post</h1>
	
		<form name="AddPostForm" action="forum" method="post">
		
			<p align="center">	<big><b>Edit the post details:</b></big>
				
			<table align="center">
				
				<tr><td>	Title:	</td><td>	<BR>
            		<TEXTAREA id="postTitleEdit" COLS= 50 ROWS="1"><%=titlePost %></TEXTAREA>
            	<BR>
				<tr><td>	Body:	</td><td>
				<BR>
            		<TEXTAREA id="postBodyEdit" COLS= 50 ROWS="18"><%=bodyPost %></TEXTAREA>
            	<BR>
            			</td></tr>
			</table>
			
		<div align="center">
			<button onclick="loadEditListPosts(<%= session.getAttribute("ThreadId")%>)"> Change </button>
		</div>
		

		</form>
		
	</body>
	
</html>