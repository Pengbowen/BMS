package com.lanyuan.actionapi;

import java.util.Properties;

import com.lanyuan.assembly.basic.database.ConnectionUtils;
/**
 * 
 * 配置参数帮助类<br/>
 * 说明：定义配置参数的枚举，以及获取配置参数信息。<br/>
 * 不同的项目定义的枚举参数可能不太一样，可手工加入
 * 
 * @author qinye
 * @date 2015年8月14日 下午6:37:30
 */
public class ConfigHelper
{
    /**
     * 配置文件的所在位置<br/>
     * 一般放置在src/config/目录下
     */
    private static String sFileName = "config/website.config.properties"; 

    /**
     * 
     * 配置参数的枚举集合
     *
     * @author qinye
     * @date 2015年8月14日 下午6:36:06
     */
    public enum KeyName
    {
        /**
         * 上传文件类型
         */
        Upload_FileType("upload_filetype"),
        /**
         * 上传文件大小
         */
        Upload_FileSize("upload_filesize"),
        /**
         * 上传图片是否加水印
         */
        IsADDImageMarkLogo("isADDImageMarkLogo"),
        /**
         * 管理员的sessionid前缀
         */
        SessionId_Admin("sessionid_admin"),
        /**
         * 会员的sessionid前缀
         */
        SessionId_Member("sessionid_member"),
        /**
         * 调查问卷的网站地址
         */
        SuveryWebUrl("suveryweburl");

        private String value;

        KeyName(String key)
        {
            value = key;
        }

        public String getKeyName()
        {
            return value;
        }

    }

    /**
     * 通过key，获取配置文件的value
     * 
     * @param key 配置参数的key
     * @return 返回配置参数的value
     */
    public static String getPropertiesValue(KeyName key)
    {
        String value = "";
        // 创建配置对象
        Properties prop = new Properties();
        try
        {
            prop.load(ConnectionUtils.class.getClassLoader().getResourceAsStream(sFileName));
            value = prop.getProperty(key.getKeyName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return value;
    }
    
/*    public static void main(String[] args) {
		ServiceDictionary dataOpt = new ServiceDictionary();		
	}*/

}
