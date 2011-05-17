<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>Forum Web Client</title>
	</head>
	
	<body bgcolor="#BBF07F">
	
		<h1 align="center">Add Post</h1>
	
		<form name="AddPostForm" action="forum" method="post">
		
			<p align="center">	<big><b>Enter the Post details:</b></big>
				
			<table align="center">
				
				<tr><td>	Title:	</td><td>	<input type="text" name="title" size="25" style="width: 430px; "/>		</td></tr>
				<tr><td>	Body:	</td><td>
				<BR>
            		<TEXTAREA NAME="body" COLS= 50 ROWS="20"></TEXTAREA>
            	<BR>
            			</td></tr>
			</table>
			
			<p align="center">	<input type="submit" value="Add" name=FillPostDetails />
		

		</form>
		
	</body>
	
</html>