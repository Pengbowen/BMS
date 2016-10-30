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
	var url=rootPath+"permissionManage/add.action";
	AjaxJson(url, true, strData, doDocAdd);
}

function doDocAdd(rtnJson) {
	if (rtnJson.result == "1") {
		alert(rtnJson.message);
		location.reload();
	    closeWin();
	} else {
		alert(rtnJson.message);
	}
}

</script>
<div id="right">
  <form id="myForm">
	<div class="right">
		<div class="popMain">
			<div class="popAdd">
				<ul class="popInfo clearfix">
					<s:if test="#request.parentId==null">
					<li class="text-width"><span>所属应用：</span><i>
						  <s:select id="belongApp" name="belongApp" cssStyle="width:300px;" 
						            cssClass="popText Text02" list="#request.limitApp" 
						            value="#request.belongApp" listKey="key" listValue="value"  
						            headerKey="-1" headerValue="--请选择--">
    	     				</s:select>
						</i>
					</li>
					</s:if>
					<s:else>
					  <input type="hidden" id="parentId" name="parentId" value="${parentId }"/>
					  <input type="hidden" id="belongApp" name="belongApp" value="${belongApp }"/>
					 <li class="text-width"><span>父级权限ID：</span><i> 
					    <input type="text" class="popText Text02" style="width:300px;" disabled="disabled" value="${parentId}" />
					  </i></li>
					</s:else>
					
					<li class="text-width"><span>权限中文名：</span><i>
						<input type="text" class="popText Text02" style="width:300px;" id="limitName" name="limitName" value="${limitName}" />
						<font color="red" style="padding-right:0;"> * 汉字</font> 
					</i></li>
					
					<li class="text-width"><span>权限英文名：</span><i>
						<input type="text" class="popText Text02" style="width:300px;" id="limitEnglishName" name="limitEnglishName" value="${limitEnglishName}" />
						<font color="red" style="padding-right:0;">*英文不包含其他特殊字符</font> 
					</i></li>
					
					<li class="text-width"><span>是叶子节点：</span><i> 
						<input type="checkbox" id="leaf" name="leaf" value="1" style="margin-top:8px;" />
						<font color="red" style="padding-right:0;line-height:30px;">*如果不是父节点，需要选中该项</font>
					</li>		
										
					<li class="text-width"><span>访问URL路径：</span><i>
						<input type="text" class="popText Text02" id="path" name="path" value="${path}" style="width:300px;"/></i>
					</li>
					
					<li class="text-width"><span>URL比较规则：</span><i>
						  <select id="targetcompare" name="targetcompare" class="popText Text02" style="width:300px;">
						     <option value="1">仅比较路径（不包含参数）</option>
						     <option value="2">除了值以外都比较（路径、参数名）</option>
						     <option value="0">完全比较（路径、参数名、参数值完全相同）</option>
						  </select>
						</i>
					</li>
					
					<li class="text-width"><span>权限描述：</span><i>
						<textarea class="popText Text04" style="height:100px;width:300px;" id="description" name="description">${description}</textarea>
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