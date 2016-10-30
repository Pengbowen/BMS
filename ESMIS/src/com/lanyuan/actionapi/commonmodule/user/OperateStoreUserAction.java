package com.lanyuan.actionapi.commonmodule.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
import com.lanyuan.assembly.util.StrUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;

public class OperateStoreUserAction extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String realName;// 姓名
	private String sexInfo;// 性别
	private String loginName;// 登录名称
	private String password;// 登录密码
	private ServiceUser g_service = new ServiceUser();

	/* 数据添加 */
	public void add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		LoginUser operatorUser = null;
		if (request.getSession().getAttribute(LoginUser.SESSIONID) == null) {
			if (operatorUser == null) {
				printString("-4", "非法访问！");
				return;
			}
		} else {
			operatorUser = (LoginUser) request.getSession().getAttribute(
					LoginUser.SESSIONID);
		}
		if(StrUtil.isEmpty(loginName)||StrUtil.isEmpty(password)){
			printString("-1", "登录名和密码不能为空！");
			return;
		}
		
		SingleUser clsUser = new SingleUser();
		clsUser.setUnitNo(operatorUser.getUnitNo());
		clsUser.setRealName(realName);
		clsUser.setSexInfo(sexInfo);
		clsUser.setLoginName(loginName);
		clsUser.setPassword(password);
		clsUser.setRoleId("04");
		boolean flag = g_service.isExistLoginName(clsUser.getLoginName());
		if (flag) {
			this.printString("2", "登录名已存在！");
		} else {
			String str = "";
			str = g_service.add(clsUser);
			if (!"".equals(str)) {
				this.printString("1", str);
			} else {
				this.printString("2", "添加失败");
			}
		}
	}

	/* 数据修改 */
	public void modify() {
		HttpServletRequest request = ServletActionContext.getRequest();
		LoginUser operatorUser = null;
		if (request.getSession().getAttribute(LoginUser.SESSIONID) == null) {
			if (operatorUser == null) {
				printString("-4", "非法访问！");
				return;
			}
		} else {
			operatorUser = (LoginUser) request.getSession().getAttribute(
					LoginUser.SESSIONID);
		}
		if(StrUtil.isEmpty(userId)){
			printString("-1", "用户Id为空！");
			return;
		}
		
		SingleUser clsUser = g_service.selectById(userId);
		//set
		clsUser.setRealName(realName);
		clsUser.setSexInfo(sexInfo);
		clsUser.setLoginName(loginName);
		clsUser.setPassword(password);
		
		boolean flag = g_service.modify(clsUser, userId);
		if (flag) {
			this.printString("1", "修改成功");
		} else {
			this.printString("2", "修改失败");
		}
	}
	
    /* 删除 */
    public void delete()
    {
    	if(userId==null){this.printString("2", "用户不存在！");return;}
        boolean flag = g_service.delete(userId);
        if (flag)
        {
        	this.printString("1", "删除成功");
        }
        else
        {
        	this.printString("2", "删除失败");
        }
    }

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSexInfo() {
		return sexInfo;
	}

	public void setSexInfo(String sexInfo) {
		this.sexInfo = sexInfo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
