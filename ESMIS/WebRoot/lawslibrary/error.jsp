<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	.fenye-list li.shangyiye{
	padding:0px;
	width:60px;
	font-size:14px;
}
	.fenye-list ul{
	align:center;	
	width:100%;
	}
	
	</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>错误提示</title>
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/SST/face/js/jqPaginator.min.js" type="text/javascript"></script>
 </head>   
<body onload="change('sstHome')">
<!--头部开始 -->
<div class="top">
  <%@ include file="../../header.jsp" %>
</div>
<!--头部结束 -->
<!--banner开始 -->
<div class="sub-banner-newwrap sub-banner01">
	<div class="sub-posit">
    	<div class="sub-posit-cont">
        	<a class="posit-a" href="${pageContext.request.contextPath}">首页</a> <i class="icon icon-angle-right"></i><a class="posit-a" href="${pageContext.request.contextPath}/sst/toSSTHome.action">体系表</a> <i class="icon icon-angle-right"></i> <b id="title"> 法律法规详情页</b>
        </div>
    </div>
</div>
<!--content开始 -->
<div class="sub-contnew" style="min-height:680px;">
    
    <ul class="sub-content">
        <li class="sub-content-text" style="text-align:center;">
           <img src="${pageContext.request.contextPath}/images/pictre_1.png"/>
        </li>
        <li class="sub-content-text" style="text-align:center;font-size:30px;">
          数据不存在！
        </li>
    </ul>
</div>
<!--content结束 -->
<!--底部开始 -->
<div class="bottom">
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="76" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</div>
<!--底部结束 -->
</body>
