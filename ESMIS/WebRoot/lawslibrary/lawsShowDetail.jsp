<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>法律法规详情</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script
src="${pageContext.request.contextPath}/lawslibrary/scripts/lawslibrary.js"
	type="text/javascript">
</script>
<link href="${pageContext.request.contextPath}/style/detail.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
		$(document).ready(function(){
		$(".popuName").each(function(){
			$(this).click(function(){
				var imgs = $(this).find("img");
				if(imgs.attr("src") == "${pageContext.request.contextPath}/images/detailArr02.png"){
					imgs.attr("src","${pageContext.request.contextPath}/images/detailArr01.png");
				}else{
					imgs.attr("src","${pageContext.request.contextPath}/images/detailArr02.png");
				}
				$(this).next().toggle();
			});
		});
		var path = "${filePath}";
		if(path == null || path == ""){
			$("#down").css("background","#CCCCCC");
			$("#down").removeAttr("onclick");
		}
	});
 </script>
</head>
<body>
<div class="popuInfo">
<form id="myForm">
	<div class="popuType">
         <div class="popuName">
            <img src="${pageContext.request.contextPath}/images/detailArr02.png"/><span>基本信息</span>
        </div>
        <div class="popuDetail">
        	<input type="hidden" id="filePath" name="filePath" value="${filePath}"/> 
        	<input type="hidden" id="lawsName" name="lawsName" value="${lawsName}"/> 
			<ul>
			    <li><span>法规编号</span><em>${lawsNo}</em></li>
                <li><span>法规名称</span><em>${lawsName}</em></li>
                <li><span>法规类型</span><em>${lawsType}</em></li>
                <li><span>升级版本号</span><em>${upgradeVersion}</em></li>
                <li><span>数据来源</span><em>${dataSource}</em></li>
                <li><span>浏览量</span><em>${browseVolume}</em></li>
           	</ul>
           	<ul>
           	  	<li><span>关键字</span><em>${keyWords}</em></li>
           	</ul>
           	<ul>
           	   <li><span>备注</span><em>${remark}</em></li>
           	</ul>
           	  <span style="float: left;
    				display: block;
    				width:90px;
    				height:30px;
    				text-align: right;
    				margin-right: 5px;
    				padding-right: 5px;
    				padding-top: 5px;
    				margin-top: 35px;
   					background-color: #efefef;">说明</span>
           	  <input class="easyui-textbox" multiline="true" disabled="disabled"
			   type="text" style="width:500px;height:100px;" id="explains"
			    name="explains" value="${explains }" />
			    <ul>
			    <li><span>批准单位</span><em>${approvedUnit}</em></li>
                <li><span>批准时间</span><em>${approvedDate}</em></li>
                <li><span>实施时间</span><em>${effectiveDate}</em></li>
                <li><span>标准提出部门</span><em>${proposedUnit}</em></li>
                <li><span>标准起草部门</span><em>${draftedUnit}</em></li>
                <li><span>主要起草人</span><em>${majorDrafters}</em></li>
           	</ul>
          </div>
        </div>
        <div class="popuType">
        <div class="popuName">
            <img src="${pageContext.request.contextPath}/images/detailArr02.png"/><span>操作人信息</span>
        </div>
         <div class="popuDetail">
        	<%-- <input type="hidden" id="filePath" name="filePath" value="${filePath}"/> 
        	<input type="hidden" id="lawsName" name="lawsName" value="${lawsName}"/>  --%>
			<ul>
                <li><span>创建人</span><em>${creatorName}</em></li>
                <li><span>创建时间</span><em>${createTime}</em></li>
                <li><span>升级人</span><em>${upgradeUser}</em></li>
                <li><span>升级时间</span><em>${upgradeDate}</em></li>
           	  </ul>
        </div>
      </div>
    <div class="popuType">
    </div>  
    <div class="easyui-btn">
		<i> 	
		    <input type="button" value="下载查看" class="btn-effect" id="down" onclick="downLoad()" /> 	 					
		    <input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" /> 	
		</i>
	</div>
</form> 
 </div> 
<!-- 点击新增所显示页面 -->
<div id="win"></div>        
</body>
</html>
