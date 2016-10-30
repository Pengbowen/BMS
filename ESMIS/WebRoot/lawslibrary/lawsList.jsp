<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>法律法规管理</title>
<!-- 国际化定义 -->

<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js">
</script>
<script
	src="${pageContext.request.contextPath}/lawslibrary/scripts/lawslibrary.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + 'lawslibrary/searchLaws.action';
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true, //自适应大小
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			idField : 'id',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			columns : [ [//设置gridview的列信息*
			{
				field : 'code',
				title : '序号',
				width : '40',
				align : 'center',
				formatter : showClassNo
			},{
				field : 'standardId',
				title : '法规Id',
				width : '200',
				align : 'left',
				hidden : true
			},{
				field : 'standardNo',
				title : '法规文号',
				width : '200',
				align : 'left',
			},{
				field : 'standardName',
				title : '法规名称',
				width : '300',
				align : 'left',
				formatter : detailView
			},{
				field : 'browseVolume',
				title : '浏览量',
				width : '100',
				align : 'center'
			},{
				field : 'operate',
				title : '操作',
				width : '80',
				align : 'center',
				formatter : formateOperateCell
			} ] ],
			toolbar:[{
				text:'新增',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   NewData();
				}
			}]	,
			pagination : true
		});
		//是否分页*
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
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<div class="query clearfix" style="padding: 10px 0px 10px 10px;">
				<div style="float:left;">
					<input type="hidden" id="standardCategory" name="standardCategory" value="${standardCategory }" inQuery="true" operator="=" />
						<span style="margin-left:3px;">法规名称：</span> 
						<input class="easyui-textbox" prompt="请输入法规名称"style="width:150px;height:27px;vertical-align:middle;"id="standardName" name="standardName" inQuery="true" operator="like" />
					<span style="margin-left:3px;">关键字：</span> 
						<input class="easyui-textbox" prompt="请输入关键字"style="width:150px;height:27px;vertical-align:middle;"id="subjectWords" name="subjectWords" inQuery="true" operator="like" />
					<button  class="btn btn-primary" type="button" style="width:80px;height:28px;float:right;margin-right: 80px;" onclick="searchByCondition()"><i class="icon icon-search"></i>&nbsp;&nbsp;查询 
					</button>		
				</div>
			</div>
		</div>	
		<div region="center" border="false" style="padding:0 3px 0 10px;">
			<table id="ViewID" ></table>
		</div>
	</div>
	<!-- 点击新增所显示页面 -->
	<div id="win"></div>
</body>
</html>
