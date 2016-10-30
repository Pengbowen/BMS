<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>首页</title>
<link type="text/css" href="style/add-style.css" rel="stylesheet" />
<link type="text/css" href="css/table.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	function simplecontent(){
		var url = rootPath+"simplecontent/skipToUpdate.action";
		top.tab("设置企业方针目标",url);
	}
	function simplecontent(){
		var url = rootPath+"simplecontent/skipToUpdate.action";
		top.tab("设置企业方针目标",url);
	}
</script>
</head>
<body>
    <div class="table">
    <div class="table-title">企业标准体系总图</div>
    <div class="table-content">
        <div class="table-content-1">
            <div>
                <a onclick="simplecontent()" href="javascript:void(0);" class="text-y">企业方针目标</a>
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
                <a onclick="window.parent.tab('企业贯彻的标准化法规和企业标准化法规',rootPath+'lawslibrary/showLawsLibrary.action?lawsType=1')" href="javascript:void(0)" class="text-g">企业贯彻的标准化法规和企业标准化规定</a>
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
                <a onclick="window.parent.tab('企业使用的法律法规和规章',rootPath+'lawslibrary/showLawsLibrary.action?lawsType=2')" href="javascript:void(0)" class="text-b">企业适用的法律法规和规章</a>
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
                <a onclick="window.parent.tab('技术标准体系表',rootPath+'sst/toTechnicalStandard.action')" href="javascript:void(0)" class="text-1">技术标准体系</a>
                <a onclick="window.parent.tab('工作标准体系表',rootPath+'sst/toWorkStandard.action')" href="javascript:void(0)" class="text-2">工作标准体系</a>
                <a onclick="window.parent.tab('管理标准体系表',rootPath+'sst/toManageStandard.action')" href="javascript:void(0)" class="text-3">管理标准体系</a>
            </div>
        </div>
    </div>
</div>
    
</body>
</html>