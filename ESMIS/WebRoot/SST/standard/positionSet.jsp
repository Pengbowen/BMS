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
	$(function() {
		$('#tb').datagrid({
			fit : false,
			nowrap : true,//当数据长度超出列宽时将会自动截取
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			rownumbers:true,
			//idField : 'roleId',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*
			height:200,
			columns : [ [//设置gridview的列信息*
			
			{
				field : 'standardNo',
				title : '标准编号',
				width : '365',
				align : 'center'
			}, {
				field : 'standardName',
				title : '标准名称',
				width : '365',
				align : 'center'
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
				hidden : true
			}]]
		});
		var temp = window.parent.data;
		$('#tb').datagrid({
			data:temp
		});

		
	});
</script>
</head>

<body>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding:0 3px 0 10px;">
			<table id="tb" ></table>
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<span style="margin-left:10	px;">调整顺序：</span>
     			<input class="easyui-textbox" prompt="请输入调整数值"
						style="width:150px;height:30px;vertical-align:middle;"
						id="standardId" name="standardId" inQuery="true" operator="like" />	
						
					<button  class="btn btn-danger" type="button" style="height:28px;width:70px;margin-right:0px;float:right;" onclick="javascript:window.parent.closeWin();">
						<i class="icon icon-times"></i>&nbsp;&nbsp;关闭
		     	</button>		
			<button  class="btn btn-primary" type="button" style="height:28px;width:70px;margin-right:20px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-collapse"></i>&nbsp;&nbsp;置尾
		     	</button>
		     	<button  class="btn btn-primary" type="button" style="height:28px;margin-right:20px;float:right;" onclick="window.closed">
						<i class="icon icon-collapse-top"></i>&nbsp;&nbsp;置顶
		     	</button>
		     		<button  class="btn btn-primary" type="button" style="height:28px;width:70px;margin-right:20px;float:right;" onclick="searchByCondition()">
						<i class="icon icon-arrow-down"></i>&nbsp;&nbsp;下移
		     	</button>
		     	<button  class="btn btn-primary" type="button" style="height:28px;margin-right:70px;margin-right:20px;float:right;" onclick="window.closed">
						<i class="icon icon-arrow-up"></i>&nbsp;&nbsp;上移
		     	</button>
		     	</div>
		</div>	
	</div>
</body>
</html>