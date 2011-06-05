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
					<div style="color:#66FF00;"><%=friend%></div>
		<%
			}
	
			for(String friend: offline_friends){
		%>
				<div style="color:red;"><%=friend%></div>
		<%
			}
		%>
		
		<br>
		<br>
		<br>

		<select class="selectStyle" id="addFriendName">
		<%
			for(String user: users_to_add){
		%>
		   <option value=<%=user%>><%=user%></option>
	    <%
			}
		%>
	    </select>
		
		<br>

		<button onclick="addFriend()" class="buttonsStyle1">Add</button>

		<br>		
		<br>
		<br>
						
		<select class="selectStyle" id="removeFriendName" >
		<%
			for(String user: users_to_remove){
		%>
		   <option value=<%=user%>><%=user%></option>
	    <%
			}
		%>
	    </select>

		<br>
		
		<button onclick="removeFriend()" class="buttonsStyle1">Remove</button>

	</body>

</html>
