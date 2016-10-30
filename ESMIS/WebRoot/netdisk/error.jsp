<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>下载失败</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>

<script type="text/javascript">
	var error = '${errorMessage}';
	var rootPath = "${pageContext.request.contextPath}/";
	$(function() {
		$.messager.alert("信息提示!",error,"error",function(r){
			//window.history.back();return false;
			window.location.href = rootPath+"netdisk/reception_list.jsp";
		}).panel({onClose:function(){
			window.location.href = rootPath+"netdisk/reception_list.jsp";
			//window.history.back();return false;
		}});
	})
</script>
</head>
<body>
</body>
</html>
