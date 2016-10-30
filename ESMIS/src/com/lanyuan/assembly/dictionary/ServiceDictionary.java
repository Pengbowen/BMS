
package com.lanyuan.assembly.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.lanyuan.assembly.basic.baseclasses.DataCommonService;
import com.lanyuan.assembly.basic.database.ConnException;

public class ServiceDictionary extends DataCommonService
{
    private DAODictionary dicDAO = new DAODictionary();
    private DAODataDictionary dataDicDAO = new DAODataDictionary();

    /**
     * 新增字典
     * 
     * @param clsDic 字典对象
     * @return 成功返回空字符串，失败返回错误信息
     * @throws ConnException
     */
    public String add(SingleDictionary clsDic) throws ConnException
    {
        // 判断字典id是否已经存在
        if (dicDAO.isExistById(clsDic.getDictionaryId())) { return "添加的字典id已经存在，无法添加！"; }
        // 添加字典
        return dicDAO.add(exchangeSingleToEntity(clsDic));
    }

    /**
     * 修改字典
     * 
     * @param clsDic 字典对象
     * @return 成功返回空字符串，失败返回错误信息
     * @throws ConnException
     */
    public String modify(SingleDictionary clsDic) throws ConnException
    {
        return dicDAO.update(clsDic.getDictionaryId(),
                exchangeSingleToEntity(clsDic));
    }

    /**
     * 根据字典id，删除字典
     * 
     * @param dictionaryId 字典id
     * @return 成功返回空字符串，失败返回错误信息
     * @throws ConnException
     */
    public int delete(int dictionaryId) throws ConnException
    {
        // 1、删除字典
        int result = dicDAO.delete(dictionaryId);

        // 2、如果id不为0，删除数据字典
        result += dataDicDAO.deleteByDictionary(dictionaryId);
        return result;
    }

    /**
     * 根据字典id，查询字典信息
     * 
     * @param dictionaryId 字典id
     * @return 返回对象map集合
     * @throws ConnException
     */
    public SingleDictionary selectById(int dictionaryId) throws ConnException
    {
        List<EntityDictionary> clsData = dicDAO.searchById(dictionaryId);
        if (null == clsData) return null;
        return exchangeEntityToSingle(clsData.get(0));
    }

    /**
     * 分页查询字典
     * 
     * @param page 第几页
     * @param pageSize 一页显示条数
     * @return 返回对象map集合
     * @throws ConnException
     */
    public List<SingleDictionary> select(int page, int pageSize)
            throws ConnException
    {
        List<EntityDictionary> list = dicDAO.search(page, pageSize);
        if (null == list) return null;

        List<SingleDictionary> reuslt = null;
        reuslt = new ArrayList<SingleDictionary>();
        for (int i = 0; i < list.size(); i++)
        {
            reuslt.add(exchangeEntityToSingle(list.get(i)));
        }
        return reuslt;

    }

    /**
     * 查询所有字典信息
     * 
     * @return 返回字典对象map集合
     * @throws ConnException
     */
    public List<SingleDictionary> select()
    {
        List<EntityDictionary> list = dicDAO.search();
        if (null == list) return null;

        List<SingleDictionary> reuslt = null;
        reuslt = new ArrayList<SingleDictionary>();
        for (int i = 0; i < list.size(); i++)
        {
            reuslt.add(exchangeEntityToSingle(list.get(i)));
        }
        return reuslt;
    }

    /**
     * 由数据字典封装字典对象
     * 
     * @param clsDataDic 数据字典对象
     * @return 字典对象
     */
    private EntityDictionary exchangeSingleToEntity(SingleDictionary clsDataDic)
    {
        EntityDictionary clsDic = new EntityDictionary();
        clsDic.setDictionaryId(clsDataDic.getDictionaryId());
        clsDic.setsName(clsDataDic.getsName());
        clsDic.setsExplain1(clsDataDic.getsExplain1());
        clsDic.setBlnCustomCodeFieldTag((clsDataDic.isBlnCustomCodeFieldTag()));
        clsDic.setBlnMultistageTag((clsDataDic.isBlnMultistageTag()));
        clsDic.setsDataTableName((clsDataDic.getsDataTableName()));
        return clsDic;
    }

    /**
     * 由数据字典封装字典对象
     * 
     * @param clsDataDic 数据字典对象
     * @return 字典对象
     */
    private SingleDictionary exchangeEntityToSingle(EntityDictionary clsDataDic)
    {
        SingleDictionary clsDic = new SingleDictionary();
        clsDic.setDictionaryId(clsDataDic.getDictionaryId());
        clsDic.setsName(clsDataDic.getsName());
        clsDic.setsExplain1(clsDataDic.getsExplain1());
        clsDic.setBlnCustomCodeFieldTag((clsDataDic.isBlnCustomCodeFieldTag()));
        clsDic.setBlnMultistageTag((clsDataDic.isBlnMultistageTag()));
        clsDic.setsDataTableName((clsDataDic.getsDataTableName()));
        return clsDic;
    }
   
}
