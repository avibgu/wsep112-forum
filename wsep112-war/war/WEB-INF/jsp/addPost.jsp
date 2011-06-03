<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		
		
	</head>
	
	<body>
	
		<h1 style="font-size: large;" style="color: white" align="center"><b>Add Post</b></h1>
	
		<form name="AddPostForm" action="forum" method="post">
		
			<p align="center">	<big><b>Enter the Post details:</b></big>
				
			<table align="center">
				
				<tr><td>	Title:	</td><td>	<input type="text" id="postTitle" size="25" style="width: 430px; "/>		</td></tr>
				<tr><td>	Body:	</td><td>
				<BR>
            		<TEXTAREA id="postBody"  COLS= 50 ROWS="18"></TEXTAREA>
            	<BR>
            			</td></tr>
			</table>
			
			<p align="center" style="width: 844px; height: 46px">	
			<button onclick="loadAddedThread(<%=session.getAttribute("ThreadId")%>)" class="buttonsStyle"> Add Thread </button>
		

		</form>
		
	</body>
	
</html>