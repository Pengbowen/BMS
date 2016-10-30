var rootPath = "${pageContext.request.contextPath}/";
//设置体系表样式
function setting(SSTId) {
	var url = rootPath + "SST/styleSetting.jsp?SSTId="+SSTId+"&name="+encodeURI(encodeURI(parent.$('#tabs').tabs('getSelected').panel('options').title));
	NewData(url);
}
//设置层项目信息
function setItems(SSTId){
	window.parent.tab("体系层项目管理",rootPath+"sst/toLayerItems.action?SSTId="+SSTId);
}
function joinStandard(name,value,tag){
	if(tag=="1"){
		window.parent.tab("标准管理",rootPath+"sst/toStandardList.action?SSTId="+name+"&layerItemId="+value);
	}else{
		window.parent.tab("标准管理",rootPath+"sst/toStandardList.action?SSTId="+name+"&subClassId="+value);
	}
}
function NewData(url) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
	width : 806,
	height : 413,
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
	var modifyUrl = rootPath + 'sst/modifyItem.action?layerItemId='
			+ row.layerItemId;
	html += '<A onclick="ModifyData(\''
			+ modifyUrl
			+ '\'); " href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 删除
	var deleteUrl = rootPath + 'sst/deleteLayerItem.action?layerItemId='
			+ row.layerItemId;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 设置子分类信息
	var setUrl = rootPath + "sst/toSubClassifyManage.action?SSTId="
			+ row.SSTId+"&layerItemId="+row.layerItemId;
	html += '<A  href='+setUrl+' title="设置子分类信息" class="icon icon-cogs"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
function ModifyData(url) {
	$('#win').window({
		width : 405,
		height : 350,
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
//保存数据
function saveMessage() {
	if(!verifyData()){
		return;
	}
	var url;
	var bleongItemId=$("#select").combobox('getValue');
	if ($("#layerItemId").val() == "") {
		url = "sst/addLayerItem.action";
	} else {
	
		url = "sst/modifyLayerItem.action?belongItemId="+bleongItemId;
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
//验证数据
function verifyData() {
	if (jQuery.trim($("#layerItemName1").val()) == "") {
		$.messager.alert('提示信息', '请输入项目名称！');
		$("#layerItemName1").focus();
		return false;
	}
	if (jQuery.trim($("#layerNo1").val()) == "") {
		$.messager.alert('提示信息', '请输入所属层级!');
		$("#layerNo1").focus();
		return false;
	}
	if (jQuery.trim($("#layerNo1").val()) <= 0) {
		$.messager.alert('提示信息', '所属层级必须不小于1！');
		$("#layerNo1").focus();
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
