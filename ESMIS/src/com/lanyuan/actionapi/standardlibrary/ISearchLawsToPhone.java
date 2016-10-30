package com.lanyuan.actionapi.standardlibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.util.EncryptUtil;
import com.lanyuan.assembly.util.LawAndStandardUtil;

public class ISearchLawsToPhone extends ResultSearchAction
{

    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;
    public void searchToPhone(){
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
           order.Add(MappingList.standardId.name(), true);
        }
        cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.documentType.name(), "2"));
        String applicableMajor = cond.getConditionValueWithConditionName("applicableMajor");
        if(!StringUtils.isBlank(applicableMajor)){
            cond.removeConditionWithConditionName("applicableMajor");
            
            String[] str = applicableMajor.split(",");
            
            ConditionGroup condapp = new ConditionGroup();
            for(int i=0;i<str.length;i++){
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
            returnData.put(MappingList.effectiveDate.name(), single.getEffectiveDate() + "");
            Map<String, String> map=EncryptUtil.encrypt(single.getStandardId());
            returnData.put("key", map.get("key"));
            returnData.put("str", map.get("str"));
            //判断文件是否存在   优先判断epub文件
            String path = LawAndStandardUtil.getPath(single.getStandardId(), "epub");
            if(new File(path).exists()){
                returnData.put("fileExists", "1");
            }else{
                path = LawAndStandardUtil.getPath(single.getStandardId(), "pdf");
                if(new File(path).exists()){
                    returnData.put("fileExists", "2");
                }else{
                    returnData.put("fileExists", "0");
                }
            }
            datalist.add(returnData);
        }
        this.printString(datalist, recordCount, "1", null);
    }

}
