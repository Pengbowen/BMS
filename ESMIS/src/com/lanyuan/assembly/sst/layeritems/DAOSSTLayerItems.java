package com.lanyuan.assembly.sst.layeritems;
import java.util.List;

import com.lanyuan.assembly.common.DaoManager;

/**
 * DAO类
 * 
 * @ClassName:DAOSSTLayerItems
 * @Description: 增删改查的操作
 * @author pbw
 * @date Mon Aug 22 17:23:14 CST 2016
 */
 interface DAOSSTLayerItems extends DaoManager<EntitySSTLayerItems>
 {
     /**
      * 根据所属体系表ID查询最大项目编号
      */
     public String selectMaxlayerItemId(String layerItemId);
	 /**
	  * 根据标准类别查找所有的层项目
	  * @param sSTId 标准类别编号
	  * @return List<SingleSSTLayerItems>
	  */
	 public List<EntitySSTLayerItems> selectBySSTId(int sSTId);
	
	 /**
	  * 根据项目编号查询子项目数量
	  */
	 public int selectSubItemCount(String layerItemId);
	 /**
	  * 根据项目编号查询子分类数量
	  * @param layerItemId
	  * @return 
	  */
	 public int selectSubClassCount(String layerItemId);
	 /**
	  * 根据项目编号查询标准数量
	  * @param layerItemId
	  * @return
	  */
	 public int selectStandardCount(String layerItemId);
	 /**
	  * 查询所有项目信息
	  */
	 public List<EntitySSTLayerItems> selectAllLayerItems();
	 /**
	  * 根据层级查询本层级最大显示顺序
	  */
	 public int selectMaxDisplayOrderByLayerNo(int layerNo);
	 /**
	  * 获取所有的项目编号
	  */
	 public List<String> selectAllLayerItemId(int SSTId);
	 /**
	  * 获取所有子分类编号
	  */
	 public List<String> selectAllSubClassId(int SSTId);
	
 }