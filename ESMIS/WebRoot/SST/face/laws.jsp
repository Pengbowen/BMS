<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${standardCategory==1?"贯彻执行法规查询":"适用执行法规查询"}</title>
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet"/>
<link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>

<script type="text/javascript">
			var rootPath = "${pageContext.request.contextPath}/";
			$(function(){
                $(".pane .nLi h3").click(function(){
                    if($(this).parent(".nLi").hasClass("on")){
                        $(this).next(".sub").slideUp(300,function(){
                            $(this).parent(".nLi").removeClass("on")
                            if($("#icon").hasClass("icon-chevron-up")){
                                $("#icon").removeClass("icon-chevron-up")
                                $("#icon").addClass("icon-chevron-down");
                            }
                        });
                    }else{
                        $(this).next(".sub").slideDown(300,function(){
                            $(this).parent(".nLi").addClass("on")
                            if($("#icon").hasClass("icon-chevron-down")){
                                $("#icon").removeClass("icon-chevron-down")
                                $("#icon").addClass("icon-chevron-up");
                            }
                        });
                    }
                })
            })
            function submitForm(v){
				document.getElementById("currentPage").value=v;
				if(document.getElementById("standardNo").value=="" && document.getElementById("standardName").value==""){
					alert("请输入要查询的内容");
					document.getElementById("standardNo").focus();
					return false;
				}
                var frm=document.searchForm;
                frm.submit();
        	}  
			
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
		        $("#"+id).html('<i class="icon icon-star"></i><i onclick="removeCookie('+id+')">已收藏</i>');
		      }
		    //取消收藏
		    function removeCookie(id){
		      var cookieValue = getCookie();
		      cookieValue = cookieValue.replace(","+id,"");
		      var d = new Date();
		      d.setTime(d.getTime() + '1');
		      
		        document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
		        $("#"+id).html('<i class="icon icon-star-empty"></i><i onclick="addCollect('+id+')">加入收藏</i>');
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
		        };
		      }
		      return false;
		    }
</script>
</head>
<body onload="change('sstHome')">
<!--头部开始 -->
  <%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--头部tab选项卡开始-->
<div class="current-position-box">
	<div class="current-position">
         		<a class="posit-b" href="${pageContext.request.contextPath}">首页</a>
         		<i class="icon icon-angle-right"></i>
                <a class="posit-b" href="${pageContext.request.contextPath}/sst/toSSTHome.action"> 体系表</a>
                <i class="icon icon-angle-right"></i>
                <b> ${standardCategory==1?"贯彻执行法规查询":"适用执行法规查询"}</b>
    </div>
