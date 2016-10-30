<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>登录页面</title>
<link href="${pageContext.request.contextPath}/style/css/zui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ajaxjson.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/login.js"></script>
<script type="text/javascript">
var currentUrl = "${pageContext.request.contextPath}/index.jsp";
</script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/login/login.action" method="post">
<div id="login">
	<div class="login">
    	<div class="loginLogo"></div>
        <div class="loginBox">
        	<div class="loginName">企业标准管理系统—后台</div>
            <ul class="loginTxt">
            	<li class="loginIco01">
            		<span class="icon icon-user"></span><label id="show1"></label>
            		<input type="text"  id="username" name="username"  value="" class="loginText" />
            	</li>
            	<li class="loginIco02">
            		<span class="icon icon-lock"></span><label id="show2"></label>
                	<input type="password"  id="password" name="password"  value="" class="loginText" />
            	</li> 
            	<li>
            	   <input type="button" id="button" value="登录" class="loginBtn"  onclick="SubmitLogin()"/></li>
            </ul>
        </div>
    </div>
</div>
<div class="login-bottom"></div>
</form>
</body>
</html>