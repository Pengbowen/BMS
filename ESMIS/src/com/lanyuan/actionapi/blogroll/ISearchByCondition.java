package com.lanyuan.actionapi.blogroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;

/**
 * 
 * TODO 友情链接condition查询
 *
 * @author zlc
 * @date 2016-8-24 上午10:21:21
 */
public class ISearchByCondition extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ServiceBlogroll blogrollService;

    public void search(){
        
        ConditionGroup cond = this.getConditionGroupObject();
        if(cond == null)
        {
            cond = new ConditionGroup();
        }
        List<SingleBlogroll> list = new ArrayList<SingleBlogroll>();
        list = blogrollService.selectByCondition(cond, null);
        List<HashMap<String , String >> datalist = new ArrayList<HashMap<String,String>>();
        if(list != null){
            for (SingleBlogroll single : list)
            {
                HashMap<String , String > map = new HashMap<String, String>();
                map.put("id", single.getId());
                map.put("linkeUrl", single.getLinkeUrl());
                map.put("linkName", single.getLinkName());
                map.put("pictureUrl", single.getPictureUrl());
                map.put("orderNumber", single.getOrderNumber()+"");
                datalist.add(map);
            }
            
        }
        this.printString(datalist, datalist.size(), "1", "");
    }
}
