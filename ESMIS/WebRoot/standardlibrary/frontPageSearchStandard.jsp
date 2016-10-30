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
    <title>高级查询页面</title>
    <link href="${pageContext.request.contextPath}/css/my.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/zui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/skin/layer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/list-1.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/jqPaginator.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/templist.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/condjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/laypage-v1.3/layer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/standardlibrary/scripts/standardlibrary.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var rootPath="${pageContext.request.contextPath}/";
	 	var loadDataUrl = rootPath+"standardlibrary/searchStandard.action?linesOfPage=20";
	 	var lawsDataUrl = rootPath+"lawslibrary/searchFortLaws.action?linesOfPage=20";
	 	var url1=rootPath+"dictionary/searchCategory.action";
   	    var url2=rootPath+"dictionary/searchAdoptIS.action";
    	var url3=rootPath+"dictionary/searchDegree.action";
	 	var currentPage;
	 	var pageCount;
	 	var applicableMajor;
	 	$(function(){
	 		//layer.load(1);
	 	   $(".pane .nLi h3").click(function(){
                        if($(this).parent(".nLi").hasClass("on")){
                            $(this).next(".sub").slideUp(300,function(){
                                $(this).parent(".nLi").removeClass("on");
                                if($("#icon").hasClass("icon-chevron-up")){
                                    $("#icon").removeClass("icon-chevron-up");
                                    $("#icon").addClass("icon-chevron-down");
                                }
                            });
                        }else{
                            $(this).next(".sub").slideDown(300,function(){
                                $(this).parent(".nLi").addClass("on");
                                if($("#icon").hasClass("icon-chevron-down")){
                                    $("#icon").removeClass("icon-chevron-down");
                                    $("#icon").addClass("icon-chevron-up");
                                }
                            });
                        }
                    });
                    $('.tab-menu-right a').click(function(){
                		$(this).addClass('hit').siblings().removeClass('hit');
                		$('.pane-box>div:eq('+$(this).index()+')').show().siblings().hide();                		
           			 });
	 	 AjaxJson(url1,true,null,fun);
   	 	 AjaxJson(url2,true,null,fun1);
   	 	 AjaxJson(url3,true,null,fun2);
	 	//加载数据
	 	
	 	AjaxJson(lawsDataUrl,true,getParameter1(),loadData1,false,false);
	 	//AjaxJson(loadDataUrl,true,null,loadData,false,false);
	 	//loadFenye(null);
	 	loadFenye1(getParameter1());
	 	});
	function fun(data)
	{
	 $.each(data, function (index, units) {  
                $("#standardCategory").append("<option value="+units.id+">" + units.text + "</option>");  
            }); 
	}
	function fun1(data)
	{
	     $.each(data, function (index, units) {  
                $("#adoptIS").append("<option value="+units.id+">" + units.text + "</option>");  
            });  
    }
     function fun2(data)
    {
	     $.each(data, function (index, units) {  
                $("#adoptionDegree").append("<option value="+units.id+">" + units.text + "</option>");  
            });              
	}
	 function loadData(json){
	 	currentPage = json.currentPage;
	    pageCount = json.pageCount;
	 	recordCount = json.recordCount;
	 	var linesOfPage=5;
	 	var datalist = json.datalist;
	 	var element = ''; 
	 	for(var i=0;i<datalist.length;i++){
	 	var data = datalist[i];
	 	var key =data.key;//key
	 	var urlStr =data.str;//str
	 	var standardId = data.standardId;//标准Id
	 	var standardNo = data.standardNo;//标准编号
	 	var standardCategory = data.standardCategory;//标准类别
	 	var standardName = data.standardName;//标准名称
	 	var browseVolume = data.browseVolume;//浏览量
	 	var oldStandardNo = data.oldStandardNo;//替代标准编号
	 	var oldStandardId = data.oldStandardId;//替代标准Id
	 	var SSTLoaction = data.SSTLoaction;//体系表位置
	 	var effectiveState=data.effectiveState;//标准状态
	 	var documentType=data.documentType;//文档类型
	 	var approvedDate=data.approvedDate;//发布时间
	 	var effectiveDate=data.effectiveDate;//实施时间
	 	var str,num;
	 	if(approvedDate==null)
	 	{
	 		approvedDate="";
	 	}
	 	if(effectiveDate==null)
	 	{
	 		effectiveDate="";
	 	}
	 	if(standardCategory==null||standardCategory==""){
	 	 num=0;
	 	}else{
	 	 str =standardCategory.substring(0,1);
	 	}
	 	num=ascii(str)%4;
		var flog=isHaveThisOne(standardId);
		if(effectiveState=="1"){
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
			element += '<i id="shoucang'+standardId+'" ><a onclick="removeCookie('+standardId+')"  style="float:left;text-align:left;width:90px;color:#ea8010;"><i class="icon icon-star " style="color:#ea8010;"></i><i  style="color:#ea8010;font-size:12px;font-weight:normal;">已收藏</i></a></i>';
		}else{
			element += '<i id="shoucang'+standardId+'" ><a class="shoucang" onclick="addCollect('+standardId+')" ><i class="icon icon-star-empty"></i><i>收藏</i></a></i>';
		}		
		element += ' <i class="liulan"><i class="icon icon-eye-open"></i><i>'+browseVolume+'</i></i>';
		element += '</li>';
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
	 	$("#data").html("");
	 	$("#data").html(element);
	 	layer.closeAll('loading');
	 	}
	 	
	function loadPage(pageIndex,data){
		var pageUrl= loadDataUrl+"&currentPage="+pageIndex;
		AjaxJson(pageUrl,true,data,loadData,false,false);
	}
	function loadFenye(data){
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
           last: '<li class="last shangyiye"><a href="javascript:void(0);">共<i>'+pageCount+'</i>页<\/a><\/li>',
           page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
           onPageChange: function (n) {
              loadPage(n,data);
           }
       });
	}	
	function searchByCondition1(){
		var url = loadDataUrl;
		var data =getParameter00();
		AjaxJson(url, true,data, loadData, false, false);
		loadFenye(data);
	}
	
	// 生成GridView数据请求页面的paramerter
