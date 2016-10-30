<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增衣物信息</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/dictionarynew/scripts/operate.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	$(function(){
		
		
		var id = "${id }";
		if(id != null && id != ""){
			$("#code").textbox('textbox').attr('readonly',true);
		}else{
			$("input",$("#code").next("span")).blur(function(){
				blurCode();
			});
		}
	});
</script>
</head>

<body>
<div class="easyui-panel" border=0>
		<form id="myForm" >
			<input type="hidden" id="id" name="id" value="${id }" />
			<input type="hidden" id="dictionaryid" name="dictionaryid" value="${dictionaryid }" />
			<table cellpadding="5" class="list-con">
				<br/><br/>
				<tr>
					<td class="list-name">名称：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="content" name="content" value="${content }"/></td>
				</tr>
				<tr id="trCode">
					<td class="list-name">关键值：</td>
					<td class="list-info"><input class="easyui-textbox" style="width:200px;height:28px;" id="code"  name="code" value="${code}"/>
					</td>
				</tr>
				<tr>
					<td class="list-name">显示顺序：</td>
					<td class="list-info"><input class="easyui-textbox" type="number" style="width:200px;height:28px;" id="showNum" name="showNum" value="${showNum }"/>
					</td>
				</tr>
			</table>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" />
				</i>
			</div>
		</form>
</div>
</body>
</html>
