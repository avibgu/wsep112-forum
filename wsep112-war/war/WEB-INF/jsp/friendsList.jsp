<%@ page import="java.lang.String" %>

<jsp:useBean id="online_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="offline_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_add" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_remove" scope="request" type="java.util.Vector<java.lang.String>" />

<html>

	<body>

		<ul>
		
			<%
				for(String friend: online_friends){
			%>
					<li>
						<div style="color:green;"><%=friend%></div>
					</li>
			<%
				}
		
				for(String friend: offline_friends){
			%>
					<li>	
						<div style="color:red;"><p><%=friend%></p></div>
					</li>
			<%
				}
			%>
		
		</ul>
		
		
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
					
				<tr>		<input type="button" value="Add Friend" onclick="addFriend()" style=" height: 28px; width: 126px;  background-color: Navy; font-size: 65%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif">	</tr>
					
					
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

					<tr>		<input type="button" value="Remove Friend" onclick="removeFriend()" style=" height: 28px; width: 126px;  background-color: Navy; font-size: 65%; color: White; font-style: italic; font-family: Tahoma, Verdana, Arial, Sans-Serif">	</tr>
					
				</form>


	</body>

</html>
