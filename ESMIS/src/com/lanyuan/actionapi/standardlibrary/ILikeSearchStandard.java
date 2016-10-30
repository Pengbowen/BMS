package com.lanyuan.actionapi.standardlibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.query.index.SingleIndex;
import com.lanyuan.assembly.query.search.ServiceSearch;
import com.lanyuan.assembly.query.search.SinglePage;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.util.EncryptUtil;
import com.opensymphony.xwork2.Action;

public class ILikeSearchStandard extends ResultSearchAction{
	private static final long serialVersionUID = 1L;
	
		@Autowired
		private ServiceSearch searchService;
	 	/**
	     * 标准Id
	     */
	    private String standardId;
	    /**
	     * 标准编号
	     */
	    private String standardNo;
	    /**
	     * 标准名称
	     */
	    private String standardName;
	    /**
	     * 英文名称
	     */
	    private String standardNameEN;
		/**
	     * 专业
	     */
	    private String applicableMajor;
	    /**
	     * 内容
	     */
	    private String content;
	    /**
	     * 查询内容
	     */
	    private String likeSearch;
	    
	   
	    public String searchStandardlibrary(){
	    	try {
	    		SinglePage single = new SinglePage();
	    	Map<String, String> category =new HashMap<String, String>();
	    	category.put("国家标准", "GB");
	    	category.put("电力行业标准", "DL");
	    	category.put("水利行业标准", "SL");
	    	category.put("水电行业标准", "SD");
	    	category.put("机械行业标准", "JB");
	    	category.put("计量标准", "JJG");
	    	category.put("环境", "HJ");
	    	category.put("国网公司企业标准", "Q/GDW");
	    	category.put("华能企业标准", "Q/HN");
	    	List<String> list =null;
	    	if(likeSearch==null||"".equals(likeSearch)){
	    		printString(null, 0, "2", "查询信息不能为空！");
	    		return Action.SUCCESS;
	    	}
	    	if(applicableMajor!=null&&!"".equals(applicableMajor)){
	    	String [] arr = applicableMajor.split("、");
	    	list = new ArrayList<String>();
	    	for (String string : arr) {
				list.add(string);
			}
	    	}else {
				applicableMajor=null;
			}
	    	single	=searchService.Generalquery(getCurrentPage(), getLinesOfPage(), list, likeSearch);
	    	List<SingleIndex> indexList = single.getList();
	    	int recordCount = single.getTotalPage();
	    	List<HashMap<String, String>> datalist= new ArrayList<HashMap<String,String>>();
	    	for (SingleIndex singdata : indexList) {
	    		String categoryName =singdata.getStandardCategoryName();
	    		String standardCategory = category.get(categoryName);
	    		 HashMap<String, String> returnData = new HashMap<String, String>();
	    		 returnData.put(MappingList.standardId.name(), singdata.getStandardId());
	             returnData.put(MappingList.standardNo.name(), singdata.getStandardNo());
	             returnData.put(MappingList.standardName.name(), singdata.getStandardName());
	             returnData.put(MappingList.standardNameEN.name(), singdata.getStandardNameEN());
	             returnData.put(MappingList.applicableMajor.name(), singdata.getApplicableMajor());
	             returnData.put(MappingList.standardCategory.name(), standardCategory);
	             returnData.put(MappingList.standardCategoryName.name(), categoryName);
	             returnData.put(MappingList.effectiveDate.name(), singdata.getEffectiveDate());
	             returnData.put(MappingList.effectiveState.name(), singdata.getEffectiveState());
	             returnData.put(MappingList.documentType.name(), singdata.getDocumentType());
	             returnData.put(MappingList.oldStandardId.name(), singdata.getOldStandardId());
	             returnData.put(MappingList.oldStandardNo.name(), singdata.getOldStandardNo());
	             returnData.put(MappingList.approvedDate.name(), singdata.getApprovedDate());
	             //System.out.println(singdata.getApprovedDate()+"----------");
	             returnData.put("content", singdata.getContent());
	             Map<String, String> map=EncryptUtil.encrypt(singdata.getStandardId());
	             returnData.put("key", map.get("key"));
	             returnData.put("str", map.get("str"));
	             datalist.add(returnData);
			}
	    	this.printString(datalist, recordCount, "1", "");
	    	} catch (Exception e) {
				e.printStackTrace();
			}
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
		public String getStandardName() {
			return standardName;
		}
		public void setStandardName(String standardName) {
			this.standardName = standardName;
		}
		public String getStandardNameEN() {
			return standardNameEN;
		}
		public void setStandardNameEN(String standardNameEN) {
			this.standardNameEN = standardNameEN;
		}
		public String getApplicableMajor() {
			return applicableMajor;
		}
		public void setApplicableMajor(String applicableMajor) {
			this.applicableMajor = applicableMajor;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getLikeSearch() {
				return likeSearch;
		}
		public void setLikeSearch(String likeSearch) {
				this.likeSearch = likeSearch;
		}
}
