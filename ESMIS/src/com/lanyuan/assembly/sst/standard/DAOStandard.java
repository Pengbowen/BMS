package com.lanyuan.assembly.sst.standard;

import java.util.List;

import com.lanyuan.assembly.common.DaoManager;
import com.lanyuan.assembly.sst.SingleStandardCensus;

public interface DAOStandard extends DaoManager<EntitySSTStandardList> {
	
	/**
	 * 根据所属体系表查询标准列表
	 */
	public List<EntitySSTStandardList> findStandardListBySSTId(String SSTId);
	/**
	 * 根据子分类编号查询标准列表
	 * 
	 */
	public List<EntitySSTStandardList> findStandardBySubClassId(String subClassId);
	/**
	 * 根据项目编号查询标准列表
	 * 
	 */
	public List<EntitySSTStandardList> findByLayerItemId(String layerItemId);
	/**
	 * 标准的显示顺序上移
	 * @param standardId
	 */
	//public int moveUpStandard(String standardId);
	/**
	 * 标准的显示顺序下移
	 * @param standardId
	 */
	//public int moveDownStandard(String standardId);
	/**
	 * 根据项目id查询最大显示顺序
	 */
	public int selectMaxDisplayOrderByItemId(String layerItemId);
	/**
	 * 根据子分类id查询最大显示顺序
	 */
	public int selectMaxDisplayOrderBySubClassId(String subClassId);
	/**
	 * 统计项目标准数量
	 */
	public List<SingleStandardCensus> totalItemStandardCount(int SSTId);
	/**
	 * 统计子分类标准数量
	 * @param SSTId
	 * @return
	 */
	public List<SingleStandardCensus> totalSubClassStandardCount(int SSTId);
	/**
	 * 获取最大显示顺序
	 */
	public Integer getMaxDisplayOrderByLayerItemId(String layerItemId);
	/**
	 * 获取最大显示顺序
	 */
	public Integer getMaxDisplayOrderBySubClassId(String subClassId);
	 /**
	  * 根据查询某层项目下某标准编号的数量
	  */
	 public Integer getCountByLayerItemId(String standardId,String layerItemId);
	 /**
	  * 根据查询某子分类下某标准编号的数量
	  */
	 public Integer getCountBySubClassId(String standardId,String subClassId);
	 
	
}
