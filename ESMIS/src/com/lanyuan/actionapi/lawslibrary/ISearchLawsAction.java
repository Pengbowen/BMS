package com.lanyuan.actionapi.lawslibrary;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
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
public class ISearchLawsAction extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceLaws lawsService;
    public String searchLaws()
    {
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
        int recordCount = lawsService.getTotalCount(cond);
        
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
            returnData.put(MappingList.effectiveState.name(), single.getEffectiveState()+ "");
            returnData.put(MappingList.approvedDate.name(), single.getApprovedDate()+ "");
            
            datalist.add(returnData);
        }
        printString(datalist, recordCount, "1", null);
        return Action.SUCCESS;
    }
}
