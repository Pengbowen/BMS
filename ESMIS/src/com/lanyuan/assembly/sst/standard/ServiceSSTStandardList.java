package com.lanyuan.assembly.sst.standard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.baseclasses.ResultMessage;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.common.Services;
import com.lanyuan.assembly.sst.SingleStandardCensus;

/**
 * 体系表-标准业务接口
 * @author PBW
 * @date 2016年8月23日14:22:36
 */
public interface ServiceSSTStandardList extends Services<SingleSSTStandardList>
{
    
        public enum MappingList
	    {
	        /**
	         * 所属体系表
	         */
	        SSTId(" lysoft_sst_standardlist.SSTId ") ,
	        /**
	         * 所属层级
	         */
	        layerNo(" lysoft_sst_standardlist.layerNo ") ,
	        /**
	         * 所属层项目
	         */
	        layerItemId(" lysoft_sst_standardlist.layerItemId ") ,
	        /**
	         * 子分类编号
	         */
	        subClassId(" lysoft_sst_standardlist.subClassId ") ,
	        /**
	         * 标准id
	         */
	        standardId(" lysoft_sst_standardlist.standardId ") ,
	        /**
	         * 显示顺序
	         */
	        displayOrder(" lysoft_sst_standardlist.displayOrder ") ,
	        /**
	         * 修改人
	         */
	        modifyer(" lysoft_sst_standardlist.modifyer ") ,
	        /**
	         * 修改人姓名
	         */
	        modifyerName(" lysoft_sst_standardlist.modifyerName ") ,
	        /**
	         * 修改时间
	         */
	        modifyTime(" lysoft_sst_standardlist.modifyTime ") ,
	        /**
	         * 修改IP
	         */
	        modifyIP(" lysoft_sst_standardlist.modifyIP "),
	        /**
	         * 标准类别
	         */
	        standardCategory("lysoft_sst_standardlist.standardCategory ") ,
	        /**
	         * 旧标准编号
	         */
	        oldStandardNo(" lysoft_standardlibrary.oldStandardNo ") ,
	        /**
	         * 标准编号
	         */
	        standardNo("lysoft_sst_standardlist.standardNo ") ,
	        /*
	         *文档类型
	         */
	        documentType(" lysoft_standardlibrary.documentType "),
	        /**
	         * 采用国际标准
	         */
	        adoptIS(" lysoft_standardlibrary.adoptIS ") ,
	        /**
	         * 标准名称
	         */
	        standardName(" lysoft_standardlibrary.standardName "), 
	        /**
	         * 有效状态
	         */
	        effectiveState(" lysoft_standardlibrary.effectiveState "), 
	        ;
	        
	        
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
    
        public Map<Integer,List<EntitySSTStandardList>> selectStandardBySSTId(String SSTId);
        /**
         * 根据子分类编号查询标准
         * @param subClassId
         * @return
         */
        public List<SingleSSTStandardList> selectStandardBySubClassId(String subClassId);
        /**
         * 根据项目编号查询标准
         * @param subClassId
         * @return
         */
        public List<SingleSSTStandardList> selectByLayerItemId(String layerItemId);
     
        /**
         * 根据项目id查询最大显示顺序
         */
        public int selectMaxDisplayOrderByItemId(String layerItemId);
        /**
         * 根据子分类id查询最大显示顺序
         */
        public int selectMaxDisplayOrderBySubClassId(String subClassId);
        
        /**
         * 综合统计
         */
        public List<HashMap<String,String>> totalCount(int SSTId);
        /**
         * 获取最大显示顺序
         */
        public int getMaxDisplayOrder(String layerItemId);
        /**
         *获取最大显示顺序
         */
        public int getMaxDisplayOrderBySubClassId(String subClassId);
        /**
         * 设置标准的显示顺序
         */
        public int setDisplayOrder(String standardId,int displayOrder);
        /**
         * 判断某个标准在某层项目下是否存在
         */
        public boolean isExistInLayerItem(String standardId,String layerItemId);
        /**
         * 判断某个标准在某子分类下是否存在
         */
        public boolean isExistInSubClassId(String standardId,String layerItemId);
        
    }


