 package com.lanyuan.actionapi.netdisk;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;

/**
 * @Description:下载文件
 * @author WYL
 * @date 2016年8月26日 上午10:44:19
 */
public class IDownloadNetDiskAction
{
    @Autowired
    private ServiceNetdisk netdiskService;
    private String objectNo;
    private String objectName;
    private String objectSuffix;
    private String objectSavePath;
    
    private String errorMessage = "";
    
    public void downloadNetDisk()throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
        SingleNetdisk single = new SingleNetdisk();
        single=netdiskService.selectParentObjectByNo(objectNo);
        setInfoData(single);
        //将服务器中文件名称改为中文文件名
        objectName =  java.net.URLEncoder.encode(objectName, "UTF-8");
        request.setCharacterEncoding("utf-8");
        //定义路径，判断文件是否存在于服务器中
        File file = new File(getWebRoot() + objectSavePath);
        if (file.exists())
        {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;filename="+ objectName +"."+ objectSuffix);
        request.getRequestDispatcher(objectSavePath).forward(request,
                response);
        }else{
        	errorMessage = "下载失败:文件不存在！";
        	HttpServletRequest req =ServletActionContext.getRequest();
			req.getRequestDispatcher("/netdisk/error.jsp").forward(req,response);
			return;
        }
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
    
    
    //获取下载路径，及文件名称
    public void setInfoData(SingleNetdisk single){
        this.objectNo = single.getObjectNo();
        this.objectName = single.getObjectName();
        this.objectSuffix = single.getObjectSuffix();
        this.objectSavePath = single.getObjectSavePath();
    }
    
	/**
	 * @return the objectNo
	 */
	public String getObjectNo() {
		return objectNo;
	}
	/**
	 * @param objectNo the objectNo to set
	 */
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}
	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	/**
	 * @return the objectSavePath
	 */
	public String getObjectSavePath() {
		return objectSavePath;
	}
	/**
	 * @param objectSavePath the objectSavePath to set
	 */
	public void setObjectSavePath(String objectSavePath) {
		this.objectSavePath = objectSavePath;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
