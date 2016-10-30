package com.lanyuan.actionapi.commonexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
public class ICommonDownLoad extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    /**
     * 文件名
     */
    private String fileName;
    private String suffixType;
    public String commonDownLoad()
    {
        if (StringUtils.isEmpty(fileName))
        {
            this.printString("2", "下载文件名为空！");
            return null;
        }
        if (StringUtils.isEmpty(suffixType))
        {
            this.printString("2", "文件后缀类型为空！");
            return null;
        }
    	try {
			fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
			suffixType = java.net.URLDecoder.decode(suffixType, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        File file = new File(getWebRoot() + "uploadfiles/toolsDownload/" + fileName + suffixType);
        System.out.println(file);
        if (file.exists())
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
            String dateTime = df.format(new Date());// new Date()为获取当前系统时间
            try
            {
                request.setCharacterEncoding("utf-8");
                if(suffixType.equals(".zip"))
                {
                    response.setHeader("Content-Disposition","inline;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8") + suffixType);  
                }else
                {
                    response.setHeader("Content-Disposition","inline;filename="+ java.net.URLEncoder.encode(dateTime+fileName, "UTF-8") + suffixType);
                }
                request.getRequestDispatcher("/uploadfiles/toolsDownload/" + fileName +suffixType).forward(request,response);
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

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getSuffixType()
    {
        return suffixType;
    }

    public void setSuffixType(String suffixType)
    {
        this.suffixType = suffixType;
    }
}
