<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'socketTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
  
  
  <script type="text/javascript">
  
  		init();

        function init(){

            
            if (!window.WebSocket && window.MozWebSocket)
                window.WebSocket=window.MozWebSocket;
            if (!window.WebSocket){
                alert("WebSocket not supported by this browser");
                return;
            }
            var websocket = new WebSocket("ws://127.0.0.1:8080/yes/SpeedServlet");
            websocket.onopen = onopen;
            websocket.onclose = onclose;
            websocket.onmessage = onmessage;
        function onopen(evt){
           /*  box.clear(); */
           alert("open");
            console.info("Loading...");
        }
        function onclose(evt){
            /* box.clear(); */
            alertInfo("Server Closed");
        }
        function onmessage(evt){
            var data = evt.data;
            data=eval('(' + data + ')');
            var speed=data.data;
            console.info(speed[0]);
 		}
       }
        
      
  
  
  </script>
</html>
