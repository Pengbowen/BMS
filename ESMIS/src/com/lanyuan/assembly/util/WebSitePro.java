package com.lanyuan.assembly.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 获取website.config.properties
 * @author ly-two
 * @date 2016-7-29 下午5:19:37
 */
public class WebSitePro
{
	public static String fileName = "conf/website.properties";

    public static Properties getProp()
    {
        Properties prop = new Properties();
        prop = getProperties(getWebRoot() + fileName);
        return prop;
    }

    /**
     * 获取访客数量
     */
    public static int get_visiterCount()
    {
        if (StringUtils.isBlank(getProp().getProperty("visiterCount")))
        {
            return 0;
        }
        else
        {
            return Integer.parseInt(getProp().getProperty("visiterCount"));
        }
    }

    /**
     * 读取配置文件
     */
    public static Properties getProperties(String fileName)
    {
        if (StringUtils.isBlank(fileName))
        {
            return null;
        }
        Properties pro = null;
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(fileName);
            pro = new Properties();
            pro.load(in);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return pro;
    }

    /**
     * 更新properties文件的键值对
     */
    public static boolean updateProperties(String fileName, String key, String value)
    {
        if (!StringUtils.isBlank(key))
        {
            Properties prop = new Properties();
            try
            {
                InputStream fis = new FileInputStream(fileName);
                prop.load(fis);
                OutputStream fos = new FileOutputStream(fileName);
                prop.setProperty(key, value);
                prop.store(fos, ".....SavaTime.....");
                fos.close();
            }
            catch (Exception e)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取WebRoot根目录
     */
    public static String getWebRoot()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null)
        {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        java.net.URL url = classLoader.getResource("");
        String ROOT_CLASS_PATH = url.getPath() + File.separator;
        File rootFile = new File(ROOT_CLASS_PATH);
        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + File.separator;
        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + File.separator;
        return SERVLET_CONTEXT_PATH.replaceAll("%20", " ");
    }
	/**
	 * @author：mbz
	 * @Description：获取高亮显示样式
	 * @Date：2016年9月12日
	 * @return
	 */
	public static String get_html_format_began(){
		return getProp().getProperty("html_format_began");
	}
	public static String get_html_format_end(){
		return getProp().getProperty("html_format_end");
	}
	/**
	 * @author：mbz
	 * @Description：获取显示字符数
	 * @Date：2016年9月12日
	 * @return
	 */
	public static String get_max_show(){
		return getProp().getProperty("max_show");
	}
	/**
	 * @author：mbz
	 * @Description：获取索引存放地址
	 * @Date：2016年9月6日
	 * @return
	 */
	public static String get_index_url(){
		return getProp().getProperty("index_url");
	}
	public static String get_lawsindex_url(){
		return getProp().getProperty("lawsindex_url");
	}
	/**
	 * 获取法律法规，标准的文件根目录
	 * 
	 * @return
	 */
	public static String get_file_path(){
	    return getProp().getProperty("file_path");
	}
	/**
     * 获取移动端指定文件目录
     * 
     * @return
     */
    public static String get_phone_file_path(){
        return getProp().getProperty("phone_file_path");
    }
    public static String getStandard_filePath(){
        return getProp().getProperty("standard_filePath");
    }
	
}
