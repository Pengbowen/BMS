package com.lanyuan.actionapi.sst.layeritems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.ConstApplication.Dictionary;
import com.lanyuan.actionapi.basic.baseclasses.ResultSearchMapAction;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.lanyuan.assembly.sst.layeritems.EntitySSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;

/**
 * 定义搜索父级项目
 * @author PBW
 *
 */
public class ISearchParentAction extends ResultSearchMapAction {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	private Integer SSTId;
	
	private String layerItemId;
	@Autowired
	private ServiceSSTLayerItems layerItemService;
	
	public static void main(String[] args) {
		ServiceDictionaryData dictionary = new  ServiceDictionaryData();
		SingleDictionaryData s =  dictionary.selectById(100200);
		System.out.println(s.getCustomfield1());
	}
	
	public String execute(){
		 List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		List<SingleSSTLayerItems> list = layerItemService.selectBySSTId(SSTId);
	
		
		//只取层级不大于2的项目作为父级
		for(SingleSSTLayerItems e:list){
			 HashMap<String, String> returnData = new HashMap<String, String>();
			 Integer layerNo = e.getLayerNo();
			 if(layerNo!=null){
				 if(layerNo<3){
					 returnData.put("id",e.getLayerItemId());
					 returnData.put("text", e.getLayerItemName()+" "+e.getLayerItemId());
					 datalist.add(returnData);
				 } 
			 }else{
				 return this.SUCCESS;
			 }
		}
		printString(datalist);
		return this.SUCCESS;
	}

	public Integer getSSTId() {
		return SSTId;
	}

	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}

	public String getLayerItemId() {
		return layerItemId;
	}

	public void setLayerItemId(String layerItemId) {
		this.layerItemId = layerItemId;
	}
	
}
