package com.lanyuan.assembly.sst;

import java.util.List;

/**
 * 体系表框图数据结构
 * @author yt
 * @date 2016-9-12 上午11:06:25
 */
public class SSTNode
{
    /**
     * 父结点ID
     */
    private String parent;
    /**
     * 孩子结点，存储项目编号ID
     */
    private List<String> childNodeList;

    public String getParent()
    {
        return parent;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public List<String> getChildNodeList()
    {
        return childNodeList;
    }

    public void setChildNodeList(List<String> childNodeList)
    {
        this.childNodeList = childNodeList;
    }
}
