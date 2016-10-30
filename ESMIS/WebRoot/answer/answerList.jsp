<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>意见反馈</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />

	<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/answer/scripts/list.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		var rootPath = "${pageContext.request.contextPath}/";
		var LoadDataUrl = rootPath + 'answer/searchAnswerByConditionAndPage.action?type='+${type};
		var gridviewId = 'ViewID', pager;
		var currentPage = 1, linesOfPage = 10;
		//页面加载完成后，执行的页面初始化工作
		$(function() {
			$('#' + gridviewId).datagrid({
				fit : true,
				nowrap : true,//当数据长度超出列宽时将会自动截取
				singleSelect : true,//是否只允许选择一行
				autoRowHeight : false,//行高自适应
				striped : true,//交替显示行背景
				//idField : 'id',//唯一列
				loadMsg : '', //数据加载中，等待 展示的文字*
				/* frozenColumns : [ [ {
					field : 'ck',
					title : '选择',
					checkbox : true
				} ] ], */
				columns : [ [//设置gridview的列信息*
				{
					field : 'code',
					title : '序号',
					width : '70',
					align : 'center',
					formatter : showClassNo
	
				}, {
					field : 'id',
					title : 'id',
					hidden : true
				} , {
					field : 'submitTitle',
					title : '标题',
					width : '250',
					align : 'left',
					formatter : submitTitleFormatter
				}, {
					field : 'submitName',
					title : '提交人',
					width : '180',
					align : 'left'
				}, {
					field : 'submitorEmail',
					title : '邮箱',
					width : '200',
					align : 'left'
				}, {
					field : 'submitTime',
					title : '提交时间',
					width : '150',
					align : 'center',
					formatter : submitTimeFormatter
				}, {
					field : 'isreceive',
					title : '状态',
					width : '90',
					align : 'center',
					formatter : isreceiveFormatter
				}, {
					field : 'operate',
					title : '操作',
					width : '150',
					align : 'center',
					formatter : operateFormatter
				}] ],
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
			<div class="query clearfix" style="padding: 18px 18px 18px 10px;">
			<div style="float:left;">
				<span style="margin-left:10px;">标题：</span> 
					<input class="easyui-textbox" id="submitTitle" name="submitTitle" inQuery="true" operator="like" data-options="prompt:'请输入标题'" style="width:150px;height:26px" />
				<span style="margin-left:10px;">状态：</span> 
					<select class="easyui-combobox" id="isreceive" name="isreceive" inQuery="true" style="width:150px;height:26px">
						<option value="-1">全部</option>
						<option value="0">未回复</option>
						<option value="1">已回复</option>
					</select>
				<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-left:30px;float: right" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;搜索
				</button>
			</div>
		</div>
	</div>
	<div region="center" border="false" style="padding:0 3px 0 10px;">
		<table id="ViewID"></table>
	</div>

	</div>

	<!-- 点击新增所显示页面 -->
	<div id="win"></div> 
  </body>
</html>
