<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>项目编辑</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/layeritems/scripts/operate.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
 var rootPath = "${pageContext.request.contextPath}/"
 function downloadItem(){
 $.ajax({
 	url:rootPath+"sst/download.action",
 	success:function(){
 	$.messager.alert("下载成功");
 	},
 	error:function(){
 	$.messager.alert("下载失败");
 	}
 });
 
 
 }
  </script>
<div>
	<table>
	
	<tr>
				 <td class="list-info">
					<h1><span style="padding-left:310px;"><i class="icon icon-upload-alt"></i><a onclick="upLoad()" style="width:120px;height:30px;">上传文档</a></span></h1>
				   </td> 
	</tr>
	<tr>
		<td>
		<font color="red" style="padding-left:230px;">
						注意:可上传xls,xlsx格式文件，最大不超过5MB</font>
		</td>
	</tr>
	<tr>
		<td style="padding-left:310px;">
		<span>&nbsp;</span>
		</td>
	
	</tr>
	<tr>
		<td style="padding-left:310px;">
		<span>&nbsp;</span>
		</td>
	
	</tr>
	<tr>
		<td style="padding-left:310px;">
			<a href="/ESMIS/sst/downloadSubClass.action"><i class="icon icon-cloud-download" href="">点击下载模板</i></a>
		
		</td>
	
	</tr>
				
				
	</table>
<!-- 点击新增所显示页面 -->
	<div id="win"></div>
</div>
</body>
</html>
