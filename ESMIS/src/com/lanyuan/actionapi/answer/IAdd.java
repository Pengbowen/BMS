package com.lanyuan.actionapi.answer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswerToAdd;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 意见建议新增接口（分享标准，我要标准。）
 *
 * @author zlc
 * @date 2016-8-26 下午5:59:12
 */
public class IAdd extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceAnswer answerService;
    
    private String submitTitle;//提交标题
    private String submitContent;//提交内容
    private String filePath;//文件存放位置
    private String submitorMobile;//提交人手机号
    private String submitorEmail;//提交人邮箱
    
    //意见建议
    public String addAnswer(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(submitTitle == null && submitTitle.isEmpty()){
            this.printString("2", "提交标题为空！");
            return Action.ERROR;
        }
        if(submitContent == null && submitContent.isEmpty()){
            this.printString("2", "提交内容为空！");
            return Action.ERROR;
        }
        if(submitorEmail == null && submitorEmail.isEmpty()){
            this.printString("2", "提交人邮箱为空！");
            return Action.ERROR;
        }
        LoginUser user = null;
        OperatorInfo operator = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){
                operator = new OperatorInfo("111111", "游客", "0.0.0.0");
            }
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitTitle(submitTitle);
        single.setSubmitContent(submitContent);
        single.setSubmitID(operator.getOperator());
        single.setSubmitIP(operator.getOperateIp());
        single.setSubmitName(operator.getOperatorName());
        single.setSubmitorEmail(submitorEmail);
        single.setSubmitorMobile(submitorMobile);
        
        
        int i = answerService.addAnswer(single);
        if(i == 1){
            this.printString("1", "添加成功！");
        }else{
            this.printString("2", "添加失败！");
        }
        return Action.SUCCESS;
    }

    //分享标准
    public String addCriteria(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(submitTitle == null && submitTitle.isEmpty()){
            this.printString("2", "提交标题为空！");
            return Action.ERROR;
        }
        if(submitContent == null && submitContent.isEmpty()){
            this.printString("2", "提交内容为空！");
            return Action.ERROR;
        }
        if(submitorEmail == null && submitorEmail.isEmpty()){
            this.printString("2", "提交人邮箱为空！");
            return Action.ERROR;
        }
        int start = submitContent.indexOf("href=\"")+6;
        if(start > 6){
            int end = submitContent.indexOf("\"",start);
            filePath = submitContent.substring(start, end);
        }
        LoginUser user = null;
        OperatorInfo operator = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){
                operator = new OperatorInfo("111111", "游客", "0.0.0.0");
            }
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitTitle(submitTitle);
        single.setSubmitContent(submitContent);
        single.setSubmitID(operator.getOperator());
        single.setSubmitIP(operator.getOperateIp());
        single.setSubmitName(operator.getOperatorName());
        single.setSubmitorEmail(submitorEmail);
        single.setSubmitorMobile(submitorMobile);
        
        
        int i = answerService.addCriteria(single,filePath);
        if(i == 1){
            this.printString("1", "添加成功！");
        }else{
            this.printString("2", "添加失败！");
        }
        return Action.SUCCESS;
    }
    
    //我要标准
    public String addAskForCriteria(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(submitTitle == null && submitTitle.isEmpty()){
            this.printString("2", "提交标题为空！");
            return Action.ERROR;
        }
        if(submitContent == null && submitContent.isEmpty()){
            this.printString("2", "提交内容为空！");
            return Action.ERROR;
        }
        if(submitorEmail == null && submitorEmail.isEmpty()){
            this.printString("2", "提交人邮箱为空！");
            return Action.ERROR;
        }
        
        LoginUser user = null;
        OperatorInfo operator = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){
                operator = new OperatorInfo("111111", "游客", "0.0.0.0");
            }
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        
        SingleAnswerToAdd single = new SingleAnswerToAdd();
        single.setSubmitTitle(submitTitle);
        single.setSubmitContent(submitContent);
        single.setSubmitID(operator.getOperator());
        single.setSubmitIP(operator.getOperateIp());
        single.setSubmitName(operator.getOperatorName());
        single.setSubmitorEmail(submitorEmail);
        single.setSubmitorMobile(submitorMobile);
        
        
        int i = answerService.addAskForCriteria(single);
        if(i == 1){
            this.printString("1", "添加成功！");
        }else{
            this.printString("2", "添加失败！");
        }
        return Action.SUCCESS;
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
