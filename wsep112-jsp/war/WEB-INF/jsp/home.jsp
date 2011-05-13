<%@ page import="com.sun.syndication.feed.synd.SyndFeed" %> 
<%@ page import="com.sun.syndication.feed.synd.SyndEntry" %> 
<%@ page import="java.util.Iterator" %>

<jsp:useBean id="syndFeed" scope="request" type="SyndFeed" />
<html>
   <head>
      <title>website</title>
   </head>
   <body>
      <h1>Home</h1>
      <h2><%=syndFeed.getTitle()%></h2>
      <ul>
         <% 
           Iterator it = syndFeed.getEntries().iterator();
           while (it.hasNext())
           {
              SyndEntry entry = (SyndEntry) it.next();
         %>
            <li>
               <a href="<%=entry.getLink()%>"><%=entry.getTitle()%></a>
            </li>
         <% } %>
      </ul>
   </body>
</html>