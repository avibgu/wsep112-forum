<%@ page import="common.forum.items.PostInfo" %>
<jsp:useBean id="posts_list" scope="request" type="java.util.List<common.forum.items.PostInfo>" />
<jsp:useBean id="username" scope="request" type="java.lang.String" />


<html>

	<body>
		<form>
		<a style="text-align: center;" onclick="loadThreadsList(<%=session.getAttribute("ForumId")%>)" href="#"><p align="justify"><i><p align="center">Back</p></i></p>
		</a>
		<p align="center"><b><big><p align="center"><%=posts_list.get(0).get_title() %>
		</p></big></b><ul style="list-style: none;"></ul>
		
			<TABLE align="center" BORDER="3" cellpadding="7">
		
                <TR> 
                    <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; "><p align="center">Message</p></TH>
                    <TH style=" background-color: #9999FF; font-size: 18px; line-height: normal; color: #400080; white-space: normal; text-decoration: underline; height: 36px"><p align="center">Author</p></TH>
                </TR>
                <% for(int row=0; row < posts_list.size(); row++) {
                	PostInfo post = posts_list.get(row); %>
				    <TR align="left">
				    <TD style=" font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal;">
				 	<TABLE align="center" border="1" cellspacing="4" width="98%" style="background-color: #F0F0F0">
				    <TR align="left">
				    <TD style="font-size: x-small"> <big> <i><u>Subject:</u></i>   </big>   <%= post.get_title() %> <br /> <big> <i><u>Publish date:</u></i> </big><%= post.getDateTime() %>     </TD>
				    
				    				    
				    </TR>
				     
				    <TR>
                    <TD style="font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: left; white-space: normal"> <%= post.get_body() %> </TD>
                    </TR>
                    <TR>
					<TD style="border-top-style: none; border-bottom-style: none; border-left-style: none; border-right-style: none; left: auto">
					<% if (post.getOwner().getUserName().equals(username)) {%>
                             <a style=" font-size:small; text-align: left;" href="#">Edit        
					</a>	
					/ 			
					 <a style="font-size:small; text-align: right;" href="#">Delete
					</a>				      				      
                            <%} %>
                    </TD>
                    </TR>
                 
                    
              		</TABLE>
              		</TD>
              		
                    <TD style="font-size: 14px; font-family: Verdana, Arial, Sans-Serif; text-align: center; white-space: normal; clip: rect(auto, auto, auto, auto); top: auto; padding-top: 0px; vertical-align: top"> <%= post.getOwner().getUserName() %> </TD>
                  
                    </TR>
                   
				<% } %>
				
             </TABLE>

			<div align="center">
				<button onclick="addPost()" class="buttonsStyle"> Add Thread </button>
			</div>

	</form>
	</body>

</html>
