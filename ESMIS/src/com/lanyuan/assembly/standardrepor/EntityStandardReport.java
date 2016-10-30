package com.lanyuan.assembly.standardrepor;
public class EntityStandardReport{


	/**
	 * 报表编号
	 */
	private String reportId;
	/**
	 * 标准类别
	 */
	private String standardCategory;
	/**
	 * 标准类别名称
	 */
	private String standardCategoryName;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 项目编号
	 */
	private String itemId;
	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 *  返回  报表编号
	 */
	public String getReportId(){
		return reportId;
	}

	/**
	 *  设置  报表编号
	 */
	public void setReportId(String reportId){
		this.reportId=reportId;
	}


	/**
	 *  返回  标准类别
	 */
	public String getStandardCategory(){
		return standardCategory;
	}

	/**
	 *  设置  标准类别
	 */
	public void setStandardCategory(String standardCategory){
		this.standardCategory=standardCategory;
	}


	/**
	 *  返回  显示顺序
	 */
	public Integer getDisplayOrder(){
		return displayOrder;
	}

	/**
	 *  设置  显示顺序
	 */
	public void setDisplayOrder(Integer displayOrder){
		this.displayOrder=displayOrder;
	}


	/**
	 *  返回  项目编号
	 */
	public String getItemId(){
		return itemId;
	}

	/**
	 *  设置  项目编号
	 */
	public void setItemId(String itemId){
		this.itemId=itemId;
	}


	/**
	 *  返回  数量
	 */
	public Integer getQuantity(){
		return quantity;
	}

	/**
	 *  设置  数量
	 */
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}

	public EntityStandardReport(){
	   super();
	}

	public String getStandardCategoryName() {
		return standardCategoryName;
	}

	public void setStandardCategoryName(String standardCategoryName) {
		this.standardCategoryName = standardCategoryName;
	}
}
