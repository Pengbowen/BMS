var rootPath = "${pageContext.request.contextPath}/";
// 保存数据
function saveMessage() {
	if (!verifyData())
		return;
	var url;
	if ($("#standardId").val() == "") {
		var dataSource=1;
		url = "standardlibrary/addStandardByMananl.action?dataSource="+dataSource;
	}else{
		//修改时隐藏文件上传
		
		url = "standardlibrary/modifyStandard.action";
	}
	strData = $("#myForm").serialize();
	AjaxJson(rootPath + url, true, strData, doDocAddByIframe);
}

//升级数据保存
function saveUpgrade()
{
	if (!verifyUpgradeData())
		return;
	var url;
	var dataSource=0;
	url = "lawslibrary/addLawsByUpgrade.action?dataSource="+dataSource;
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
		$.messager.alert("信息提示！", data.message,'',function (r){
					top.closeSelectedTab();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}
//类型
function showDataSource(value, row, index){
	var html = "";
	if (value == "1") {
		html = '<font color="blue">手工入库</font>';
	}
	if (value == "0") {
		html = '<font color="red">升级入库</font>';
	}
	return html;
}
function showDataSource1(value, row, index){
	var html = "";
	if (value == "1") {
		html = '手工入库';
	}
	if (value == "0") {
		html = '升级入库';
	}
	return html;
}
//有效状态
function showState(value, row, index){
	var html = "";
	if (value == "1") {
		html = '<font color="blue">有效</font>';
	}
	if (value == "0") {
		html = '<font color="red">作废</font>';
	}
	return html;
}
function showState1(value, row, index){
	var html = "";
	if (value == "1") {
		html = '有效';
	}
	if (value == "0") {
		html = '作废';
	}
	return html;
}
//升级数据验证
function verifyUpgradeData()
{
	if (jQuery.trim($("#keyWords").val()) == "") {
		$.messager.alert('提示信息', '请输入关键字！');
		$("#keyWords").focus();
		return false;
	}
	if (jQuery.trim($("#lawsNo").val()) == "") {
		$.messager.alert('提示信息', '请输入新法规编号！');
		$("#lawsNo").focus();
		return false;
	}
	if (jQuery.trim($("#lawsName").val()) == "") {
		$.messager.alert('提示信息', '请输入法规名称！');
		$("#lawsName").focus();
		return false;
	}
	if (jQuery.trim($("#upgradeVersion").val()) == "") {
		$.messager.alert('提示信息', '请输入升级版本号！');
		$("#upgradeVersion").focus();
		return false;
	}
	return true;
}
//验证数据
function verifyData() {
	
	if (jQuery.trim($("#standardNo").val()) == "") {
		$.messager.alert('提示信息', '请输入标准编号！');
		$("#standardNo").focus();
		return false;
	}
	if (jQuery.trim($("#standardCategory").combobox('getValue')) == "") {
		$.messager.alert('提示信息', '请选择标准类别！');
		$("#standardCategory").focus();
		return false;
	}
	if (jQuery.trim($("#standardName").val()) == "") {
		$.messager.alert('提示信息', '请输入标准名称！');
		$("#standardName").focus();
		return false;
	}
	if (jQuery.trim($("#proposedUnit").val()) == "") {
		$.messager.alert('提示信息', '请输入提出部门！');
		$("#proposedUnit").focus();
		return false;
	}
	if (jQuery.trim($("#approvedUnit").val()) == "") {
		$.messager.alert('提示信息', '请输入批准部门！');
		$("#approvedUnit").focus();
		return false;
	}
	if (jQuery.trim($("#approvedDate").datebox('getValue')) == "") {
		$.messager.alert('提示信息', '请输入批准时间！');
		$("#approvedDate").focus();
		return false;
	}
	if (jQuery.trim($("#effectiveDate").datebox('getValue')) == "") {
		$.messager.alert('提示信息', '请输入实施时间！');
		$("#effectiveDate").focus();
		return false;
	}
	return true;
}
//文件上传
function upLoad(){
	commonOperate('上传数据',rootPath+'standardlibrary/fileupload.jsp','540','185');
	}
//文件下载
function downLoad()
{
	 var filePath=jQuery.trim($("#filePath").val());
	 console.log(filePath);
	 var standardName=jQuery.trim($("#standardName").val());
	 if(standardName==null||standardName=="")
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
	//修改标准信息
	var modifyUrl = rootPath + 'standardlibrary/operatorStandard.action?standardId='+row.standardId;
	html += '<A onclick="top.tab(\'修改标准\',\'' + modifyUrl	+ '\')" href="javascript:void(0);"title="信息修改" class="icon icon-pencil"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	//删除
	var deleteUrl = rootPath + 'standardlibrary/deleteStandard.action?standardNo='+ row.standardNo+'&standardId='+row.standardId;
	html += '<A onclick="DeleteData(\''+ deleteUrl+ '\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></A>&nbsp;&nbsp;&nbsp;&nbsp;';
	return html;
	
}
//查看标准详情
function detailView(value, row, index) {
	var html = "";
	var detailUrl = rootPath + 'standardlibrary/showStandardDetail.action?standardId='+row.standardId;
	html += '<A onclick="detailShow(\'' + detailUrl + '\')" href="javascript:void(0);">';
	html += value + '</A>';
	return html;
}
//获取上传文件路径
function getPath(path)
{
	$("#filePath").val(path);
}
//查询标准总库详情
function detailShow(detailUrl) {
	parent.showDialogPage("标准总库详情",detailUrl,"800","600");
}
//框架新增方法
function updateStandard(modifyUrl) {
	parent.showDialogPage("修改标准",modifyUrl,"1080","400");
}

/**
 * 根据iframe对象获取iframe的window对象
 * @param frame
 * @returns {Boolean}
 */
function GetFrameWindow(frame){
	return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}
//加载法律法规数据
function loadData1(json){
 	currentPage = json.currentPage;
    pageCount = json.pageCount;
 	recordCount = json.recordCount;
 	var linesOfPage=5;
 	var datalist = json.datalist;
 	var element = ''; 

 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardId = data.standardId;//法规Id
	 	var standardNo = data.standardNo;//法规文号
	 	var standardCategory = data.standardCategory;//法规类别
	 	var standardName = data.standardName;//法规名称
	 	var browseVolume = data.browseVolume;//浏览量
	 	var effectiveDate=data.effectiveDate;//实施时间
	 	var oldStandardNo = data.oldStandardNo;//替代标准编号
	 	var effectiveState=data.effectiveState;//法规状态
	 	var documentType=data.documentType;//文档类型
	 	var approvedDate=data.approvedDate;//发布时间
	 	//alert(approvedDate);
	 	var key =data.key;//key
	 	var urlStr =data.str;//str
	 	var standard =null;
		var flog=isHaveThisOne(standardId);
		if(effectiveState=="0"){
			element += ' <div class="search-list law-delete">';
			element += '<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>';
		}else{
			element += '<div class="search-list law">';
		}
		element += ' <span></span>';
 		element += ' <ul class="text">';
 		element += '<li class="title" style="width:939px;"><a target="_blank" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'"><i>'+standardNo+'</i><i>'+standardName+'</i></a>';
		element += '  <a class="download" href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url='+urlStr+'&key='+key+'"><i class="icon icon-download"></i><i>下载</i></a>';
		if(flog==true){
			element += '<i id="shoucang'+standardId+'" ><a onclick="removeCookie('+standardId+')"  style="float:left;text-align:left;width:90px;color:#ea8010;"><i class="icon icon-star " style="color:#ea8010;"></i><i  style="color:#ea8010;">已收藏</i></a></i>';
		}else{
			element += '<i id="shoucang'+standardId+'" ><a class="shoucang" onclick="addCollect('+standardId+')" ><i class="icon icon-star-empty"></i><i>收藏</i></a></i>';
		}		
		element += ' <i class="liulan"><i class="icon icon-eye-open"></i><i>'+browseVolume+'</i></i>';
		element += '</li>';
		element += ' <li class="text-other">';
		element += '<i class="fabu-time">发布时间：<em>'+approvedDate+'</em></i>';
		element += '<i class="shishi-time">实施时间：<em>'+effectiveDate+'</em></i>';
		element += '</li>';
		element += '</ul>';
		element += '<div class="clearfix"></div>';
		element += '</div>';
 	}
 	$("#lawsdata").html("");
 	$("#lawsdata").html(element);
 }
function loadFenye1(data){
	$("#start1").empty();
			$("#start1").jqPaginator({
            totalPages:pageCount,//总页数
            visiblePages:10,//设置最多显示页码
            currentPage: currentPage,//当前页码
            activeClass:'selected',
            pageSize:5,
            first: '<li class="first shangyiye" ><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev shangyiye"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
       	    next: '<li class="next shangyiye"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>下一页<\/a><\/li>',
            last: '<li class="last shangyiye"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (n) {
            	loadPage1(n,data);            	
            }
        });
		}	
function loadPage1(pageIndex,data){
	var pageUrl= lawsDataUrl+"&currentPage="+pageIndex;
	 AjaxJson(pageUrl,true,data,loadData1,false,false);
	}
function getParameter1(){
	var standardNo1 = $("#standardNo1").val();
	var standardName1 = $("#standardName1").val();
	var documentType1 = $("#documentType1").val();
	var standardCategory1 = $("#standardCategory1").val();
	//alert(standardNo1+":"+standardName1+":"+documentType1+":"+standardCategory1);
	var param = "rand=" + Math.random();
		param += "&standardNo="+standardNo1;
		param += "&standardName="+standardName1;
		param += "&documentType="+documentType1;
		param += "&standardCategory="+standardCategory1;
		//alert(param);
	return param; 	
	}