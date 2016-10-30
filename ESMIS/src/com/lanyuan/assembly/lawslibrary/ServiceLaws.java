package com.lanyuan.assembly.lawslibrary;

import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.common.Services;


public interface ServiceLaws extends Services<SingleLaws>
{
    /**
     * 浏览量增加
     * 
     * @param lawsId
     * @return
     */
    public int updateBrowseVolume(String lawsId);
    
    /**
     * 作废
     * 
     * @param lawsId
     * @return
     */
    public int cancellation(String lawsId);
    
    /**
     * 通过法规编号获取标准详情
     * 
     * @param lawsNo
     * @return 查询结果
     */
    //public SingleLaws getDataByLawsNo(String lawsNo);
    /**
     * 通过法规编号删除标准
     * 
     * @param lawsNo
     * @return
     */
    //public int deleteByLawsNo(String lawsNo);
    
    /**
     * 获取最大lawsId
     * 
     * @return
     */
    public String getMaxLawsId();
    /**
     * 获取统计法律法规类别数量
     * 
     * @return
     */
    public List<HashMap<String, String>> selectLawsStatement();
    
        public enum MappingList
	    {
	        /**
	         * 法规id
	         */
            standardId(" standardId "),
	        /**
	         * 法规文号
	         */
            standardNo(" standardNo "),
	        /**
	         * 法规名称
	         */
            standardName(" standardName "),
	        /**
	         * 法规类别
	         */
            standardCategory(" standardCategory "),
            /**
             * 类别名称
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
	         * 关键字
	         */
	        subjectWords(" subjectWords "),
	        /**
	         * 说明
	         */
	        explains(" explains ") ,
	        /**
	         * 备注
	         */
	        remark(" remark ") ,
	        /**
	         * 数据来源
	         */
	        dataSource(" dataSource ") ,
	        /**
	         * 文件存放位置
	         */
	        filePath(" filePath ") ,
	        /**
	         * 浏览量
	         */
	        browseVolume(" browseVolume ") ,
	        /**
             * 升级版本号
             */
            upgradeVersion(" upgradeVersion ") ,
            /**
             * 升级用户
             */
            upgradeUser(" upgradeUser ") ,
            /**
             * 升级时间
             */
            upgradeDate(" upgradeDate ") ,
            /**
             * 旧业务Id
             */
            oldStandardId(" oldStandardId "),
            /**
             * 旧标准编号
             */
            oldStandardNo(" oldStandardNo "),
	        /**
	         * 创建人
	         */
	        creator(" creator ") ,
	        /**
	         * 创建人姓名
	         */
	        creatorName(" creatorName ") ,
	        /**
	         * 创建时间
	         */
	        createTime(" createTime ") ,
	        /**
	         * 创建IP
	         */
	        createIP(" createIP ") ,
	        /**
	         * 最后修改人
	         */
	        modifyer(" modifyer ") ,
	        /**
	         * 最后修改人姓名
	         */
	        modifyerName(" modifyerName ") ,
	        /**
	         * 最后修改时间
	         */
	        modifyTime(" modifyTime ") ,
	        /**
	         * 最后修改IP
	         */
	        modifyIP(" modifyIP ") ,
            /**
             * 内容
             */
            content(" content "),
            /**
             * 作废标记
             */
            effectiveState(" effectiveState "),
            /**
             * 文档类型
             */
            documentType(" documentType ");
	        private String value;
	
	        MappingList(String value)
	        {
	            this.value = value;
	        }
	
	        public String getValue()
	        {
	            return this.value;
	        }
	    }
    }

