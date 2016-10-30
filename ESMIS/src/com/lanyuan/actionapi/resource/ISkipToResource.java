package com.lanyuan.actionapi.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.lanyuan.assembly.utils.string.JsonUtils;
import com.opensymphony.xwork2.ActionSupport;

public class ISkipToResource extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    private String str = "";
    
    public String resourceList(){
        ServiceDictionaryData sdd = new ServiceDictionaryData();
        //标准类别
        OrderGroup order=new OrderGroup();
        order.Add(ServiceDictionaryData.MappingList.id.name(),true);
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceDictionaryData.MappingList.dictionaryid.name(),"1000"));
        List<SingleDictionaryData> list=sdd.selectByCondition(cond, order);
        List<HashMap<String, String>> listC = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> map = null;
        if(list != null && !list.isEmpty()){
            for(SingleDictionaryData single:list){
                map = new HashMap<String, String>();
                map.put("standardCategory", single.getCode()+"");
                map.put("standardCategoryName", single.getContent()+"");
                listC.add(map);
            }
        }
        //专业
        ConditionGroup cond1 = new ConditionGroup();
        cond1.addWithAnd(new ConditionNormal(ServiceDictionaryData.MappingList.dictionaryid.name(),"1001"));
        List<SingleDictionaryData> list1=sdd.selectByCondition(cond1, order);
        List<HashMap<String, String>> listA = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> map1 = null;
        if(list1 != null && !list1.isEmpty()){
            for(SingleDictionaryData single:list1){
                map1 = new HashMap<String, String>();
                map1.put("applicableMajor", single.getCode()+"");
                map1.put("applicableMajorName", single.getContent()+"");
                listA.add(map1);
            }
        }
        
        HashMap<String, Object> resultList = new HashMap<String, Object>();
        resultList.put("listC", listC);//类别
        resultList.put("listA", listA);//专业
        str = JsonUtils.objectToJsonStr(resultList);
        
        
       
        
        
        return ActionSupport.SUCCESS;
    }


    /**
     * @return 返回 str
     */
    public String getStr()
    {
        return str;
    }

    /**
     * @param str 设置
     */
    public void setStr(String str)
    {
        this.str = str;
    }
}
