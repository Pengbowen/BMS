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
	width:100%;
	}
	
	</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>标准列表</title>
    <link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
	<link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet" />
   
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.js"></script>
     	<script src="${pageContext.request.contextPath}/laypage-v1.3/laypage/laypage.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/laypage-v1.3/layer.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var rootPath="${pageContext.request.contextPath}/";
    	var dataStr = "${dataStr}";
    	var name = "${title}";
    	var SSTId = "${SSTId}";
	 	var loadDataUrl = rootPath+"standardDetail/loadStandardList.action?linesOfPage=20&"+dataStr;
	 	var loadCategoryUrl = rootPath+"dictionary/searchCategory.action";
	 	var loadAdoptISUrl = rootPath+"dictionary/searchAdoptIS.action";
	 	var pageCount;
	 	var currentPage;
	 	var recordCount;
	 	$(function(){
	 	//加载导航信息
	 	if(name !="-1"){
	 	$("#title").html(name);
	 	}
	 	if(SSTId=="1"){
	 	 var ele ='<i class="icon icon-angle-right" ></i><a class="posit-a" href="${pageContext.request.contextPath}/SST/technicalStandard.jsp">技术标准体系表</a>';
	 	$("#sstName").html(ele);
	 	}else if(SSTId == "2"){
	 	var ele ='<i class="icon icon-angle-right" ></i><a class="posit-a" href="${pageContext.request.contextPath}/SST/manageStandard.jsp">管理标准体系表</a>';
	 	$("#sstName").html(ele);
	 	}else if(SSTId=="3"){
	 	var ele ='<i class="icon icon-angle-right" ></i><a class="posit-a" href="${pageContext.request.contextPath}/SST/workStandard.jsp">工作标准体系表</a>';
	 	$("#sstName").html(ele);
	 	}
	 	//加载数据
	 	AjaxJson(loadDataUrl,true,null,loadData,false,false);
	 	
	 	//加载标准类别选项
	 	AjaxJson(loadCategoryUrl,true,null,loadCategory,false,false);
	 	$("#bzlb").hide();
	 	loadFenye(loadDataUrl);
	 	});
	 	function loadData(json){
	 	$("#dataDiv").html("");
	 	currentPage = json.currentPage;
	 	pageCount = json.pageCount;
	 	recordCount = json.recordCount;
	 	if(recordCount<1){
	 	var html=		'<div class="nnone" style="margin:auto;margin-top:50px;width:320px;height:230px;">'+
                        '<b class="nnone-img1"><img src="${pageContext.request.contextPath}/image/nnone1.png" alt=""/></b>'+
                        '</b>'+
                    	'</div>';
	 	
	 	$("#dataDiv").html(html);
	 	return;
	 	}
	 	var linesOfPage=5;
	 	var datalist = json.datalist;
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	
	 	var data = datalist[i];
	 	var standardNo = data.standardNo;//标准编号
	 	var standardId = data.standardId;//标准Id
	 	var standardCategory = data.standardCategory;//标准类别
	 	var standardName = data.standardName;//标准名称
	 	var browseVolume = data.browseVolume;//浏览量
	 	var oldStandardNo = data.oldStandardNo;//替代标准编号
	 	var oldStandardId = data.oldStandardId;//替代标准Id
	 	var SSTLoaction = data.SSTLoaction;//体系表位置
	 	var newStandardNo = data.newStandardNo;//新标准编号
	 	var newStandardId = data.newStandardId;//新标准id
 		var state =data.effectiveState;//标准状态 有效1 作废0
 		var lockStr =data.lockStr;//加密字符串
 		var key = data.key;//秘钥
 		var documentType = data.documentType;//文件类型 1-标准 2-法律法规
 		var approvedDate=data.approvedDate;//发布时间
	 	var effectiveDate=data.effectiveDate;//实施时间
 		var num = "";
 		if(state==1) {
			if (documentType != "2") {
				num = ascii(standardNo)%4;
				if(num==0){
				num=4;
				}
				if(num==1){
				num=7;
				}
				if(num==2){
				num=17;
				}
				if(num==3){
				num=19;
				}
			} else {
				num = "laws";
			}
    
		}else{
 			num = "fei";
 			if(documentType=="2"){
 			num=" law-delete"
 		}
 		}
 		if(approvedDate=="0"){
 		approvedDate="";
 		}
 		if(effectiveDate=="0"){
 		effectiveDate="";
 		}
 		 element += '<div class="search-list bz-'+num+' ">';
 		 
 		 if(documentType == "2"){
				element += '<span></span>';
			}else{
				element += '<span>'+standardCategory+'</span>';
			}
			if(state == 0){
				element += '<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>';
			}
 		
  		 element += ' <ul class="text">';
  		element += '<li class="title" style="width:939px;"><a target="_blank" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+standardId+'"><i>'+standardNo+'</i><i>'+standardName+'</i></a>';
	   element += '       <a class="download"  href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url='+lockStr+'&key='+key+'"><i class="icon icon-download"></i><i>下载</i></a>';
	    if(isHaveThisOne(standardId)){
   		 element += '<a class="shoucang" id="'+standardId+'"><i class="icon icon-star"></i><i onclick="removeCookie('+standardId+')">已收藏</i></a>';
   		 }else{
   		  element += '<a class="shoucang" id="'+standardId+'"><i class="icon icon-star-empty"></i><i onclick="addCollect('+standardId+')">收藏</i></a>';
   		 } 
	   element += ' <i class="liulan"><i class="icon icon-eye-open"></i><i>'+browseVolume+'</i></i>';
	    element += '</li>';
	     element += '  <li class="text-other">';
	     element += '<i class="fabu-time">发布日期：<em>'+approvedDate+'</em></i>';
		element += '<i class="shishi-time">实施日期：<em>'+effectiveDate+'</em></i>';
	     //判断是否有替代标准
	     if(newStandardNo != 0){
	     element += '<i class="tidai">注：该标准已被<a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+newStandardId+'" class="title-2"><em>'+newStandardNo+'</em></a>取代</i>'
	     }else if(oldStandardNo != 0){
    	  element += '<i class="tidai">注：该标准已把<a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id='+newStandardId+'" class="title-2"><em>'+newStandardNo+'</em></a>取代</i>'
	     
	     }
   		 element += '   </li>';
  		 element += ' </ul>';
	     element += '  <div class="clearfix"></div>';
 		 element += '  </div>';
	 	}
	 	
	 	$("#dataDiv").html(element);
	 	}
	 	//加载标准类别
	 	function loadCategory(json){
	 	var datalist = json;
	 	for(var i=0;i<datalist.length;i++){
	 	var id = datalist[i].id;
	 	var text = datalist[i].text;
	 	var option='<option value="'+id+'">'+text+'</option>';
	 	$("#bzlb").append(option);
	 	}
	 	
	 	}
	 	//加载采用国际标准
	 	function loadAdoptIS(data){
	 	for(var i=0;i<data.length;i++){
	 	var id = data[i].id;
	 	var text = data[i].text;
	 	var option='<option value="'+id+'">'+text+'</option>';
	 	$("#cbcd").append(option);
	 	}
	 	
	 	}
	 //加载分页	
	function loadPage(pageIndex,url){
	var pageUrl= url+"&currentPage="+pageIndex;
	 AjaxJson(pageUrl,true,null,loadData,false,false);
	}
	function loadFenye(url){
			laypage({
		            cont: 'ss', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
		            pages:pageCount, //通过后台拿到的总页数
		            curr: currentPage, //当前页
		            skin: '#50a7ef',
		            skip: true,
		            first: '首页',
		            last: '共'+pageCount+'页',
		            jump: function(obj, first){ //触发分页后的回调
		                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
		                    loadPage(obj.curr,url);
		                }else{
		                	layer.closeAll('loading');
		                }
		            }
	       	 	});
	 	
	
	
		/* 	$("#ss").jqPaginator({
            totalPages:pageCount,//总页数
            visiblePages:10,//设置最多显示页码
            currentPage: currentPage,//当前页码
            activeClass:'selected',
            pageSize:5,
            first: '<li class="first shangyiye" style="width:44px;text-align:center;"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev shangyiye"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
            next: '<li class="next shangyiye"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
            last: '<li class="last shangyiye" style="width:105px;text-align:center;"><a href="javascript:void(0);">末页(共'+pageCount+'页)<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (n) {
               loadPage(n,url);
            }
        }); */
		}	
		function searchStand(){
		var standardNo="";
		var standardName="";
		var standardNo = $("#standardNo").val();
		var standardName = $("#standardName").val();
		var includeInvalid = 0;//是否包含作废  0-不包括,1-包括 ,默认为不包括;
		if($("#includeInvalid").is(":checked")){
		includeInvalid = 1;
		}
		var dataStr = "&standardNo="+standardNo;
			dataStr +="&standardName="+standardName;
			dataStr +="&includeInvalid="+includeInvalid;
		var serachUrl = loadDataUrl + dataStr;
		AjaxJson(serachUrl,true,null,loadData,false,false);
		loadFenye(serachUrl);
		}
		function checkStandard(standardId){
		window.open(rootPath+"standardlibrary/toStandardDetail.action?id="+standardId);
		}
		
		//收藏相关方法
		var c_name = "collect";
		//收藏
		function addCollect(id){
    		var cookieValue = getCookie();
    		if(cookieValue != ""){
    			cookieValue += ","+id;
    		}else{
    			cookieValue = ","+id;
    		}
    		var d = new Date();
			d.setTime(d.getTime() + '1');
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#"+id).empty();
    		$("#"+id).html('<i class="icon icon-star"></i><i onclick="removeCookie('+id+')">已收藏</i>');
    	}
		//取消收藏
		function removeCookie(id){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
			
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#"+id).empty();
    		$("#"+id).html('<i class="icon icon-star-empty"></i><i onclick="addCollect('+id+')">收藏</i>');
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
		function isHaveThisOne(id){
			var value = getCookie();
			var isHava;
			if(value != ""){
				isHava = value.indexOf(id);
				if(isHava != -1){
				return true;
			}
			}
			return false;
		}
		
		function ascii(str){
	      var a = "A".charCodeAt(); 
	      var param = str.charCodeAt(0); 
	      var i = (param - a)+1;
	      return i;
	    }
		
    </script>
 </head>   
<body onload="change('sstHome')">
<!--头部开始 -->
  <%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
        <a class="posit-a" href="${pageContext.request.contextPath}">首页</a> <i class="icon icon-angle-right" ></i><a class="posit-a" href="${pageContext.request.contextPath}/sst/toSSTHome.action">体系表</a>
        	<em id="sstName"></em><i class="icon icon-angle-right"></i>  <b id="title"> 标准列表</b>
    </div>
</div>

<!--banner结束 -->
<div class="search-table">
	<div class="pane-box">
    <div class="pane" style="display:block;padding-top:16px;">
        <ul class="list-content">
            <li class="conditions" style="padding:10px 20px;">
                <h3 style="margin: 0;height:20px;line-height:20px;'">查询条件:</h3>
                
                <div class="group">
                    <div class="row-list" style="width:40%">
                        <label class="control-label">标准编号/法规文号：</label>
                        <input type="" id="standardNo" class="form-control-1" required="required" name="" style="width: 170px;">
                    </div>
                    <div class="row-list" style="width:33%">
                        <label class="control-label">标准/法规名称：</label>
                        <input type="" id="standardName" class="form-control-2" required="required" name="" style="width: 170px;">
                    </div>
                     <div class="row-list" style="width:13%;line-height:15px;">
                        <input type="checkbox" id="includeInvalid" class="form-control-2" required="required" name="" style="width:20px; height:15px;line-height:15px;margin:0px 0px"/>包含作废
                    </div>
                     <div class="row-list" style="width:10%;height:32px;" >
                       <a  class="demand" onclick="searchStand()" style="width:50px;height:32px;margin-top:0px;margin-left:50px;line-height:32px">查询</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
               
            </li>
            <div id="dataDiv">
            </div>
            <div class="fenye">
                <ul class="fenye-list" id="ss">
                </ul>
            </div>
        </ul>
    </div>
    </div>
</div>
<!--底部开始 -->
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
