package com.lanyuan.assembly.notice;

public class SingleNoticeToOperate
{
    /**
     * 通知公告标题
     */
    private String noticeTitle;
    /**
     * 通知公告内容
     */
    private String noticeContent;
    /**
     * 显示顺序
     */
    private Integer showNum;
    /**
     * 截止时间
     */
    private String deadline;
    /**
     * @return 返回 noticeTitle
     */
    public String getNoticeTitle()
    {
        return noticeTitle;
    }
    /**
     * @return 返回 noticeContent
     */
    public String getNoticeContent()
    {
        return noticeContent;
    }
    /**
     * @return 返回 showNum
     */
    public Integer getShowNum()
    {
        return showNum;
    }
    /**
     * @return 返回 deadline
     */
    public String getDeadline()
    {
        return deadline;
    }
    /**
     * @param noticeTitle 设置
     */
    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }
    /**
     * @param noticeContent 设置
     */
    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }
    /**
     * @param showNum 设置
     */
    public void setShowNum(Integer showNum)
    {
        this.showNum = showNum;
    }
    /**
     * @param deadline 设置
     */
    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }
}
