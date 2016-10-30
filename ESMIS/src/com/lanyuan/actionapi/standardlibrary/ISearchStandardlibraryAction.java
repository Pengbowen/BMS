package com.lanyuan.actionapi.standardlibrary;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.util.EncryptUtil;

public class ISearchStandardlibraryAction extends ResultSearchAction{
	private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;
    
    private String applicableMajorName;//专业名称

    private Integer zuofei;//作废
    
    public void searchStandardlibrary() throws UnsupportedEncodingException{
    	 // 调用父类方法,条件查询和排序
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
            // 按法规id排序
           order.Add(MappingList.effectiveDate.name(), false);
        }
        /*//根据条件添加专业查询condition
        if(!StringUtils.isBlank(applicableMajorName)){
            applicableMajorName = URLDecoder.decode(applicableMajorName, "utf-8");
            ServiceDictionaryData sdd = new ServiceDictionaryData();
            //获取专业code
            List<Map<String, String>> list = sdd.searchByContent(1001, applicableMajorName);
            if(list != null && !list.isEmpty()){
                //添加查询条件
                for (Map<String, String> map : list)
                {
                    cond.addWithOr(new ConditionNormal(ServiceStandardLibrary.MappingList.applicableMajor.name(),map.get("code"),enumOperator.Like));
                }
            }
        }*/
        
        if(zuofei != 1){
            cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.effectiveState.name(),"1"));
        }
        
        String applicableMajor = cond.getConditionValueWithConditionName("applicableMajor");
        if(!StringUtils.isBlank(applicableMajor)){
        	cond.removeConditionWithConditionName("applicableMajor");
        	
        	String[] str = applicableMajor.split(",");
        	
        	ConditionGroup condapp = new ConditionGroup();
        	for(int i=1;i<str.length;i++){
        		condapp.addWithOr(new ConditionNormal(ServiceStandardLibrary.MappingList.applicableMajor.name(),str[i],enumOperator.Like));
        	}
        	cond.addWithAnd(condapp);
        }
        
        int recordCount = standardLibraryService.getTotalCount(cond);
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "没有数据！");
            return;
        }
        List<SingleStandardLibraryData> rtnList = null;
        if (this.isBreakPage())
        {
        	rtnList = standardLibraryService.selectPageList(getCurrentPage(),
                                                                  getLinesOfPage(), cond, order);
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
            returnData.put(MappingList.standardNo.name(), single.getStandardNo());
            returnData.put(MappingList.standardName.name(), single.getStandardName());
            returnData.put(MappingList.standardCategory.name(), single.getStandardCategory() + "");
            returnData.put(MappingList.standardCategoryName.name(), single.getStandardCategoryName() + "");
            returnData.put(MappingList.dataSource.name(), single.getDataSource() + "");
            returnData.put(MappingList.oldStandardNo.name(), single.getOldStandardNo());
            returnData.put(MappingList.oldStandardId.name(), single.getOldStandardId());
            returnData.put(MappingList.effectiveState.name(), single.getEffectiveState() + "");
            returnData.put(MappingList.SSTLoaction.name(), single.getSSTLoaction() + "");
            returnData.put(MappingList.browseVolume.name(), single.getBrowseVolume() + "");
            returnData.put(MappingList.documentType.name(), single.getDocumentType() + "");
            returnData.put(MappingList.approvedDate.name(),single.getApprovedDate()==null?"数据缺失！":single.getApprovedDate());
            returnData.put(MappingList.effectiveDate.name(),single.getEffectiveDate()==null?"数据缺失！":single.getEffectiveDate());
           // System.out.println(single.getApprovedDate()+"----------");
            Map<String, String> map=EncryptUtil.encrypt(single.getStandardId());
            returnData.put("key", map.get("key"));
            returnData.put("str", map.get("str"));
            datalist.add(returnData);
        }
        this.printString(datalist, recordCount, "1", null);
    }

    /**
     * @return 返回 applicableMajorName
     */
    public String getApplicableMajorName()
    {
        return applicableMajorName;
    }

    /**
     * @param applicableMajorName 设置
     */
    public void setApplicableMajorName(String applicableMajorName)
    {
        this.applicableMajorName = applicableMajorName;
    }

    /**
     * @return 返回 zuofei
     */
    public Integer getZuofei()
    {
        return zuofei;
    }

    /**
     * @param zuofei 设置
     */
    public void setZuofei(Integer zuofei)
    {
        this.zuofei = zuofei;
    }


}
