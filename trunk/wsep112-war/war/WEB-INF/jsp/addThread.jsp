<%@ page import="java.lang.String" %>
<jsp:useBean id="ForumId" scope="session" type="java.lang.String" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		
	</head>
	
	
	<body bgcolor="#BBF07F" style="height: 752px; ">
	
		<h1 style="font-size: large;" align="center" style="color: white">Add Thread</h1><form name="AddThreadForm" action="threadsList" method="get">
		
			<p align="center">	<big><b>Enter the thread details:</b></big>
				
			<table align="center">
				
				<tr><td>	Title:	</td><td>	<input type="text" id="threadTitle" size="25" style="width: 430px; "/>		</td></tr>
				<tr><td>	Body:	</td><td>
				<BR>
            		<TEXTAREA id="threadBody" COLS= 50 ROWS="20"></TEXTAREA>
            	<BR>
            			</td></tr>
			</table>
			
			<p align="center"><button onclick="loadAddedThread(<%=ForumId%>)">Add</button></p>
			
				<input type="submit" value="Add" name="FillThreadDetails" style=" height: 42px; width: 128px;  background-color: Navy; font-size: 100%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif"/>

		</form>
		
	</body>
	
</html>