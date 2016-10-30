package com.lanyuan.assembly.blogroll;


import java.lang.reflect.Method;
import java.util.ArrayList;
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

@Service("blogrollService")
public class ServiceBlogrollImpl implements ServiceBlogroll
{

    @Autowired
    private DaoBlogroll dao;
    
    /**
     * 添加友情链接
     * 
     * @param en
     * @return 操作数据库行数
     */
    public int add(SingleBlogroll single)
    {   
        EntityBlogroll en = new EntityBlogroll();
        en = SingleToEntity(single);
        return dao.insert(en);
    }
    
    /**
     * 修改友情链接
     * 
     * @param en
     * @return 操作数据库行数
     */
    public int update(SingleBlogroll single,String id)
    {
        EntityBlogroll en = new EntityBlogroll();
        en = SingleToEntity(single);
        en.setId(id);
        return dao.update(en);
    }
    
    /**
     * 删除友情链接
     * 
     * @param id
     * @return 操作数据库行数
     */
    public int delete(String id)
    {
        return dao.deleteById(id);
    }
    
    /**
     * 获取总数
     * 
     * @param condition
     * @return 总数
     */
    public int getTotalCount(ConditionGroup cond)
    {
        cond=this.exchangeMappingToField(cond);
        String sql =new QueryResolver().getWhereSql(cond,null).toString();
        return dao.getTotalCount(sql);
    }
    
    /**
     * 根据Id查询
     * 
     * @param id
     * @return SingleBlogroll
     */
    public SingleBlogroll selectById(String id)
    {
        EntityBlogroll en = new EntityBlogroll();
        SingleBlogroll single = new SingleBlogroll();
        en = dao.selectById(id);
        single = entityToSingle(en);
        return single;
    }

    /**
     * 按照Condition查询
     * 
     * @param cond
     * @return List<SingleBlogroll>
     */
    public List<SingleBlogroll> selectByCondition(ConditionGroup cond, OrderGroup order)
    {   
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        if(cond == null){
            cond = new ConditionGroup();
        }
        if(order == null){
            order = new OrderGroup();
        }
        order.Add(ServiceBlogroll.MappingList.orderNumber.name(), true);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        List<EntityBlogroll> list = new ArrayList<EntityBlogroll>();
        List<SingleBlogroll> datelist = new ArrayList<SingleBlogroll>();
        list = dao.selectList(sql);
        if(list != null && !list.isEmpty()){
            for (EntityBlogroll entityBlogroll : list)
            {
                SingleBlogroll single = new SingleBlogroll();
                single = entityToSingle(entityBlogroll);
                datelist.add(single);
            }
        } else {
            datelist = null;
        }
        return datelist;
    }
    
    /**
     * 按照Condition分页查询
     * 
     * @param cond
     * @return List<SingleBlogrollToAll>
     */
    public List<SingleBlogroll> selectByConditionAndPage(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order)
    {
        List<EntityBlogroll> list = new ArrayList<EntityBlogroll>();
        List<SingleBlogroll> datelist = new ArrayList<SingleBlogroll>();
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        if(cond == null){
            cond = new ConditionGroup();
        }
        if(order == null){
            order = new OrderGroup();
        }
        order.Add(ServiceBlogroll.MappingList.orderNumber.name(), true);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page page =new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setSql(sql);
        list = dao.selectPageList(page);
        if(list != null && !list.isEmpty()){
            for (EntityBlogroll entityBlogroll : list)
            {
                SingleBlogroll single = new SingleBlogroll();
                single = entityToSingle(entityBlogroll);
                datelist.add(single);
            }
        }
        return datelist;
    }
    
    /**
     * EntityBlogroll转SingleBlogroll
     * 
     * @param en
     * @return SingleBlogroll
     */
    private SingleBlogroll entityToSingle(EntityBlogroll en)
    {
        SingleBlogroll single = new SingleBlogroll();
        single.setLinkeUrl(en.getLinkeUrl());
        single.setLinkName(en.getLinkName());
        single.setOrderNumber(en.getOrderNumber());
        single.setPictureUrl(en.getPictureUrl());
        single.setId(en.getId());
        return single;
    }
    
    
    /**
     * toAdd  没有ID
     * 
     * @param
     * @return
     */
    private EntityBlogroll SingleToEntity(SingleBlogroll single)
    {
        EntityBlogroll en = new EntityBlogroll();
        en.setLinkeUrl(single.getLinkeUrl());
        en.setLinkName(single.getLinkName());
        en.setOrderNumber(single.getOrderNumber());
        en.setPictureUrl(single.getPictureUrl());
        return en;
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
