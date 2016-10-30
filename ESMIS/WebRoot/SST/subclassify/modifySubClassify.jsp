<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>子分类编辑</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/SST/subclassify/scripts/operate.js" type="text/javascript"></script>

</head>
<body>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	//设置启用与否
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
  	
  	
		$('#select').combobox({    
   				 url:'${pageContext.request.contextPath}/sst/loadParentLayerItems.action?SSTId=${SSTId}',    
    			valueField:'id',    
   		 		textField:'text',
   		 		width:200,
   		 		disabled:true,
   		 		onLoadSuccess: function(){
   		 		var belongItemId = '';
   		 		layerItemId = '${layerItemId}';
				if(layerItemId != ''){
   		 		$('#select').combobox("select",layerItemId);
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
   
</script>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm" >
			
			<table cellpadding="5" class="list-con">
			<tr>
					<td class="list-name">所属项目：</td>
					<td class="list-info">
					<%-- 	<input class="easyui-combobox" data-options="valueField:'layerItemId',textField:'layerItemName',panelHeight:'auto',url:'${pageContext.request.contextPath}/sst/loadParentLayerItems.action?SSTId=${SSTId}'" id="belongLayerItemId" name="belongLayerItemId" style="width:200px;height:28px;" editable="true" value="${layerItemName }">
						</input> --%>
						<input type="text" id="select" />
					</td>
				</tr> 
				<tr>
					<td class="list-name">子分类名称：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="subClassName1" name="subClassName" value="${subClassName}"/>
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<%-- <tr>
					<td class="list-name">项目名称：</td>
					<td class="list-info">
						<input class="easyui-combobox" data-options="valueField:'id',textField:'text',onSelect:changeWay,panelHeight:'auto',url:'${}/clothesclassify/getFirstClass.action'"  id="classNature" name="classNature" style="width:200px;height:28px;" editable="false" value="${classNature }">
						</input>
					</td>				
				</tr> --%>
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
			<input type="text"  value="${subClassId}" name="subClassId" id="subClassId" style="display:none"/>
			
			<input type="text"  value="${SSTId}" name="SSTId" id="SSTId"style="display:none"/>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" />
				</i>
			</div>
		</form>
		<input type="text"  value="${layerItemId}"  id="layerItemId" style="display:none"/>
	</div>
</div>
</body>
</html>
