package com.lanyuan.assembly.answer;

import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

public interface ServiceAnswer
{
    /**
     * 提交意见
     * 
     * @param single
     * @return 1 成功   0 失败
     */
    public int addAnswer(SingleAnswerToAdd single);
    
    /**
     * 分享标准
     * 
     * @param single
     * @return 1 成功   0 失败
     */
    public int addCriteria(SingleAnswerToAdd single,String filePath);
    
    /**
     * 我要标准
     * 
     * @param single
     * @return 1 成功   0 失败
     */
    public int addAskForCriteria(SingleAnswerToAdd single);
    
    /**
     * 反馈意见建议
     * 分享标准回复
     * 索要标准回复
     * 
     * @param single
     * @param id
     * @return 1 成功   0 失败
     */
    public int replyAnswer(SingleAnswerToUpdata single,Integer id);
    
   /* *//**
     * 
     * 
     * @param single
     * @param id
     * @return 1 成功   0 失败
     *//*
    public int replyShared(SingleAnswerToUpdata single,Integer id);
    
    *//**
     * 
     * 
     * @param single
     * @param id
     * @return 1 成功   0 失败
     *//*
    public int replyAsked(SingleAnswerToUpdata single,Integer id);
    */
    /**
     * 删除
     * 
     * @param id
     * @return 1 成功   0 失败
     */
    public int delete(String id);
    
    public int getTotalCount(ConditionGroup cond);
    
    public SingleAnswer selectById(String id);
    
    public List<SingleAnswer> selectByCondition(ConditionGroup cond, OrderGroup order);
    
    public List<SingleAnswer> selectByConditionAndPage(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order);
    
    
    public enum MappingList {
        /**
         * 编号
         */
        id("lysoft_answer.id"),
        /**
         * 意见类型
         */
        submitType("lysoft_answer.submitType"),
        /**
         * 提交标题
         */
        submitTitle("lysoft_answer.submitTitle"),
        /**
         * 提交内容
         */
        submitContent("lysoft_answer.submitContent"),
        /**
         * 文件存放位置
         */
        filePath("lysoft_answer.filePath"),
        /**
         * 提交人ID
         */
        submitID("lysoft_answer.submitID"),
        /**
         * 提交人姓名
         */
        submitName("lysoft_answer.submitName"),
        /**
         * 提交时间
         */
        submitTime("lysoft_answer.submitTime"),
        /**
         * 提交IP
         */
        submitIP("lysoft_answer.submitIP"),
        /**
         * 提交人手机号
         */
        submitorMobile("lysoft_answer.submitorMobile"),
        /**
         * 提交人邮箱
         */
        submitorEmail("lysoft_answer.submitorEmail"),
        /**
         * 是否回复
         */
        isreceive("lysoft_answer.isreceive"),
        /**
         * 回复人姓名
         */
        receiveName("lysoft_answer.receiveName"),
        /**
         * 回复时间
         */
        receiveTime("lysoft_answer.receiveTime"),
        /**
         * 回复人IP
         */
        receiveIP("lysoft_answer.receiveIP"),
        /**
         * 回复内容
         */
        receiveContent("lysoft_answer.receiveContent");

        private String value;
        MappingList(String value) {
           this.value = value;
        }
        public String getValue() {
           return this.value;
        }
     }
}
