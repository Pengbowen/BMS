<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>回复页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/auto.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<!-- 遮罩层 -->
<script charset="utf-8" src="${pageContext.request.contextPath}/laypage-v1.3/layer.js"></script>

</head>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";

var type = "${type}";

//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[id="editor"]',{
  					height:'340px'
  });
});

function reply() {
	if (!replyData())
		return;
	var url = "answer/receive.action";
	
	var id = $("#id").val();
	var submitTitle = $("#submitTitle").val();
	var receiveContent = editor.html().replace(new RegExp(/(\n)/g),'');
	var strData = "id="+id+"&submitTitle="+submitTitle+"&receiveContent="+receiveContent;
	AjaxJson(rootPath + url, true, strData, message);
	layer.load(2);
}

function replyData() {
	if (editor.html().trim() == "") {
		$.messager.alert('提示信息！', '请输入回复内容！');
		$("#editor").focus();
		return false;
	}
	return true;
}

function message(data){
	layer.closeAll('loading');
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			refreshParent();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}

function refreshParent(){
	//当前
	var tab = top.$('#tabs').tabs('getSelected');
	var index = top.$('#tabs').tabs('getTabIndex',tab);
	var tabTitle = "";
	var url = "";
	if(type == 0){
		tabTitle = "意见反馈";
		url = rootPath+"answer/skipToAnswer.action?type=0";
	}
	if(type == 1){
		tabTitle = "分享标准";
		url = rootPath+"answer/skipToAnswer.action?type=1";
	}
	if(type == 2){
		tabTitle = "我要标准";
		url = rootPath+"answer/skipToAnswer.action?type=2";
	}
	top.$('#tabs').tabs('close',tabTitle);
	top.tab(tabTitle,url);
	top.$('#tabs').tabs('close',"回复信息");
}

</script>
<body>
<div class="easyui-layout" fit="true">
		<div region="north" border="false"
			style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
			<form id="myForm">
				<div class="query clearfix" style="padding: 18px 18px 18px 10px;height:400px;">
				<input type="text" style="display:none;"  id="id" value="${ id}"/>
				<table>
					<tr>
						<td><span style="margin-left:10px;">回复标题：</span> </td>
						<td><input class="easyui-textbox" id="submitTitle" style="width:250px;height:26px" value="${ submitTitle}"/></td>
					</tr>
					<tr>
						<td><span style="margin-left:10px;">回复内容：</span></td>
						<td style="height:205px;width:700px;"><textarea id="editor" style="width:100%;height:205px;"></textarea></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<input type="button" value="回复" class="btn-effect" onclick="reply()"  style="flot:right;"/>
					<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()"  style="flot:right;"/>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>
