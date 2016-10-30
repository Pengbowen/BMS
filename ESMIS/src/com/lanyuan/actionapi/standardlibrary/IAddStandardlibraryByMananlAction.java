package com.lanyuan.actionapi.standardlibrary;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryHandworkData;
import com.lanyuan.assembly.util.LawAndStandardUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;
/**
 * Description: 手工入库标准实现接口
 * @author afl
 *
 */

public class IAddStandardlibraryByMananlAction extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	 @Autowired
	 private ServiceStandardLibrary standardLibraryService;
	
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

		public void addStandard(){
			  if(StringUtils.isBlank(standardNo)){
				  this.printString("2", "标准编号为空！");
		            return ;
			  }
			  if(standardNo.equals(standardLibraryService.getDataByStandardNo(standardNo))){
				  this.printString("2", "标准编号已存在！");
		            return ;
			  }
			  if(StringUtils.isBlank(standardCategory)){
				  this.printString("2", "标准类别为空！");
		            return ;
			  }
			  if(StringUtils.isBlank(standardName)){
				  this.printString("2", "标准名称为空！");
		            return ;
			  }
			  if(StringUtils.isBlank(approvedUnit)){
				  this.printString("2", "标准批准部门为空！");
		            return ;
			  }
			  if(StringUtils.isBlank(approvedUnit)){
				  this.printString("2", "标准提出部门为空！");
		            return ;
			  }
			  if(StringUtils.isBlank(approvedDate)){
				  this.printString("2", "标准批准时间为空！");
		            return ;
			  }
			  if(StringUtils.isBlank(effectiveDate)){
				  this.printString("2", "标准实施时间为空！");
		            return ;
			  }
			  if (dataSource==null)
		        {
		            this.printString("2", "数据来源为空！");
		            return ;
		        }
			 HttpServletRequest request = ServletActionContext.getRequest();
			 LoginUser user = null;
		        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
		        {
		            if (user == null){
		            	printString("-4", "非法访问！");
		            return;
		            }
		        }
		        
			  SingleStandardLibraryHandworkData single= new SingleStandardLibraryHandworkData();
			   ServiceDictionaryData service = new ServiceDictionaryData();
			   
			   String standardCategoryName = service.selectByCode(standardCategory);
			   
			   // 获取当前操作人信息
		        OperatorInfo operater = this.getCurrentOperator();
		        /* 创建人 */
		        String creator = operater.getOperator();
		        /* 创建人姓名 */
		        String creatorName = operater.getOperatorName();
		        /* 创建IP */
		        String createIP = operater.getOperateIp();
		        /* 创建时间 */
		        String createTime = operater.getOperateTime("yyyy-MM-dd HH:mm:ss");
		        /*查询标准类别名字*/
		       if ("".equals(effectiveState)||effectiveState==null) {
		    	   effectiveState=1;
			} 
		       
		        single.setStandardNo(standardNo);
		        single.setStandardCategory(standardCategory);
		        single.setStandardCategoryName(standardCategoryName);
		        single.setOldStandardNo(oldStandardNo);
		        single.setStandardName(standardName);
		        single.setStandardNameEN(standardNameEN);
		        single.setSubjectWords(subjectWords);
		        single.setAdoptionDegree(adoptionDegree);
		        single.setAdoptIS(adoptIS);
		        single.setApprovedUnit(approvedUnit);
		        single.setProposedUnit(proposedUnit); 
		        single.setDraftedUnit(draftedUnit);
		        single.setMajorDrafters(majorDrafters);
		        single.setApplicableMajor(applicableMajor); 
		        single.setSharePeople(sharePeople);
		        single.setStandardClassNo(standardClassNo);
		        single.setStandardKindNo(standardKindNo);
		        single.setApprovedDate(approvedDate);
		        single.setEffectiveDate(effectiveDate);
		        single.setFilePath(filePath);
		        single.setEffectiveState(effectiveState);
		        single.setCreator(creator);
		        single.setCreatorName(creatorName);
		        single.setCreateTime(createTime);
		        single.setCreateIP(createIP);
		        single.setDataSource(dataSource);
		        int data = standardLibraryService.addByMananl(standardNo,single);
		        if (data > 0)
		        {
		            this.printString("1", "添加成功！");
		        }
		        if (data==-1) {
		        	 this.printString("-1", "标准编号已存在！");
		        }
		        else
		        {
		            this.printString("2", "添加失败！");
		        }
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
}
