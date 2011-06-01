<%@ page import="common.forum.items.ThreadInfo" %>
<jsp:useBean id="threads_list" scope="request" type="java.util.List<common.forum.items.ThreadInfo>" />

<html>

	<body>
		
		<p align="center">
			<a style="text-align: center;" onclick="loadForumsList()"  href="#"> Back </a>
		</p>
		
		<p align="center">	Please choose one of the threads:	</p>
		
		<TABLE align="center" class="threadsListTableStyle">
		
			<TR> 
				<TH class="threadsListThStyle">Subject</TH>
				<TH class="threadsListThStyle">Author</TH>
				<TH class="threadsListThStyle">Last change</TH>
			</TR>
			
			<%
				for(ThreadInfo thread: threads_list){
			%>
		
			<TR>
			    
			    <TD class="threadsListTdStyle">
			    
					<a onclick="loadPostsList(<%=thread.getThread_id()%>)"  href="#"><%=thread.getTitle()%></a>
					
			    </TD>
			    
				<TD class="threadsListTdStyle"> <%= thread.get_owner() %> </TD>
				
				<TD class="threadsListTdStyle">

					<%= thread.getDateTime() %>
					
					<br>
					
					<%= thread.get_lastModifiedUser()%>	
            	 
				</TD>
            	 	 
			</TR>
			
			<%	}	%>
			
		</TABLE>

		<br>
		
		<div align="center">
			<button onclick="addThread()" class="buttonsStyle"> Add Thread </button>
		</div>
		
	</body>

</html>
