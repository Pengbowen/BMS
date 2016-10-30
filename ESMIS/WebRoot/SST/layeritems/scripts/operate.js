var rootPath = "${pageContext.request.contextPath}/";
//设置体系表样式
//function setting() {
//	var url = rootPath + "SST/styleSetting.jsp?name="+parent.$('#tabs').tabs('getSelected').panel('options').title;
//	NewData(url);
//}
function NewData(url,str) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
	width : 420,
	height : 360,
	title : str,
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
function formateOperateCell(value, row, index) {
	var html = "";
	// 修改
	var modifyUrl = rootPath + 'sst/modifyItem.action?layerItemId='
			+ row.layerItemId;
	html += '<A onclick="NewData(\''
			+ modifyUrl
			+ '\','+'\'修改\''+'); " href="javascript:void(0);" title="修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 删除
	var deleteUrl = rootPath + 'sst/deleteLayerItem.action?layerItemId='
			+ row.layerItemId+"&belongItemId="+row.belongItemId;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 设置子分类信息
	var setUrl = rootPath + "sst/toSubClassifyManage.action?SSTId="
			+ row.SSTId+"&layerItemId="+row.layerItemId;
	html += '<A onclick=newTab("子分类管理",\''+setUrl+'\') href="#" class="icon icon-cogs"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	
	if(SSTType=="1"){
		//增加
		var addSubItemUrl = rootPath + 'sst/addSubLayerItem.action?layerId='
		+ row.layerItemId+'&SSTId='+row.SSTId+'&SSTType='+SSTType;
		html += '<A onclick="NewData(\''
		+ addSubItemUrl
		+ '\','+'\'添加子项目\''+'); " href="javascript:void(0);" title="添加子项目" class="icon-plus-sign"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	}
	
	//查看标准
	var standardUrl = rootPath + 'sst/toStandardList.action?layerItemId='
	+ row.layerItemId+'&SSTId='+row.SSTId;
	html += '<A onclick=newTab("标准管理",\''+standardUrl+'\') href="#" class="icon icon-file-text-o"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
function newTab(title,url){
	window.parent.tab(title,url);
}
//保存数据
function saveMessage() {
	
	if(!verifyData()){
		return;
	}
	var layerItemId = "";
	layerItemId = $("#layerItemId").val();
	var url;
	
	
	if ($("#layerItemId").val() == "") {
		url = "sst/insertLayerItem.action";
	} else {
		url = "sst/modifyLayerItem.action";
	}
	
	
	strData = "layerItemId="+$("#layerItemId").val();
	strData +="&layerNo1="+$("#layerNo1").val();
	strData +="&displayOrder="+$("#displayOrder").val();
	strData +="&isEnabled="+$("#enableState").val();
	strData +="&isVisible="+$("#visibleState").val();
	strData +="&SSTId="+$("#SSTId").val();
	strData +="&layerItemName1="+$("#layerItemName1").val();
	strData +="&layerItemNo="+$("#layerItemNo").val();
	alert(SSTType);
	if(SSTType==1){
		var bleongItemId=$("#select").combobox('getValue');
		
		strData +="&belongItemId="+bleongItemId;
	}							
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
	if (jQuery.trim($("#layerItemNo").val()) == "") {
		$.messager.alert('提示信息', '请输入项目编号!');
		$("#layerItemNo").focus();
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
	
	
	return true;
}
function formatName(value, row, index){
	var html = "";
	// 修改
	var modifyUrl = rootPath + 'sst/modifyItem.action?layerItemId='
			+ row.layerItemId;
	html += '<A onclick="NewData(\''
			+ modifyUrl
			+ '\','+'\'修改\''+'); " href="javascript:void(0);" title="修改" >'+value+'</A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
//文件上传
function upLoad(){
	 commonOperate('上传数据',rootPath+'SST/layeritems/fileupload.jsp','540','185');
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
//判断子分类数量是否为0

