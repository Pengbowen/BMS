package com.lanyuan.assembly.netdisk;
public class EntityNetdisk{


	/**
	 * 对象编号
	 */
	private String objectNo;
	/**
	 * 所属目录
	 */
	private String parentobjectNo;
	/**
	 * 包含子项目数量
	 */
	private Integer chirldItemCount;
	/**
	 * 对象名称
	 */
	private String objectName;
	/**
	 * 对象类别
	 */
	private Integer objectType;
	/**
	 * 文件后缀
	 */
	private String objectSuffix;
	/**
	 * 存放位置
	 */
	private String objectSavePath;
	/**
	 * 关键字
	 */
	private String keyWords;
	/**
	 * 说明
	 */
	private String explains;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 最后修改人
	 */
	private String modifyer;
	/**
	 * 最后修改人姓名
	 */
	private String modifyerName;
	/**
	 * 最后修改时间
	 */
	private String modifyTime;
	/**
	 * 最后修改IP
	 */
	private String modifyIP;

	/**
	 *  返回  对象编号
	 */
	public String getObjectNo(){
		return objectNo;
	}

	/**
	 *  设置  对象编号
	 */
	public void setObjectNo(String objectNo){
		this.objectNo=objectNo;
	}


	/**
	 *  返回  所属目录
	 */
	public String getParentobjectNo(){
		return parentobjectNo;
	}

	/**
	 *  设置  所属目录
	 */
	public void setParentobjectNo(String parentobjectNo){
		this.parentobjectNo=parentobjectNo;
	}


	/**
	 *  返回  包含子项目数量
	 */
	public Integer getChirldItemCount(){
		return chirldItemCount;
	}

	/**
	 *  设置  包含子项目数量
	 */
	public void setChirldItemCount(Integer chirldItemCount){
		this.chirldItemCount=chirldItemCount;
	}


	/**
	 *  返回  对象名称
	 */
	public String getObjectName(){
		return objectName;
	}

	/**
	 *  设置  对象名称
	 */
	public void setObjectName(String objectName){
		this.objectName=objectName;
	}


	/**
	 *  返回  对象类别
	 */
	public Integer getObjectType(){
		return objectType;
	}

	/**
	 *  设置  对象类别
	 */
	public void setObjectType(Integer objectType){
		this.objectType=objectType;
	}


	/**
	 *  返回  文件后缀
	 */
	public String getObjectSuffix(){
		return objectSuffix;
	}

	/**
	 *  设置  文件后缀
	 */
	public void setObjectSuffix(String objectSuffix){
		this.objectSuffix=objectSuffix;
	}


	/**
	 *  返回  存放位置
	 */
	public String getObjectSavePath(){
		return objectSavePath;
	}

	/**
	 *  设置  存放位置
	 */
	public void setObjectSavePath(String objectSavePath){
		this.objectSavePath=objectSavePath;
	}


	/**
	 *  返回  关键字
	 */
	public String getKeyWords(){
		return keyWords;
	}

	/**
	 *  设置  关键字
	 */
	public void setKeyWords(String keyWords){
		this.keyWords=keyWords;
	}


	/**
	 *  返回  说明
	 */
	public String getexplains(){
		return explains;
	}

	/**
	 *  设置  说明
	 */
	public void setexplains(String explains){
		this.explains=explains;
	}


	/**
	 *  返回  备注
	 */
	public String getRemark(){
		return remark;
	}

	/**
	 *  设置  备注
	 */
	public void setRemark(String remark){
		this.remark=remark;
	}


	/**
	 *  返回  最后修改人
	 */
	public String getModifyer(){
		return modifyer;
	}

	/**
	 *  设置  最后修改人
	 */
	public void setModifyer(String modifyer){
		this.modifyer=modifyer;
	}


	/**
	 *  返回  最后修改人姓名
	 */
	public String getModifyerName(){
		return modifyerName;
	}

	/**
	 *  设置  最后修改人姓名
	 */
	public void setModifyerName(String modifyerName){
		this.modifyerName=modifyerName;
	}


	/**
	 *  返回  最后修改时间
	 */
	public String getModifyTime(){
		return modifyTime;
	}

	/**
	 *  设置  最后修改时间
	 */
	public void setModifyTime(String modifyTime){
		this.modifyTime=modifyTime;
	}


	/**
	 *  返回  最后修改IP
	 */
	public String getModifyIP(){
		return modifyIP;
	}

	/**
	 *  设置  最后修改IP
	 */
	public void setModifyIP(String modifyIP){
		this.modifyIP=modifyIP;
	}

	public EntityNetdisk(){
	   super();
	}
}
