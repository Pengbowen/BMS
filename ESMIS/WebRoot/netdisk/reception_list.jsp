<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>知识库列表</title>
<link type="text/css" href="${pageContext.request.contextPath}/style/add-style.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/css/sub-style2.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/netdisk/scripts/menu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/netdisk/style/receptionnetdisk.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/netdisk/style/lanrenzhijia.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/netdisk/scripts/jquery.tipsy.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/netdisk/scripts/operate.js" type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	function JSLoadData(datamin,urlData){
		$.ajax({
			type: "POST",
			global: false,
			dataType:"json",
            url: rootPath+urlData,
            data: datamin,
            success:function(datalist)
            {
            	var element="";
            	//图片类型
            	var pageType="";
            	//从后台接收到的数据
            	var myData = datalist.datalist;
            	if(myData!=null){
	            	var parentData = myData[0];
	            	var parentobjectNoMin = parentData.parentobjectNo;
            	}else{
            		showTips("未查询到相关文件",200,1);
            	}
            	var catalogmin = datalist.catalog;
            	//console.log(catalogmin);
            	//填充数据展现列表页面
				element += '<div class="search-table" >';
				element += '<div class="pane-box" style="padding:20px;">';
			
            	element += '<div id="dataload" class="pane" style="display:block;background-color:#fff;border:solid 1px #ddd;padding-top:0;">';
                	element += '<div class="add-knowledge" style="width:1000px;padding:0;">';
                		element += '<div class="add-position" style="width:1000px;">';
                			element += '<div class="add-position-pos" style="padding-left:0;>';
                				element += '<ol class="breadcrumb" style="padding:0">';
                					element += '<li style="padding-top:10px;padding-left:20px;float:left;"><a onclick="JSLoadData({parentobjectNo:100},\'netdisk/showReception.action\')"><i style="color:#666666;font-size:18px;" title="知识库" class="icon icon-window-alt"></i></a></li>';
                					element += catalogmin;
                				element += '</ol>';
                			element += '</div>';
                			element += '<div class="add-position-search">';
                				element += '<input type="button" class="btn  btn-primary" style="float:right;width:80px;height:32px;border-top-left-radius:0;border-bottom-left-radius:0;margin-right:20px;" value="搜索" onclick="searchByObjectName();"/>';
                			element += '</div>';
                			element += '<div class="add-position-search">';
                				element += '<input type="text" id="objectName" inQuery="true" operator="like" value="请输入文件名称" onkeydown="if(event.keyCode==13)searchByObjectName()" onfocus="javascript:if(this.value==\'请输入文件名称\')this.value=\'\';" class="form-control form-focus" style="float:right;height:32px;border-top-right-radius:0;border-bottom-right-radius:0;padding: 0;margin: 0;text-align: left;text-indent: 3px;"/>';
                			element += '</div>';
                		element += '</div>';
                	element += '</div>';
                element += '<span style="padding-top:350px;font-size:30px;text-align:center;margin-left:auto;margin-right:auto;display:none;width:600px;height:374px;background:url(${pageContext.request.contextPath}/images/nofile.png)" id="messager">${messager}</span>';
                element += '<input id="parentobjectNo" type="hidden" value="'+parentobjectNoMin+'">';
                element += '<div id="myHtml" class="alldom" style="height:500px;width:960px; overflow:hidden; overflow-y:visible">';
            	if(myData!=null){
            		for(var i=0;i<myData.length;i++){
                		var data =myData[i];
                		var objectName = data.objectName;
                		var objectSuffix = data.objectSuffix;
						switch (objectSuffix) {
						case "doc":
							pageType = "word";
							break;
						case "docx":
							pageType = "word";
							break;
						case "xls":
							pageType = "excel";
							break;
						case "xlsx":
							pageType = "excel";
							break;
						case "pdf":
							pageType = "pdf";
							break;
						case "ppt":
							pageType = "ppt";
							break;
						case "rar":
							pageType = "rar";
							break;
						case "txt":
							pageType = "txt";
							break;
						default:
							pageType = "file";
						}
						var objectNo = data.objectNo;
						var objectName = data.objectName;
                		var parentobjectNo = data.parentobjectNo;
                		var chirldItemCount = data.chirldItemCount;
                		var objectType = data.objectType;
                		//数据展现
                		element +='<ul id="divall" onmouseover="getParams(\''+objectNo+'\',\''+objectName+'\',\''+parentobjectNo+'\',\''+chirldItemCount+'\',\''+objectType+'\');">';
                    	element += '<input type="hidden" value="'+objectSuffix+'"/>';
                    	 	element += '<input type="hidden" value="'+objectNo+'"/>';
                    	 	element += '<input type="hidden" value="'+parentobjectNo+'"/>';
                    	 	element += '<input type="hidden" value="'+chirldItemCount+'"/>';
                    	 	element += '<input type="hidden" value="'+objectType +'"/>';
                    	 	element += '<li title="'+objectName +'" style="background:url(${pageContext.request.contextPath}/netdisk/style/'+pageType+'.png) center top no-repeat;" class="changeColor">';
                    	 	element += '<input type="text" disabled="true" id="objectName" class="changename" value="'+objectName+'"/>';
                    	element += '</li></ul>';
                	}
            		element += '</div><div style="clear:both;"></div>';
            		element += '</div></div></div>';
            		element += '<div class="menu-zdy" id="menu">';
            		element += '<div class="menu-one" style="display:none" id="folder">';
            		element += '<a id="open" onclick="openFolder()">打开文件夹</a>';
            		element += '</div>';
            		element += '<div class="menu-one" id="file">';
            		element += '<a onclick="Download()" href="javascript:void(0);">下载文件</a>';
            		element += '</div>';
            		element += '</div>';
                	//先清空数据的div
               		$("#JSLoadData").html("");
                	//再对其赋值
           	      	$("#JSLoadData").html(element);
            	}else{
            		//alert("该文件夹中没有数据");
            		//showTips("该文件夹中没有文件",500,1);
            	}
            }
		});
	}
	//右键打开文件夹
	function openFolder(){
		JSLoadData({parentobjectNo:iobjectNo,objectName:iobjectName},"netdisk/showReception.action");
	}
	$(function() {
		//加载数据
		var parentobjectNo = $("#parentobjectNo").val();
		JSLoadData({parentobjectNo:parentobjectNo},"netdisk/showReception.action");
		//判断结果是否为空
		var dd = '${messager}';
		if(dd!=""){
			var ui = document.getElementById("messager"); 
			ui.style.display="block";
		}
	    //去掉默认的contextmenu事件，否则会和右键事件同时出现。
	    document.oncontextmenu = function(e) {
	        e.preventDefault();
	    };
	    
	    var $parent = $('#divall'),
	    $bgcolor = $('#divall li'),
	    $namehide = $('#divall li input.changename');
	    //设置input为禁用状态
	    $namehide.attr({"disabled":"true"});
	    //双击事件
        $bgcolor.live('dblclick',function() {
       		$(".changeColor").css('background-color', 'white');
			$(this).css('background-color', '#EEE0E5');
			if(iobjectType==0){
				var URL = rootPath +'netdisk/downloadNetDisk.action?objectNo='+iobjectNo;
				window.location.href = URL;
				//setTimeout(showTips("文件已下载",2000,1),500);
			}else{
				JSLoadData({parentobjectNo:iobjectNo,objectName:iobjectName},"netdisk/showReception.action");
			}
        });
	    //左键点击事件变色
	    $bgcolor.live('mousedown',function(event) {
	    	if(event.button==0){
				var thisli = $(this);    		
	    		//单击事件给点击的文件付样式
	    		$(".changeColor").css('background-color', 'white');
	    		thisli.css('background-color', '#EEE0E5');
	    	}else if(event.button==1){
	    		alert("无鼠标中轴事件！");
	    	}else{
	    		$(".changeColor").css('background-color', 'white');
				$(this).css('background-color', '#EEE0E5');
			    //siblings() 获得匹配集合中每个元素的同胞，通过选择器进行筛选是可选的。
			    //.siblings() 方法允许我们在 DOM 树中搜索这些元素的同胞元素，并用匹配元素构造一个新的 jQuery 对象。
			      $(this).addClass('bgclocrc').siblings().removeClass('bgclocrc');
			        $(this).attr("id", 'remove').siblings().attr('id', '');
			        //右键菜单
			        var container = document.getElementById('remove');
			        //console.log(container);
			        var menu = document.getElementById('menu');
			        /*显示菜单*/
			        function showMenu() {
			            var evt = window.event || arguments.callee.caller.arguments[0];
			            /*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
			            var rightedge = container.clientWidth - evt.clientX;
			            var bottomedge = container.clientHeight - evt.clientY;
			            /*如果从鼠标位置到容器右边的空间小于菜单的宽度，就定位菜单的左坐标（Left）为当前鼠标位置向左一个菜单宽度*/
			            if (rightedge < menu.offsetWidth) menu.style.left = container.scrollLeft + evt.clientX - menu.offsetWidth + "px";
			            else
			            /*否则，就定位菜单的左坐标为当前鼠标位置*/
			            menu.style.left = container.scrollLeft + evt.clientX + "px";
			            /*如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度*/
			            if (bottomedge < menu.offsetHeight) menu.style.top = container.scrollTop + evt.clientY - menu.offsetHeight + "px";
			            else
			            /*否则，就定位菜单的上坐标为当前鼠标位置*/
			            menu.style.top = container.scrollTop + evt.clientY + "px";
			            
						if(iobjectType==1){
							//打开文件夹，隐藏下载
							$("#folder").css('display','block');
							$("#file").css('display','none');
						}else{
							//显示下载
							$("#folder").css('display','none');
							$("#file").css('display','block');
						}
			            /*设置菜单可见*/
			            menu.style.display = "block";
			            LTEvent.addListener(menu, "contextmenu", LTEvent.cancelBubble);
			        }
			        /*隐藏右键菜单*/
			        function hideMenu() {
			            menu.style.display = 'none';
			        }
			        showMenu();
			        LTEvent.addListener(container, "contextmenu", LTEvent.cancelBubble);
			        LTEvent.addListener(container, "contextmenu", showMenu);
			        LTEvent.addListener(document, "click", hideMenu);
	    	}
	    });
	        return true;
	 });
	
</script>
</head>


<body onload="change('netdisk')">
<!--头部开始 -->
<%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">       
		<a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
        <i class="icon icon-angle-right"></i>
        <b> 知识库</b>
    </div>
</div>
<!--banner结束 -->
<!-- 利用前端JS生成 -->
<div id="JSLoadData"></div>
<!--底部开始 -->
<iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>
