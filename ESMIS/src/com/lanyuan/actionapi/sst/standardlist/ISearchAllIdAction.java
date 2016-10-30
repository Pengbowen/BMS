package com.lanyuan.actionapi.sst.standardlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.opensymphony.xwork2.Action;

public class ISearchAllIdAction extends ResultSearchAction{
	 private static final long serialVersionUID = 1L;
	 /**
	  * 体系表统计表头
	  */
	 @Autowired
	 private ServiceSSTLayerItems layerItemService ; 
	 /**
		 * 
		 */
		public Integer SSTId;
	
		   public String showTotalCount(){
		        if(SSTId==null)
		        {
		         this.printString(null, 0, "2", "编号为空！");
		         return null;
		         
		        }
		    	getDatagridColumn(SSTId);
		    	return Action.SUCCESS;
		    }
		public String getDatagridColumn(Integer id){
			 List<String> list = layerItemService.selectAllId(SSTId);
			 List<HashMap<String, String>> datalist = new ArrayList<HashMap<String,String>>();
			 for (String string : list) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", string);
				datalist.add(map);
			}
			this.printString(datalist, datalist.size(), "1", "");
			
			 return Action.SUCCESS;
		}
		public Integer getSSTId() {
			return SSTId;
		}
		public void setSSTId(Integer sSTId) {
			SSTId = sSTId;
		}
}
