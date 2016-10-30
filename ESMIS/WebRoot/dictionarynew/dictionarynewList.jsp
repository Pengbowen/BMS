<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>衣物信息列表</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/dictionarynew/scripts/operate.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + 'dictionarynew/search.action?id=${id}';
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	var dictionaryid ='${id}';
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			idField : 'id',//唯一列
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
				width : '80',
				align : 'left',
				formatter : showClassNo
			}, {
				field : 'id',
				title : 'ID',
				hidden : true,
				width : '80',
				align : 'left'
			}, {
				field : 'dictionaryid',
				title : '数据字典ID',
				hidden : true,
				width : '80',
				align : 'left'
			}, {
				field : 'content',
				title : '名称',
				width : '150',
				align : 'left'
				//formatter : detailView
			}, {
				field : 'customfield1',
				title : '显示与否',
				width : '100',
				align : 'center',
				hidden:true
			}, {
				field : 'fid',
				title : 'FID',
				width : '110',
				align : 'center',
				hidden:true
			}, {
				field : 'showNum',
				title : '显示序号',
				width : '110',
				align : 'center'
			} , {
				field : 'serviceClass',
				title : '服务类型',
				width : '110',
				align : 'center',
				hidden:true,
				formatter : formateServiceClass
			}, {
				field : 'operate',
				title : '操作',
				width : '110',
				align : 'center',
				formatter : formateOperateCell
			}] ],
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
	
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<div class="query clearfix"style="padding: 10px 0px 10px 10px;">
			<div style="float:left;">
				<span>查询：</span> <input class="easyui-searchbox"
					searcher="searchByCondition" prompt="请输入标准名称"
					style="width:150px;height:28px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" title="标准信息"
					type="text" name="content" id="content" inQuery="true"
					operator="like"></input>
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
