package com.lanyuan.assembly.sst.subclassify;
import java.util.List;

import com.lanyuan.assembly.common.Services;

/**
 * 体系表-子分类业务接口
 * @author PBW
 * @date 2016年8月23日14:23:55
 */
public interface ServiceSSTSubClassify extends Services<SingleSSTSubClassify>{
        public enum MappingList
	    {
	        /**
	         * 所属体系表
	         */
	        SSTId(" SSTId ") ,
	        /**
	         * 所属层级
	         */
	        layerNo(" layerNo ") ,
	        /**
	         * 所属层项目
	         */
	        layerItemId(" layerItemId ") ,
	        /**
	         * 子分类编号
	         */
	        subClassId(" subClassId ") ,
	        /**
	         * 子分类名称
	         */
	        subClassName(" subClassName ") ,
	        /**
	         * 包含标准数量
	         */
	        standardCount(" standardCount ") ,
	        /**
	         * 状态
	         */
	        isEnabled(" isEnabled ") ,
	        /**
	         * 显示状态
	         */
	        isVisible(" isVisible ") ,
	        /**
	         * 显示顺序
	         */
	        displayOrder(" displayOrder ") ,
	        /**
	         * 修改人
	         */
	        modifyer(" modifyer ") ,
	        /**
	         * 修改人姓名
	         */
	        modifyerName(" modifyerName ") ,
	        /**
	         * 修改时间
	         */
	        modifyTime(" modifyTime ") ,
	        /**
	         * 修改IP
	         */
	        modifyIP(" modifyIP ") ;
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
        /**
         * 根据体系表编号获取所有的子分类
         * @param SSTId
         * @return
         */
   	 public List<SingleSSTSubClassify> selectBySSTId(int SSTId);
   	 /**
   	  * 根据层项目编号获取子分类最大显示顺序
   	  */
   	 public Integer selectMaxLayerDisOrder(String layerItemId);
   	 
   	 
   	 
}

