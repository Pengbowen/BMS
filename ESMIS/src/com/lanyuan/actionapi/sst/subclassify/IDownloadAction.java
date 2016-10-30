package com.lanyuan.actionapi.sst.subclassify;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.opensymphony.xwork2.Action;

public class IDownloadAction extends ResultSearchAction{
	
	private static final long serialVersionUID = 1L;
	public static String downloadName =  "子分类上传模板";
//	public static String path="/uploadfiles";
	
	public String execute(){
		  HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
		String UrldownloadName = "";
	try { 
		UrldownloadName = URLEncoder.encode(downloadName,"UTF-8");
		//downloadName = new String(downloadName.getBytes("gb2312"),"UTF-8");
    } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
    }
		
	System.out.println("downloadName="+downloadName);
	System.out.println("UrldownloadName="+UrldownloadName);
	ServletContext sctx =ServletActionContext.getServletContext();
    String path=sctx.getRealPath("/uploadfiles");
    
    File file = new File(path+"/"+downloadName+".xls");
        //创建工作薄
        WritableWorkbook workbook;
            try {
				workbook = Workbook.createWorkbook(file);
				 //创建一个标签页
	            WritableSheet sheet = workbook.createSheet("子分类上传模板",1);
	            
	            Label p0 = new Label(0,0,"所属父级编号");
	            sheet.addCell(p0);
	            
	            Label p1 = new Label(1,0,"子分类名称");
	            sheet.addCell(p1);
	            
                workbook.write();
                workbook.close();
	            
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
        
      
        try{
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "inline;filename="+UrldownloadName+".xls"); 
            request.getRequestDispatcher("/uploadfiles/"+downloadName+".xls").forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
		
        return Action.SUCCESS;
	 }
}
	    
	