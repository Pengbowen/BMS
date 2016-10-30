
package com.lanyuan.actionapi.userlogin;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.lanyuan.web.LoginAuthentication.LoginVerify;

/**
 * 修改密码
 * 
 * @author cdh
 * @date 2015-12-31 上午11:42:53
 */
public class IModifyPwd extends ResultOperateAction

{

    /**
     * 
     */
    private static final long serialVersionUID = -7410663142506727968L;
    private String userName; // 用户id
    private String Oldpassword;// 老密码
    private String Newpassword;// 新密码

    public void changePwd() throws Exception
    {
        LoginVerify login = LoginVerify.getInstance();
        String id = null;
        LoginUser loginUser = login.signIn(userName, Oldpassword);

        if (loginUser != null)
        {
            id = loginUser.getUserId();
        }

        if (id == null || "".equals(id))
        {
            this.printString("2", "修改密码提交的数据不正确");
            return;
        }

        if (Oldpassword == null || "".equals(Oldpassword))
        {
            this.printString("3", "老密码不能为空");
            return;
        }

        if (Newpassword == null || "".equals(Newpassword))
        {
            this.printString("4", "新密码不能为空");
            return;
        }

        boolean bRtn = login.modifyPassword(id, Newpassword, Oldpassword);
        if (bRtn)
        {
            this.printString("1", "修改成功");
        }
        else
        {
            this.printString("6", "修改失败");
        }
    }

    /**
     * @return the oldpassword
     */
    public String getOldpassword()
    {
        return Oldpassword;
    }

    /**
     * @param oldpassword the oldpassword to set
     */
    public void setOldpassword(String oldpassword)
    {
        Oldpassword = oldpassword;
    }

    /**
     * @return the newpassword
     */
    public String getNewpassword()
    {
        return Newpassword;
    }

    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword)
    {
        Newpassword = newpassword;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
