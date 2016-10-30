<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>收藏页面</title>
    <link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
    <link  href="${pageContext.request.contextPath}/css/my.css" type="text/css" rel="stylesheet" />
    
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
    
    <!-- EasyUI -->
	<link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/easyui/jquery.min.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js" type="text/javascript" ></script>
	    
    <script type="text/javascript">
    	var rootPath = "${pageContext.request.contextPath}/";
    	var c_name = "collect";
    	$(function(){
    		ssss();
    	});
    	function ssss(){
    		var url = rootPath + "standardlibrary/searchMoreStandard.action";
    		var cookieValue = getCookie();
    		if(cookieValue == ""){
	    		var html = '';
	    		html += '<ul class="collect-title">';
	    		html += '<li><input type="checkbox" id="allbox" style="margin:15px 0px 0px 10px;"/>&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;</li>';
	    		html += '<li><a href="javascript:void(0);"><i class="icon icon-download-alt" style="font-size:20px;"></i></a><a href="javascript:void(0);"onclick="batchDownload()">下载&nbsp;&nbsp;&nbsp;&nbsp;</a> </li>';
	    		html += '<li><a href="javascript:void(0);"><i class="icon icon-trash" style="font-size:20px;"></i></a><a href="javascript:void(0);" onclick="batchRemove()">&nbsp;&nbsp;批量删除</a> </li>';
	    		//html += '<li><a href="javascript:void(0);"><i class="icon icon-trash"></i></a><a href="javascript:void(0);" onclick="addCollect(110000001)">&nbsp;&nbsp;&nbsp;&nbsp;写cookie(仅限测试用)</a> </li>';
	    		html += '</ul>';
	    		html += '<img src="${pageContext.request.contextPath}/images/noData.png"/>';
	    		$("#html").html(html);
    			return;
    		} 
    		var strData = "id=" + cookieValue;
    		AjaxJson(url, true, strData, serach);
    	}
    	function serach(json){
    		var recordCount = json.recordCount;
    		var html = '';
    		html += '<ul class="collect-title">';
    		html += '<li><input type="checkbox" id="allbox" style="margin:15px 0px 0px 10px;"/>&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;</li>';
    		html += '<li><a href="javascript:void(0);"><i class="icon icon-download-alt" style="font-size:20px;"></i></a><a href="javascript:void(0);"onclick="batchDownload()">下载&nbsp;&nbsp;&nbsp;&nbsp;</a> </li>';
    		html += '<li><a href="javascript:void(0);"><i class="icon icon-trash" style="font-size:20px;"></i></a><a href="javascript:void(0);" onclick="batchRemove()">&nbsp;&nbsp;批量删除</a> </li>';
    		//html += '<li><a href="javascript:void(0);"><i class="icon icon-trash"></i></a><a href="javascript:void(0);" onclick="addCollect(110000001)">&nbsp;&nbsp;&nbsp;&nbsp;写cookie(仅限测试用)</a> </li>';
    		html += '</ul>';
    		for(var i=0; i<recordCount; i++){
    			var dataThis = json.datalist[i];
    			var detaileUrl = "${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id="+dataThis.id;
    			html += '<ul class="collect-text">';
    			html += '<li class="text-left">';
    			html += '<input type="text" style="display:none;" id="id'+i+'" value="'+dataThis.id+'"/>';
    			html += '<input type="text" style="display:none;" id="key'+i+'" value="'+dataThis.key+'"/>';
    			html += '<input type="text" style="display:none;" id="url'+i+'" value="'+dataThis.strUrl+'"/>';
    			if(dataThis.oldStandardNo == "" || dataThis.oldStandardNo == null){
    				html += '<input style="text-algin:16px;" type="checkbox" num="'+i+'" name="cbox"/><a href="javaScript:void(0);" onclick="window.open(&#34;'+detaileUrl+'&#34;)"><em>&nbsp;&nbsp;</em><em>'+dataThis.standardNo+'</em>';
    			}else{
    				html += '<input style="text-algin:16px;" type="checkbox" num="'+i+'" name="cbox"/><a href="javaScript:void(0);" onclick="window.open(&#34;'+detaileUrl+'&#34;)"><em>&nbsp;&nbsp;</em><em>'+dataThis.standardNo+'（替'+dataThis.oldStandardNo+'）</em>';
    			}
    			html += '&nbsp;&nbsp;&nbsp;&nbsp;<em>'+dataThis.standardName+'</em></li></a>';
    			html += '<li class="text-right"><a href="javascript:void(0);" num="'+i+'" title="下载" name ="download"><i style="font-size:20px;" class="icon icon-download-alt"></i></a>';
    			html += '<a href="javascript:void(0);" num="'+i+'" title="删除" name="delete"> <i style="font-size:20px;" class="icon icon-trash"></i></a></li></ul>';
    			
    			
    		}
    		$("#html").html(html);
    		listening();
    	}
    	
    	function batchRemove(){
    		$.messager.confirm('提示信息！','确认删除？',function(ok){
    			if(ok){
    				$("input[name=cbox]").each(function(){
		    			if(this.checked){
		    				var num = $(this).attr("num");
		    				var id = $("#id"+num).val();
		    				removeCookie(id);
		    			}
	    			});
	    			window.location.reload();
    			}
    		});
    		
    	}
    	
    	function batchDownload(){
    		var id = '';
    		var url = '';
    		$("input[name=cbox]").each(function(){
    			if(this.checked){
    				var num = $(this).attr("num");
    				var idVal = $("#key"+num).val();
    				var urlVal = $("#url"+num).val();
    				urlVal = urlVal.trim();
    				id += idVal + ",";
    				url += urlVal + ",";
    			}
    		});
    		id = id.substring(0,id.length-1);
    		url = url.substring(0,url.length-1);
    		var actionUrl = rootPath + "standardlibrary/batchDownload.action";
    		var strData = "key="+id+"&url="+url;
    		window.location.href=actionUrl+"?"+strData;
    		//AjaxJson(url,true,strData,message);
    	}
    	
    	function listening(){
    		$("[name=download]").click(function(){
    			var param = $(this).attr("num");
    			var key = $("#key"+param).val();
    			var url = $("#url"+param).val();
    			url = url.trim();
    			var actionUrl = rootPath + "standardlibrary/batchDownload.action";
    			var strData = "key="+key+"&url="+url;
    			window.location.href=actionUrl+"?"+strData;
    			//AjaxJson(actionUrl,true,strData,message);
    		});
    		
    		$("[name=delete]").click(function(){
    			var nowThis = this;
    			$.messager.confirm('提示信息！','确认删除？',function(r){
				    if (r){
						var param = $(nowThis).attr("num");
		    			var id = $("#id"+param).val();
		    			removeCookie(id);
		    			var value = getCookie();
		    			var param = value.indexOf(id);
		    			if(param == -1){
		    				$.messager.alert("提示信息！","删除成功！","",function(r){
		    					window.location.reload();
		    				});
		    			}else{
		    				$.messager.alert("提示信息！","删除失败！请稍后重试！");
	    				}
				    }
				});
    		});
    		
    		$("#allbox").click(function(){
    			if(this.checked){
    				$("input[name=cbox]").attr("checked",function(){
    					this.checked = true;
    				});
    			}else{
    				$("input[name=cbox]").attr("checked",function(){
    					this.checked = false;
    				});
    			}
    		});
    	}
    	
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
    	}
    	function delCookie() {
			var d = new Date();
			d.setTime(d.getTime() -1);
			var expires = "expires="+d.toUTCString();
			document.cookie = c_name + "=" + "" + "; " + expires;
		}
		
		function removeCookie(id){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '1');
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
			//window.location.reload();
		}
		
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
    	
    	function message(data){
		if (data.result == "1") {
			$.messager.alert("信息提示", data.message,'',function(r){
				top.refreshAppointTabs(rootPath+"notice/skipToNotice.action","通知公告");
			});
		}else{
			$.messager.alert("信息提示！", data.message);
		}
	}
    </script>
    
</head>

<body>
	<!--头部开始 -->
	<%@ include file="../header.jsp"%>
	<!--头部结束 -->
	<!--banner开始 -->
	<div class="current-position-box">
		<div class="current-position">       
		         <a class="posit-a" href="${pageContext.request.contextPath}">首页</a> 
		         <i class="icon icon-angle-right"></i> <b> 我的收藏 </b>
    	</div>
	</div>
	<div class="search-table">
		<div class="pane-box" style="padding-top:20px;padding-bottom:20px;">
			<!--banner结束 -->
			<!--content开始 -->
			<div class="collect-content">
				<div class="collect-box" style="margin-top:0;padding-top:20px;background-color:#fff;">
					<div id="html" style="text-align:center;"></div>
				</div>
			</div>
		</div>
	</div>
	<!--content结束 -->
	<!--底部开始 -->
	<iframe src="${pageContext.request.contextPath}/footer.html"
		frameborder="0" width="100%" height="120" scrolling="no"
		marginwidth="0" marginheight="0"></iframe>
	<!--底部结束 -->
</body>
</html>