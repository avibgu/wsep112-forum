<%@ page import="common.forum.items.ThreadInfo" %>
<jsp:useBean id="threads_list" scope="request" type="java.util.List<common.forum.items.ThreadInfo>" />



<html>
<head>

		<style type="text/css">
			body { background:#CC99FF  url("http://www.wallcoo.com/cartoon/abstract_colors_1920x1200_1112/wallpapers/1440x900/abstract_color_background_picture_8015.jpg") no-repeat fixed center;
		    font:italic bold 12px/30px Georgia, serif;
	    	font-size: large;}
	    	h1{
	    		color: #660066;
	    	}
	    </style></head>
	<body>
		<form name="loginForm" action="forum" method="get">
		<a style="text-align: center;" href="forum"><p align="justify"><i><p align="center">Back</p></i></p>
		</a>
		<p align="center"><b><big><p align="center">Please choose one of the threads:
		</p></big></b><ul style="list-style: none;">
		<br>
		
		<TABLE align="center" BORDER="1" style=" height: 156px; width: 403px;">
                <TR> 
                
                
                </TR>
               <%
				for(ThreadInfo thread: threads_list){
					
			%>
				    <TR>
				    <TD style="width: 309px; font-size: 12px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal" "> 
				    <p style="text-align: center; font-size: 14px; white-space: normal; line-height: normal; text-transform: none; font-style: normal; display: list-item; list-style: none;">
					<a href="forum?id=<%=thread.getThread_id()%>&window=posts"><%=thread.getTitle()%></a>
				    </TD>
             	     
                       
				    </TR>
				    <p align="center">	
				<% } %>
				
             </TABLE>
		
		<ul style="list-style: none;">
		
			<p align="center">	<br><br><input type="submit" value="Add Thread" name=AddThreadButton style=" height: 42px; width: 139px;  background-color: Navy; font-size: 100%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif" />
				
		</ul>
	</form>
	</body>

</html>
