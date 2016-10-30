<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加角色</title>
</head>
<body>
	<script type="text/javascript">
		var rootPath = "${pageContext.request.contextPath}/";

		function saveMessage(roleId) {
			var strData = $("#myForm").serialize();

			var url = "";
			if (jQuery.trim(roleId) == "") {
				url = rootPath + "roleManage/add.action";
			} else {
				strData += "&roleId=" + roleId;
				url = rootPath + "roleManage/modify.action";
			}
			AjaxJson(url, true, strData, doDocAdd);
		}

		function doDocAdd(rtnJson) {
			if (rtnJson.result == "1") {
				$.messager.alert('提示信息', rtnJson.message);
				location.reload();
				closeWin();
			} else {
				$.messager.alert('提示信息', rtnJson.message);
			}
		}
	</script>
	<div id="right">
  <form id="myForm">
	<div class="right">
		<div class="popAdd">
			<ul  class="popInfo">
				<s:if test="#request.parentId==null">
				<li>
					<b>所属应用：</b>
					<i>
				        <s:select id="belongApp" name="belongApp" cssStyle="width:300px;"  cssClass="popText Text02" list="#request.appMap" 
				            value="#request.belongApp" listKey="key" listValue="value"   headerKey="-1" headerValue="--请选择--">
  	     				</s:select>
					</i><div class="clearboth"></div>
				</li>
				</s:if>
				<s:else>
				  <input type="hidden" id="belongApp" name="belongApp" value="${belongApp }"/>
				  <input type="hidden" id="parentId" name="parentId" value="${parentId}" />
				</s:else>
				
				<li>
				    <b>角色名称：</b>
				    <i>
					    <input type="text" class="popText Text02" style="width:300px;" id="roleName" name="roleName" value="${roleName}" />
				    </i>
				    <font color="red"> * 汉字</font> <div class="clearboth"></div>
				</li>
								
				<li>
				    <b>角色类型：</b>
				    <i>
					    <s:select id="roleType" name="roleType" cssStyle="width:300px;"  cssClass="popText Text02" list="#request.roleTypeMap" 
					         value="#request.roleType" listKey="key" listValue="value"  headerKey="-1" headerValue="--请选择--">
   	     				</s:select>
					</i><div class="clearboth"></div>
				</li>				
				
				<li>
				    <b>启用：</b>
				    <i style="padding-top:8px;">
				        <s:if test="#request.enable==0"> 
					        <input type="checkbox" id="enable" name="enable"  value="1" />
				        </s:if>
				        <s:else>
				            <input type="checkbox" id="enable" name="enable" checked="checked" value="1" />
				        </s:else>
				    </i><div class="clearboth"></div>
				</li>
				
				<li>
				    <b>角色描述：</b>
				    <i>
					    <textarea class="popText Text04" style="height:45px;width:300px" id="description" name="description">${description}</textarea>
				    </i>
				    <div class="clearboth"></div>
				</li>																																						
				
			</ul>
			<!-- 执行按钮-->
			<div class="easyui-btn" style="margin-top:40px">
				<i> 
					<input type="button" value="确定" class="btn-effect" onclick="saveMessage('${roleId}');" /> 
					<input type="button" value="关闭" class="btn-effect" onclick="closeWin();" />
				</i>
			</div>				 
		</div>
	</div>
	
	
</form>
</div>
</body>
</html>