package com.lanyuan.assembly.notice;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * TODO 通知公告跳转类
 *
 * @author zlc
 * @date 2016-8-31 上午10:58:51
 */
public class ISkipForNotice extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceNotice noticeService;
    
    private String id;
    
    private SingleNotice data;
    
    public String execute(){
        return Action.SUCCESS;
    }
    
    public String add(){
        return Action.SUCCESS;
    }
    
    public String update(){
        
        if(id == null || id.isEmpty()){
            this.pause("2");
            return Action.ERROR;
        }
        data = noticeService.selectById(id);
        return Action.SUCCESS;
    }

    /**
     * @return 返回 id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id 设置
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return 返回 data
     */
    public SingleNotice getData()
    {
        return data;
    }

    /**
     * @param data 设置
     */
    public void setData(SingleNotice data)
    {
        this.data = data;
    }
    
}
