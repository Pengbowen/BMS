package com.lanyuan.actionapi.sst.standardlist;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.opensymphony.xwork2.Action;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class IDownloadAction extends ResultSearchAction{
	
	@Autowired
	private ServiceSSTStandardList standardService;
	
	private static final long serialVersionUID = 1L;
	public static String downloadName =  "批量添加标准模板";
	
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
    /*if(file.exists()){
    	file.delete();
    }*/
        //创建工作薄
        WritableWorkbook workbook;
            try {
				workbook = Workbook.createWorkbook(file);
				
				/**
				 * 1.查询所有的标准
				 * 2.循环写入xls文件
				 * 		需要字段,标准编号,标准名称,标准类别
				 * 		0,0|1,0|2,0
				 * 		0,1|1,1|2,1
				 * 		0,2|1,2|2,2
				 * 		0,3|1,3|2,3
				 * 		0,4|1,4|2,4
				 * 		0,5|1,5|2,5
				 * 3,下载
				 * 
				 */
			List<SingleSSTStandardList> standardList=  standardService.selectList(null, null);
			 //创建一个标签页
            WritableSheet sheet = workbook.createSheet("批量调整标准顺序",1);
            //设置单元格宽度
            sheet.setColumnView(0, 20);//第一列
            sheet.setColumnView(1, 30);//第二列
            sheet.setColumnView(2, 30);//第三列
            sheet.setColumnView(3, 50);//第三列
            
            Label p0 = new Label(0,0,"标准Id");
            sheet.addCell(p0);
			  Label p1 = new Label(1,0,"标准编号");
	            sheet.addCell(p1);
	            
	            Label p2 = new Label(2,0,"标准类别");
	            sheet.addCell(p2);
	            Label p3 = new Label(3,0,"标准名称");
	            sheet.addCell(p3);
			
			/**
			 * 循环取到的标准，并加入xls表格当中
			 */
			for(int i=1;i<=standardList.size();i++){
				SingleSSTStandardList single = standardList.get(i-1);
				String id = single.getStandardId();
				String no = single.getStandardNo();//标准编号
				String category =single.getStandardCategory();//标准类别
				String name = single.getStandardName();//标准名称
				 Label pId = new Label(0,i,id);
				  sheet.addCell(pId);
				  Label pNo = new Label(1,i,no);
				  sheet.addCell(pNo);
				  Label pCategory = new Label(2,i,category);
				  sheet.addCell(pCategory);
				  Label pName = new Label(3,i,name);
				  sheet.addCell(pName);
				 
			}
			//锁定表,使表不可编辑
			// sheet.getSettings().setProtected(true);
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
	    
	