<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>提交页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/auto.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
</head>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[id="editor"]',{
  					height:'340px'
  });
});
var submitType = '${ submitType}';
function add() {
	/* if (!addData())
		return; */
	var url = "";
	if(submitType == ""){
		$.messager.alert("提示信息！","类型为空！");
		return;
	}
	if(submitType == 0){
		url = "answer/addAnswer.action";
	}
	if(submitType == 1){
		url = "answer/addCriteria.action";
	}
	if(submitType == 2){
		url = "answer/addAskForCriteria.action";
	}
	if(url == ""){
		$.messager.alert("提示信息！","服务器地址与类型未匹配上！");
		return;
	}
	var submitTitle = $("#submitTitle").val();
	var submitContent = editor.html();
	submitContent = editor.html().replace(new RegExp(/(\n)/g),'');
	var submitorEmail = $("#submitorEmail").val();
	var submitorMobile = $("#submitorMobile").val();
	var strData = "submitTitle="+submitTitle+"&submitContent="
				+submitContent+"&submitorEmail="+submitorEmail+"&submitorMobile="+submitorMobile;
	AjaxJson(rootPath + url, true, strData, message);
}

function addData() {
	if ($("#submitTitle").val() == "") {
		$.messager.alert('提示信息！', '请输入标题！');
		$("#submitTitle").textbox('textbox').focus(); 
		return false;
	}
	if (editor.html().trim() == "") {
		$.messager.alert('提示信息！', '请输入回复内容！');
		$("#editor").textbox('textbox').focus(); 
		return false;
	}
	if ($("#submitorEmail").val() == "") {
		$.messager.alert('提示信息！', '电子邮件不能为空！');
		$("#submitorEmail").textbox('textbox').focus(); 
		return false;
	}else{
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test($("#submitorEmail").val())){
			$.messager.alert('提示信息！','您的电子邮件格式不正确');
			return false;
		}
	}
	if($("#submitorMobile").val().trim() != ""){
		var phone = $("#submitorMobile").val();
	    if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){ 
	        $.messager.alert('提示信息！', '手机号码格式有误，请重填'); 
	        $('#submitorMobile').textbox('textbox').focus(); 
	    	return false; 
    	} 
	}
	return true;
}

function message(data){
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			top.refreshAppointTabs(rootPath+"answer/skipToAnswer.action","意见反馈");
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}
</script>
<body>
<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<form id="myForm">
				<div class="query clearfix" style="padding: 18px 18px 18px 10px;height:100%;">
				<table>
					<tr>
						<td style="folt:left;"><span style="margin-left:10px;">电子邮件：</span></td>
						<td style="folt:left;"><input class="easyui-textbox" id="submitorEmail" style="width:150px;height:26px" /><font color="red" style="padding-right:0;">*必填</font></td>
					</tr>
					<tr>
						<td style="folt:right;"><span style="margin-left:10px;">手机号：</span></td>
						<td style="folt:right;"><input class="easyui-textbox" id="submitorMobile" style="width:150px;height:26px"  /></td>
					</tr>
					<tr>
						<td><span style="margin-left:10px;">标题：</span> </td>
						<td><input class="easyui-textbox" id="submitTitle" style="width:400px;height:26px" /></td>
					</tr>
					<tr>
						<td><span style="margin-left:10px;">内容：</span></td>
						<td style="height:205px;width:700px;"><textarea id="editor" style="width:100%;height:205px;"></textarea></td>
					</tr>
					
				</table>
				<div style="text-align: center;">
					<input type="button" value="提交" class="btn-effect" onclick="add()"  style="flot:right;"/>
					<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()"  style="flot:right;"/>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>
