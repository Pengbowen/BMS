<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>工作标准体系表</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/scripts/operate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/scripts/work-standard.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/style/add-style.css" rel="stylesheet" />
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
</script>
<style>
a{
	color:#0574c6;
    text-decoration:none;
}
a:hover {
	color:#0574c6;
	cursor: pointer;
    text-decoration:none;
}
</style>
</head>
<body>
	<input type="hidden" id="manage" value="${manage}"/>
	<div class="add-standard-box" id="add-standard-box" style="position:relative;display:block;">
    	<div class="add-title">工作标准体系表</div>
    </div>
    <!--bnt开始 -->
    <div style="position:absolute;left:50%;margin-left:-160px;display:none;" id="standard-bnt">
        <form>
            <input type="button" class="standard-bnt standard-bnt1" onclick="setItems(3)" value="设置层项目信息"/>
            <input type="button" onClick="setting(3)" class="standard-bnt standard-bnt2" value="设置体系表样式"/>
            <!-- 
            	<input type="button" class="standard-bnt standard-bnt1" value="发布" style="margin-left:20px;background-color:#CCCCCC;"/>
        	-->
        </form>
    </div>
    <!-- 设置体系表样式 -->
	<div id="win"></div>
</body>
</html>