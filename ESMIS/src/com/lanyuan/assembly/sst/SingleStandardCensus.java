package com.lanyuan.assembly.sst;

public class SingleStandardCensus {
	/**
	 * 标准类别
	 */
	public String standardCategory;
	/**
	 * 层项目编号
	 */
	public String layerItemId;
	/**
	 * 子分类编号
	 */
	public String subClassId;
	
	public String standardCategoryName;
	
	/**
	 * 数量
	 */
	public Integer count;
	
	
	public String getStandardCategory() {
		return standardCategory;
	}
	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}
	
	public String getLayerItemId() {
		return layerItemId;
	}
	public void setLayerItemId(String layerItemId) {
		this.layerItemId = layerItemId;
	}
	public String getSubClassId() {
		return subClassId;
	}
	public void setSubClassId(String subClassId) {
		this.subClassId = subClassId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getStandardCategoryName() {
		return standardCategoryName;
	}
	public void setStandardCategoryName(String standardCategoryName) {
		this.standardCategoryName = standardCategoryName;
	}
	
	

}
