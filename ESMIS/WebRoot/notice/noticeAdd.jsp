<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增友情链接</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/notice/scripts/add.js" type="text/javascript"></script>

<!-- 中文定义 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/auto.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[id="noticeContent"]',{
  					height:'340px'
  });
});
</script>
</head>
<body>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm">
			<input type="hidden" id="id" name="id" value="${id }" />
			<table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name">标题：</td>
					<td class="list-info">
					<input class="easyui-textbox" type="text" style="width:350px;height:28px;" id="noticeTitle" name="noticeTitle" value="${noticeTitle }"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">截止时间：</td>
					<td class="list-info">
						<input class="easyui-datebox" id="deadline" name="deadline" style="width:200px;height:28px;" editable="false" value="${deadline }"/>
						
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">内容：</td>
					<td class="list-info" style="width:850px;">
					<textarea id="noticeContent" name="noticeContent" style="width:100%;height:100%;">${noticeContent }</textarea>
					</td>
				</tr>
			</table>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()" />
				</i>
			</div>
		</form>
	</div>
</div>
</body>
</html>
