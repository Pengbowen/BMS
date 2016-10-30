<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta>
<title>法律法规详情</title>
<link  href="../SST/face/css/sub-style2.css" type="text/css" rel="stylesheet" />
<link  href="../SST/face/css/zui.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	var lawsType = "${lawsType}";
	var lawsId = "${lawsId}";
	var dataSource = "${dataSource}";
	$(function() {
		if(lawsType==1){
			$("#lawsType").html("企业贯彻执行的法律法规");
		}
		if(lawsType==2){
			$("#lawsType").html("企业适用的法律法规");
		}
		if(dataSource == 1){
			$("#seediv").html('<iframe src="${pageContext.request.contextPath}/standardlibrary/testHtml.action?standardId='+lawsId+'" frameborder="0" width="960px" height="1200px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>');
		}
		if(dataSource == 9){
			$("#seediv").html('<embed style="width:1000px; height:1200px;" src="${pageContext.request.contextPath}/standardlibrary/test.action" bgcolor="#000000" name="simplevideostreaming" align="middle" type="application/pdf" />');
		}
	});
				

</script>
</head>

<body onload="change('sstHome')">
<!--头部开始 -->
  <%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="sub-banner-newwrap sub-banner-new">
	<div class="sub-posit">
    	<div class="sub-posit-cont">
        	<a class="posit-a" href="${pageContext.request.contextPath}">首页</a> <i class="icon icon-angle-right"></i><a class="posit-a" href="${pageContext.request.contextPath}/lawslibrary/toLaws_fit.action?style=${lawsType}" id="lawsType">法律法规详情</a> <i class="icon icon-angle-right"></i> <b> ${noticeTitle }</b>
        </div>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="sub-contnew">
	<div class="nlist-detil-title">
    	<span><i class="icon icon-time"></i>${ createTime}</span><span><i class="icon icon-eye-open"></i>${ browseVolume}</span>
        ${title }
    </div>
    <div class="new-detil" id="seediv">
    <embed id="a2" style="width:1000px; height:1200px;" src="${pageContext.request.contextPath}/standardlibrary/test.action" bgcolor="#000000" name="simplevideostreaming" align="middle" type="application/pdf" />
    </div>
   	<!-- <iframe src="${pageContext.request.contextPath}/standardlibrary/testHtml.action?standardId=1111111" frameborder="0" width="960px" height="1200px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe> -->
   	
   	<!-- <div class="new-detil-xg"><span>下一个：<a href="javascript:vido(0);" class="xg-play">金数据全网整合营销 </a></span>上一个：<i class="xg-display">开门红商城基础营销 </i></div> -->
    
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>
