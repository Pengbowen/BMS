<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>资源库</title>
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/templist.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/condjson.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/laypage-v1.3/laypage/laypage.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/laypage-v1.3/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		var rootPath = "${pageContext.request.contextPath}/";
		var json = '${str}';
		var data = eval ("(" + json + ")");
		var typeNum = "";
		var paramValue = "";
		var paramTilte = "";
		var listAdata = data.listA;
		$(function(){
			//专业
			var listAhtml = '';
			for(var i=0;i<listAdata.length;i++){
				listAhtml += '<a href="javaScript:void(0);"'
				+' onclick="getData(1,1,&#34;'+listAdata[i].applicableMajor+'&#34;,&#34;'+listAdata[i].applicableMajorName+'&#34;)">&nbsp;&nbsp;&nbsp;&nbsp;'
				+listAdata[i].applicableMajorName+'</a>';
			}
			$("#listA").html(listAhtml);
			
			//类别
			var listCdata = data.listC;
			var listChtml = '';
			for(var i=0;i<listCdata.length;i++){
				listChtml += '<a href="javaScript:void(0);"'
				+' onclick="getData(1,2,&#34;'+listCdata[i].standardCategory+'&#34;,&#34;'+listCdata[i].standardCategoryName+'&#34;)">&nbsp;&nbsp;&nbsp;&nbsp;'
				+listCdata[i].standardCategoryName+'</a>';
			}
			$("#listC").html(listChtml);
			//getData(1,4,1,"全部专业");
			
		});
		
		function searchData(){
			if(typeNum == "" || paramValue == "" || paramTilte == ""){
				alert("请先选择导航栏菜单！");return;
			}
			getData("",typeNum,paramValue,paramTilte);
		}
		
		function getData(curr,type,value,title){
		//type  1 专业  2类别  3法律法规
			//if(curr == 1)$("#standardNo").val("");$("#standardName").val("");
			$("#zyzh").hide();
			$("#zytitle").show();
			if(type != 4 || curr != 1)pageScroll();
			layer.load(1);
			typeNum = type;
			paramValue = value;
			paramTilte = title;
			//图片文本
			var text = "";
			var tidai = "标准";
			var param = "";
			if(type == 1){//专业
				param += '{"setid":"","mappingid":"applicableMajor","value":"'+value+'","operator":"like","connector":"","type":"0"},';
				param += '{"setid":"","mappingid":"documentType","value":"1","operator":"=","connector":"","type":"0"}';
				$("#zhuanye").html(title);
				if(curr == 1 )
				{
					var str = '<div class="form-group" style="padding-right:20px;"> ';
					str += '<select name="select" id="applicableMajor" inQuery="true" class="form-control" style="width:254px;float:left;height:30px;margin-right:24px;">';
					str += '<option value="-1">请选择专业类别</option>';
					var listCdata = data.listC;
					for(var i=0;i<listCdata.length;i++){
						str += '<option value="'+listCdata[i].standardCategory+'">'+listCdata[i].standardCategoryName+'</option>';
					}
					str += '</select> ';
					str += '<input type="button" class="btn btn-primary"  style="width:80px;background:#50a7ef;border:none;float:left;" onclick="searchData()" value="查询"></input>';
					$("#myform").html(str);
				}
			}
			if(type == 2){//类别
				param += '{"setid":"","mappingid":"standardCategory","value":"'+value+'","operator":"=","connector":"","type":"0"},';
				param += '{"setid":"","mappingid":"documentType","value":"1","operator":"=","connector":"","type":"0"}';
				$("#zhuanye").html(title);
				if(curr == 1 )
				{
					var str = '<ul><li style="float:left;line-height:30px;"><i class="major" style="padding-right:20px;"><em>已选专业：</em><em id="professionalName">全部专业</em><input type="text" id="applicableMajor" style="display:none" inQuery="true"/></i>';
					str += '<input type="button" id="parentIframe" onclick="layOpen()" value="筛选专业"/></li>';
					str += '<li style="float:right;line-height:30px;""><input type="button" class="btn btn-primary"  style="width:80px;background:#50a7ef;border:none;" onclick="searchData()" value="查询"></input></li>';
					str += '</ul>';
					$("#myform").html(str);
				}
				
			}
			if(type == 3){//法律
				param += '{"setid":"","mappingid":"standardCategory","value":"'+value+'","operator":"=","connector":"","type":"0"},';
				param += '{"setid":"","mappingid":"documentType","value":"2","operator":"=","connector":"","type":"0"}';
				if(value == 1){
					$("#zhuanye").html("贯彻执行的法律法规");
				}else{
					$("#zhuanye").html("适用执行的法律法规");
				}
				tidai = "法规";
				if(curr == 1 )
				{
					var str = '<div class="form-group" style="padding-right:20px;"><label>文号：</label> <input class="form-control" type="text" style="width:140px;" id="standardNo" inQuery="true"/></div>';
					str += '<div class="form-group" style="padding-right:20px;"><label>名称：</label><input class="form-control" type="text" style="width:180px;" id="standardName" inQuery="true" operator="like"/></div>';
					str += '<input type="button" class="btn btn-primary"  style="width:80px;background:#50a7ef;border:none;" onclick="searchData()" value="查询"></input>';
					$("#myform").html(str);
				}																				
			}
			if(type == 4){//默认加载
				param += '{"setid":"","mappingid":"documentType","value":"1","operator":"=","connector":"","type":"0"}';
				$("#zhuanye").html(title);
				$("#bianhao").html("标准编号：");
				$("#mingcheng").html("标准名称：");
			}
			/* var zName = $("#zName").val();
			if(typeof(zName)=="undefined")
			{
				zName="";
			} else{
				zName= encodeURIComponent(encodeURIComponent(zName));
			} */
			var linesOfPage = 20;
			curr = curr || 1;
			var zuofei = 0;
			if(document.getElementById("zuofei").checked){
				zuofei = 1;
			}
			var url = rootPath + "/standardlibrary/searchStandard.action?zuofei="+zuofei;
			url += (url.indexOf("?") > 0) ? "&" : "?";
			url += "currentPage=" + curr;
			url += "&linesOfPage=" + linesOfPage;
			var strData = getParameter();
			if("=" == (strData.substring(strData.length-1))){
				strData += "["+ param +"]";
			}else{
				strData = strData.substring(0,strData.length-1);
				strData += ","+ param +"]";
			}
			AjaxJson(url, true, strData, function(data){
				var dataLength = data.datalist.length;
				var html = '';
				if(data.result == 2){
					html += '<div class="nnone">';
					html += '<b class="nnone-img1"><img src="${pageContext.request.contextPath}/image/nnone1.png" alt=""/></b>';
					html += '<b class="nnone-text">';
					if($("#standardNo").val() == "" && $("#standardName").val() == "" ){
						html += "该分类暂时查询不到数据！";
					}else{
						html += "该分类暂时查询不到数据，请选择在其它分类中查询！";
					}
					html += '</b></div>';
				}
				for(var i=0; i<dataLength; i++){
					var standardId = data.datalist[i].standardId;
					//浏览量
					var browseVolume = data.datalist[i].browseVolume;
					//替代标准id
					var oldStandardId = data.datalist[i].oldStandardId; 
					//替代标准no
					var oldStandardNo = data.datalist[i].oldStandardNo;
					//作废标记
					var effectiveState = data.datalist[i].effectiveState;
					//类别
					var standardCategory = data.datalist[i].standardCategory;
					//编号
					var standardNo = data.datalist[i].standardNo;
					//名称
					var standardName = data.datalist[i].standardName;
					//加密key
					var key = data.datalist[i].key;
					//加密url
					var strUrl = data.datalist[i].str;
					//发布时间
					var approvedDate = data.datalist[i].approvedDate;
					//实施时间
					var effectiveDate = data.datalist[i].effectiveDate;
					//下载url
					var downloadUrl = rootPath + "standardlibrary/batchDownload.action?key="+key+"&url="+strUrl;;
					//详情url
					var detailUrl = "${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id="+standardId;
					//替代详情url
					var oldDetailUrl = "${pageContext.request.contextPath}/standardlibrary/toStandardDetail.action?id="+oldStandardId;
					
					var num = "";
					
					if(effectiveState == 0){
						if(type != "3"){
							num = "fei";
						}else{
							num = " law-delete";
						}
					}else{
						if(type != "3"){
							num = ascii(standardNo)%4;
							if(num == 0) num = 4;
							if(num == 1) num = 7;
							if(num == 2) num = 17;
							if(num == 3) num = 19;
						}else{
							num = "laws";
						}
					}
					html += '<div class="search-list bz-'+num+'" style="width:740px;height:93px;padding:16px 0;">';
					if(type == "3"){
						html += '<span></span>';
					}else{
						html += '<span>'+standardCategory+'</span>';
					}
					if(effectiveState == 0){
						html += '<img src="${pageContext.request.contextPath}/image/zuofei-bg.png" class="ph-zuofei"/>';
					}
					html += '<ul class="text"  style="width:695px;">';
					//标题列
					html += '<li class="title" style="padding-top:6px;width:680px;"><a title="'+standardNo+' '+standardName+'" href="javaScript:void(0);" style="width: 420px;" class="title-1" onclick="window.open(&#34;'+detailUrl+'&#34;)">'+standardNo+' '+standardName+'</a>';
					//下载
					html += '<a class="download" href="'+downloadUrl+'" style="width:70px;"><i class="icon icon-download"></i><i>下载</i></a>';
					//收藏
					html += '<a class="shoucang" id="'+standardId+'"  style="width:70px;">';
					if(isHaveThisOne(standardId)){
						html += '<i class="icon icon-star" ></i><i onclick="removeCookie('+standardId+')">已收藏</i>';
					}else{
						html += '<i class="icon icon-star-empty"></i><i onclick="addCollect('+standardId+')">收藏</i>';
					}
					html += '</a>';
					//浏览量
					html += '<i class="liulan" style="width:70px;"><i class="icon icon-eye-open"></i><i>'+browseVolume+'</i></i>';
					html += '</li>';
					//第二列
					html += '<li class="text-other" style="width: 680px;">';
					//1发布时间  实施时间
					html += '<i class="fabu-time">发布时间：<em>'+approvedDate+'</em></i><i class="shishi-time">实施时间：<em>'+effectiveDate+'</em></i>';
					//替代
					if(oldStandardId != null && oldStandardId != "" && oldStandardNo != null){
						html += '<i class="tidai">注：该'+tidai+'已被<a href="javascript:vido(0);" class="title-2" onclick="window.open(&#34;'+oldDetailUrl+'&#34;)"><em>'+oldStandardNo+'</em></a>取代</i>';
					}
					html += '</li>';
					html += '</ul>';
					html += '<div class="clearfix"></div>';
					html += '</div>';
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
		                    getData(obj.curr,type,value);
		                }else{
		                	layer.closeAll('loading');
		                }
		            }
	       	 	});
			});
				
		}
		
		function ascii(str){
	      var a = "A".charCodeAt(); 
	      var param = str.charCodeAt(0); 
	      var i = (param - a)+1;
	      return i;
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
		
		function pageScroll(){ 
			var param = document.documentElement.scrollTop+document.body.scrollTop-121;
		
			//把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数） 
			window.scrollBy(0,-param); 
			layer.closeAll('loading');
			//延时递归调用，模拟滚动向上效果 
			//scrolldelay = setTimeout('pageScroll()',100); 
			//获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值 
			//var sTop=document.documentElement.scrollTop+document.body.scrollTop; 
			//判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面） 
			 //layer.closeAll('loading'); 
		} 
		
		function layOpen(){
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
		}
		
	</script>

</head>

<body onload="change('sourceList')">
<!--头部开始 -->
<%@ include file="../../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
         <a class="posit-a" href="javascript:void(0);" onclick="pageScroll()">首页</a>
         <i class="icon icon-angle-right"></i>
         <b> 资源库</b>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="search-table">
<div class="pane-box" style="padding-bottom:20px;padding-top:20px;">
   <div  class="pane" style="display:block;padding-top:16px;background-color:#fff;border:solid 1px #dddddd;padding-left:20px;margin-bottom:20px;">
   		<!-- left-->
   		<div class="a-resour-l menu_list" id="firstpane">
        	<p class="menu_head menu_head_noselscted">标准专业</p>
            <div style="display:none" class="menu_body" id="listA">
            </div>
            <p class="menu_head menu_head_noselscted">标准类别</p>
            <div style="display:none" class="menu_body" id="listC">
            </div>
            <p class="menu_head menu_head_noselscted">法律法规</p>
            <div style="display:none" class="menu_body" >
              <a href="javascript:void(0);" onclick="getData(1,3,1)">贯彻执行</a>
              <a href="javascript:void(0);" onclick="getData(1,3,2)">适用执行</a>
            </div>
        </div>
        <!-- right-->
        <div class="a-resour-r">
        	<div class="a-resour-r-search menu_list" id="firstpane1">
            	<p class="menu_head1" style="display: none;" id="zytitle"><i id="zhuanye" >全部专业</i></p>
                <div style="display:none;" class="menu_body1" >
                  <form class="form-inline" id="myform">
                   </form>
                   <input type="checkbox" id="zuofei"/>显示作废信息
                </div>
                <div class="zyzh" id="zyzh">
				资源库包含标准、法律法规两种资源。<br/>
				页面提供多种分类方式查询资源。<br/>
				可点击左侧分类查询资源！
                </div>
            </div>
            	<!-- 列表 开始 -->
            	<div class="a-resour-r-cont" id="list"></div>
                <!--fenye  开始 -->
                <div class="fenye" id="listPage"></div>
                <!--fenye  结束 -->
            </div>
            <div class="clearfix"></div>
   </div>
</div>
</div>
<!--content结束 -->
<!--底部开始 -->
<iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
<script type="text/javascript">
$(document).ready(function(){
	//$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane p.menu_head").click(function(){
		pageScroll();
		var state = $(this).hasClass("a-resor-l-title");
		if(state){
			$(this).toggleClass("a-resor-l-title").next("div.menu_body").slideToggle(300);
		}else{
			$(this).siblings(".a-resor-l-title").next("div.menu_body").slideToggle(300);
			$(this).siblings(".a-resor-l-title").removeClass("a-resor-l-title");
			$(this).toggleClass("a-resor-l-title").next("div.menu_body").slideToggle(300);
		}
	});
	/* $("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){alert(111);
		$(this).addClass("a-resor-l-title").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("a-resor-l-title");
	}); */
	$("#firstpane .menu_body a").click(function(){
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
	});
	$("#firstpane .menu_body a").mouseover(function(){
		$(this).addClass("moveSelected");
		$(this).siblings().removeClass("moveSelected");
	}); 
	
});

$(document).ready(function(){
	//查询条件框 默认展开
	//$("#firstpane1 .menu_body1:eq(0)").show();
	$("#firstpane1 p.menu_head1").click(function(){
		$(this).toggleClass("a-resour-r-search-t").next("div.menu_body1").slideToggle(300);
	});
	/* $("#secondpane1 .menu_body1:eq(0)").show();
	$("#secondpane1 p.menu_head1").mouseover(function(){
		$(this).addClass("a-resour-r-search-t").next("div.menu_body1").slideDown(500).siblings("div.menu_body1").slideUp("slow");
		$(this).siblings().removeClass("a-resour-r-search-t");
	}); */
});
</script>
</html>

