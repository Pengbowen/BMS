package com.lanyuan.actionapi.notice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNotice;
import com.opensymphony.xwork2.Action;

/**
 * 
 * TODO 根据ID查询通知公告详情
 *
 * @author zlc
 * @date 2016-8-31 上午10:59:05
 */
public class ISearchById extends ResultDetailAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceNotice noticeService;
    
    private String id;
    
    public String search(){
        
        if(id == null || id.isEmpty()){
            this.printString(null, "2", "ID为空！");
            return Action.ERROR;
        }
        SingleNotice single = new SingleNotice();
        single = noticeService.selectById(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(single == null){
            this.printString(null, "2", "数据不存在！");
            return Action.ERROR;
        }else {
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
        }
        this.printString(map, "1", "");
        
        return Action.SUCCESS;
    }
        
}
