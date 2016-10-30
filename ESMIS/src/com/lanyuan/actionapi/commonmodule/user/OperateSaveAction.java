package com.lanyuan.actionapi.commonmodule.user;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
/**
 * 用户的操作类
 * 保存数据
 * 修改数据
 * 停用、启用
 * 删除用户
 * @author ly-one
 *
 */
public class OperateSaveAction extends ResultOperateAction
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private SingleUser user;
    private ServiceUser g_service = new ServiceUser();

    /* 数据添加 */
    public void add()
    {
        if (null != user)
        {
            //user.setUnitNo("10");
        	//暂时修改新增的人员编号80开头-----2016/04/25---wyl
        	  user.setUnitNo(user.getUnitNo());
        	boolean flag = g_service.isExistLoginName(user.getLoginName());
            if (flag)
            {
                this.printString("2", "登录名已存在！");
            }
            else
            {
                String str = "";
                str = g_service.add(user);
                if (!"".equals(str))
                {
                	this.printString("1", "添加成功");
                }
                else
                {
                	this.printString("2", "添加失败");
                }
            }
        }
    }

    /* 数据修改 */
    public void modify()
    {
    	if(id==null){this.printString("2", "用户不存在！");return;}
        boolean flag = g_service.modify(user, id);
        if (flag)
        {
        	this.printString("1", "修改成功");
        }
        else
        {
        	this.printString("2", "修改失败");
        }
    }

    /** 开启用户权限 */
    public void start()
    {
    	if(id==null){this.printString("2", "用户不存在！");return;}
        boolean flag = g_service.modifyLimitState(id, 1);
        if (flag)
        {
        	this.printString("1", "已开启该用户的权限");
        }
        else
        {
        	this.printString("2", "操作失败");
        }
    }

    /** 停用用户权限 */
    public void end()
    {
    	if(id==null){this.printString("2", "用户不存在！");return;}
        boolean flag = g_service.modifyLimitState(id, 0);
        if (flag)
        {
        	this.printString("1", "已停用该用户的权限");
        }
        else
        {
        	this.printString("2", "操作失败");
        }
    }

    /** 重置密码 */
    public void reset()
    {
    	if(id==null){this.printString("2", "用户不存在！");return;}
        boolean flag =  g_service.resetUserPwd(id, "111111");
        if (flag)
        {
        	this.printString("1", "密码重置成功");
        }
        else
        {
        	this.printString("2", "密码重置失败");
        }
    }

    /* 删除 */
    public void delete()
    {
    	if(id==null){this.printString("2", "用户不存在！");return;}
        boolean flag = g_service.delete(id);
        if (flag)
        {
        	this.printString("1", "删除成功");
        }
        else
        {
        	this.printString("2", "删除失败");
        }
    }

    /**
     * @return返回id
     */

    public String getId()
    {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    public SingleUser getUser()
    {
        return user;
    }

    public void setUser(SingleUser user)
    {
        this.user = user;
    }
}
