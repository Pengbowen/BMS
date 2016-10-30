<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<base target="_blank"/>
<title>企业标准化管理V4.0</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet"/>
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/home.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/html5shiv.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/respond.min.js" type="text/javascript"></script>

<style type="text/css">
		#demo{
		overflow:hidden;
		width:800px;
		height:30px;
		line-height:30px;
		position:relative;
		margin:15px 0;
		}
		#demo1{
		height:auto;
		text-align:left;
		height:${fn:length(noticeList)*30}px;
		}
		#demo2{
		height:auto;
		text-align:left;
		height:30px;
		}
		#demo1 li{
		list-style-type:none;
		text-align:left;
		text-indent:8px;
		height:30px;
		}
		#demo2 li{
		height:30px;
		list-style-type:none;
		text-align:left;
		text-indent:8px;
		}
	</style>
	<script type="text/javascript"> 
    var speed=40;
    window.onload=function(){
    var demo=document.getElementById("demo"); 
    var demo2=document.getElementById("demo2"); 
    var demo1=document.getElementById("demo1"); 
    demo2.innerHTML=demo1.innerHTML;
    function Marquee(){ 
    if(demo.scrollTop>=demo1.offsetHeight){
    demo.scrollTop=0; 
    }
    else{ 
    demo.scrollTop=demo.scrollTop+1;
    } 
    } 
    var MyMar=setInterval(Marquee,speed) 
    demo.onmouseover=function(){clearInterval(MyMar)} 
    demo.onmouseout=function(){MyMar=setInterval(Marquee,speed)} 
    }
    function searchStandard(){
    	var url = '${pageContext.request.contextPath}/standardlibrary/frontLinkSearchStandard.action';    	
    	var likeSearch = $.trim($("#likeSearch").val());    	
    	if(likeSearch==""){
    		alert("请输入要查询的内容！");
    		$("#likeSearch").focus();
    		return false;
    	}
    	window.location.href= url+ "?likeSearch="+encodeURI(encodeURI(likeSearch));
    }
	$(document).ready(function() {
		//回车事件绑定
		$('#likeSearch').bind('keyup', function(event) {
			if (event.keyCode == "13") {
				//回车执行查询
				searchStandard();
			}
		});
	});	
    </script>
</head>

<body onload="change('webIndex')">
<!--头部开始 -->
<div class="top">
	<%@ include file="header_home.jsp" %>
</div>
<!--头部结束 -->
<!--banner开始 -->
<div class="sub-banner-homewrap sub-banner-05">
    <div class="search-position">
    	<ul class="qiye-logo"><span><img src="${pageContext.request.contextPath}/image/logo.png" height="46"/></span></ul>
        <ul class="search-top">
            <li class="search-left">
                <input type="text" class="sub-sear-text form-control" name="likeSearch" id="likeSearch">
                <span><input type="button" value="搜索" class="add-bnt" onclick="searchStandard()"></span>
            </li>
            <li class="search-right"><a href="${pageContext.request.contextPath}/standardlibrary/frontSearchStandard.action">高级查询</a></li>
        </ul>
    </div>
</div>
<!--banner结束 -->
<!--通知公告content开始 --><!--修改html============================================== -->
<s:if test="noticeList != null ">
<div class="notice-box">
	<div class="notice">
        <i class="icon icon-volume-up" style="float:left; line-height:60px;font-size:18px;color:#e88e19;">&nbsp;通知公告</i>
    	<div id="demo" style="float:left;" >
        <ul id="demo1">
        	<s:iterator value="noticeList" var="notice">
            	<li><a href="${pageContext.request.contextPath}/notice/skipToDetailForFront.action?id=${notice.id}">${notice.noticeTitle}</a></li>
            </s:iterator>
        </ul>
        <div id="demo2"></div>
        </div>
        <div class="notice-right"><a href="${pageContext.request.contextPath}/notice/noticeShow.jsp">更多<i class="icon icon-double-angle-right"></i></a></div>
    </div>
</div>
</s:if>
<!--content结束 -->
<!--content开始 -->
<s:if test="newStandardLibraryList.size()>0">
<div class="standard-content-box">
    <div class="standard-content-1">
        <h1 style="margin-top:0;">最新标准</h1>
        <h2 style="margin-top:0;">New standard</h2>
        <div class="standard-text">
            <ul class="bg-1"><img src="${pageContext.request.contextPath}/image/001.png"/></ul>
            <ul style="width: 650px;">
            	<s:iterator value="newStandardLibraryList" var="standardLibrary">
                <li class="standard-text-right">
                	<s:if test='%{#standardLibrary.standardCategory == "GB"}'>
                		<span class="GB-1">GB</span>
                	</s:if>
                	<s:elseif test='%{#standardLibrary.standardCategory == "Q"}'>
                		<span class="Q">Q</span>
                	</s:elseif>
                	<s:elseif test='%{#standardLibrary.standardCategory == "DL"}'>
                		<span class="DL">DL</span>
                	</s:elseif>
                	<s:else>
                		<span class="SSL">${standardLibrary.standardCategory}</span>
                	</s:else>
                    <ul>
                        <li><a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}"><i>${standardLibrary.standardCategory}/</i><i>${standardLibrary.standardNo}</i></a></li>
                        <li>
                        	<a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}">
                        		<i>
                        			<s:if test="#standardLibrary.standardName.length() > 16">
 										<s:property value="#standardLibrary.standardName.substring(0,16)"/>
									</s:if>
									<s:else>
										${standardLibrary.standardName}
									</s:else>
                        		</i>
                        		<i>
                        			<s:if test="#standardLibrary.standardNameEN!='' && #standardLibrary.standardNameEN!=null">
                        				<s:if test="#standardLibrary.standardNameEN.length() > 45">
 											(<s:property value="#standardLibrary.standardNameEN.substring(0,45)"/>......)
										</s:if>
										<s:else>
											(${standardLibrary.standardNameEN})
										</s:else>
                        			</s:if>
                        		</i>
                        	</a>
                        </li>
                    </ul>
                    <a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}"><i class="icon icon-angle-right"></i></a>
                </li>
            	</s:iterator>
            </ul>
        </div>
    </div>
