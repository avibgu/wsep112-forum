<%@ page import="java.lang.String" %>
<jsp:useBean id="ForumId" scope="session" type="java.lang.String" />

<html>
	
	<body>
	
		<p align="center">
			<a style="text-align: center;" onclick="loadThreadsList(<%=ForumId%>)"  href="#"><img src="http://png-5.findicons.com/files/icons/756/ginux/16/backward.png"> Back </a>
		</p>
		
		<h3 align="center">	Add Thread	</h3>
		
		<p align="center">	Enter the thread details:	</p>

		<table align="center">
			
			<tr>
				<td>	Title:	</td>
				<td>	<input type="text" id="threadTitle" size="25" style="width: 420px; "/>	</td>
			</tr>
			
			<tr></tr>
			
			<tr>
				<td valign="top">	Body:	</td>
				<td> <TEXTAREA id="threadBody" COLS= 50 ROWS="20"></TEXTAREA>	</td>
			</tr>
			
		</table>

		<br>

		<div align="center">
			<button onclick="loadAddedThread(<%=ForumId%>)" class="buttonsStyle"><img src="http://png-5.findicons.com/files/icons/1035/human_o2/16/document_new.png"> Add Thread </button>
		</div>
		
	</body>
	
</html>