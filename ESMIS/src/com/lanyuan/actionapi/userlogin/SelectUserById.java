package com.lanyuan.actionapi.userlogin;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.commonmodule.permission.ServiceRole;
import com.lanyuan.assembly.commonmodule.permission.SingleRole;
import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;

/**
 * 手持设备、pc端 根据用户ID获取用户的信息
 * 
 * @author ly-one
 * 
 */
public class SelectUserById extends ResultDetailAction
{
    private static final long serialVersionUID = 1L;
    /** 用户ID */
    private String userId;

    /**
     * 获取用户的信息 以json格式数据 输出到 终端上
     */
    public void getUser()
    {
        if (StringUtils.isBlank(userId))
        {
            this.printString(null, "3", "传递用户ID不正确！");
            return;
        }
        ServiceUser service = new ServiceUser();
        SingleUser single = service.selectById(userId);
        if (single == null)
        {
            this.printString(null, "4", "没查询到数据");
            return;
        }
        ServiceUnit serviceUnit = new ServiceUnit();
        SingleUnit unit = serviceUnit.selectById(single.getUnitNo());

        HashMap<String, Object> detailData = new HashMap<String, Object>();
        detailData.put("loginName", single.getLoginName());
        detailData.put("realName", single.getRealName());
        detailData.put("roleId", single.getRoleId());

        ServiceRole serviceRole = new ServiceRole(ConstApplication.ApplicationName);
        SingleRole singleRole = serviceRole.getRoleByRoleId(single.getRoleId());
        detailData.put("unitNo", single.getUnitNo());
        detailData.put("roleName", singleRole != null ? singleRole.getRoleName() : "");
        detailData.put("sex", single.getSexInfo());
        if(single.getSexInfo()==null){
        	detailData.put("sex", "女");
        }else{
        	if("0".equals(single.getSexInfo())){
        		detailData.put("sex", "女");
        	}else{
        		detailData.put("sex", "男");
        	}
        }
        detailData.put("unitName", unit == null ? "" : unit.getUnitName());
        this.printString(detailData, "1", null);
    }

    public void getLoginLimit()
    {
        if (StringUtils.isBlank(userId))
        {
            this.printString(null, "3", "传递用户ID为空");
            return;
        }

        ServiceUser service = new ServiceUser();
        SingleUser user = service.selectById(userId);
        if (user == null)
        {
            this.printString(null, "4", "该用户不存在！");
            return;
        }

        ServiceRole serviceRole = new ServiceRole(ConstApplication.ApplicationName);
        SingleRole role = serviceRole.getRoleByRoleId(user.getRoleId());
        HashMap<String, Object> detailData = new HashMap<String, Object>();
        if ("1".equals(role.getRoleType()))
        {
            detailData.put("isLimit", "1");
        }
        else
        {
            detailData.put("isLimit", "0");
        }
        this.printString(detailData, "1", null);
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
