package com.lanyuan.actionapi.simplecontent;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.commonmodule.simplecontent.ServiceSimpleContent;
import com.lanyuan.assembly.commonmodule.simplecontent.SingleSimpleContent;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO  跳转  修改 企业方针目标
 *
 * @author zlc
 * @date 2016-9-2 上午10:12:39
 */
public class ISimplecontentUpdate extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    
    private static String contentid = "simplecontent";
    
    private String contenttitle;
    
    private String content;
    
    public String execute(){
        ServiceSimpleContent service = new ServiceSimpleContent();
        List<SingleSimpleContent> list = new ArrayList<SingleSimpleContent>();
        list = service.selectAll();
        if(list != null){
            contenttitle =  list.get(0).getContentTitle();
            content = list.get(0).getContent();
        }
        return Action.SUCCESS;
    }
    
    public String update(){
        if(content == null && content.isEmpty()){
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
        ServiceSimpleContent service = new ServiceSimpleContent();
        boolean update = service.updateContent(content, operator.getOperateIp(), contentid);
        if(update){
            this.printString("1", "修改成功！");
        }else{
            this.printString("2", "修改失败！");
        }
        return Action.SUCCESS;
    }

    /**
     * @return 返回 contenttitle
     */
    public String getContenttitle()
    {
        return contenttitle;
    }

    /**
     * @return 返回 content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param contenttitle 设置
     */
    public void setContenttitle(String contenttitle)
    {
        this.contenttitle = contenttitle;
    }

    /**
     * @param content 设置
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
}
