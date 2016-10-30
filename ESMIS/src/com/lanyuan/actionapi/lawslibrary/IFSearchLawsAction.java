package com.lanyuan.actionapi.lawslibrary;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.ServiceLaws.MappingList;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.opensymphony.xwork2.Action;

/**
 * @Description: 查询法律法规
 * @author
 * @date 2016-8-25 下午2:00:07
 */
public class IFSearchLawsAction extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceLaws lawsService;
    
    /**
     * wen
     */
    private String documentType;
    private String standardNo;
    private String standardName;
    private String standardCategory;
    
    public String searchLaws()
    {
        // 调用父类方法,条件查询和排序
        ConditionGroup cond = new ConditionGroup();
        OrderGroup order = new OrderGroup();
        // 如果条件查询和排序对象为空,实例化对象
        // 按法规id排序
        order.Add(MappingList.standardId.name(), true);
        
        cond.addWithAnd(new ConditionNormal(ServiceLaws.MappingList.documentType.name(),documentType));
        if(standardNo!=null&&!"".equals(standardNo)&&standardNo!="-1"){
        cond.addWithAnd(new ConditionNormal(ServiceLaws.MappingList.standardNo.name(),standardNo,enumOperator.Like));
        }
        if(standardName!=null&&!"".equals(standardName)&&standardName!="-1"){
        	cond.addWithAnd(new ConditionNormal(ServiceLaws.MappingList.standardName.name(),standardName,enumOperator.Like));
        }
        if(standardCategory!=null&&!"".equals(standardCategory)&&!"-1".equals(standardCategory)){
        	cond.addWithAnd(new ConditionNormal(ServiceLaws.MappingList.standardCategory.name(),standardCategory));
        }
        
        int recordCount = lawsService.getTotalCount(cond);
        
        //System.out.println(documentType);
        
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return Action.SUCCESS;
        }
        List<SingleLaws> rtnList = null;
        if (this.isBreakPage())
        {
            rtnList = lawsService.selectPageList(getCurrentPage(),getLinesOfPage(), cond, order);
        }
        else
        {
            rtnList = lawsService.selectList(cond, order);
        }
        List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        for (SingleLaws single : rtnList)
        {
            HashMap<String, String> returnData = new HashMap<String, String>();
            returnData.put(MappingList.standardId.name(), single.getStandardId());
            returnData.put(MappingList.standardNo.name(), single.getStandardNo());
            returnData.put(MappingList.standardName.name(), single.getStandardName());
            returnData.put(MappingList.browseVolume.name(), single.getBrowseVolume()+ "");
            returnData.put(MappingList.effectiveDate.name(), single.getEffectiveDate()+ "");
            returnData.put(MappingList.documentType.name(), single.getDocumentType()+ "");
            returnData.put(MappingList.standardCategory.name(), single.getStandardCategory()+ "");
            returnData.put(MappingList.approvedDate.name(), single.getApprovedDate()+ "");
            
            datalist.add(returnData);
        }
        printString(datalist, recordCount, "1", null);
        return Action.SUCCESS;
    }

	public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
