
package com.lanyuan.assembly.common;


/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page
{

    private int pageNo;
    private int pageSize;
    private String sql;
    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getSql()
    {
        return sql;
    }

    public void setSql(String sql)
    {
        this.sql = sql;
    }

}
