package com.lanyuan.assembly.blogroll;

/**
 * 
 * TODO 业务Single 没有ID   SingleBlogrollToAll 有表完整字段
 *
 * @author zlc
 * @date 2016-8-23 下午2:37:36
 */
public class SingleBlogroll
{
    /**
     * id
     */
    private String id;
    
    /**
     * 文字标题
     */
    private String linkName;
    
    /**
     * 链接地址
     */
    private String linkeUrl;
    
    /**
     * 图片地址
     */
    private String pictureUrl;
    
    /**
     * 显示顺序
     */ 
    private int orderNumber;

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
    public int getOrderNumber()
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
    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
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
    protected void setId(String id)
    {
        this.id = id;
    }
}
