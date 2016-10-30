<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>工作标准体系表</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet"/>
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/my.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet"/>
<script>
function reinitIframe(){  
	var iframe = document.getElementById("iframepage");  
	iframe.height=document.documentElement.clientHeight-228;
}  
</script>
</head>
<body onload="change('sstHome')">
<!--头部开始 -->
<%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
        <a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
        <i class="icon icon-angle-right"></i>
        <a class="posit-a" href="${pageContext.request.contextPath}/sst/toSSTHome.action">体系表</a>
        <i class="icon icon-angle-right"></i><b> 工作标准体系表</b>
    </div>
</div>
<!--banner结束 -->
<div class="search-table">
	<iframe src="${pageContext.request.contextPath}/SST/standard-iframe.jsp?type=work" height="580" frameborder="0" width="100%" marginwidth="0" marginheight="0" id="iframepage" onLoad="reinitIframe()"></iframe>
</div>
<!--底部开始 -->
<iframe src="../footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>