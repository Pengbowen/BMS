package com.lanyuan.assembly.sst.layeritems;

import java.util.List;
import com.lanyuan.assembly.common.Services;

/**
 * 层项目业务类
 * 
 * @author PBW
 * @date 2016年8月23日14:20:38
 */
public interface ServiceSSTLayerItems extends Services<SingleSSTLayerItems> {

	/**
	 * 根据标准类别查找所有的层项目
	 * 
	 * @param SSTId
	 *            标准类别编号
	 * @return Map<Integer,List<EntitySSTLayerItems>>
	 */
	// public Map<Integer,List<EntitySSTLayerItems>> selectBySSTId(String
	// SSTId);

	public List<SingleSSTLayerItems> selectBySSTId(int SSTId);
	/**
	 * 根据所属体系表ID查询最大项目编号
	 */
	public int selectMaxlayerItemId(String layerItemId);
	/**
	 * 根据项目ID查询包含子项目数量
	 * 
	 * @param 项目编号
	 * @return 包含子项目数量
	 */
	public int selectSubItemCountById(String layerItemId);

	/**
	 * 根据项目ID查询包含子分类数量
	 * 
	 * @param 项目编号
	 * @return 包含子分类数量
	 */
	public int selectSubClassCountById(String layerItemId);

	/**
	 * 根据项目ID查询包含标准数量
	 * 
	 * @param 项目编号
	 * @return 包含标准数量
	 */
	public int selectStandardCount(String layerItemId);

	/**
	 * 根据项目Id设置项目子属性(子项目数量,子分类数量,标准数量)
	 * 
	 * @param layerItemId
	 */
	public SingleSSTLayerItems setItemSubCount(SingleSSTLayerItems e);
	
	public int selectMaxDisplayOrderByLayerNo(int layerNo);
	/**
	 * 查找所有Id
	 * @param SSTId
	 * @return
	 */
	public List<String> selectAllId(int SSTId);
	
	public enum MappingList {
		/**
		 * 所属体系表
		 */
		SSTId(" SSTId "),
		/**
		 * 项目编号
		 */
		layerItemId(" layerItemId "),
		/**
		 * 项目名称
		 */
		layerItemName(" layerItemName "),
		/**
		 * 所属层级
		 */
		layerNo(" layerNo "),
		/**
		 * 所属父级
		 */
		belongItemId(" belongItemId "),
		/**
		 * 显示顺序
		 */
		displayOrder(" displayOrder "),
		/**
		 * 包含子分类数量
		 */
		subClassifyCount(" subClassifyCount "),
		/**
		 * 包含标准数量
		 */
		standardCount(" standardCount "),
		/**
		 * 状态
		 */
		isEnabled(" isEnabled "),
		/**
		 * 修改人
		 */
		modifyer(" modifyer "),
		/**
		 * 修改人姓名
		 */
		modifyerName(" modifyerName "),
		/**
		 * 修改时间
		 */
		modifyTime(" modifyTime "),
		/**
		 * 修改IP
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
