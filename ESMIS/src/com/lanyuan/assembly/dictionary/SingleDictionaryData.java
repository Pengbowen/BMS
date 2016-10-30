package com.lanyuan.assembly.dictionary;

public class SingleDictionaryData
{
    private int id;
    private int dictionaryid;
    private String code;
    private String content;
    private String customfield1;
    //显示序号
    private Integer showNum;
    //服务分类
    private String serviceClass;
    private int fid;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getDictionaryid() {
		return dictionaryid;
	}
	public void setDictionaryid(int dictionaryid) {
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
	public int getFid() {
        return fid;
    }
    public void setFid(int fid) {
        this.fid = fid;
    }
    
	@Override
	public String toString() {
		return "SingleDictionaryData [id=" + id + ", dictionaryid="
				+ dictionaryid + ", code=" + code + ", content=" + content
				+ ", customfield1=" + customfield1 + ", showNum=" + showNum
				+ ", serviceClass=" + serviceClass + ", fid=" + fid + "]";
	}
	public SingleDictionaryData() {
		super();
	}
    
}
