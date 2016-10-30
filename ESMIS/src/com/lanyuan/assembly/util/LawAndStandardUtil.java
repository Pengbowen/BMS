package com.lanyuan.assembly.util;

import java.io.File;

/**
 * 
 * TODO 标准和法律法规的工具类
 *
 * @author zlc
 * @date 2016-9-24 下午3:39:08
 */
public class LawAndStandardUtil
{
    
    /**
     * 获取路径
     * 
     * @param str 编号
     * @param endStr 文件后缀名
     * @return
     */
    public static String getPath(String str,String endStr){
        if(str == null || str.length()<6){
            return null;
        }
        StringBuilder path = new StringBuilder();
        path.append(WebSitePro.get_file_path() + str.substring(0,6));
        path.append(File.separator+endStr+File.separator);
        if(endStr==""){
        	 path.append(str+endStr);
        }else{
        	 path.append(str+"."+endStr);
        }
       
        return path.toString();
    }
    
    /**
     * 移动文件
     * 
     * @param currentUrl 当前文件路径
     * @param assignUrl 移动位置路径（只需要路径）
     */
    public static boolean removeFile(String currentUrl,String assignUrl){
        try {  
            File afile = new File(currentUrl);  
            if (afile.renameTo(new File(assignUrl + afile.getName()))) {
                return true;
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return false;  
    }
    
    /**
     * 删除文件
     * 
     * @param url 文件路径
     */
    public static boolean deleteFiles(String url){
        try {  
            File afile = new File(url);  
            if (afile.delete()) {  
                return true;
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }
    
    public static void main(String[] args)
    {
//        LawAndStandardUtil.removeFile(null, null);
//        LawAndStandardUtil.deleteFiles(null);
        LawAndStandardUtil.getPath("910000001", "pdf"); //C:\Users\zfs\Desktop\910000\pdf\910000001.pdf
    }
    
}
