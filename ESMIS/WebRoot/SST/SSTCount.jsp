<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>体系表统计</title>
<link href="${pageContext.request.contextPath}/style/public.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>

<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + "user/loadData.action";

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
			}, {
				field : 'loginName',
				title : '标准类别编号',
				width : '94',
				align : 'left'
			}, {
				field : 'unitNo',
				title : '标准类别名称',
				width : '290',
				align : 'left',
			}, {
				field : 'unitName',
				title : '1级',
				width : '110',
				align : 'left',
			}, {
				field : 'unitName',
				title : '2级',
				width : '110',
				align : 'left',
			}, {
				field : 'unitName',
				title : '3级',
				width : '110',
				align : 'left',
			}, {
				field : 'unitName',
				title : '4级',
				width : '110',
				align : 'left',
			}] ],
			toolbar:[{
				text:' 发布数据 ',
				handler:function(){
				   showAdd();
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

</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" class="clearfix">
			 <div class="query clearfix"style="padding: 10px 0px 10px 10px;margin: 10px;">
				<div style="float:left;">
							<span>体系表：</span>
							<i>
								<select style="width:251px;height:25px;">
									<option>技术标准体系表</option>
									<option>管理标准体系表</option>
									<option>工作标准体系表</option>
								</select>
							</i>
						</div>
						<div class="search" style="margin:0px;">            
			            	<button  class="btn btn-primary" type="button" style="width:80px;height:28px;margin-left:10px;float: right" onclick="searchByCondition();">
								<i class="icon icon-search"></i>&nbsp;&nbsp;统计
							</button>
			            </div>
			 </div>
			</div>
		<div region="center" border="false" style="padding:0 3px 0 10px;">
			<table id="ViewID" ></table>
		</div>
	</div>
</body>
</html>