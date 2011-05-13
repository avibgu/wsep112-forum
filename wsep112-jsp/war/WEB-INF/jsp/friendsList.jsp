<%@ page import="java.util.Iterator" %>
<%@ page import="common.forum.items.UserInfo" %>

<jsp:useBean id="friends" scope="request" type="java.util.Vector<common.forum.items.UserInfo>" />

<ul>

	<%
		for(UserInfo friend: friends){
			
			if(friend.getStatus() == "ONLINE"){
	%>
				<li>	
					<div style="color:green;"><p><%=friend.getUserName()%></p></div>
				</li>
	<%
			}
			else{
	%>
				<li>	
					<div style="color:red;"><p><%=friend.getUserName()%></p></div>
				</li>
	<%
			}
		}
	%>

</ul>
