<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta>
<title>企业目标方针</title>
<link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<style type="text/css">
.sub-sear-text{
	width:320px;
	height:36px;
	line-height:36px;
	text-indent:0.5em;
	font-size:14px;
	border:1px solid #dadada;
	border-top-left-radius:5px;
	-webkit-border-top-left-radius:5px;
	-moz-border-top-left-radius:5px;
	border-bottom-left-radius:5px;
	-webkit-border-bottom-left-radius:5px;
	-moz-border-bottom-left-radius:5px;
	float:left;
	background-color:#ffffff;
}
</style>
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
		      <i class="icon icon-angle-right"></i><b>企业方针目标</b>
    	</div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="search-table">
<div class="pane-box" style="padding-bottom:20px;padding-top:20px;">
<div class="sub-contnew">
	<div class="nlist-detil-title">
        ${ contenttitle}
    </div>
   	<div class="new-detil">
    	${ content}
    </div>
</div>
</div>
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="../footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>
