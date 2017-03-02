<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    This is my JSP page. <br>
    <ul>
    	<li><input type="text"/></li>
    	<li><a href="<%=basePath%>/Student_add.action">添加学生https://github.com/Pengbowen/BMS.git</a></li>
    	<li><a href="<%=basePath%>/Student_delete.action">删除学生</a></li>
    </ul>
    <form action="<%=basePath%>/Student_add" method="post">
    <div>
    	姓名:<input type="text" name="name"> 
    </div>
    <div>
    	姓名:<input type="submit" value="提交"> 
    </div>
    </form>
  </body>
</html>
