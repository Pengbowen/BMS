<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta>
<title>通知公告页面</title>
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
.search-table .new-detil p{
	font-size:14px;
	line-height:30px;
	color:#999;
	text-indent:30px;
	margin-bottom:10px;
}
.search-table .nlist-detil-title{
	color:#333;
	font-weight:bold;
}
.search-table .nlist-detil-title span{
	font-weight:normal;
}
</style>
</head>

<body>
<!--头部开始 -->
    <%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">       
		               	<a class="posit-a" href="${pageContext.request.contextPath}">首页</a> 
		               	<i class="icon icon-angle-right"></i>
		               	<a class="posit-a" href="${pageContext.request.contextPath}/notice/noticeShow.jsp">通知公告</a> 
		               	<i class="icon icon-angle-right"></i>
		               	 <b> ${noticeTitle }</b>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="search-table">
	<div class="pane-box"  style="padding-top:20px;padding-bottom:20px;">
    <div class="pane" style="display:block;padding:16px 30px;background-color:#fff;border:solid 1px #ddd;">
	<div class="nlist-detil-title">
    	<span><i class="icon icon-time"></i>${ publishTime}</span><span><i class="icon icon-eye-open"></i>${ count}</span>
        ${noticeTitle }
    </div>
   	<div class="new-detil">
    	${noticeContent }
    </div>
   	<!-- <div class="new-detil-xg"><span>下一个：<a href="javascript:vido(0);" class="xg-play">金数据全网整合营销 </a></span>上一个：<i class="xg-display">开门红商城基础营销 </i></div> -->
   </div>
</div>    
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="../footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>
