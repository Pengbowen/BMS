<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件详情页面</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/netdisk/scripts/operate.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/style/public.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	function DownLoad(file){
		var url = rootPath+file;
		window.location.href=url;
		$.messager.alert("信息提示！","文件已下载","",function(r){
			window.parent.closeWin();
		});
	}
</script>

</head>

<body>
<div id="right">
	<div class="easyui-panel" style="width:100%,height:500px" border=0>
		<div class="detil-detil">
			<form id="myForm">
    	    <table cellpadding="5">
				<ul style="padding-top:20px;">
					<li class="detail01"><span>名称：</span> <em>${objectName}</em></li>
					<li class="detail01"><span>后缀名：</span> <em>${objectSuffix}</em></li>
					<li class="detail01"><span>类别：</span> <em>${objectType=="1"?"文件夹":"文件"}</em></li>
					<li class="detail01"><span>关键词：</span> <em>${keyWords}</em></li>
					<li class="detail01"><span>简介：</span> <em>${explains}</em></li>
					<li id="adress" style="display:none" class="detail01"><span>文件路径：</span> <em>${objectSavePath}</em></li>
					<li id="adress1" class="detail01"><span>下载地址：</span> <em><input onClick="DownLoad('${objectSavePath}');" style="height:25px;width:100px;" title="点击下载" type="button" value="${objectSavePath}" /><br/>
					<font color="red">*点击这里，将下载此文件</font></em></li>
				</ul>
			</table>
			</form>
    	</div>
	</div>
</div>  
</body>
</html>
