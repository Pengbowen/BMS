<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加标准</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/standard/scripts/operate.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	var documentType="${documentType}";
	var LoadDataUrl = rootPath + "sst/loadStandardData.action?documentType="+documentType;
	//声明变量接收后天传来的体系表编号,层项目编号,子分类编号
	var SSTId= "${SSTId}";
	var layerItemId = "${layerItemId}";
	var subClassId = "${subClassId}";
	$(function() {
		if(documentType==1){
		$("#law").remove();
		}
		if(documentType==2){
		$("#standard").remove();
		}
	
		$('#' + gridviewId).datagrid({
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : false,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			checkOnSelect:true,
			height:450,
			//idField : 'roleId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			columns : [ [//设置gridview的列信息*
			{
				field : 'code1',
				width : '50',
				align : 'center',
				checkbox:'true'
			},{
				field : 'standardId',
				title : '标准Id',
				width : '100',
				align : 'center',
				hidden: true
			},{
				field : 'standardNo',
				title : '标准编号',
				width : '200',
				align : 'center'
			}, {
				field : 'standardName',
				title : '标准名称',
				width : '360',
				align : 'left'
			}, {
				field : 'standardCategoryName',
				title : '标准类别',
				width : '600',
				align : 'left',
				hidden: true
			}
			] ],
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
		//获取选择的数据,如果用户没有选择数据,就提醒用户选择
		data = $('#' + gridviewId).datagrid("getSelections");
		if(data.length==0){
		$.messager.alert('提示信息', '请先选择数据');
		return;
		};
		//声明数据字符串
		var dataStr="dataStr =";
		for(var i =0;i<data.length;i++){
		var row = data[i];
		//数据命名为dataStr
		var standardId="";
		if(i!=data.length-1){
		standardId = row.standardId+",";
		}else{
		standardId= row.standardId;
		}
		dataStr+=standardId;
		}
		var sendUrl = rootPath+"sst/importStandard.action?SSTId="+SSTId+"&layerItemId="+layerItemId+"&subClassId="+subClassId; 
		 AjaxJson(sendUrl, true, dataStr, function(datalist) {
			$.messager.alert('提示信息', datalist.message);
		}, false) 
	}
	//关闭窗口方法
	function closeWindow(){
	//刷新父级页面
	window.parent.reload();
	//关闭窗口 
	window.parent.closeWin();
	}
	
	
</script>
<div class="easyui-panel" style="width:100%;height:600px;" border=0>
	<div>
		<div id="standard">
		 <div class="input_box" style="margin-top:8px;margin-bottom:8px;">
     			<span style="margin-left:20px;">标准编号：</span>
     			<input class="easyui-textbox" prompt="请输入项目编号"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardNo" name="standardNo" inQuery="true" operator="like" />	
				<span style="margin-left:100px;">标准名称：</span>
				<input class="easyui-textbox" prompt="请输入标准名称"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardName" name="standardName" inQuery="true" operator="like" />	
		</div>
		<div class="input_box" style="margin-top:8px;margin-bottom:8px;">
				<span style="margin-left:20px;">标准类别：</span>
     			<input class="easyui-combobox" prompt="请选择标准类别" inQuery="true" panelHeight="auto"  value="${standardCategory}"
						id="standardCategory" name="standardCategory"  editable="true" style="width:150px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
					</input>
				<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-right:70px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;查询
		     	</button>
 	      </div>
 	      </div>
 	      <div id="law">
		 <div class="input_box" style="margin-top:8px;margin-bottom:8px;">
     			<span style="margin-left:20px;">法规文号：</span>
     			<input class="easyui-textbox" prompt="请输入法规文号"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardNo" name="standardNo" inQuery="true" operator="like" />	
				<span style="margin-left:20px;">法规名称：</span>
				<input class="easyui-textbox" prompt="请输入法规名称"
						style="width:150px;height:26px;vertical-align:middle;"
						id="standardName" name="standardName" inQuery="true" operator="like" />	
						<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-right:10px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;查询
		     	</button>
		</div>
		<%-- <div class="input_box" style="margin-top:8px;margin-bottom:8px;">
				<span style="margin-left:20px;">标准类别：</span>
     			<select id="standardCategory" name="standardCategory"  editable="true" style="width:150px;height:28px;">
     				<option value="1">企业贯彻执行的法律法规</option>
     				<option value="2">企业适用的法律法规</option>
     			</select>
				<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-right:70px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-search"></i>&nbsp;&nbsp;查询
		     	</button>
 	      </div> --%>
 	      </div>
		<!-- 数据加载容器 -->
			<table id="ViewID" style="height:500px;"></table>
			<div class="easyui-btn">
				<i> 
					<input type="button" value="导入" class="btn-effect" onclick="selectData()" />
					<input type="button" value="关闭" class="btn-effect" onclick="closeWindow()" />
				</i>
			</div>
	</div>
</div>
</body>
</html>
