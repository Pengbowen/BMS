<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
<meta name="viewport" content="width=auto, initial-scale=1.0, user-scalable=yes"/>
<meta name="mqq-bottom-ad" content="no" />
<meta content="telephone=no" name="format-detection" />
<title>freemarker Demo</title>
<link  href="${base}/css/zui.css" type="text/css" rel="stylesheet"/>
<link  href="${base}/css/list-1.css" type="text/css" rel="stylesheet"/>
<script src="${base}/scripts/json2.js" type="text/javascript"></script>
<script src="${base}/js/jquery.js" type="text/javascript"></script>
<script src="${base}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${base}/js/jqPaginator.min.js" type="text/javascript"></script>

<script type="text/javascript">
			var rootPath = "${base}/";
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
<!--头部tab选项卡开始-->
<div class="tabPanel"></div>
<!--头部tab选项卡结束-->
<!--头部tab选项卡内容开始-->
<div class="search-table">
    <div class="pane-box">
        <div class="pane" style="display:block;">
            <div class="tab-posit-cont">
                <a class="posit-b" href="${base}">首页</a>
                <i class="icon icon-angle-right" style="color:#fff;"></i>
                <a class="posit-b" href="${base}/sst/toSSTHome.action"> 体系表</a>
                <i class="icon icon-angle-right"></i>
                <b> 12345</b>
            </div>
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">
                    <h3 style="cursor: pointer;">
                       	 查询条件
                        <i class="icon icon-chevron-up" id="icon"></i>
                    </h3>                
                    <ul class="sub">
                    <form action="${base}/lawslibrary/f.action" method="post" name="searchForm">
                    <input type="hidden" name="currentPage" id="currentPage"/>
                        <li>
                            <label>法规文号 :</label>
                            <input name="standardNo" value="" class="search-1"/>
                            <label>法规名称 :</label>
                            <input name="standardName" value="" class="search-2"/>
                            <label>分类 :</label>
                            <select class="search-4" style="width:180px;" name="standardCategory"  >
                                <option value="">全部法规</option>
                                <option value="1">贯彻执行法规</option>
                                <option value="2">适用执行法规</option>
                            </select>
                        </li>
                        <li class="search-btn">
                            <button id="search" type="button" onClick="submitForm(1)">查询</button>
                        </li>
                        </form>
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->
            <#if lawsDataList??>
            <#list lawsDataList as law>
            <div class="search-list <#if law.effectiveState==0>law-delete<#else>law</#if>">
				<#if law.effectiveState==0>
					<img src="${base}/image/zuofei-bg.png" class="ph-zuofei"/>
				</#if>
                <span></span>
                <ul class="text">
                    <li class="title">
                    	<a href="${base}/standardlibrary/toStandardDetail.action?id=${law.standardId}" target="_blank" title="${law.standardName}">
                    		<i>${law.standardNo}</i>
                    		<i>${law.standardName}</i>
                    	</a>
                    </li>
                    <li class="text-other">
                        <a class="tidai"><em>实施时间：</em><em><#if law.effectiveDate=="">无<#else>${law.effectiveDate}</#if></em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>${law.browseVolume}</i></i>
                        <script>
                        var standardId=${law.standardId};
                        document.write('<a class="shoucang" id="'+standardId+'">');
                        if(isHaveThisOne(standardId)){
                        	document.write('<i class="icon icon-star"></i><i onclick="removeCookie('+standardId+')">已收藏</i>');
    					}else{
    						document.write('<i class="icon icon-star-empty"></i><i onclick="addCollect('+standardId+')">加入收藏</i>');
    					}
                        document.write('</a>');
                        </script>
                        <a class="download" href="${base}/standardlibrary/batchDownload.action?key=${law.modifyerName}&url=${law.modifyer}" target="_blank"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
		 </#list>
	</#if>
        </div>
    </div>
</div>
<!--头部tab选项卡内容结束-->
<iframe src="${base}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</body>
</html>