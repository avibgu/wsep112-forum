<%@ page import="java.lang.String" %>
<jsp:useBean id="ForumId" scope="session" type="java.lang.String" />

<html>
	
	<body bgcolor="#BBF07F" style="height: 752px; ">
	
		<h1 align="center">	Add Thread	</h1>
		
		<p align="center">	Enter the thread details:	</p>
		
		<form name="AddThreadForm" action="threadsList" method="get">

			<table align="center">
				
				<tr>
					<td>	Title:	</td>
					<td>	<input type="text" id="threadTitle" size="25" style="width: 430px; "/>	</td>
				</tr>
				
				<tr>
					<td valign="top">	Body:	</td>
					<td> <TEXTAREA id="threadBody" COLS= 50 ROWS="20"></TEXTAREA>	</td>
				</tr>
				
			</table>

		</form>
		
		<div align="center">
			<button onclick="loadAddedThread(<%=ForumId%>)" class="buttonsStyle"> Add Thread </button>
		</div>
		
	</body>
	
</html>