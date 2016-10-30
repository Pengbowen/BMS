
package com.lanyuan.assembly.answer;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionBinaryBit;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionRange;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.QueryResolver;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;
import com.lanyuan.assembly.common.Page;

@Service("answerService")
public class ServiceAnswerImpl implements ServiceAnswer
{
    @Autowired
    private DaoAnswer dao;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    
    public int addAnswer(SingleAnswerToAdd single)
    {   
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToAdd(single);
        en.setSubmitTime(df.format(new Date()));
        en.setSubmitType(0);
        return dao.insert(en);
    }
    
    public int addCriteria(SingleAnswerToAdd single, String filePath)
    {   
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToAdd(single);
        en.setSubmitTime(df.format(new Date()));
        en.setSubmitType(1);
        en.setFilePath(filePath);
        return dao.insert(en);
    }
    
    public int addAskForCriteria(SingleAnswerToAdd single)
    {   
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToAdd(single);
        en.setSubmitTime(df.format(new Date()));
        en.setSubmitType(2);
        return dao.insert(en);
    }
    
    public int replyAnswer(SingleAnswerToUpdata single,Integer id)
    {
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToUpdata(single);
        en.setId(id);
        en.setReceiveTime(df.format(new Date()));
        return dao.update(en);
    }
    
    /*public int replyShared(SingleAnswerToUpdata single,Integer id)
    {
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToUpdata(single);
        en.setId(id);
        en.setReceiveTime(df.format(new Date()));
        return dao.update(en);
    }
    
    public int replyAsked(SingleAnswerToUpdata single,Integer id)
    {
        EntityAnswer en = new EntityAnswer();
        en = singleToEntityToUpdata(single);
        en.setId(id);
        en.setReceiveTime(df.format(new Date()));
        return dao.update(en);
    }*/
    
    public int delete(String id)
    {
        return dao.deleteById(id);
    }
    
    public int getTotalCount(ConditionGroup cond)
    {
        cond=this.exchangeMappingToField(cond);
        String sql =new QueryResolver().getWhereSql(cond,null).toString();
        return dao.getTotalCount(sql);
    }
    
    public SingleAnswer selectById(String id)
    {
        EntityAnswer en = new EntityAnswer();
        SingleAnswer single = new SingleAnswer();
        en = dao.selectById(id);
        single = entityToSingle(en);
        return single;
    }

    public List<SingleAnswer> selectByCondition(ConditionGroup cond, OrderGroup order)
    {   
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        List<EntityAnswer> list = new ArrayList<EntityAnswer>();
        List<SingleAnswer> datelist = new ArrayList<SingleAnswer>();
        list = dao.selectList(sql);
        if(list != null && !list.isEmpty()){
            for (EntityAnswer entityAnswer : list)
            {
                SingleAnswer single = new SingleAnswer();
                single = entityToSingle(entityAnswer);
                datelist.add(single);
            }
        } else {
            datelist = null;
        }
        return datelist;
    }
    
    public List<SingleAnswer> selectByConditionAndPage(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order)
    {
        List<EntityAnswer> list = new ArrayList<EntityAnswer>();
        List<SingleAnswer> datelist = new ArrayList<SingleAnswer>();
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page page =new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setSql(sql);
        list = dao.selectPageList(page);
        if(list != null && !list.isEmpty()){
            for (EntityAnswer entityAnswer : list)
            {
                SingleAnswer single = new SingleAnswer();
                single = entityToSingle(entityAnswer);
                datelist.add(single);
            }
        }
        return datelist;
    }
    
    private SingleAnswer entityToSingle(EntityAnswer cls)
    {
        SingleAnswer single = new SingleAnswer();
        single.setId(cls.getId());
        single.setSubmitType(cls.getSubmitType());
        single.setSubmitTitle(cls.getSubmitTitle());
        single.setSubmitContent(cls.getSubmitContent());
        single.setFilePath(cls.getFilePath());
        single.setSubmitID(cls.getSubmitID());
        single.setSubmitName(cls.getSubmitName());
        single.setSubmitTime(cls.getSubmitTime());
        single.setSubmitIP(cls.getSubmitIP());
        single.setSubmitorMobile(cls.getSubmitorMobile());
        single.setSubmitorEmail(cls.getSubmitorEmail());
        single.setIsreceive(cls.getIsreceive());
        single.setReceiveID(cls.getReceiveID());
        single.setReceiveName(cls.getReceiveName());
        single.setReceiveTime(cls.getReceiveTime());
        single.setReceiveIP(cls.getReceiveIP());
        single.setReceiveContent(cls.getReceiveContent());
        return single;
    }
    
    /**
     * 新增用
     * 
     * @param single
     * @return EntityAnswer
     */
    private EntityAnswer singleToEntityToAdd(SingleAnswerToAdd single)
    {
        EntityAnswer cls = new EntityAnswer();
        cls.setSubmitTitle(single.getSubmitTitle());
        cls.setSubmitContent(single.getSubmitContent());
        cls.setSubmitID(single.getSubmitID());
        cls.setSubmitName(single.getSubmitName());
        cls.setSubmitIP(single.getSubmitIP());
        cls.setSubmitorMobile(single.getSubmitorMobile());
        cls.setSubmitorEmail(single.getSubmitorEmail());
        cls.setIsreceive(single.getIsreceive());
        return cls;
    }
    
