<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />


<html>
		<head>
	    </head>	
	<body>
		<form name="PostsForm" action="forum" method="get">
		<br><br><ul style="text-align: center;">
		
			<TABLE align="center" BORDER="1" style=" height: 156px; width: 855px">
                <TR> 
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 205px"><p align="center">Title</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline"><p align="center">Body</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline"><p align="center">Publish date</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline"><p align="center">Owner</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline"><p align="center">Edit</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 24px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline"><p align="center">Delete</p></TH>
                
                </TR>
                <% for(int row=0; row < posts_list.size(); row++) {
                	PostInfo post = posts_list.get(row); %>
				    <TR>
				    <TD style="width: 72px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal" "> <%= post.get_title() %> </TD>
                    <TD style="width: 496px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal"> <%= post.get_body() %> </TD>
                    <TD style="width: 82px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal"> <%= post.getDateTime() %> </TD>
                    <TD style="width: 66px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal"> <%= post.getOwner().getUserName() %> </TD>
                    <TD style="width: 65px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal"> <% if (post.getOwner().getUserName().equals(username)) {%>
                             <a style="text-align: center;" href="forum?postId=<%=post.get_post_id()%>&amp;window=edit">Edit
					</a>				      
                            <%} %> </TD>
				    <TD style="width: 76px; background-color: #E0E0E0; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: left; white-space: normal""> <% if (post.getOwner().getUserName().equals(username)) {%>
                             <p align="center"><a style="text-align: center;" href="forum?postId=<%=post.get_post_id()%>&amp;window=delete">Delete
					</a>				      
						     
                            </p><%} %> </TD>
				    </TR>
				    <p align="center">	
				<% } %>
				
             </TABLE>
             <p align="center">	<input type="submit" value="Add Post" name=AddPostButton style=" height: 42px; width: 128px;  background-color: #9999FF; font-size: 100% "/>
		</ul>
	</form>
	</body>

</html>
