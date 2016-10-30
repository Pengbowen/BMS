package com.lanyuan.actionapi.standardlibrary;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.util.LawAndStandardUtil;
import com.lanyuan.assembly.util.StrUtil;
import com.lanyuan.assembly.util.WebSitePro;



public class IDownloadToPhone extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    private String standardId;

    private String str;//文件后缀
    
    private Integer fileExists;
    
    public void down(){
         
        if(fileExists == null || !StrUtil.isNumber(fileExists.toString())) return;
        
        switch (fileExists)
        {
        case 0: str = ""; break;
        case 1: str = "epub"; break;
        case 2: str = "pdf"; break;
        }
        
        
        String path = LawAndStandardUtil.getPath(standardId, str);
        if(path.lastIndexOf("\\") != path.length()-1) {
            path+="\\";
        }
            
        String phonePath = WebSitePro.get_phone_file_path();
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(path);
            BufferedInputStream bufis = new BufferedInputStream(fis);
     
            FileOutputStream fos = new FileOutputStream(phonePath+path.substring(path.lastIndexOf("\\")+1,path.length()));
            BufferedOutputStream bufos = new BufferedOutputStream(fos);
     
            int len = 0;
            while ((len = bufis.read()) != -1) {
                bufos.write(len);
            }
            
            bufis.close();
            bufos.close();
        }
        catch (IOException  e)
        {
            e.printStackTrace();
        }
        
    }

    /**
     * @return 返回 fileExists
     */
    public Integer getFileExists()
    {
        return fileExists;
    }

    /**
     * @param fileExists 设置
     */
    public void setFileExists(Integer fileExists)
    {
        this.fileExists = fileExists;
    }

    /**
     * @return 返回 standardId
     */
    public String getStandardId()
    {
        return standardId;
    }

    /**
     * @param standardId 设置
     */
    public void setStandardId(String standardId)
    {
        this.standardId = standardId;
    }
    
}
