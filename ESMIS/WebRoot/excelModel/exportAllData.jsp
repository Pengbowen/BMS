<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.opensymphony.xwork2.ActionSupport" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%
	ActionContext cxt = ActionContext.getContext();//获取action 上下文环境
	HttpServletRequest request1 = (HttpServletRequest) cxt.get(ServletActionContext.HTTP_REQUEST);
	String LoadDataUrl = request.getParameter("LoadDataUrl");
	try {
		//接收到js传进来的参数后，解密
		LoadDataUrl = java.net.URLDecoder.decode(LoadDataUrl, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	//将modelId单独保存
	String modelId = LoadDataUrl.substring(LoadDataUrl.indexOf("modelId=") + 8);
	//截取接收过来的查询方法
	String searchUrl=LoadDataUrl.substring(0, LoadDataUrl.indexOf("?"));
	//截取查询方法的参数
	String searchData=LoadDataUrl.substring(LoadDataUrl.indexOf("?")+1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>导出全部数据</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/commonExport.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>

<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	//接收url,稍后执行,查询出要导出的数据
	var searchUrl= '<%=searchUrl %>';
	//接收查询数据的参数，后续导出前查询使用
	var searchData= '<%=searchData %>';
	//接收modelId,查询完数据后传入下一个action
	var modelId = "<%=modelId %>";
	//总条数
	var recordCount = parent.recordCount;
	//总页数
	var pageCount = parent.pageCount;
	//每页条数
	var linesOfPage = parent.linesOfPage;
	//当前页
	var countNum = 1;
	//执行js获取json数据的方法，得到需要格式化的字段
	config(modelId);
	//设置进度条的样式及读取数据
	$(function () {
            var value = $("#p").progressbar("getValue");
            if ((value < 100) && (countNum<=pageCount)) {
            	value =parseInt(((countNum*linesOfPage)/recordCount)*100);
            	if(value>100 )value =100;
            	$("#p").progressbar("setValue", value);
    			$.ajax({
    				url:searchUrl,
    				method: 'post',
    				data:searchData+'&currentPage='+countNum+'&linesOfPage='+linesOfPage,
    				success:function(data){
    					var obj = eval('(' + data + ')');
    					for ( var j = 0; j < obj.datalist.length; j++) {
    						var item = obj.datalist[j];
    						var index = myDataList.length;
    						var result = {};
    						$.each(item,function(key,value)	
    						{
    							if(myConfig[key] != undefined)
    							{
    								if(myConfig[key]=="")
    								{
    									result[key] = value;
    								}else{
    									result[key] = eval('window.parent.'+myConfig[key]+'("'+value+'", '+index+', '+j+');');
    								}
    							}
    						});
    						myDataList[index] = result;
						}
    					//将数据post给后台
    					ImplementExport(myDataList,modelId,countNum);
    					//清空数据，为下次传做准备
    					myDataList=[];
    				}
    			});
    			if(countNum==pageCount){
            		$("#p").progressbar("setValue", 100);
            		$(".progressbar-value .progressbar-text").html("完成");
            	}
            	countNum++;
            	setTimeout(arguments.callee, 500);
           	}
            //设置进度条样式
            if(value < 100){
            	document.getElementById("star").style.display='';
            	document.getElementById("end").style.display='none';
            	document.getElementById("close").style.display='none';
            }
          	//执行导出
            if(value == 100){
            	//进度条为100%时,显示为完成,颜色修改为黄绿色(查特酒绿)
            	$(".progressbar-value .progressbar-text").css("background-color","Chartreuse");
            	$(".progressbar-value .progressbar-text").html("完成");
            	document.getElementById("close").style.display='';
            	document.getElementById("star").style.display='none';
            	document.getElementById("end").style.display='';
            }
	 });
	//点击确定执行导出全部的action
	function ImplementExport(myDataList,modelId,countNum){
		var exportUrl = rootPath+'commonexport/commonExport.action';
		var cdata =encodeURIComponent(JSON.stringify(myDataList));
		//所有post完成后执行下载
		if(countNum>pageCount){
			$.ajax({
	    		url:exportUrl,
	    		type:'JSON',
	    		async: false,
	    		method: 'post',
	    		data:{param:cdata,modelId:modelId,countNum:countNum-1},
	    		success:function(data){
					var url = rootPath+'commonexport/exportDownload.action';
					window.location.href = url+'?modelId='+modelId;
	    		}
			});
		}else{
			$.ajax({
	    		url:exportUrl,
	    		type:'JSON',
	    		async: false,
	    		method: 'post',
	    		data:{param:cdata,modelId:modelId,countNum:countNum-1}
			});
		}
	}
</script>
</head>
<body>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<table cellpadding="10" class="list-con">
		<tr>
			<td class="list-name">导出进度：</td>
			<td class="list-info"><div id="p" class="easyui-progressbar" data-options="value:0" style="width:300px;"></div></td>
		</tr><br/><br/><br/>
		<tr>
			<td class="list-name">执行过程：</td>
			<td id="star" class="list-info"><i class="icon icon-spin icon-spinner-snake"></i>&nbsp;&nbsp;第①步：查询数据......</td>
			<td id="end" class="list-info"><i class="icon icon-smile"></i>&nbsp;&nbsp;第②步：导出已执行，点击确定按钮退出本页！</td>
		</tr>
	</table>
	<div id="close" style="display:'none'" class="easyui-btn">
		<button  class="btn btn-primary" type="button" style="width:60px;height:28px;margin-left:320px;float:right" onclick="window.parent.closeWin()">确定
		</button>
	</div>
</div>
</body>
</html>
