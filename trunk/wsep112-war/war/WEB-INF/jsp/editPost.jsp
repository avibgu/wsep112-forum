<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="title" scope="request" type="java.lang.String" />
<jsp:useBean id="body" scope="request" type="java.lang.String" />

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<h1 align="center">Edit Post</h1>
	
		<form name="AddPostForm" action="forum" method="post">
		
			<p align="center">	<big><b>Edit the post details:</b></big>
				
			<table align="center">
				
				<tr><td>	Title:	</td><td>	<BR>
            		<TEXTAREA NAME="title" COLS= 50 ROWS="1"><%=title %></TEXTAREA>
            	<BR>
				<tr><td>	Body:	</td><td>
				<BR>
            		<TEXTAREA NAME="body" COLS= 50 ROWS="20"><%=body %></TEXTAREA>
            	<BR>
            			</td></tr>
			</table>
			
			<p align="center">	<input type="submit" value="Change" name=FillEditPostDetails />
		

		</form>
		
	</body>
	
</html>