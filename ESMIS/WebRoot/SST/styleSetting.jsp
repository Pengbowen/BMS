<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String name=java.net.URLDecoder.decode(request.getParameter("name"), "UTF-8");
String SSTId=request.getParameter("SSTId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>体系表样式设置</title>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/SST/scripts/styleSetting.js"></script>
<style>
.listName{
	height:30px;line-height:30px;font-weight:bold;background-color:#EFF5FF;	
}
</style>
<script type="text/javascript">
var rootPath = "${pageContext.request.contextPath}/";
window.onload=function(){
	getList(<%=SSTId%>);       
}
</script>
</head>
<body>
	<div style="border:1px solid #797979;width:789px;height:auto;">
			<!--标题 begin-->
			<div style="margin-left:38px;padding: 18px 0 0 0;font-size: 16px;font-weight: bold;"><%=name%></div>
			<!--标题 end-->
			<form method="post">
				<input type="hidden" id="dCount" name="dCount" value="0">
				<div style="padding:5px 10px 10px 36px;" class="list">
					<table class="listInfo" style="width:715px;text-align:center;" border="0" cellSpacing="1" cellPadding="0" height="35">
						<tbody class="listName" id="list">
						</tbody>
					</table>
				</div>
				<div style="text-align:right;margin-top:5px;margin-bottom:15px;margin-right:33px;">
					<hr style="margin-left:30px;border-top: 1px solid #000000;width:725px;"/>
					<input type="button" id="save" onClick="save()" value="保存" style="width:100px;"/>
					<input type="button" value="关闭" onClick="closeWin()" style="width:100px;"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>