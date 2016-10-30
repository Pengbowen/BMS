package com.lanyuan.actionapi.userlogin;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.lanyuan.web.LoginAuthentication.LoginVerify;
/**
 * 手持设备的推出系统接口
 * 推出成功清掉session
 * 
 * @author ly-one
 *
 */
public class AdroidQuitAction extends ResultOperateAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户ID*/
	private String userId;
	
	public void quit()
	{
		if(userId==null){this.printString("2", "用户不存在！");return;}
		HttpServletRequest request = ServletActionContext.getRequest();
        
        HttpSession session = request.getSession();
        if(session.getAttribute(LoginUser.SESSIONID)==null)
        {
        	this.printString("1", "已退出系统");
        	return;
        }
        
        @SuppressWarnings("unchecked")
		Enumeration<String> em = session.getAttributeNames();
        boolean bRtn = LoginVerify.getInstance().logout( userId );
        if (bRtn)
        {
            while ( em.hasMoreElements() )
            {
                session.removeAttribute(em.nextElement().toString());
            }

            this.printString("1", "注销成功");
        }
        else
        {
        	this.printString("2", "注销失败");
        }
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
