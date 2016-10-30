	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" type="text/css" href="../style/public.css" />
	<link rel="stylesheet" type="text/css" href="../style/washing.css" />
    
    <link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ajaxjson.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
    <title>设置体系表权限</title>
		   <style type="text/css">
	.btn-effect{
		width:100px;
		/*height:24px;*/
		line-height:24px;
		border-radius:3px;
		border:0;
		background:#F9B129;
		margin-right:10px;
		color:#fff;
	}
	.easyui-btn{
		border-top:1px solid #ccc;
		padding:15px 0 10px 0px;
		text-align: center;
	}
	.data_i{
		width:120px;
	}
	.tabPanel{height:30px;width:630px;}
.tabPanel div{
	float:left;margin:0 2px 0 0;border:1px solid #aaa;font-size:11px;height:29px;line-height:30px;width:150px;text-align:center;cursor:pointer;
	text-shadow:0 1px 0 #fff;
	border-radius:4px 4px 0 0;
	box-shadow:inset 0 1px 0 rgba(255, 255, 255, 0.5);
	background:#ddd;
}
.tabPanel .hit{
	border-bottom:1px solid #fff;cursor:pointer;color:black;text-shadow:0 1px 0 #fff;
	background:#fff;
}
.pane{border:1px solid #aaa;border-top:0;min-height:100px;background-color:#fff;display:none;}
.pane p{padding:15px 15px 0 10px;}
.pane h4{padding:15px 15px 0 10px;font-size:14px;font-weight:bold;}
	b{
	text-align:left;
	}
	</style>
	<script type="text/javascript">
    	var rootPath = "${pageContext.request.contextPath}/";
    	var SSTId =1;
    	
		//请求数据
		$(function(){
		$("#main").trigger("click");
		$('.tabPanel div').click(function(){
		$(this).addClass('hit').siblings().removeClass('hit');
		$('.panes>div:eq('+$(this).index()+')').show().siblings().hide();	
	})
		 });
		 
		 
		 
		function loadData(sstid){
		SSTId=sstid;
		$("#html").html("");
			var url = rootPath + 'sst/powerLoadData.action?SSTId='+SSTId;
		 	AjaxJson(url, true, null, serviceHtml, true);
		
		}		
    	
    	function serviceHtml(json){
		 	var html = "";
		 	var key = "";
		 	var checkState="";
		 	 var datalist = json.datalist;
		 	 var layerNo="";
		 	 for(var i=0;i<datalist.length;i++){
		 	 checkState="";
		 	 var key = json.datalist[i].layerNo + "_" + json.datalist[i].id;
		 	 var tempData = datalist[i];
		 	 var id = tempData.id;
		 	 var name = tempData.name;
		 	 var state = tempData.state;
		 	 if(state=="1") {
		 	 checkState = 'checked="true"';
		 	 }
		 	 //层级相同
		 	 if(layerNo ==  tempData.layerNo){
		 	 html += '<input type="text" style="display:none;" name="checkvalue" value="'+json.datalist[i].id+'"/><b style="width:150px"><input type="checkbox" name="checkbox" '+checkState+' id="'+key+'"/>'+json.datalist[i].name+'</b>';
			 		if (i == json.datalist.length-1){
			 			html += '</i><em><font>全选</font><input type="checkbox" name="checkall" id="'+json.datalist[i-1].layerNo+'"/></em></li>';
			 		}
		 	 }else {
		 	
			 		layerNo = json.datalist[i].layerNo;
			 		if (i != 0){
			 			html += '</i><em><font>全选</font><input type="checkbox" name="checkall" id="'+json.datalist[i-1].layerNo+'"/></em></li>';
			 		};
			 		html +='<li><strong style="width:50px;">'+'第'+layerNo+'层'+'</strong><i>';
			 		html += '<input type="text" style="display:none;" name="checkvalue" value="'+json.datalist[i].id+'"/><b style="width:150px"><input type="checkbox" name="checkbox" '+checkState+' id="'+key+'" />'+json.datalist[i].name+'</b>';
			 		if (i == json.datalist.length-1){
						html += '</i><em ><font>全选</font><input type="checkbox" name="checkall" id="'+json.datalist[i].layerNo+'"/></em></li>';
			 		}; 
		 	 }
		 	 }
		 	$("#html").html(html); 
		 	checkedAll(json);
		 }
		 
    	function jiaoyan()
		{
		  var x = document.getElementsByName("checkbox");
		  var z = $("input:checkbox[name='checkbox']:checked");
		  var v = document.getElementsByName("checkvalue");
		  var connectno = "";
		  var checkCount=0;
		  for(var p = 0; p < x.length; p++){
		  	if(x[p].checked){
		  	checkCount++;
		  		connectno += v[p].value;
		  		if(checkCount != z.length){
		  			connectno += ",";
		  		};
		  	};
		  }
		  var url = rootPath + 'sst/savePowerSet.action';
		  var data = "SSTId=" + SSTId + "&connectno=" + connectno ;
		  AjaxJson(url, true, data, doDocAddByIframe, true, false);  
		 }
		 //Iframe框架下的回调函数刷新页面
		function doDocAddByIframe(data) {
		  if (data.result == "1") {
		    $.messager.alert("信息提示！", data.message);
		  } else {
		    $.messager.alert("信息提示！", data.message);
		  };
		}
		function checkedAll(json){
			$("input[name='checkall']").click(function(){
				if(this.checked){
					for (var i=0;i<json.datalist.length; i++){
						var key = this.id + "_" + json.datalist[i].id;
						$("#"+key).attr("checked","checked");
						//checked属性(配合IE浏览器兼容问题使用)
						$("#"+key).prop("checked",true);
					};
				} else {
					for (var i=0;i<json.datalist.length; i++){
						var key = this.id + "_" + json.datalist[i].id;
						$("#"+key).removeAttr("checked");
						//checked属性(配合IE浏览器兼容问题使用)
						$("#"+key).prop("checked",false);
					};
				};
				
			});
			
		}
	

    </script>

  </head>
  
  <body>
  <div>
 <!-- <button id = "main" class="btn btn-lg  btn-primary" type="button" style="width:200px; height:36px;margin-left:250px;" onclick="loadData(1)"><i class="icon icon-list-ul"></i> 技术标准体系表</button>
 <button class="btn btn-lg  btn-info" type="button" style="width:200px;height:36px;margin-left:400px;" onclick="loadData(2)"><i class="icon icon-list-ul"></i> 管理标准体系表</button>
 <button class="btn btn-lg  btn-success" type="button" style="width:200px;height:36px;margin-left:400px;" onclick="loadData(3)"><i class="icon icon-list-ul"></i> 工作标准体系表</button> -->
 <div style="width:420px;margin:40px auto;">
    <div class="tabPanel">
			<div class="hit" onclick="loadData(1)" id="main"><i class="icon icon-list-ul"></i> 技术标准体系表</div>
			<div onclick="loadData(2)"><i class="icon icon-list-ul"></i> 管理标准体系表</div>
			<div onclick="loadData(3)"><i class="icon icon-list-ul"></i> 工作标准体系表</div>
    </div>
</div>

  </div>
  
  
    <div class="box" style="width:100%;height:100%">
<div class="tit" id="title"></div>
<div class="brand">
	<ul id="html">
    </ul>
</div>
<div class="easyui-btn">
	<i> 
		<input type="button" value="提交" class="btn-effect" onclick="jiaoyan()" />
		<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeSelectedTab();"/>
	</i>
</div>
</div>
  </body>
</html>
