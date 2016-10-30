<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>404页面</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
</head>
<body>
  	<div style="margin:5% auto 5%;">
  		<h1 align="center">亲，程序404啦... 请检查你的访问地址是否正确。</h1>
  		
  		<p style="text-align: center;">
  			<a href="javascript:;" onclick="window.location.href = '${pageContext.request.contextPath}/'">返回首页</a>
  		</p>
	</div>
  </body>
</html>
