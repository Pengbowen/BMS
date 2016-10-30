<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>标准详情</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script
src="${pageContext.request.contextPath}/standardlibrary/scripts/standardlibrary.js"
	type="text/javascript">
</script>
<link href="${pageContext.request.contextPath}/style/detail.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	$(function(){
	var filepath=$("#filePath").val();
	if(filepath!=null&&""!=filepath){
		$("#filedown").css('display','');
	}
	});
 </script>
</head>
<body>
<div class="popuInfo">
<form id="myForm">
	<div class="popuType">
        <div class="popuName">
           <span>基本信息</span>
        </div>
        <div class="popuDetail">
        	<input type="hidden" id="filePath" name="filePath" value="${filePath}"/> 
        	<input type="hidden" id="standardName" name="standardName" value="${standardName}"/> 
			<ul>
			    <li><span>标准编号</span><em>${standardNo}</em></li>
                <li><span>标准类别</span><em>${standardCategory}</em></li>
                <li><span>标准名称<br />（中文）</span><em>${standardName}</em></li>
                <li><span>标准名称<br />（英文）</span><em>${standardNameEN}</em></li>
                <li><span>替代标准</span><em>${oldStandardNo}</em></li>
                <li><span>主题词</span><em>${subjectWords}</em></li>
                <li><span>采标程度</span><em>${adoptionDegree}</em></li>
                <li><span>采用国际标准</span><em>${adoptIS}</em></li>
                <li><span>分类号</span><em>${standardClassNo}</em></li>
                <li><span>门类号</span><em>${standardKindNo}</em></li>
                <li><span>批准部门</span><em>${approvedUnit}</em></li>
                <li><span>批准时间</span><em>${approvedDate}</em></li>
                <li><span>标准提出部门</span><em>${proposedUnit}</em></li>
                <li><span>实施时间</span><em>${effectiveDate}</em></li>
                <li><span>标准起草部门</span><em>${draftedUnit}</em></li>
                <li><span>主要起草人</span><em>${majorDrafters}</em></li>
                <li><span>所属专业</span><em>${applicableMajor}</em></li>
                <li><span>标准分享人</span><em>${sharePeople}</em></li>
                <li><span>标准有效状态</span><em>${effectiveState}</em></li>
                <li><span>数据来源</span><em>${dataSource}</em></li>
                <li><span>浏览量</span><em>${browseVolume}</em></li>
                <li><span>文件存放位置</span><em>${filePath}</em></li>
                <li><span>最后修改人姓名</span><em>${modifyerName}</em></li>
                <li><span>备注</span><em>${remark}</em></li>
           </ul>
        
        </div>
    </div>
    <div class="popuType">
    </div>  
    <div class="easyui-btn">
		<i> 
			<input id="filedown" type="button" value="下载查看" class="btn-effect" onclick="downLoad()" style="display: none " /> 	 						
			<input type="button" value="关闭" class="btn-effect" onclick="resetParent()" />
		</i>
	</div>
	</form>  
</div>         
</body>
</html>
