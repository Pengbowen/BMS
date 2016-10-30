package com.lanyuan.actionapi.standardlibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.opensymphony.xwork2.Action;

public class IDownloadStandard extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;
    /**
     * 文件路径
     */
    private String filePath;
    private String fileName;
    private String standardNo;
    private String standardId;

	public String downLoad(){
    	if(standardId!=null||standardNo!=null){
    		SingleStandardLibraryData singData = new SingleStandardLibraryData();
    		   if (StringUtils.isBlank(standardId))
               {
                    singData= standardLibraryService.getDataByStandardNo(standardNo);
               }
               else
               {
                   // 如果lawId不为空，则根据lawId查询详情
               	singData = standardLibraryService.selectById(standardId);
               }
    		   String[] arr=singData.getFilePath().split("/");
    		   fileName=arr[arr.length-1];
    		   filePath=singData.getFilePath();
        if (StringUtils.isBlank(filePath))
        {
            this.printString("2", "文件路径为空！");
            return Action.SUCCESS;
        }
        if (StringUtils.isBlank(fileName))
        {
            this.printString("2", "文件名称为空！");
            return Action.SUCCESS;
        }
        try {
            filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
            fileName=java.net.URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(getWebRoot() +"/"+filePath);
        if (file.exists())
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            try
            {
                request.setCharacterEncoding("utf-8");
                String name = file.getName();
                String suffixType=name.substring(name .lastIndexOf("."));
                response.setHeader("Content-Disposition","attachment;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8") + suffixType);  
                request.getRequestDispatcher("/"+filePath).forward(request,response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            this.printString("2", "文件不存在");
            return null;
        }
    	}
        return null;
    }
    /**
     * 读取WebRoot的路径下文件
     */
    public String readConf(String path) throws IOException
    {
        InputStream inputStream = new FileInputStream(new File(path));
        InputStreamReader file = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(file);
        String fileContent = "";
        String tempString = null;
        while ((tempString = reader.readLine()) != null)
        {
            fileContent += tempString;
        }
        reader.close();
        return fileContent;
    }

    /**
     * 获取WebRoot的路径
     */
    public String getWebRoot()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null)
        {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        java.net.URL url = classLoader.getResource("");
        String ROOT_CLASS_PATH = url.getPath() + "/";
        File rootFile = new File(ROOT_CLASS_PATH);
        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";
        return SERVLET_CONTEXT_PATH.replaceAll("%20", " ");
    }
    public String getFilePath()
    {
        return filePath;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getStandardNo() {
		return standardNo;
	}
	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
}
