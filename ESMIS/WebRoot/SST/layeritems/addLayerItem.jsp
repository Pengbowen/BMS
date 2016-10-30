<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>项目编辑</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/layeritems/scripts/operate.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	$(function(){
	//调用状态设置方法,默认为启用
		var isEnabeld="";
  	isEnabled = '${isEnabled}';
  	var isVisible ="";
  	isVisible = '${isVisible}';
  	if(isEnabled ==""){
  	$("#enableState").val(1);
  	}
  	if(isVisible==""){
  	$("#visibleState").val(1);
  	}
  	if('${state}' == 1){
  	addFirstItem();
  	}
  	
		$('#select').combobox({    
   				url:'${pageContext.request.contextPath}/sst/loadParentLayerItems.action?SSTId=${SSTId}',    
    			valueField:'id',    
   		 		textField:'text',
   		 		width:200,
   		 		disabled:true,
   		 		onLoadSuccess: function () {
   		 		if('${state}'=="3"){
   		 		var layerId = '';
   		 		layerId = '${layerId}';
				if(layerId != ''){
   		 		$('#select').combobox("select",layerId);
   		 		}
   		 		}else{
   		 			var belongItemId = '';
   		 		belongItemId = '${belongItemId}';
				if(belongItemId != ''){
   		 		$('#select').combobox("select",belongItemId);
   		 		}
   		 		}
				}
		}); 
		$('#isEnabled').switchbutton({  
        onChange: function(checked){
            if (checked == true){
                $('#enableState').val(1);
                return;  
            }else{ 
                $('#enableState').val(0);
            }}  
        });  
		var state = $('#enableState').val();
		if(state=="0" ){
			$('#isEnabled').switchbutton({checked: false,});
		}else{
			$('#isEnabled').switchbutton({checked: true,});
		}
		$('#isVisible').switchbutton({  
        onChange: function(checked){
            if (checked == true){
                $('#visibleState').val(1);
                return;  
            }else{ 
                $('#visibleState').val(0);
            }}  
        });  
		var state = $('#visibleState').val();
		if(state=="0" ){
			$('#isVisible').switchbutton({checked: false,});
		}else{
			$('#isVisible').switchbutton({checked: true,});
		}
    });
  
   //如果是新增根项目
   function addFirstItem(){
  $("#fatherClass").hide();
   $("#layerNo1").textbox("setValue","1");
   //$("#layerNo1").textbox("options").editable=false;
    $("#layerNo1").textbox("enable");
   }
</script>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm" >
			
			<table cellpadding="5" class="list-con">
			<tr>
					<td class="list-name">项目名称：</td>
					<td class="list-info"><input class="easyui-textbox"  type="text" style="width:200px;height:28px;" id="layerItemName1" name="layerItemName1" value="${layerItemName1}"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">项目编号：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="layerItemNo" name="layerItemNo" value="${layerItemNo}"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				 <tr id="fatherClass">
					<td class="list-name">所属父级：</td>
					<td class="list-info">
						<input type="text" id="select" />
					</td>
				</tr> 
				<tr>
					<td class="list-name">所属层级：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="layerNo1" name="layerNo1" value="${layerNo1}"  disabled="true"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">显示顺序：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="displayOrder" name="displayOrder" value="${displayOrder}"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">可用状态：</td>
					<td class="list-info"><input class="easyui-switchbutton" id="isEnabled" data-options="onText:'启用',offText:'停用'" />
					<input type="hidden" id="enableState" name="isEnabled" value="${isEnabled}"/>
					</td>
				</tr>
				<tr>
					<td class="list-name">显示状态：</td>
					<td class="list-info"><input class="easyui-switchbutton" id="isVisible" data-options="onText:'启用',offText:'停用'" />
					<input type="hidden" id="visibleState" name="isVisible" value="${isVisible}"/>
					</td>
				</tr>
			</table>
			<input type="text"  value="${layerItemId}" name="layerItemId" id="layerItemId" style="display:none"/>
			<input type="text"  value="${SSTId}" name="SSTId" id="SSTId"style="display:none"/>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" />
				</i>
			</div>
		</form>
			<input type="text"  value="${belongItemId}" name="belongItemId" id="belongItemId"style="display:none"/>
	</div>
</div>
</body>
</html>
