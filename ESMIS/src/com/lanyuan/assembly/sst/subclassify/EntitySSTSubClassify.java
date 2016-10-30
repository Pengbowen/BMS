package com.lanyuan.assembly.sst.subclassify;
/**
 * 体系表-子分类实体类
 * @author PBW
 * @date 2016年8月23日14:23:55
 */
public class EntitySSTSubClassify{
	/**
	 * 所属体系表
	 */
	private Integer SSTId;
	/**
	 * 所属层级
	 */
	private Integer layerNo;
	/**
	 * 所属层项目
	 */
	private String layerItemId;
	/**
	 * 子分类编号
	 */
	private String subClassId;
	/**
	 * 子分类名称
	 */
	private String subClassName;
	/**
	 * 包含标准数量
	 */
	private Integer standardCount;
	/**
	 * 状态
	 */
	private Integer isEnabled;
	/**
	 * 显示状态  1-显示,0-隐藏
	 */
	private Integer isVisible;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
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


	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 *  返回  所属层项目
	 */
	public String getLayerItemId(){
		return layerItemId;
	}

	/**
	 *  设置  所属层项目
	 */
	public void setLayerItemId(String layerItemId){
		this.layerItemId=layerItemId;
	}


	/**
	 *  返回  子分类编号
	 */
	public String getSubClassId(){
		return subClassId;
	}

	/**
	 *  设置  子分类编号
	 */
	public void setSubClassId(String subClassId){
		this.subClassId=subClassId;
	}


	/**
	 *  返回  子分类名称
	 */
	public String getSubClassName(){
		return subClassName;
	}

	/**
	 *  设置  子分类名称
	 */
	public void setSubClassName(String subClassName){
		this.subClassName=subClassName;
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

	/**
	 *  设置  修改IP
	 */
	public void setModifyIP(String modifyIP){
		this.modifyIP=modifyIP;
	}

	public EntitySSTSubClassify(){
	   super();
	}
}
