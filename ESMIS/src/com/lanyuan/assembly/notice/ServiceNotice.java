package com.lanyuan.assembly.notice;

import java.util.List;

import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

public interface ServiceNotice
{
    /**
     * 添加通知公告
     * 
     * @param single
     * @param operator
     * @return 操作数据库行数
     */
    public int add(SingleNoticeToOperate single,OperatorInfo operator);
    
    /**
     * 修改通知公告
     * 
     * @param single
     * @param 操作人 operator 
     * @param id
     * @return 操作数据库行数
     */
    public int update(SingleNoticeToOperate single,OperatorInfo operator,Integer id);
    
    public int deleteById(String id);
    
    public int getTotalCount(ConditionGroup cond);
    
    public SingleNotice selectById(String id);
    
    public List<SingleNotice> selectList(ConditionGroup cond, OrderGroup order);
    
    public List<SingleNotice> selectPageList(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order);
    
    public enum MappingList{
        /*
         * id
         */
        id("id"),
        /*
         * 通知公告标题
         */
        noticeTitle("noticeTitle"),
        /*
         * 通知公告内容
         */
        noticeContent("noticeContent"),
        /*
         * 显示顺序
         */
        showNum("showNum"),
        /*
         * 截止时间
         */
        deadline("deadline"),
        /*
         * 发布人
         */
        publisher("publisher"),
        /*
         * 发布姓名
         */
        publisherrName("publisherrName"),
        /*
         * 发布时间
         */
        publishTime("publishTime"),
        /*
         * 发布IP
         */
        publishIP("publishIP"),
        /*
         * 最后修改人
         */
        modifyer("modifyer"),
        /*
         * 最后修改人姓名
         */
        modifyerName("modifyerName"),
        /*
         * 最后修改时间
         */
        modifyTime("modifyTime"),
        /*
         * 最后修改IP
         */
        modifyIP("modifyIP");
        
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
