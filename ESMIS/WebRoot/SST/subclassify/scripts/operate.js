var rootPath = "${pageContext.request.contextPath}/";
//设置体系表样式
function setting() {
	var url = rootPath + "SST/styleSetting.jsp?name="+parent.$('#tabs').tabs('getSelected').panel('options').title;
	NewData(url);
}
function NewData(url) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
	width : 380,
	height : 290,
	title : '体系表样式设置',
	// 定义窗口是否可拖拽。
	draggable : true,
	// 定义是否显示最大化按钮。
	maximizable : false,
	// 定义是否显示最小化按钮。
	minimizable : false,
	// 定义是否显示折叠按钮。
	collapsible : false,
	// 定义窗口是不是模态（modal）窗口。
	modal : true,
	content : content,
	});
// 窗口居中。
$('#win').center;
}
//客衣类格式化操作列内容
function formateOperateCell(value, row, index) {
	var html = "";
	// 修改
	var modifyUrl = rootPath + 'sst/modifySubClass.action?subClassId='
			+ row.subClassId;
	html += '<A onclick="NewWindow(\''
			+ modifyUrl
			+ '\','+'\'修改\''+',380,290); " href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 删除
	var deleteUrl = rootPath + 'sst/deleteSubClass.action?subClassId='
			+ row.subClassId+"&layerItemId="+row.layerItemId;
	html += '<A onclick="DeleteData(\''
		+ deleteUrl
		+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
		
	return html;
}
//保存数据
function saveMessage() {
	var layerItemId = $("#select").combobox("getValue");
	if(!verifyData()){
		return;
	}
	var url;
	
	if ($("#subClassId").val() == "") {
		url = "sst/addSubClass.action?layerItemId="+layerItemId;
	} else {
	
		url = "sst/saveSubClass.action?layerItemId="+layerItemId;
	}
	strData = $("#myForm").serialize();
	AjaxJson(rootPath + url, true, strData, message);
}

function message(data){
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			resetParent();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}
//关闭本页面，刷新父页面
function resetParent(){
	//关闭本窗口
	window.parent.closeWin();
	//刷新父级窗口
	window.parent.reload();
}
//验证数据
function verifyData() {
	if (jQuery.trim($("#subClassName1").val()) == "") {
		$.messager.alert('提示信息', '请输入子分类名称！');
		$("#subClassName1").focus();
		return false;
	}
	if (jQuery.trim($("#displayOrder").val()) == "") {
		$.messager.alert('提示信息', '请输入显示顺序!');
		$("#displayOrder").focus();
		return false;
	}
	if (jQuery.trim($("#displayOrder").val()) <= 0) {
		$.messager.alert('提示信息', '显示顺序必须不小于1！');
		$("#displayOrder").focus();
		return false;
	}
	if (jQuery.trim($("#select").combobox('getValue')) == "") {
		$.messager.alert('提示信息', '请选择所属父级');
		$("#select").focus();
		return false;
	}
	
	return true;
}
function NewWindow(url,title,width,height) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
	width : width,
	height : height,
	title : title,
	// 定义窗口是否可拖拽。
	draggable : true,
	// 定义是否显示最大化按钮。
	maximizable : false,
	// 定义是否显示最小化按钮。
	minimizable : false,
	// 定义是否显示折叠按钮。
	collapsible : false,
	// 定义窗口是不是模态（modal）窗口。
	modal : true,
	content : content,
	});
// 窗口居中。
$('#win').center;
}