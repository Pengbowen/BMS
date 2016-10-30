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
<link type="text/css" href="${pageContext.request.contextPath}/style/css/zui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/netdisk/scripts/menu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/netdisk/style/netdisk.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/commonUpload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/netdisk/style/lanrenzhijia.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/netdisk/scripts/jquery.tipsy.js" type="text/javascript"></script>


<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var filters = "{title:\"文档\",extensions:\"doc,docx,xls,xlsx\"}";
	$(function() {
		var parentobjectNo = $("#parentobjectNo").val();
		//判断结果是否为空
		var dd = '${messager}';
		if(dd!=""){
			var ui = document.getElementById("messager"); 
			ui.style.display="block";
		}
		//下载失败时返回图片信息
		var downloadError = '${errorMessage}';
		if(downloadError != ""){
			//var ui = document.getElementById("messager"); 
			//ui.style.display="block";
			alert(downloadError);
		}
	    //去掉默认的contextmenu事件，否则会和右键事件同时出现。
	    document.oncontextmenu = function(e) {
	        e.preventDefault();
	    };
	    var $parent = $('#divall'),
	    $bgcolor = $('#divall li'),
	    $carry = $('.carrynews'),
	    $removenews = $('.remove'),
	    $removeall = $('.removeall'),
	    $removeright = $('#removethispc'),
	    $namehide = $('#divall li input.changename'),
	    $changename = $('#changename');
	    $removenews.hide();
	    //工具栏新建文件夹
	    function getfilename() {
	        var files = $("input.changename");
	        var number = 0;
	        for (var i = 0; i < files.length; i++) {
	            if ($(files[i]).val().indexOf("新建文件夹") > -1) {
	                number = number + 1;
	            }
	        }
	        if (number > 0) {
	            return "新建文件夹" + number;
	        } else {
	            return "新建文件夹";
	        }
	    }
	    //live() 方法为被选元素附加一个或多个事件处理程序，并规定当这些事件发生时运行的函数。
	   	//通过 live() 方法附加的事件处理程序适用于匹配选择器的当前及未来的元素（比如由脚本创建的新元素）。
	    $carry.live('click',
	    function() {
	    	var name = getfilename();
	    	var parentobjectNo = $("#parentobjectNo").val();
	    	var objectType =1;
	    	var objectSuffix ="";
	        $parent.append("<li class='changeColor'><input type='text' \class='changename'\ value=" + name + "></input></li>");
	    	//新建文件夹
	        $.ajax({
    			type: "POST",
                url: rootPath+"netdisk/addParentObject.action",
                data: {parentobjectNo:parentobjectNo,objectType:objectType,objectName:name,objectSuffix:objectSuffix},
                success:function(data) {
                	//$("input").focus();
                	window.location.reload();
                }
    		});
	    });
	    //头部清空文件夹（暂不支持使用）
	    $removeall.live('click',
	    function() {
	        $.messager.alert("提醒！", '确认清空文件夹？', '',function(r){
	            $parent.empty();
	        });
	    });
	    
	    //双击事件
      	var TimeOut = null;
        $bgcolor.live('dblclick',function() {
        	$(".changeColor").css('background-color', 'white');
			$(this).css('background-color', '#EEE0E5');
			//获取选中的objectNo的值;  preVall()获得当前匹配元素集合中每个元素的前面的同胞元素
			var objectNo = $($(this).prevAll()[3]).val();
			var name = searcyObjectName(objectNo).objectName;
			var objectType = searcyObjectName(objectNo).objectType;
			var searchType = 0;
			if(objectType==0){
				var URL = rootPath +'netdisk/downloadNetDisk.action?objectNo='+objectNo;
				window.location.href = URL;
				setTimeout($.messager.show({
	                title: "操作提示",
	                msg: "文件已下载，完成后请注意保存！",
	                showType: 'slide',
	                timeout: 2000
	            }),5000);
				 
			}else{
				window.location.href=rootPath+"netdisk/show.action?parentobjectNo="+objectNo+'&objectName='+encodeURIComponent(encodeURIComponent(name))+'&searchType='+searchType +"&catalog="+encodeURIComponent(encodeURIComponent($("#catalog").val()));
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
	    		$.messager.alert("信息提示！","无鼠标中轴事件！");
	    	}else{
	    		//有延迟取值
	    		$(".changeColor").css('background-color', 'white');
    			$(this).css('background-color', '#EEE0E5');
	    		 //右键删除文件夹
			     btns02 = document.getElementById('removethispc');
			        $removenews.fadeIn(250);
			        //siblings() 获得匹配集合中每个元素的同胞，通过选择器进行筛选是可选的。
			        //.siblings() 方法允许我们在 DOM 树中搜索这些元素的同胞元素，并用匹配元素构造一个新的 jQuery 对象。
			        $(this).addClass('bgclocrc').siblings().removeClass('bgclocrc');
			        $(this).attr("id", 'remove').siblings().attr('id', '');
			        $("input[type=text]").attr("id", 'namecc').siblings().attr('id', '');
			        //hasClass()函数用于指示当前jQuery对象所匹配的元素是否含有指定的css类名。
			        //hasClass()函数的返回值是Boolean类型，返回表示是否包含指定css类名的boolean值，如果包含就返回true，否则返回false
			        //如果当前jQuery对象匹配多个元素，只要其中有任意一个元素含有指定的css类名，就返回true
			        //右键菜单中的删除功能
			        btns02.onclick = function() { //js 调用
			        	$.messager.confirm("提醒！", '确认删除？',function(r){
			            	if(r){if ($bgcolor.hasClass('bgclocrc')) {
			            		var li = getChangecolor();
			            		var inputs = $(li).prevAll();
			            		var objectNo = ($(inputs[3]).val());
			            		$.ajax({
			            			type: "POST",
			                        url: rootPath+"netdisk/deleteNetDisk.action",
			                        data: {objectNo:objectNo},
			                        success:function(data) {  
						                window.reload();
			                        }
			            		});
				            } else {
				                $.messager.alert("提醒！", '请选择文件！');
				            }
			            	}
			            });
			        };
			        //右键菜单
			        var container = document.getElementById('remove');
			        var menu = document.getElementById('menu');
			        /*显示菜单*/
			        function showMenu() {
			            var evt = window.event || arguments[0];
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
			            /*设置菜单可见*/
			            menu.style.display = "block";
			            //获取选中的文件var li = getChangecolor();
						var li = getChangecolor();
						var inputs = $(li).prevAll();
						//对象类型
						var objectNo = $($(this).prevAll()[3]).val();
						var fileType = searcyObjectName(objectNo).objectType;
			            //根据文件类型，显示是否可以执行下载文件  1文件夹，0文件
			            if(fileType==0){
			            	$("#download").css('display','block');
			            }else{
			            	$("#download").css('display','none');
			            }
			            LTEvent.addListener(menu, "contextmenu", LTEvent.cancelBubble);
			        }
			        /*隐藏菜单*/
			        function hideMenu() {
			            menu.style.display = 'none';
			        }
			        LTEvent.addListener(container, "contextmenu", LTEvent.cancelBubble);
			        LTEvent.addListener(container, "contextmenu", showMenu);
			        LTEvent.addListener(document, "click", hideMenu);
			        //右键功能
			        $changename.live('click',function() {
			        	var li = getChangecolor();
						var inputs = $(li).prevAll();
						//对象编号
						var objectNo = ($(inputs[3]).val());
						var objectNameFirst = searcyObjectName(objectNo).objectName;
			            if ($bgcolor.hasClass('bgclocrc')) {
			            	$('#remove').find('.changename').focus();
			                $('#remove').find('.changename').val(objectNameFirst);
			                $('#remove').find('.changename').css('border', '1px solid #FF0000');
			            } else {
			                $.messager.alert("提醒！", '请选择文件！');
			            }
			        });
	    	}
	    });
	    var objectName;
	    //修改文件名
	    $namehide.live('focus',function() {
			objectName=$(this).val();
	        $(this).css('border', '1px solid #FF0000');
	    });
	    //判断文件名称是否为空
	    $namehide.live('blur',function() {
	    	var li = getChangecolor();
			var inputs = $(li).prevAll();
			//对象编号
			var objectNo = ($(inputs[3]).val());
			//父级目录
			var parentobjectNo = ($(inputs[2]).val());
			var objectNameFirst = searcyObjectName(objectNo).objectName;
	        $(this).css('border', 'none');
	       // $('input').removeAttr("readonly");
	        if ($(this).val() == "") {
	        	$(this).val(objectNameFirst);
	        }else{
	        	objectName=($(li).children().val());
	        	if(objectNameFirst==objectName){
	        		//没有做任何修改
	        	}else{
	        	$.messager.confirm("提醒！", '确定修改文件名？',function(r){
	        		if(r){
			        	$.ajax({
		        			type: "POST",
		                    url: rootPath+"netdisk/modifyNetDisk.action",
		                    data: {objectNo:objectNo,parentobjectNo:parentobjectNo,objectName:objectName},
		                    success:function(data) { 
		                    	window.location.reload();
		                }})
		                }else{
		                	//点击取消时，输入框回滚
		                	$(li).children().val(objectNameFirst);
		                };
		        })};
	        }
	        return true;
	    });
	});
	//通过对象名查找对象，只查当前目录下，方法实现调用ConditionGroup查询
	//09-28 查询方法修改为查询全局的
	function searchByObjectName(){
		var parentobjectNo = $("#parentobjectNo").val();
		var objectName;
		var searchType = 1;
		if($("#objectNameSearch").val()=="请输入文件名称" || $("#objectNameSearch").val()==""){
			$.messager.alert("信息提醒！","请输入文件名称！");
		}else{
			objectName = $("#objectNameSearch").val();
			var url = rootPath + 'netdisk/show.action?parentobjectNo='+parentobjectNo+'&objectName='+encodeURIComponent(encodeURIComponent(objectName))+'&searchType='+searchType;
			window.location.href=url;
		}
	};
	
	//获取当前选中的li
	function getChangecolor(){
		var lis = $(".changeColor");
		var flag=false;
		var li;
		for(var i=0;i<lis.length;i++){
			//根据背景颜色判断是否为选中的文件
			if($(lis[i]).css("background-color")=="rgb(238, 224, 229)"){
				flag=true;
				li = lis[i];
				break;
			}
		}
		if(flag==false){
			setTimeout("getChangecolor()",5);
		}else{
			return li;
		}
	}
	//新增，修改，查看详情弹窗方法
	function commonOperate(title,width,height){
		var li = getChangecolor();
		//prevAll() 获得当前匹配元素集合中每个元素的前面的同胞元素，使用选择器进行筛选是可选的。
		var inputs = $(li).prevAll();
		//传递参数对象编号
		var objectNo = ($(inputs[3]).val());
		var url = rootPath+'netdisk/detailNetDisk.action?objectNo='+objectNo;
		var content = '<iframe scrolling="auto" frameborder="0"  src="'
				+ url + '" style="width:100%;height:100%;"></iframe>';			
		$('#win').window({
			width : width,
			height : height,
			title : title,
			// 定义窗口是否可拖拽。
			draggable : true,
			// 定义是否显示最大化按钮。
			maximizable : true,
			// 定义是否显示最小化按钮。
			minimizable : false,
			// 定义是否显示折叠按钮。
			collapsible : false,
			// 定义窗口是不是模态（modal）窗口。
			modal : true,
			content : content,
		});
		// 窗口居中。
		$('#win').center;			
	}
	//根据文件编号，查找文件名称，文件类型
	function searcyObjectName(objectNo){
		//定义需要传递给下一个action的参数
		var dataMin;
		$.ajax({
			type: "POST",
			async: false,
            url: rootPath+"netdisk/searchByNo.action",
            data: {objectNo:objectNo},
            success:function(data) {
            	dataMin = data;
    		}
        });
		return dataMin;
	}
	/**若为文件则执行下载**/
	function downloadFile(){
		var li = getChangecolor();
		var inputs = $(li).prevAll();
		//对象编号
		var objectNo = ($(inputs[3]).val());
		//根据文件编号，查找到文件执行下载
		var URL = rootPath +'netdisk/downloadNetDisk.action?objectNo='+objectNo;
		window.location.href = URL;
	}
	/* 鼠标悬停事件 */
	$(function() { 
		$('.changeColor').tipsy({gravity: 's'});
	});

