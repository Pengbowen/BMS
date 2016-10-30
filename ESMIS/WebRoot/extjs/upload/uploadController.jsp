<%@ page language="java" import="java.util.*, java.io.*" contentType = "text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="java.text.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%

//文件保存目录路径
//String relativePath="uploadfiles/files";

String relativePath=request.getParameter("fileURL");
String savePath = pageContext.getServletContext().getRealPath("/"+relativePath+"/");
File fileData = new File(savePath);
if(!fileData.exists())fileData.mkdirs();

SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
String currentTime = sdf.format(new Date());
String newFileName="";
newFileName = request.getParameter("fileName");

//注意上面的import的jar包是必须的下面是使用apache commons fileupload接收上传文件；
FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("UTF-8");
//因为内部类无法引用request，所以要实现一个。
class MyProgressListener implements ProgressListener{
	 private HttpServletRequest request = null;
	 MyProgressListener(HttpServletRequest request){
	      this.request = request;
	 }
	 public void update(long pBytesRead, long pContentLength, int pItems) {
		  double percentage = ((double)pBytesRead/(double)pContentLength);
		  //上传的进度保存到session，以便processController.jsp使用
		  request.getSession().setAttribute("uploadPercentage", percentage);
	 }
}
upload.setProgressListener(new MyProgressListener(request));
List items = upload.parseRequest(request);
try{
	Iterator iter = items.iterator();
	while (iter.hasNext()) {
	    FileItem item = (FileItem) iter.next();
	    if (item.isFormField()){
	 		//判断该表单项是否是普通类型
	    } else {
	        //否则该表单项是file 类型的
	        String fileName = item.getName();
	        if(fileName.lastIndexOf(".")>=0)
	        {
		        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
		        if(newFileName != null && newFileName != ""){
		        	newFileName +=  fileSuffix;
		        }else{
		        	newFileName=currentTime+fileSuffix;
		        }
		        
		       
		        File uploadedFile = new File(savePath+"/"+newFileName);
		        item.write(uploadedFile);
	        }
	    }
	}
	//将新生成的文件 存入session中 便于processController调用
	request.getSession().setAttribute("newFileName", relativePath+"/"+newFileName);
}catch(Exception e){
    request.getSession().setAttribute("newFileName", "");
}
out.flush();
%>
