<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
<title>查看通知公告</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/auto.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
	//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[id="noticeContent"]',{
  					height:'340px',
  					items : [],
                  filterMode : false,
                  readonlyMode : true
  });
});
</script>
  </head>
  
  <body>
   <div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm">
			<table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name">标题：</td>
					<td class="list-info">
					<input class="easyui-textbox" type="text" style="width:350px;height:28px;" disabled="disabled"  id="noticeTitle" name="noticeTitle" value="${noticeTitle }"/>
					</td>
				</tr>
				<tr>
					<td class="list-name">截止时间：</td>
					<td class="list-info">
						<input class="easyui-textbox" id="deadline" name="deadline" disabled="disabled"  style="width:200px;height:28px;" value="${deadline }"/>
					</td>
				</tr>
				<tr>
					<td class="list-name">内容：</td>
					<td class="list-info" style="width:850px;">
					<textarea id="noticeContent" name="noticeContent" disabled="disabled"  style="width:100%;height:100%;">${noticeContent }</textarea>
					</td>
				</tr>
				<tr>
					<td style="folt:left;"><span style="margin-left:10px;">发布人：</span></td>
					<td ><input  class="easyui-textbox" id="publisherrName" disabled="disabled"  style="width:150px;height:26px" value="${ publisherrName}" />
					<span style="margin-left:10px;float: right;">发布时间：<input class="easyui-textbox" id="publishTime" disabled="disabled"  style="width:150px;height:26px" value="${ publishTime}" /></span>
					</td>
				</tr>
			</table>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()" />
				</i>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
