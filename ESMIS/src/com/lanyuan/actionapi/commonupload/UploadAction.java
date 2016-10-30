package com.lanyuan.actionapi.commonupload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;

public class UploadAction extends ResultOperateAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	//知识库service类
    private ServiceNetdisk netdiskService;
	private List<File> file;

	private List<String> fileFileName;

	private List<String> fileContentType;

	private String parentobjectNo;
	
	/**本action获取不到登录人的信息，这里接收前台传入的值*/
	private String modifyer;
	private String modifyerName;
	private String modifyIP;

	@Override
	public String execute() throws Exception {
		Upload upload = new Upload();
		SingleNetdisk singleNetdisk = new SingleNetdisk();
		/**objectNo 编码规则是：文件夹（D）+年月日(6)+六位随机数字(6) 如：D160823000001**/
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String time = sdf.format(new Date());
        int n = 0;
        String str = "";
        Integer chirldItemCount = 0; 
        for(int j = 0;j<6;j++){
            n = (int)(0+Math.random()*10);
            str += n + "";
        }
        String objectNo="";
        //获取文件后缀名称
        String objectSuffix = "";
        if(fileFileName!=null&&!fileFileName.isEmpty())
		{
			for(int j=0;j<fileFileName.size();j++)
			{
				String fileName = fileFileName.get(j);
				//截取文件后缀名称
				objectSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
				
			}
		}
		//String objectSuffix=fileFileName.substring(fileFileName.lastIndexOf(".")+1);
        /**当对象类型为0时即为文件F开头**/
        objectNo = "F" + time + str;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        String ParentFile= df.format(new Date());// new Date()为获取当前系统时间
		List<String> list = upload.upload(file, this.getFileFileName(),
				"/uploadfiles/network/"+ParentFile+"/",objectNo,objectSuffix);
		if(list!=null&&!list.isEmpty())
		{
			for(int i=0;i<list.size();i++)
			{
				File file1=new File(list.get(i));
				//获取文件名称
				String test = file1.getName().substring(0,file1.getName().lastIndexOf("."));
		        singleNetdisk.setObjectNo(objectNo);
		        singleNetdisk.setChirldItemCount(chirldItemCount);
		        singleNetdisk.setObjectName(test);
		        singleNetdisk.setParentobjectNo(parentobjectNo);
		        //定义储存服务器上的路径 （路径+文件编号+"."+后缀名称）
		        String newSavePath = "/uploadfiles/network/"+ParentFile+"/" +objectNo+"."+objectSuffix;
		        singleNetdisk.setObjectSavePath(newSavePath);
		        singleNetdisk.setObjectSuffix(objectSuffix);
		        singleNetdisk.setObjectType(0);
		        Date date=new Date();
		        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        
		        singleNetdisk.setModifyer(modifyer);
		        //解密接受前台传递的目录样式
				try {
					modifyerName = URLDecoder.decode(modifyerName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        singleNetdisk.setModifyerName(modifyerName);
		        singleNetdisk.setModifyTime(format.format(date));
		        singleNetdisk.setModifyIP(modifyIP);
		        netdiskService.addParentObject(singleNetdisk);
			}
		}
		return SUCCESS;
	}
	
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String getParentobjectNo() {
		return parentobjectNo;
	}

	public void setParentobjectNo(String parentobjectNo) {
		this.parentobjectNo = parentobjectNo;
	}

	/**
	 * @return the modifyer
	 */
	public String getModifyer() {
		return modifyer;
	}

	/**
	 * @param modifyer the modifyer to set
	 */
	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

	/**
	 * @return the modifyerName
	 */
	public String getModifyerName() {
		return modifyerName;
	}

	/**
	 * @param modifyerName the modifyerName to set
	 */
	public void setModifyerName(String modifyerName) {
		this.modifyerName = modifyerName;
	}

	/**
	 * @return the modifyIP
	 */
	public String getModifyIP() {
		return modifyIP;
	}

	/**
	 * @param modifyIP the modifyIP to set
	 */
	public void setModifyIP(String modifyIP) {
		this.modifyIP = modifyIP;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
