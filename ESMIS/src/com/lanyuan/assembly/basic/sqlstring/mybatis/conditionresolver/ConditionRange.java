package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;

/**
 * 范围查询条件实体类
 * 
 * @author qinye
 * 
 */
public class ConditionRange
{
    private String mappingId;
    private String maxValue;
    private String minValue;
    private enumConnector connector; // 连接符

    /**
     * 带参数构造函数
     * 
     * @param mappingId 映射id
     * @param maxValue 最大值
     * @param minValue 最小值
     */
    public ConditionRange(String mappingId, String maxValue, String minValue)
    {
        this.mappingId = mappingId;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.connector = enumConnector.Null;
    }

    /**
     * @return 返回映射id
     */
    public String getMappingId()
    {
        return mappingId;
    }

    /**
     * @return 返回条件的最大值
     */
    public String getMaxValue()
    {
        return (null == maxValue) ? "" : maxValue;
    }

    /**
     * @return 返回条件的最小值
     */
    public String getMinValue()
    {
        return (null == minValue) ? "" : minValue;
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
        return "ConditionRange [connector=" + connector + ", mappingId=" + mappingId
                + ", maxValue=" + maxValue + ", minValue=" + minValue + "]";
    }
}
