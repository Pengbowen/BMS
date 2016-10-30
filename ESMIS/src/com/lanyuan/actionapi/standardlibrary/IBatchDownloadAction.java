package com.lanyuan.actionapi.standardlibrary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.util.EncryptUtil;
import com.lanyuan.assembly.util.LawAndStandardUtil;
import com.lanyuan.assembly.util.WebSitePro;

public class IBatchDownloadAction extends ResultOperateAction{
	private static final long serialVersionUID = 1L;
	private String standardId;
	private String url;
	private String key;
	private String msg;
	//打包下载,测试通过
	public String download() throws ServletException, IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<String> urlList = new ArrayList<String>();
		List<String> keyList = new ArrayList<String>();
		
		
		//url="daJ6nm/fJTZ8iHqiVy4XFlCUHNKfRI2Fs5n2nrhZ+TVeKBQ+PKF4fUUZlLvJ5eDC,Gvfs4EBVIZgfbC9auKQOwllPRmiJ4j0xoXPywFv4sbSDKe/brt+yLXaP64KIUd1r,Fv7jL5bkxr8a7wHcflU2OP0sjapqss2KuAtcbNz+Wmay1tSso8xuiYGrt1XxkFum";
		//key="1A3Q2,p552G,s2f4Q";
		/**
		 * 加密字符串可能会出现空格,对加密字符串进行处理,删除空格
		 */
		//将加密字符串分割,并放入list
		if(url !=null && !url.isEmpty() && key!=null && !key.isEmpty()){
			if(url.contains(",")){ 
			urlList = Arrays.asList(url.split(","));
			}else{
			urlList.add(url);
			}
			if(key.contains(",")){ 
				 keyList = Arrays.asList(key.split(","));
				}else{
				keyList.add(key);
				}
		}else{
			//如果url为空或者key为空
			msg="参数错误";
			return this.ERROR;
		}
		if(urlList.size()!=keyList.size()){
			msg="参数错误";
			  return this.ERROR;
		}
		List<File> files = new ArrayList<File>();
		
		for(int i = 0;i<urlList.size();i++){
			String u = urlList.get(0);
			String k = keyList.get(0);
			String filePath = "";
			try {
				if(u.contains(" ")){
					u.replace(" ", "");
				}
			    String id = EncryptUtil.decrypt(u, k);
			    filePath = LawAndStandardUtil.getPath(id, "pdf");
			    //String rootPath = WebSitePro.getWebRoot();
			    String rootPath = WebSitePro.getStandard_filePath();
				if(filePath!=null){
				    File file = new File(rootPath+filePath);
				    if(file.exists()){
				        files.add(new File(rootPath+filePath));
				    }else{
				    	//如果文件不存在,转到错误界面
				    	msg="文件不存在";
				        return this.ERROR;
				    }
					
				}
			} catch (Exception e) {
				msg="文件不存在";
				e.printStackTrace();
				return this.ERROR;
			}
		}
//		for(int i=0;i<urls.length;i++){
//			String u = urls[i];
//			String k = keys[i];
//			String filePath = "";
//			try {
//				filePath = EncryptUtil.decrypt(u, k);
//				if(filePath!=null){
//					files.add(new File(filePath));
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			response = downLoadFiles(files,request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}
	
	//文件打包下载
    public static HttpServletResponse downLoadFiles(List<File> files,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            //List<File> files = new ArrayList<File>();
     
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip*/
            File file = new File("d:/biaozhun.zip");
            if (!file.exists()){   
                file.createNewFile();   
            }
            response.reset();
          //  response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
           ZipOutputStream zipOut 
            = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
           zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            downloadZip(file,response);
          // request.getRequestDispatcher("d:/biaozhun.zip").forward(request, response);
        }catch (Exception e) {
                e.printStackTrace();
            }
            /**直到文件的打包已经成功了，
             * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
             * 稍后会呈现出来，接下来的就是往客户端写数据了*/
        return response ;
    }
  /**
     * 把接受的全部文件打成压缩包 
     * @param List<File>;  
     * @param org.apache.tools.zip.ZipOutputStream  
     */
    public static void zipFile
            (List<File> files,ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    public static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
        try {
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();

        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");

//如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(file.getName(), "UTF-8"));
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        } catch (IOException ex) {
        ex.printStackTrace();
        }finally{
             try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return response;
    }

/**  
     * 根据输入的文件与输出流对文件进行打包
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(File inputFile,
            ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象   
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void downloadOne() throws ServletException, IOException{
     String filePath = 	LawAndStandardUtil.getPath(standardId, "pdf");
    //String fileName = standardId+".pdf";
    HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request =ServletActionContext.getRequest();
	File file = new File(filePath);
	downloadZip(file, response);
//	request.setCharacterEncoding("utf-8");
//	response.setContentType("application/octet-stream");
//    response.setHeader("Content-Disposition", "attachment;filename=" +fileName);
    request.getRequestDispatcher(filePath).forward(request, response);
    }
    
    
public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}

public String getStandardId() {
	return standardId;
}

public void setStandardId(String standardId) {
	this.standardId = standardId;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}



}
