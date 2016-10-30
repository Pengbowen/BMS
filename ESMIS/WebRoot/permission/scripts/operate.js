//操作列
function formateOperateCell(value,row,index){
	var html = "";
	
	//查看权限
	var lookLimitUrl = rootPath+"permission/look_limit.jsp?roleId="+row.roleId+"&belongApp="+row.belongModule;
	html+='<A onclick="ShowData(\''+lookLimitUrl+'\',\'loadGridViewData\');" href="javascript:void(0);" class="icon icon-search" title="查看权限"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	
    //修改
	var modifyUrl =rootPath+"roleManage/showModify.action?roleId="+row.roleId+"&belongApp="+row.belongModule;
	html+='<A onclick="ModifyData(\''+modifyUrl+'\',\'loadGridViewData\');" href="javascript:void(0);" class="icon icon-pencil" title="修改"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	
	//删除
	var deleteUrl = rootPath+"roleManage/delete.action?roleId="+row.roleId+"&belongApp="+row.belongModule;
	html+='<A onclick="DeleteData(\''+deleteUrl+'\',\'确认要删除吗?\')" href="javascript:void(0);" class="icon icon-trash" title="删除"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	
	//增加子角色
	/*var addChildUrl = rootPath+"roleManage/showAdd.action?parentId="+row.roleId+"&belongApp="+row.belongModule;
	html+='<A onclick="NewData(\''+addChildUrl+'\',\'loadGridViewData\');" href="javascript:void(0);" class="icon icon-plus" title="增加子角色"></A>';*/
	
	return html;
}
function ShowData(url) {
	$('#win').window({
		width : 550,
		height : 450,
		title : '权限列表',
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
