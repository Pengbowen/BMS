<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增法律法规信息</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript">
</script>
<!-- 国际化定义 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js">
</script>
<script
src="${pageContext.request.contextPath}/lawslibrary/scripts/lawslibrary.js"
	type="text/javascript">
</script>
<script src="${pageContext.request.contextPath}/scripts/commonUpload.js" type="text/javascript"></script>
<script type="text/javascript">
	var filters = "{title:\"文档\",extensions:\"doc,docx,pdf\"}";
	var rootPath = "${pageContext.request.contextPath}/";
	//修改时隐藏文件上传功能
	 $(function() {
	 if($("#lawsTypes").val()==1){
	 	$('#lawsType').combobox('setValue',1);
	 }else{
	 	$('#lawsType').combobox('setValue',2);
	 }
	 $('#lawsType').combobox('select', "${lawsType}");
});  
</script>
</head>
<body>
	<div class="easyui-panel" style="width:100%,height:400px" border=0>
		<form id="myForm">
		<input type="hidden" id="lawsId" name="lawsId" value="${lawsId}"/>
		<input type="hidden" id="lawsTypes" name="lawsTypes" value="${lawsType}"/>
			<table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name"style="width:100px;">法规分类：</td>
					<td class="list-info">
						<input class="easyui-combobox" readonly="readonly" data-options="panelHeight:'auto',valueField:'id',textField:'text',data:[{'id':1,'text':'贯彻执行法规'},{'id':2,'text':'适用执行法规'}] " id="lawsType" name="lawsType" style="width:200px;height:28px;">
						</input>
				   </td>     
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">法规编号：</td>
					<td class="list-info">
					<input class="easyui-textbox"
						type="text" style="width:200px;height:28px;" id="lawsNo"
						name="lawsNo" value="${lawsNo }" /> 
						<font color="red" style="padding-right:0;">*必填</font>
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">法规名称：</td>
					<td class="list-info">
					<input class="easyui-textbox"
						type="text" style="width:400px;height:28px;" id="lawsName"
						name="lawsName" value="${lawsName }" /> 
						<font color="red" style="padding-right:0;">*必填</font>
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">关键字：</td>
					<td class="list-info">
					<input class="easyui-textbox"
						type="text" style="width:400px;height:28px;" id="keyWords"
						name="keyWords" value="${keyWords }" /> 
						<font color="red" style="padding-right:0;">*必填</font>
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">批准单位：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="approvedUnit"
						name="approvedUnit" value="${approvedUnit }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">批准时间：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="approvedDate"
						name="approvedDate" value="${approvedDate }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">实施时间：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="effectiveDate"
						name="effectiveDate" value="${effectiveDate }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">提出部门：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="proposedUnit"
						name="proposedUnit" value="${proposedUnit }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">起草部门：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="draftedUnit"
						name="draftedUnit" value="${draftedUnit }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">主要起草人：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="majorDrafters"
						name="majorDrafters" value="${majorDrafters }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">内容：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="content"
						name="content" value="${content }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">说明：</td>
					<td class="list-info">
					<input class="easyui-textbox" multiline="true"
						type="text" style="width:400px;height:100px;" id="explaintd"
						name="explaintd" value="${explaintd }" />
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">备注：</td>
					<td class="list-info">
					<input class="easyui-textbox"
						type="text" style="width:400px;height:28px;" id="remark"
						name="remark" onkeyup="countNum(this)" value="${remark }" /> 
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;"></td>
					<td class="list-info">
					<input 
						type="hidden" style="width:400px;height:28px;" id="filePath"
						name="filePath"   /> 
				   </td>
				</tr>
			<tr  id="filearea">
				<td class="list-name"style="width:100px;"></td>
				 <td class="list-info">
					<span><i class="icon icon-upload-alt"></i><a onclick="upLoad()" style="width:120px;height:30px; font-size: 20px;">上传文档</a></span>
						<font color="red" style="padding-right:0;">
						注意:可上传PDF,DOC,DOCX格式文件，最大不超过5MB</font>
				   </td> 
				</tr>
			</table>
			</form>
	</div>
	<!-- 执行按钮-->
	<div class="easyui-btn">
		<i> 					
			<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
			<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" /> 	
		</i>
	</div>
<!-- 上传成功后返回结果 -->
<div id="res"></div>
<!-- 点击新增所显示页面 -->
<div id="win"></div>
</body>
</html>
