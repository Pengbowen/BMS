package com.lanyuan.actionapi.answer;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswer;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 跟据ID查询意见建议详情
 *
 * @author zlc
 * @date 2016-8-27 上午10:21:42
 */
public class ISearchById extends ResultDetailAction
{

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceAnswer answerService;
    
    private String id;
    
    public String search(){
        
        if(id == null || id.isEmpty()){
            this.printString(null, "2", "ID为空！");
            return Action.ERROR;
        }
        SingleAnswer single = new SingleAnswer();
        single = answerService.selectById(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(single == null){
            this.printString(null, "2", "数据不存在！");
            return Action.ERROR;
        }else {
            map.put("id", single.getId()+"");
            map.put("submitType", single.getSubmitType()+"");
            map.put("submitTitle", single.getSubmitTitle());
            map.put("submitContent", single.getSubmitContent());
            map.put("filePath", single.getFilePath());
            map.put("submitID", single.getSubmitID());
            map.put("submitName", single.getSubmitName());
            map.put("submitTime", single.getSubmitTime());
            map.put("submitIP", single.getSubmitIP());
            map.put("submitorMobile", single.getSubmitorMobile());
            map.put("submitorEmail", single.getSubmitorEmail());
            map.put("isreceive", single.getIsreceive()+"");
            map.put("receiveID", single.getReceiveID());
            map.put("receiveName", single.getReceiveName());
            map.put("receiveTime", single.getReceiveTime());
            map.put("receiveIP", single.getReceiveIP());
            map.put("receiveContent", single.getReceiveContent());
        }
        this.printString(map, "1", "");
        
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
    

}
