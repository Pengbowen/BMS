<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>标准详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.DL{
    width:35px;
    height: 42px;
    display: inline-block;
    background: url("${pageContext.request.contextPath}/image/DL.png") no-repeat center;
    float: left;
    color:white;
    text-align: center;
    line-height:35px;
    font-size:20px;
}
	</style>
	 <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    
	<script type="text/javascript">
		var rootPath="${pageContext.request.contextPath}/";
		var replaceUrl = rootPath+"/standardlibrary/searchReplaceStandard.action?standardNo=GB/T 218-1996";
		var seriesStandard = rootPath+"/standardlibrary/searchSeriesStandard.action?standardNo=GB/T 218-1996";
		var searchSSTStandardUrl = rootPath+"/standardlibrary/searchSSTStandard.action?standardNo=GB/T 218-1996";
		$(function(){
	 	AjaxJson(replaceUrl,true,null,loadData,false,false);
		AjaxJson(seriesStandard,true,null,loadData1,false,false);
		AjaxJson(searchSSTStandardUrl,true,null,loadData2,false,false);
		//判断是否已经收藏
		isHaveThisOne();
		});
		function loadData(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardNo = data.standardNo;//标准编号
	 	var standardName = data.standardName;//标准名称
	 	var standardCategory = data.standardCategory;
 		 element +=      '<ul class="replace-content">'+
               			 '<li>'+
                    	 '<a href="javascript:vido(0);" style="display: inline-block;">';
        if(standardCategory=="GB"){
        element+='<span class="GB-1">GB</span>';
        }else if(standardCategory=="JB"){
        element+='<span class="Q">JB</span>';
        }else if(standardCategory=="SD"){
         element+='<span class="DL">SD</span>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
        element+='<span class="DL">'+standardCategory+'</span>';
        }else{
         element+='<span class="DL">'+standardCategory+'</span>';
        }
                 element+=       ' <ul style="float: left;">';
            	 element+=	     ' <li class="list-title" onclick="checkDetail()">';
                 element+=       ' <em>'+data.standardNo+'</em>';
              	 element+=       ' <em>'+data.standardName+'</em>';
              	 element+=       ' </li>';
                 element+=       ' </ul>';
                 element+= 		 ' </a>';
                 element+= 		 ' </li>';
           		 element+=		 ' </ul> ';               			
	 	}
	 	$("#replace").append(element);
	 	}
	 	
		function loadData1(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var count = json.recordCount;
	 	if(count<1){
	 	$("#xilie").append('<div class="none"></div>');
	 	}
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardNo = data.standardNo;//标准编号
	 	var standardName = data.standardName;//标准名称
	 	var standardCategory = data.standardCategory;
 		 element +=      '<ul class="replace-content">'+
               			 '<li>'+
                    	 '<a href="javascript:vido(0);" style="display: inline-block;">';
        if(standardCategory=="GB"){
        element+='<span class="GB-1">GB</span>';
        }else if(standardCategory=="JB"){
        element+='<span class="Q">JB</span>';
        }else if(standardCategory=="SD"){
         element+='<span class="DL">SD</span>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
        element+='<span class="DL">'+standardCategory+'</span>';
        }else{
         element+='<span class="DL">'+standardCategory+'</span>';
        }
                 element+=       ' <ul style="float: left;">';
            	 element+=	     ' <li class="list-title" onclick="checkDetail()">';
                 element+=       ' <em>'+data.standardNo+'</em>';
              	 element+=       ' <em>'+data.standardName+'</em>';
              	 element+=       ' </li>';
                 element+=       ' </ul>';
                 element+= 		 ' </a>';
                 element+= 		 ' </li>';
           		 element+=		 ' </ul> ';               			
	 	}
	 	$("#xilie").append(element);
	 	}
	 		function loadData2(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var count = json.recordCount;
	 	if(count<1){
	 	$("#xilie").append('<div class="none"></div>');
	 	}
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardNo = data.standardNo;//标准编号
	 	var standardName = data.standardName;//标准名称
	 	var standardCategory = data.standardCategory;
 		 element +=      '<ul class="replace-content">'+
               			 '<li>'+
                    	 '<a href="javascript:vido(0);" style="display: inline-block;">';
              			
        if(standardCategory=="GB"){
        element+='<span class="GB-1">GB</span>';
        }else if(standardCategory=="JB"){
        element+='<span class="Q">JB</span>';
        }else if(standardCategory=="SD"){
         element+='<span class="DL">SD</span>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
        element+='<span class="DL">'+standardCategory+'</span>';
        }else{
         element+='<span class="DL">'+standardCategory+'</span>';
        }
                 element+=       ' <ul style="float: left;">';
            	 element+=	     ' <li class="list-title">';
                 element+=       ' <em>'+data.standardNo+'</em>';
              	 element+=       ' <em>'+data.standardName+'</em>';
              	 element+=       ' </li>';
                 element+=       ' </ul>';
                 element+= 		 ' </a>';
                 element+= 		 ' </li>';
           		 element+=		 ' </ul> ';               			
	 	}
	 	$("#sst").append(element);
	 	}
	 	function checkDetail(){
	 	window.open("${pageContext.request.contextPath}/standardlibrary/toStandardDetail1.action?standardNo =GB/T 218-1996");
	 	}
	 	
	 	
	 	var c_name = "collect";
	 	
		var id = "110000125";
		//收藏
		function addCollect(){
    		var cookieValue = getCookie();
    		if(cookieValue != ""){
    			cookieValue += ","+id;
    		}else{
    			cookieValue = ","+id;
    		}
    		var d = new Date();
			d.setTime(d.getTime() + '1');
		/* 	alert('collect=' + cookieValue + ';expires='+d.toUTCString()+';path=' + rootPath); */
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang").html('<i class="icon icon-star"></i><a onclick="removeCookie()">已收藏</a>');
    	}
		//取消收藏
		function removeCookie(){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang").html('<i class="icon icon-star-empty"></i><a onclick="addCollect()">收藏</a>');
		}
		
		//检测是否有cookie
		function getCookie()
		{
		if (document.cookie.length>0)
		  {
		  c_start=document.cookie.indexOf(c_name + "=");
		  if (c_start!=-1)
		    { 
		    c_start=c_start + c_name.length+1 ;
		    c_end=document.cookie.indexOf(";",c_start);
		    if (c_end==-1) c_end=document.cookie.length;
		    return unescape(document.cookie.substring(c_start,c_end));
		    } 
		  }
		return "";
		}
		
		//判断是否收藏过当前标准； 是 true，不是false;
		function isHaveThisOne(){
			var value = getCookie();
			var isHava;
			if(value != ""){
				isHava = value.indexOf(id);
				if(isHava != -1){
			$("#shoucang").html('<i class="icon icon-star"></i><a onclick="removeCookie()">已收藏</a>');
				return true;
			}
			}
			
			return false;
		}
	 	//更多
	 	function loadMore(){
	 	var SSTId  = $("#sstid").val();
			window.open("http://localhost:8080/ESMIS/standardDetail/toStandardList.action?SSTId="+SSTId);
	 	}
	 	//下载
	 	function dl	(){
	 	var standardId = $("#standardId").val();
	 	window.location.href=rootPath+"standardlibrary/downloadOne.action?standardId="+standardId;
	 	}
	 	
	</script>
  </head>
  
  <body onload="change('sstHome')">
