package com.lanyuan.assembly.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.baseclasses.DataService;
import com.lanyuan.assembly.basic.controller.IQueryResolve;
import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.dictionary.EntityDataDictionary.FieldList;

/**
 * Service类
 * 
 * @ClassName:ServicePriceLevel
 * @author gyy
 * @date Sat Nov 14 15:11:33 CST 2016
 */
public class ServiceDictionaryData extends DataService implements IQueryResolve
{
    private DAODataDictionary g_dao;

    public ServiceDictionaryData()
    {
        this.g_dao = new DAODataDictionary();
    }

    public enum MappingList
    {

        id(EntityDataDictionary.FieldList.id.getValue()),

        dictionaryid(EntityDataDictionary.FieldList.dictionaryid.getValue()),

        code(EntityDataDictionary.FieldList.code.getValue()),

        content(EntityDataDictionary.FieldList.content.getValue()),

        customfield1(EntityDataDictionary.FieldList.customfield1.getValue()),

        showNum(EntityDataDictionary.FieldList.showNum.getValue()),

        serviceClass(EntityDataDictionary.FieldList.serviceClass.getValue()),

        fid(EntityDataDictionary.FieldList.fid.getValue());

        private String value;

        MappingList(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return this.value;
        }
    }

    public List<Map<String, String>> searchByContent(Integer id,String content)
    {
        // System.out.println("通过Id返回数据");
        this.assertNotNull(id, "编号不能为空");
        this.assertNotNull(content, "名称不能为空");
        // System.out.println("----");
        List<Map<String, String>> list = g_dao.searchByContent(id, content);
        return list;
    }
    
    /**
     * 新增
     * 
     * @param data 实体类
     * @return 成功返回true,失败返回false
     * @throws ConnException 
     */
    public boolean add(SingleDictionaryData data) throws ConnException
    {
        this.assertNotNull(data, "对象没有实例化");
        int id = this.g_dao.getNewId();
        data.setId(id);
        int rtn = this.g_dao.add1(singleToEntity(data));
        return rtn > 0;
    }

    /**
     * 获取最大dictionaryId并+1
     */
    public int getMaxDictionaryId(Integer dictionaryId)
    {
        return this.g_dao.getMaxDictionaryId(dictionaryId);
    }

    /**
     * 修改
     * 
     * @param data 实体类
     * @return
     */
    public boolean modify(Integer id, SingleDictionaryData data)
    {
        // System.out.println("修改方法执行");
        this.assertNotNull(id, "编号不能为空");
        this.assertNotNull(data, "对象没有实例化");
        int rtn = this.g_dao.modify(id, singleToEntity(data));
        if (rtn <= 0) return false;
        return true;
    }

    @Override
    public String getMappingValueById(String mappingId)
    {
        return MappingList.valueOf(mappingId).getValue();
    }

    @SuppressWarnings("unchecked")
    public SingleDictionaryData getDataObject()
    {
        return new SingleDictionaryData();
    }

    public int getTotalCount(ConditionGroup clsQuery)
    {
        return g_dao.getTotalCount(clsQuery);
    }

    /**
     * 根据条件查询
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SingleDictionaryData>
            selectByCondition(ConditionGroup clsQuery, OrderGroup clsOrder)
    {
        if (null == clsOrder)
        {
            clsOrder = new OrderGroup();
            clsOrder.Add(FieldList.id.getValue(), false);
        }
        List<EntityDataDictionary> listValue = g_dao.selectByCondition(clsQuery, clsOrder);
        if (listValue == null)
        {
            return null;
        }
        List<SingleDictionaryData> listData = new ArrayList<SingleDictionaryData>();
        for (EntityDataDictionary cls : listValue)
        {
            listData.add(entityToSingle(cls));
        }
        return listData;
    }

    /**
     * 分页条件查询
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SingleDictionaryData> selectByConditionAndPage(int page, int pagecount,
            ConditionGroup clsQuery, OrderGroup clsOrder)
    {
        if (page == 0) page = 1;
        if (null == clsOrder)
        {
            clsOrder = new OrderGroup();
            clsOrder.Add(FieldList.id.getValue(), false);
        }
        List<EntityDataDictionary> listValue = g_dao.selectByConditionAndPage(page, pagecount,
                                                                              clsQuery, clsOrder);
        if (listValue == null)
        {
            return null;
        }
        List<SingleDictionaryData> listData = new ArrayList<SingleDictionaryData>();
        for (EntityDataDictionary cls : listValue)
        {
            listData.add(entityToSingle(cls));
        }
        return listData;
    }

    public SingleDictionaryData entityToSingle(EntityDataDictionary cls)
    {
        SingleDictionaryData single = new SingleDictionaryData();
        single.setId(cls.getId());
        single.setDictionaryid(cls.getDictionaryid());
        single.setCode(cls.getCode());
        single.setContent(cls.getContent());
        single.setCustomfield1(cls.getCustomfield1());
        single.setShowNum(cls.getShowNum());
        single.setServiceClass(cls.getServiceClass());
        single.setFid(cls.getFid());
        return single;
    }

    public EntityDataDictionary singleToEntity(SingleDictionaryData single)
    {
        EntityDataDictionary cls = new EntityDataDictionary();

        cls.setId(single.getId());
        cls.setDictionaryid(single.getDictionaryid());
        cls.setCode(single.getCode());
        cls.setContent(single.getContent());
        cls.setCustomfield1(single.getCustomfield1());
        cls.setShowNum(single.getShowNum());
        cls.setServiceClass(single.getServiceClass());
        cls.setFid(single.getFid());

        return cls;
    }

    public boolean delete(Integer id)
    {
        this.assertNotNull(id, "等级编号不能为空");
        int rtn = this.g_dao.delete(id);
        if (rtn <= 0) return false;
        return true;
    }

    public SingleDictionaryData selectById(Integer id)
    {
        // System.out.println("通过Id返回数据");
        this.assertNotNull(id, "编号不能为空");
        // System.out.println("----");
        EntityDataDictionary entity = g_dao.selectById(id);
        // System.out.println("++++");
        if (null == entity) return null;
        // System.out.println(entity);
        return entityToSingle(entity);
    }
    /**
     * 根据code返回content值
     */
    public String selectByCode(String code){
    	List<String> list = g_dao.selectByCode(code);
    	String content="";
    	if(list==null) return content;
    	for(int i = 0;i<list.size();i++){
    		content+=list.get(i);
    		if(i+1!=list.size()){
    			content+=",";
    		}
    	}
    	return content;
    }
}
