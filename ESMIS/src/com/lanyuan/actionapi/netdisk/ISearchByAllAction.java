package com.lanyuan.actionapi.netdisk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;
import com.opensymphony.xwork2.Action;

/**
 * @Title: ISearchListNetDiskAction.java
 * @Package com.lanyuan.actionapi.netdisk
 * @Description: 文件目录列表页面
 * @author WYL
 * @date 2016年9月19日
 */

public class ISearchByAllAction extends ResultSearchAction
{
	private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNetdisk netdiskService;

    /**
	 * 所属目录
	 */
	private String parentobjectNo;
    /**
	 * 对象名称
	 */
	private String objectName;
    /**
     * 列表展现页面使用此对象
     */
    private List<HashMap<String, String>> datalist;
    /**
     * 查询失败返回值
     */
    private String messager = "";
    /**
     * 二级目录及以后
     */
    private String catalog ="";
    /**
     * 查询类型（0.将objectName做为参数传入目录链接中,1.将objectName传入ConditionGroup查询,2.点击目录时将catalog截取）
     */
    private Integer searchType;
    /**
     * 点击目录时接收到的参数
     */
    public String execute(){
    	if(StringUtils.isEmpty(parentobjectNo))
    	{
    		parentobjectNo="100";
    	}
		ConditionGroup cond = new ConditionGroup();
		OrderGroup order = new OrderGroup();
		// 排序
		order.Add(ServiceNetdisk.MappingList.objectType.name(), false);
		order.Add(ServiceNetdisk.MappingList.objectName.name(),true);
		if (!StringUtils.isBlank(objectName)) {
			if (!"100".equals(parentobjectNo)) {
				List<SingleNetdisk> list = new ArrayList<SingleNetdisk>();
				while (!parentobjectNo.equals("100")) {
					SingleNetdisk single = netdiskService.searchParentNo(parentobjectNo);
					list.add(single);
					parentobjectNo = single.getParentobjectNo();
				}
				/** 倒叙查找所有父级编号 */
				for (int i = list.size() - 1; i >= 0; i--) {
					catalog += "<i style='padding-top:26px;float:left;color:#666666;font-size:18px;'class='icon icon-angle-right'></i><li style='padding-top: 10px;float:left;'><a onclick='JSLoadData({\"parentobjectNo\":\""
							+ list.get(i).getObjectNo()
							+ "\",\"objectName\":\"name\",\"searchType\":\"2\"},\"netdisk/showReception.action\")'>"
							+ list.get(i).getObjectName() + "</a></li>";
				}
				catalog += "<i style='padding-top:26px;float:left;color:#666666;font-size:18px;'class='icon icon-angle-right'></i><i style='padding-top:10px;float:left;'>&nbsp;&nbsp;<font style='color:blue'>\""
						+ objectName
						+ "\"</font></i><font size='3' style='padding-top:10px;float:left;'>&nbsp;&nbsp;的查询结果:</font>";
				cond.addWithAnd(new ConditionNormal(ServiceNetdisk.MappingList.objectName.name(), objectName,
						enumOperator.Like));
			
			
			
			} else {
				// parentobjectNo为100时，ConditionGroup查询
				List<SingleNetdisk> list = new ArrayList<SingleNetdisk>();
				while (!parentobjectNo.equals("100")) {
					SingleNetdisk single = netdiskService.searchParentNo(parentobjectNo);
					list.add(single);
					parentobjectNo = single.getParentobjectNo();
				}
				/** 倒叙查找所有父级编号 */
				for (int i = list.size() - 1; i >= 0; i--) {
					catalog += "<i style='padding-top:26px;float:left;color:#666666;font-size:18px;'class='icon icon-angle-right'></i><li style='padding-top: 10px;float:left;'><a onclick='JSLoadData({\"parentobjectNo\":\""
							+ list.get(i).getObjectNo()
							+ "\",\"objectName\":\"name\",\"searchType\":\"2\"},\"netdisk/showReception.action\")'>"
							+ list.get(i).getObjectName() + "</a></li>";
				}
				catalog += "<i style='padding-top:26px;float:left;color:#666666;font-size:18px;'class='icon icon-angle-right'></i><i style='padding-top:10px;float:left;'>&nbsp;&nbsp;<font style='color:blue'>\""
						+ objectName
						+ "\"</font></i><font size='3' style='padding-top:10px;float:left;'>&nbsp;&nbsp;的查询结果:</font>";
				cond.addWithAnd(new ConditionNormal(ServiceNetdisk.MappingList.objectName.name(), objectName,
						enumOperator.Like));
			}
		}
		List<SingleNetdisk> diskList = netdiskService.selectList(cond, order);
		if (diskList != null) {
			datalist = new ArrayList<HashMap<String, String>>();
			for (SingleNetdisk disk : diskList) {
				HashMap<String, String> returnData = new HashMap<String, String>();
				returnData.put("objectSuffix", disk.getObjectSuffix());
				returnData.put("objectNo", disk.getObjectNo());
				returnData.put("parentobjectNo", disk.getParentobjectNo());
				returnData.put("objectName", disk.getObjectName());
				returnData.put("objectType", disk.getObjectType() + "");
				returnData.put("objectSavePath", disk.getObjectSavePath());
				datalist.add(returnData);
			}
		}else{
			messager="未查询到文件！";
			return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}
    
	public String getParentobjectNo() {
		return parentobjectNo;
	}
	public void setParentobjectNo(String parentobjectNo) {
		this.parentobjectNo = parentobjectNo;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public List<HashMap<String, String>> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<HashMap<String, String>> datalist) {
		this.datalist = datalist;
	}
	public String getMessager() {
		return messager;
	}
	public void setMessager(String messager) {
		this.messager = messager;
	}
	/**
	 * @return the catalog
	 */
	public String getCatalog() {
		return catalog;
	}
	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	/**
	 * @return the searchType
	 */
	public Integer getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
}
