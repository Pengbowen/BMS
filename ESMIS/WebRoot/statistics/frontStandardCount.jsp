<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>标准统计页面</title>
   	<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/zui.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/list-1.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	
	<link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/easyui/themes/icon.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/gridview.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/templist.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/condjson.js"></script>
	<script src="${pageContext.request.contextPath}/statistics/scripts/operate.js"></script>
	<script type="text/javascript">
		var rootPath = "${pageContext.request.contextPath}/";
		var gridviewId = 'ViewID', pager;
		var currentPage = 1, linesOfPage = 10;
	
		/***页面默认进来加载标准总库的数据**/
		$(function() {
			LoadDataUrl = rootPath + "standardrepor/searchStatement.action?reportId=10";
			changeTotal(1);
	
			//获取gridView的分页对象
			pager = $('#' + gridviewId).datagrid('getPager');
			$(pager).pagination({
				onSelectPage : function(pageNumber, pageSize) {
					currentPage = pageNumber;
					linesOfPage = pageSize;
					loadGridViewData();
				}
			});
			//延迟100ms执行加载数据函数：loadingInfo
			setTimeout(loadingInfo, 100);
		});
	</script>

</head>   
<body onload="change('StatementStandard')"> 
<%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--头部tab选项卡开始-->
<div class="current-position-box">
	<div class="current-position">
         <a class="posit-b" href="${pageContext.request.contextPath}">首页</a>
		        <i class="icon icon-angle-right"></i>
		        <a class="posit-b" href="${pageContext.request.contextPath}/standardrepor/frontStatementStandard.action">资源统计</a>
		        <i class="icon icon-angle-right"></i>
		        <b id="ceshi"> 标准总库</b>
    </div>
</div>
<!-- <div class="tabPanel">
    <div class="tab-menu">
        <div class="tab-menu-right">
            <a class="hit" href="javascript:void(0);changeTotal(1);">标准统计</a>
            <a onclick="changeTotal(2)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=1">技术标准</a>
            <a onclick="changeTotal(3)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=2">管理标准</a>
            <a onclick="changeTotal(4)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=3">工作标准</a>
            <a href="javascript:void(0);changeTotal(5);">法律法规统计</a>
        </div>
    </div>
</div> -->
<!--头部tab选项卡结束-->
<div class="search-table">
	
	<div class="pane-box">
		<div class="pane" style="display: block;padding:0;">	
			<div class="tab-menu">
            	<a class="hit" href="javascript:void(0);changeTotal(1);">标准统计</a>
            	<a onclick="changeTotal(2)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=1">技术标准统计</a>
            	<a onclick="changeTotal(3)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=2">管理标准统计</a>
            	<a onclick="changeTotal(4)" href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=3">工作标准统计</a>
            	<a href="javascript:void(0);changeTotal(5);">法律法规统计</a>
     		</div>	  
      		<div region="center" border="false">
				<table id="ViewID" style="width:100%;height:400px;"></table>
			</div>
		</div>
	</div>
</div>
<!--底部开始 -->
<iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
