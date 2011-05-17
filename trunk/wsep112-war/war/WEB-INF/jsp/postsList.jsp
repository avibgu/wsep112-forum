<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />



<html>

	<body>

		<ul>
		
			<TABLE BORDER="1">
                <TR>
                    <TH>Title</TH>
                    <TH>Body</TH>
                    <TH>Publish date</TH>
                    <TH>Owner</TH>
                </TR>
                <% for(int row=0; row < posts_list.size(); row++) { %>
				    <TR>
				    <TD> <%= posts_list.get(row).get_title() %> </TD>
                    <TD> <%= posts_list.get(row).get_body() %> </TD>
                    <TD> <%= posts_list.get(row).getDateTime() %> </TD>
                    <TD> <%= posts_list.get(row).getOwner().getUserName() %> </TD>
				    </TR>
				<% } %>
             </TABLE>
			
		
		</ul>

	</body>

</html>
