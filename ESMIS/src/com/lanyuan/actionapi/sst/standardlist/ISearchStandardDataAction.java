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
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;

public class ISearchStandardDataAction extends ResultSearchAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ServiceStandardLibrary standardLibraryService;
	
	private String documentType;//文档类型 1-标准,2-法律法规
	
	public String execute(){

   	 // 调用父类方法,条件查询和排序
       ConditionGroup cond = this.getConditionGroupObject();
       OrderGroup order = this.getOrderGroupObject();
       // 如果条件查询和排序对象为空,实例化对象
       //添加查找类型
      
       if (cond == null){
           cond = new ConditionGroup();
           //添加查找类型
           cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.documentType.name(), documentType, enumOperator.Equal));
       }else{
    	   cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.documentType.name(), documentType, enumOperator.Equal)); 
       }
      
       if (order == null)
       {
           order = new OrderGroup();
           // 按法规id排序
          order.Add(MappingList.standardId.name(), true);
       }
       int recordCount = standardLibraryService.getTotalCount(cond);
       if (recordCount <= 0)
       {
           printString(null, 0, "2", "没有数据！");
            return this.SUCCESS;
       }
       List<SingleStandardLibraryData> rtnList = null;
       if (this.isBreakPage())
       {
       	rtnList = standardLibraryService.selectPageList(getCurrentPage(), getLinesOfPage(), cond, order);
       }
       else
       {
           rtnList = standardLibraryService.selectList(cond, order);
       }
      
       List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
       for (SingleStandardLibraryData single : rtnList)
       {
           HashMap<String, String> returnData = new HashMap<String, String>();
           returnData.put(MappingList.standardId.name(), single.getStandardId());
           returnData.put(MappingList.standardNo.name(), single.getStandardNo());//标准编号
           returnData.put(MappingList.standardName.name(), single.getStandardName());//标准名称
           returnData.put(MappingList.standardCategory.name(), single.getStandardCategory() + "");//标准类别
           returnData.put(MappingList.standardCategoryName.name(), single.getStandardCategoryName() + "");//标准类别名称
           datalist.add(returnData);
       }
       this.printString(datalist, recordCount, "1", null);
		return this.SUCCESS;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
}
