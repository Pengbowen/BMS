package com.lanyuan.actionapi.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.lanyuan.assembly.commonmodule.permission.ApplicationPermission;
import com.lanyuan.assembly.commonmodule.permission.SinglePermission;
import com.lanyuan.assembly.utils.checksum.VerifyCommon;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 自定义拦截器
 *  拦截非法访问的URL
 */
public class CustomInterceptor extends AbstractInterceptor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
	    HttpServletRequest request = ServletActionContext.getRequest();
	    String paraString = "";
	    //修改拦截器，处理空指针异常
	    if(!VerifyCommon.isNullOrEmpty(request.getQueryString()))
	    {
	    	paraString = "?" + request.getQueryString();
	    }
        String target = request.getRequestURI()+paraString;
        String contextPath = request.getContextPath();
        if ( contextPath != null )
        {
            target = target.substring(contextPath.length());
        }

        Map<String, Object> session = invocation.getInvocationContext().getSession();
        
        if (session.get(LoginUser.SESSIONID) == null)
        {
        	StringBuffer url = request.getRequestURL();
        	if ( !StringUtils.isBlank(request.getQueryString()))
            {
                url.append("?");
                url.append(request.getQueryString());
            }
            session.put("LastPage", url.toString());
            return "NoHasLimit";
        }
        
        if(session.containsKey("LastPage")) session.remove("LastPage");

        ApplicationPermission appPermission=ApplicationPermission.getInstance();
        SinglePermission sp = appPermission.getPermissionByUrl(target);
        if (sp == null)
        {
        	return "NoHasLimit";
        }
        
        LoginUser user = (LoginUser)session.get(LoginUser.SESSIONID);
    	if (!user.hasPermission(sp.getPermissionId())) return "NoHasLimit";
    	
    	//获取当前权限的全名：LPMS.System.UserManage.UnitManage
    	String[] limits=sp.getPermissionId().split("\\.");
    	if(limits.length>0)
    	{
    		SinglePermission permission=null;
    		String limitName="";
    		String rootLimitId=limits[0];
    		for(int i=1;i<limits.length;i++)
    		{
    			rootLimitId+="."+limits[i];
    			permission=appPermission.getDataById(rootLimitId);
    			limitName+=permission.getPerName()+" > ";
    		}
    		limitName = (limitName.length()>0?limitName.substring(0, limitName.lastIndexOf(">")):"");
    		session.put("currentLimitName", limitName);
    	}
    	
        return invocation.invoke();
	}
	
}
