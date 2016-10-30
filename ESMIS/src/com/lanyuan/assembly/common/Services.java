
package com.lanyuan.assembly.common;

/**
 * 2012-7-7 jqsl2012@163.com
 */

import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

/**
 * 该接口提供业务逻辑最基本的服务，所有的业逻辑类都必须实现此接口，这样该业务逻辑类对应
 * 的action就免去了写基本selectList、insert、update、toEdit、deletes麻烦s
 * 
 * @author zfs
 * 
 */
public interface Services<K>
{
    /**
     * 添加
     * 
     * @param e
     * @return
     */
    public int insert(K e);

    /**
     * 删除
     * 
     * @param e
     * @return
     */
    public int deleteById(String id);

    /**
     * 修改
     * 
     * @param e
     * @return
     */
    public int update(K e);

    /**
     * 根据ID查询一条记录
     * 
     * @param e
     * @return
     */
    public K selectById(String id);
    /**
     * 查询记录数
     * 
     * @param id
     */
    public int getTotalCount(ConditionGroup cond);

    /**
     * 根据条件查询所有
     * 
     * @return
     */
    public List<K> selectList(ConditionGroup cond, OrderGroup order);

    /**
     * 分页查询
     * 
     * @param e
     * @return
     */
    public List<K> selectPageList(int page, int pagecount,ConditionGroup cond, OrderGroup order);

}
