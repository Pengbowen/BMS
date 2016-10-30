package com.lanyuan.assembly.dictionary;

import com.lanyuan.assembly.basic.baseclasses.EntityBase;
import com.lanyuan.assembly.basic.database.DBConfig;
import com.lanyuan.assembly.dictionary.EntityDataDictionary;

/**
 * 
 * 数据字典的数据实体类
 *
 * @author qinye
 * @date 2014-11-22 下午08:12:10
 */
public class EntityDataDictionary extends EntityBase
{
    public static String s_tableName = DBConfig.getCOM_NAME() + "basic_dictionary_data";
    
    private Integer id; // 数据ID(排序)
    private Integer dictionaryid;//字典id
    private String code;//编号
    private String content;//内容
    private String customfield1;
    //显示顺序
    private Integer showNum;
    //服务分类
    private String serviceClass;
    private Integer fid;

    public enum FieldList
    {
    	id("id"),
    	dictionaryid("dictionaryid"),
    	code("code"),
    	content("content"),
    	customfield1("customfield1"),
    	showNum("showNum"),
    	serviceClass("serviceClass"),
    	fid("fid");
    	
    	private String value;

        FieldList(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return this.value;
        }
        
        public String getContainTable()
        {
            return EntityDataDictionary.s_tableName + "." + this.value;
        }
        
        @Override
        public String toString()
        {
            return this.value;
        }
    }
    
    
    // 构造函数
    public EntityDataDictionary()
    {
    	super();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDictionaryid() {
		return dictionaryid;
	}

	public void setDictionaryid(Integer dictionaryid) {
		this.dictionaryid = dictionaryid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustomfield1() {
		return customfield1;
	}

	public void setCustomfield1(String customfield1) {
		this.customfield1 = customfield1;
	}

	

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

    @Override
    protected String getTableName()
    {
        // TODO Auto-generated method stub
        return EntityDataDictionary.s_tableName;
    }

	public static String getS_tableName() {
		return s_tableName;
	}

	public static void setS_tableName(String s_tableName) {
		EntityDataDictionary.s_tableName = s_tableName;
	}

	@Override
	public String toString() {
		return "EntityDataDictionary [id=" + id + ", dictionaryid="
				+ dictionaryid + ", code=" + code + ", content=" + content
				+ ", customfield1=" + customfield1 + ", showNum=" + showNum
				+ ", serviceClass=" + serviceClass + ", fid=" + fid + "]";
	}

}
