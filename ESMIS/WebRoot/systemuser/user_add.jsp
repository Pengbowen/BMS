<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改用户信息</title>
</head>
<body>
<script type="text/javascript">
	$(function(){
	if ($("#password1").val() =="") 
	{
		$("#passwordarea1").css('display','');
		$("#passwordarea2").css('display','');
		$("#passwordarea3").css('display','');
	}
	if ($("#password1").val() !="") 
	{
		$("#passwordarea1").css('display','none');
		$("#passwordarea2").css('display','none');
		$("#passwordarea3").css('display','none');
	}
	
	//onBeforeLoad
	$('#unitno').combotree({
		onSelect: function(node) {
		//console.log($("#unitno").combotree("getValue"));
			// 判断是否是叶子节点
			var isLeaf = $(this).tree('isLeaf', node.target);
			if (!isLeaf) {
				$.messager.alert("信息提示！","请选择叶子节点！");
				// 返回false表示取消本次选择操作
				return false;
			}
		}
	});
});
</script>
	<div id="right">
		<div class="right" style="margin:0;">
			<form id="myForm">
				<div class="popMain">
					<div class="popAdd">

						<input type="hidden" id="id" name="id" value="${user.userId }" />

						<ul class="popInfo clearfix">
							<li><span>所属部门：</span><i> 
									<%-- <s:select
										cssClass="popText Text03" cssStyle="width:300px;"
										name="user.unitNo" id="unitno" list="#request.unitMap"
										value="#request.user.unitNo" listKey="key" listValue="value"
										headerKey="-1" headerValue="--请选择--">
									</s:select>  --%>
									
									<s:select id="unitno" list="#request.unitMap" name="user.unitNo" class="easyui-combotree"
									 listKey="key" listValue="value"
									 style="width:300px;" 
									data-options="url:'../unit/loadUnitMap.action',required:true">
									</s:select>
							</i>
							</li>

							<li><span>角色名称：</span><i> <s:select
										cssClass="popText Text03" cssStyle="width:300px;"
										list="#request.hashData" listKey="key" listValue="value"
										id="roleId1" name="user.roleId" value="#request.user.roleId"
										headerKey="-1" headerValue="--请选择--">
									</s:select> </i>
							</li>

							<li><span>人员姓名：</span><i> <input type="text"
									class="popText Text02" style="width:300px;" id="realname1"
									name="user.realName" value="${user.realName }" /> <font
									color="red" style="padding-right:0;">*必填</font> </i>
							</li>

							<li><span>人员性别：</span><i> <s:radio id="sexinfo1"
										name="user.sexInfo" list="#{'0':'女','1':'男'}"
										value="#{user.sexInfo}" /> </i>
							</li>

							<li><span>登录名：</span><i> <input type="text"
									class="popText Text02" style="width:300px;" id="loginname1"
									name="user.loginName" value="${user.loginName }" /> <font
									color="red" style="padding-right:0;">*必填</font> </i>
							</li>
							<li id="passwordarea3" style="display:none"><span>&nbsp;</span><i> <font color="red">提示：密码必须为纯数字，长度为3~8位</font>
							</i>
							</li>
							<li id="passwordarea1" style="display:none"><span>密码：</span><i> <input type="password"
									class="popText Text02" style="width:300px;" id="password1"
									name="user.password" onchange="newPassword()" value="${user.password}" /> <font
									color="red" style="padding-right:0;">*必填</font> </i>
							</li>

							<li id="passwordarea2" style="display:none"><span>确认密码：</span><i> <input type="password"
									class="popText Text02" style="width:300px;" id="newPwd1"
									name="newPwd" value="${user.password}" /> <font color="red" style="padding-right:0;">*必填</font>
							</i>
							</li>
						</ul>
						<!-- 执行按钮-->
						<div class="easyui-btn">
							<i> <input type="button" value="保存" class="btn-effect"
										onclick="saveMessage();" /> <input type="button" value="关闭"
										class="btn-effect" onclick="closeWin()" /> </i>
						</div>
						
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
