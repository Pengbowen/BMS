package com.lanyuan.assembly.lawslibrary;
public class EntityLaws{
	/**
	 * 法规id
	 */
	private String standardId;
	/**
	 * 法规文号
	 */
	private String standardNo;
	/**
	 * 法规名称
	 */
	private String standardName;
	/**
	 * 法规类别
	 */
	private String standardCategory;
	/**
	 * 类别名称
	 */
	private String standardCategoryName;
	/**
	 * 关键字
	 */
	private String subjectWords;
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
     * 旧Id
     */
    private String oldStandardId;
    /**
     * 旧法规编号
     */
    private String oldStandardNo;
    /**
     * 内容
     */
    private String content;
    /**
     * 文档类型
     */
    private String documentType;
	/**
	 * 说明
	 */
	private String explaintd;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 数据来源
	 */
	private Integer dataSource;
	/**
	 * 文件存放位置
	 */
	private String filePath;
	/**
	 * 浏览量
	 */
	private Integer browseVolume;
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
	 * 作废标记
	 */
	private Integer effectiveState;
	
	/**
     * @return 返回 effectiveState
     */
    public Integer getEffectiveState()
    {
        return effectiveState;
    }

    /**
     * @param effectiveState 设置
     */
    public void setEffectiveState(Integer effectiveState)
    {
        this.effectiveState = effectiveState;
    }

    /**
	 *  返回  说明
	 */
	public String getExplaintd(){
		return explaintd;
	}

	/**
	 *  设置  说明
	 */
	public void setExplaintd(String explaintd){
		this.explaintd=explaintd;
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
	 *  返回  数据来源
	 */
	public Integer getDataSource(){
		return dataSource;
	}

	/**
	 *  设置  数据来源
	 */
	public void setDataSource(Integer dataSource){
		this.dataSource=dataSource;
	}


	/**
	 *  返回  文件存放位置
	 */
	public String getFilePath(){
		return filePath;
	}

	/**
	 *  设置  文件存放位置
	 */
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}


	/**
	 *  返回  浏览量
	 */
	public Integer getBrowseVolume(){
		return browseVolume;
	}

	/**
	 *  设置  浏览量
	 */
	public void setBrowseVolume(Integer browseVolume){
		this.browseVolume=browseVolume;
	}


	/**
	 *  返回  创建人
	 */
	public String getCreator(){
		return creator;
	}

	/**
	 *  设置  创建人
	 */
	public void setCreator(String creator){
		this.creator=creator;
	}


	/**
	 *  返回  创建人姓名
	 */
	public String getCreatorName(){
		return creatorName;
	}

	/**
	 *  设置  创建人姓名
	 */
	public void setCreatorName(String creatorName){
		this.creatorName=creatorName;
	}


	/**
	 *  返回  创建时间
	 */
	public String getCreateTime(){
		return createTime;
	}

