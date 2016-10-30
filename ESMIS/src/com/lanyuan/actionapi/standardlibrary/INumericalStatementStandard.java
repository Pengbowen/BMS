package com.lanyuan.actionapi.standardlibrary;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultCommonSearchAction;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.opensymphony.xwork2.Action;

public class INumericalStatementStandard  extends ResultCommonSearchAction{
	 /**
     * 跳转标准总库统计页面
     */
	 @Autowired
	 private ServiceStandardLibrary standardLibraryService;
	
    private static final long serialVersionUID = 1L;
    //显示页面
    public String execute()
    {
        return Action.SUCCESS;
    }
    public String showStatement(){
    	List<HashMap<String, String>> list=standardLibraryService.searchStatement();
    	if(list==null||list.isEmpty()){
    		this.printString(null,0,"2","数据为空");
			return Action.SUCCESS;
		}
    	Integer count = 0;
    	for (HashMap<String, String> hashMap : list) {
    		Object standardcategorycount = hashMap.get("standardcategorycount");
    		String value = standardcategorycount.toString();
    		int param = Integer.valueOf(value);
    		count += param;
//    		System.out.println(param);
//    		System.out.println(count);
			
		}
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("standardcategory", "合计");
    	map.put("standardcategoryname", "");
    	map.put("standardcategorycount", count+"");
    	list.add(map);
    	this.printString(list,list.size(),"1","");
        return Action.SUCCESS;
    }
}
