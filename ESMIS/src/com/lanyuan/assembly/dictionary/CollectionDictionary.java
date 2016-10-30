package com.lanyuan.assembly.dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.baseclasses.DataHandle;
import com.lanyuan.assembly.basic.database.ConnException;

public class CollectionDictionary
  extends DataHandle
  implements Iterable<SingleDictionaryData>
{
  private DAODataDictionary clsDAO;
  private int dictionaryId;
  LinkedHashMap<String, SingleDictionaryData> mapList;
  private Iterator<?> listIt;
  
  public CollectionDictionary(int dictionaryid)
  {
    this.mapList = new LinkedHashMap();
    this.dictionaryId = dictionaryid;
    this.clsDAO = new DAODataDictionary();
    reload();
  }
  
  public String searchNameByCode(String code)
  {
    String sname = null;
    if (this.mapList.get(code) != null) {
      sname = ((SingleDictionaryData)this.mapList.get(code)).getContent();
    }
    return sname;
  }
  
  private void reload()
  {
    List<EntityDataDictionary> enList = this.clsDAO.searchBydicID(this.dictionaryId);
    if (enList != null)
    {
      for (EntityDataDictionary d : enList) {
        this.mapList.put(d.getCode(), exchangeEntityToSingle(d));
      }
      this.listIt = this.mapList.entrySet().iterator();
    }
  }
  
  public Map<String, String> getDictionary()
  {
    Map<String, String> list = new LinkedHashMap();
    for (Map.Entry<String, SingleDictionaryData> en : this.mapList.entrySet()) {
      if (((SingleDictionaryData)en.getValue()).getCustomfield1().equals("1")) {
        list.put((String)en.getKey(), ((SingleDictionaryData)en.getValue()).getContent());
      }
    }
    return list;
  }
  
  public int add(SingleDictionaryData data)
    throws ConnException
  {
    assertNotNull(data);
    data.setDictionaryid(this.dictionaryId);
    data.setShowNum(data.getShowNum());
	data.setServiceClass(" ");
    return this.clsDAO.add(exchangeSingleToEntity(data));
  }
  
  private List<SingleDictionaryData> selectDataDictionaryByDictionary()
  {
    List<EntityDataDictionary> list = this.clsDAO.searchBydicID(this.dictionaryId);
    if (list == null) {
      return null;
    }
    List<SingleDictionaryData> result = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      result.add(exchangeEntityToSingle((EntityDataDictionary)list.get(i)));
    }
    return result;
  }
  
  public int update(SingleDictionaryData data)
    throws ConnException
  {
    assertNotNull(data);
    data.setDictionaryid(this.dictionaryId);
    data.setShowNum(data.getShowNum());
    data.setServiceClass(" ");
    return this.clsDAO.update(exchangeSingleToEntity(data));
  }
  
  private EntityDataDictionary exchangeSingleToEntity(SingleDictionaryData clsData)
  {
    EntityDataDictionary clsDic = new EntityDataDictionary();
    clsDic.setId(Integer.valueOf(clsData.getId()));
    clsDic.setDictionaryid(Integer.valueOf(clsData.getDictionaryid()));
    clsDic.setCode(clsData.getCode());
    clsDic.setContent(clsData.getContent());
    clsDic.setFid(Integer.valueOf(clsData.getFid()));
    clsDic.setCustomfield1(clsData.getCustomfield1());
    clsDic.setShowNum(clsData.getShowNum());
    clsDic.setServiceClass(clsData.getServiceClass());
    return clsDic;
  }
  
  private SingleDictionaryData exchangeEntityToSingle(EntityDataDictionary clsData)
  {
    SingleDictionaryData clsDic = new SingleDictionaryData();
    clsDic.setId(clsData.getId().intValue());
    clsDic.setDictionaryid(clsData.getDictionaryid().intValue());
    clsDic.setCode(clsData.getCode());
    clsDic.setContent(clsData.getContent());
    clsDic.setFid(clsData.getFid().intValue());
    clsDic.setCustomfield1(clsData.getCustomfield1());
    clsDic.setShowNum(clsData.getShowNum());
    clsDic.setServiceClass(clsData.getServiceClass());
    return clsDic;
  }
  
  public boolean delete(String code)
  {
    assertNotNull(code, "默认值不能为空");
    assertNotNull(Integer.valueOf(this.dictionaryId), "字典类型编号不能为空");
    String sql = "delete from " + EntityDataDictionary.s_tableName + " where " + EntityDataDictionary.FieldList.code.getValue() + " =? and " + EntityDataDictionary.FieldList.dictionaryid.getValue() + "=?";
    System.out.println(sql);
    Object[] param = { code, Integer.valueOf(this.dictionaryId) };
    int iN = executeUpdate(sql, param);
    return iN > 0;
  }
  
  public Iterator<SingleDictionaryData> iterator()
  {
    return new itr();
  }
  
  private class itr
    implements Iterator<SingleDictionaryData>
  {
    private itr() {}
    
    public boolean hasNext()
    {
      return CollectionDictionary.this.listIt.hasNext();
    }
    
    public SingleDictionaryData next()
    {
      Map.Entry<String, SingleDictionaryData> next = (Map.Entry)CollectionDictionary.this.listIt.next();
      return (SingleDictionaryData)next.getValue();
    }
    
    public void remove()
    {
      CollectionDictionary.this.listIt.remove();
    }
  }
}
