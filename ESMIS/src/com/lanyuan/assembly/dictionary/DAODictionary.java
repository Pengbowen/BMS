package com.lanyuan.assembly.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.baseclasses.DataHandle;
import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.basic.database.ConnectionUtils;
import com.lanyuan.assembly.dictionary.EntityDictionary;


/**
 * 
 * 数据字典的对象DAO类
 *
 * @author qinye
 * @date 2014-11-22 下午08:15:28
 */
class DAODictionary extends DataHandle
{
    private EntityDictionary basic = null; // 创建实体对象
    

    // 增加字典的SQL语句
    String sAddSql = "insert into " + EntityDictionary.s_tableName + " values(?,?,?,?,?,?)";

    // 修改字典sql
    String sUpSql = "update "
            + EntityDictionary.s_tableName
            + "  set name=?,explain1=?,CustomCodeFieldTag=?,MultistageTag=?,DataTableName=?  where id = ?";

    // 删除字典sql语句
    String sDelSql = "delete from  " + EntityDictionary.s_tableName + " where id = ?";

    // 查询分页显示
    String sSchSql = "select * from " + EntityDictionary.s_tableName + " limit ?,?";
    // 查询所有
    String sSchAllSql = "select * from " + EntityDictionary.s_tableName;
    // 根据id查询
    String sSchByIdSql = "select * from " + EntityDictionary.s_tableName + " where id = ? ";

    // 根据字典id判断字典记录是否存在
    String sIsExistByIdSql = "select count(*) from " + EntityDictionary.s_tableName + " where id = ?";

    // 查询总页数sql
    String sTotalPaSql = "select count(*) from " + EntityDictionary.s_tableName;

    /**
     * 添加一条字典记录
     * 
     * @param basic 字典对象
     * @return 成功返回空串，失败返回错误信息
     * @throws ConnException
     */
    public String add(EntityDictionary basic) throws ConnException
    {
        String sStrRet = ""; // 用于存储返回信息
        Object param[] = { basic.getDictionaryId(), basic.getsName(), basic.getsExplain1(),
                basic.isBlnCustomCodeFieldTag(), basic.isBlnMultistageTag(),
                basic.getsDataTableName() };
        int iN = this.executeUpdate(sAddSql, param);
        if (iN != 1)
        {
            sStrRet = "执行出错，增加字典的sql语句有误！";
        }
        return sStrRet;
    }

    /**
     * 修改字典
     * 
     * @param iId 字典信息的主键
     * @param basic 字典对象
     * @return 成功返回空串，失败返回错误信息
     * @throws ConnException
     */
    public String update(int iId, EntityDictionary basic) throws ConnException
    {
        String sStrRet = ""; // 用于存储返回信息,确认是否执行成功
        // 判断要修改的id是否存在
        if (!isExistById(iId))
        {
            sStrRet = "id不存在，无法修改，请确认再操作！";
            return sStrRet;
        }
        Object param[] = { basic.getsName(), basic.getsExplain1(), basic.isBlnCustomCodeFieldTag(),
                basic.isBlnMultistageTag(), basic.getsDataTableName(), iId };
        int iN = this.executeUpdate(sUpSql, param);
        if (iN != 1)
        {
            sStrRet = "修改字典的sql语句执行出错！";
        }
        return sStrRet;
    }

    /**
     * 根据id，删除字典
     * 
     * @param iId 删除的字典信息的主键
     * @return 删除成功：返回空值；失败：返回错误信息
     * @throws ConnException
     */
    public int delete(int iId) throws ConnException
    {
        Object param[] = { iId };
        return this.executeUpdate(sDelSql, param);
    }

    /**
     * 分页查询字典
     * 
     * @param iPageSize 每页多少条记录
     * @param iPage 查询第几页
     * @return 返回对象map集合
     * @throws ConnException
     */
    public List<EntityDictionary> search(int iPage, int iPageSize) throws ConnException
    {
        // 在结果集中，第page页的记录显示pageSize条
        int begin = (iPage - 1) * iPageSize; // mysql条数从0开始
        Object[] param = { begin, iPageSize };
        List<Map<String, String>> listObject = this.executeSelect(sSchSql, param);
        // 从对象集合中获取数据封装成对象
        return setPropertyData(listObject);
    }

    /**
     * 查询所有字典信息
     * 
     * @return 返回字典对象集合
     * @throws ConnException
     */
    public List<EntityDictionary> search()
    {
        Object[] param = {};
        List<Map<String, String>> listObject = this.executeSelect(sSchAllSql, param);
        // 从对象集合中获取数据封装成对象
        return setPropertyData(listObject);
    }

    /**
     * 根据id查询字典，获取字典对象，以json字符串的形式返回
     * 
     * @param id 字典信息的主键
     * @return 返回对象map集合
     * @throws ConnException
     */
    public List<EntityDictionary> searchById(int id) throws ConnException
    {
        Object[] param = { id };
        List<Map<String, String>> listMap = this.executeSelect(sSchByIdSql, param);
        // 从对象集合中获取数据封装成对象
        return setPropertyData(listMap);
    }

    /**
     * 根据id判断字典记录是否存在，TRUE：存在；FALSE：不存在
     * 
     * @param iId 字典id
     * @return true：存在；false：不存在
     * @throws ConnException
     */
    public boolean isExistById(int iId) throws ConnException
    {
        boolean flag = false; // 用于返回是否存在记录
        Object param[] = { iId };
        int iN = this.getCountBySql(sIsExistByIdSql, param);
        if (iN > 0)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取分页显示时的总页数
     * 
     * @param iPageSize 每页显示的条数
     * @return 返回分页时的总页数
     * @throws ConnException
     */
    public int totalPages(int iPageSize) throws ConnException
    {
        int iTotal = ConnectionUtils.totalPages(sTotalPaSql, iPageSize);
        return iTotal;
    }

    /**
     * 为实体类对象添加属性
     * 
     * @param listMap list中封装的Map键值对，Map中封装着一个对象的所有属性
     * @return 返回实体类对象的集合list
     */
    private List<EntityDictionary> setPropertyData(List<Map<String, String>> listMap)
    {
        // 存储对象集合用于返回
        List<EntityDictionary> mapObj = new ArrayList<EntityDictionary>();
        if (null != listMap)
        {
            // 1、遍历对象集合，n个对象
            for (int i = 0; i < listMap.size(); i++)
            {
                Map<String, String> map = listMap.get(i); // 从对象集合中遍历每一个对象
                basic = new EntityDictionary(); // 用于封装一个对象的所有属性
                // 2、遍历每一个对象数组 ，6个属性
                if (map.get("id") != null)
                {
                    basic.setDictionaryId(Integer.parseInt((String) map.get("id")));
                }
                if (map.get("name") != null)
                {
                    basic.setsName((String) map.get("name"));
                }
                if (map.get("explain1") != null)
                {
                    basic.setsExplain1((String) map.get("explain1"));
                }
                if (map.get("customcodefieldtag") != null)
                {
                    basic.setBlnCustomCodeFieldTag("1".equals((String) map.get("customcodefieldtag")) ? true
                            : false);
                }
                if (map.get("multistagetag") != null)
                {
                    basic.setBlnMultistageTag("1".equals((String) map.get("multistagetag")) ? true
                            : false);
                }
                if (map.get("datatablename") != null)
                {
                    basic.setsDataTableName((String) map.get("datatablename"));
                }
                // 把对象添加到集合中，用于返回对象集合
                mapObj.add(basic);
            }
        }
        return mapObj;
    }

}
