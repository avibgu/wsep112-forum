<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />


<html>

	<body>
		<form name="PostsForm" action="forum" method="get">
		<ul>
		
			<TABLE BORDER="1" style="height: 156px; width: 589px">
                <TR>
                    <TH>Title</TH>
                    <TH>Body</TH>
                    <TH>Publish date</TH>
                    <TH>Owner</TH>
                    <TH>Edit</TH>
                </TR>
                <% for(int row=0; row < posts_list.size(); row++) { %>
				    <TR>
				    <TD> <%= posts_list.get(row).get_title() %> </TD>
                    <TD> <%= posts_list.get(row).get_body() %>
                    <TD> <%= posts_list.get(row).getDateTime() %> </TD>
                    <TD> <%= posts_list.get(row).getOwner().getUserName() %> </TD>
                    <TD> <% if (posts_list.get(row).getOwner().getUserName().equals(username)) {%>
                             <input type="hidden" name="postId" value="<%=posts_list.get(row).get_post_id() %>" />
						     <input type="hidden" name="title" value="<%=posts_list.get(row).get_title() %>" />
						     <input type="hidden" name="body" value="<%=posts_list.get(row).get_body() %>" />
						     <input type="submit" value="Edit" name="EditPostButton"> 
						      </TD>
                            <%} %>
				    </TR>
				    <p align="center">	
				<% } %>
				
             </TABLE>
             <p align="center">	<input type="submit" value="Add Post" name=AddPostButton />
		</ul>

	</body>

</html>
