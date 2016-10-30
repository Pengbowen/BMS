<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改企业方针目标</title>
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

function update() {
	if (!updateData())
		return;
	var url = "simplecontent/simplecontentToUpdate.action";
	
	var content = editor.html().replace(new RegExp(/(\n)/g),'');
	var strData = "content="+content;
	AjaxJson(rootPath + url, true, strData, message);
}

function updateData() {
	if (editor.html().trim() == "") {
		$.messager.alert('提示信息！', '内容不能为空！');
		return false;
	}
	return true;
}

function message(data){
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			top.closeSelectedTab();
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
				<div class="query clearfix" style="padding: 18px 18px 18px 10px;height:400px;">
				<table>
					<tr>
						<td><span style="margin-left:10px;">标题：</span> </td>
						<td><input class="easyui-textbox" id="contenttitle" style="width:250px;height:26px" disabled="disabled" value="${ contenttitle}"/></td>
					</tr>
					<tr>
						<td><span style="margin-left:10px;">内容：</span></td>
						<td style="height:205px;width:700px;"><textarea id="editor" style="width:100%;height:205px;">${ content}</textarea></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<input type="button" value="保存" class="btn-effect" onclick="update()"  style="flot:right;"/>
					<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()"  style="flot:right;"/>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>
