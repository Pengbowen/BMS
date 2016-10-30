package com.lanyuan.actionapi.standardrepor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.standardrepor.ServiceStandardReport;
import com.lanyuan.assembly.standardrepor.ServiceStandardReport.MappingList;
import com.lanyuan.assembly.standardrepor.SingleStandardReport;
import com.opensymphony.xwork2.Action;

public class ISearchStandardReportAction extends ResultSearchAction{
	private static final long serialVersionUID = 1L;
	  @Autowired
	    private ServiceStandardReport standardReportService;
	  @Autowired
	     private ServiceSSTLayerItems layerItemService ; 
	/**
	 * 报表编号
	 */
	private String reportId;
	/**
	 * 标准类别
	 */
	private String standardCategory;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 项目编号
	 */
	private String itemId;
	/**
	 * 数量
	 */
	private Integer quantity;
	
	public String searchStatistics(){
		if(reportId=="0"){
			printString(null, 0, "2", null);
			return Action.SUCCESS;
		}
		ServiceDictionaryData data = new ServiceDictionaryData();
    	List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<SingleStandardReport> datalist=standardReportService.selectByReportId(reportId);
	    String key = "";
        Map<String,Integer> indexList = new LinkedHashMap<String,Integer>();
        HashMap<String,String> countMap = new HashMap<String, String>();
        
        List<String> layerItemList = layerItemService.selectAllId(Integer.valueOf(reportId));
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
            	key = datalist.get(i).getStandardCategory();
            	if(!indexList.containsKey(key))
            	{
            		String standardCategory= datalist.get(i).getStandardCategory();
            		String standardCategoryName= datalist.get(i).getStandardCategoryName();
                    HashMap<String,String> item1 = new HashMap<String, String>();
                    item1.put("standardCategory", standardCategory);
                    if(standardCategoryName==null||"".equals(standardCategoryName))
                    {
                    	standardCategoryName=data.selectByCode(standardCategory);
                    	item1.put("standardCategoryName",data.selectByCode(standardCategoryName));
                    }else {
                    	item1.put("standardCategoryName",data.selectByCode(standardCategoryName) );
					}
                    item1.putAll(lineItem);
                    item1.put("code",(list.size()+1)+"");
                    list.add(item1); 
                    indexList.put(key, list.size()-1);                    
            	}
            	
            	//赋值
                HashMap<String,String> item2 = list.get(indexList.get(key));
                categoryId = datalist.get(i).getItemId();
                if(item2.containsKey(categoryId))
                {
                   item2.put(categoryId, datalist.get(i).getQuantity()+"");
                   list.set(indexList.get(key), item2);
                }
            	
            	if(!countMap.containsKey(categoryId))
            	{
            		countMap.put(datalist.get(i).getItemId(), datalist.get(i).getQuantity()+"");
            	}else{
            		Integer iCount = Integer.parseInt(countMap.get(datalist.get(i).getItemId())) ;
            		iCount +=Integer.parseInt(datalist.get(i).getQuantity()+"") ;
            		countMap.put(datalist.get(i).getItemId(), iCount+"");
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
	public String statistics(){
		if(reportId=="0"){
			printString(null, 0, "2", null);
			return Action.SUCCESS;
		}
		ServiceDictionaryData data = new ServiceDictionaryData();
    	List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		List<SingleStandardReport> list=standardReportService.selectByReportId(reportId);
		
		Integer count = 0;	
		for (SingleStandardReport single : list) {
				HashMap<String, String> returnData = new HashMap<String, String>();
				returnData.put(MappingList.standardCategory.name(), single.getStandardCategory());
				returnData.put(MappingList.itemId.name(), single.getItemId());
				returnData.put(MappingList.quantity.name(), single.getQuantity()+"");
				returnData.put("standardCategoryName",single.getStandardCategoryName());
				int param = single.getQuantity();
	    		count += param;
				 datalist.add(returnData);
			}
		HashMap<String, String> map = new HashMap<String, String>();
			map.put("standardCategoryName", "合计");
			map.put("quantity", count+"");
			datalist.add(map);
			this.printString(datalist,datalist.size(), "1", null);
		return Action.SUCCESS;
	}
	
	
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getStandardCategory() {
		return standardCategory;
	}
	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
