/**
 * 查询条件解析器文件
 * 
 * @file QueryResolver.java
 * @package com.lanyuan.assembly.basic.sqlstring.conditionresolver
 * @description 查询条件解析器文件
 * @copyright Copyright(c)2014
 * @company lanyuan
 * @author qinye
 * @date 2014-6-21 下午15:54
 * @version V1.1
 */

package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import java.util.ArrayList;
import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.EntitySql;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumDelimiter;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;

/**
 * 查询条件解析器
 * 
 * @author qinye
 * @date 2014-6-21 下午15:54
 */
public class QueryResolver
{
    /**
     * 获取where条件语句
     * 
     * @param clsQuery 查询条件
     * @param clsOrder 排序方式
     * @param useMapping 是否使用映射关系表,true代表读映射关系表，false代表不读映射关系表，直接按照传递的条件生成sql
     * @return 成功返回where条件操作sql的EntitySql实体类，失败返回null
     */
    public EntitySql getWhereSql(ConditionGroup clsQuery, OrderGroup clsOrder)
    {

        /** 存放生成where条件：对应的参数 */
        ArrayList<Object> tempParam = new ArrayList<Object>();

        /* 查询条件对象转换成sql语句 */
        StringBuffer tempSql = new StringBuffer();
        String sOrder = resolverOrder(clsOrder);

        if (resolverQuery(tempSql, tempParam, clsQuery))
        {
            tempSql.insert(0, " where ");
        }
        tempSql.append(sOrder);
        return new EntitySql(tempSql.toString(), tempParam.toArray());
    }

    /**
     * 解析生成条件的sql语句
     * 
     * @param clsquery 查询对象
     * @return 成功返回字符串，失败返回空串
     */
    private boolean resolverQuery(StringBuffer sqlBuffer, List<Object> param,
            ConditionGroup clsQuery) throws IllegalArgumentException
    {
        boolean bReturn = false;
        boolean bReturn1 = false;
        if (clsQuery == null) return bReturn;

        int i = 0, iCount = clsQuery.getCount();
        if (iCount <= 0) return bReturn;

        for (i = 0; i < iCount; i++)
        {
            Object objData = clsQuery.getValue(i);

            // 判断数据类型
            if (objData instanceof ConditionNormal)
            {
                // 判断数据为ConditionNormal类型
                ConditionNormal clsData = (ConditionNormal) objData;
                mosaicSqlByConditionNormal(sqlBuffer, param, clsData);
                bReturn = true;
            }
            else if (objData instanceof ConditionRange)
            {
                // 判断数据为ConditionRange类型
                ConditionRange clsData = (ConditionRange) objData;
                mosaicSqlByConditionRange(sqlBuffer, param, clsData);
                bReturn = true;
            }
            else if (objData instanceof ConditionBinaryBit)
            {
                // 判断数据为ConditionBinaryBit类型
                ConditionBinaryBit clsData = (ConditionBinaryBit) objData;
                mosaicSqlByConditionBinaryBit(sqlBuffer, param, clsData);
                bReturn = true;
            }
            else if (objData instanceof ConditionGroup)
            {
                // 判断数据为ConditionGroup类型
                ConditionGroup clsData = (ConditionGroup) objData;
                bReturn1 = mosaicSqlByConditionGroup(sqlBuffer, param, clsData);
            }
            else
            {}
        }
        return bReturn || bReturn1;
    }

