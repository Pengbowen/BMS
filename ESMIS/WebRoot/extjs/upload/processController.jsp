<%@ page language="java" import="java.util.*" contentType = "text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%
 //注意上面的抬头是必须的。否则会有ajax乱码问题。
 //从session取出uploadPercentage并送回浏览器
 Object percent = request.getSession().getAttribute("uploadPercentage");
 String msg = "";
 double d =(percent==null?0:(Double)percent);
 if(d<1)
 {
   //d<1代表正在上传，
   msg = "正在上传文件...";
   out.write("{success:true, msg: \""+msg+"\", percentage:\"" + d + "\", finished: false}");
 }
 else if(d>=1)
 {
	  //d>1 代表上传已经结束，开始处理分析，
	  //本例只是模拟处理，在session中放置一个processExcelPercentage，代表分析excel的进度。
	  msg = "正在分析处理文件...";
	  
	  //从session里获取上传后的文件名称
	  String  newFileName ="";
	  if(request.getSession().getAttribute("newFileName")!=null)
	    newFileName=request.getSession().getAttribute("newFileName").toString();
	 
	  boolean finished = false;
	  double processExcelPercentage = 0.0;
	  Object o = request.getSession().getAttribute("processExcelPercentage");
	  if(o==null)
	  {
      		processExcelPercentage = 0.0;
      		request.getSession().setAttribute("processExcelPercentage", 0.0);
	  }
	  else
	  {
	      //模拟处理,百分比每次递增0.2 
	      processExcelPercentage = (Double)o + 0.2;
	      request.getSession().setAttribute("processExcelPercentage", processExcelPercentage);
	       //当processExcelPercentage>1代表分析完毕。
	      if(processExcelPercentage>=1)
	      {
		     request.getSession().removeAttribute("uploadPercentage");
		     request.getSession().removeAttribute("processExcelPercentage");
		     //客户端判断是否结束的标志
		     finished = true;
      	  }
 	 }
 	 
    out.write("{success:true, msg: \""+msg+"\", percentage:\"" + processExcelPercentage + "\", finished: "+ finished +",newfile:\""+newFileName+"\"}");
    //注意返回的数据，success代表状态,percentage是百分比,finished代表整个过程是否结束。
 }
 out.flush();
%>
