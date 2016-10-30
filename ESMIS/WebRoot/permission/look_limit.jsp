<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv = "X-UA-Compatible" content = "IE=edge" />

</head>
<body>
<script type="text/javascript">
//根路径
var rootPath="${pageContext.request.contextPath}/";

var roleId="<%=request.getParameter("roleId")%>";
var belongApp="<%=request.getParameter("belongApp")%>";

$(function(){		
	//构建一颗树
	$("#test").tree({
          url:rootPath+"roleManage/lookLimit.action?roleId="+roleId+"&belongApp="+belongApp,
          lines:true,//是否显示竖线
          animate:true,//是否开启动画
          checkbox:true,//是否显示节点前的复选框
          onBeforeLoad:function(node, param){//加载前
             $("#saveId").hide();
             $("#load").show().append('<img src="'+rootPath+'images/loading.gif"/>正在加载数据，请稍后...');
          },
          onLoadSuccess:function(node, data){//加载到数据后
              $("#load").html("").hide();
              $("#saveId").show();
          }
     });
});	
 
//获取选中的权限
function saveData(){
     //获取打√的被选中的节点和被选中的实心圆点的的节点
     var nodes = $("#test").tree("getChecked", ["checked","indeterminate"]);
	 if(nodes.length<=0)
	 {
	    $.messager.alert("提示","请选择权限！");
        return;
	 }
	 
	 var idList = "";  
     for(var i=0;i<nodes.length;i++)
     {
        idList+=nodes[i].id+",";
     }
     
     var strData="roleId="+roleId+"&belongApp="+belongApp;
         strData+="&limits="+idList.substring(0, idList.lastIndexOf(","));
     
     $.ajax({
        url:rootPath+"roleManage/saveData.action",
        data:strData,
        type:"post",
        cache:false,
        dataType:"json",
        beforeSend:function(){
           $("#saveId").hide();
           $("#load").show().append('<img src="'+rootPath+'images/loading.gif" width="30" height="30"/>&nbsp;正在保存数据...');
        },
        success:function(data){
            if(data.result=="1")
		    { 
		     	$("#load").hide();
		     	$.messager.alert("提示",data.message);
	    		loadGridViewData();
	    		closeWin();
		    }else
		    { 
		       $.messager.alert("提示",data.message);
		    }
        }
     });
}
</script>
 
  <div>
      <!-- 树形权限菜单 -->
      <ul id="test" class="easyui-tree" style="height:100%;margin-left: 5px;"></ul> 
  </div>

  <p id="load" align="center" style="margin-top: 10px;display: none;"></p>
  
  <div class="search-save" ><a href="javascript:void(0);" id="saveId" class="easyui-linkbutton bnt-save" onclick="saveData();">保存</a></div>

</body>
</html>
