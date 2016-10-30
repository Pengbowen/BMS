package com.lanyuan.actionapi.sst.layeritems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.opensymphony.xwork2.Action;

/**
 * 查询项目接口
 * @author PBW
 * @date 2016年8月25日09:35:13
 */
public class ISearchLayerItemsAction extends ResultSearchAction
{
    /**
     *版本号 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所属体系表
     */
    private String SSTId;
    
    /**
     * 所属体系表是否具有从属关系(有--1; 无--0);
     * 
     */
    private String SSTType;
    
    @Autowired
    private ServiceSSTLayerItems layerItemService;

    /**
     * 执行方法
     * @return
     */
    public String execute()
    {
    	if(SSTId!=null){
    	//实例化数据字典
    	ServiceDictionaryData service = new ServiceDictionaryData();
    	//数据字典Id
    	Integer id = 0;
    	//技术标准体系表
    	if(SSTId.equals("1")){
    		 id = 100500;
    	}
    	//管理标准体系表
    	if(SSTId.equals("2")){
    		 id = 100501;
    	}
    	//工作标准体系表
    	if(SSTId.equals("3")){
    		 id = 100502;
    	}
    	SingleDictionaryData dict = service.selectById(id);
    	
    	if(dict!=null){
    		//若结果不为空,则体系表类型可以确定
    		SSTType = dict.getCustomfield1();
    	}
    	}
        return Action.SUCCESS;
    }

    /**
     * 搜索项目/加载数据
     */
    public void loadData()
    {
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
            order.Add(ServiceSSTLayerItems.MappingList.layerNo.name(), true);
        }
        int recordCount = layerItemService.getTotalCount(cond);
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return;
        }
        List<SingleSSTLayerItems> rtnList = null;
        if (this.isBreakPage())
        {
            rtnList = layerItemService.selectPageList(getCurrentPage(), getLinesOfPage(), cond,
                                                      order);
        }
        else
        {
            rtnList = layerItemService.selectList(cond, order);
        }

        if (rtnList != null && rtnList.size() > 0)
        {
            for (SingleSSTLayerItems e : rtnList)
            {
                HashMap<String, String> returnData = new HashMap<String, String>();
                // 查询子分类数量
                // Integer subClassCount =
                // layerItemService.selectSubClassCountById(e.getLayerItemId());
                // subClassCount = subClassCount==null?0:subClassCount;
                returnData.put("layerItemName", e.getLayerItemName());// 项目名称layerItemName
                returnData.put("layerNo", e.getLayerNo().toString());// 所属层级
                returnData.put(ServiceSSTLayerItems.MappingList.SSTId.name(),
                               e.getSSTId().toString());
                returnData.put(ServiceSSTLayerItems.MappingList.layerItemId.name(),
                               e.getLayerItemId());
                returnData.put("displayOrder", e.getDisplayOrder().toString());// 显示顺序
                returnData.put("isEnabled", e.getIsEnabled().toString());// 可用状态
                returnData.put("belongItemId", e.getBelongItemId());// 所属父级
                // returnData.put("subClassCount", subClassCount.toString());
                datalist.add(returnData);
            }
        }
        printString(datalist, recordCount, "1", "ok");
    }

    public String getSSTId()
    {
        return SSTId;
    }

    public void setSSTId(String sSTId)
    {
        SSTId = sSTId;
    }

	public String getSSTType() {
		return SSTType;
	}

	public void setSSTType(String sSTType) {
		SSTType = sSTType;
	}

}
