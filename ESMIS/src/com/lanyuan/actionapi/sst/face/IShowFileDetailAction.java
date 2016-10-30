package com.lanyuan.actionapi.sst.face;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.util.LawAndStandardUtil;
import com.lanyuan.assembly.util.WebSitePro;
import com.sun.xml.internal.fastinfoset.sax.Properties;

/**
 * 用来显示标准详情
 * @author Administrator
 *
 */
public class IShowFileDetailAction  extends ResultOperateAction{
	private static final long serialVersionUID = 1L;
	private String standardId;
	private String path;
	public String execute(){
		 HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String fileName = "pdf文件";
        String path = LawAndStandardUtil.getPath(standardId, "pdf");
        try{
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Disposition", "inline;filename="+fileName+".xls"); 
            request.getRequestDispatcher("/"+path).forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
		return this.SUCCESS;
	}
	public String showHtml() throws ServletException, IOException{
		 HttpServletRequest request = ServletActionContext.getRequest();
		 HttpServletResponse response = ServletActionContext.getResponse();
		if(standardId.equals("1") || standardId.equals("9")){
			return this.SUCCESS;
		}
		//String rootPath = WebSitePro.getWebRoot();
		String rootPath = WebSitePro.getStandard_filePath();
		
		path = LawAndStandardUtil.getPath(standardId, "html");
		File file = new File(rootPath+path);
		if(!file.exists()){
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
