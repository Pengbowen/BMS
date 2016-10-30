<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/layeritems/scripts/operate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/commonUpload.js" type="text/javascript"></script>
<title>层项目管理</title>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + "sst/loadLayerItems.action";
	var gg = "{title:\"文档\",extensions:\"doc,docx,xls,xlsx\"}";
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	//获取本体系表是否具有从属关系,如果有,则为1,无则为0
	var SSTType = "${SSTType}";
	$(function() { 
		var SSTId = $("#SSTId").val();
		var id = ${SSTId};
		$("#SSTId").combobox("select",id);
		$('#' + gridviewId).datagrid({
			fit : true,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			//idField : 'roleId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
		
			columns : [ [//设置gridview的列信息*
			{
				field : 'SSTId',
				title : '体系表编号',
				width : '100',
				align : 'center',
				hidden : true
			},{
				field : 'layerItemId',
				title : '项目编号',
				width : '100',
				align : 'center',
				hidden : true
			},
			{
				field : 'code',
				title : '序号',
				width : '100',
				align : 'center',
				formatter : showClassNo
			},{
				field : 'layerItemName',
				title : '项目名称',
				width : '500',
				align : 'left',
				formatter:formatName
			}, {
				field : 'layerNo',
				title : '所属层级',
				width : '100',
				align : 'left'
			}, {
				field : 'displayOrder',
				title : '显示顺序',
				width : '100',
				align : 'center'
			}, {
				field : 'isEnabled',
				title : '可用状态',
				width : '100',
				align : 'center',
				formatter:formatState
			}, {
				field : 'operate',
				title : '操作',
				width : '150',
				align : 'center',
				formatter:formateOperateCell
			}, {
				field : 'belongItemId',
				title : '所属父级',
				width : '1',
				align : 'center',
				hidden : true
			}
			] ],
			toolbar:[{
				text:'新增',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				   NewData(rootPath + "sst/addLayerItem.action?SSTId="+id+"&SSTType="+SSTType,"添加项目");
				}
			} ,'-',{
				text:'批量导入',
				iconCls:'icon-redo',
				handler:function(){
				NewWindow(rootPath+"sst/toUploadItems.action","上传",800,250);
				}
			} ]	,
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
	
	function formatState(value, row, index){
		var html="<span>"
		if(value==1){
		html+="启用</span>";
		}
		if(value==0){
		html+="停用</span>"
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
				<span style="margin-left:8px;">体系表：</span>
				<select id="SSTId" class="easyui-combobox"  prompt="--请选择--" name="washOver" style="width:150px;height:26px;" inQuery="true" disabled="true">   
    			<option  value="1">技术标准体系表</option>   
    			<option  value="2">管理标准体系表</option>   
    			<option  value="3">工作标准体系表</option>   
				</select>  
     			
     			<span style="margin-left:10px;">项目名称：</span>
     			<input class="easyui-textbox" prompt="请输入项目名称"
						style="width:150px;height:26px;vertical-align:middle;"
						id="layerItemName" name="consignee" inQuery="true" operator="like" />	
				<span style="margin-left:3px;">层级：</span>
				<input class="easyui-textbox" prompt="请输入层级"
						style="width:150px;height:26px;vertical-align:middle;"
						id="layerNo" name="consignee" inQuery="true" operator="like" />	
				<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-right:0px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;查询
		     	</button>
 	     	  </div>
			</div>
			</div>
		</div>
			<input type="text" value="${SSTId}" style="display:none">
				<div region="center" border="false"
					style="padding:0 3px 0 10px;">
					<table id="ViewID"></table>
				</div>

</div>

	<!-- 点击新增所显示页面 -->
	<div id="win"></div>
</body>
</html>