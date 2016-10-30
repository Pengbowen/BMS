package com.lanyuan.actionapi.sst;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
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
import com.opensymphony.xwork2.Action;

/**
 * 法律法规查询 位置：首页-企业标准体系总图-法律法规查询
 *
 * @author ly-two
 * @date 2016-10-11 上午10:16:43
 */

public class ISearchLawsAction extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;
    /**
     * 法规list集合
     */
    private List<SingleStandardLibraryData> lawsDataList;
    /**
     * 法规文号
     */
    private String standardNo;
    /**
     * 法规名称
     */
    private String standardName;
    /**
     * 分类
     */
    private String standardCategory;
    /***
     * 总页码
     */
    private String sumPage;
    /**
     * 当前页
     */
    private int currentPage=1;

    public String execute()
    {
        if(StringUtils.isBlank(standardNo) && StringUtils.isBlank(standardName)){
            return Action.SUCCESS;
        }
        // 调用父类方法,条件查询和排序
        ConditionGroup cond = new ConditionGroup();
        cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.documentType.name(),
                "2", enumOperator.Equal));
        if (!StringUtils.isBlank(standardNo))
        {
            cond.addWithAnd(new ConditionNormal(
                    ServiceStandardLibrary.MappingList.standardNo.name(), standardNo,
                    enumOperator.Like));
        }
        if (!StringUtils.isBlank(standardName))
        {
            cond.addWithAnd(new ConditionNormal(
                    ServiceStandardLibrary.MappingList.standardName.name(), standardName,
                    enumOperator.Like));
        }
        if (!StringUtils.isBlank(standardCategory))
        {
            cond.addWithAnd(new ConditionNormal(
                    ServiceStandardLibrary.MappingList.standardCategory.name(), standardCategory,
                    enumOperator.Equal));
        }
        OrderGroup order = new OrderGroup();
        order.Add(MappingList.standardId.name(), true);
        int recordCount = standardLibraryService.getTotalCount(cond);
        if (recordCount <= 0)
        {
            return Action.SUCCESS;
        }
        else
        {
            if (recordCount % getLinesOfPage() == 0)
            {
                sumPage = recordCount / 20 + "";
            }
            else
            {
                sumPage = recordCount / 20 + 1 + "";
            }
        }
        if (this.isBreakPage())
        {
            lawsDataList = standardLibraryService.selectPageList(currentPage, 20, cond, order);
        }
        else
        {
            lawsDataList = standardLibraryService.selectList(cond, order);
        }

        if (lawsDataList != null)
        {
            for (int i = 0; i < lawsDataList.size(); i++)
            {
                Map<String, String> lock = EncryptUtil.encrypt(lawsDataList.get(i).getStandardId());
                lawsDataList.get(i).setModifyer(lock.get("str"));
                lawsDataList.get(i).setModifyerName(lock.get("key"));
            }
        }
        return Action.SUCCESS;
    }

    public List<SingleStandardLibraryData> getLawsDataList()
    {
        return lawsDataList;
    }

    public String getSumPage()
    {
        return sumPage;
    }

    public void setSumPage(String sumPage)
    {
        this.sumPage = sumPage;
    }

    public String getStandardNo()
    {
        return standardNo;
    }

    public void setStandardNo(String standardNo)
    {
        this.standardNo = standardNo;
    }

    public String getStandardName()
    {
        return standardName;
    }

    public void setStandardName(String standardName)
    {
        this.standardName = standardName;
    }

    public String getStandardCategory()
    {
        return standardCategory;
    }

    public void setStandardCategory(String standardCategory)
    {
        this.standardCategory = standardCategory;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
}
