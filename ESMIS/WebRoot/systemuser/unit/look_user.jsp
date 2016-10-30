<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="${pageContext.request.contextPath}/style/public.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/systemuser/unit/scripts/operate.js"
	type="text/javascript"></script>
<title>人员管理</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" class="clearfix">
			<div class="query clearfix"
				style="padding: 10px 0px 10px 10px;margin: 10px;">
			<input style="display:none" id="unitNo" value="${unitNo }"
				inQuery="true" />
			<div style="float:left;">
				<span>角色：</span><i> <s:select id="roleId" name="roleId"
						cssStyle="width:120px;" cssClass="boxText text01"
						list="#request.roleMap" listKey="key" listValue="value"
						headerKey="-1" headerValue="--请选择--" inQuery="true"
						onchange="loadGridViewData();">
					</s:select> </i>
			</div>

			<div class="inputBox" style="margin-top:0;padding-left:10px;">
				<span>登录名：</span><i> <input class="boxText text01"
					id="loginName" inQuery="true" operator="like" /> </i>
			</div>

			<div class="search" style="margin:0px;">
				<button class="btn btn-primary" type="button"
					style="width:80px;height:28px;margin-left:10px;float: right"
					onclick="searchByCondition();">
					<i class="icon icon-search"></i>&nbsp;&nbsp;搜索
				</button>
			</div>

		</div>
	</div>
	<div region="center" border="false" style="padding:0 3px 0 10px;">
		<table id="datagrid"></table>
	</div>

</div>
</div>
<div id="win"></div>
<script type="text/javascript">
		var rootPath = "${pageContext.request.contextPath}/";
		var LoadDataUrl = rootPath + "user/loadData.action";

		var gridviewId = 'datagrid', pager;
		var currentPage = 1, linesOfPage = 10;
		$(function() {
			$('#' + gridviewId).datagrid({
				fit : true,
				nowrap : true,//当数据长度超出列宽时将会自动截取
				autoRowHeight : false,//行高自适应
				striped : true,//交替显示行背景
				checkOnSelect : false,
				singleSelect : true,
				idField : 'userId',//唯一列
				loadMsg : '', //数据加载中，等待 展示的文字*
				columns : [ [//设置gridview的列信息*
				{
					field : 'num',
					title : '序号',
					width : '45',
					align : 'center',
					formatter : showClassNo
				}, {
					field : 'loginName',
					title : '登录名',
					width : '150',
					align : 'left'
				}, {
					field : 'roleName',
					title : '角色名称',
					width : '150',
					align : 'left'
				}, {
					field : 'realName',
					title : '姓名',
					width : '100',
					align : 'left'
				}, {
					field : 'sex',
					title : '性别',
					width : '50',
					align : 'left'
				}, {
					field : 'stateName',
					title : '状态',
					width : '100',
					align : 'left'
				}, {
					field : 'operate',
					title : '操作',
					width : '200',
					align : 'left',
					formatter : formateOperateCell1
				} ] ],
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
</body>
</html>
