package com.lanyuan.assembly.sst.standard;


/**
 * 体系表-标准实体类
 * @author PBW
 * @date 2016年8月23日14:22:36
 */
public class EntitySSTStandardList {
	
	

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
	 * 主题词
	 */
	private String subjectWords;
	/**
	 * 体系表位置
	 */
	private String SSTLoaction;
	/**
	 * 采用国际标准
	 */
	private String adoptIS;
	/**
	 * 采标程度
	 */
	private String adoptionDegree;
	/**
	 * 标准类别
	 */
	private String standardCategory;
	/**
	 * 标准类别名称
	 */
	private String standardCategoryName;
	/**
	 * 批准/发布单位
	 */
	private String approvedUnit;
	/**
	 * 批准/发布时间
	 */
	private String approvedDate;
	/**
	 * 实施时间
	 */
	private String effectiveDate;
	/**
	 * 标准提出部门
	 */
	private String proposedUnit;
	/**
	 * 标准起草部门
	 */
	private String draftedUnit;
	/**
	 * 主要起草人
	 */
	private String majorDrafters;
	/**
	 * 分类号
	 */
	private String standardClassNo;
	/**
	 * 门类号
	 */
	private String standardKindNo;
	/**
	 * 专业
	 */
	private String applicableMajor;
	/**
	 * 新业务Id
	 */
	private String newStandardId;
	/**
	 * 新标准编号
	 */
	private String newStandardNo;
	/**
	 * 旧业务Id
	 */
	private String oldStandardId;
	/**
	 * 旧标准编号
	 */
	private String oldStandardNo;
	/**
	 * 说明
	 */
	private String explaintd;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 标准有效状态
	 */
	private Integer effectiveState;
	/**
	 * 升级版本号
	 */
	private String upgradeVersion;
	/**
	 * 升级用户
	 */
	private String upgradeUser;
	/**
	 * 升级时间
	 */
	private String upgradeDate;
	/**
	 * 数据来源
	 */
	private Integer dataSource;
	/**
	 * 文件存放位置
	 */
	private String filePath;
	/**
	 * 标准分享人
	 */
	private String sharePeople;
	/**
	 * 浏览量
	 */
	private Integer browseVolume;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建人姓名
	 */
	private String creatorName;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建IP
	 */
	private String createIP;
	/**
	 * 最后修改人
	 */

	/**
	 * 所属体系表
	 */
	private Integer sSTId;
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
	 * 标准id
	 */
	private String standardId;
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
	 * 文件类型 1-标准 2-法律法规
	 */
	private String documentType;

	/**
	 *  返回  所属体系表
	 */
	public Integer getSSTId(){
		return sSTId;
	}

	/**
	 *  设置  所属体系表
	 */
	public void setSSTId(Integer sSTId){
		this.sSTId=sSTId;
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
	 *  返回  标准id
	 */
	public String getStandardId(){
		return standardId;
	}

	/**
	 *  设置  标准id
	 */
	public void setStandardId(String standardId){
		this.standardId=standardId;
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

	public String getSubjectWords() {
		return subjectWords;
	}

	public void setSubjectWords(String subjectWords) {
		this.subjectWords = subjectWords;
	}

	public String getSSTLoaction() {
		return SSTLoaction;
	}

	public void setSSTLoaction(String sSTLoaction) {
		SSTLoaction = sSTLoaction;
	}

	public String getAdoptIS() {
		return adoptIS;
	}

	public void setAdoptIS(String adoptIS) {
		this.adoptIS = adoptIS;
	}

	public String getAdoptionDegree() {
		return adoptionDegree;
	}

	public void setAdoptionDegree(String adoptionDegree) {
		this.adoptionDegree = adoptionDegree;
	}

	public String getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}

	public String getStandardCategoryName() {
		return standardCategoryName;
	}

	public void setStandardCategoryName(String standardCategoryName) {
		this.standardCategoryName = standardCategoryName;
	}

	public String getApprovedUnit() {
		return approvedUnit;
	}

	public void setApprovedUnit(String approvedUnit) {
		this.approvedUnit = approvedUnit;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getProposedUnit() {
		return proposedUnit;
	}

	public void setProposedUnit(String proposedUnit) {
		this.proposedUnit = proposedUnit;
	}

	public String getDraftedUnit() {
		return draftedUnit;
	}

	public void setDraftedUnit(String draftedUnit) {
		this.draftedUnit = draftedUnit;
	}

	public String getMajorDrafters() {
		return majorDrafters;
	}

	public void setMajorDrafters(String majorDrafters) {
		this.majorDrafters = majorDrafters;
	}

	public String getStandardClassNo() {
		return standardClassNo;
	}

	public void setStandardClassNo(String standardClassNo) {
		this.standardClassNo = standardClassNo;
	}

	public String getStandardKindNo() {
		return standardKindNo;
	}

	public void setStandardKindNo(String standardKindNo) {
		this.standardKindNo = standardKindNo;
	}

	public String getApplicableMajor() {
		return applicableMajor;
	}

	public void setApplicableMajor(String applicableMajor) {
		this.applicableMajor = applicableMajor;
	}

	public String getNewStandardId() {
		return newStandardId;
	}

	public void setNewStandardId(String newStandardId) {
		this.newStandardId = newStandardId;
	}

	public String getNewStandardNo() {
		return newStandardNo;
	}

	public void setNewStandardNo(String newStandardNo) {
		this.newStandardNo = newStandardNo;
	}

	public String getOldStandardId() {
		return oldStandardId;
	}

	public void setOldStandardId(String oldStandardId) {
		this.oldStandardId = oldStandardId;
	}

	public String getOldStandardNo() {
		return oldStandardNo;
	}

	public void setOldStandardNo(String oldStandardNo) {
		this.oldStandardNo = oldStandardNo;
	}

	public String getExplaintd() {
		return explaintd;
	}

	public void setExplaintd(String explaintd) {
		this.explaintd = explaintd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEffectiveState() {
		return effectiveState;
	}

	public void setEffectiveState(Integer effectiveState) {
		this.effectiveState = effectiveState;
	}

	public String getUpgradeVersion() {
		return upgradeVersion;
	}

	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}

	public String getUpgradeUser() {
		return upgradeUser;
	}

	public void setUpgradeUser(String upgradeUser) {
		this.upgradeUser = upgradeUser;
	}

	public String getUpgradeDate() {
		return upgradeDate;
	}

	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}

	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSharePeople() {
		return sharePeople;
	}

	public void setSharePeople(String sharePeople) {
		this.sharePeople = sharePeople;
	}

	public Integer getBrowseVolume() {
		return browseVolume;
	}

	public void setBrowseVolume(Integer browseVolume) {
		this.browseVolume = browseVolume;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateIP() {
		return createIP;
	}

	public void setCreateIP(String createIP) {
		this.createIP = createIP;
	}

	public Integer getsSTId() {
		return sSTId;
	}

	public void setsSTId(Integer sSTId) {
		this.sSTId = sSTId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	
	
}
