package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;

/**
 * 普通查询条件实体类
 * 
 * @author qinye
 * 
 */
public class ConditionNormal
{
    private String mappingId; // 映射id
    private String value; // 条件的值
    private enumOperator operator; // 条件的操作符
    private enumConnector connector; // 条件连接符

    /**
     * 带参数结构体
     * 
     * @param mappingid 映射id
     * @param value 参数值
     */
    public ConditionNormal(String mappingid, String value)
    {
        this.mappingId = mappingid;
        this.value = value;
        this.operator = enumOperator.Equal;
        this.connector = enumConnector.Null;
    }

    /**
     * 带参数结构体
     * 
     * @param mappingid 映射id
     * @param value 参数值
     * @param operator 操作符
     */
    public ConditionNormal(String mappingid, String value, enumOperator operator)
    {
        this.mappingId = mappingid;
        this.value = value;
        this.operator = operator;
        this.connector = enumConnector.Null;
    }


    public String getMappingId()
    {
        return mappingId;
    }

    public String getValue()
    {
        return value;
    }

    public ConditionGroup.enumOperator getOperator()
    {
        return operator;
    }

    public void setOperator(enumOperator operator)
    {
        this.operator = operator;
    }

    public void setConnector(enumConnector connector)
    {
        this.connector = connector;
    }

    public enumConnector getConnector()
    {
        return connector;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ConditionNormal [connector=" + connector + ", mappingId=" + mappingId
                + ", operator=" + operator + ", value=" + value + "]";
    }
}
