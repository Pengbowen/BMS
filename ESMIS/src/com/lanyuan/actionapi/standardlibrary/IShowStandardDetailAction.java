package com.lanyuan.actionapi.standardlibrary;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description: 显示标准详情
 * @author
 * @date 2016-8-25 下午2:00:07
 */
public class IShowStandardDetailAction extends ActionSupport
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
     * 替代标准
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
     * 批准部门
     */
    private String approvedUnit;
    /**
     * 批准时间
     */
    private String approvedDate;
    /**
     * 标准提出部门
     */
    private String proposedUnit;
    /**
     * 实施时间
     */
    private String effectiveDate;
    /**
     * 标准起草部门
     */
    private String draftedUnit;
    /**
     * 主要起草人
     */
    private String majorDrafters;
    /**
     * 所属专业
     */
    private String applicableMajor;
    /**
     * 标准分享人
     */
    private String sharePeople;
    /**
     * 标准有效状态
     */
    private String effectiveState;
    /**
     * 浏览量
     */
    private Integer browseVolume;
    /**
     * 文件存放位置
     */
    private String filePath;
    /**
     * 最后修改人姓名
     */
    private String modifyerName;
   
	/**
     * 备注
     */
    private String remark;
    /**
     * 数据来源
     */
    private String dataSource;
	/**
	 * 体系表位置
	 */
	private String SSTLoaction;
	
	public String showLawsDetail()
    {
        if (!StringUtils.isBlank(standardNo) || !StringUtils.isBlank(standardId))
        {
        	SingleStandardLibraryData singData = new SingleStandardLibraryData();
            // 如果lawsId为空，则根据编号查询详情
            if (StringUtils.isBlank(standardId))
            {
                 singData= standardLibraryService.getDataByStandardNo(standardNo);
            }
            else
            {
                // 如果lawId不为空，则根据lawId查询详情
            	singData = standardLibraryService.selectById(standardId);
            }
            if (singData!= null)
            {
                setInfoData(singData);
                browseVolume=singData.getBrowseVolume();
                if(browseVolume==null){
                	browseVolume=0;
                }
                singData.setBrowseVolume(browseVolume+1);
                standardLibraryService.update(standardId,singData);
            }
        }
        return Action.SUCCESS;
    }
    public void setInfoData(SingleStandardLibraryData single)
    {
    	String applicableMajor=single.getApplicableMajor();
    	ServiceDictionaryData service = new ServiceDictionaryData();
    	 applicableMajor = service.selectByCode(applicableMajor);
        Integer dataSourceNo=single.getDataSource();
        String dataSourceName=null;
        int eff=single.getEffectiveState();
        String state=null;
        if(dataSourceNo==null){
        	dataSourceNo=1;
        }
        if(dataSourceNo==0)
        {
            dataSourceName="升级入库";
        }
        if(dataSourceNo==1)
        {
            dataSourceName="手工添加";
        }
        if(eff==0)
        {
        	state="作废";
        }
        if(eff==1)
        {
        	state="有效";
        }
        this.standardId = single.getStandardId();
        this.standardCategory = single.getStandardCategoryName();
        this.oldStandardNo = single.getOldStandardNo();
        this.standardName = single.getStandardName();
        this.standardNameEN = single.getStandardNameEN();
        this.subjectWords = single.getSubjectWords();
        this.adoptionDegree = single.getAdoptionDegree();
        this.adoptIS = single.getAdoptIS();
        this.dataSource=dataSourceName;
        this.SSTLoaction = single.getSSTLoaction();
        this.standardClassNo = single.getStandardClassNo();
        this.standardKindNo = single.getStandardKindNo();
        this.approvedUnit = single.getApprovedUnit();
        this.approvedDate = single.getApprovedDate();
        this.proposedUnit =single.getProposedUnit();
        this.effectiveDate = single.getEffectiveDate();
        this.draftedUnit = single.getDraftedUnit();
        this.majorDrafters = single.getMajorDrafters();
        this.applicableMajor = applicableMajor;
        this.sharePeople=single.getSharePeople();
        this.effectiveState=state;
        this.browseVolume=single.getBrowseVolume();
        this.filePath=single.getFilePath();
        this.modifyerName=single.getModifyerName();
        this.remark=single.getRemark();
     }
    
    
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStandardCategory() {
		return standardCategory;
	}
	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
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
	public String getProposedUnit() {
		return proposedUnit;
	}
	public void setProposedUnit(String proposedUnit) {
		this.proposedUnit = proposedUnit;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public String getApplicableMajor() {
		return applicableMajor;
	}
	public void setApplicableMajor(String applicableMajor) {
		this.applicableMajor = applicableMajor;
	}
	public String getSharePeople() {
		return sharePeople;
	}
	public void setSharePeople(String sharePeople) {
		this.sharePeople = sharePeople;
	}
	public String getEffectiveState() {
		return effectiveState;
	}
	public void setEffectiveState(String effectiveState) {
		this.effectiveState = effectiveState;
	}
	public Integer getBrowseVolume() {
		return browseVolume;
	}
	public void setBrowseVolume(Integer browseVolume) {
		this.browseVolume = browseVolume;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getModifyerName() {
		return modifyerName;
	}
	public void setModifyerName(String modifyerName) {
		this.modifyerName = modifyerName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStandardNo() {
			return standardNo;
	}
	public void setStandardNo(String standardNo) {
			this.standardNo = standardNo;
	}
	   public String getDataSource() {
			return dataSource;
		}
		public void setDataSource(String dataSource) {
			this.dataSource = dataSource;
		}
		public String getSSTLoaction() {
			return SSTLoaction;
		}
		public void setSSTLoaction(String sSTLoaction) {
			SSTLoaction = sSTLoaction;
		}
}