<!--头部开始 -->
<div class="top">
  <%@ include file="../../header.jsp" %>
</div>
<!--头部结束 -->
<!--banner开始 -->
<div class="sub-banner-newwrap sub-banner-04">
    <div class="sub-posit">
        <div class="sub-posit-cont">
            <b style="font-size:12px;color:#fff;">当前位置：</b>
            <a class="posit-a" href="javascript:vido(0);">首页</a>
            <i class="icon icon-angle-right" style="color:#fff;"></i>
            <a class="posit-a" href="javascript:vido(0);" style="color: #fff;">${title}</a>
        </div>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="sub-contnew" style="background-color:#fafafa;">
    <ul class="sub-title">
         <li class="sub-title-left"><i>${standardNo }</i> <i>${title}</i><em style="  margin-left:18px;">分享人：</em><em>${shareBy }</em></li>
        <li class="sub-title-right" id="box"><em>体系表位置：</em> <em>${sstLocation }</em>
            <i class="icon icon-download-alt" ></i><a  href="${pageContext.request.contextPath}/standardlibrary/downloadOne.action?standardId=110000006">下载</a>
          	<em id="shoucang"><i class="icon icon-star-empty"></i><a onclick="addCollect()">收藏</a></em>                  
        </li>
    </ul>
    <embed id="a2" style="width:1000px; height:1200px;" src="${pageContext.request.contextPath}/standardlibrary/test.action" bgcolor="#000000" name="simplevideostreaming" align="middle" type="application/pdf" />
<%--   	 <iframe src="${pageContext.request.contextPath}/standardlibrary/testHtml.action" frameborder="0" width="960px" height="1200px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>
 --%>   	
 <div class="bottom">
   
</div>
</div>
<!--content结束 -->
<!--底部替代标准列表内容开始 -->
<div class="replace-list-box">
    <div class="replace-list">
        <div class="replace-content">
            <ul class="replace-title" id="replace">
                <li style="float:left;">替代标准</li>
                <li style="float:right;"></li>
            </ul>
        </div>
        <div class="replace-pline"></div>
        <div class="replace-content">
            <ul class="replace-title" id="xilie">
                <li style="float:left;">系列标准</li>
                <li style="float:right;"></li>
            </ul>
        </div>
        <div class="replace-pline"></div>
        <div class="replace-content">
            <ul class="replace-title" id="sst">
                <li style="float:left;">体系表标准 	</li>
                <li style="float:right;"><a href="javascript:vido(0);"  onclick = "loadMore()" class="more">更多<i class="icon icon-double-angle-right"></i></a></li>
            </ul>
          
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!--底部替代标准列表内容结束 -->
<!--底部开始 -->
<div class="bottom">
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="76" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</div>
<!--底部结束 -->
<input type="text" id="sstid" value="${SSTId}" style="display:none">
<input type="text" id="standardId" value="${standardId}" style="display:none">
</body>
</html>
