
//点击新增页面执行的方法
var rootPath = "${pageContext.request.contextPath}/";

//操作列
function formateOperateCell(value, row, index) {
	var html = "";

	//修改
	var modifyUrl = rootPath + "user/tomodifyPage.action?id=" + row.userId;
	html += '<A onclick="ModifyData1(\''
			+ modifyUrl
			+ '\');" href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//启用
	if (row.state == "0") {
		var startUrl = rootPath + "user/startUser.action?id=" + row.userId;
		html += '<A onclick="OptEasyUIToAjax(\''
				+ startUrl
				+ '\',\'确认要启用吗?\')" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<A style="color:gray;" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	} else {
		html += '<A style="color:gray;" href="javascript:void(0);"  title="启用" class="icon icon-play" ></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//停用
		var endUrl = rootPath + "user/endUser.action?id=" + row.userId;
		html += '<A onclick="OptEasyUIToAjax(\''
				+ endUrl
				+ '\',\'确认要停用吗?\')" href="javascript:void(0);" title="停用" class="icon icon-stop"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	//重置密码
	var resetPWDUrl = rootPath + "user/resetUser.action?id=" + row.userId;
	html += '<A onclick="OptEasyUIToAjax(\''
			+ resetPWDUrl
			+ '\',\'重置后的密码默认为：111111\')" href="javascript:void(0);"  title="重置密码" class="icon icon-key"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//删除
	var deleteUrl = rootPath + "user/deleteUser.action?id=" + row.userId;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//查看
	var lookLimitUrl = rootPath + "user/todetailPage.action?id="
			+ row.userId;
	html += '<A onclick="showDetail(\'' + lookLimitUrl
			+ '\');" href="javascript:void(0);"  title="查看详情" class="icon icon-search"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//设置体系表权限
	var sstLimitSet = rootPath + "sst/powerSet.action";
	html += '<A onclick="newTab(\'' + sstLimitSet
			+ '\');" href="javascript:void(0);"  title="角色体系表权限设置" class="icon icon-sliders "></A>';
	return html;
}
//新标签打开方法
	function newTab(url){
		window.parent.tab("角色体系表权限设置",url);
	}

// 新框架新增方法
function showDetail(url) {
	$('#win').window({
		width : 450,
		height : 300,
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
	// 窗口居中。
	$('#win').center;
	loadGridViewData();
}
//新增
function showAdd() {
	var url = rootPath + "user/toaddPage.action";
	NewData('' + url + '');
}
// 新框架新增方法
function NewData(url) {
	$('#win').window({
		width : 450,
		height : 450,
		title : '新增人员信息',
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

function ModifyData1(url) {
	$('#win').window({
		width : 450,
		height : 450,
		title : '修改人员信息',
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

//user-add.jsp添加页面方法
function saveMessage() {
	if (!verifyData())
		return;
	var url;
	if ($("#id").val() == "") {
		url = rootPath + "user/addUser.action";
	} else {
		url = rootPath + "user/modifyUser.action";
	}

	strData = $("#myForm").serialize();
	AjaxJson(url, true, strData, doDocAdd);
}

function doDocAdd(data) {
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			location.reload();
			closeWin();
		});
	} else {
		$.messager.alert("提示", data.message);
	}
}

//验证数据
function verifyData() {
	if (jQuery.trim($("#unitno").val()) == "-1") {
		$.messager.alert("提示", "请选择部门");
		return false;
	}
	if ($("#roleId1").val() == "" || $("#roleId1").val() == "-1") {
		$.messager.alert("提示", "请选择角色");
		$("#roleId1").focus();
		return false;
	}

	if (jQuery.trim($("#realname1").val()) == "") {
		$.messager.alert("提示", "请填写姓名");
		$("#realname1").focus();
		return false;
	}

	if ($("#sexinfo1").val() == "") {
		$.messager.alert("提示", "请填选择性别");
		return false;
	}

	if ($("#loginname1").val() == "") {
		$.messager.alert("提示", "请填写登录名");
		$("#loginname").focus();
		return false;
	}
	//新增人员时验证密码
	if ($("#id").val() == ""){
		var oldPwd = $("#password1").val();
		if (oldPwd.length<3||oldPwd.length>8) {
			$.messager.alert("提示", "密码长度必须为3~8位");
			return false;
		}
		if (!(/^\d+$/.test(oldPwd))) {
			$.messager.alert("提示", "必须为纯数字");
			return false;
		}
	}
	var oldPwd = $("#password1").val();
	var newPwd = $("#newPwd1").val();
	if (oldPwd != newPwd) {
		$.messager.alert("提示", "两次输入的密码不一致,请重新输入");
		$("#newPwd1").focus();
		return false;
	}
	return true;
}
//修改密码时验证密码规则
function newPassword(){
	var oldPwd = $("#password1").val();
	if (oldPwd.length<3||oldPwd.length>8) {
		$.messager.alert("提示", "密码长度必须为3~8位");
		return false;
	}
	if (!(/^\d+$/.test(oldPwd))) {
		$.messager.alert("提示", "必须为纯数字");
		return false;
	}
}
