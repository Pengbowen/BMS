package com.lanyuan.actionapi.notice;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNotice;
import com.lanyuan.assembly.notice.SingleNoticeToOperate;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * TODO 通知公告跳转类
 *
 * @author zlc
 * @date 2016-8-31 下午4:05:06
 */
public class ISkipForNotice extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceNotice noticeService;
    
    private String id;
    
    //截止时间
    private String deadline;
    
    //标题
    private String noticeTitle;
    
    //内容
    private String noticeContent;
    
    //发布人
    private String publisherrName;
    
    //发布时间
    private String publishTime;
    
    //浏览量
    private int count;
    
    public String execute(){
        return Action.SUCCESS;
    }
    
    public String add(){
        return Action.SUCCESS;
    }
    
    public String update(){
        if(id == null && id.isEmpty()){
            this.pause("2");
            return Action.SUCCESS;
        }
        SingleNotice single = new SingleNotice();
        single = noticeService.selectById(id);
        if(single != null){
            id = single.getId()+"";
            deadline = single.getDeadline();
            noticeTitle = single.getNoticeTitle();
            noticeContent = single.getNoticeContent();
            publisherrName = single.getPublisherrName();
            publishTime = single.getPublishTime();
        }
        return Action.SUCCESS;
    }
    
    public String detail(){
        if(id == null && id.isEmpty()){
            this.pause("2");
            return Action.SUCCESS;
        }
        SingleNotice single = new SingleNotice();
        single = noticeService.selectById(id);
        if(single != null){
            
            id = single.getId()+"";
            count = single.getShowNum()==null ? 0 : single.getShowNum();
            deadline = single.getDeadline();
            noticeTitle = single.getNoticeTitle();
            noticeContent = single.getNoticeContent();
            publisherrName = single.getPublisherrName();
            publishTime = single.getPublishTime().substring(0,10);
            SingleNoticeToOperate singleOperate = new SingleNoticeToOperate();
            singleOperate.setShowNum(++count);
            noticeService.update(singleOperate, null, Integer.valueOf(id));
        }
        return Action.SUCCESS;
    }
    
    /**
     * @return 返回 deadline
     */
    public String getDeadline()
    {
        return deadline;
    }

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
     * @param deadline 设置
     */
    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
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
     * @return 返回 publisherrName
     */
    public String getPublisherrName()
    {
        return publisherrName;
    }

    /**
     * @return 返回 publishTime
     */
    public String getPublishTime()
    {
        return publishTime;
    }

    /**
     * @param publisherrName 设置
     */
    public void setPublisherrName(String publisherrName)
    {
        this.publisherrName = publisherrName;
    }

    /**
     * @param publishTime 设置
     */
    public void setPublishTime(String publishTime)
    {
        this.publishTime = publishTime;
    }

    /**
     * @return 返回 count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param count 设置
     */
    public void setCount(int count)
    {
        this.count = count;
    }
    
    
}