    /**
     * 通过ConditionNormal对象拼接SQL语句
     */
    private void mosaicSqlByConditionNormal(StringBuffer sqlBuffer, List<Object> param,
            ConditionNormal clsData)
    {

        String sFieldId = "";
            sFieldId = clsData.getMappingId();
        // 加前置连接符
        sqlBuffer.append(" ");
        sqlBuffer.append(clsData.getConnector().getValue());

        sqlBuffer.append(" ");
        sqlBuffer.append(sFieldId);
        sqlBuffer.append(" ");


        // 对特殊操作符like进行处理
        if (clsData.getOperator() == enumOperator.Like)
        {
            param.add(enumDelimiter.Like.getValue() + clsData.getValue()
                    + enumDelimiter.Like.getValue());
            sqlBuffer.append(enumOperator.Like.getValue());
            sqlBuffer.append("?");
        }
        else if (clsData.getOperator() == enumOperator.NoLike)
        {
            param.add(enumDelimiter.Like.getValue() + clsData.getValue()
                    + enumDelimiter.Like.getValue());
            sqlBuffer.append(enumOperator.NoLike.getValue());
            sqlBuffer.append("?");
        }
        else if (clsData.getOperator() == enumOperator.LeftPercent)
        {
            param.add(enumDelimiter.Like.getValue() + clsData.getValue());
            sqlBuffer.append(enumOperator.Like.getValue());
            sqlBuffer.append("?");
        }
        else if (clsData.getOperator() == enumOperator.RightPercent)
        {
            param.add(clsData.getValue() + enumDelimiter.Like.getValue());
            sqlBuffer.append(enumOperator.Like.getValue());
            sqlBuffer.append("?");
        }
        else if (clsData.getOperator() == enumOperator.NoPercent)
        {
            param.add(clsData.getValue());
            sqlBuffer.append(enumOperator.Like.getValue());
            sqlBuffer.append("?");
        }
        else if (clsData.getOperator() == enumOperator.IsNull)
        {            
            sqlBuffer.append(" ");
            sqlBuffer.append(enumOperator.IsNull.getValue());
            sqlBuffer.append(" ");
        }
        else if (clsData.getOperator() == enumOperator.IsNotNull)
        {
            sqlBuffer.append(" ");
            sqlBuffer.append(enumOperator.IsNotNull.getValue());
            sqlBuffer.append(" ");
        }        
        else
        {
            sqlBuffer.append(clsData.getOperator().getValue());
            param.add(clsData.getValue());
            sqlBuffer.append("?");
        }              
    }

    /**
     * 通过ConditionRange对象拼接SQL语句
     */
    private void mosaicSqlByConditionRange(StringBuffer sqlBuffer, List<Object> param,
            ConditionRange clsData)
    {

        String sFieldId = "";
        boolean hasMinValue = !clsData.getMinValue().equals("");
        boolean hasMaxValue = !clsData.getMaxValue().equals("");

        if (!hasMinValue && !hasMaxValue) return;

            sFieldId = clsData.getMappingId();

        // 加前置连接符
        sqlBuffer.append(" ");
        sqlBuffer.append(clsData.getConnector().getValue());

        sqlBuffer.append(" ");
        sqlBuffer.append(enumDelimiter.LeftBracket.getValue());
        sqlBuffer.append(" ");

        if (hasMinValue)
        {
            sqlBuffer.append(sFieldId);
            sqlBuffer.append(">=?");
            sqlBuffer.append(" ");
        }

        if (hasMinValue && hasMaxValue)
        {
            sqlBuffer.append(enumConnector.And.getValue());
            sqlBuffer.append(" ");
        }

        if (hasMaxValue)
        {
            sqlBuffer.append(sFieldId);
            sqlBuffer.append("<=?");
            sqlBuffer.append(" ");
        }
        sqlBuffer.append(enumDelimiter.RightBracket.getValue());


        // 将最大值，最小值放入变量“param”中
        if (hasMinValue) param.add(clsData.getMinValue());
        if (hasMaxValue) param.add(clsData.getMaxValue());
    }

