
package com.lanyuan.actionapi.userlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.lanyuan.actionapi.basic.baseclasses.ResultCommonSearchAction;
import com.lanyuan.assembly.commonmodule.permission.ApplicationPermission;
import com.lanyuan.assembly.commonmodule.permission.RolePermission;
import com.lanyuan.assembly.commonmodule.permission.SinglePermission;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;

/**
 * pc端接口 根据用户ID获取该用户所拥有的权限
 * 
 * @author ly-one
 * 
 */
public class ISelectUserLimit extends ResultCommonSearchAction
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String userId;

    // 根据userId获取 登陆权限
    public void getLimits()
    {
        ServiceUser service = new ServiceUser();
        SingleUser user = service.selectById(userId);
        if (user == null)
        {
            this.printString(null, 0, "4", "该用户不存在！");
            return;
        }
        RolePermission rp = new RolePermission(ConstApplication.ApplicationName, user.getRoleId());
        Set<String> limits = rp.getPermissionByRoleId(user.getRoleId());
        if (limits == null || limits.isEmpty())
        {
            this.printString(null, 0, "5", "该用户没有任何权限！");
            return;
        }

        List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();

        ApplicationPermission appPermission = ApplicationPermission.getInstance();

        HashMap<String, String> hash = null;

        for (String limitId : limits)
        {
            for (SinglePermission cls : appPermission)
            {
                if (limitId.equals(cls.getPermissionId()))
                {
                    hash = new HashMap<String, String>();
                    hash.put("limitId", limitId);
                    hash.put("limitName", cls.getPerName());
                    datalist.add(hash);
                }
            }
        }

        this.printString(datalist, datalist.size(), "1", null);
    }

    /**
     * @return the userId
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
