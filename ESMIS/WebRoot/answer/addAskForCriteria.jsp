<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我要标准</title>
<link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet"/>
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/my.css" type="text/css" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>

<!-- EasyUI -->
<link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/easyui/jquery.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js" type="text/javascript" ></script>

<!-- kindeditor -->
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/auto.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
function add() {
	if (!addData())
		return; 
	var url = "answer/addAskForCriteria.action";
	var submitTitle = $("#submitTitle").val();
	var submitContent = $("#editor").val();
	var submitorEmail = $("#submitorEmail").val();
	var submitorMobile = $("#submitorMobile").val();
	var strData = "submitTitle="+submitTitle+"&submitContent="
				+encodeURIComponent(submitContent)+"&submitorEmail="+submitorEmail+"&submitorMobile="+submitorMobile;
	AjaxJson(rootPath + url, true, strData, message);
}

function addData() {
	if ($("#submitorEmail").val() == "") {
		$.messager.alert('提示信息！', '电子邮件不能为空！');
		$("#submitorEmail").focus(); 
		return false;
	}else{
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test($("#submitorEmail").val())){
			$.messager.alert('提示信息！','您的电子邮件格式不正确');
			return false;
		}
	}
	if($("#submitorMobile").val().trim() != ""){
		var phone = $("#submitorMobile").val();
	    if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){ 
	        $.messager.alert('提示信息！', '手机号码格式有误，请重填'); 
	        $('#submitorMobile').focus(); 
	    	return false; 
    	} 
	}
	if ($("#submitTitle").val() == "") {
		$.messager.alert('提示信息！', '请输入标准编号！');
		$("#submitTitle").focus(); 
		return false;
	}
	if ($("#editor").val().trim() == "") {
		$.messager.alert('提示信息！', '请输入标准名称！');
		$("#editor").focus(); 
		return false;
	}
	return true;
}

function message(data){
	if (data.result == "1") {
		$.messager.alert("信息提示", data.message,'',function(r){
			window.location.reload();
		});
	}else{
		$.messager.alert("信息提示！", data.message);
	}
}

function skip(num){
	if(num == 0){
		window.location.href= rootPath + "answer/addAnswer.jsp";
	}
	if(num == 1){
		window.location.href= rootPath + "answer/addCriteria.jsp";
	}
	if(num == 2){
		window.location.href= rootPath + "answer/addAskForCriteria.jsp";
	}
}

</script>
</head>

<body>
<!--头部开始 -->
    <%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
         <a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
        <i class="icon icon-angle-right"></i>
        <b> 我要标准</b>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="search-table">
<div class="pane-box">
<div class="share-content">
    <div class="share-box">
        <ul class="share-left">
            <!-- <li class="share-title">分享</li> -->
            <li><a href="javascript:void(0);" onclick="skip(1)">分享标准</a></li>
            <li><a href="javascript:void(0);" onclick="skip(2)" class="active-now">我要标准</a></li>
            <li><a href="javascript:void(0);" onclick="skip(0)">意见建议</a></li>
        </ul>
        <ul class="my-right">
            <li>
                <i>邮箱：</i><input type="text" class="sub-sear-text form-control" style="width: 460px;" id="submitorEmail"/>
            </li>
             <li>
                <i>手机：</i><input type="text" class="sub-sear-text form-control" style="width: 460px;" id="submitorMobile"/>
            </li>
            <li>
                <i>标准编号：</i><input type="text" class="sub-sear-text form-control" style="width: 460px;" id="submitTitle"/>
            </li>
            <li>
                <i>标准名称：</i><input type="text" class="sub-sear-text form-control" style="width: 460px;" id="editor"/>
                <button onclick="add()" class="demand">提交</button>
            </li>
        </ul>
    </div>
</div>
</div>
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>
