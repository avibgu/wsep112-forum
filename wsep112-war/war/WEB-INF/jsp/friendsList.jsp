<%@ page import="java.lang.String" %>

<jsp:useBean id="online_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="offline_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_add" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_remove" scope="request" type="java.util.Vector<java.lang.String>" />

<html>

	<body>
		
		<%
			for(String friend: online_friends){
		%>
					<div style="color:green;"><%=friend%></div>
		<%
			}
	
			for(String friend: offline_friends){
		%>
					<div style="color:red;"><p><%=friend%></p></div>
		<%
			}
		%>
		
		<br>
		
		<form>
	
			<select id="addFriendName">
			<%
				for(String user: users_to_add){
			%>
			   <option value=<%=user%>><%=user%></option>
		    <%
				}
			%>
		    </select>
			
			<br>
		
			<input type="button" value="Add Friend" onclick="addFriend()"/>
			
		</form>
		
		<form>

		<br>				
			<select id="removeFriendName" >
			<%
				for(String user: users_to_remove){
			%>
			   <option value=<%=user%>><%=user%></option>
		    <%
				}
			%>
		    </select>

			<br>
			
			<input type="button" value="Remove Friend" onclick="removeFriend()"/>
			
		</form>

	</body>

</html>