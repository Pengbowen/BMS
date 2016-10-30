package com.lanyuan.actionapi.answer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswer;
import com.lanyuan.assembly.answer.SingleAnswerToUpdata;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.mail.SendMailUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 回复意见建议（分享标准、我要标准）
 *
 * @author zlc
 * @date 2016-8-27 上午10:22:08
 */
public class IUpdate extends ResultOperateAction
{

    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceAnswer answerService;

    private String id;//id
    
    private String receiveContent;//回复内容
    
    public String execute(){
        HttpServletRequest request = ServletActionContext.getRequest();
        if(id == null || id.isEmpty()){
            this.printString("2", "id为空！");
            return Action.ERROR;
        }
        if(receiveContent == null || receiveContent.isEmpty()){
            this.printString("2", "回复内容为空！");
            return Action.ERROR;
        }
        
        LoginUser user = null;
        OperatorInfo operator = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){printString("-4", "非法访问！");return null;}
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        
        SingleAnswerToUpdata single = new SingleAnswerToUpdata();
        single.setReceiveContent(receiveContent.replaceAll("\n", "").trim());
        single.setReceiveIP(operator.getOperateIp());
        single.setReceiveName(operator.getOperatorName());
        single.setReceiveID(operator.getOperator());
        int i = answerService.replyAnswer(single, Integer.valueOf(id));
        if(i > 0){
            SingleAnswer singleAnswer = answerService.selectById(id);
            if(singleAnswer != null){
                String message = SendMailUtil.send(singleAnswer.getSubmitorEmail(), singleAnswer.getSubmitTitle(), singleAnswer.getReceiveContent());
                System.out.println("message="+message);
                this.printString("1", "回复成功！");
            }else{
                this.printString("1", "回复回复邮件发送失败！");
            }
        }else{
            this.printString("2", "回复失败！");
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
     * @return 返回 receiveContent
     */
    public String getReceiveContent()
    {
        return receiveContent;
    }

    /**
     * @param id 设置
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @param receiveContent 设置
     */
    public void setReceiveContent(String receiveContent)
    {
        this.receiveContent = receiveContent;
    }

}
