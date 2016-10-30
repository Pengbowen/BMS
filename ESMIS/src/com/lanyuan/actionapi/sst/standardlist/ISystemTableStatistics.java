package com.lanyuan.actionapi.sst.standardlist;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lanyuan.actionapi.basic.baseclasses.ResultCommonSearchAction;
import com.lanyuan.assembly.sst.DatagridColumn.DatagridColumn;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.opensymphony.xwork2.Action;

public class ISystemTableStatistics  extends ResultCommonSearchAction{
    private static final long serialVersionUID = 1L;
	 /**
     * 跳转体系表统计页面
     */
	 @Autowired
	 private ServiceSSTLayerItems layerItemService ; 
	/**
	 * 
	 */
	public Integer SSTId;
	
	/**
	 * columnJson
	 */
	private String columnJson;
	
    /**
     * 显示根据体系表号所有统计数据
     * @return
     */
    public String showTotalCount(){
        if(SSTId==null)
        {
         this.printString(null, 0, "2", "编号为空！");
         return null;
         
        }
    	getDatagridColumn(SSTId);
    	return Action.SUCCESS;
    }
    public String  getDatagridColumn(Integer id)
    {
        List<String> list = layerItemService.selectAllId(SSTId);
        List<DatagridColumn> columnList = new ArrayList<DatagridColumn>();
        DatagridColumn dcl1 = new DatagridColumn();
        DatagridColumn dcl2 = new DatagridColumn();
        DatagridColumn dcl3 = new DatagridColumn();
        dcl1.setField("code");
        dcl1.setTitle("序号");
        dcl1.setWidth(60);
        dcl1.setAlign("center");
        dcl2.setField("standardCategory");
        dcl2.setTitle("标准类别编号");
        dcl2.setAlign("center");
        dcl2.setWidth(120);
        dcl3.setField("standardCategoryName");
        dcl3.setTitle("标准类别名称");
        dcl3.setWidth(300);
        dcl3.setAlign("left");
        columnList.add(dcl1);
        columnList.add(dcl2);
        columnList.add(dcl3);
       if(list!=null&&!list.isEmpty())
        {
            for(int i=0;i<list.size();i++)
            {
                DatagridColumn dc = new DatagridColumn();
                dc.setField(list.get(i));
                dc.setTitle(list.get(i));
                dc.setWidth(100);
                dc.setAlign("right");
                columnList.add(dc); 
            }  
        }
        columnJson= JSON.toJSONString(columnList, SerializerFeature.UseSingleQuotes);
        return Action.SUCCESS;
        
    }
    public Integer getSSTId()
    {
        return SSTId;
    }
    public void setSSTId(Integer sSTId)
    {
        SSTId = sSTId;
    }
    public String getColumnJson()
    {
        return columnJson;
    }
    public void setColumnJson(String columnJson)
    {
        this.columnJson = columnJson;
    }   
}
