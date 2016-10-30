
package com.lanyuan.assembly.basic.sqlstring.mybatis;



/**
 * 用于存放解析器的解析结果，有sql语句和对应参数
 * 
 * @author qinye
 * 
 */
public class EntitySql
{
    private String sql;
    private Object[] parameter;

    public String getSql()
    {
        return sql;
    }

    public Object[] getParameter()
    {
        return parameter;
    }

    public EntitySql(String sql, Object[] param)
    {
        this.sql = sql;
        this.parameter = param;
    }

    @Override
    public String toString()
    {
        StringBuffer sb=new StringBuffer();
        String[] sqlArr= this.sql.split("[?]");
        for (int i = 0; i < sqlArr.length; i++)
        {
            sb.append(sqlArr[i]);
            if(parameter.length>i)
            {
                sb.append(" '"+parameter[i]+"'");
            }
        }
        return sb.toString();
    }
    
}
