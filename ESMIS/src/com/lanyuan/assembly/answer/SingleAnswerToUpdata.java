package com.lanyuan.assembly.answer;

/**
 * 
 * TODO 回复Single类
 *
 * @author zlc
 * @date 2016-8-26 上午10:57:53
 */
public class SingleAnswerToUpdata
{
    /**
     * 回复人ID
     */
    private String receiveID;
    /**
     * 回复人姓名
     */
    private String receiveName;
    /**
     * 回复人IP
     */
    private String receiveIP;
    /**
     * 回复内容
     */
    private String receiveContent;
    /**
     * 是否回复
     */
    private Integer isreceive = 1;
    
    
    /**
     * @return 返回 isreceive
     */
    public Integer getIsreceive()
    {
        return isreceive;
    }
    /**
     * @return 返回 receiveName
     */
    public String getReceiveName()
    {
        return receiveName;
    }
    /**
     * @return 返回 receiveIP
     */
    public String getReceiveIP()
    {
        return receiveIP;
    }
    /**
     * @return 返回 receiveContent
     */
    public String getReceiveContent()
    {
        return receiveContent;
    }
    /**
     * @param receiveName 设置
     */
    public void setReceiveName(String receiveName)
    {
        this.receiveName = receiveName;
    }
    /**
     * @param receiveIP 设置
     */
    public void setReceiveIP(String receiveIP)
    {
        this.receiveIP = receiveIP;
    }
    /**
     * @param receiveContent 设置
     */
    public void setReceiveContent(String receiveContent)
    {
        this.receiveContent = receiveContent;
    }
    /**
     * @return 返回 receiveID
     */
    public String getReceiveID()
    {
        return receiveID;
    }
    /**
     * @param receiveID 设置
     */
    public void setReceiveID(String receiveID)
    {
        this.receiveID = receiveID;
    }

}
