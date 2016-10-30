<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加权限</title>
</head>
<body>
<script type="text/javascript">
var rootPath="${pageContext.request.contextPath}/";

function saveMessage() 
{
	var strData=$("#myForm").serialize();
	var url=rootPath+"permissionManage/modify.action";
	AjaxJson(url, true, strData, doDocAdd);
}

function doDocAdd(rtnJson) {
	if (rtnJson.result == "1") {
		$.messager.alert("信息提示",rtnJson.message);
		location.reload();
	    closeWin();
	} else {
		$.messager.alert("信息提示",rtnJson.message);
	}
}
</script>
<div id="right">
  <form id="myForm">
	<div class="right">
		<div class="popMain">
			<div class="popAdd">
				<ul class="popInfo clearfix">
					<input type="hidden" id="id" name="id" value="${id }"/>
					
					<li class="text-width"><span>父级权限ID：</span><i> 
					    <input type="text" class="popText Text02" style="width:300px;" disabled="disabled" value="${id}" />
					</i></li>
					
					<li class="text-width"><span>权限中文名：</span><i>
						<input type="text" class="popText Text02" style="width:300px;" id="limitName" name="limitName" value="${limitName}" />
						<font color="red" style="padding-right:0;"> * 汉字</font> 
					</i></li>
					
					<li class="text-width"><span>访问URL路径：</span><i>
						<input type="text" class="popText Text02" id="path" name="path" value="${path}" style="width:300px;"/></i>
					</li>
					
					<li class="text-width"><span>权限描述：</span><i>
						<textarea class="popText Text04" style="height:100px;width:300px;" id="description" name="description">${description}</textarea>
					</i></li>																																						
					
					<li class="text-width"><span>显示顺序：</span><i>
						<input type="text" style="width:300px;" class="popText Text02" id="showorder" name="showorder" value="${showorder}" /></i>
					</i></li>
				</ul>
				<!-- 执行按钮-->
				<div class="easyui-btn">
					<i> 
						<input type="button" value="确定" class="btn-effect"  onclick="saveMessage();"  />
			            <input type="button" value="关闭" class="btn-effect" onclick="closeWin();"  /> 		
					</i>
				</div>
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>