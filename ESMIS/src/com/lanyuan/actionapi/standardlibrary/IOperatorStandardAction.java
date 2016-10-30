package com.lanyuan.actionapi.standardlibrary;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.opensymphony.xwork2.Action;

/**
 * 
 * @Description: 手动新增，修改法规的操作接口
 * @author
 * @date 2016-8-25 下午2:31:13
 */
public class IOperatorStandardAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;
    /**
	 * 标准Id
	 */
	private String standardId;
		
	
		/**
		 * 标准编号
		 */
		private String standardNo;
		/**
		 * 标准类别
		 */
		private String standardCategory;
		/**
		 * 旧业务Id
		 */
		private String oldStandardId;
		/**
		 * 旧标准编号
		 */
		private String oldStandardNo;
		
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
		 * 采标程度
		 */
		private String adoptionDegree;
		/**
		 * 采用国际标准
		 */
		private String adoptIS;
		/**
		 * 分类号
		 */
		private String standardClassNo;
		/**
		 * 门类号
		 */
		private String standardKindNo;
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
		 * 主要起草人
		 */
		private String majorDrafters;
		/**
		 * 标准起草部门
		 */
		private String draftedUnit;
		/**
		 * 专业
		 */
		private String applicableMajor;
		/**
		 * 标准有效状态
		 */
		private Integer effectiveState;
		/**
		 * 文件存放位置
		 */
		private String filePath;
		/**
		 * 标准分享人
		 */
		private String sharePeople;
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
	     * 数据来源
	     */
	    private Integer dataSource;
	

    public String operatorStand() 
    {
        if (!StringUtils.isBlank(standardId))
        {
        	//System.out.println(standardId);
        	 SingleStandardLibraryData singleLaws = new SingleStandardLibraryData();
        	 
             singleLaws = standardLibraryService.selectById(standardId);
            if (singleLaws != null)
            {
                setInfoData(singleLaws);
            }
        }
        return Action.SUCCESS;
    }

    public void setInfoData(SingleStandardLibraryData single)
    {
        this.standardId = single.getStandardId();
        this.standardNo = single.getStandardNo();
        this.standardCategory = single.getStandardCategory();
        this.oldStandardNo= single.getOldStandardNo();
        this.standardName = single.getStandardName();
        this.standardNameEN = single.getStandardNameEN();
        this.subjectWords = single.getSubjectWords();
        this.adoptionDegree = single.getAdoptionDegree();
        this.adoptIS = single.getAdoptIS();
        this.standardClassNo = single.getStandardClassNo();
        this.standardKindNo = single.getStandardKindNo();
        this.approvedUnit = single.getApprovedUnit();
        this.approvedDate = single.getApprovedDate();
        this.effectiveDate = single.getEffectiveDate();
        this.proposedUnit = single.getProposedUnit();
        this.majorDrafters = single.getMajorDrafters();
        this.draftedUnit = single.getDraftedUnit();
        this.applicableMajor = single.getApplicableMajor();
        this.effectiveState = single.getEffectiveState();
        this.filePath = single.getFilePath();
        this.sharePeople = single.getSharePeople();
        this.creator = single.getCreator();
        this.creatorName = single.getCreatorName();
        this.createTime = single.getCreateTime();
        this.createIP = single.getCreateIP();
        this.dataSource = single.getDataSource();
    }
    public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	public String getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
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

	public String getAdoptionDegree() {
		return adoptionDegree;
	}

	public void setAdoptionDegree(String adoptionDegree) {
		this.adoptionDegree = adoptionDegree;
	}

	public String getAdoptIS() {
		return adoptIS;
	}

	public void setAdoptIS(String adoptIS) {
		this.adoptIS = adoptIS;
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

	public String getMajorDrafters() {
		return majorDrafters;
	}

	public void setMajorDrafters(String majorDrafters) {
		this.majorDrafters = majorDrafters;
	}

	public String getDraftedUnit() {
		return draftedUnit;
	}

	public void setDraftedUnit(String draftedUnit) {
		this.draftedUnit = draftedUnit;
	}

	public String getApplicableMajor() {
		return applicableMajor;
	}

	public void setApplicableMajor(String applicableMajor) {
		this.applicableMajor = applicableMajor;
	}

	public Integer getEffectiveState() {
		return effectiveState;
	}

	public void setEffectiveState(Integer effectiveState) {
		this.effectiveState = effectiveState;
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

	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}
	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

}
