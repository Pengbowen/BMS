package com.lanyuan.actionapi.lawslibrary;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class IShowListAction extends ActionSupport
{
    /**
     * 通用跳转列表页面
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 法规类别
     */
    private String standardCategory;
    //显示页面
    public String execute()
    {
        return Action.SUCCESS;
    }
    /**
     * @return 返回 standardCategory
     */
    public String getStandardCategory()
    {
        return standardCategory;
    }
    /**
     * @param standardCategory 设置
     */
    public void setStandardCategory(String standardCategory)
    {
        this.standardCategory = standardCategory;
    }

}
