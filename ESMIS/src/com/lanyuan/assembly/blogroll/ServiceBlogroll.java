package com.lanyuan.assembly.blogroll;


import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

public interface ServiceBlogroll
{
    public int add(SingleBlogroll single);
    
    public int update(SingleBlogroll single,String id);
    
    public int delete(String id);
    
    public int getTotalCount(ConditionGroup cond);
    
    public SingleBlogroll selectById(String id);
    
    public List<SingleBlogroll> selectByCondition(ConditionGroup cond, OrderGroup order);
    
    public List<SingleBlogroll> selectByConditionAndPage(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order);
    
    public enum MappingList{
        /**
         * id
         */
        id("lysoft_blogroll.id"),
        /**
         * 文字标题
         */
        linkName("lysoft_blogroll.linkName"),
        /**
         * 链接地址
         */
        linkeUrl("lysoft_blogroll.linkeUrl"),
        /**
         * 图片地址
         */
        pictureUrl("lysoft_blogroll.pictureUrl"),
        /**
         * 显示顺序
         */
        orderNumber("lysoft_blogroll.orderNumber");
        
        private String value;
        
        MappingList(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return this.value;
        }
    }
}
