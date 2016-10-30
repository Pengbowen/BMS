package com.lanyuan.assembly.dictionary;

import com.lanyuan.assembly.basic.baseclasses.EntityBase;
import com.lanyuan.assembly.basic.database.DBConfig;

/**
 * 
 * 数据字典的对象实体类
 *
 * @author qinye
 * @date 2014-11-22 下午08:03:50
 */
public class EntityDictionary extends EntityBase
{
    static String s_tableName = DBConfig.getCOM_NAME() + "basic_dictionary";
    protected String getTableName()
    {
        return s_tableName;
    }
    
    /**
     * 字典ID
     */
    private int dictionaryId; 
    
    /**
     * 字典名称
     */
    private String sName = "";
    
    /**
     * 说明
     */
    private String sExplain1 = "";
    
    /**
     * 包含自定义编码
     */
    private boolean blnCustomCodeFieldTag = false;
    
    /**
     * 多级字典
     */
    private boolean blnMultistageTag = false;
    
    /**
     * 数据存放对应数据库表
     */
    private String sDataTableName = "";

    
    // 构造函数
    public EntityDictionary()
    {}

    public EntityDictionary(int iId, String sName, String sExplain1, boolean blnCustomCodeFieldTag,
            boolean blnMultistageTag, String sDataTableName)
    {
        super();
        this.dictionaryId = iId;
        this.sName = sName;
        this.sExplain1 = sExplain1;
        this.blnCustomCodeFieldTag = blnCustomCodeFieldTag;
        this.blnMultistageTag = blnMultistageTag;
        this.sDataTableName = sDataTableName;
    }

    /**
     * 字典ID
     */    
    public int getDictionaryId()
    {
        return dictionaryId;
    }

    /**
     * 字典ID
     */    
    public void setDictionaryId(int dictionaryId)
    {
        this.dictionaryId = dictionaryId;
    }

    /**
     * 字典名称
     */    
    public String getsName()
    {
        return sName;
    }

    /**
     * 字典名称
     */    
    public void setsName(String sName)
    {
        this.sName = sName;
    }

    /**
     * 说明
     */    
    public String getsExplain1()
    {
        return sExplain1;
    }

    /**
     * 说明
     */    
    public void setsExplain1(String sExplain1)
    {
        this.sExplain1 = sExplain1;
    }

    /**
     * 包含自定义编码
     */    
    public boolean isBlnCustomCodeFieldTag()
    {
        return blnCustomCodeFieldTag;
    }

    /**
     * 包含自定义编码
     */    
    public void setBlnCustomCodeFieldTag(boolean blnCustomCodeFieldTag)
    {
        this.blnCustomCodeFieldTag = blnCustomCodeFieldTag;
    }

    /**
     * 多级字典
     */    
    public boolean isBlnMultistageTag()
    {
        return blnMultistageTag;
    }

    /**
     * 多级字典
     */    
    public void setBlnMultistageTag(boolean blnMultistageTag)
    {
        this.blnMultistageTag = blnMultistageTag;
    }

    /**
     * 数据存放对应数据库表
     */    
    public String getsDataTableName()
    {
        return sDataTableName;
    }

    /**
     * 数据存放对应数据库表
     */    
    public void setsDataTableName(String sDataTableName)
    {
        this.sDataTableName = sDataTableName;
    }

    @Override
    public String toString()
    {
        return "EntityBasicDictionary [blnCustomCodeFieldTag=" + blnCustomCodeFieldTag
                + ", blnMultistageTag=" + blnMultistageTag + ", dictionaryId=" + dictionaryId
                + ", sDataTableName=" + sDataTableName + ", sExplain1=" + sExplain1 + ", sName="
                + sName + "]";
    }

}
