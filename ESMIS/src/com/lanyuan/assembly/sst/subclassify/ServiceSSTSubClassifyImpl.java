package com.lanyuan.assembly.sst.subclassify;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.common.ServersManager;
@Service("serviceSubClassify")
public class ServiceSSTSubClassifyImpl extends ServersManager<SingleSSTSubClassify,EntitySSTSubClassify,DAOSSTSubClassify> implements ServiceSSTSubClassify {
	@Autowired
	public void setDao(DAOSSTSubClassify dao) {
		this.dao = dao;
	}
	
	/**
	 * 根据体系表ID获取所有子分类项目
	 */
	public List<SingleSSTSubClassify> selectBySSTId(int SSTId) {
		List<EntitySSTSubClassify> list = dao.seletctSubClassifyBySSTId(SSTId);
		if(list ==null || list.isEmpty()){
			return null;
		}
		List<SingleSSTSubClassify> singleList = new ArrayList<SingleSSTSubClassify>();
		for(EntitySSTSubClassify e:list){
			SingleSSTSubClassify s = entityToSingle(e);
			singleList.add(s);
		}
		return singleList;
	}

	public SingleSSTSubClassify getNewSingleObj() {
		return new SingleSSTSubClassify();
	}

	public EntitySSTSubClassify getNewEntityObj() {
		return new EntitySSTSubClassify();
	}

	public Integer selectMaxLayerDisOrder(String layerItemId) {
		return dao.selectMaxDisplayOrderByLayerItemId(layerItemId);
	}
	
}
