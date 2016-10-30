<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<style type="text/css">
	*{margin:0;padding:0;list-style-type:none;}
	a,img{border:0;}
	body{overflow:hidden;font:12px/180% Arial, Helvetica, sans-serif ,"新宋体";}
	/* shortcut */
	.shortcut{position:fixed;top:0;left:0;z-index:9999;width:100%;}
	*html,*html body /* 修正IE6振动bug */{background-image:url(about:blank);background-attachment:fixed;}
	*html .shortcut{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
	.shortcut{height:28px;background:#EEEEEE;box-shadow:1px 0px 2px rgba(0,0,0,0.2);border-bottom:1px solid #DDDDDD;padding:40px 0 0 0;}
	.shortcut li{float:left;cursor:pointer;margin:5px 0 0 15px;display:inline;}
	/* #picbbox */
	.down_showpic{overflow:hidden;text-align:center;}
</style>
</head>
<body onmouseout="Ban()">
<!--演示内容开始-->
<div id="picbbox">
	<div class="down_showpic">
		<%
			String type=request.getParameter("type");
			if("technical".equals(type)){
			%>
				<iframe id='showiframe' src="${pageContext.request.contextPath}/sst/toTechnicalStandard.action?manage=false" frameborder="0" width="3000" height="3000" scrolling="no" marginwidth="0" marginheight="0"></iframe>
			<%
			}else if("manage".equals(type)){
			%>
				<iframe id='showiframe' src="${pageContext.request.contextPath}/sst/toManageStandard.action?manage=false" frameborder="0" width="3000" height="3000" scrolling="no" marginwidth="0" marginheight="0"></iframe>
			<%
			}else if("work".equals(type)){
			%>
				<iframe id='showiframe' src="${pageContext.request.contextPath}/sst/toWorkStandard.action?manage=false" frameborder="0" width="3000" height="3000" scrolling="no" marginwidth="0" marginheight="0"></iframe>
			<%
			}
		%>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$(".down_showpic").css("width",$(window).width());
	//浏览器缩放
	$(window).resize(function(){
		$(".down_showpic").css("width",$(window).width());
	});
});

var p_w;
var p_h;
$(".down_showpic>iframe").load(function(){
	p_w=$(this).width();
	p_h=$(this).height();
})
var picdiv;
dragpic=-1;
dragpicy=-1;
setTimeout(function(){
	picdiv = document.getElementById('showiframe').contentWindow.document.body;
	$(picdiv).bind({
		mousedown: function(e){
			dragpic=e.pageX || (e.clientX);
			dragpicy=e.pageY || (e.clientY);
			if (e.stopPropagation){ 
				picdiv.style.cursor = "move";
				e.stopPropagation(); 
				e.preventDefault();
			};
		},
		mouseup: function(){
			//console.log("松开了鼠标");
			dragpic=-1;
			picdiv.style.cursor = "";
		},mousemove: function(e){
			if(dragpic>=0){
				dqsb=e.pageX || (e.clientX );
				dqsby=e.pageY || (e.clientY );
				$(".down_showpic").scrollLeft($(".down_showpic").scrollLeft()+(dragpic-dqsb));
				$(document).scrollTop($(document).scrollTop()+(dragpicy-dqsby));
				dragpic=dqsb;
				dragpicy=dqsby;
			}
		},mouseout: function(){
			//鼠标移出元素时触发
		}
	});
},500);
//鼠标超出body则禁用拖动
function Ban(){
	picdiv.style.cursor = "band";
	dragpic=-1;
}
</script>
</body>
</html>