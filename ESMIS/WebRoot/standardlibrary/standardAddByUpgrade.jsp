<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>升级标准</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
	<!-- 国际化定义 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js">
</script>
	<link href="${pageContext.request.contextPath}/style/detail.css"
	rel="stylesheet" type="text/css" />
<script
src="${pageContext.request.contextPath}/standalibrary/scripts/standardlibrary.js"
	type="text/javascript">
</script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
</script>
</head>
<body>
	<div class="easyui-panel" style="width:100%,height:400px" border=0>
 <form id="myForm">
	<div class="popuType">
		<div class="popuName">
           <span>基本信息</span>
        </div>
			<table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name"style="width:100px;">标准类型：</td>
					<td class="list-info">
					<input class="easyui-combobox" prompt="请选择标准类型" inQuery="true" panelHeight="auto" value="${standardCategory }"
						id="standardCategory" name="standardCategory"  editable="false" style="width:200px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
					</input>
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">标准编号：</td>
					<td class="list-info">
					<input class="easyui-textbox" readonly="readonly"
						type="text" style="width:200px;height:28px;" id="oldStandardNo"
						name="oldStandardNo" value="${oldStandardNo }" /> 
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">当前版本号：</td>
					<td class="list-info">
					<input class="easyui-textbox" disabled="disabled"
						type="text" style="width:200px;height:28px;" id="upgradeVersion"
						name="upgradeVersion" value="${upgradeVersion }" /> 
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">标准名称：</td>
					<td class="list-info">
					<input class="easyui-textbox" readonly="readonly"
						type="text" style="width:400px;height:28px;" id="standardName"
						name="standardName" value="${standardName }" /> 
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">主题词：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:400px;height:28px;" id="subjectWords"
						name="subjectWords" value="${subjectWords }" />
					<font color="red" style="padding-right:0;">*必填</font> 
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
						name="remark" value="${remark }" /> 
				   </td>
				</tr>
			</table>
			<div class="popuName">
             <span>升级信息</span>
           </div>
           <table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name"style="width:100px;">新标准编号：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:200px;height:28px;" id="standardNo"
						name="standardNo"  /> 
					<font color="red" style="padding-right:0;">*必填</font>
				   </td>
				</tr>
				<tr>
					<td class="list-name"style="width:100px;">升级版本号：</td>
					<td class="list-info">
					<input class="easyui-textbox" 
						type="text" style="width:200px;height:28px;" id="upgradeVersion"
						name="upgradeVersion"  /> 
					<font color="red" style="padding-right:0;">*必填, 如2016.3或者2016</font>
				   </td>
				</tr>
				<tr >
				<td class="list-name"style="width:100px;"></td>
				 <td class="list-info">
					<span><i class="icon icon-upload-alt"></i><a onclick="upLoad()" style="width:120px;height:30px;">上传文档</a></span>
						<font color="red" style="padding-right:0;">
						注意:可上传PDF,DOC,DOCX格式文件，最大不超过5MB</font>
				   </td> 
				</tr>
			</table>
		</div>
		</form>
	</div>
	<!-- 执行按钮-->
	<div class="easyui-btn">
		<i> 					
			<input type="button" value="提交" class="btn-effect" onclick="saveUpgrade()" />
			<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" /> 	
		</i>
	</div>
	<!-- 点击新增所显示页面 -->
	<div id="win"></div>
</body>
</html>
