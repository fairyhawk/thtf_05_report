<%
 response.setStatus(301);
 response.setHeader( "Location", "http://"+request.getServerName() + ":" + request.getServerPort() +"/cms/login.jsp" );
 response.setHeader( "Connection", "close" );
%>