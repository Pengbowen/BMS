<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>项目编辑</title>
<style type="text/css">
/*样式重置*/
html{font-family:"微软雅黑",Arial,sans-serif}
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,form,fieldset,input,button,textarea,p,th,td{padding:0;margin:0;font-family:Microsoft YaHei,sans-serif,Arial}
table{border-collapse:collapse;border-spacing:0}
fieldset,img{border:0}
a{text-decoration:none;color:#000;outline:none;}
li{list-style:none}
i{font-style: normal}
caption,th{text-align:left}
h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal}
input,button,textarea,select,optgroup,option{font-family:inherit;font-size:inherit;font-style:inherit;font-weight:inherit}
input,button,textarea,select{*font-size:100%}
a{text-decoration: none;cursor: pointer;}
.clearfix{clear: both;}

/*内容样式*/
body{
    background: #333;
}
.process{
    width:500px;
    height:700px;
    position: fixed;
    left: 50%;
    top:50%;
    margin-left:-250px;
    margin-top:-250px;
    background-color: #f8f8f8;
    border-radius:10px;
    -moz-border-radius:10px;
    -webkit-border-radius:10px;
}
.process .process-title{
    border-top-left-radius:10px;
    -moz-border-top-left-radius:10px;
    -webkit-border-top-left-radius:10px;
    border-top-right-radius:10px;
    -moz-border-top-right-radius:10px;
    -webkit-border-top-right-radius:10px;
    background-color: #50a7ef;
    color:#ffffff;
    font-size:16px;
    line-height: 36px;
    padding-left:20px;
    margin-bottom:30px;
}

.process .process-menu span{
    display:inline-block;
    float: left;
}
.process .process-menu ul{
    float: left;
    padding:10px 16px 8px 16px;
    border-bottom: dashed 1px #666666;
}
.process .process-menu li{
    text-align: center;
}
.process .process-menu .title{
    font-size:24px;
    color:#333333;
}
.process .process-menu .title i{
    font-size:14px;
    color:#666666;
}
.process .process-menu .text-1{
    font-size:24px;
    color:#50a7ef;
}
.process .process-menu .text-2{
    font-size:24px;
    color:#e88e19;
}
.process .process-menu .text-3{
    font-size:24px;
    color:#f65e4e;
}
.process .process-menu{
    display:block;
    height:85px;
    padding-left:135px;
    margin-bottom:50px;
    position: relative;
}
.process .process-menu .h-pline{
    height:50px;
    width:1px;
    padding: 0;
    background:#999999;
    position: absolute;
    left:175px;
    bottom:-48px;
}



</style>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/layeritems/scripts/operate.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
 var rootPath = "${pageContext.request.contextPath}/"
 function downloadItem(){
 $.ajax({
 	url:rootPath+"sst/download.action",
 	success:function(){
 	$.messager.alert("下载成功");
 	},
 	error:function(){
 	$.messager.alert("下载失败");
 	}
 });
 }
 function upload(){
   commonOperate('上传数据',rootPath+'SST/standard/fileupload.jsp','540','185');
 }
  </script>
<%-- <div>
	<table>
	<div>
	<iframe src="${pageContext.request.contextPath}/SST/standard/fileupload.jsp" style="margin-top:30px;margin-left:150px" width="500" height="128" allowTransparency="true"> 
	</iframe> 
	</div>
	<tr>
		<td style="padding-left:310px;">
		<span>&nbsp;</span>
		</td>
	
	</tr>
	<tr>
		<td style="padding-left:310px;">
		<span>&nbsp;</span>
		</td>
	
	</tr>
	<tr>
		<td style="padding-left:310px;">
			<a href="/ESMIS/sst/downloadStandard.action"><i class="icon icon-cloud-download" href="">点击下载模板</i></a>
		
		</td>
	
	</tr>
	</table>
</div> --%>
<div class="process">
        <div class="process-title">操作流程</div>
        <div class="process-menu">
            <span>
                <img src="${pageContext.request.contextPath}/image/download.png"/>
            </span>
            <ul>
                <li class="title">01<i>step</i></li>
              <a href="/ESMIS/sst/downloadStandard.action"> <li class="text-1">点击下载数据</li></a>
            </ul>
            <ul class="h-pline"></ul>
        </div>
        <div class="process-menu">
            <span><img src="${pageContext.request.contextPath}/image/message.png"/></span>
            <ul>
                <li class="title">02<i>step</i></li>
                <li class="text-2">数据调整</li>
            </ul>
            <ul class="h-pline"></ul>
        </div>
        <div class="process-menu">
            <span><img src="${pageContext.request.contextPath}/image/upload.png"/></span>
            <ul>
                <li class="title">03<i>step</i></li>
                <a onclick="upload();"><li class="text-3" >点击上传数据</li></a>
            </ul>
        </div>
</div>
<div id="win">
</div>
</body>
</html>
