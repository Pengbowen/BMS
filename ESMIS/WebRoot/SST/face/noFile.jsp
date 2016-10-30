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
    <title>错误</title>
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
  <%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
	<div class="tabPanel">
    	<div class="sub-posit-cont" style="padding-top:36px;">
        	<a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
        	<i class="icon icon-angle-right"></i><b id="title"> 错误页面</b>
     </div>
</div>
<!--content开始 -->
<div class="sub-contnew" style="min-height:680px;">
  				<div class="a-resour-r-cont">
                	<div class="nnone" style="margin:auto;margin-top:50px;width:320px;height:210px;">
                        <b class="nnone-img1"><img src="image/nnone1-1.png" alt=""/></b>                      
                    </div>
                </div>
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
