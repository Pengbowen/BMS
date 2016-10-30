
<%@page import="org.apache.poi.ss.formula.functions.Count"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lanyuan.web.LoginAuthentication.LoginUser"%>
<%@page import="com.lanyuan.assembly.view.WebSite"%>
<%@page import="com.lanyuan.actionapi.ConstApplication"%>
<%
    String basePath = WebSite.getWebSiteUrl(request);
	String path = request.getContextPath();
			/* 	LoginUser user = (LoginUser) request.getSession().getAttribute(
			 LoginUser.SESSIONID);
			 //获取当前登陆人信息
			 String userName = user.getRealName();
			 String userId = user.getUserId();
			 Set<String> limitSet = user
			 .getAppPermissions(ConstApplication.ApplicationName);
			 boolean role = limitSet.contains("RAP.CommonFunction.SystemAdvice"); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[name="editor"]',{
                  height:'340px',
                  items : [],
                  filterMode : false,
                  readonlyMode : true  
  					
  });
});


var isreceive = '${ detail.isreceive}';
$(function(){
	if(isreceive == 0){
		$("#receive").css("display","none");
	}
});
</script> 
</head>
<!--  页面主题部分-->
<body style="overflow-y: auto;">
<div  fit="true">
	<div region="north" border="false"
		style="border-bottom:0px solid #ddd;min-height:28px;padding:10px 10px 1px 10px;background:#fafafa;">
		<div class="query clearfix" style="padding: 18px 18px 18px 10px;">
			<form id="myForm">
				<table cellpadding="5">
				<tr>
					<td class="list-name">标题：</td>
					<td><input class="easyui-textbox" id="submitTitle" style="width:250px;height:26px;" disabled="disabled" value="${ detail.submitTitle}"/></td>
				</tr>
				<tr>
					<td class="list-name">内容：</td>
					<td style="height:350px;width:700px;"><textarea id="submitContent" disabled="disabled" name="editor" style="width:100%;height:205px;background:#FFF;">${ detail.submitContent}</textarea></td>
				</tr>
				<tr>
					<td style="folt:left;" class="list-name">电子邮件：</td>
					<td><input class="easyui-textbox" id="submitorEmail" style="width:150px;height:26px" disabled="disabled" value="${ detail.submitorEmail}" />
					<span style="margin-left:10px;float: right;">手机号：<input class="easyui-textbox" id="submitorMobile" style="width:150px;height:26px" disabled="disabled" value="${ detail.submitorMobile}" /></span>
					</td>
				</tr>
				<tr>
					<td style="folt:left;" class="list-name">提交时间：</td>
					<td><input class="easyui-textbox" id="submitTime" style="width:150px;height:26px" disabled="disabled" value="${ detail.submitTime}" />
					<span style="margin-left:10px;float:right;">提交人：<input class="easyui-textbox" id="submitName" style="width:150px;height:26px" disabled="disabled" value="${ detail.submitName}" /></span>
					</td>
				</tr>
			</table>
			<div class="query clearfix" style="padding: 18px 18px 18px 10px;border:0px;"></div>
			<table id="receive">
				<tr>
					<td class="list-name">回复标题：</td>
					<td><input class="easyui-textbox" id="submitTitle" style="width:250px;height:26px" value="${ detail.submitTitle}" /></td>
				</tr>
				<tr>
					<td class="list-name">回复内容：</td>
					<td style="height:350px;width:700px;"><textarea id="receiveContent" disabled="disabled" name="editor" style="width:100%;height:205px;background:#FFF;">${ detail.receiveContent}</textarea></td>
				</tr>
				<tr>
					<td style="folt:left;" class="list-name">回复时间：</td>
					<td><input class="easyui-textbox" id="receiveTime" style="width:150px;height:26px" value="${ detail.receiveTime}" />
					<span style="margin-left:10px;float:right;">回复人：<input class="easyui-textbox" id="receiveName" style="width:150px;height:26px" value="${ detail.receiveName}" /></span>
					</td>
				</tr>
			</table>
			<div style="text-align: center;">
				<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()" />
			</div>
			</form>
		</div>
	</div >
</div>

</body>
</html>