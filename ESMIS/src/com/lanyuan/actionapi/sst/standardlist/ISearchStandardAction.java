package com.lanyuan.actionapi.sst.standardlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.util.StrUtil;
/**
 * 搜索标准列表
 * @author PBW
 *
 */
public class ISearchStandardAction extends ResultSearchAction {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	//项目编号
	private String layerItemId;
	//子分类编号
	private String subClassId;
	//标准分类
	private String standardClassify;
	//标准编号
	private String standardId;
	//标准名称
	private String standardName;
	//体系表编号
	private String SSTId;
	
	private String condition;
	@Autowired
	private ServiceSSTStandardList standardService;
	@Autowired
	private ServiceStandardLibrary serviceStandardLibrary;
	
	
	/**
	 * 跳转到标准显示页面
	 */
	public String execute(){
		boolean flag = false;
		if(!StrUtil.isEmpty(SSTId)){
			condition="SSTId="+SSTId;
			flag = true;
		}
		if(!StrUtil.isEmpty(layerItemId)){
			if(flag){
				condition+="&layerItemId="+layerItemId;
			}else{
				condition="layerItemId="+layerItemId;
			}
		}else if(!StrUtil.isEmpty(subClassId)){
			if(flag){
				condition+="&subClassId="+subClassId;
			}else{
				condition="subClassId="+subClassId;
			}
			
		}
		return this.SUCCESS;
	}
	/**
	 * 加载数据
	 * 1.根据子分类编号查询出标准列表
	 * 2.根据条件(标准分类,标准编号,标准名称) 查询出具体的标准
	 */
	public void loadData(){
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		ConditionGroup cond = this.getConditionGroupObject();
		OrderGroup order = this.getOrderGroupObject();
		 if (cond == null)
	        {
	            cond = new ConditionGroup();
	        }
	        if (order == null)
	        {
	            order = new OrderGroup();
	            // 按标准显示顺序排序
	            order.Add(ServiceSSTStandardList.MappingList.displayOrder.name(), true);
	            order.Add(ServiceSSTStandardList.MappingList.modifyTime.name(), true);
	        }
		//判断SSTId并添加
		if(!StrUtil.isEmpty(SSTId)){
			cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.SSTId.name(), SSTId, enumOperator.Equal));
		}
		//判断项目编号并添加
		if(!StrUtil.isEmpty(layerItemId)){
			cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.layerItemId.name(), layerItemId, enumOperator.Equal));
		}
		//判断子分类编号并添加
		if(!StrUtil.isEmpty(subClassId)){
			cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.subClassId.name(), subClassId, enumOperator.Equal));
		}
        // 如果条件查询和排序对象为空,实例化对象
       
        int recordCount = standardService.getTotalCount(cond);
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return ;
        }
        List<SingleSSTStandardList> rtnList = null;
        if (this.isBreakPage())
        {
            rtnList = standardService.selectPageList(getCurrentPage(),getLinesOfPage(), cond, order);
        }
        else
        {
            rtnList = standardService.selectList(cond, order);
        }
        for(SingleSSTStandardList s:rtnList){
        	 HashMap<String, String> returnData = new HashMap<String, String>();
        	 returnData.put("standardNo", s.getStandardNo());//标准编号
        	 returnData.put("standardName", s.getStandardName());//标准名称
        	 returnData.put("standardCategory", s.getStandardCategory());//标准类别
        	//替代标准
        	 String newStandardId = s.getNewStandardId();
        	 //Id
        	 returnData.put("standardId", s.getStandardId());
        	 returnData.put("displayOrder", s.getDisplayOrder().toString());
        	 //显示顺序
        	 String newStandard = "无";
//        	 if(newStandardId !=null && !newStandardId.isEmpty()){
//        		 SingleStandardLibraryData standard= serviceStandardLibrary.selectById(newStandardId);
//        		 if(standard!=null){
//        			 newStandard = standard.getStandardName(); 
//        		 }else{
//        			 newStandard="未知";
//        		 }
//        	 }
//        	 returnData.put("replaceStandard",newStandard);
        	 //有效状态
        	 String state = "未知";
        	 Integer x = s.getEffectiveState();
        	 if(x==null){
        		 x=0;
        	 }
        	 if(x==1){
        		 state="有效";
        	 }
        	 if(x==2){
        		 
        		 state="作废";
        	 }
        	 returnData.put("effectiveState", state);//标准状态
        	 datalist.add(returnData);
        }
	printString(datalist, recordCount, "1", "成功");
	}
	public String getSubClassId() {
		return subClassId;
	}
	public void setSubClassId(String subClassId) {
		this.subClassId = subClassId;
	}
	public String getStandardClassify() {
		return standardClassify;
	}
	public void setStandardClassify(String standardClassify) {
		this.standardClassify = standardClassify;
	}
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
