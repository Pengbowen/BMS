// 保存数据
function saveMessage() {
	if (!verifyData())
		return;

	var url;
	if ($("#id").val() == "") {
		url = "dictionarynew/add.action";
		// url
		// +=url+"dictionary="+document.getElementById("dictionaryid").value;
	} else {
		url = "dictionarynew/modify.action";
	}
	strData = $("#myForm").serialize();
	AjaxJson(rootPath + url, true, strData, doDocAddByIframe);
}

// 关闭本页面，刷新父页面
function resetParent() {
	// 关闭本窗口
	window.parent.closeWin();
	// 刷新父级窗口
	window.parent.refreshSelTab();
}

// Iframe框架下的回调函数刷新页面
function doDocAddByIframe(data) {
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message, '', function(r) {
			resetParent();
		});
	} else {
		$.messager.alert("信息提示！", data.message);
	}
}

// 验证数据
function verifyData() {
	if (jQuery.trim($("#content").val()) == "") {
		$.messager.alert('提示信息', '请输入名称！');
		$("#content").focus();
		return false;
	}
	if (jQuery.trim($("#code").val()) == "") {
		$.messager.alert('提示信息', '请输入关键值！');
		$("#code").focus();
		return false;
	}
	if (jQuery.trim($("#showNum").val()) == "") {
		$.messager.alert('提示信息', '请输入显示顺序！');
		$("#showNum").focus();
		return false;
	}
	return true;
}

// 验证关键值是否存在
function blurCode() {
	var code = $("#code").val();
	var dictionaryid = $("#dictionaryid").val();
	if (code != "") {
		var changeUrl = "dictionarynew/checkCode.action?code=" + code
				+ "&dictionaryid=" + dictionaryid;
		AjaxJson(rootPath + changeUrl, true, null, doDoc);
		function doDoc(data) {
			if (data.result == "1") {
				$.messager.alert('提示信息', '关键值已经存在,请重新输入');
				return false;
			}
		}
	}
}
// 状态
function showState(value, row, index) {
	var html = "";
	if (value == "1") {
		html = "是";
	} else {
		html = "否";
	}
	return html;
}
// 服务分类，数据库存放数字格式，页面显示内容
function formateServiceClass(value, row, index) {
	var html = "";
	if (value == "10") {
		html = "衣物";
	}
	if (value == "20") {
		html = "鞋包";
	}
	return html;
}
// 数据字典格式化操作列内容
function formateOperateCell(value, row, index) {
	var html = "";
	// 修改
	var modifyUrl = rootPath + 'dictionarynew/showInfo.action?id=' + row.id;
	html += '<A onclick="ModifyData(\''
			+ modifyUrl
			+ '\')" href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 删除
	var deleteUrl = rootPath + 'dictionarynew/delete.action?id=' + row.id;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

// 新框架统一修改页面
function ModifyData(url) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	$('#win').window({
		width : 400,
		height : 258,
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
		content : content
	});
	// 窗口居中。
	$('#win').center;
}
// 新框架新增方法
function NewData(url) {
	var url = rootPath + 'dictionarynew/showInfo.action?dictionaryid='
			+ dictionaryid;
	var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	$('#win').window({
		width : 415,
		height : 300,
		title : '新增标准信息',
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
		content : content
	});
	// 窗口居中。
	$('#win').center;
}
