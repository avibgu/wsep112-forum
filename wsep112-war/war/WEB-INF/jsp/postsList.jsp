<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />


<html>
		<head>
		
	    </head>	
	<body>
		<form name="PostsForm" action="forum" method="get" style="height: 385px; ">
		<a style="text-align: center;" href="forum?id=<%=session.getAttribute("ForumId")%>&window=threads"><p align="justify"><i><p align="center">Back</p></i></p>
		</a>
		<p align="center"><b><big><p align="center"><%=posts_list.get(0).get_title() %>
		</p></big></b><ul style="list-style: none;"></ul>
		
			<TABLE align="center" BORDER="3" style=" height: 188px; width: 842px;" cellpadding="7">
		
                <TR> 
                    <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 696px"><p align="center">Message</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; width: 111px; height: 36px"><p align="center">Author</p></TH>
                </TR>
                <% for(int row=0; row < posts_list.size(); row++) {
                	PostInfo post = posts_list.get(row); %>
				    <TR align="left">
				    <TD style=" font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; width: 658px">
				 	<TABLE align="center" border="1" cellspacing="4" width="98%" style="background-color: #F0F0F0">
				    <TR align="left">
				    <TD style="font-size: x-small"> <big> <i><u>Subject:</u></i>   </big>   <%= post.get_title() %> <br /> <big> <i><u>Publish date:</u></i> </big><%= post.getDateTime() %>     </TD>
				    
				    				    
				    </TR>
				     
				    <TR>
                    <TD style="width: 496px; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: left; white-space: normal"> <%= post.get_body() %> </TD>
                    </TR>
                    <TR>
					<TD style="border-top-style: none; border-bottom-style: none; border-left-style: none; border-right-style: none; left: auto">
					<% if (post.getOwner().getUserName().equals(username)) {%>
                             <a style=" font-size:small; text-align: left;" href="forum?postId=<%=post.get_post_id()%>&amp;window=edit">Edit        
					</a>	
					/ 			
					 <a style="font-size:small; text-align: right;" href="forum?postId=<%=post.get_post_id()%>&amp;window=delete">Delete
					</a>				      				      
                            <%} %>
                    </TD>
                    </TR>
                 
                    
              		</TABLE>
              		</TD>
              		
                    <TD style="width: 52px; font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; clip: rect(auto, auto, auto, auto); top: auto; padding-top: 0px; vertical-align: top"> <%= post.getOwner().getUserName() %> </TD>
                  
                    </TR>
                   
				<% } %>
				
             </TABLE>
      
       
             <p align="center" style="width: 1110px; ">	<input type="submit" value="Add Post" name=AddPostButton style=' height: 42px; width: 128px;  background-color: Navy; font-size: 100%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif'/>
	
	</form>
	</body>

</html>
