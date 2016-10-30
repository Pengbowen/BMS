package com.lanyuan.actionapi.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.lanyuan.actionapi.basic.baseclasses.ResultSearchMapAction;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.opensymphony.xwork2.Action;


/**
 * 
 * @author afl
 * 查询采用国际标准
 *
 */

public class ISearchAdoptISAction  extends ResultSearchMapAction{
private static final long serialVersionUID = 1L;

	public String execute()
	{
		ServiceDictionaryData sdd = new ServiceDictionaryData();
		OrderGroup order=new OrderGroup();
		order.Add(ServiceDictionaryData.MappingList.id.name(),true);
		ConditionGroup cond = new ConditionGroup();
		cond.addWithAnd(new ConditionNormal(ServiceDictionaryData.MappingList.dictionaryid.name(),"1003"));
		List<SingleDictionaryData> list=sdd.selectByCondition(cond, order);
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = null;
		if(list != null && !list.isEmpty()){
            for(SingleDictionaryData single:list){
                map = new HashMap<String, String>();
                map.put("id", single.getCode()+"");
                map.put("text", single.getCode()+"     ("+ single.getContent()+")");
                datalist.add(map);
            }
        }
		this.printString(datalist);
        return Action.SUCCESS;
	}
}
