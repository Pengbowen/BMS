package com.lanyuan.actionapi.commonmodule.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchAction;
import com.lanyuan.actionapi.userlogin.ConstApplication;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.commonmodule.permission.ServiceRole;
import com.lanyuan.assembly.commonmodule.permission.SingleRole;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
import com.opensymphony.xwork2.Action;
/**
 * 用户查询列表
 * ConditionGroup 查询
 * @author ly-one
 *
 */
public class UserListAction extends ResultSearchAction
{	
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private ServiceRole sRole=new ServiceRole(ConstApplication.ApplicationName);
    
    private HashMap<String,String> roleMap=new LinkedHashMap<String, String>();
    private String unitNo;
    
    /**显示用户列表*/
    public String execute()
    {        
    	List<SingleRole> listRole=sRole.selectByEnabled();
    	if(listRole!=null&&!listRole.isEmpty())
    	{
    		for(SingleRole cls:listRole)
    		{
    			roleMap.put(cls.getRoleId(), cls.getRoleName());
    		}
    	}
    	
        return Action.SUCCESS;
    }

    /**加载*/
    public void loadData()
    {
    	ServiceUser g_service = new ServiceUser();
    	
    	ConditionGroup cond = this.getConditionGroupObject();
    	if(cond==null)
    	{
    		cond = new ConditionGroup();
    	}
    	
    	OrderGroup order = this.getOrderGroupObject();
    	if(order==null)
    	{
    		order=new OrderGroup();
    	}
    	order.Add(ServiceUser.MappingList.userId.name(), true);
    	int count=g_service.getTotalCount(cond);
    	if(count<=0)
    	{
    		this.printString(null, count, "2", "暂无数据");
    		return;
    	}
    	
    	List<SingleUser> listData = g_service.selectByConditionAndPage(getCurrentPage(), getLinesOfPage(), cond, order);
    	
    	List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
    	
    	HashMap<String,String> hash = null;
    	SingleRole role=null;
    	for(SingleUser cls:listData)
    	{
    		hash = new HashMap<String,String>();
    		hash.put("userId", cls.getUserId());
    		hash.put("loginName", cls.getLoginName());
    		role=sRole.getRoleByRoleId(cls.getRoleId());
    		hash.put("roleName", role!=null?role.getRoleName():"");
    		hash.put("realName", cls.getRealName());
    		hash.put("sex", "1".equals(cls.getSexInfo())?"男":"女");
    		hash.put("state", cls.getLoginLimit()?"1":"0");
    		hash.put("stateName", cls.getLoginLimit()?"可用":"停用");
    		datalist.add(hash);
    	}
    	this.printString(datalist, count, "1", null);
    }

	public HashMap<String, String> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(HashMap<String, String> roleMap) {
		this.roleMap = roleMap;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	
}