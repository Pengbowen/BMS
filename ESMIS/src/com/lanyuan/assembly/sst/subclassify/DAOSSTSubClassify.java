package com.lanyuan.assembly.sst.subclassify;

import java.util.List;

import com.lanyuan.assembly.common.DaoManager;
/**
 * 子分类业务接口
 * @author PBW
 * @date 2016年8月23日14:31:58
 */

public interface DAOSSTSubClassify extends DaoManager<EntitySSTSubClassify> {

	 /**
	  * 根据所属体系表查询所有的子分类
	  * @param 体系表ID
	  * @return List<SingleSSTSubClassify>
	  */
	 public List<EntitySSTSubClassify> seletctSubClassifyBySSTId(int SSTId);
	 /**
	  * 根据所属层项目查询最大显示序号
	  */
	 public Integer selectMaxDisplayOrderByLayerItemId(String layerItemId);
	
	 
}
