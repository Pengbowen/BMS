package com.lanyuan.actionapi.sst.subclassify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.subclassify.EntitySSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;

public class ISearchSubClassifyAction extends ResultSearchAction {
	private static final long serialVersionUID = 1L;
	//所属项目名称
	private String layerItemId;
	//所属体系表
	private String SSTId;
	

	@Autowired
	private ServiceSSTSubClassify  serviceSubClassify;
	/**
	 * 跳转到修改页面
	 */
	public String execute(){
		return this.SUCCESS;
	}
	
	/**
	 * 搜索方法
	 */
	public void loadData(){
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
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
            // 按层级排序
            order.Add(ServiceSSTSubClassify.MappingList.layerNo.name(), true);
        }
        int recordCount = serviceSubClassify.getTotalCount(cond);
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return;
        }
        List<SingleSSTSubClassify> rtnList = null;
        if (this.isBreakPage())
        {
            rtnList = serviceSubClassify.selectPageList(getCurrentPage(),
                                                                  getLinesOfPage(), cond, order);
        }
        else
        {
            rtnList = serviceSubClassify.selectList(cond, order);
        }
		
			for(SingleSSTSubClassify e:rtnList){
				 HashMap<String, String> returnData = new HashMap<String, String>();
//				 所属体系表	SSTId
//				 所属层级	layerNo
//				 所属层项目	layerItemId
//				 子分类编号	subClassId
//				 子分类名称	subClassName
//				 包含标准数量	standardCount
//				 可用状态	isEnabled
//				 显示状态	isVisible
//				 显示顺序	displayOrder
				 
//				 修改人	modifyer
//				 修改人姓名	modifyerName
//				 修改时间	modifyTime
//				 修改IP	modifyIP
				 returnData.put("SSTId", e.getSSTId().toString());
				 returnData.put("layerNo", e.getLayerNo().toString());
				 returnData.put("layerItemId", e.getLayerItemId());
				 returnData.put(ServiceSSTSubClassify.MappingList.displayOrder.name(),e.getDisplayOrder().toString());
				 returnData.put("subClassId", e.getSubClassId());
				 returnData.put("subClassName", e.getSubClassName());
				 returnData.put("isEnabled", e.getIsEnabled().toString());
				 Integer standardCount = e.getStandardCount();
				 standardCount= standardCount==null?0:standardCount;
				 returnData.put("standardCount", standardCount+"");
				 datalist.add(returnData);
			}
			printString(datalist, rtnList.size(),"1", "ok");
		
	}


	public String getLayerItemId() {
		return layerItemId;
	}

	public void setLayerItemId(String layerItemId) {
		this.layerItemId = layerItemId;
	}

	public String getSSTId() {
		return SSTId;
	}

	public void setSSTId(String sSTId) {
		SSTId = sSTId;
	}


}
