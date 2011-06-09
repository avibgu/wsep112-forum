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
		
    <tr>
      <td style="width: 132px; ">
        <input type="hidden" name="dff_view" value="grid">
        Search:<br><input type="text" name="dff_keyword" id="searchFriend"  size="30" maxlength="50" style="width: 90px; "><br>
		<button onclick="loadFriendsList()" class="buttonsStyle1" style="width: 95px;" ><img src="http://png-5.findicons.com/files/icons/1715/gion/16/system_search.png">Find</button>
      </td>
    </tr>

		
		<br>
		<br><select class="selectStyle" id="addFriendName">
		<%
			for(String user: users_to_add){
		%>
		   <option value="<%=user%>"><%=user%></option>
	    <%
			}
		%>
	    </select><br>

		

		<button onclick="addFriend()" class="buttonsStyle1" style="width: 95px;" ><img src="http://png-2.findicons.com/files/icons/1620/crystal_project/16/ok.png">Add</button><br>		
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
		
		<button onclick="removeFriend()" class="buttonsStyle1" style="width: 95px;" ><img src="http://png-4.findicons.com/files/icons/577/refresh_cl/16/delete.png">Remove</button>
		
	</body>

</html>
