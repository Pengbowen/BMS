<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
	    var sstid=${SSTId};
	    var columnJson=${columnJson};
		var rootPath = "${pageContext.request.contextPath}/";
	    var LoadDataUrl = rootPath+"standardrepor/searchStatementData.action?reportId="+sstid; 
		var gridviewId = 'ViewID', pager;
		var currentPage = 1, linesOfPage = 10;
		$(function() {
		//alert(sstid);
			$('#' + gridviewId).datagrid({
				fit : false,
				nowrap : true,//当数据长度超出列宽时将会自动截取
				singleSelect : true,//是否只允许选择一行
				striped : true,//交替显示行背景
				idField : '',//唯一列
				loadMsg : '', //数据加载中，等待 展示的文字*
			    columns :[columnJson],
				pagination : false
			//是否分页*
			});
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
			$("#SSTId"+sstid).addClass("hit");
			switch (sstid) {
				case 1:
					$("#ceshi").text("技术标准统计");
					break;
				case 2:
					$("#ceshi").text("管理标准统计");
					break;
				case 3:
					$("#ceshi").text("工作标准统计");
					break;
				}
			
		});
</script>
</head>   
<body onload="change('StatementStandard')">
<!--头部开始 -->
<%@ include file="../header.jsp" %>
<div class="current-position-box">
	<div class="current-position">
         <a class="posit-b" href="${pageContext.request.contextPath}">首页</a>
		        <i class="icon icon-angle-right"></i>
		        <a class="posit-b" href="${pageContext.request.contextPath}/standardrepor/frontStatementStandard.action">资源统计</a>
		        <i class="icon icon-angle-right"></i>
		        <b id="ceshi">技术标准统计</b>
    </div>
</div>
<div class="search-table">
	<div class="pane-box">
		<div class="pane" style="display: block;padding:0;height:600px;">
		    <div class="tab-menu">
            	<a href="${pageContext.request.contextPath}/standardrepor/frontStatementStandard.action">标准统计</a>
	            <a id="SSTId1"   href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=1" onclick="changeTotal(2)" >技术标准统计</a>
	            <a id="SSTId2"   href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=2" onclick="changeTotal(3)" >管理标准统计</a>
	            <a id="SSTId3"   href="${pageContext.request.contextPath}/standardlibrary/frontStatementSST.action?SSTId=3" onclick="changeTotal(4)" >工作标准统计</a>
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
