package com.lanyuan.actionapi.userlogin;

import java.io.IOException;
import java.util.Properties;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
/**
 * 手持设备 获取默认的密码
 * 
 * @author ly-one
 *
 */
public class IAndroidPwd extends ResultOperateAction
{

    /**
     * gyy
     */
    private static final long serialVersionUID = 1L;
    
    private String userId;
    
    public void andpwd()
    {
        Properties prop = new Properties();
        try {
            prop.load(IAndroidPwd.class.getClassLoader()
                    .getResourceAsStream("config/website.config.properties"));
        } catch (IOException e) {
            
        } 
        this.printString("1", prop.getProperty("androidPwd"));
    }

    /**
     * @return 返回 userId
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId 设置
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    
}
