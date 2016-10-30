<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>二级分类编辑</title>
<script src="${pageContext.request.contextPath}/scripts/common.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/SST/layeritems/scripts/operate.js" type="text/javascript"></script>

</head>
<body>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	//设置启用与否
	$(function(){
		$('#select').combobox({    
   				 url:'${pageContext.request.contextPath}/sst/loadParentLayerItems.action?SSTId=${SSTId}',    
    			valueField:'id',    
   		 		textField:'text' ,
   		 		onLoadSuccess: function () { 
				 var data = $('#type').combobox('getData');
            	 if (data.length > 0) {
                 $('#type').combobox('select', data[0].layerItemId);
            	 } 
}
		}); 
		$('#isEnabled').switchbutton({  
        onChange: function(checked){
            if (checked == true){
                $('#enableState').val(1);
                return;  
            }else{ 
                $('#enableState').val(2);
            }}  
        });  
		var state = $('#enableState').val();
		if(state=="2" ){
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
                $('#visibleState').val(2);
            }}  
        });  
		var state = $('#visibleState').val();
		if(state=="2" ){
			$('#isVisible').switchbutton({checked: false,});
		}else{
			$('#isVisible').switchbutton({checked: true,});
		}
    });
</script>
<div class="easyui-panel" style="width:100%,height:500px" border=0>
	<div>
		<form id="myForm">
			
			<table cellpadding="5" class="list-con">
			<tr>
					<td class="list-name">项目名称：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="layerItemName1" name="layerItemName1" />
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				
				<tr>
					<td class="list-name">显示顺序：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="displayOrder" name="displayOrder" />
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				<tr>
					<td class="list-name">所属层级：</td>
					<td class="list-info"><input class="easyui-textbox" type="text" style="width:200px;height:28px;" id="layerNo1" name="layerNo" />
						<font color="red" style="padding-right:0;">*必填</font>
					</td>
				</tr>
				
				 <tr>
					<td class="list-name">所属父级：</td>
					<td class="list-info">
					<%-- 	<input class="easyui-combobox" data-options="valueField:'layerItemId',textField:'layerItemName',panelHeight:'auto',url:'${pageContext.request.contextPath}/sst/loadParentLayerItems.action?SSTId=${SSTId}'" id="belongLayerItemId" name="belongLayerItemId" style="width:200px;height:28px;" editable="true" value="${layerItemName }">
						</input> --%>
						<input type="text" id="select" />
					</td>
				</tr> 
				<!-- <tr>
					<td class="list-name">显示顺序：</td>
					<td class="list-info"><input class="easyui-numberspinner" data-options="min:0" style="width:100px;height:28px;" id="displayOrder" name="showNum" />
					</td>
				</tr> -->
				<tr>
					<td class="list-name">可用状态：</td>
					<td class="list-info"><input class="easyui-switchbutton" id="isEnabled" data-options="onText:'启用',offText:'停用'" />
					<input type="hidden" id="enableState" name="isEnabled" />
					</td>
				</tr>
				<tr>
					<td class="list-name">显示状态：</td>
					<td class="list-info"><input class="easyui-switchbutton" id="isVisible" data-options="onText:'启用',offText:'停用'" />
					<input type="hidden" id="visibleState" name="isVisible" />
					
					</td>
				</tr>
				<input type="text"  value="${SSTId}" name="SSTId" id="SSTId"style="display:none"/>
				<input type="text"  value="${layerItemId}" name="layerItemId" id="layerItemId"style="display:none"/>
			</table>
			<!-- 执行按钮-->
			<div class="easyui-btn">
				<i> 
					<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
					<input type="button" value="关闭" class="btn-effect" onclick="window.parent.closeWin()" />
				</i>
			</div>
		</form>
	</div>
</div>
</body>
</html>
