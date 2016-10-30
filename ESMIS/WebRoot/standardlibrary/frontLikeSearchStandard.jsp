<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	.fenye-list li.shangyiye{
	padding:0px;
	width:60px;
	font-size:14px;
	}
	.fenye-list ul{
	align:center;	
	}
	</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>智能查询</title>
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jqPaginator.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/templist.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/condjson.js" type="text/javascript"></script>
 	<script type="text/javascript">
 		var rootPath="<%=basePath%>";
	 	var loadDataUrl = rootPath+"standardlibrary/frontLikeSearch.action?linesOfPage=20";
	 	var currentPage;
	 	var pageCount=1;
	 	var likeSearch;
	 	var recordCount;
	 	var applicableMajor;
	 	$(function(){
	 	var data = getParameter1();
	 	if(likeSearch!=null&&""!=likeSearch){
	 	$("#find").hide();
	 	var url=loadDataUrl;
	 	AjaxJson(url,true,data,loadData,false,false);
 		if(recordCount>0){
		loadFenye(data);
		}
	 	}else{
	 	$("#messager").show();
	 	}
	 	});
 	function getParameter1(){
 	 likeSearch = $("#likeSearch").val();
 	// alert(likeSearch);
 	if(likeSearch==""){
    		alert("请输入要查询的内容！");
    		$("#likeSearch").focus();
    		return false;
    	}
 	var param = "rand=" + Math.random();
		param += "&likeSearch="+likeSearch;
	return param; 	
 	}
 	function loadData(json){
	 	currentPage = json.currentPage;
	    pageCount = json.pageCount;
	 	recordCount = json.recordCount;
	 	//alert("recordCount="+recordCount);
	 	if(recordCount==0){
		 	$("#find").show();
		 	$("#disdata").css('display','block');
		 	$("#messager").css('display','block');
		 	$(".fenye").hide();
	 	}
	 	if(recordCount>0){
		 	$("#find").hide();
		 	$("#disdata").css('display','none');
	 	}
	 	var linesOfPage=5;
	 	var datalist = json.datalist;
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
			 	var data = datalist[i];
			 	applicableMajor =data.applicableMajor;//专业
			 	var standardId = data.standardId;//标准Id
			 	var standardNo = data.standardNo;//标准编号
			 	var standardName = data.standardName;//标准名称
			 	var standardNameEN = data.standardNameEN;//标准英文名称
			 	var standardCategory = data.standardCategory;//标准类别
			 	var standardCategoryName = data.standardCategoryName;//标准类别名称
			 	var effectiveDate = data.effectiveDate;//实施时间
			 	var effectiveState = data.effectiveState;//有效状态
			 	var documentType = data.documentType;//文档类别
			 	var approvedDate=data.approvedDate;//发布时间
			 	var content = data.content;//内容
			 	var oldStandardNo = data.oldStandardNo;//替代标准
			 	var oldStandardId = data.oldStandardId;//替代标准Id
			 	var key =data.key;//key
	 			var urlStr =data.str;//str
			 	var str,num;
			 	if(documentType=="1")
			 	{
			 		if(standardCategory==null||standardCategory==""){
			 			var src=standardNo.indexOf('>');
			 			if(src!="-1"){
			 				standardCategory=standardNo.substr(src+1,2);
			 			}else{
			 			 	standardCategory=standardNo.substr(0,2);
			 			}
			 		}
				 	 str =standardCategory.substring(0,1);
			 		
				 	 //alert(str);
				 	 num=ascii(str)%4;
			 	}
				var flog=isHaveThisOne(standardId);
			 	if(standardCategory==null){
			 	standardCategory="";
			 	}
			 	var content = data.content;//内容
			 	if(documentType=="1"){
				 		if(effectiveState=="1")
				 		{
							if(num==0){
								 element += '<div class="search-list bz-4">';
							}
							if(num==1){
								 element += '<div class="search-list bz-7">';
							}
							if(num==2){
								 element += '<div class="search-list bz-17">';
							}
							if(num==3){
								 element += '<div class="search-list bz-19">';
							}
						}else{
							 element+= '<div  class="search-list detele">';
							 element+= '<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>';
						}
						element += ' <span>'+standardCategory+'</span>';
				 		element += ' <ul class="text">';
						element += '<li class="title" style="width:939px;"><a target="_blank" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'"><i>'+standardNo+'</i><i>'+standardName+'</i></a>';
						element += '  <a class="download" href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url='+urlStr+'&key='+key+'"><i class="icon icon-download"></i><i>下载</i></a>';
						if(flog==true){
							element += '<i id="shoucang'+standardId+'" ><a onclick="removeCookie('+standardId+')"  style="float:left;text-align:left;width:90px;color:#ea8010;"><i class="icon icon-star " style="color:#ea8010;"></i><i  style="color:#ea8010;">已收藏</i></a></i>';
						}else{
							element += '<i id="shoucang'+standardId+'" ><a class="shoucang" onclick="addCollect('+standardId+')" ><i class="icon icon-star-empty"></i><i>收藏</i></a></i>';
						}		
						element += '</li>';
						element += ' <li class="text-content">';
						element += ' <a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'">';
						element += 	 content;
						element += ' </a>';
						element += ' </li>';
						element += ' <li class="text-other">';
						element += '<i class="fabu-time">发布时间：<em>'+approvedDate+'</em></i>';
						element += '<i class="shishi-time">实施时间：<em>'+effectiveDate+'</em></i>';
						if(""!=oldStandardNo&&oldStandardNo!=null)
						{
							element += '<i class="tidai">注：该标准已被<a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+oldStandardId+'" class="title-2"><em>'+oldStandardNo+'</em></a>(标准标号)取代</i>'
						}
						
						element += '</li>';
						element += '</ul>';
						element += '<div class="clearfix"></div>';
						element += '</div>';
				 	}
				 	if(documentType=="2")
				 	{
			 			if(effectiveState=="0")
			 			{
							element += ' <div class="search-list law-delete">';
							element += '<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>';
						}else
						{
							element += '<div class="search-list law">';
						}
						element += ' <span></span>';
				 			element += ' <ul class="text">';
						element += '<li class="title" style="width:939px;"><a target="_blank" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'"><i>'+standardNo+'</i><i>'+standardName+'</i></a>';
						element += '  <a class="download" href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url='+urlStr+'&key='+key+'"><i class="icon icon-download"></i><i>下载</i></a>';
						if(flog==true){
							element += '<i id="shoucang'+standardId+'" ><a onclick="removeCookie('+standardId+')"  style="float:left;text-align:left;width:90px;color:#ea8010;"><i class="icon icon-star " style="color:#ea8010;"></i><i  style="color:#ea8010;">已收藏</i></a></i>';
						}else{
							element += '<i id="shoucang'+standardId+'" ><a class="shoucang" onclick="addCollect('+standardId+')" ><i class="icon icon-star-empty"></i><i>收藏</i></a></i>';
						}		
						element += '</li>';
						element += ' <li class="text-content">';
						element += ' <a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'">';
						element += 	 content;
						element += ' </a>';
						element += ' </li>';
						element += ' <li class="text-other">';
						element += '<i class="fabu-time">发布时间：<em>'+approvedDate+'</em></i>';
						element += '<i class="shishi-time">实施时间：<em>'+effectiveDate+'</em></i>';
						element += '</li>';
						element += '</ul>';
						element += '<div class="clearfix"></div>';
						element += '</div>';
			 	}
			 }
		 	$("#data").html("");
		 	$("#data").html(element);
	 	}
	function loadPage(pageIndex,data){
	var pageUrl= loadDataUrl+"&currentPage="+pageIndex;
	 AjaxJson(pageUrl,true,data,loadData,false,false);
	}
	function loadFenye(data){
			$(".fenye").css('display','block');
			$("#start").empty();
			$("#start").jqPaginator({
            totalPages:pageCount,//总页数
            visiblePages:10,//设置最多显示页码
            currentPage: currentPage,//当前页码
            activeClass:'selected',
            pageSize:5,
            first: '<li class="first shangyiye" ><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev shangyiye"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
       	    next: '<li class="next shangyiye"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>下一页<\/a><\/li>',
            last: '<li class="last shangyiye"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (n) {
               loadPage(n,data);
            }
        });
		}	
	function searchByCondition1(){
	var data = getParameter1();
	var url=loadDataUrl;
	AjaxJson(url, true,data, loadData, false, false);
	if(recordCount>0){
	loadFenye(data);
	}
	}
	
   		 		function addCollect(id){
		//alert(id);
    		var cookieValue = getCookie();
    		if(cookieValue != ""){
    			cookieValue += ","+id;
    		}else{
    			cookieValue = ","+id;
    		}
    		var d = new Date();
			d.setTime(d.getTime() + '1');
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang"+id).html('<i class="icon icon-star"></i><a class="shoucang" onclick="removeCookie('+id+')">已收藏</a>');
    	}
		//取消收藏
		function removeCookie(id){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
			
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang"+id).html('<i class="icon icon-star-empty"></i><a class="shoucang" onclick="addCollect('+id+')">加入收藏</a>');
		}
		var c_name = "collect";
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
		function isHaveThisOne(id){
			id=","+id;
			var value = getCookie();
			var isHava;
			if(value != ""){
				isHava = value.indexOf(id);
				if(isHava != -1){
				$("#shoucang"+id).html('<i class="icon icon-star"></i><a class="shoucang" onclick="removeCookie('+id+')">已收藏</a>');
				return true;
			}
			}
			return false;
		}
		 function ascii(str){
		 if(str == null || str.length == 0){
		 	//alert("参数错误！");
		 	return 0;
		 }
	      var a = "A".charCodeAt(); 
	      var param = str.charCodeAt(); 
	      var i = (param - a)+1;
	      return i;
   		 }
 	</script>
 
 </head>   
<body >
<!--头部开始 -->
<div >
 <%@ include file="../header.jsp" %>
</div>
<!--头部结束 -->
<!--头部tab选项卡开始-->
<div class="tabPanel">
</div>
<!--头部tab选项卡结束-->

<!--头部tab选项卡内容开始-->
<div class="search-table">
    <div class="pane-box">
        <div class="pane" style="display:block;">
            <div class="tab-posit-cont">
                <a class="posit-b" href="${pageContext.request.contextPath}">首页</a>
                <i class="icon icon-angle-right"></i>
                <a class="posit-a">"<font color="red">${likeSearch}</font>"的查询结构</a>
            </div>
            <div id="data">
            <div class="nnone" style="margin:auto;margin-top:50px;width:320px;height:210px;">
                        <b class="nnone-img1"><img src="${pageContext.request.contextPath}/image/nnone1-1.png" alt=""/></b>                  
             </div>
            </div>      
            <div class="fenye" style="margin:0px; height:60px;padding-top:20px;">
                <ul class="fenye-list" id="start" >
                </ul>
            </div>

        </div>
    </div>

</div>
<!--头部tab选项卡内容结束-->

<!--底部开始 -->
<div >
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</div>
<!--底部结束 -->
</body>
