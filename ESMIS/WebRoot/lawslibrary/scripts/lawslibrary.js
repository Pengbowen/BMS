// 保存数据
function saveMessage() {
	if (!verifyData())
		return;
	var url;
	if ($("#lawsId").val() == "") {
		var dataSource=1;
		url = "lawslibrary/addLawsByMananl.action?dataSource="+dataSource;
	}else{
		url = "lawslibrary/modifyLaws.action";
	}
	strData = $("#myForm").serialize();
	AjaxJson(rootPath + url, true, strData, doDocAddByIframe);
}
//关闭本页面，刷新父页面
function resetParent(){
	//关闭本窗口
	window.parent.closeWin();
	//刷新父级窗口
	window.parent.refreshSelTab();
}
//Iframe框架下的回调函数刷新页面
function doDocAddByIframe(data) {
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			resetParent();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}
//类型
function showType(value, row, index){
	var html = "";
	if (value == "1") {
		html = '<font color="blue">企业贯彻法规</font>';
	}
	if (value == "2") {
		html = '<font color="red">企业适用法规</font>';
	}
	return html;
}
//验证数据
function verifyData() {
	if (jQuery.trim($("#lawsNo").val()) == "") {
		$.messager.alert('提示信息', '请输入法规编号！');
		$("#lawsNo").focus();
		return false;
	}
	if (jQuery.trim($("#lawsName").val()) == "") {
		$.messager.alert('提示信息', '请输入法规名称！');
		$("#lawsName").focus();
		return false;
	}
	if (jQuery.trim($("#keyWords").val()) == "") {
		$.messager.alert('提示信息', '请输入关键字！');
		$("#keyWords").focus();
		return false;
	}
	return true;
}
//文件上传
function upLoad(){
	 commonOperate('上传数据',rootPath+'lawslibrary/fileupload.jsp','540','185');
}
//文件下载
function downLoad()
{
	 var filePath=jQuery.trim($("#filePath").val());
	 var lawsName=jQuery.trim($("#lawsName").val());
	 if(lawsName==null||lawsName=="")
	 {
		 $.messager.alert('提示信息', '文件名称为空！');
		 return false;
	 }
	 if(filePath==null||filePath=="")
	 {
		 $.messager.alert('提示信息', '文件路径为空！');
		 return false;
	 }
	 var Url = 'lawslibrary/downLoad.action?filePath='+filePath+'&fileName='+encodeURI(encodeURI(lawsName));
	 window.location.href =Url;
}
//操作列内容
function formateOperateCell(value, row, index) {
	var html = "";
	//修改法律法规信息
	var modifyUrl = rootPath + 'lawslibrary/operatorLaws.action?lawsId='+row.standardId;
	html += '<A onclick="commonOperate(\'修改法律法规\',\'' + modifyUrl
	+ '\',\'600\',\'450\')" href="javascript:void(0);"title="信息修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//作废
	var cancellationUrl = rootPath + 'lawslibrary/cancellation.action?lawsId='+ row.standardId;
	html += '<A onclick="DeleteData(\''+ cancellationUrl+ '\',\'确认要作废吗?\')" href="javascript:void(0);" title="作废" class="icon-remove-sign"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//删除
	var deleteUrl = rootPath + 'lawslibrary/deleteLaws.action?lawsId='+ row.standardId;
	html += '<A onclick="DeleteData(\''+ deleteUrl+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
}
//查看法律法规详情
function detailView(value, row, index) {
	var html = "";
	var detailUrl = rootPath + 'lawslibrary/showLawsDetail.action?lawsId='+row.standardId;
	html += '<A onclick="commonOperate(\'法律法规详情\',\'' + detailUrl
		+ '\',\'750\',\'600\')" href="javascript:void(0);">';
	html += row.standardName + '</A>';
	return html;
}
//获取上传文件路径
function getPath(path)
{
	$("#filePath").val(path);
	$.messager.alert("提示信息！","上传成功！");
}
//框架新增方法
function NewData() {
	var lawsType=$("#standardCategory").val();
	var url = rootPath+'lawslibrary/operatorLaws.action?lawsType='+lawsType;
	commonOperate("新增法律法规",url,"600","500");
}