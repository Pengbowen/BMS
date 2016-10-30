var rootPath = "${pageContext.request.contextPath}/";
//设置体系表样式
//function setting() {
//	var url = rootPath + "SST/styleSetting.jsp?name="+parent.$('#tabs').tabs('getSelected').panel('options').title;
//	NewData(url);
//}
function NewData(url) {
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
	width : 650,
	height : 650,
	title : '添加标准/法律法规',
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
	// 删除
	var deleteUrl = rootPath + 'sst/deleteStandard.action?standardId='
			+ row.standardId;
	html += '<A onclick="DeleteData(\''
			+ deleteUrl
			+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 上移
	var upUrl = rootPath + "sst/moveUp.action?standardId="
			+ row.standardId+"&displayOrder="+row.displayOrder;
	html += '<A onclick="moveUp('+index+')" href="#" title="上移" class="icon icon-arrow-up"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 下移
	var downUrl = rootPath + "sst/moveDown.action?standardId="
			+ row.standardId+"&displayOrder="+row.displayOrder;
	html += '<A onclick="moveDown('+index+')" href="#" title="下移" class="icon icon-arrow-down"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 置顶
	var upUrl = rootPath + "sst/moveUp.action?standardId="
			+ row.standardId+"&displayOrder="+row.displayOrder;
	html += '<A onclick="setUp('+index+')" href="#" title="置顶" class="icon icon-double-angle-up"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	// 置尾	
	var downUrl = rootPath + "sst/moveDown.action?standardId="
			+ row.standardId+"&displayOrder="+row.displayOrder;
	html += '<A onclick="setDown('+index+')" href="#" title="置尾" class="icon icon-double-angle-down"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}

//保存数据
function saveMessage() {
	if(!verifyData()){
		return;
	}
	var url;
		url = "sst/insertStandard.action";
	strData=strData = $("#myForm").serialize();
									
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
	if (jQuery.trim($("#standardId").val()) == "") {
		$.messager.alert('提示信息', '请输入标准编号！');
		$("#standardId").focus();
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
			+ '\'); " href="javascript:void(0);" title="修改" >'+value+'</A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
//关闭本页面，刷新父页面
function move(url){
	$.ajax({
		url:url,
		type:"get",
		success:function(){
			window.reload();
		},
		error:function(){
			$.messager.alert("移动失败!");
		}
	});
	
	
}
function pop(url,title,width,height) {
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
//文件上传
function upLoad(){
	 commonOperate('上传数据',rootPath+'SST/layeritems/fileupload.jsp','300','130');
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
function moveUp(index){
	if(index-1<0){
		$.messager.alert("提示","不能再上移了!");
		return;
	}
	//获取所有行
	var rows=$('#ViewID').datagrid('getRows');
	var row = rows[index]; 
	//取上一行
	var rowUp = rows[index-1];
	var standardId1 =row.standardId;
	var standardId2 = rowUp.standardId;
	var orderNow = row.displayOrder;
	var orderMove = rowUp.displayOrder;
	var url = rootPath;
	var strData="standardId1="+standardId1+
				"&standardId2="+standardId2+
				"&orderNow="+orderNow+
				"&orderMove="+orderMove;
	url +="sst/move.action";
	AjaxJson(url, true, strData, showMsg);
}
function moveDown(index){
	if(index+2 > recordCount){
		$.messager.alert("提示","不能下移了");
		return;
	}
	
	//获取所有行
	var rows=$('#ViewID').datagrid('getRows');
	var row = rows[index]; 
	//取上一行
	var rowUp = rows[index+1];
	var standardId1 =row.standardId;
	var standardId2 = rowUp.standardId;
	var orderNow = row.displayOrder;
	var orderMove = rowUp.displayOrder;
	var url = rootPath;
	var strData="standardId1="+standardId1+
				"&standardId2="+standardId2+
				"&orderNow="+orderNow+
				"&orderMove="+orderMove;
	url +="sst/move.action";
	AjaxJson(url, true, strData, showMsg);
}
function setUp(index){
	if(index==0){
		$.messager.alert("提示","操作成功");
		return;
	}
	//获取所有行
	var rows=$('#ViewID').datagrid('getRows');
	var row = rows[index]; 
	//取上一行
	var rowUp = rows[0];
	var standardId1 =row.standardId;
	var standardId2 = rowUp.standardId;
	var orderNow = row.displayOrder;
	var orderMove = rowUp.displayOrder;
	var url = rootPath;
	var strData="standardId1="+standardId1+
				"&standardId2="+standardId2+
				"&orderNow="+orderNow+
				"&orderMove="+orderMove;
	url +="sst/move.action";
	AjaxJson(url, true, strData, showMsg);
}
function setDown(index){
	var count = recordCount-1;
	if(index==count){
		$.messager.alert("提示","操作成功");
		return;
	}
	//获取所有行
	var rows=$('#ViewID').datagrid('getRows');
	var row = rows[index]; 
	//取上一行
	var rowUp = rows[count];
	var standardId1 =row.standardId;
	var standardId2 = rowUp.standardId;
	var orderNow = row.displayOrder;
	var orderMove = rowUp.displayOrder;
	var url = rootPath;
	var strData="standardId1="+standardId1+
				"&standardId2="+standardId2+
				"&orderNow="+orderNow+
				"&orderMove="+orderMove;
	url +="sst/move.action";
	AjaxJson(url, true, strData, showMsg);
}
function showMsg(data){
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			//刷新父级窗口
			window.reload();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}