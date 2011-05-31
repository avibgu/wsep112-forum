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
		<a style="text-align: center;" onclick="loadForumsList()"  href="#"><p align="justify"><i><p align="center">Back</p></i></p>
		</a>
		<p align="center"><b><big><p align="center">Please choose one of the threads:
		</p></big></b><ul style="list-style: none;">
		<br>
		
		<TABLE align="center" BORDER="1" style=" height: 45px; width: 786px;">
                <TR> 
                	 <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 369px"><p align="center">Subject</p></TH>
                     <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 135px; height: 36px"><p align="center">Author</p></TH>
                     <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 195px; height: 36px"><p align="center">Last change</p></TH>
                
                </TR>
               <%
				for(ThreadInfo thread: threads_list){
					
			%>
				    <TR>
				    <TD style="width: 314px; font-size: 12px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; height: 29px" "> 
				    <p style="text-align: center; font-size: 14px; white-space: normal; line-height: normal; text-transform: none; font-style: normal; display: list-item; list-style: none;">
					<a href="forum?id=<%=thread.getThread_id()%>&window=posts"><%=thread.getTitle()%></a>
				    </TD>
             	 	<TD style="width: 52px; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; clip: rect(auto, auto, auto, auto); top: auto; padding-top: 0px; vertical-align: top"> <%= thread.get_owner() %> </TD>
             	 	<TD style="width: 52px; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; clip: rect(auto, auto, auto, auto); top: auto; padding-top: 0px; vertical-align: top">
             	 	 <%= thread.getDateTime() %><br /><%= thread.get_lastModifiedUser()%>	
             	 
             	 	 </TD>
					</TR>
				<% } %>
				
             </TABLE>
		
		<ul style="list-style: none; width: 1008px">
		
			<p align="center" style="width: 1056px; ">	<br><br><input type="submit" value="Add Thread" name=AddThreadButton style=" height: 42px; width: 148px;  background-color: Navy; font-size: 100%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif" />
				
		</ul>
	</form>
	</body>

</html>
