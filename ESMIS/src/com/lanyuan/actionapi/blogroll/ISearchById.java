package com.lanyuan.actionapi.blogroll;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;
import com.opensymphony.xwork2.Action;

public class ISearchById extends ResultDetailAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceBlogroll blogrollService;
    
    private String id;
    
    public String search(){
        
        if(id == null || id.isEmpty()){
            this.printString(null, "2", "ID为空！");
            return Action.ERROR;
        }
        SingleBlogroll single = new SingleBlogroll();
        single = blogrollService.selectById(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(single == null){
            this.printString(null, "2", "数据不存在！");
            return Action.ERROR;
        }else {
            map.put("id", single.getId());
            map.put("linkeUrl", single.getLinkeUrl());
            map.put("linkName", single.getLinkName());
            map.put("pictureUrl", single.getPictureUrl());
            map.put("orderNumber", single.getOrderNumber());
        }
        this.printString(map, "1", "");
        
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
