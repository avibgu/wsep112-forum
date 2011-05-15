<%@ page import="java.lang.String" %>

<jsp:useBean id="online_friends" scope="request" type="java.util.Vector<java.lang.String>" />
<jsp:useBean id="offline_friends" scope="request" type="java.util.Vector<java.lang.String>" />

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

	</body>

</html>
