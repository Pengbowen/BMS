<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通知公告页面</title>
<link  href="${pageContext.request.contextPath}/css/sub-style2.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet" />
<link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/laypage-v1.3/laypage/laypage.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>

</head>

<body>
<!--头部开始 -->
  <%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
         <a class="posit-a" href="/ESMIS">首页</a> 
         <i class="icon icon-angle-right"></i> 
         <b id="title">通知公告</b>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="search-table">
	<div class="pane-box">
	   <div  class="pane" style="display:block;padding-top:0;">
			<div class="a-n-cont">
				<ul class="a-n-list" id="list">
				</ul>
				<div class="fenye" id="listPage"></div>
			</div> 
		</div>
	</div> 
</div>
<!--content结束 -->
<!--底部开始 -->
    <iframe src="../footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	function detail(id){
		var url = rootPath + "notice/skipToDetailForFront.action?id="+id;
		window.open(url);
	}

	//以下将以jquery.ajax为例，演示一个异步分页
	function loadPage(curr){
		var linesOfPage = 10;
		curr = curr || 1;
		var url = rootPath+'notice/searchNoticeByConditionAndPage.action';
		url += (url.indexOf("?") > 0) ? "&" : "?";
		url += "currentPage=" + curr;
		url += "&linesOfPage=" + linesOfPage;
		AjaxJson(url, true, null, function(data){
			var num = data.datalist.length;
			var html = '';
			for(var i=0; i<num; i++){
				var id = data.datalist[i].id;
				//标题
				var title = data.datalist[i].noticeTitle;
				//时间
				var time = data.datalist[i].publishTime.substring(0,10);
				//内容
				var content = data.datalist[i].noticeContent;
				
				html += '<li><div class="a-n-list-title"><a onclick="detail(\''+id+'\')" href="javascript:vido(0);">'+title+'</a></div>';
				html += '<div class="a-n-list-cont"><a onclick="detail(\''+id+'\')" href="javascript:vido(0);">'+content+'</a></div>';
				html += '<div class="a-n-list-time">'+time+'</div>';
				html += '</li>';
			}
			document.getElementById('list').innerHTML = html;
			laypage({
	            cont: 'listPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
	            pages: data.pageCount, //通过后台拿到的总页数
	            curr: curr, //当前页
	            skin: '#50a7ef',
	            skip: true,
	            first: '首页',
	            last: '共'+data.pageCount+'页',
	            jump: function(obj, first){ //触发分页后的回调
	                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                    loadPage(obj.curr);
	                }
	            }
       	 	});
		});
	}	
	//运行
	$(document).ready(function() {loadPage();});
</script>
</html>