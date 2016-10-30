<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改企业信息</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
	<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
//实例化编辑器
var editor;
KindEditor.ready(function(K) {
  editor = K.create('textarea[name="companyUrl"]',{
                  height:'25px',
                  width:'25px',
                  items : [],
                  filterMode : false,
                  readonlyMode : true  
  					
  });
});

//保存数据
function saveMessage() {
	var id = '${unit.unitNo}';
	var url = rootPath + "unit/modifyUnit.action";
	var strData = $("#myForm").serialize();
	strData += "&id=" + id;
	AjaxJson(url, true, strData, doDocAdd);
}

function doDocAdd(data) {
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			location.reload();
			closeWin();
		});
	} else {
		$.messager.alert("提示", data.message);
	}
}
//文件上传
function upLoad(){
	 commonOperate('上传数据',rootPath+'systemuser/unit/fileupload.jsp','540','185');
}
function setCompanyUrl(url){
	$("#companyUrl").html(url);
	$.messager.alert("提示信息！","上传成功！");
}
</script>
</head>
<body>
	<div class="easyui-panel" style="width:100%,height:400px;" border=0>
		<form id="myForm" style="display:inline-black;">
			<input type="hidden" id="unitno" name="unti.unitno" value="${unit.unitNo}" />
			<table cellpadding="5" class="list-con"  style="border:solid 1px #ddd;">
				<tr>
					<td class="list-name" style="width:100px;">单位简称：</td>
					<td class="list-info"><input class="easyui-textbox"
						type="text" style="width:200px;height:28px;" id="category"
						name="unit.category" value="${unit.category }" /><!--  <font color="red"
						style="padding-right:0;">*必填</font> -->
					</td>
				</tr>
				<tr>
					<td class="list-name" style="width:100px;">单位全称：</td>
					<td class="list-info"><input class="easyui-textbox"
						type="text" style="width:200px;height:28px;" id="unitName"
						name="unit.unitName" value="${unit.unitName }" /> 
					</td>
				</tr>
				<tr>
					<td class="list-name" style="width:100px;">英文名称：</td>
					<td class="list-info"><input class="easyui-textbox"
						type="text" style="width:200px;height:28px;" id="belongDistrict"
						name="unit.belongDistrict" value="${unit.belongDistrict }" /> 
					</td>
				</tr>
				<tr id="filearea">
					<td class="list-name" style="width:100px;">单位LOGO：</td>
					<td class="list-info">
					<p id="companyUrl" name="unit.companyUrl" disabled="disabled">${unit.companyUrl}</p>
					<%-- <input class="easyui-textbox"
						type="text" style="width:200px;height:28px;" id="companyUrl"
						name="unit.companyUrl" value="${unit.companyUrl}" />  --%>
					</td>
				</tr>
				<tr id="filearea">
					<td class="list-name" style="width:100px;"></td>
					<td class="list-info"><p>注意:可上传PNG格式文件，最大不超过5MB，像素为：162*46</p>
					</td>
				</tr>
				<tr id="filearea">
					<td class="list-name" style="width:100px;"></td>
					<td class="list-info"><input style="width:80px;" type="button" value="上传Logo"
						class="btn-effect" onclick="upLoad()" />
					</td>
				</tr>
		</table>
				<!-- 执行按钮-->
		<div class="easyui-btn" style="border:none;text-align:left;padding-left:324px;">
			<i>
			 	<input type="button" value="提交" class="btn-effect"onclick="saveMessage()" /> 
			 	<input type="button" value="关闭"class="btn-effect" onclick="top.closeSelectedTab()" />
			</i>
		</div>
		</form>
	</div>
	
	<div id="win"></div>
</body>
</html>
