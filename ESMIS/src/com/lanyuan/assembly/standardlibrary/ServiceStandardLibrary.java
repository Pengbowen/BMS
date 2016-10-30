package com.lanyuan.assembly.standardlibrary;

import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.common.Services;

public interface ServiceStandardLibrary extends Services<SingleStandardLibraryData> {


	/**
	 * 增加"手工入库"的标准<br/><br/>
	 * 伪代码步骤：<br/>
	 * 1.生成standId<br/>
	 * 2.如果存在替换标准信息，获取老标准编号。<br/>
	 * 3.保存标准信息<br/>
	 * 4.修改老标准档案信息<br/>
	 * 
	 * @param clsData
	 * @return
	 */
	public int addByMananl(String standardNo,SingleStandardLibraryHandworkData singleStandardLibraryHandworkData);
	/**
     * 修改"手工入库"的标准<br/><br/
     * @param clsData
     * @return
     */
	public int update(String standardId,SingleStandardLibraryHandworkData singleStandardLibraryHandworkData);
	/**
     * 修改"手工入库"的标准<br/><br/
     * @param clsData
     * @return
     */
	public int update(String standardId,SingleStandardLibraryData single);

	/**
	 * 增加"升级入库"的标准<br/><br/>
	 * 
	 * 1.在原有标准基础上增加升级字段<br/>
	 * @param clsData
	 * @return
	 */
	public int addByUpgrade(SingleStandardLibraryUpdateData singleStandardLibraryUpdateData);
	/**
	 * 通过标准编号删除标准
	 * 
	 * @param standardNo
	 * @return
	 */
	public int deleteByStandardNo(String standardNo);
	/**
	 * 通过标准编号获取标准详情
	 * 
	 * @param standardNo
	 * @return
	 */
	public SingleStandardLibraryData getDataByStandardNo(String standardNo );
	
	/**
	 *按照SSTLoaction查询所属标准<br/><br/>
	 * 
	 * 1.通过体系表位置查出所拥有的标准<br/>
	 * 2.体系表位置格式“，1306/01,1307/01,”<br/>
	 * 3.返回所有标准<br/>
	 * 
	 * @param cond，SStl
	 * @return
	 */
	public List<SingleStandardLibraryData> selectBySSTLoaction(int page, int pagecount,
            ConditionGroup cond, OrderGroup order);
	/**
	 *修改标准所属的体系表位置<br/><br/>
	 * 
	 * 1.体系表位置格式“，1306/01,1307/01,”<br/>
	 * 
	 * @param SStl需要修改的体系表位置
	 * @return
	 */
	public int updateSSTLoaction(String standardId,String SStl);
	/**
	 *取消标准所属的体系表位置<br/><br/>
	 * 
	 * 1.体系表位置格式“，1306/01,1307/01,”<br/>
	 * 
	 * @param SStl需要取消的体系表位置
	 * @return
	 */
	public int deleteSSTLoaction(String standardId,String SStl);
	/**
	 *增加标准所属的体系表位置<br/><br/>
	 * 
	 * 1.体系表位置格式“，1306/01,1307/01,”<br/>
	 * 
	 * @param SStl需要修改的体系表位置
	 * @return
	 */
	public int addSSTLoaction(String standardId,String SStl);

	/**
	 *统计标准总库报表<br/><br/>
	 * @return
	 */
	public List<HashMap<String, String>> searchStatement();

	/**
	 *通过Id批量查询标准<br/><br/>
	 * @return
	 */
	public List<HashMap<String, String>> searchMoreStandard(String str);
	/**
     * 浏览量增加
     * 
     * @param lawsId
     * @return
     */
    public int updateBrowseVolume(String standardId);
	
	
	
	public enum MappingList {
		/**
		 * 标准id
		 */
		standardId(" standardId "),
		/**
		 * 标准编号
		 */
		standardNo(" standardNo "),
		/**
		 * 标准名称
		 */
		standardName(" standardName "),
		/**
		 * 英文名称
		 */
		standardNameEN(" standardNameEN "),
		/**
		 * 主题词
		 */
		subjectWords(" subjectWords "),
		/**
		 * 体系表位置
		 */
		SSTLoaction(" SSTLoaction "),
		/**
		 * 采用国际标准
		 */
		adoptIS(" adoptIS "),
		/**
		 * 采标程度
		 */
		adoptionDegree(" adoptionDegree "),
		/**
		 * 标准类别
		 */
		standardCategory(" standardCategory "),
		/**
		 * 标准类别名称
		 */
		standardCategoryName(" standardCategoryName "),
		/**
		 * 批准/发布单位
		 */
		approvedUnit(" approvedUnit "),
		/**
		 * 批准/发布时间
		 */
		approvedDate(" approvedDate "),
		/**
		 * 实施时间
		 */
		effectiveDate(" effectiveDate "),
		/**
		 * 标准提出部门
		 */
		proposedUnit(" proposedUnit "),
		/**
		 * 标准起草部门
		 */
		draftedUnit(" draftedUnit "),
		/**
		 * 主要起草人
		 */
		majorDrafters(" majorDrafters "),
		/**
		 * 分类号
		 */
		standardClassNo(" standardClassNo "),
		/**
		 * 门类号
		 */
		standardKindNo(" standardKindNo "),
		/**
		 * 专业
		 */
		applicableMajor(" applicableMajor "),
		/**
		 * 新业务Id
		 */
		newStandardId(" newStandardId "),
		/**
		 * 新标准编号
		 */
		newStandardNo(" newStandardNo "),
		/**
		 * 旧业务Id
		 */
		oldStandardId(" oldStandardId "),
		/**
		 * 旧标准编号
		 */
		oldStandardNo(" oldStandardNo "),
		/**
		 * 说明
		 */
		explaintd(" explaintd "),
		/**
		 * 备注
		 */
		remark(" remark "),
		/**
		 * 标准有效状态
		 */
		effectiveState(" effectiveState "),
		/**
		 * 升级版本号
		 */
		upgradeVersion(" upgradeVersion "),
		/**
		 * 升级用户
		 */
		upgradeUser(" upgradeUser "),
		/**
		 * 升级时间
		 */
		upgradeDate(" upgradeDate "),
		/**
		 * 数据来源
		 */
		dataSource(" dataSource "),
		/**
		 * 文件存放位置
		 */
		filePath(" filePath "),
		/**
		 * 标准分享人
		 */
		sharePeople(" sharePeople "),
		/**
		 * 浏览量
		 */
		browseVolume(" browseVolume "),
		/**
		 * 创建人
		 */
		creator(" creator "),
		/**
		 * 创建人姓名
		 */
		creatorName(" creatorName "),
		/**
		 * 创建时间
		 */
		createTime(" createTime "),
		/**
		 * 创建IP
		 */
		createIP(" createIP "),
		/**
		 * 最后修改人
		 */
		modifyer(" modifyer "),
		/**
		 * 最后修改人姓名
		 */
		modifyerName(" modifyerName "),
		/**
		 * 最后修改时间
		 */
		modifyTime(" modifyTime "),
		/**
		 * 最后修改时间
		 */
		documentType(" documentType "),
		/**
		 * 最后修改IP
		 */
		modifyIP(" modifyIP ");
		private String value;

		MappingList(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
