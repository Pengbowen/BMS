<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${title }</title>
<link href="${pageContext.request.contextPath}/css/my.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/login.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/sub-style2.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/zui.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/skin/layer.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/list-1.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js"
	type="text/javascript"></script>

<script type="text/javascript">
	 	var rootPath="${pageContext.request.contextPath}/";//根路径
	 	var id = ${id};//获取标准Id
		var replaceUrl = rootPath+"/standardlibrary/searchReplaceStandard.action?id="+id;//加载替代标准路径
		var seriesStandard = rootPath+"/standardlibrary/searchSeriesStandard.action?id="+id;//加载相关标准路径
		var searchSSTStandardUrl = rootPath+"/standardlibrary/searchSSTStandard.action?id="+id;//加载体系表标准路径
		var SSTId="${SSTId}";//获取体系表id
		var width=920;//定义阅读区域初始宽度
		//收藏用到的c_name
	 	var c_name = "collect";
	 	var bodyHeight =0;  //浏览器可视高度
	 	var bodyWidth = 0;	//浏览器可视宽度
		var contentHeight=0;//内容显示区域高度(除去页头和页尾)
	 	var contentWidth = 0 //内容显示区域宽度

	$(function() {
		//切换标签栏和目录
		var $a = $('.reader-left-menu a');
		var $ul = $('.menu-1 .standard-content-left');
		$a.click(function() {
			var $this = $(this);
			var $t = $this.index();
			if ($ul.eq($t).is(":visible")){
			$ul.eq($t).hide();
			$(".reader-content").css("width","960");
			$("#iData").css("width","920");
			}else{
			$a.removeClass();
			$this.addClass('currents');
			$ul.hide();
			$ul.eq($t).show();
			$(".reader-content").css("width","800");
			$("#iData").css("width","760");
			}
		})
	});
	 //点击x 触发事件
		function closeLeft(){
			$(".menu-1 .standard-content-left").hide();
			$(".reader-content").css("width","960");
			$("#iData").css("width","920");
		} 
		
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
		$(".reader-left-menu").hide();
		$(".leftMenu").hide();
		$(".reader-content").css("width","1000");
		$("#iData").css("width","960");
		//resizePageContent(1,false);
		
			$("#read").html("");
				var html=  '<div class="nnone"  style="margin:auto;padding-top:50px;width:320px;height:230px;">'+
                        '<b class="nnone-img1"><img src="${pageContext.request.contextPath}/image/nnone1-2.png" alt=""/></b>'+                        
                    	'</div>';
	 	$("#read").html(html);
	 	$("#read").css("height","430");
		}else{
		//判断是手工入库还是升级入库  1-升级入库, 9-手工入库
		if(haveFile=="1"){
		$("#read").html('<iframe id="iData" src="${pageContext.request.contextPath}/standardlibrary/testHtml.action?standardId='+id+'" frameborder="0" width="900px" height="'+800+'px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>');
		var loadListUrl = rootPath+"/standardlibrary/loadList.action?id="+id;
		AjaxJson(loadListUrl,true,null,loadList,false,false);
		}
		if(haveFile=="2"){
		
		$("#read").html('<embed id="iData" style="width:920px; height:'+800+'px;" src="${pageContext.request.contextPath}/standardlibrary/readPdf.action?standardId='+id+'" bgcolor="#000000" name="simplevideostreaming" align="middle" type="application/pdf" />');
		//隐藏章节及章节列表
		$(".reader-left-menu").hide();
		$(".leftMenu").hide();
		$(".reader-content").css("width","1000");
		$("#iData").css("width","960");
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
		
		//加载替代标准数据
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
	 	
	 	//加载相关标准数据
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
	 	}
	 	$("#xilie").append(element);
	 	}
	 	
	 	//加载体系表标准数据
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
	 	
	 	//查看标准详情
	 	function checkDetail(id){
	 	window.open("${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id="+id);
	 	}
	 	
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
    		$(".shoucang").html('<i class="icon icon-star"></i><em onclick="removeCookie()">已收藏</em>');
    	}
		//取消收藏
		function removeCookie(){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
			
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$(".shoucang").html('<i class="icon icon-star-empty"></i><em onclick="addCollect()">收藏</em>');
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
			$(".shoucang").html('<i class="icon icon-star"></i><em onclick="removeCookie()">已收藏</em>');
				return true;
			}
			}
			return false;
		}
	 	//加载章节目录
	 	function loadList(data){
	 	$("#catlog").html("");
	 		var element = ' <li class="chapter-title">章节导航<i class="icon icon-times"  onclick="closeLeft(this);" style="cursor: pointer;"></i></li>';
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
			 goTo(path);
			 $("#iData").css("width","920px");
			 }
			element='<li class="chapter" ><a href="javaScript:void(0);" title ="'+title+'" onclick="goTo(&#34;'+path+'&#34;)"><i class="icon icon-caret-right">'+title+'</a></li>';
				$("#catlog").append(element);	
			}
	 	}
	 	//解码路径,并将显示区域显示为该路径的内容
	 	function goTo(path){
	 	path = decodeURI(path);
		$("#read").html("");	
		console.log(path);
		$("#read").html('<iframe id="iData" src="'+rootPath+path+'" frameborder="0" width="760px" height="800px" scrolling="yes" marginwidth="0" marginheight="0" ></iframe>');
		}
		//获取字符在字母表中的位置  例如:A 返回 1;
		function ascii(str) {
		var a = "A".charCodeAt();
		var param = str.charCodeAt(0);
		var i = (param - a) + 1;
		return i;
		}
		//点击隐藏或显示相关标准
		function clickRight(visible){
		//如果
		if(visible){
		$(".reader-right-list").show();
		$("#left").hide();
		$("#right").show();
		}else{
		$(".reader-right-list").hide();
		$("#right").hide();
		$("#left").show();
		}
		}
		function fullScreen(){
		bodyHeight = $(document).height();
		contentHeight = bodyHeight;
		bodyWidth = $(document).width();
		contentWidth = bodyWidth;
		var content = $("#contentArea").html();
		
		}
