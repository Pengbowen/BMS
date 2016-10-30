<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加标准</title>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
</head>
<body>
				
				<span>标&nbsp;&nbsp;准&nbsp;&nbsp;编&nbsp;&nbsp;号：&nbsp;</span>
				 <input class="easyui-textbox" prompt="请输入标准编号" id="standardNo" name="standardNo"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				
				 <span>标&nbsp;&nbsp;准&nbsp;&nbsp;名&nbsp;&nbsp;称：&nbsp;</span>
				 <input class="easyui-textbox" prompt="请输入标准名称" id="standardName" name="standardName"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				
				<span>替&nbsp;&nbsp;代&nbsp;&nbsp;标&nbsp;&nbsp;准：&nbsp;</span>
				 <input class="easyui-textbox" prompt="请输入替代标准" id="oldStandardNo" name="oldStandardNo"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" 
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				
				<span>标&nbsp;&nbsp;准&nbsp;&nbsp;类&nbsp;&nbsp;别：&nbsp;</span>
				<input class="easyui-combobox" prompt="请选择标准类型" inQuery="true" panelHeight="auto" 
						id="standardCategory" name="standardCategory"  editable="true" style="width:200px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
					</input>
				
				s
</body>
</html>