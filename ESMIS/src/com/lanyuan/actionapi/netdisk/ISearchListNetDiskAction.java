package com.lanyuan.actionapi.netdisk;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

public class ISearchListNetDiskAction extends ResultSearchAction
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
		// 将parentobjectNo（必须有）传入ConditionGroup进行查询。
		cond.addWithAnd(new ConditionNormal(ServiceNetdisk.MappingList.parentobjectNo.name(),parentobjectNo));
		// 如果objectName不为空，则将其作为参数传入ConditionGroup进行查询。
		if (!StringUtils.isBlank(objectName)) {
			//解密接受前台传递的objectName
			try {
				objectName = URLDecoder.decode(objectName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//解密接受前台传递的目录样式
			try {
				catalog = URLDecoder.decode(catalog, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//当父级目录不为100时，拼接二级及以后目录样式
			if(!"100".equals(parentobjectNo)){
				if(searchType==1){
					//1.将objectName传入ConditionGroup查询    目录不变
					List<SingleNetdisk> list =new ArrayList<SingleNetdisk>();
			    	while(!parentobjectNo.equals("100"))
			    	{
			    		SingleNetdisk single = netdiskService.searchParentNo(parentobjectNo);
			    		list.add(single);
			    		parentobjectNo=single.getParentobjectNo();
			    	}
			    	/**倒叙查找所有父级编号*/
			    	for (int i = list.size()-1; i >= 0; i--) {
			    		catalog+="<li><a href='${pageContext.request.contextPath}/netdisk/show.action?parentobjectNo="+list.get(i).getObjectNo()+"&objectName=name"+"&searchType=2'>"+list.get(i).getObjectName()+"</a></li>";
					}
			    	catalog+="&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;\" "+objectName+" \"&nbsp;&nbsp;的查询结果";
			    	cond.addWithAnd(new ConditionNormal(ServiceNetdisk.MappingList.objectName.name(), objectName,enumOperator.Like));
				}else if(searchType==0){
					//0.将objectName做为参数传入目录链接中   改变目录
					System.out.println("双击事件");
					catalog += "<li><a href='${pageContext.request.contextPath}/netdisk/show.action?parentobjectNo="+parentobjectNo+"&objectName=name"+"&searchType=2'>"+objectName+"</a></li>";
				}else if(searchType==2){
					//2.当点击目录时，查询其所有父级编号及名称
					List<SingleNetdisk> list =new ArrayList<SingleNetdisk>();
			    	while(!parentobjectNo.equals("100"))
			    	{
			    		SingleNetdisk single = netdiskService.searchParentNo(parentobjectNo);
			    		list.add(single);
			    		parentobjectNo=single.getParentobjectNo();
			    	}
			    	/**倒叙查找所有父级编号*/
			    	for (int i = list.size()-1; i >= 0; i--) {
			    		catalog+="<li><a href='${pageContext.request.contextPath}/netdisk/show.action?parentobjectNo="+list.get(i).getObjectNo()+"&objectName=name"+"&searchType=2'>"+list.get(i).getObjectName()+"</a></li>";
					}
					System.out.println("这里点击了目录："+"\r"+catalog);
				}
			}else{
				//parentobjectNo为100时，ConditionGroup查询
				catalog+="&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;\" "+objectName+" \"&nbsp;&nbsp;的查询结果";
				cond.addWithAnd(new ConditionNormal(ServiceNetdisk.MappingList.objectName.name(), objectName,enumOperator.Like));
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
