<%@ page language="java" import="java.util.*,java.io.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>页面发生错误</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
</head>
<body>
  	<div style="margin:5% auto 5%;">
  		<p style="margin-left:80px;line-height:24px;">
  			<img src="${pageContext.request.contextPath}/images/exception.png" />
  			     程序发生了错误，有可能该页面正在调试或者是设计上的缺陷，你可以
  			  <a href="javascript:;" onclick="window.location.href = '${pageContext.request.contextPath}/'">返回首页</a>
  			<br/>
  			
  			错误类型：<%=exception.getClass()%><br/>
  			请求路径：<%=request.getAttribute("javax.servlet.forward.request_uri") %>
  		</p>
  		
		<hr width="100%"></hr>
		
		<p style="margin-left:80px;line-height:24px;">错误信息:</p>
		<pre style="margin-left:80px;">
		   <%
			  ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			  exception.printStackTrace(new PrintStream(ostr));
			  out.print(ostr);
		   %>
		</pre>		
		
	</div>
  </body>
</html>
