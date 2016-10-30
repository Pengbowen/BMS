package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;

public class ConditionBinaryBit
{
    private String mappingId; // 映射id
    private String value; // 条件的值

    private enumConnector connector; // 条件连接符

    public String getMappingId()
    {
        return mappingId;
    }

    public String getValue()
    {
        return value;
    }

    public enumConnector getConnector()
    {
        return connector;
    }

    public void setConnector(enumConnector connector)
    {
        this.connector = connector;
    }

    /**
     * 构造函数
     * 
     * @param mappingId 映射id
     * @param value 操作值
     */
    public ConditionBinaryBit(String mappingId, String value)
    {
        this.mappingId = mappingId;
        this.value = value;
        this.connector = enumConnector.Null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ConditionBinaryBit [connector=" + connector + ", mappingId=" + mappingId
                + ", value=" + value + "]";
    }
}
