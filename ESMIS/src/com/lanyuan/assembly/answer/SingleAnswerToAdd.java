package com.lanyuan.assembly.answer;

/**
 * 
 * TODO 新增Single类
 *
 * @author zlc
 * @date 2016-8-26 上午10:58:24
 */
public class SingleAnswerToAdd
{
    /**
     * 提交标题
     */
    private String submitTitle;
    /**
     * 提交内容
     */
    private String submitContent;
    /**
     * 提交人ID
     */
    private String submitID;
    /**
     * 提交人姓名
     */
    private String submitName;
    /**
     * 提交IP
     */
    private String submitIP;
    /**
     * 提交人手机号
     */
    private String submitorMobile;
    /**
     * 提交人邮箱
     */
    private String submitorEmail;
    /**
     * 是否回复
     */
    private Integer isreceive = 0;
    
    /**
     * @return 返回 isreceive
     */
    public Integer getIsreceive()
    {
        return isreceive;
    }
    /**
     * @return 返回 submitTitle
     */
    public String getSubmitTitle()
    {
        return submitTitle;
    }
    /**
     * @return 返回 submitContent
     */
    public String getSubmitContent()
    {
        return submitContent;
    }
    /**
     * @return 返回 submitID
     */
    public String getSubmitID()
    {
        return submitID;
    }
    /**
     * @return 返回 submitName
     */
    public String getSubmitName()
    {
        return submitName;
    }
    /**
     * @return 返回 submitIP
     */
    public String getSubmitIP()
    {
        return submitIP;
    }
    /**
     * @return 返回 submitorMobile
     */
    public String getSubmitorMobile()
    {
        return submitorMobile;
    }
    /**
     * @return 返回 submitorEmail
     */
    public String getSubmitorEmail()
    {
        return submitorEmail;
    }
    /**
     * @param submitTitle 设置
     */
    public void setSubmitTitle(String submitTitle)
    {
        this.submitTitle = submitTitle;
    }
    /**
     * @param submitContent 设置
     */
    public void setSubmitContent(String submitContent)
    {
        this.submitContent = submitContent;
    }
    /**
     * @param submitID 设置
     */
    public void setSubmitID(String submitID)
    {
        this.submitID = submitID;
    }
    /**
     * @param submitName 设置
     */
    public void setSubmitName(String submitName)
    {
        this.submitName = submitName;
    }
    /**
     * @param submitIP 设置
     */
    public void setSubmitIP(String submitIP)
    {
        this.submitIP = submitIP;
    }
    /**
     * @param submitorMobile 设置
     */
    public void setSubmitorMobile(String submitorMobile)
    {
        this.submitorMobile = submitorMobile;
    }
    /**
     * @param submitorEmail 设置
     */
    public void setSubmitorEmail(String submitorEmail)
    {
        this.submitorEmail = submitorEmail;
    }
    
    
}
