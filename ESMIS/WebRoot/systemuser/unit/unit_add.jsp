<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>部门信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="right">
<div class="right" style="margin:0;">
<form id="myForm">
<div class="popMain">
    <div class="popAdd">
    	<input type="hidden" id="category" name="unit.category" value="1"/>
    	<ul class="popInfo clearfix">
    	    <li><span>部门名称：</span><i>
    	           <input type="text" class="popText Text02" style="width:300px;" id="unitname" name="unit.unitName" value="${unit.unitName }"/>
    	       <font color="red" style="padding-right:0;">*必填</font>
    	       </i></li>
    	    <li><span>部门简称：</span><i>
    	           <input type="text" class="popText Text02" style="width:300px;" id="abbreviation" name="unit.abbreviation" value="${unit.abbreviation }"/>
    	       </i></li> 
    	    </li>   
    	    
         	<li><span>联系人：</span><i>
    	           <input type="text" class="popText Text02" style="width:300px;" id="concator" name="unit.concator" value="${unit.concator }"/>
    	       </i></li>
         	<li><span>联系电话：</span><i>
    	           <input type="text" class="popText Text02" style="width:300px;" id="phone" name="unit.telephone" value="${unit.telephone }"/>
    	       </i></li>
         	<li><span>联系地址：</span><i>
    	           <input type="text" class="popText Text02" style="width:300px;" id="address" name="unit.address" value="${unit.address }"/>
    	       </i></li>
    	       
         	<li><span>状态：</span><i>
         	    <s:if test="#request.id==null||#request.id==''"> 
                  <s:radio id="enable" name="unit.enable" list="#{'true':'可用','false':'不可用'}" listKey="key" listValue="value" value="true"/>   
                </s:if>
                <s:else>
                  <s:radio id="enable" name="unit.enable" list="#{'true':'可用','false':'不可用'}" listKey="key" listValue="value" value="#request.unit.enable"/>
                </s:else>
              </i></li>
         </ul>
         <!-- 执行按钮-->
				<div class="easyui-btn">
					<i>
		            	<input type="button" value="提交" class="btn-effect" onclick="saveMessage('${id}');"/>
		            	<input type="button" value="关闭" class="btn-effect" onclick="closeWin()"/>
            		</i>
				</div>
        
    </div>
</div>
</form> 
</div>
</div>   
</body>
</html>
