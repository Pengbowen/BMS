package com.lanyuan.actionapi.userlogin;

import java.util.HashMap;
import java.util.Set;

import com.lanyuan.assembly.commonmodule.permission.RolePermission;
import com.lanyuan.web.LoginAuthentication.IApplicationLogin;
/**
 * 获取用户登录某个应用的权限、角色、session 
 * 登陆某个应用
 *
 */
public class ApplicationLoginImpl implements IApplicationLogin
{
	//获取权限
	public Set<String> getPermissionSet(com.lanyuan.web.LoginAuthentication.LoginUser loginUser) 
	{
		Set<String> limit=null;
		RolePermission rp=new RolePermission(ConstApplication.ApplicationName);
		
		if(getRoleSet(loginUser)!=null&&!getRoleSet(loginUser).isEmpty())
		{
			limit=rp.getPermissionByRoleId(getRoleSet(loginUser));
		}
		
		return limit;
	}
	
	//获取角色
	public Set<String> getRoleSet(com.lanyuan.web.LoginAuthentication.LoginUser loginUser)
	{
		return loginUser.getAppRoles(ConstApplication.ApplicationName);
	}

	//设置其他session信息
	public HashMap<String, Object> setOwnSession(com.lanyuan.web.LoginAuthentication.LoginUser loginUser) 
	{
		
		return null;
	}
}
