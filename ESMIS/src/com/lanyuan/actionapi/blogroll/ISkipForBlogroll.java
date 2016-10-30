package com.lanyuan.actionapi.blogroll;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * TODO 友情链接跳转功能集合类
 *
 * @author zlc
 * @date 2016-8-25 下午14:53:35
 */
public class ISkipForBlogroll extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceBlogroll blogrollService;

    private String id;
    
    private String linkName;
    
    private String linkeUrl;
    
    private String pictureUrl;
    
    private String orderNumber;
    
    public String execute()
    {
        return Action.SUCCESS;
    }
    
    public String add(){
        return Action.SUCCESS;
    }
    
    public String update(){
        if(id == null && id.isEmpty()){
            this.pause("2.ID为空!");
            return Action.SUCCESS;
        }
        SingleBlogroll single = new SingleBlogroll();
        single = blogrollService.selectById(id);
        if(single != null){
            id = single.getId();
            linkName = single.getLinkName();
            linkeUrl = single.getLinkeUrl();
            pictureUrl = single.getPictureUrl();
            orderNumber = single.getOrderNumber()+"";
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
     * @return 返回 linkName
     */
    public String getLinkName()
    {
        return linkName;
    }

    /**
     * @return 返回 linkeUrl
     */
    public String getLinkeUrl()
    {
        return linkeUrl;
    }

    /**
     * @return 返回 pictureUrl
     */
    public String getPictureUrl()
    {
        return pictureUrl;
    }

    /**
     * @return 返回 orderNumber
     */
    public String getOrderNumber()
    {
        return orderNumber;
    }

    /**
     * @param id 设置
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param linkName 设置
     */
    public void setLinkName(String linkName)
    {
        this.linkName = linkName;
    }

    /**
     * @param linkeUrl 设置
     */
    public void setLinkeUrl(String linkeUrl)
    {
        this.linkeUrl = linkeUrl;
    }

    /**
     * @param pictureUrl 设置
     */
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @param orderNumber 设置
     */
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
}
