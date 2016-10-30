package com.lanyuan.actionapi.blogroll;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 添加友情链接
 *
 * @author zlc
 * @date 2016-8-23 下午6:19:49
 */
public class IAdd extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceBlogroll blogrollService;
    
    private String linkName;//文字标题
    
    private String linkeUrl;//链接地址
    
    private String pictureUrl;//图片地址
    
    private String orderNumber;//显示顺序
    
    public String execute(){
        
        if(linkName == null && linkName.isEmpty()){
            this.printString("2", "文字标题为空！");
            return Action.ERROR;
        }
        if(linkeUrl == null && linkeUrl.isEmpty()){
            this.printString("2", "链接地址为空！");
            return Action.ERROR;
        }
        SingleBlogroll single = new SingleBlogroll();
        single.setLinkeUrl(linkeUrl);
        single.setLinkName(linkName);
        if(orderNumber == null || orderNumber.length() == 0){
            orderNumber = "0";
        }
        single.setOrderNumber(Integer.valueOf(orderNumber));
        single.setPictureUrl(pictureUrl);
        int i = blogrollService.add(single);
        if(i == 1){
            this.printString("1", "添加成功！");
        }else{
            this.printString("2", "添加失败！");
        }
        return Action.SUCCESS;
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
