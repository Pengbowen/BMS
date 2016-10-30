<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>总库统计表</title>
<link href="${pageContext.request.contextPath}/style/public.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + "lawslibrary/showStatementLaws.action";

	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 10;
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			idField : 'userId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			columns : [ [//设置gridview的列信息*
			{
				field : 'code',
				title : '序号',
				width : '55',
				align : 'center',
				formatter : showClassNo
			}, 	{
				field : 'standardcategory',
				title : '法规类别号',
				width : '250',
				align : 'center',
				hidden:true
			},  {
				field : 'standardcategoryname',
				title : '法规类别',
				width : '220',
				align : 'left',
			}, {
				field : 'standardcategorycount',
				title : '数量',
				width : '270',
				align : 'left',
			}] ],
			toolbar:[{
				text:' 发布生成报表 ',
				handler:function(){
				   addData();
				}
			}]	,
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
	});
	function addData(){
	 if(confirm("发布生成报表，会删除表中对应数据数据，确定发布吗？")){
      var  param = JSON.stringify($('#' + gridviewId).datagrid('getData').rows);
	  var url;
	  strData = "&param="+param;
	  var reportId=12;
      url = "standardrepor/addStandardrepor.action?reportId="+reportId;
	  strData = "&param="+param;
	  AjaxJson(rootPath + url, true, strData, doDocAddByIframe);
      }else{
          return;
      }
function doDocAddByIframe(data) {
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			resetParent();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}
}

</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:0 3px 0 10px;">
			<table id="ViewID" ></table>
		</div>
	</div>
</body>
</html>