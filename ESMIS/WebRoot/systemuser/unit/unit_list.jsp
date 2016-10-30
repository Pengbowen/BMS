<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>部门管理</title>
<link href="${pageContext.request.contextPath}/style/public.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/systemuser/unit/scripts/operate.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + "unit/loadData.action";

	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true, //自适应大小
			nowrap : true,//当数据长度超出列宽时将会自动截取
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			checkOnSelect : false,
			singleSelect : true,
			idField : 'unitNo',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			columns : [ [//设置gridview的列信息*
			{field : 'code',
				title : '序号',
				width : '40',
				align : 'left',
				formatter : showClassNo
			},{
				field : 'num',
				title : '序号',
				width : '45',
				align : 'center',
				hidden: true
			}, {
				field : 'unitName',
				title : '单位名称',
				width : '200',
				align : 'left'
			}, {
				field : 'abbreviation',
				title : '简称',
				width : '150',
				align : 'left'
			}, {
				field : 'concator',
				title : '联系人',
				width : '100',
				align : 'left'
			}, {
				field : 'concatPhone',
				title : '联系电话',
				width : '150',
				align : 'left'
			}, {
				field : 'address',
				title : '地址',
				width : '200',
				align : 'left'
			}, {
				field : 'operate',
				title : '操作',
				width : '200',
				align : 'center',
				formatter : formateOperateCell
			} ] ],
			toolbar:[{
				text:'新增部门',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   showAdd();
				}
			}]	,
			pagination : true
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
	});

</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
		   <div class="query clearfix"style="padding: 10px 0px 10px 10px;">
			<div style="float:left;">
				<span>查询：</span> <input class="easyui-searchbox"
					searcher="searchByCondition" prompt="请输入部门名称"
					style="width:150px;height:28px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" title="部门名称"
					type="text" name="unitName" id="unitName" inQuery="true"
					operator="like" onclick="searchByCondition();"></input>
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
