<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lanyuan.web.LoginAuthentication.LoginUser"%>
<%
LoginUser user = (LoginUser)request.getSession().getAttribute(LoginUser.SESSIONID);
String userName = "";
if(user!=null){
	userName = user.getLoginName();
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="${pageContext.request.contextPath}/style/public.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/popup.css" rel="stylesheet" type="text/css" />
  
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<!--  显示时间 -->
<script src="${pageContext.request.contextPath}/scripts/login.js" type="text/javascript" language="javascript"></script>

<script type="text/javascript">
  var data = {};
  var userName = "<%=userName%>";
  var loginflag = false;
  //提交数据
  function saveMessage()
  {		
	  if(!verifyData())return false;
	  if(loginflag)
	  {
	  	var strData = "userName=" + userName + "&Oldpassword=" + data.Oldpassword + "&Newpassword=" + data.Newpassword;
	  	url = "${pageContext.request.contextPath}/login/changepwd.action";
	  }
	  else
	  {
		var strData = "Oldpassword=" + data.Oldpassword + "&Newpassword=" + data.Newpassword;
		strData += "&Conpassword=" + data.Conpassword + "&ran=" + Math.random();
		url="${pageContext.request.contextPath}/login/modifyPwd.action";
	  }
		AjaxJson(url, true, strData, doDocAdd);
  }
  
  //验证数据
  function verifyData()
  {
	if(jQuery.trim($("#Oldpassword").val()) == ""){
		alert("请输入原密码");
		$("#Oldpassword").focus();
		return false;
	}
	data.Oldpassword = encodeURIComponent(jQuery.trim($("#Oldpassword").val()));
	if(jQuery.trim($("#Newpassword").val()) == ""){
		alert("请输入新密码");
		$("#Newpassword").focus();
		return false;
	}
	data.Newpassword = encodeURIComponent(jQuery.trim($("#Newpassword").val()));
	if(jQuery.trim($("#Conpassword").val()) == ""){
		alert("请输入确认密码");
		$("#Conpassword").focus();
		return false;
	}
	data.Conpassword= encodeURIComponent($("#Conpassword").val());
	if(jQuery.trim($("#Newpassword").val()) != jQuery.trim($("#Conpassword").val())){
		alert("两次输入的密码不一样");
		$("#Newpassword").focus();
		return false;
	}
	return true;
  }

  function doDocAdd(rtnJson){
	if(rtnJson.result == "1"){
		alert(rtnJson.message);
		closeWin();
	}else{
		alert(rtnJson.message);
	}
  }
  
	 //关闭页面
    function closeWin() {
    	parent.$('#win').window('close');
    }
  </script>
</head>

<body>
<div class="popMain"  style="width: 390px">
	<div class="popIco"></div>
    <div class="popAdd">
    	<ul class="popInfo clearfix">
        	<li>
        	<span style="width:20%;">原密码：</span>
        	<i>
        	<input type="password" class="popText Text02" id="Oldpassword" name="oldpassword" />
        	</i>
        </li>
		<li>
			<span style="width:20%;" style="width:20%;">新密码：</span>
			<i>
				<input type="password" class="popText Text02" id="Newpassword" name="newpassword" />
			</i>
		</li>
	    <li>
	    	<span style="width:20%;">确认密码：</span>
	    	<i>
	    	<input type="password" class="popText Text02" id="Conpassword" name="conpassword" />
	    	</i>
	    </li>
         </ul>
     <div class="popDone">
            <div style="margin-left: 40%;">
            	<input type="button" value="确定" class="popBtn" onclick="saveMessage()"/>
            	<input type="button" value="取消" class="popBtn" onclick="closeWin()"/>
            </div>
        </div>
    </div>
</div>    
</body>
</html>