</script>

</head>
<body>
	<!--头部导航start-->
	<!--头部开始 -->
	<%@ include file="../../header.jsp"%>
	<!--头部结束 -->
	<!--标准详情查看开始-->
	<div class="search-table-2">
		<div class="reader-box" id="contentArea">
			
			<!--标准标题栏开始-->
			<div class="reader-title">
				<div class="reader-title-left">
					<i>${standardNo }</i>&nbsp;&nbsp;&nbsp; <i>${title}</i>
				</div>
				<div class="reader-title-right">
					<a href="javascript:void(0);" class="full-page" ><i class="icon icon-expand-full" style="display:none"></i></a>
					 <a href="javascript:void(0);" class="shoucang" id="collect"><i class="icon icon-star-empty"></i><em onclick="addCollect()">收藏</em></a>
					<!-- <i class="icon icon-star"></i>   表示已经收藏-->
					<a href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?url=${lockStr}&key=${key}" class="download"><i class="icon icon-download"></i>下载</a> 
					<a href="javascript:void(0);" class="kuozhan"  id="right"onclick="clickRight(false);"><i class="icon icon-double-angle-right"></i></a>
					<a href="javascript:void(0);" class="kuozhan" id="left" onclick="clickRight(true);" style="display:none"><i class="icon icon-double-angle-left"></i></a>
					<!--<i class="icon icon-double-angle-left"></i>  表示收缩之后的图文显示-->
				</div>
				<div class="clearfix"></div>
			</div>
			<!--标准标题栏结束-->

			<!--标准内容左侧栏开始-->
			<div class="reader-left-menu">
				<a class="leftMenu"><i class="icon icon-list-ul"></i>章节</a> 
				<a class="leftMenu" style="display:none"><i class="icon icon-bookmark-empty" ></i>页签</a>
			</div>
			<!--标准内容左侧栏结束-->
			<div class="reader-menus" >
				<!--章节菜单开始-->
				<div class="menu-1" >
					<ul class="standard-content-left" id="catlog" style="height:845px">
						<li class="chapter-title">章节导航 <i class="icon icon-times"  onclick="closeLeft(this);"></i></li>
					</ul>
				</div>
				<!--章节菜单结束-->

				<!--页签菜单开始-->
				<div class="menu-1" >
					<ul class="standard-content-left" id="bookmark" style="height:845px">
						<li class="chapter-title">书签列表  <i class="icon icon-times"  onclick="closeLeft(this);"></i></li>
					</ul>
				</div>
				<!--页签菜单结束-->
			</div>
			<!--标准查看html内容开始-->
			<div class="reader-content">
				<ul class="reader-content-mid">
					<li class="reader-content-text" id="read"></li>
				</ul>
			</div>
			<!--标准查看html内容结束-->
			<div class="clearfix"></div>
			<!--标准内容替代标准右侧栏开始-->
			<div class="reader-right-list">
				<div class="standard-classic" id="replace">
					<p>替代标准</p>
				</div>
				<div class="standard-classic" id="xilie">
					<p>系列标准</p>
				</div>
				<div class="standard-classic" id="sst">
					<p>体系表标准</p>
				</div>
			</div>
			<!--标准内容替代标准右侧栏结束-->
		</div>
	</div>
	<!--标准详情查看结束-->
	<!--头部tab选项卡内容结束-->
	<!--尾部页脚开始-->
	<!--底部开始 -->
	<iframe src="${pageContext.request.contextPath}/footer.html"
		frameborder="0" width="100%" height="120" scrolling="no"
		marginwidth="0" marginheight="0"  id="footer"></iframe>
	<!--底部结束 -->
	<!--尾部页脚结束-->
</body>
</html>