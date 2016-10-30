package com.lanyuan.actionapi.sst.standardlist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.ResultCommonSearchAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.opensymphony.xwork2.Action;

public class IShowListDateAction  extends ResultCommonSearchAction
{
    /**
     * 跳转体系表统计页面
     */
     @Autowired
     private ServiceSSTStandardList standardService ;
     @Autowired
     private ServiceSSTLayerItems layerItemService ; 
     
    public  Integer SSTId;
    private static final long serialVersionUID = 1L;
    /**
     * 显示根据体系表号所有统计数据
     * @return
     */
    
    
    
    
    
    public String showTotalCount(){
        if(SSTId==null)
        {
            this.printString(null, 0, "2", "编号为空");
            return null;
        }
        List<HashMap<String, String>> datalist=standardService.totalCount(SSTId);
        
        String key = "";
        Map<String,Integer> indexList = new LinkedHashMap<String,Integer>();
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> countMap = new HashMap<String, String>();
        
        List<String> layerItemList = layerItemService.selectAllId(SSTId);
        HashMap<String,String> lineItem = new HashMap<String, String>();
        if(layerItemList!=null && !layerItemList.isEmpty())
        {
            for (int i = 0; i < layerItemList.size(); i++) 
            {
                lineItem.put(layerItemList.get(i), "0");
            }
        }
        countMap.putAll(lineItem);
        
        String categoryId = "";
        if(datalist!=null&!datalist.isEmpty())
        {
            for (int i = 0; i < datalist.size(); i++) 
            {
            	key = datalist.get(i).get("standardCategory");
            	if(!indexList.containsKey(key))
            	{
                    HashMap<String,String> item1 = new HashMap<String, String>();
                    item1.put("standardCategory", datalist.get(i).get("standardCategory"));
                    item1.put("standardCategoryName", datalist.get(i).get("standardCategoryName"));
                    item1.putAll(lineItem);
                    item1.put("code",(list.size()+1)+"");
                    list.add(item1); 
                    indexList.put(key, list.size()-1);                    
            	}
            	
            	//赋值
                HashMap<String,String> item2 = list.get(indexList.get(key));
                categoryId = datalist.get(i).get("id");
                if(item2.containsKey(categoryId))
                {
                   item2.put(categoryId, datalist.get(i).get("count"));
                   list.set(indexList.get(key), item2);
                }
            	
            	if(!countMap.containsKey(categoryId))
            	{
            		countMap.put(datalist.get(i).get("id"), datalist.get(i).get("count"));
            	}else{
            		Integer iCount = Integer.parseInt(countMap.get(datalist.get(i).get("id"))) ;
            		iCount +=Integer.parseInt(datalist.get(i).get("count")) ;
            		countMap.put(datalist.get(i).get("id"), iCount+"");
            	}
            }
            
            countMap.put("standardCategoryName", "");
            countMap.put("standardCategory", "合计");
            list.add(countMap);
            this.printString(list,list.size(),"1","");
            return Action.SUCCESS;
        }else
        {
            this.printString(null,0,"1","");
            return Action.SUCCESS;
        }
       
    }
    public Integer getSSTId() {
        return SSTId;
    }
    public void setSSTId(Integer sSTId) {
        SSTId = sSTId;
    }
    public   static   void  removeDuplicate(List list)   { 
    	   for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
    		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
    		      if  (list.get(j).equals(list.get(i)))   { 
    		        list.remove(j); 
    		      } 
    		    } 
    		  } 
    		  System.out.println(list); 
    		} 
}