function getParameter00() {
	var json = "";
	var param = "rand=" + Math.random();
	if (document.getElementById("orderString")) {
		json = $("#orderString").val().replace(" ", "");
		if (json != "")
			param += "&orderString=" + json;
	}
	param += "&conditionString=" + compareConditionByQuery();
	return param;
}

	function searchByCondition2(){
	var url = lawsDataUrl;
	var data =getParameter1();
	AjaxJson(url, true,data, loadData1, false, false);
	loadFenye1(data);
	}
	//收藏
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
    		$("#shoucang"+id).html('<a onclick="removeCookie('+id+')" id="shoucang'+id+'" style="float:right;text-align:left;width:90px;color:#ea8010;"><i class="icon icon-star " style="color:#ea8010;font-weight:normal;"></i><i style="color:#ea8010;font-weight:normal;font-size:12px;">已收藏</i></a>');
    	}
		//取消收藏
		function removeCookie(id){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
			
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    		$("#shoucang"+id).html('<a class="shoucang" onclick="addCollect('+id+')"  id="shoucang'+id+'"><i class="icon icon-star-empty"></i><i>收藏</i></a>');
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
				$("#shoucang"+id).html('<a onclick="removeCookie('+id+')" id="shoucang'+id+'" style="float:right;text-align:left;width:90px;font-size:12px;font-weight:normal;"><i class="icon icon-star "></i><i>已收藏</i></a>');
				return true;
			}
			}
			return false;
		}
		  function ascii(str){
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
	<div class="search-table-tab">
		<div class="tab-menu-right">
          <a class="hit" >标准查询</a>
          <a>法律法规查询</a>
    	</div>
    </div>
	
    <div class="pane-box">
        <div class="pane" style="display:block;padding-top:0;">
            <div class="tab-posit-cont">
                <a class="posit-b" href="${pageContext.request.contextPath};">首页</a>
                <i class="icon icon-angle-right"></i>
                <i class="posit-b" >高级查询</i>
                <i class="icon icon-angle-right"></i>
                <i class="posit-a" >标准查询</i>
            </div>
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">                    
                    <ul class="sub">
                       <li>
                            <i class="major"><em>已选专业：</em>
                            <em id="professionalName">全部专业</em>
                            <input type="text" id="applicableMajor" style="display:none" inQuery="true"/>
                            </i>
                          <!--  <label>选择专业 </label>-->
                            <button id="parentIframe">选择专业<i class="icon icon-chevron-down" ></i></button>

                        </li>
                    
                        <li>
                            <label>标准编号 :</label>
                            <input  id="standardNo" name="standardNo" inQuery="true" operator="like" class="search-1" style="width:200px;"/>
                            <label>标准名称 :</label>
                            <input  id="standardName" name="standardName" inQuery="true" operator="like"  class="search-2"/>
                            <label>采用国际标准 :</label>
                            <input id="adoptIS" name="adoptIS"  inQuery="true" class="search-3" style="width:180px;"/>
                            <input id="documentType" name="documentType" value="1"  inQuery="true" class="search-3" style="width:180px;" type="hidden"/>
                        </li>
                        <li>
                            <label>替代标准 :</label>
                            <input id="oldStandardNo" name="oldStandardNo" operator="like" inQuery="true" class="search-1"  style="width:200px;"/>
                            <label>标准类别 :</label>
                             <select class="search-2-1" prompt="请选择标准类型" inQuery="true" panelHeight="auto" 
						id="standardCategory" name="standardCategory"  editable="true" style="width:180px;height:30px;margin-right:50px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
						<option value="-1">全部</option>
					</select>
					 <button id="search" type="button" onclick="searchByCondition1();" style="margin-left:70px;width:180px;">查询</button> 	                           
                        </li>                                                 
                            <script>
                                $('#parentIframe').on('click', function(){
                                    layer.open({
                                        type: 2,
                                        //skin: 'layui-layer-lan',
                                        title: '选择专业',
                                        fix: true,
                                        shadeClose: true,
                                        maxmin: false,
                                        area: ['700px', '490px'],
                                        content: '${pageContext.request.contextPath}/standardlibrary/selectProfessional.jsp'
                                    });
                                });
                            </script>
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->
 			<div id="data" style="display:block;">
            </div>
            <div class="fenye" style="height:60px;padding-top:20px;">
                <ul class="fenye-list" id="start" >
                </ul>
            </div>
 			<div id="tont">
            </div>

        </div>
        <div class="pane" style="padding-top:0;">
            <div class="tab-posit-cont">
                <a class="posit-b" href="${pageContext.request.contextPath};">首页</a>
                <i class="icon icon-angle-right"></i>
                <i class="posit-b" >高级查询</i>
                <i class="icon icon-angle-right"></i>
                <i class="posit-a" >法律法规查询</i>
            </div>
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">
                    <ul class="sub">
                        <li>
                            <label>法规文号 :</label>
                            <input  id="standardNo1"  class="search-1"/>
                            <label>法规名称 :</label>
                            <input  id="standardName1" class="search-2"/>
                            <input id="documentType1"  value="2" class="search-2" type="hidden"/>
                            <label>分类 :</label>
                            <select class="search-4" style="width:180px;" id="standardCategory1"  >
                                <option value="-1">全部</option>
                                <option value="1">贯彻</option>
                                <option value="2">适用</option>
                            </select>
 							<button id="search" type="button"  onclick="searchByCondition2();">查询</button>
                        </li>
                           
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->
			<div id="lawsdata">
			</div>
            <div class="fenye" style="height:60px;padding-top:20px;float:left;">          		
                <ul class="fenye-list" id="start1" >               		 
                </ul>
            </div>
    </div>

</div>
<!--头部tab选项卡内容结束-->
<!--底部开始 -->
<div class="bottom">
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</div>
<!--底部结束 -->
</body>
