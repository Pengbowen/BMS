<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误页面</title>
<link href="${pageContext.request.contextPath}/style/error.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" language="javascript"></script>
</head>
<body>
<div class="main">
	<div class="prompt">
    	<p><img src="${pageContext.request.contextPath}/images/err02.png" /></p>
    	<p><font>如果您看到该页面，可能由于以下原因造成：</font></p>
        <ul class="promptInfo">
            <li>您登陆后台系统身份过期！</li>
            <li>请检查您计算机的网络连接。</li>
        </ul>
        <p>页面会在<b><span id="ShowDiv">3</span></b>秒之后跳转至后台管理登录页面！</p>
    </div>
</div>
</body>
</html>
<script language='javascript' type='text/javascript'>
 var secs =3; //倒计时的秒数
 var URL="${pageContext.request.contextPath}/";
 function Load()
 {
   for(var i=secs;i>=0;i--) 
   { 
	   window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000);
   } 
 }
 function doUpdate(num) 
 { 
   $('#ShowDiv').html(num);
   if(num == 0) 
   { 
      window.open(URL,'_self');
   } 
 }
 Load();
</script> 
</html>