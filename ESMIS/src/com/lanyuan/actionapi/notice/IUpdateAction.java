package com.lanyuan.actionapi.notice;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNoticeToOperate;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 修改通知公告
 *
 * @author zlc
 * @date 2016-8-31 上午11:00:39
 */
public class IUpdateAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceNotice noticeService;
    
    //截止时间
    private String deadline;
    
    //显示顺序
    private Integer showNum;
    
    //通知公告内容
    private String noticeContent;
    
    //通知公告标题
    private String noticeTitle;
    
    private Integer id;
    
    public String execute(){
        
        if(noticeTitle == null && noticeTitle.isEmpty()){
            this.printString("2", "标题为空！");
            return Action.ERROR;
        }
        if(noticeContent == null && noticeContent.isEmpty()){
            this.printString("2", "内容为空！");
            return Action.ERROR;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        OperatorInfo operator = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){printString("-4", "非法访问！");return Action.ERROR;}
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        SingleNoticeToOperate single = new SingleNoticeToOperate();
        single.setDeadline(deadline);
        single.setNoticeContent(noticeContent);
        single.setNoticeTitle(noticeTitle);
        single.setShowNum(showNum);
        
        int i = noticeService.update(single, operator,id);
        if(i == 1){
            this.printString("1", "修改成功！");
        }else{
            this.printString("2", "修改失败！");
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
     * @return 返回 showNum
     */
    public Integer getShowNum()
    {
        return showNum;
    }

    /**
     * @return 返回 noticeContent
     */
    public String getNoticeContent()
    {
        return noticeContent;
    }

    /**
     * @return 返回 noticeTitle
     */
    public String getNoticeTitle()
    {
        return noticeTitle;
    }

    /**
     * @return 返回 id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param deadline 设置
     */
    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }

    /**
     * @param showNum 设置
     */
    public void setShowNum(Integer showNum)
    {
        this.showNum = showNum;
    }

    /**
     * @param noticeContent 设置
     */
    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    /**
     * @param noticeTitle 设置
     */
    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    /**
     * @param id 设置
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
}