</div>
<!--头部tab选项卡结束-->
<!--头部tab选项卡内容开始-->
<div class="search-table">
    <div class="pane-box">
        <div class="pane" style="display:block;">            
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">
                    <ul class="sub">
                    <form action="${pageContext.request.contextPath}/lawslibrary/searchLawsLibrary.action" method="post" name="searchForm">
                    <input type="hidden" name="currentPage" id="currentPage"/>
                        <li>
                            <label>法规文号 :</label>
                            <input name="standardNo" id="standardNo" value="${standardNo}" class="search-1"/>
                            <label>法规名称 :</label>
                            <input name="standardName" id="standardName" value="${standardName}" class="search-2"/>
                            <label>分类 :</label>
                            <select class="search-4" style="width:180px;" name="standardCategory">
                                <option value="">全部法规</option>
                                <option value="1" ${standardCategory=="1"?"selected":""}>贯彻执行法规</option>
                                <option value="2" ${standardCategory=="2"?"selected":""}>适用执行法规</option>
                            </select>
                            <button id="search" type="button" onClick="submitForm(1)">查询</button>
                        </li>
                        </form>
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->
             <s:if test="lawsDataList != null ">
		<s:iterator value="lawsDataList" var="law">
			<div class="search-list ${law.effectiveState==0?'law-delete':'law'}">
				<s:if test="#law.effectiveState==0">
					<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>
				</s:if>
                <span></span>
                <ul class="text">
                    <li class="title" style="width:100%;">
                    	<a href="${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id=${law.standardId}" target="_blank" title="${law.standardName}">
                    		<i>${law.standardNo}</i>
                    		<i>${law.standardName}</i>
                    	</a>
                    </li>
                    <li class="text-other">
                    	<i class="fabu-time">发布时间：<em><em>${law.effectiveDate==""?"无":law.effectiveDate}</em></i>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>${law.browseVolume}</i></i>
                        <script>
                        var standardId=${law.standardId};
                        document.write('<a class="shoucang" id="'+standardId+'">');
                        if(isHaveThisOne(standardId)){
                        	document.write('<i class="icon icon-star"></i><i onclick="removeCookie('+standardId+')">已收藏</i>');
    					}else{
    						document.write('<i class="icon icon-star-empty"></i><i onclick="addCollect('+standardId+')" >加入收藏</i>');
    					}
                        document.write('</a>');
                        </script>
                        <a class="download" href="${pageContext.request.contextPath}/standardlibrary/batchDownload.action?key=${law.modifyerName}&url=${law.modifyer}" target="_blank"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
		</s:iterator>
				<!--fenye  开始 -->
			<s:if test="sumPage != null ">
				<s:if test="sumPage!=1">
                <div class="fenye">
                    <ul class="fenye-list">
                    	<li class="shangyiye" style="margin-left:0;"><a href="javascript:void(0);" onClick="submitForm(1)">首页</a></li>
                        <s:if test="currentPage!=1">
							<li class="shangyiye" style="margin-left:0;"><a href="javascript:void(0);" onClick="submitForm(${currentPage-1})">上一页</a></li>
						</s:if>
					<s:if test="%{currentPage<=5}">  
                        <s:if test="%{sumPage<=8}">  
                            	<s:bean name="org.apache.struts2.util.Counter" id="counter1">  
                            	<s:param name="first" value="1" />  
                            	<s:param name="last" value="sumPage" />  
                                	<s:iterator>  
                                		<li ${current-1==currentPage?"class='selected'":""}><a href="javascript:void(0);" onClick="submitForm(${current-1})">${current-1}</a></li>  
                                	</s:iterator>  
                            	</s:bean>  
                        </s:if>  
                        <s:elseif test="%{sumPage>8}">  
                            <s:bean name="org.apache.struts2.util.Counter" id="counter2">  
                            <s:param name="first" value="1" />  
                            <s:param name="last" value="8" />  
                            <s:iterator>  
                             	<li ${current-1==currentPage?"class='selected'":""}><a href="javascript:void(0);" onClick="submitForm(${current-1})">${current-1}</a></li>  
                            </s:iterator>  
                            </s:bean>  
                        </s:elseif>  
                    </s:if>  
					<s:else>
						<s:if test="%{currentPage+5<=sumPage}">  
							<s:bean name="org.apache.struts2.util.Counter" id="counter2">  
                            <s:param name="first" value="currentPage-4" />  
                            <s:param name="last" value="currentPage+5" />  
                            <s:iterator>  
                             	<li ${current-1==currentPage?"class='selected'":""}><a href="javascript:void(0);" onClick="submitForm(${current-1})">${current-1}</a></li>  
                            </s:iterator>  
                            </s:bean> 
						</s:if>
						<s:else>
							<s:bean name="org.apache.struts2.util.Counter" id="counter2">  
                            <s:param name="first" value="currentPage-9" />  
                            <s:param name="last" value="sumPage" />  
                            <s:iterator>  
                             	<li ${current-1==currentPage?"class='selected'":""}><a href="javascript:void(0);" onClick="submitForm(${current-1})">${current-1}</a></li>  
                            </s:iterator>  
                            </s:bean> 
						</s:else>
					</s:else>
                        <s:if test="currentPage-1<sumPage">
                        	<li class="shangyiye"><a href="javascript:void(0);" onClick="submitForm(${currentPage+1})">下一页</a></li>
                        </s:if>
                        <li class="shangyiye"><a href="javascript:void(0);" onClick="submitForm(${sumPage})">末页</a></li>
                    </ul>
                </div>
                </s:if>
             </s:if>
                <!--fenye  结束 --> 
	</s:if>
	<s:if test="lawsDataList == null ">
		<center><img src="${pageContext.request.contextPath}/images/noData.png"/></center>
	</s:if>
        </div>
    </div>
</div>
<!--头部tab选项卡内容结束-->
<iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</body>
</html>