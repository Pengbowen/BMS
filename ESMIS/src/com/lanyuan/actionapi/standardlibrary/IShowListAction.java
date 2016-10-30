package com.lanyuan.actionapi.standardlibrary;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class IShowListAction extends ActionSupport
{
    /**
     * 通用跳转列表页面
     */
    private static final long serialVersionUID = 1L;

    //显示页面
    public String execute()
    {
        return Action.SUCCESS;
    }
}
