package com.lanyuan.actionapi.answer;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswer;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * TODO 意见建议跳转功能集合类
 *
 * @author zlc
 * @date 2016-8-25 下午14:53:35
 */
public class ISkipForAnswer extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceAnswer answerService;

    private String id;
    
    private String submitType;//提交类型
    
    private String submitTitle;//提交标题
    
    private SingleAnswer detail;//详情
    
    private String type;//回复类型
    
    private String title;
    
    public String execute()
    {   
        return Action.SUCCESS;
    }
    
    public String add(){
        if(submitType != null && submitType.length() > 0){
            int i = Integer.valueOf(submitType);
            switch (i)
            {
            case 0:
                title = "意见建议";
                break;
            case 1:
                title = "分享标准";         
                break;
            case 2:
                title = "我要标准";
                break;
            }
            
        }
        return Action.SUCCESS;
    }
    
    public String update(){
        if(id == null && id.isEmpty()){
            this.pause("2.ID为空!");
            return Action.SUCCESS;
        }
        SingleAnswer single = new SingleAnswer();
        single = answerService.selectById(id);
        if(single != null){
            id = single.getId()+"";
            submitTitle = single.getSubmitTitle();
            type = single.getSubmitType()+"";
        }
        return Action.SUCCESS;
    }
    
    public String detail(){
        if(id == null && id.isEmpty()){
            this.pause("2.ID为空!");
            return Action.SUCCESS;
        }
        SingleAnswer single = new SingleAnswer();
        single = answerService.selectById(id);
        if(single != null){
            detail = single;
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
     * @param id 设置
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @return 返回 submitTitle
     */
    public String getSubmitTitle()
    {
        return submitTitle;
    }

    /**
     * @param submitTitle 设置
     */
    public void setSubmitTitle(String submitTitle)
    {
        this.submitTitle = submitTitle;
    }

    /**
     * @return 返回 detail
     */
    public SingleAnswer getDetail()
    {
        return detail;
    }

    /**
     * @param detail 设置
     */
    public void setDetail(SingleAnswer detail)
    {
        this.detail = detail;
    }

    /**
     * @return 返回 submitType
     */
    public String getSubmitType()
    {
        return submitType;
    }

    /**
     * @param submitType 设置
     */
    public void setSubmitType(String submitType)
    {
        this.submitType = submitType;
    }

    /**
     * @return 返回 type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type 设置
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return 返回 title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title 设置
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
}
