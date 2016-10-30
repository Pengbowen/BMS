<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限列表</title>
<meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/style/public.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script type="text/javascript">
var rootPath="${pageContext.request.contextPath}/";
var postUrl=rootPath+"permissionManage/loadData.action";
var gridviewId="tg";
$(function(){		
	//构建一颗树
	$("#tg").treegrid({	
	    url:postUrl,
		//rownumbers: true,		
		animate:true,		
		collapsible:true,		
		fitColumns:true,			
		idField:'perid',		
		treeField:'pername',//显示图标	
		loadMsg: '正在加载，请稍后...',
			onBeforeLoad:function(){
			resizeGridview();
		},  
		columns:[[	
			   {field :'num',title : '序号',width : '40',align : 'center',formatter : formateSerialNo1},
               {field:'pername',title:'权限名称',width:180,align:'left'},
               {field:'perid',title:'权限ID',width:250,align:'left'},
               {field:'enableName',title:'状态',width:50,align:'center'},
               {field:'showOrder',title:'顺序号',width:40,align:'center'},
               {field:'target',title:'URL链接',width:300,align:'left'},
			   {field:'operate',title:'操作',width:80,align:'left',formatter:formatterOperateColunms}
		]],
		toolbar:[{
				id:'btnCollapseAll',
				text:'全部折叠',
				iconCls:'icon-collapse',
				handler:function(){
				   $("#tg").treegrid('collapseAll');
				}
			},'-',{
				id:'btnExpandAll',
				text:'全部展开',
				iconCls:'icon-expand',
				handler:function(){
				  $("#tg").treegrid('expandAll');
				}
			},'-',{
				//id:'btnExpandAll',
				text:'新增权限',
				iconCls:'icon icon-plus-sign',
				handler:function(){
				  showAdd();
				}
			} ]						
	});
		//设置窗体的大小缩放时，gridview的大小也自动缩放
    $(window).resize(function () {
		resizeGridview();
    });
});

//操作类列
function formatterOperateColunms(value,row,index)
{
     var html = '<div class="coloperate">';
     html+="&nbsp;&nbsp;";
     if(row.enable=="1")
     {
       html+='<a href="javascript:void(0);" style="color:gray;" title="启用" class="icon icon-play" ></a>&nbsp;&nbsp;&nbsp;';
       html+='<a href="javascript:void(0);"  title="停用" class="icon icon-stop" onclick="editState(\''+row.perid+'\',0)" ></a>&nbsp;&nbsp;&nbsp;';
     }else
     {
        html+='<a href="javascript:void(0);"  title="启用" onclick="editState(\''+row.perid+'\',1)" class="icon icon-play"></a>&nbsp;&nbsp;&nbsp;';
        html+='<a href="javascript:void(0);" style="color:gray;" title="停用" class="icon icon-stop"></a>&nbsp;&nbsp;&nbsp;';
     }
     
     html+='<a href="javascript:void(0);" title="修改" onclick="showModify(\''+row.perid+'\')" class="icon icon-pencil"></a>&nbsp;&nbsp;&nbsp;';
     html+='<a href="javascript:void(0);" title="删除" onclick="showDelete(\''+row.perid+'\',\''+row.pername+'\')" class="icon icon-trash"></a>&nbsp;&nbsp;&nbsp;';
     if(row.leafNode == "0")
     {
     	html+='<a href="javascript:void(0);"  title="增加子权限" onclick="showAddChild(\''+row.perid+'\')" class="icon icon-plus"></a>';
     }
     
     html+='</div>';
     
     return html;							
}
 
// 新框架新增方法
function NewData(url) {
	$('#win').window({
		width : 650,
		height : 460,
		title : "新增权限",
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
}
 //添加父权限
function showAdd()
{
	var url=rootPath+"permissionManage/showAdd.action";
	NewData(url,"searchByCondition");
}
//添加子权限
function showAddChild(limitId)
{
	var url=rootPath+"permissionManage/showAdd.action?parentId="+limitId;
	NewData(url,"searchByCondition");
}

//修改权限
function showModify(limitId)
{
	var url=rootPath+"permissionManage/showModify.action?id="+limitId;
	ModifyData(url,"searchByCondition");
}
//新框架统一修改页面
function ModifyData(url) {
	$('#win').window({
		width : 650,
		height : 450,
		title : '修改',
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
	//loadGridViewData();
}
//启用停用
function editState(limitId,state)
{
    var title="如果有子权限也会"+(state==1?"启用":"停用")+"，确定要"+(state==1?"启用":"停用")+"该权限吗？";
    var url=rootPath+"permissionManage/eidtState.action?id="+limitId+"&state="+state;
	OptOneToAjax(url,title);
}
//系统权限启用
	function OptOneToAjax(NextHref,MsgTitle){
		$.messager.confirm("信息提示",decodeURI(MsgTitle),function(ok){
		if(ok)
		{
			url=NextHref;
			AjaxJson(url, true, "", doReturnData);
			$.messager.alert("信息提示！", "操作成功！");
		}
	});
	}
	function doReturnData(rtnJson){
		if(rtnJson.result == "1"){
			$.messager.alert(rtnJson.message);
			window.location.reload();
		}else{
			$.messager.alert(rtnJson.message);
		}
	}

//删除权限
function showDelete(limitId,title)
{
	var url=rootPath+"permissionManage/delete.action?id="+limitId;
	OptOneToAjax(url,"确定要删除"+title+"该权限吗？");
}

//搜索
function searchByCondition()
{
    $("#tg").treegrid("reload", {belongApp:$("#belongApp").val()});
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
				             list="#request.limitApp" listKey="key" listValue="value">
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
			<table id="tg" ></table>
		</div>

	</div>
	
	<!-- 点击新增所显示页面 -->
	<div id="win"></div>
</body>
</html>

