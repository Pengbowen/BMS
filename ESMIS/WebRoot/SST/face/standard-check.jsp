<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
    <title>${title}</title>
    <link  href="${pageContext.request.contextPath}/css/my.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/public.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/login.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/skin/layer.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet"/>
       <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
       <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
       
       <script type="text/javascript">
		var rootPath="${pageContext.request.contextPath}/";
		var id = ${id};
		var replaceUrl = rootPath+"/standardlibrary/searchReplaceStandard.action?id="+id;
		var seriesStandard = rootPath+"/standardlibrary/searchSeriesStandard.action?id="+id;
		var searchSSTStandardUrl = rootPath+"/standardlibrary/searchSSTStandard.action?id="+id;
		var SSTId="${SSTId}";
		var width=580;
		
		$(function(){
			//如果没有分享人，则隐藏分享人
		var share = "${shareBy}";
		if(share=="-1"){
		$("#sharePeople").hide();
		}	
		//如果没有体系比位置，则隐藏体系表位置
		var sstLocation = "${sstLocation}";
		if(sstLocation == "-1"){
			$("#sstLocation").hide();
		}
		//判断是否已经收藏
		isHaveThisOne();
		//如果没有文件,则显示无文件
		var haveFile = "${haveFile}";
		if(haveFile == "-1"){
		//隐藏章节导航
		resizePageContent(1,false);
		
			$("#s").html("");
				var html=  '<div class="nnone"  style="margin:auto;margin-top:50px;width:320px;height:230px;">'+
                        '<b class="nnone-img1"><img src="${pageContext.request.contextPath}/image/nnone1-2.png" alt=""/></b>'+                        
                    	'</div>';
	 	$("#s").html(html);
	 	$("#s").css("height","380");
		}else{
		//判断是手工入库还是升级入库  1-升级入库, 9-手工入库
		//var standardId = $("#standardId").val();
		//var type= standardId.charAt(0);
		if(haveFile=="1"){
		$("#s").html('<iframe id="iData" src="${pageContext.request.contextPath}/standardlibrary/testHtml.action?standardId='+id+'" frameborder="0" width="580px" height="800px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>');
		var loadListUrl = rootPath+"/standardlibrary/loadList.action?id="+id;
		AjaxJson(loadListUrl,true,null,loadList,false,false);
		}
		if(haveFile=="2"){
		$("#s").html('<embed id="iData" style="width:580px; height:800px;" src="${pageContext.request.contextPath}/standardlibrary/readPdf.action?standardId='+id+'" bgcolor="#000000" name="simplevideostreaming" align="middle" type="application/pdf" />');
		//隐藏章节及章节列表
		
		$("#chapters").hide();
		resizePageContent(1,false);
		
		}		
		}
		
		
		
	 	AjaxJson(replaceUrl,true,null,loadData,false,false);
		AjaxJson(seriesStandard,true,null,loadData1,false,false);
		
		if(SSTId!="-1"){
		AjaxJson(searchSSTStandardUrl,true,null,loadData2,false,false);
		}else{
		$("#sst").append('<div class="none"></div>');
		}
		
		
		});
		function loadData(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var recordCount = datalist.length;
	 	if(recordCount<1){
	 		$("#replace").append('<div class="none"></div>');
	 	}
	 	
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardId = data.standardId;//标准id
	 	var standardName = data.standardName;//标准名称
	 	var standardNo = data.standardNo;
	 	var standardCategory = data.standardCategory;
 			if(standardCategory=="GB"){
       element +=            '<ul> <li  class="standard-classic-phto GB"><span>GB</span></li>';
        }else if(standardCategory=="JB"){
       element +=            '<ul> <li  class="standard-classic-phto JB"><span>JB</span></li>';
        }else if(standardCategory=="SD"){
         element +=            '<ul> <li  class="standard-classic-phto SD"><span>SD</span></li>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
       element +=            '<ul> <li  class="standard-classic-phto DL"><span>'+standardCategory+'</span></li>';
        }else{
         element +=           '<ul> <li  class="standard-classic-phto Q"><span>'+standardCategory+'</span></li>';
        }
           element +=            '<li  class="standard-classic-text">';
           element +=            '<a  href="javascript:vido(0);" onclick="checkDetail('+standardId+')"><i>'+standardNo+'</i>&nbsp;&nbsp;<br/><i>'+standardName+'</i></a>';
           element +=            '</li></ul>';             			
	 	}
	 	$("#replace").append(element);
	 	}
	 	
		function loadData1(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var count = datalist.length;
	 	if(count<1){
	 	$("#xilie").append('<div class="none"></div>');
	 	}
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardNo = data.standardNo;
	 	var standardId = data.standardId;//标准id
	 	var standardName = data.standardName;//标准名称
	 	var standardCategory = data.standardCategory;
	 		
	 	if(standardCategory=="GB"){
       element +=            '<ul> <li  class="standard-classic-phto GB"><span>GB</span></li>';
        }else if(standardCategory=="JB"){
       element +=            '<ul> <li  class="standard-classic-phto JB"><span>JB</span></li>';
        }else if(standardCategory=="SD"){
         element +=            '<ul> <li  class="standard-classic-phto SD"><span>SD</span></li>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
       element +=            '<ul> <li  class="standard-classic-phto DL"><span>'+standardCategory+'</span></li>';
        }else{
         element +=           '<ul> <li  class="standard-classic-phto Q"><span>'+standardCategory+'</span></li>';
        }
           element +=            '<li  class="standard-classic-text">';
           element +=            '<a  href="javascript:vido(0);" onclick="checkDetail('+standardId+')"><i>'+standardNo+'</i>&nbsp;&nbsp;<br/><i>'+standardName+'</i></a>';
           element +=            '</li></ul>';
	 	
	 	
	 	
 		/*  element +=      '<ul class="replace-content">'+
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
            	 element+=	     ' <li class="list-title" onclick="checkDetail('+standardId+')">';
                 element+=       ' <em>'+data.standardNo+'</em>';
              	 element+=       ' <em>'+data.standardName+'</em>';
              	 element+=       ' </li>';
                 element+=       ' </ul>';
                 element+= 		 ' </a>';
                 element+= 		 ' </li>';
           		 element+=		 ' </ul> ';    */            			
	 	}
	 	$("#xilie").append(element);
	 	}
	 		function loadData2(json){
	 	$("#data").html("");
	 	var datalist = json.datalist;
	 	var count = json.recordCount;
	 	if(count<1){
	 	$("#sst").append('<div class="none"></div>');
	 	}
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var standardId = data.standardId;//标准id
	 	var standardName = data.standardName;//标准名称
	 	var standardNo = data.standardNo;
	 	var standardCategory = data.standardCategory;
 			if(standardCategory=="GB"){
       element +=            '<ul> <li  class="standard-classic-phto GB"><span>GB</span></li>';
        }else if(standardCategory=="JB"){
       element +=            '<ul> <li  class="standard-classic-phto JB"><span>JB</span></li>';
        }else if(standardCategory=="SD"){
         element +=            '<ul> <li  class="standard-classic-phto SD"><span>SD</span></li>';
        }else if(standardCategory=="DL" || standardCategory=="SL"){
       element +=            '<ul> <li  class="standard-classic-phto DL"><span>'+standardCategory+'</span></li>';
        }else{
         element +=           '<ul> <li  class="standard-classic-phto Q"><span>'+standardCategory+'</span></li>';
        }
           element +=            '<li  class="standard-classic-text">';
           element +=            '<a  href="javascript:vido(0);" onclick="checkDetail('+standardId+')"><i>'+standardNo+'</i>&nbsp;&nbsp;<br/><i>'+standardName+'</i></a>';
           element +=            '</li></ul>';    			
	 	}
	 	$("#sst").append(element);
	 	}
	 	function checkDetail(id){
	 	window.open("${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id="+id);
	 	}
	 	
	 	
	 	var c_name = "collect";
	 	
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
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang").html('<i class="icon icon-star"></i><em onclick="removeCookie()">已收藏</em>');
    	}
		//取消收藏
		function removeCookie(){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
			
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang").html('<i class="icon icon-star-empty"></i><em onclick="addCollect()">收藏</em>');
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
			$("#shoucang").html('<i class="icon icon-star"></i><em onclick="removeCookie()">已收藏</em>');
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
	 	function dl(){
	 	var standardId = $("#standardId").val();
	 	window.location.href=rootPath+"standardlibrary/downloadOne.action?standardId="+standardId;
	 	}
	 	
	 	function resizePageContent(tag,isVisble)
	 	{
	 	    var iWidth = 160+590+250;
	 		if(tag==1)
	 		{	 		
	 		
	 			if(isVisble)
	 			{
	 			   $(".standard-content-left").show();
	 			   iWidth -= 160;
	 			}else{
	 			   if(!$(".standard-content-right").is(":visible")) return;
	 			   $(".standard-content-left").hide();
	 			}
	 			if($(".standard-content-right").is(":visible")){
	 				iWidth -= 250;
	 			}		 			 				 			
	 		}
	 		else if(tag==2)
	 		{
	 			isVisble = !$(".standard-content-right").is(":visible");
	 			if(isVisble)
	 			{
	 			   $(".standard-content-right").show();
	 			   $(".icon-angle-right").show();
	 			   $(".icon-angle-left").hide();
	 			   iWidth -= 250;
	 			}else{
	 			   $(".standard-content-right").hide();
	 			   $(".icon-angle-right").hide();
	 			   $(".icon-angle-left").show();			   
	 			}	 			
	 			if($(".standard-content-left").is(":visible")){
	 				iWidth -= 160;
	 			}		 				 			
	 		}
	 		width=iWidth;
	 		$(".standard-content-mid").css("width",iWidth+"px");
	 		$("#iData").css("width",iWidth+"px");
	 	}
	 	
	 	
	 	function loadList(data){
	 	$("#catlog").html("");
	 		var element = ' <li class="chapter-title">章节导航<i class="icon icon-times"  onclick="resizePageContent(1,false)" style="cursor: pointer;"></i></li>';
	 			$("#catlog").append(element);	
	 		var datalist =data.datalist;
	 		
			for(var i=0;i<datalist.length;i++){
			var map = datalist[i];
			var title = map.title;
			var path = "";
			path = map.path;
			var start = path.search("ESMIS");
			 path  = path.substring(start+5);
			 path=encodeURIComponent(path);
			 if(i==0){
			 test(path);
			 }
			 
			element='<li class="chapter" ><a href="javaScript:void(0);" onclick="test(&#34;'+path+'&#34;)"><i class="icon icon-caret-right">'+title+'</a></li>';
				$("#catlog").append(element);	
			}	 
			
			
	 	}
	 	function test(path){
	 	path = decodeURI(path);
		$("#s").html("");	
		$("#s").html('<iframe id="iData" src="'+rootPath+path+'" frameborder="0" width="'+width+'px" height="800px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>');
		}
	 	
	</script>
</head>
<body  >
<!--头部导航start-->
<!--头部开始 -->
  <%@ include file="../../header.jsp" %>
  	<div class="tabPanel">
    	<div class="sub-posit-cont" style="padding-top:36px;">
        	<a class="posit-a" href="${pageContext.request.contextPath}">首页</a> <i class="icon icon-angle-right"  ></i><a class="posit-a" href="${pageContext.request.contextPath}/resource/showResourceList.action">资源库</a>
        	<i class="icon icon-angle-right" ></i>  <b id="title"> ${title }</b>
     	</div>
     	</div>
<!--头部结束 -->
   <div class="search-table-1">
    <div class="pane-box">
        <div class="title">
                <div class="title-left">
                    <i class="icon icon-th-large" onclick="resizePageContent(1,true)" id="chapters" style="cursor: pointer;">&nbsp;章节</i>
                    <i>${standardNo }</i> <i>${title}</i><em style="margin-left:18px;" id="sharePeople">分享人：${shareBy }</em>

                </div>
                <div class="title-right">
                    <a href="javascript:vido(0);" class="shoucang"  id="shoucang"><i class="icon icon-star-empty"></i><em onclick="addCollect();">收藏</em></a>           <!--<i class="icon icon-star"></i>   表示已经收藏-->
                    <a href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url=${lockStr}&key=${key}" class="download"  ><i class="icon icon-download"></i>下载</a>
                </div>
        </div>


        <div class="standard-content">
            <ul class="standard-content-left" id="catlog" style="height:800px;overflow:auto">
                <li class="chapter-title">章节导航<i class="icon icon-times"  onclick="resizePageContent(1,false)" style="cursor: pointer;"></i></li id="catlog" >
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第一章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第二章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第三章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第四章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第五章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第六章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第七章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第八章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
                <li class="chapter"><a  href="javascript:vido(0);"><i class="icon icon-caret-right">&nbsp;第九章</i>&nbsp;&nbsp;工厂企业厂界环境噪音排放标准</a></li>
            </ul>

            <ul class="standard-content-mid full-mid">
                <li class="chevron" onclick="resizePageContent(2)" style="cursor: pointer;">
                <i class="icon icon-angle-right" ></i>
                <i class="icon icon-angle-left" style="display:none"></i>
                </li>   
                
                <li class="sub-content-text" id="s">
                   
                </li>
            </ul>

            <ul class="standard-content-right" style="width:250px">
                <!--替代标准开始-->
                <li class="standard-classic" id="replace">
                    <p >替代标准</p>
                </li>
                <!--替代标准结束-->
                <!--系列标准开始-->
                <li class="standard-classic" id="xilie">
                    <p >系列标准</p>
                </li>
                <!--系列标准结束-->
                <!--体系表标准开始-->
                <li class="standard-classic" id="sst">
                    <p >体系表标准</p>
                   
                    <!-- <ul>
                        <li  class="standard-classic-phto Q"><span>Q</span></li>
                        <li  class="standard-classic-text">
                            <a  href="javascript:vido(0);"><i>GB/T</i>&nbsp;<i>1230-1998</i>&nbsp;&nbsp;<i>工业企业厂界环境噪音排放标准</i></a>
                        </li>
                    </ul> -->
                </li>
                <!--体系表标准结束-->
            </ul>
            <ul class="clearfix"></ul>
        </div>
        <!--<div class="pane">4</div>-->
        <!--<div class="pane">5</div>-->
    </div>
</div>
<!--头部tab选项卡内容结束-->
<!--底部开始 -->
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
<input type="text" id="sstid" value="${SSTId}" style="display:none">
<input type="text" id="standardId" value="${id}" style="display:none">
</body>
</html>