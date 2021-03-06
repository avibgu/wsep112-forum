<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />
<jsp:useBean id="ForumId" scope="session" type="java.lang.String" />
<jsp:useBean id="error" scope="request" type="java.lang.String" />

<html>

	<body>
	
		<p align="center">
			<a style="text-align: center;" onclick="loadThreadsList(<%=ForumId%>)"  href="#"><img src="http://png-5.findicons.com/files/icons/756/ginux/16/backward.png"> Back </a>
		</p>
		
		<p align="center" style="color: red">	<%=error%>	</p>
		
		<p align="center">	<%= posts_list.get(0).get_title() %>	</p>

		<TABLE align="center"  align="center" class="postsListTableStyle">
	
               <TR> 
                   <TH class="postsListThStyle"> Message </TH>
                   <TH class="postsListThAuthorStyle"> Author </TH>
               </TR>
               
		<%
			for(int row=0; row < posts_list.size(); row++) {
				PostInfo post = posts_list.get(row);
		%>
			
			<TR align="left">
			
				<TD class="postsListTdStyle">
					<div>
					
						<p><i><u>Subject:</u></i>	<%= post.get_title() %>	</p>
						<p><i><u>Publish date:</u></i>	<%= post.getDateTime() %> </p>
						<p>	<%= post.get_body() %> </p>
						
						<%
							if (post.getOwner().getUserName().equals(username)) {
						%>
								<a onclick="getTitle(<%=row %>)" href="#"> Edit </a>	
								/ 
						<%
							} 
							if (post.getOwner().getUserName().equals(username) || username.equals("forum-admin")) {
						%>		
								<a href="#" onclick="deletePost(<%=post.get_post_id()%>,<%=posts_list.size()%>,<%=post.getThread_id()%>)"> Delete </a>				      				      
						<%
							}
						%>

					</div>
				</TD>
				
				<TD class="postsListTdAuthorStyle">
					<%= post.getOwner().getUserName() %>
				</TD>
			
			</TR>
			
		<%
			}
		%>
						
		</TABLE>

		<br>

		<div align="center">
			<button onclick="addPost()" class="buttonsStyle"><img src="http://png-5.findicons.com/files/icons/1035/human_o2/16/document_new.png"> Add Post </button>
		</div>
		
	</body>

</html>
