<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/standard/scripts/operate.js" type="text/javascript"></script>
<title>标准列表</title>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var condition = "";
	 condition = "${condition}";
	var LoadDataUrl = rootPath + "sst/loadStandardList.action?"+condition;
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	var layerItemId = "";
	layerItemId = "${layerItemId}";
	var subClassId = "";
	subClassId = "${subClassId}";
	var data;
	$(function() {
		var SSTId = ${SSTId};
		$('#' + gridviewId).datagrid({
			fit : true,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : false,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			checkOnSelect:true,
			//idField : 'roleId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
		
			columns : [ [//设置gridview的列信息*
			
		/* 	{
				field : 'code1',
				width : '100',
				align : 'center',
				checkbox:'true'
			}, */
			{
				field : 'code',
				title : '序号',
				width : '100',
				align : 'center',
				formatter : showClassNo
			},{
				field : 'standardNo',
				title : '标准编号',
				width : '300',
				align : 'center'
			}, {
				field : 'standardName',
				title : '标准名称',
				width : '600',
				align : 'left'
			}, 
		/* 	{
				field : 'replaceStandard',
				title : '替代标准',
				width : '200',
				align : 'center'
			}, */
			{
				field : 'effectiveState',
				title : '状态',
				width : '100',
				align : 'center'
			},{
				field : 'operate',
				title : '操作',
				width : '150',
				align : 'center',
				formatter:formateOperateCell
			},{
				field : 'standardId',
				title : '标准id',
				width : '100',
				align : 'center',
				hidden : true
			},{
				field : 'displayOrder',
				title : '显示顺序',
				width : '100',
				align : 'center',
				hidden : false
			}
			] ],
			toolbar:[{
				text:'添加标准',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   NewData(rootPath + "sst/toAddStandard.action?documentType=1&SSTId="+SSTId+"&subClassId="+subClassId+"&layerItemId="+layerItemId);
				}
			} ,'-',{
				text:'添加法律法规',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   NewData(rootPath + "sst/toAddStandard.action?documentType=2&SSTId="+SSTId+"&subClassId="+subClassId+"&layerItemId="+layerItemId);
				}
			},
			//批量导入功能去掉
			'-',{
				text:'批量调整顺序',
				iconCls:'icon icon-sliders',
				handler:function(){
				NewWindow(rootPath+"sst/toUploadStandard.action","上传",800,450);
				}
			}
			/* , '-',{
				text:'批量调整显示顺序',
				iconCls:'icon icon-sliders',
				handler:function(){
				   selectData();
				} 
			} */
			]	,
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
	function selectData(){
		 data = $('#' + gridviewId).datagrid("getSelections");
		if(data.length==0){
		$.messager.alert('提示信息', '请先选择数据');
		return;
		};
		for(var i =0;i<data.length;i++){
		var row = data[i];
		}
		var url = rootPath + "sst/setPosition.action";
		var title = "批量调整顺序";
		pop(url,"批量调整顺序",800,300);
		
	}
	function formatState(value, row, index){
		var html="<span>"
		if(value==1){
		html+="启用</span>";
		}
		if(value==0){
		html+="停用</span>";
		}
		return html;
	}
</script>
</head>

<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<div class="query clearfix" style="padding: 18px 0px 18px 10px;">
			<div style="float:left;">
			 <div class="input_box">
				<%-- <span style="margin-left:8px;">标准类别：</span>
				<input class="easyui-combobox" prompt="请选择标准类型" inQuery="true" panelHeight="auto" 
						id="standardCategory" name="standardCategory"  editable="false" style="width:200px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
				</input> --%>
     			
     			<span style="margin-left:10px;">标准编号/法规文号：</span>
     			<input class="easyui-textbox" prompt="请输入标准编号"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardNo" name="standardNo" inQuery="true" operator="like" />	
				<span style="margin-left:3px;">标准/法规名称：</span>
				<input class="easyui-textbox" prompt="请输入标准名称"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardName" name="standardName" inQuery="true" operator="like" />	
				<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-right:0px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;查询
		     	</button>
 	     	  </div>
			</div>
			</div>
		</div>
			<input type="text" value="${SSTId}"  id="SSTId" style="display:none"/> 	
			<input type="text" value="${layerItemId}" id="layerItemId" style="display:none"/> 	
			<input type="text" value="${subClassId}" id="subClassId" style="display:none"/> 	
		<div region="center" border="false" style="padding:0 3px 0 10px;">
			<table id="ViewID" ></table>
		</div>
	</div>
	<!-- 点击新增所显示页面 -->
	<div id="win">
	</div>
	
</body>
</html>