package com.lanyuan.actionapi.commonmodule.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.opensymphony.xwork2.Action;

public class UnitListAction extends ResultSearchAction
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //显示页面
    public String execute()
    {
        return Action.SUCCESS;
    }

    public void loadData()
    {
    	ServiceUnit service = new ServiceUnit();
    	ConditionGroup cond=this.getConditionGroupObject();
    	if(cond==null)
    	{
    		cond = new ConditionGroup();
    	}
    	
    	OrderGroup order = this.getOrderGroupObject();
    	if(order==null)
    	{
    		order = new OrderGroup();
    	}
    	order.Add(ServiceUnit.MappingList.unitNo.name(), true);
    	
    	int countTotal=service.getTotalCount(cond);
    	if(countTotal<=0)
    	{
    		this.printString(null, 0, "2", "暂无数据");
    		return;
    	}
    	
    	List<SingleUnit> listData=service.selectByConditionAndPage(getCurrentPage(), getLinesOfPage(), cond, order);
    	if(listData!=null&&!listData.isEmpty())
    	{
    		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
    		
    		HashMap<String, String> hash = null;
    		for(SingleUnit cls:listData)
    		{
    			hash = new HashMap<String, String>();
    			hash.put(ServiceUnit.MappingList.unitNo.name(), cls.getUnitNo());
    			hash.put(ServiceUnit.MappingList.unitName.name(), cls.getUnitName());
    			hash.put(ServiceUnit.MappingList.belongDistrict.name(), cls.getBelongDistrict());
    			hash.put(ServiceUnit.MappingList.category.name(), cls.getCategory());
    			hash.put(ServiceUnit.MappingList.abbreviation.name(), cls.getAbbreviation());
    			hash.put(ServiceUnit.MappingList.telephone.name(), cls.getTelephone());
    			hash.put(ServiceUnit.MappingList.address.name(), cls.getAddress());
    			hash.put(ServiceUnit.MappingList.fax.name(), cls.getFax());
    			hash.put(ServiceUnit.MappingList.companyUrl.name(), cls.getCompanyUrl());
    			hash.put(ServiceUnit.MappingList.concator.name(), cls.getConcator());
    			hash.put(ServiceUnit.MappingList.concatPhone.name(), cls.getConcatPhone());
    			hash.put(ServiceUnit.MappingList.email.name(), cls.getEmail());
    			hash.put(ServiceUnit.MappingList.enable.name(), cls.getEnable()?"1":"0");
    			hash.put(ServiceUnit.MappingList.displayOrder.name(), cls.getDisplayOrder()+"");
    			hash.put(ServiceUnit.MappingList.remark.name(), cls.getRemark());
    			
    			datalist.add(hash);
    		}
    		
    		this.printString(datalist, countTotal, "1", null);
    	}
    	else
    	{
    		this.printString(null, 0, "2", "暂无数据");
    	}
    }
}
