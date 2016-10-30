<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>系统角色管理</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/permission/scripts/operate.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + "roleManage/loadData.action";
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			idField : 'roleId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			frozenColumns : [ [ {
				field : 'ck',
				title : '选择',
				checkbox : true
			} ] ],
			columns : [ [//设置gridview的列信息*
			{
				field : 'code',
				title : '序号',
				width : '40',
				align : 'center',
				formatter : showClassNo
			},{
				field : 'roleId',
				title : '角色ID',
				width : '100',
				align : 'left',
				hidden:true
			}, {
				field : 'roleName',
				title : '角色名称',
				width : '150',
				align : 'left'
			}, {
				field : 'enable',
				title : '是否可用',
				width : '100',
				align : 'center'
			}, {
				field : 'belongModule',
				title : '所属模块',
				width : '100',
				align : 'center'
			}, {
				field : 'operate',
				title : '操作',
				width : '150',
				align : 'center',
				formatter : formateOperateCell
			} ] ],
			toolbar:[{
				text:'新增',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   NewData();
				}
			}/* ,'-',{
				text:'批量删除',
				iconCls:'icon-trash',
				handler:function(){
				  BatchDel();
				}
			} */]	,
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

	//加载GridView数据
	function loadGridViewData() {
		var opts = $('#' + gridviewId).datagrid("options");
		opts.loadMsg = "正在加载数据，请稍后...";
		$('#' + gridviewId).datagrid("loading");

		var url = LoadDataUrl;
		url += (url.indexOf("?") > 0) ? "&" : "?";
		url += "currentPage=" + currentPage;
		url += "&linesOfPage=" + linesOfPage;
		
		if ($("#belongApp").val() != "") {
			url += "&belongApp=" + $("#belongApp").val();
		}
		AjaxJson(url, true, getParameter(), fillDataList, true, false);
	}
	//给GridView填充数据
	function fillDataList(json) {
		var datalist = {};
		datalist["total"] = json.recordCount;
		datalist["rows"] = json.datalist;
		$('#' + gridviewId).datagrid("loadData", datalist);
		$(pager).pagination({
			total : json.recordCount
		});
		$('#' + gridviewId).datagrid("loaded");
	}
	
	// 生成GridView数据请求页面的paramerter
function getParameter() {
	var json = "";
	var param = "rand=" + Math.random();
	if (document.getElementById("orderString")) {
		json = $("#orderString").val().replace(" ", "");
		if (json != "")
			param += "&orderString=" + json;
	}
	param += "&conditionString=" + compareConditionByQuery();
	return param;
}

	//执行页面中的查询操作
	function searchByCondition() {
		currentPage = 1;
		loadGridViewData();
	}


function showAdd(){
   var url=rootPath+"roleManage/showAdd.action";
   NewData(url,'loadGridViewData');
}
//新框架新增方法
function NewData() {
	$('#win').window({
		width : 450,
		height : 350,
		title : '新增系统角色',
		// 定义窗口是否可拖拽。
		draggable : true,
		// 定义是否显示最大化按钮。
		maximizable : true,
		// 定义是否显示最小化按钮。
		minimizable : false,
		// 定义是否显示折叠按钮。
		collapsible : false,
		// 定义窗口是不是模态（modal）窗口。
		modal : true,
		href : 'roleManage/showAdd.action'
	});
	// 窗口居中。
	$('#win').center;
}
//新框架统一修改页面
function ModifyData(url) {
	$('#win').window({
		width : 450,
		height : 350,
		title : '修改系统角色',
		// 定义窗口是否可拖拽。
		draggable : true,
		// 定义是否显示最大化按钮。
		maximizable : true,
		// 定义是否显示最小化按钮。
		minimizable : false,
		// 定义是否显示折叠按钮。
		collapsible : false,
		// 定义窗口是不是模态（modal）窗口。
		modal : true,
		href : url
	});
	// 窗口居中。
	$('#win').center;
	loadGridViewData();
}
</script>
</head>

<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<div class="query clearfix"style="padding: 10px 0px 10px 10px;">
			<div style="float:left;">
				<span>所属应用：</span> 
				<i> 
				 <s:select id="belongApp" name="belongApp" class="easyui-searchbox" searcher="searchByCondition"
				 	style="width:150px;height:28px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
				             list="#request.limitApp" listKey="key" listValue="value" onchange="loadGridViewData();">
 	     			  </s:select>
				</i>
			</div> 
			<div class="search" style="margin:2px 0 0 20px;">            
            	<%-- <a href="javascript:void(0);" title="搜索" onclick="searchByCondition();">
            		<img src="${pageContext.request.contextPath}/images/searches.jpg" />
            	</a> --%>
            	<!-- <a href="javascript:void(0);" onclick="searchByCondition();" style="margin-left:20px;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">搜索</a> -->
            	
            	<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-left:10px;float: right" onclick="searchByCondition();">
						<i class="icon icon-search"></i>&nbsp;&nbsp;搜索
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
