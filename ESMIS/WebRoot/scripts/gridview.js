//做导出时，设置总页数与总条数的默认值
var recordCount=0;
var pageCount=0;
//页面初始化工作*
function loadingInfo() {
	loadGridViewData();
}
// 显示序列号 1.2.3.....
function showClassNo(value, row, index) {
	return (currentPage - 1) * linesOfPage + index + 1;
}

//系统权限中序号方法重写
var i =0;
function formateSerialNo1(value, row, index) {
	i = i+1;
	return i;
}
//刷新选中的页签
function refreshSelTab()
{
	//调用tabformat中的刷新方法
	window.parent.refreshSelTab();
}	
//设置gridview的宽度和高度，根据window自适应
function resizeGridview()
{
	$('#'+gridviewId).datagrid('resize', {
        height: $(window).height() - 125
    }).datagrid('resize', {
        height: $(window).height() - 125
    });				
}

// 加载GridView数据
function loadGridViewData() {
	var opts = $('#' + gridviewId).datagrid("options");
	opts.loadMsg = "正在加载数据，请稍后...";
	$('#' + gridviewId).datagrid("loading");
	var url = LoadDataUrl;
	url += (url.indexOf("?") > 0) ? "&" : "?";
	url += "currentPage=" + currentPage;
	url += "&linesOfPage=" + linesOfPage;
	AjaxJson(url, true, getParameter(), fillDataList, true, false);
}
// 给GridView填充数据
function fillDataList(json) {
	var datalist = {};
	datalist["total"] = json.recordCount;
	datalist["rows"] = json.datalist;
	//记录总页数
	pageCount=json.pageCount;
	//记录总条数
	recordCount=json.recordCount;
	$('#' + gridviewId).datagrid("loadData", datalist);
	$(pager).pagination({
		total : json.recordCount
	});
	$('#' + gridviewId).datagrid("loaded");
}
// 生成GridView数据请求页面的paramerter
function getParameter() {
	var json = "";
	var param = "rand=" + Math.random();
	if (document.getElementById("orderString")) {
		json = $("#orderString").val().replace(" ", "");
		if (json != "")
			param += "&orderString=" + json;
	}
	param += "&conditionString=" + compareConditionByQuery();
	return param;
}

// 执行页面中的查询操作
function searchByCondition() {
	currentPage = 1;
	loadGridViewData();
}

//新框架统一删除操作 
//2016-07-29 修改方法，增加回调函数，WYL
function DeleteData(url, MsgTitle) {
	$.messager.confirm("信息提示！", MsgTitle, function(ok) {
		if (ok) {
			AjaxJson(url, true, "", doReturnDatatoStatus);
		}
	});
}
//执行删除的回调函数
function doReturnDatatoStatus(rtnJson){
	if(rtnJson.result == "1"){
		$.messager.alert("信息提示！",rtnJson.message,"",function(r){
			window.location.reload();
			}
		);
	}else{
		$.messager.alert("信息提示！",rtnJson.message);
	}
}
//删除后执行的刷新方法：
function reload(){
	window.location.reload();
}
//新增，修改，查看详情弹窗方法
function commonOperate(title,url,width,height){
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;"></iframe>';			
	$('#win').window({
		width : width,
		height : height,
		title : title,
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
		content : content,
	});
	// 窗口居中。
	$('#win').center;			
}

// 保存操作回调
function doDocAdd(data) {
	if (data.result == "1") {
		$.messager.alert('提示信息', data.message);
		loadGridViewData();
		closeWin();
	} else {
		$.messager.alert('提示信息', data.message);
		return;
	}
}
// 关闭页面
function closeWin() {
	$("#win").window('close', true);
}