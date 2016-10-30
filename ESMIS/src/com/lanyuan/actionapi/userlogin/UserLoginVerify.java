
package com.lanyuan.actionapi.userlogin;

import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
import com.lanyuan.assembly.utils.encrypt.MD5Util;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.lanyuan.web.LoginAuthentication.LoginVerify;

/**
 * 登陆验证类 方法： 登陆 修改密码 是否能访问某个应用
 * 
 */
public class UserLoginVerify extends LoginVerify
{
    // 登陆
    @Override
    public LoginUser signIn(String loginName, String password)
    {
        ServiceUser service = new ServiceUser();
        boolean flag = false;
        try
        {
            flag = service.isExistLoginName(loginName);
        }
        catch (Exception e)
        {
            this.setErrorCode(2);
            this.setErrorMessage("数据库连接失败！");
            return null;
        }

        LoginUser loginuser = null;
        if (flag)
        {
            ConditionGroup cond = new ConditionGroup();
            cond.addWithAnd(new ConditionNormal(ServiceUser.MappingList.loginName.name(), loginName));

            List<SingleUser> listData = service.selectByCondition(cond, null);

            SingleUser user = listData.get(0);
            // 判断密码是否 相等
            if (!user.getPassword().equals(MD5Util.getMD5ofStr("lanyuan" + user.getUserId() + password)))
            {
                this.setErrorCode(2);
                this.setErrorMessage("密码不正确");
            }
            else
            {
                if (user.getLoginLimit())
                {
                    loginuser = GetLoginUser.getLoginUser(user.getUserId());
                }
                else
                {
                    this.setErrorCode(3);
                    this.setErrorMessage("该用户已停用，没有访问权限！");
                }
            }
        }
        else
        {
            this.setErrorCode(2);
            this.setErrorMessage("登陆名不存在");
        }

        return loginuser;
    }

    // 修改密码
    @Override
    public boolean modifyPassword(String userId, String newPassword, String oldPassword)
    {
        ServiceUser service = new ServiceUser();
        SingleUser user = service.selectById(userId);
        if(MD5Util.getMD5ofStr("lanyuan" + userId + oldPassword).equals(user.getPassword()))
        {
            user.setPassword(newPassword);
            return service.modify(user, userId);
        }
        else
        {
            return false;
        }
    }

    // 是否能访问某个应用
    @Override
    public boolean allowAccess(String userId, String appName)
    {

        return true;
    }

    // 退出
    @Override
    public boolean logout(String userId)
    {

        return true;
    }
}
