package com.lanyuan.actionapi.commonmodule.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData.MappingList;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;

/**
 * 数据字典展现详情页面（新框架所有）
 */
public class ISearchDictionaryNewAction extends ResultSearchAction
{

    private static final long serialVersionUID = 1L;
    private String id;
    public void search()
    {
    	
        ConditionGroup cond = this.getConditionGroupObject();
        OrderGroup order = this.getOrderGroupObject();

        // 如果条件查询和排序对象为空,实例化对象
        if (cond == null)
        {
            cond = new ConditionGroup();
        }
        if (order == null)
        {
            order = new OrderGroup();
            // 按显示序号正序排序
            order.Add(MappingList.showNum.name(), true);
        }

        ServiceDictionaryData service = new ServiceDictionaryData();
        //根据数据字典id进行分类（dictionaryid）
        cond.addWithAnd(new ConditionNormal(ServiceDictionaryData.MappingList.dictionaryid.name(), id));
        int recordCount = service.getTotalCount(cond);
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return;
        }
        
        List<SingleDictionaryData> rtnList = null;
        
        if (this.isBreakPage())
        {
            rtnList = service.selectByConditionAndPage(getCurrentPage(), getLinesOfPage(), cond, order);
        }
        else
        {
            rtnList = service.selectByCondition(cond, order);
        }
        
        List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        
        for(SingleDictionaryData data : rtnList)
        {
            HashMap<String, String> returnData = new HashMap<String, String>();
            
            returnData.put(MappingList.id.name(), data.getId()+"");
            
            returnData.put(MappingList.dictionaryid.name(), data.getDictionaryid()+"");
            
            returnData.put(MappingList.code.name(), data.getCode()+"");
            
            returnData.put(MappingList.content.name(), data.getContent()+"");
            
            returnData.put(MappingList.customfield1.name(), data.getCustomfield1()+"");
            
            returnData.put(MappingList.showNum.name(), data.getShowNum()+"");
            
            returnData.put(MappingList.serviceClass.name(), data.getServiceClass()+"");
            
            returnData.put(MappingList.fid.name(), data.getFid()+"");
            
            datalist.add(returnData);
        }
        printString(datalist, recordCount, "1", null);
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
    
}