    /**
     * 通过ConditionRange对象拼接SQL语句
     */
    private void mosaicSqlByConditionBinaryBit(StringBuffer sqlBuffer, List<Object> param,
            ConditionBinaryBit clsData)
    {
        String sFieldId = "";
            sFieldId = clsData.getMappingId();
        // 加前置连接符
        sqlBuffer.append(" ");
        sqlBuffer.append(clsData.getConnector().getValue());

        sqlBuffer.append(" ");
        sqlBuffer.append(sFieldId);
        sqlBuffer.append(" ");
        sqlBuffer.append(enumOperator.BitAnd.getValue());
        sqlBuffer.append(" ");
        sqlBuffer.append("?");
        sqlBuffer.append(" ");
        sqlBuffer.append(enumOperator.Equal.getValue());
        sqlBuffer.append(" ");
        sqlBuffer.append("?");

        // 增加对应的值
        param.add(clsData.getValue());
        param.add(clsData.getValue());
    }

    /**
     * 通过ConditionGroup对象拼接SQL语句
     */
    private boolean mosaicSqlByConditionGroup(StringBuffer sqlBuffer, List<Object> param,
            ConditionGroup clsData)
    {
        String leftBracket = enumDelimiter.LeftBracket.getValue();
        String rightBracket = enumDelimiter.RightBracket.getValue();

        if (0 == clsData.getCount()) return false;

        int bufferLength = sqlBuffer.length();
        boolean bReturn = resolverQuery(sqlBuffer, param, clsData);
        if (bReturn)
        {
            // 加前置连接符 和 (
            sqlBuffer.insert(bufferLength, " " + clsData.getConnector().getValue()
                    + leftBracket + " ");

            sqlBuffer.append(" " + rightBracket);
        }
        return bReturn;
    }

    /**
     * 解析生成排序的sql语句
     * 
     * @param clsOrder 排序对象
     * @param useMapping 使用映射关系表
     * @return 成功返回字符串，失败返回空串
     */
    private String resolverOrder(OrderGroup clsOrder)
    {
        StringBuffer sOrder = new StringBuffer();
        String sTableName = "", sOrderStyle = "";
        String sFieldId = "", sMappingId = "";
        if (clsOrder == null) return "";

        List<String> listOrder = clsOrder.getOrderList();
        if (listOrder.isEmpty()) return "";
        if (listOrder.size() <= 0) return "";

        int iPos = 0;
        String key = "", delimiterChar = enumDelimiter.TableField.getValue();
        
        for (int index = 0 ;index<listOrder.size();index++)
        {
            /* 判断key中是否存在“.”，如果存在则是“集合.映射id”否则是“映射id” */
            sTableName = "";
            key = listOrder.get(index);
            iPos = key.lastIndexOf(OrderGroup.getOrderSplitChar());
            if (key.lastIndexOf(delimiterChar) >= 0)
            {
                sMappingId = key.substring(key.lastIndexOf(delimiterChar) + 1 , iPos);
                sTableName = key.substring(0,key.lastIndexOf(delimiterChar) ); 
                sOrderStyle = key.substring(iPos + OrderGroup.getOrderSplitChar().length());
            }else{
                sMappingId = key.substring(0 , iPos);
                sOrderStyle = key.substring(iPos + OrderGroup.getOrderSplitChar().length());                
            }

                sFieldId = sMappingId;

            if (!"".equals(sFieldId.trim()))
            {
                sOrder.append(",");
                if(!sTableName.trim().isEmpty())
                {
                    sOrder.append(sTableName + ".");    
                }
                sOrder.append(sFieldId);
                sOrder.append(" ");
                sOrder.append(sOrderStyle);
//                sOrder.append(" nulls last");
            }
        }
        
        if (!"".equals(sOrder))
        {
            return " order by " + sOrder.toString().substring(1);
        }
        return "";
    }
    
   /* public static void main(String[] args)
    {
        ConditionGroup cond  =new ConditionGroup();
        cond.addWithAnd(new ConditionNormal("id ", "1"));
        OrderGroup order =new OrderGroup();
        order.Add("id", true);
        order.Add("id2", true);        
        System.out.println(new QueryResolver().getWhereSql(cond,order));
    }*/
}
