package com.lanyuan.actionapi.standardlibrary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.util.EncryptUtil;
import com.opensymphony.xwork2.Action;

public class IStandardMoreSearch extends ResultSearchAction{
	private static final long serialVersionUID = 1L;
	@Autowired
    private ServiceStandardLibrary standardLibraryService;
	/**
	 * 参数Id
	 */
	private String id;
	
	/**
     * 标准Id
     */
	private String standardId;
	/**
     * 标准编号
     */
    private String standardNo;
    /**
     * 替代标准
     */
    private String oldStandardNo;
    /**
     * 文件存放位置
     */
    private String filePath;
    /**
     * 标准名称
     */
    private String standardName;
    public String execute(){
    	
    	List<HashMap<String, String>> datalist =
    			standardLibraryService.searchMoreStandard(id);
    	for (HashMap<String, String> hashMap : datalist) {
    		Map<String, String> map=EncryptUtil.encrypt(hashMap.get("id"));
    		hashMap.remove("filePath");
    		hashMap.put("key", map.get("key"));
    		hashMap.put("strUrl", map.get("str"));
		}
    	
    	this.printString(datalist,datalist.size() , "1", "");
    	 return Action.SUCCESS;
    }
 
    public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStandardNo() {
		return standardNo;
	}
	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}
	public String getOldStandardNo() {
		return oldStandardNo;
	}
	public void setOldStandardNo(String oldStandardNo) {
		this.oldStandardNo = oldStandardNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getId() {
			return id;
	}
	public void setId(String id) {
			this.id = id;
	}
}
