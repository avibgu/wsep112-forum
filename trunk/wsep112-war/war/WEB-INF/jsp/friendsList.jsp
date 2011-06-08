<%@ page import="java.lang.String" %>

<jsp:useBean id="online_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="offline_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_add" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="users_to_remove" scope="request" type="java.util.Vector<java.lang.String>" />

<html>

	<body>
		
		<br>
		<div>	Your Friends:			</div>
		<%
			for(String friend: online_friends){
		%>
					<div style="color:#66FF00;"><img src="http://www.veryicon.com/icon/16/Application/MSN/online.png"><%=friend%></div>
		<%
			}
	
			for(String friend: offline_friends){
		%>
				<div style="color:red;"><img src="http://www.veryicon.com/icon/16/Application/MSN/online%20red.png"><%=friend%></div>
		<%
			}
		%>
		
		<br>
		<br>
		
    <tr>
      <td style="width: 132px; ">
        <input type="hidden" name="dff_view" value="grid">
        Search:<input type="text" name="dff_keyword" id="searchFriend"  size="30" maxlength="50" style="width: 125px; ">
		<button onclick="loadFriendsList()" class="buttonsStyle1">Find</button>
      </td>
    </tr>

		
		
		<br><select class="selectStyle" id="addFriendName">
		<%
			for(String user: users_to_add){
		%>
		   <option value="<%=user%>"><%=user%></option>
	    <%
			}
		%>
	    </select><br>

		

		<button onclick="addFriend()" class="buttonsStyle1">Add</button><br>		
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
