
package com.lanyuan.assembly.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionBinaryBit;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionRange;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.QueryResolver;

public abstract class ServersManager<K,V, DAO extends DaoManager<V>> implements Services<K>
{

    protected DAO dao;

    public abstract void setDao(DAO dao);
    public abstract K getNewSingleObj();
    public abstract V getNewEntityObj();
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
    }
    /**
     * 添加
     * 
     * @param e
     * @return
     */
    public int insert(K k)
    {
        if (k == null) { throw new NullPointerException(); }
        return dao.insert(singleToEntity(k));
    }

    /**
     * 删除
     * 
     * @param e
     * @return
     */
    public int deleteById(String id)
    {
        return dao.deleteById(id);
    }

    /**
     * 修改
     * 
     * @param e
     * @return
     */
    public int update(K k)
    {
        if (k == null) { throw new NullPointerException(); }
        return dao.update(singleToEntity(k));
    }

    public K selectById(String id)
    {
        return entityToSingle(dao.selectById(id));
    }

    /**
     * 修改
     * 
     * @param e
     * @return
     */
    public int getTotalCount(ConditionGroup cond)
    {
        cond=this.exchangeMappingToField(cond);
        String sql =new QueryResolver().getWhereSql(cond,null).toString();
        return dao.getTotalCount(sql);
    }

    public List<K> selectList(ConditionGroup cond, OrderGroup order)
    {
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        List<V> list =dao.selectList(sql);
        if(list==null ||list.isEmpty()) return null;
        List<K> rtnlist=new ArrayList<K>();
        for (V v : list)
        {
            rtnlist.add(this.entityToSingle(v));
        }
        return rtnlist;
    }

    /**
     * 分页查询
     * 
     * @param e
     * @return
     */
    public List<K> selectPageList(int pageNo, int pageSize,ConditionGroup cond, OrderGroup order)
    {
        cond=this.exchangeMappingToField(cond);
        order =this.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page page =new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setSql(sql);
        List<V> list =dao.selectPageList(page);
        if(list==null ||list.isEmpty()) return null;
        List<K> rtnlist=new ArrayList<K>();
        for (V v : list)
        {
            rtnlist.add(this.entityToSingle(v));
        }
        return rtnlist;
    }
    
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
    
    public K entityToSingle(V v)
    {
    	if(v==null)  return null;
    	K k =this.getNewSingleObj();
    	BeanUtils.copyProperties(v, k);
		return k;
    }    
    public V singleToEntity(K k)
    {
    	if(k==null)  return null;
    	V  v=this.getNewEntityObj();
    	BeanUtils.copyProperties(k,v);
		return v;
    }
}
