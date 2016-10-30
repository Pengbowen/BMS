<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.lanyuan.assembly.util.WebSitePro"%>
<%@page import="java.io.File"%>
<%@page import="com.lanyuan.web.LoginAuthentication.LoginUser"%>
<%@page import="com.lanyuan.assembly.util.CookiesUtil"%>
<% 
	boolean sessionTag=false;
   	Date currentTime = new Date();
   	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   	String loginName="";
   	if (!(request.getSession().getAttribute(LoginUser.SESSIONID) == null)) {
   		LoginUser loginUser = (LoginUser)session.getAttribute("ly_Current_Login_User");
   		loginName=loginUser.getRealName();
   	}
   	if(StringUtils.isBlank(loginName)){
   		sessionTag=true;
   		Cookie UserNameCookie = CookiesUtil.getCookieByName(request, "ly_Current_Login_UserName");
        if (UserNameCookie != null){
        	loginName=UserNameCookie.getValue();
        }
   	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<html:base />
<title>头部</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/css/zui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
<link  href="${pageContext.request.contextPath}/css/home.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/html5shiv.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/respond.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ajaxjson.js"></script>
<script type="text/javascript">
	var currentUrl = "${pageContext.request.contextPath}";
	var sessionTag=<%=sessionTag%>;
	// 退出系统
	function LogOut() {
		window.location="${pageContext.request.contextPath}/login/loginOut.action";
		$.cookie("ly_Current_Login_UserName", "");
		$.cookie("ly_Current_Login_UserPWD", "");
	}
	function change(id){
		document.getElementById(id).className = "active";
	}
</script>

</head>
<body>
<header>
    <div class="header-box">         
        <ul class="header-left">
            <li class="qibiao-logo"><span><img src="${pageContext.request.contextPath}/image/logo-qibiao.png"/></span>企业标准化管理V4.0</li>
            <li class="nav">
            	<a target="_parent" href="${pageContext.request.contextPath}/" id="webIndex" class="active">首页</a>
        		<a target="_parent" href="${pageContext.request.contextPath}/sst/toSSTHome.action" id="sstHome">体系表</a>
        		<a target="_parent" href="${pageContext.request.contextPath}/resource/showResourceList.action" id="sourceList">资源库</a>
       			<a target="_parent" href="${pageContext.request.contextPath}/standardrepor/frontStatementStandard.action" id="StatementStandard">资源统计</a>
       			<a target="_parent" href="${pageContext.request.contextPath}/netdisk/reception_list.jsp" id="netdisk">知识库</a>
            </li>    
            <li class="clearfix"></li>
        </ul>
        <ul class="header-right">
             <li class="visitors">访客：<em><%=WebSitePro.get_visiterCount()%>人</em></li>
        	 <li class="now-date">今天是：<em><%=formatter.format(currentTime)%></em></li>
            <li><a href="${pageContext.request.contextPath}/standardlibrary/showCollect.jsp" class="collection" target="_parent" ><i class="fa fa-star"></i><span>我的收藏</span></a></li>
            <%
            	if(!"".equals(loginName)){
            	%>
            	<li class="dropdown">
                <a class="share"><i class="fa fa-user"></i><span><%=loginName%></span></a>
                <ul class="dropdown-menu-1" id="a" style="display:none;">
                	<li><a href="${pageContext.request.contextPath}/index.jsp" target="_blank">管理后台</a></li>
                    <li><a target="_parent" href="javascript:void(0);" onclick="LogOut();">退出登录</a></li>
                </ul>
            	</li>
            <script type="text/javascript">
                $(document).ready(function() {
                    		$('.dropdown').hover(function() {
                                $('ul', this).slideDown(200);
                                $(this).children('a:first').addClass("hov");
                            },
                            function() {
                                $('ul', this).slideUp(100);
                                $(this).children('a:first').removeClass("hov");
                            })
                    ;});
            </script>
            	<%	
            	}else{
            	%>
            	<li style="position: relative;display: inline-block;">
                <a class="login-box" href="javascript:void(0);" target="_parent"><i class="fa fa-lock"></i><span>登录</span></a>
                <div class="login">
                <form id="form" action="${pageContext.request.contextPath}/login/login.action" method="post" target="_parent">
                    <span class="login-title"><a href="javascript:void(0);" class="close-login" target="_parent" style="margin: 0;font-size: 12px;display: block;width:40px;height:40px;color:#666;">关闭</a></span>
                    <div class="login-input-content">
                        <div class="login-input-1">
                            <span><i class="icon icon-user" style="  line-height:44px; font-size:18px;color:#ffffff;"></i></span>
                            <input  name="username"  id="username" class="list-input"/>
                        </div>
                        <div class="login-input-2">
                            <span><i class="fa fa-lock" style="line-height:44px; font-size:18px;color:#ffffff;"></i></span>
                            <input type="password" name="password" id="password" class="list-input"/>
                        </div>
                        <div class="input-checkbox">
                            <ul>
                                <input title="支持7天内自动登录" id="rememberMe" name="rememberMe" type="checkbox"  style="margin-top:3px;margin-right:6px;float:left;">
                                <li title="支持7天内自动登录"  style="line-height:18px;float:left;">自动登录</li>
                            </ul>
                        </div>
                    </div>
                    <div class="login-button"><a target="_parent" href="javascript:void(0);" id="login-button-submit">登录</a></div>
                </form>
                </div>
            </li>
            	<%
            	}
            %>        
        </ul>
        <div class="clearfix"></div>
    </div>
</header>
</body>
</html:html>
