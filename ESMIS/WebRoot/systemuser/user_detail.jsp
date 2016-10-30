<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改认证用户信息</title>
</head>
<body>
<div id="right">
	<div class="easyui-panel" style="width:100%,height:500px" border=0>
		<div class="detil-detil">
			<form id="myForm">
    	    <table cellpadding="5">
					<ul style="padding-top:20px;">
						<li class="detail01"><span>所属部门：</span> <em>${untiName}</em></li>
						<li class="detail01"><span>角色名称：</span> <em>${roleName}</em></li>
						<li class="detail01"><span>人员姓名：</span> <em>${user.realName}</em></li>
						<li class="detail01"><span>人员性别：</span> <em id="sex">${user.sexInfo=="1"?"男":"女"}</em></li>
						
						<li class="detail01"><span>登录名：</span> <em>${user.loginName}</em></li>
						<li class="detail01"><span>账户状态：</span> <em>${user.loginLimit=="true"?"可用":"不可用"}</em></li>
					</ul>
					<!-- 执行按钮-->
<!-- 					<div class="easyui-btn">
						<i> <input type="button" value="关闭" class="btn-effect" onclick="closeWin()" /> </i>
					</div> -->
			</table>
			</form>
		
    </div>
</div>
</div>   
</body>
</html>
