package com.lanyuan.actionapi.sst.layeritems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchMapAction;
import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;

public class ISearchLayerNoAndDisplayOrderAction  extends ResultSearchMapAction{
	private static final long serialVersionUID = 1L;
	private String layerItemId;
	private Integer layerNo;
	@Autowired
	private ServiceSSTLayerItems layerItemService;
	
	public String execute(){
		 List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		    	SingleSSTLayerItems single = layerItemService.selectById(layerItemId);
		    	Integer layerNo = single.getLayerNo();
		    	HashMap<String,String> data = new HashMap<String,String>();
		    	data.put("layerNo", layerNo+1+"");
		    	//查找下一层级的最大显示序号
//		    	Integer temp = layerItemService.selectMaxDisplayOrderByLayerNo(layerNo);
//		    	data.put("displayOrder", temp+1+"");
		    	datalist.add(data);
		    	printString(datalist);
		return  this.SUCCESS;
	}
	public String searchDisplayOrder(){
		 List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		 HashMap<String,String> data = new HashMap<String,String>();
		 if(layerNo !=null){
			 Integer d = layerItemService.selectMaxDisplayOrderByLayerNo(layerNo);
			 data.put("displayOrder", d+1+"");
			 datalist.add(data);
			 printString(datalist);
		 }else{
			 return this.ERROR;
		 }
		 
		 
		return this.SUCCESS;
	}
	
	public String getLayerItemId() {
		return layerItemId;
	}

	public void setLayerItemId(String layerItemId) {
		this.layerItemId = layerItemId;
	}
	public Integer getLayerNo() {
		return layerNo;
	}
	public void setLayerNo(Integer layerNo) {
		this.layerNo = layerNo;
	}
	
	
}