	/**
	 *  设置  创建时间
	 */
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}


	/**
	 *  返回  创建IP
	 */
	public String getCreateIP(){
		return createIP;
	}

	/**
	 *  设置  创建IP
	 */
	public void setCreateIP(String createIP){
		this.createIP=createIP;
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
    /**
     *  返回  升级版本号
     */
    public String getUpgradeVersion(){
        return upgradeVersion;
    }

    /**
     *  设置  升级版本号
     */
    public void setUpgradeVersion(String upgradeVersion){
        this.upgradeVersion=upgradeVersion;
    }


    /**
     *  返回  升级用户
     */
    public String getUpgradeUser(){
        return upgradeUser;
    }

    /**
     *  设置  升级用户
     */
    public void setUpgradeUser(String upgradeUser){
        this.upgradeUser=upgradeUser;
    }


    /**
     *  返回  升级时间
     */
    public String getUpgradeDate(){
        return upgradeDate;
    }

    /**
     *  设置  升级时间
     */
    public void setUpgradeDate(String upgradeDate){
        this.upgradeDate=upgradeDate;
    }


	public EntityLaws(){
	   super();
	}

    /**
     * @return 返回 standardId
     */
    public String getStandardId()
    {
        return standardId;
    }

    /**
     * @return 返回 standardNo
     */
    public String getStandardNo()
    {
        return standardNo;
    }

    /**
     * @return 返回 standardName
     */
    public String getStandardName()
    {
        return standardName;
    }

    /**
     * @return 返回 standardCategory
     */
    public String getStandardCategory()
    {
        return standardCategory;
    }

    /**
     * @return 返回 standardCategoryName
     */
    public String getStandardCategoryName()
    {
        return standardCategoryName;
    }

    /**
     * @return 返回 subjectWords
     */
    public String getSubjectWords()
    {
        return subjectWords;
    }

    /**
     * @return 返回 approvedUnit
     */
    public String getApprovedUnit()
    {
        return approvedUnit;
    }

    /**
     * @return 返回 approvedDate
     */
    public String getApprovedDate()
    {
        return approvedDate;
    }

    /**
     * @return 返回 effectiveDate
     */
    public String getEffectiveDate()
    {
        return effectiveDate;
    }

    /**
     * @return 返回 proposedUnit
     */
    public String getProposedUnit()
    {
        return proposedUnit;
    }

    /**
     * @return 返回 draftedUnit
     */
    public String getDraftedUnit()
    {
        return draftedUnit;
    }

    /**
     * @return 返回 majorDrafters
     */
    public String getMajorDrafters()
    {
        return majorDrafters;
    }

    /**
     * @return 返回 oldStandardId
     */
    public String getOldStandardId()
    {
        return oldStandardId;
    }

    /**
     * @return 返回 oldStandardNo
     */
    public String getOldStandardNo()
    {
        return oldStandardNo;
    }

    /**
     * @return 返回 content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @return 返回 documentType
     */
    public String getDocumentType()
    {
        return documentType;
    }

    /**
     * @param standardId 设置
     */
    public void setStandardId(String standardId)
    {
        this.standardId = standardId;
    }

    /**
     * @param standardNo 设置
     */
    public void setStandardNo(String standardNo)
    {
        this.standardNo = standardNo;
    }

    /**
     * @param standardName 设置
     */
    public void setStandardName(String standardName)
    {
        this.standardName = standardName;
    }

    /**
     * @param standardCategory 设置
     */
    public void setStandardCategory(String standardCategory)
    {
        this.standardCategory = standardCategory;
    }

    /**
     * @param standardCategoryName 设置
     */
    public void setStandardCategoryName(String standardCategoryName)
    {
        this.standardCategoryName = standardCategoryName;
    }

    /**
     * @param subjectWords 设置
     */
    public void setSubjectWords(String subjectWords)
    {
        this.subjectWords = subjectWords;
    }

    /**
     * @param approvedUnit 设置
     */
    public void setApprovedUnit(String approvedUnit)
    {
        this.approvedUnit = approvedUnit;
    }

    /**
     * @param approvedDate 设置
     */
    public void setApprovedDate(String approvedDate)
    {
        this.approvedDate = approvedDate;
    }

    /**
     * @param effectiveDate 设置
     */
    public void setEffectiveDate(String effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @param proposedUnit 设置
     */
    public void setProposedUnit(String proposedUnit)
    {
        this.proposedUnit = proposedUnit;
    }

    /**
     * @param draftedUnit 设置
     */
    public void setDraftedUnit(String draftedUnit)
    {
        this.draftedUnit = draftedUnit;
    }

    /**
     * @param majorDrafters 设置
     */
    public void setMajorDrafters(String majorDrafters)
    {
        this.majorDrafters = majorDrafters;
    }

    /**
     * @param oldStandardId 设置
     */
    public void setOldStandardId(String oldStandardId)
    {
        this.oldStandardId = oldStandardId;
    }

    /**
     * @param oldStandardNo 设置
     */
    public void setOldStandardNo(String oldStandardNo)
    {
        this.oldStandardNo = oldStandardNo;
    }

    /**
     * @param content 设置
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @param documentType 设置
     */
    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }
}
