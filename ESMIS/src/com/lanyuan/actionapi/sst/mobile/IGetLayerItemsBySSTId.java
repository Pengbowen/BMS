package com.lanyuan.actionapi.sst.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems.MappingList;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
/**
 * 移动端接口
 * 
 *  根据SSTId查询所有的层项目,并且根据层级排序
 *  
 *  
 * @author Administrator
 *
 */
public class IGetLayerItemsBySSTId extends ResultSearchAction {
	private static final long serialVersionUID = 1L;
	
	private Integer SSTId;
	
	 
    @Autowired
    private ServiceSSTLayerItems layerItemService;
    /**
     * @param SSTId 体系表id
     * @return 
     * 包括(项目id,项目编号,项目名称);
     * 状态1--正常
     * 其它状态--异常
     */
	public String execute(){
		
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		ConditionGroup cond  = new ConditionGroup();
		OrderGroup order = new OrderGroup();
		if(SSTId !=null){
			cond.addWithAnd(new ConditionNormal(MappingList.SSTId.name(), SSTId+"", enumOperator.Equal));
			order.Add(MappingList.SSTId.name(), true);
			 int recordCount = layerItemService.getTotalCount(cond);
		        if (recordCount <= 0)
		        {
		            printString(null, 0, "2", "没有数据！");
		            return this.SUCCESS;
		        }else{
		        	List<SingleSSTLayerItems> layerItemList = layerItemService.selectList(cond, order);
		        	for(SingleSSTLayerItems single:layerItemList){
		        		//新建map,用于存放本条数据
		        		HashMap<String,String> map  = new HashMap<String,String>();
		        		String layerItemName  = single.getLayerItemName();
		        		String layerItemNo = single.getLayerItemNo();
		        		String layerItemId = single.getLayerItemId();
		        		Integer c_standard  = single.getStandardCount();
		        		c_standard = c_standard==null?0:c_standard;
		        		Integer c_subClass = single.getSubClassifyCount();
		        		c_subClass =  c_subClass==null?0:c_subClass;
		        		map.put(MappingList.layerItemId.name(), layerItemId); //项目id
		        		map.put(MappingList.layerItemName.name(), layerItemName);//项目名称
		        		map.put(ServiceSSTLayerItems.MappingList.subClassifyCount.name(), c_subClass+"");//子分类数量
		        		map.put(MappingList.standardCount.name(), c_standard+"");//标准数量
		        		map.put("layerItemNo", layerItemNo);//项目编号
		        		//将本条数据存入集合中
		        		datalist.add(map);
		        		
		        	}
		        	printString(datalist,recordCount , "1", "ok");
		        }
		}
		return this.SUCCESS;
	}

	public Integer getSSTId() {
		return SSTId;
	}

	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}
	

}
