package com.lanyuan.actionapi.userlogin;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
import com.lanyuan.web.LoginAuthentication.LoginUser;

/**
 * 根据用户ID获取 登录人信息
 * 
 * @author ly-one
 * 
 */
public class GetLoginUser 
{
	
	/**
	 * 根据用户ID获取 登录人信息
	 * @param userId 用户ID
	 * @return LoginUser
	 */
	public static LoginUser getLoginUser(String userId) 
	{
		if (userId == null)
			return null;
		ServiceUser service = new ServiceUser();
		SingleUser user = service.selectById(userId);
		if (user == null)
			return null;

		LoginUser loginUser = new LoginUser();
		loginUser.setLoginName(user.getLoginName());
		loginUser.setRealName(user.getRealName());
		loginUser.setUserId(userId);
		loginUser.setUnitNo(user.getUnitNo());
		Set<String> roles = new HashSet<String>();
		roles.add(user.getRoleId());
		loginUser.addRoles(ConstApplication.ApplicationName, roles);
		// 获取部门信息
		ServiceUnit su = new ServiceUnit();
		if (!StringUtils.isBlank(user.getUnitNo())) {
			SingleUnit clsUnit = su.selectById(user.getUnitNo());
			if (clsUnit != null) {
				loginUser.setUnitName(clsUnit.getUnitName());
			}
		}
		return loginUser;
	}

}
