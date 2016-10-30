package com.lanyuan.actionapi.commonmodule.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import com.lanyuan.assembly.commonmodule.authority.ServiceRole;
import com.lanyuan.assembly.commonmodule.authority.SingleRole;
import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.lanyuan.assembly.commonmodule.user.ServiceUser;
import com.lanyuan.assembly.commonmodule.user.SingleUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户新增、修改详情页 显示数据
 * 
 * @author ly-one
 *
 */
public class OperatePageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private SingleUser user;
	private String roleName;
	private String untiName;
	/* 页面上要显示的数据 */
	private TreeMap<String, String> hashData; /* 角色集合 */
	
	//单位集合
	private LinkedHashMap<String, String> unitMap;
	
	private ServiceUnit g_serviceUnit=new ServiceUnit();
	private ServiceUser g_service = new ServiceUser();
	private ServiceRole g_roleService = new ServiceRole();

	public OperatePageAction() 
	{
		hashData = new TreeMap<String, String>();
		// 读取角色信息
		List<SingleRole> listdata = g_roleService.selectEnabledRole();

		if (listdata != null) {
			for (SingleRole sr : listdata) {
				hashData.put(sr.getRoleId(), sr.getRoleName());
			}
		}
		
		unitMap = new LinkedHashMap<String, String>();
		
		List<SingleUnit> listUnit=g_serviceUnit.selectByCondition(null,null);
		if(listUnit!=null&&!listUnit.isEmpty())
		{
			for (SingleUnit singleUnit : listUnit) {
				unitMap.put(singleUnit.getUnitNo(), singleUnit.getUnitName());
			}
		}
	}

	public String add() {
		
		return Action.SUCCESS;
	}

	public String modify()
	{
		user = g_service.selectById(id);
		if (user != null) {
			return Action.SUCCESS;
		} else {
			return Action.ERROR;
		}
	}

	public String detail() 
	{
		if(id==null){return null;}
		user = g_service.selectById(id);
		roleName = g_roleService.getRoleNameByRoleId(user.getRoleId());
		
		untiName=(unitMap.get(user.getUnitNo())!=null?unitMap.get(user.getUnitNo()):"");
		
		if (user != null) {
			return Action.SUCCESS;
		} else {
			return Action.ERROR;
		}
	}

	public TreeMap<String, String> getHashData() {
		return hashData;
	}

	public void setHashData(TreeMap<String, String> hashData) {
		this.hashData = hashData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public SingleUser getUser() {
		return user;
	}
	public void setUser(SingleUser user) {
		this.user = user;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUntiName() {
		return untiName;
	}

	public void setUntiName(String untiName) {
		this.untiName = untiName;
	}

	public LinkedHashMap<String, String> getUnitMap() {
		return unitMap;
	}

	public void setUnitMap(LinkedHashMap<String, String> unitMap) {
		this.unitMap = unitMap;
	}
	
}
