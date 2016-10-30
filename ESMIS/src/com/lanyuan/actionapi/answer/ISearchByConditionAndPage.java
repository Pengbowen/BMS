package com.lanyuan.actionapi.answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.answer.ServiceAnswer;
import com.lanyuan.assembly.answer.SingleAnswer;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

/**
 * 
 * TODO 意见建议condition分页查询
 *
 * @author zlc
 * @date 2016-8-27 上午10:21:23
 */
public class ISearchByConditionAndPage extends ResultSearchAction
{

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceAnswer answerService;
        
    private String type;
    
    public void search(){
            
        ConditionGroup cond = this.getConditionGroupObject();
        OrderGroup order = this.getOrderGroupObject();
        if(cond == null)
        {
            cond = new ConditionGroup();
        }
        if(order == null){
            order = new OrderGroup();
        }
        if(type != null && type.trim().length() != 0){
            cond.addWithAnd(new ConditionNormal(ServiceAnswer.MappingList.submitType.name(), type));
        }
        order.Add(ServiceAnswer.MappingList.isreceive.name(), true);
        int count = answerService.getTotalCount(cond);
        List<SingleAnswer> list = new ArrayList<SingleAnswer>();
        list = answerService.selectByConditionAndPage(getCurrentPage(),getLinesOfPage(), cond, order);
        List<HashMap<String , String >> datalist = new ArrayList<HashMap<String,String>>();
        if(list != null){
            for (SingleAnswer single : list)
            {
                HashMap<String , String > map = new HashMap<String, String>();
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
                datalist.add(map);
            }
            
        }
        this.printString(datalist, count, "1", "");
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

}
