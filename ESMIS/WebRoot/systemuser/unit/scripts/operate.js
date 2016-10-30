var rootPath = "${pageContext.request.contextPath}/";
// 操作列
function formateOperateCell(value, row, index) {
	var html = "";
	// 修改
	var modifyUrl = rootPath + "unit/tomodifyUnit.action?id=" + row.unitNo;
	html += '<A onclick="ModifyData(\'' + modifyUrl
			+ '\');" href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 启用
	if (row.enable == "0") {
		var startUrl = rootPath + "unit/startUnit.action?id=" + row.unitNo;
		html += '<A onclick="OptEasyUIToAjax(\''
				+ startUrl
				+ '\',\'确认要启用吗?\')" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<A style="color:gray;" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	} else {
		html += '<A style="color:gray;" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 停用
		var endUrl = rootPath + "unit/endUnit.action?id=" + row.unitNo;
		html += '<A onclick="OptEasyUIToAjax(\''
				+ endUrl
				+ '\',\'确认要停用吗?\')" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	// 删除
	var deleteUrl = rootPath + "unit/deleteUnit.action?id=" + row.unitNo;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';

	// 查看人员
	html += '<A onclick="ListData(\''+row.unitNo+'\',\'' +row.unitName+ '\')" href="javascript:void(0);" title="查看人员" class="icon icon-user"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
//修改
function ModifyData(url) {
	$('#win').window({
		width : 450,
		height : 350,
		title : '修改部门信息',
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
//添加
function showAdd() {
	var url = rootPath + "unit/toaddUnit.action";
	NewData('' + url + '');
}
//新框架新增方法
function NewData(url) {
	$('#win').window({
		width : 450,
		height : 350,
		title : '新增部门信息',
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
//打开页面：查看人员列表
function ListData(unitNo,unitName)
{
	window.parent.tab('查看【'+unitName+'】的人员列表',rootPath+'unit/showUser.action?unitNo='+unitNo);
}
//保存数据
function saveMessage(id) {
	if (jQuery.trim($("#unitname").val()) == "") {
		$.messager.alert("提示", "请填写部门名称");
		$("#unitname").focus();
		return;
	}

	var url;
	var strData = $("#myForm").serialize();
	if (jQuery.trim(id) == "") {
		url = rootPath + "unit/addUnit.action";
	} else {
		url = rootPath + "unit/modifyUnit.action";
		strData += "&id=" + id;
	}

	AjaxJson(url, true, strData, doDocAdd);
}

function doDocAdd(data) {
	if (data.result == "1") {
		$.messager.alert("提示", data.message);
		location.reload();
		closeWin();
	} else {
		$.messager.alert("提示", data.message);
	}
}

//查看人员操作列
//操作列
function formateOperateCell1(value,row,index){
	var html = "";
	//查看
	var lookLimitUrl = rootPath+"user/todetailPage.action?id="+row.userId;
	html+='<A onclick="ShowData1(\''+lookLimitUrl+'\');" href="javascript:void(0);"  title="查看简介" class="icon icon-search"></A>&nbsp;&nbsp;';
	//启用
	if(row.state=="0"){
	   var startUrl = rootPath+"user/startUser.action?id="+row.userId;
	   html+='<A onclick="OptEasyUIToAjax(\''+startUrl+'\',\'确认要启用吗?\')" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;';
	   html+='<A style="color:gray;" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;';
	}else
	{
	   html+='<A style="color:gray;" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;';
	 //停用
	  var endUrl = rootPath+"user/endUser.action?id="+row.userId;
	  html+='<A onclick="OptEasyUIToAjax(\''+endUrl+'\',\'确认要停用吗?\')" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;';
	}
	//删除
	var deleteUrl = rootPath+"user/deleteUser.action?id="+row.userId;
	html+='<A onclick="OptEasyUIToAjax(\''+deleteUrl+'\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;';
	return html;
}
//查看详情
function ShowData1(url) {
	$('#win').window({
		width : 450,
		height : 250,
		title : '人员详细信息',
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
	$('#win').center;
}
