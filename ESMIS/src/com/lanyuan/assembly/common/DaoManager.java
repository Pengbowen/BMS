
package com.lanyuan.assembly.common;

/**
 * 2012-7-7 jqsl2012@163.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 该接口提供业务逻辑最基本的服务，所有的业逻辑类都必须实现此接口，这样该业务逻辑类对应
 * 的action就免去了写基本selectList、insert、update、toEdit、deletes麻烦s
 * 
 * @author zfs
 * 
 */
public interface DaoManager<V>
{
    /**
     * 添加
     * 
     * @param e
     * @return 添加结果
     */
    public int insert(V e);

    /**
     * 修改
     * 
     * @param e
     * @return 修改结果
     */
    public int update(V e);

    /**
     * 根据ID来删除一条记录
     * 
     * @param id
     * @return 删除结果
     */
    public int deleteById(String id);

    /**
     * 根据ID查询一条记录
     * 
     * @param id
     * @return 查询结果
     */
    public V selectById(String id);

    /**
     * 查询记录数
     * 
     * @param sql sql条件
     *  @return 查询结果条数
     */
    public int getTotalCount(@Param(value="sql")  String sql);

    /**
     * 根据条件查询所有
     * 
     * @return 查询结果
     */
    public List<V> selectList(@Param(value="sql")  String sql);


    /**
     * 分页查询
     * 
     * @param e
     * @return 分页查询结果
     */
    public List<V> selectPageList(Page e);
}
