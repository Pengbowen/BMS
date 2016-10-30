<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>体系表总框图</title>
<link  href="${pageContext.request.contextPath}/css/table.css" type="text/css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
</head>
<body onload="change('sstHome')">
<!--头部开始 -->
<%@ include file="../header.jsp" %>
<!--头部结束 -->
<!--banner开始 -->
<div class="current-position-box">
	<div class="current-position">
        <a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
        <i class="icon icon-angle-right"></i><b> 体系表</b>
    </div>
</div>
<!--banner结束 -->
<!--体系总框图start-->
<div class="table">
    <!--  <div class="table-title">企业标准体系总图</div> -->
    <div class="table-content">
        <div class="table-content-1">
            <div>
                <a href="${pageContext.request.contextPath}/simplecontent/skipToDetail.action" class="text-y">企业方针目标</a>
                <canvas id="canvas-1"></canvas>
                <script>
                    var canvas = document.getElementById("canvas-1");//获取到canvas元素
                    //设置宽高
                    canvas.width = 186;
                    canvas.height = 120;
                    var context = canvas.getContext("2d");//获取上下文的环境
                    //canvas 是基于状态的绘制，而不是对象
                    context.setLineDash([2, 2]);
                    context.moveTo(103,0);
                    context.lineTo(103,60);
                    context.lineTo(185,60);
                    context.lineTo(185,120);
                    context.lineWidth = 1;//线条的宽度
                    context.strokeStyle = "#666";//线条的颜色
                    context.stroke();//绘制
                </script>
            </div>
            <div><span  class="w-pline"></span></div>
            <div>
                <a href="${pageContext.request.contextPath}/lawslibrary/searchLawsLibrary.action?standardCategory=1" class="text-g" style="box-sizing:content-box;">企业贯彻的标准化法规和企业标准化规定</a>
                <canvas id="canvas-2"></canvas>
                <script>
                    var canvas = document.getElementById("canvas-2");//获取到canvas元素
                    //设置宽高
                    canvas.width = 186;
                    canvas.height = 120;
                    var context = canvas.getContext("2d");//获取上下文的环境
                    //canvas 是基于状态的绘制，而不是对象
                    context.moveTo(93,0);
                    context.lineTo(93,120);
                    context.lineWidth = 1;//线条的宽度
                    context.strokeStyle = "#333333";//线条的颜色
                    context.stroke();//绘制
                </script>
            </div>
            <div><span  class="w-pline"></span></div>
            <div>
                <a href="${pageContext.request.contextPath}/lawslibrary/searchLawsLibrary.action?standardCategory=2" class="text-b" style="box-sizing:content-box;">企业适用的法律法规和规章</a>
                <canvas id="canvas-3"></canvas>
                <script>
                    var canvas = document.getElementById("canvas-3");//获取到canvas元素
                    //设置宽高
                    canvas.width = 186;
                    canvas.height = 120;
                    var context = canvas.getContext("2d");//获取上下文的环境
                    //canvas 是基于状态的绘制，而不是对象
                    context.setLineDash([2,2]);
                    context.moveTo(82,0);
                    context.lineTo(82,60);
                    context.lineTo(1,60);
                    context.lineTo(1,120);
                    context.lineWidth = 1;//线条的宽度
                    context.strokeStyle = "#333333";//线条的颜色
                    context.stroke();//绘制
                </script>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="table-content-2">
            <div class="table-inline">
                                         企业标准体系
                <div class="h-pline"></div>
                <a href="${pageContext.request.contextPath}/SST/technicalStandard.jsp" class="text-1">技术标准体系</a>
                <a href="${pageContext.request.contextPath}/SST/workStandard.jsp" class="text-2">工作标准体系</a>
                <a href="${pageContext.request.contextPath}/SST/manageStandard.jsp" class="text-3">管理标准体系</a>
            </div>
        </div>
    </div>
</div>
<!--体系总框图end-->
<!--底部开始 -->
<iframe src="../footer.html" frameborder="0" width="100%" height="120" scrolling="no" marginwidth="0" marginheight="0"></iframe>
<!--底部结束 -->
</body>
</html>