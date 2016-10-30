package com.lanyuan.assembly.sst.layeritems;

public class SingleSSTLayerItems{
	/**
	 * 所属体系表
	 */
	private Integer SSTId;
	/**
	 * 项目编号
	 */
	private String layerItemId;
	/**
	 * 项目号码
	 */
	private String layerItemNo;
	
	
	/**
	 * 项目名称
	 */
	private String layerItemName;
	/**
	 * 所属层级
	 */
	private Integer layerNo;
	/**
	 * 所属父级
	 */
	private String belongItemId;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 包含子项目数量
	 */
	private Integer subItemCount;
	/**
	 * 包含子分类数量
	 */
	private Integer subClassifyCount;
	/**
	 * 包含标准数量
	 */
	private Integer standardCount;
	/**
	 * 状态(1-启用,0-停用)
	 */
	private Integer isEnabled;
	/**
	 * 显示状态(1,显示  0,隐藏)
	 */
	private Integer isVisible;
	/**
	 * 修改人
	 */
	private String modifyer;
	/**
	 * 修改人姓名
	 */
	private String modifyerName;
	/**
	 * 修改时间
	 */
	private String modifyTime;
	/**
	 * 修改IP
	 */
	private String modifyIP;

	/**
	 *  返回  所属体系表
	 */
	public Integer getSSTId(){
		return SSTId;
	}

	/**
	 *  设置  所属体系表
	 */
	public void setSSTId(Integer sSTId){
		this.SSTId=sSTId;
	}


	/**
	 *  返回  项目编号
	 */
	public String getLayerItemId(){
		return layerItemId;
	}

	/**
	 *  设置  项目编号
	 */
	public void setLayerItemId(String layerItemId){
		this.layerItemId=layerItemId;
	}


	/**
	 *  返回  项目名称
	 */
	public String getLayerItemName(){
		return layerItemName;
	}

	/**
	 *  设置  项目名称
	 */
	public void setLayerItemName(String layerItemName){
		this.layerItemName=layerItemName;
	}


	/**
	 *  返回  所属层级
	 */
	public Integer getLayerNo(){
		return layerNo;
	}

	/**
	 *  设置  所属层级
	 */
	public void setLayerNo(Integer layerNo){
		this.layerNo=layerNo;
	}


	/**
	 *  返回  所属父级
	 */
	public String getBelongItemId(){
		return belongItemId;
	}

	/**
	 *  设置  所属父级
	 */
	public void setBelongItemId(String belongItemId){
		this.belongItemId=belongItemId;
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
	 *  返回  包含子项目数量
	 */
	public Integer getSubItemCount(){
		return subItemCount;
	}

	/**
	 *  设置  包含子项目数量
	 */
	public void setSubItemCount(Integer subItemCount){
		this.subItemCount=subItemCount;
	}


	/**
	 *  返回  包含子分类数量
	 */
	public Integer getSubClassifyCount(){
		return subClassifyCount;
	}

	/**
	 *  设置  包含子分类数量
	 */
	public void setSubClassifyCount(Integer subClassifyCount){
		this.subClassifyCount=subClassifyCount;
	}


	/**
	 *  返回  包含标准数量
	 */
	public Integer getStandardCount(){
		return standardCount;
	}

	/**
	 *  设置  包含标准数量
	 */
	public void setStandardCount(Integer standardCount){
		this.standardCount=standardCount;
	}


	/**
	 *  返回  状态
	 */
	public Integer getIsEnabled(){
		return isEnabled;
	}

	/**
	 *  设置  状态
	 */
	public void setIsEnabled(Integer isEnabled){
		this.isEnabled=isEnabled;
	}


	/**
	 *  返回  修改人
	 */
	public String getModifyer(){
		return modifyer;
	}

	/**
	 *  设置  修改人
	 */
	public void setModifyer(String modifyer){
		this.modifyer=modifyer;
	}


	/**
	 *  返回  修改人姓名
	 */
	public String getModifyerName(){
		return modifyerName;
	}

	/**
	 *  设置  修改人姓名
	 */
	public void setModifyerName(String modifyerName){
		this.modifyerName=modifyerName;
	}


	/**
	 *  返回  修改时间
	 */
	public String getModifyTime(){
		return modifyTime;
	}

	/**
	 *  设置  修改时间
	 */
	public void setModifyTime(String modifyTime){
		this.modifyTime=modifyTime;
	}


	/**
	 *  返回  修改IP
	 */
	public String getModifyIP(){
		return modifyIP;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 *  设置  修改IP
	 */
	public void setModifyIP(String modifyIP){
		this.modifyIP=modifyIP;
	}

	public String getLayerItemNo() {
		return layerItemNo;
	}

	public void setLayerItemNo(String layerItemNo) {
		this.layerItemNo = layerItemNo;
	}

}
