package com.lanyuan.actionapi.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNotice;
/**
 * 
 * TODO Condition 查询通知公告
 *
 * @author zlc
 * @date 2016-8-31 上午11:00:18
 */
public class ISearchByCondition extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceNotice noticeService;
    
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
        order.Add(ServiceNotice.MappingList.publishTime.name(), false);
        List<SingleNotice> list = new ArrayList<SingleNotice>();
        list = noticeService.selectList(cond, order);
        List<HashMap<String , String >> datalist = new ArrayList<HashMap<String,String>>();
        if(list != null){
            for (SingleNotice single : list)
            {
                HashMap<String , String > map = new HashMap<String, String>();
                map.put("id", single.getId()+"");
                map.put("noticeTitle", single.getNoticeTitle());
                map.put("noticeContent", single.getNoticeContent());
                map.put("showNum", single.getShowNum()+"");
                map.put("deadline", single.getDeadline());
                map.put("publisher", single.getPublisher());
                map.put("publisherrName", single.getPublisherrName());
                map.put("publishTime", single.getPublishTime());
                map.put("publishIP", single.getPublishIP());
                map.put("modifyer", single.getModifyer());
                map.put("modifyerName", single.getModifyerName());
                map.put("modifyTime", single.getModifyTime());
                map.put("modifyIP", single.getModifyTime());
                datalist.add(map);
            }
        }
        this.printString(datalist, datalist.size(), "1", "");
    }
}