    /**
     * 修改用
     * 
     * @param single
     * @return EntityAnswer
     */
    private EntityAnswer singleToEntityToUpdata(SingleAnswerToUpdata single)
    {
        EntityAnswer cls = new EntityAnswer();
        cls.setIsreceive(single.getIsreceive());
        cls.setReceiveName(single.getReceiveName());
        cls.setReceiveIP(single.getReceiveIP());
        cls.setReceiveContent(single.getReceiveContent());
        cls.setReceiveID(single.getReceiveID());
        return cls;
    }
    
    public String getMappingValueById(String mappingId)
    {
        Class<?> inter[]=null;//声明一个对象数组
        inter=this.getClass().getInterfaces();//获取类实现的所有接口
        Class<?> temp = null;
        Method tempMethod;
        Object value;
        try
        {
            temp = Class.forName(inter[0].getName()+"$MappingList");
            tempMethod = temp.getDeclaredMethod("valueOf", String.class);
            value = tempMethod.invoke(temp,mappingId);
            tempMethod = value.getClass().getDeclaredMethod("getValue");
            return tempMethod.invoke(value).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    };
    
    /**
     * 将Mapping的ConditionGroup转成Field的ConditionGroup对象
     * 
     * @param clsQuery 查询条件
     * @return 返回Field的ConditionGroup对象
     */
    public ConditionGroup exchangeMappingToField(ConditionGroup clsQuery)
    {
        
        if (clsQuery == null)
        {
            return null;
        }

        enumConnector temp = null;

        ConditionGroup tempQuery = new ConditionGroup();
        for (int i = 0; i < clsQuery.getCount(); i++)
        {
            Object objData = clsQuery.getValue(i);

            // 判断数据类型
            if (objData instanceof ConditionNormal)
            {
                // 判断数据为ConditionNormal类型
                ConditionNormal clsData = (ConditionNormal) objData;
                ConditionNormal tempData = new ConditionNormal(
                        this.getMappingValueById(clsData.getMappingId()),
                        clsData.getValue());
                
                tempData.setOperator(clsData.getOperator());
                temp = clsData.getConnector();
                
                if (temp.getValue().equals(enumConnector.And.getValue()))
                {
                    tempQuery.addWithAnd(tempData);
                }
                else if (temp.getValue().equals(enumConnector.Or.getValue()))
                {
                    tempQuery.addWithOr(tempData);
                }
                else
                {
                    tempQuery.addWithAnd(tempData);
                }
            }
            else if (objData instanceof ConditionRange)
            {
                // 判断数据为ConditionRange类型
                ConditionRange clsData = (ConditionRange) objData;
                ConditionRange tempData = new ConditionRange(
                        this.getMappingValueById(clsData.getMappingId()),
                        clsData.getMaxValue(), clsData.getMinValue());
                

                temp = clsData.getConnector();
                if (temp.getValue().equals(enumConnector.And.getValue()))
                {
                    tempQuery.addWithAnd(tempData);
                }
                else if (temp.getValue().equals(enumConnector.Or.getValue()))
                {
                    tempQuery.addWithOr(tempData);
                }
                else
                {
                    tempQuery.addWithAnd(tempData);
                }

            }
            else if (objData instanceof ConditionBinaryBit)
            {
                // 判断数据为ConditionBinaryBit类型
                ConditionBinaryBit clsData = (ConditionBinaryBit) objData;
                ConditionBinaryBit tempData = new ConditionBinaryBit(
                        this.getMappingValueById(clsData.getMappingId()),
                        clsData.getValue());

                temp = clsData.getConnector();
                if (temp.getValue().equals(enumConnector.And.getValue()))
                {
                    tempQuery.addWithAnd(tempData);
                }
                else if (temp.getValue().equals(enumConnector.Or.getValue()))
                {
                    tempQuery.addWithOr(tempData);
                }
                else
                {
                    tempQuery.addWithAnd(tempData);
                }
            }
            else if (objData instanceof ConditionGroup)
            {
                // 判断数据为ConditionGroup类型
                ConditionGroup clsData = (ConditionGroup) objData;

                temp = clsData.getConnector();
                if (temp.getValue().equals(enumConnector.And.getValue()))
                {
                    tempQuery.addWithAnd(exchangeMappingToField(clsData));
                }
                else if (temp.getValue().equals(enumConnector.Or.getValue()))
                {
                    tempQuery.addWithOr(exchangeMappingToField(clsData));
                }
                else
                {
                    tempQuery.addWithAnd(exchangeMappingToField(clsData));
                }
            }
            else
            {}
        }
        return tempQuery;
    }
    
    /**
     * 将Mapping的OrderGroup转换为Field的OrderGroup
     * 
     * @param clsOrder 带Mapping的OrderGroup对象 
     * @return 带Field的OrderGroup对象
     */
    public OrderGroup exchangeMappingToField(OrderGroup clsOrder)
    {
        if(null == clsOrder) return null;
        
        OrderGroup tempOrder = new OrderGroup();
        List<String> orderList = clsOrder.getOrderList();
        for(int i=0;i<orderList.size();i++)
        {
            String temp = orderList.get(i);
            String sMappingId = temp.substring(0,temp.indexOf(OrderGroup.getOrderSplitChar()));
            sMappingId = this.getMappingValueById(sMappingId);
            String sOrder = temp.substring(temp.indexOf(OrderGroup.getOrderSplitChar())+2);
            
            tempOrder.Add(sMappingId, sOrder.equals(ConditionGroup.enumOrder.Asc.getValue()));
        }
        return tempOrder;
    } 
}