</script>
</head>
<body style=" overflow:hidden;height:900px;">
<input type ="hidden" id ="catalog" value ="${catalog}" /> 
    <div class="add-knowledge">
    	<!-- 表头操作按钮 -->
   		<div class="add-know-top">
	   		<p>
		   		<input type="submit" value="创建新文件夹" class="carrynews"/>
		   		<input type="submit" value="上传文件" onclick="makerUpload(false,filters,'${parentobjectNo}')"/>
	   		</p>
        </div>
        <!-- 目录区分区域 -->
        <div class="add-position">
        	<div class="add-position-pos">
            	<ol class="breadcrumb" style="padding:0">
            	  <li><i class="icon icon-window-alt"></i></li>
                  <li class="active"><a href="${pageContext.request.contextPath}/netdisk/show.action?parentobjectNo=100">知识库</a></li>
                 	${catalog}
                </ol>
            </div>
            <div class="add-position-search" style="width:88px;">
            	<input type="button" class="btn  btn-primary" style="float:right;height:32px;border-top-left-radius:0;border-bottom-left-radius:0;" value="搜索" onclick="searchByObjectName();"/>
            </div>
        	<div class="add-position-search">
        		<input type="text" id="objectNameSearch" inquery="true" operator="like" value="请输入文件名称" onfocus="javascript:if(this.value=='请输入文件名称')this.value='';" class="form-control form-focus" style="float:right;height: 30px;border-top-right-radius:0;border-bottom-right-radius:0;padding: 0;margin: 0;text-align: left;text-indent: 3px;" />
        	</div>
        </div>
	</div>
	<!-- 数据展现页面,利用struts标签循环数据 -->
	<span style="padding-top:350px;font-size:30px;text-align:center;margin-left:auto;margin-right:auto;display:none;height:374px;background:url(${pageContext.request.contextPath}/images/nofile.png) no-repeat center top" id="messager">${messager}</span>
	<input id="parentobjectNo" type="hidden" value='${parentobjectNo}'/>
	<!-- 下面部分 -->
	<div class="alldom" style="height:500px;width:100%; overflow:hidden; overflow-y:visible">
	    <ul id="divall">
	    <s:iterator value="datalist" var="file">
	    	<input type="hidden" value='${objectSuffix}'/>
	    	<input type="hidden" value='${objectNo}'/>
	    	<input type="hidden" value='${parentobjectNo}'/>
	    	<input type="hidden" value='${chirldItemCount}'/>
	    	<input type="hidden" value='${objectType}'/>
	        <s:if test="#request.objectSuffix=='doc'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/word.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='docx'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/word.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='xls'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/excel.png) center top no-repeat;" class="changeColor"><input type="text"  class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='xlsx'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/excel.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='pdf'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/pdf.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='ppt'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/ppt.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='rar'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/rar.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix=='txt'">
	        	<li title='${objectName}' style="background:url(${pageContext.request.contextPath}/netdisk/style/txt.png) center top no-repeat;" class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	        <s:if test="#request.objectSuffix==''">
	        	<li title='${objectName}' class="changeColor"><input type="text" class="changename" value='${objectName}'/></li>
	        </s:if>
	    </s:iterator>
	    </ul>
	</div>
	<!-- 右键菜单功能 -->
	<div style="clear:both;"></div>
	<div class="menu-zdy" id="menu">
	   <div class="menu-one">
	       <a href="#nogo" id="changename">重命名</a>
	   </div>
	   <div class="menu-one">
	       <a href="#nogo" id="removethispc">删除</a>
	   </div>
	   <div class="menu-one" id="download" style="display:none">
	       <a onclick="downloadFile()" href="javascript:void(0);">下载</a>
	   </div>
	   <!-- <div class="menu-one">
	       <a onclick="commonOperate('文件详情',450,300)" href="javascript:void(0);">文件详情</a>
	   </div> -->
	</div> 
    
<!-- 上传成功后返回结果 -->
<div id="res"></div>
<!-- 点击新增所显示页面 -->
<div id="win"></div>
</body>
</html>
