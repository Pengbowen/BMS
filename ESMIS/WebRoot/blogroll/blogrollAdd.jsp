<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增友情链接</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/blogroll/scripts/add.js" type="text/javascript"></script>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
function upLoad(){
	 commonOperate('上传数据',rootPath+'/blogroll/fileupload.jsp','430','185');
}
function setCompanyUrl(url){
	$("#pictureUrl").textbox("setValue",url);
}
</script>
</head>
<body>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm">
			<input type="hidden" id="id" name="id" value="${id }" />
			<table cellpadding="5" class="list-con">
				<tr>
					<td class="list-name">链接名称：</td>
					<td class="list-info">
					<input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="linkName" name="linkName" value="${linkName }"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">链接地址：</td>
					<td class="list-info">
						<input class="easyui-textbox" id="linkeUrl" name="linkeUrl" style="width:200px;height:28px;" value="${linkeUrl }"/>
						
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">图片地址：</td>
					<td class="list-info">
					<input class="easyui-textbox" data-options="min:0" style="width:200px;height:28px;" disabled="disabled" id="pictureUrl" name="pictureUrl" value="${pictureUrl }" prompt="请上传图片"/>
					<input type="button" style="width:75px;" value="上传图片" class="btn-effect list-info" onclick="upLoad()" />
					</td>
					
					
				</tr>
				<tr>
					<td class="list-name">显示顺序：</td>
					<td class="list-info">
					<input class="easyui-textbox" style="width:200px;height:28px;" id="orderNumber" name="orderNumber" value="${orderNumber }"/>
					</td>
				</tr>
			</table>
			<!-- 执行按钮-->
			<div class="easyui-btn" style="padding: 40px 0px 0px 0px;">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" /> 
				</i>
			</div>
		</form>
	</div>
</div>
<div id="win"></div>
</body>
</html>
