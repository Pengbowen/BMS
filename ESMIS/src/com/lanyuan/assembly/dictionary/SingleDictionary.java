package com.lanyuan.assembly.dictionary;

public class SingleDictionary
{
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
    
    public SingleDictionary()
    {
    }
    
    @Override
    public String toString()
    {
        return "SingleDictionary [blnCustomCodeFieldTag=" + blnCustomCodeFieldTag
        + ", blnMultistageTag=" + blnMultistageTag + ", dictionaryId=" + dictionaryId
        + ", sDataTableName=" + sDataTableName + ", sExplain1=" + sExplain1 + ", sName="
        + sName + "]";
    }
}