</div>
</s:if>
<!--content结束 -->
<!--content开始 -->
<s:if test="hotStandardLibraryList.size()>0">
<div class="standard-content-box-1">
    <div class="standard-content-2">
        <h1 style="margin-top:0;">热门标准</h1>
        <h2 style="margin-top:0;">Hot standard</h2>
        <div class="standard-text">
            <ul style="width: 650px;">
            	<s:iterator value="hotStandardLibraryList" var="standardLibrary">
                <li class="standard-text-right">
                    <s:if test='%{#standardLibrary.standardCategory == "GB"}'>
                		<span class="GB-1">GB</span>
                	</s:if>
                	<s:elseif test='%{#standardLibrary.standardCategory == "Q"}'>
                		<span class="Q">Q</span>
                	</s:elseif>
                	<s:elseif test='%{#standardLibrary.standardCategory == "DL"}'>
                		<span class="DL">DL</span>
                	</s:elseif>
                	<s:else>
                		<span class="SSL">${standardLibrary.standardCategory}</span>
                	</s:else>
                    <ul>
                        <li><a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}"><i>${standardLibrary.standardCategory}/</i><i>${standardLibrary.standardNo}</i></a></li>
                        <li><a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}">
                        		<i>
                        			<s:if test="#standardLibrary.standardName.length() > 16">
 										<s:property value="#standardLibrary.standardName.substring(0,16)"/>
									</s:if>
									<s:else>
										${standardLibrary.standardName}
									</s:else>
                        		</i>
                        		<i>
                        			<s:if test="#standardLibrary.standardNameEN!='' && #standardLibrary.standardNameEN!=null">
                        				<s:if test="#standardLibrary.standardNameEN.length() > 45">
 											(<s:property value="#standardLibrary.standardNameEN.substring(0,45)"/>......)
										</s:if>
										<s:else>
											(${standardLibrary.standardNameEN})
										</s:else>
                        			</s:if>
                        		</i>
                        	</a>
                        </li>
                    </ul>
                    <a title="${standardLibrary.standardName}" href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${standardLibrary.standardId}"><i class="icon icon-angle-right"></i></a>
                </li>
            	</s:iterator>
            </ul>
            <ul class="bg-1"><img src="${pageContext.request.contextPath}/image/002.png"/></ul>
        </div>
    </div>
</div>
</s:if>
<!--content结束 -->
<s:if test="blogrollList.size()>0">
<div class="link-box">
    <ul class="link">
        <li class="link-0"><img src="image/link.png" /></li>
        	<s:iterator value="blogrollList" var="blogroll" status="b">
        		<li class="<s:if test="#b.last">link-2</s:if><s:if test="!#b.last">link-1</s:if>"><a href="${blogroll.linkeUrl}" target="_blank" title="${blogroll.linkName}"><img src="${pageContext.request.contextPath}/${blogroll.pictureUrl}" alt="${blogroll.linkName}"/> </a></li>
        	</s:iterator>
    </ul>
</div>
</s:if>
<!--底部开始 -->
<iframe src="footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
<!--悬浮框开始 -->
<div class="right-bar">
	<div class="dtl11">
             <i>分享标准</i>
             <div class="dtl">
           		 <a href="${pageContext.request.contextPath}/answer/addCriteria.jsp" class="icon icon-share"></a>
            </div>
    </div>
	<div class="dtl11">
             <i>我要标准</i>
             <div class="dtl">
           		 <a href="${pageContext.request.contextPath}/answer/addAskForCriteria.jsp" class="icon icon-file-text-o"></a>
            </div>
    </div>
	<div class="dtl11">
             <i>意见建议</i>
             <div class="dtl">
           		 <a href="${pageContext.request.contextPath}/answer/addAnswer.jsp" class="icon icon-edit"></a>
            </div>
    </div>
    <div class="dtl11">
             <i class="icon icon-angle-up" style="font-size:28px;"></i>
             <div class="dtl">
           		 <a onclick="window.scroll(0, 0)" style="font-size:14px;">返回顶部</a>
            </div>
    </div>
</div>
<!--悬浮框结束 -->
</body>
</html:html>
