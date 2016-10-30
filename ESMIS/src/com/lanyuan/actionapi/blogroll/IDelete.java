package com.lanyuan.actionapi.blogroll;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 根据ID删除友情链接
 *
 * @author zlc
 * @date 2016-8-23 下午6:53:35
 */
public class IDelete extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceBlogroll blogrollService;

    private String id;
    
    public String execute(){
        
        if(id == null || id.isEmpty()){
            this.printString("2", "id为空！");
            return Action.ERROR;
        }
        int i = blogrollService.delete(id);
        if(i == 1){
            this.printString("1", "删除成功！");
        }else{
            this.printString("2", "删除失败！");
        }
        
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
    
}
