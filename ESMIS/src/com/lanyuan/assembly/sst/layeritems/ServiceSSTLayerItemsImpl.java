package com.lanyuan.assembly.sst.layeritems;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;

@Service("layerItemService")
public class ServiceSSTLayerItemsImpl extends ServersManager<SingleSSTLayerItems,EntitySSTLayerItems,DAOSSTLayerItems> implements ServiceSSTLayerItems {
	
	@Autowired
	public void setDao(DAOSSTLayerItems dao) {
		this.dao = dao;
	}
	
	/**
	 * 根据所属体系表ID获取所有的层项目
	 * @param 体系表ID
	 * @return Map<层级, 所属层级的项目集合>
	 */
	public List<SingleSSTLayerItems> selectBySSTId(int SSTId){
		List<EntitySSTLayerItems> itemsList = dao.selectBySSTId(SSTId);
		return entityListToSingleList(itemsList);
	}
	public int selectSubItemCountById(String layerItemId) {
		return dao.selectSubItemCount(layerItemId);
	}
	public int selectMaxlayerItemId(String layerItemId){
	    String result=dao.selectMaxlayerItemId(layerItemId);
	    if(StringUtils.isBlank(result)){
	        return 0;
	    }else{
	        return Integer.parseInt(result);
	    }
	}
	public int selectSubClassCountById(String layerItemId) {
		return dao.selectSubClassCount(layerItemId);
	}

	public int selectStandardCount(String layerItemId) {
		return dao.selectStandardCount(layerItemId);
	}

	
	public SingleSSTLayerItems setItemSubCount(SingleSSTLayerItems e) {
		int subItemCount = selectSubItemCountById(e.getLayerItemId());
		int subClassCount = selectSubClassCountById(e.getLayerItemId());
		int standardCount = selectStandardCount(e.getLayerItemId());
		e.setSubItemCount(subItemCount);
		e.setSubClassifyCount(subClassCount);
		e.setStandardCount(standardCount);
		return e;
	}
	public int update(SingleSSTLayerItems e){
		//设置子项目信息
		e=setItemSubCount(e);
		EntitySSTLayerItems s = singleToEntity(e);
		return dao.update(s);
	}
	public int insert(SingleSSTLayerItems e){
		ConditionGroup cond = new ConditionGroup();
		cond.addWithAnd(new ConditionNormal(MappingList.layerNo.name(), e.getLayerNo().toString(), enumOperator.Equal));
		cond.addWithAnd(new ConditionNormal(MappingList.SSTId.name(), e.getSSTId().toString(), enumOperator.Equal));
		List<SingleSSTLayerItems> list = selectList(cond, null);
		int max=0;
		String newLayerItemId;
		if(list!=null && list.size()>0){
			for(SingleSSTLayerItems single:list){
				String layerItemId = single.getLayerItemId();
				int temp = new Integer(layerItemId);
				if(temp>max){
					max=temp;
				}
			}
			newLayerItemId = max+1+"";
		}else{
		 newLayerItemId =	e.getSSTId().toString()+e.getLayerNo().toString()+"01";
		}
		
		
//		
//		//生成项目Id
//		int displayOrder = e.getDisplayOrder();
//		String order = "00";
//		if(displayOrder<10){
//			order="0"+displayOrder;
//		}else{
//			order=displayOrder+"";
//		}
//		String layerItemId = e.getSSTId().toString()+e.getLayerNo().toString()+order;
		e.setLayerItemId(newLayerItemId);
		EntitySSTLayerItems s = singleToEntity(e);
		int i =dao.insert(s);
		return i;
		
	}

	public SingleSSTLayerItems getNewSingleObj() {
		return new SingleSSTLayerItems();
	}

	public EntitySSTLayerItems getNewEntityObj() {
		return new EntitySSTLayerItems();
	}
	 public List<SingleSSTLayerItems> entityListToSingleList(List<EntitySSTLayerItems> itemsList){
		 if(itemsList==null || itemsList.isEmpty()){
				return null;
			}
			List<SingleSSTLayerItems> singleList = new ArrayList<SingleSSTLayerItems>();
			for(EntitySSTLayerItems e:itemsList){
				
				SingleSSTLayerItems s = entityToSingle(e);
				singleList.add(s);
			}
			return singleList;
	 }

	public int selectMaxDisplayOrderByLayerNo(int layerNo) {
		return dao.selectMaxDisplayOrderByLayerNo(layerNo);
	}

	public List<String> selectAllId(int SSTId) {
		List<String> layerItemIds = dao.selectAllLayerItemId(SSTId);
		List<String> subClassIds = dao.selectAllSubClassId(SSTId);
		for(String id:subClassIds){
			String start = id.substring(0,4);
			String end = id.substring(4);
			String subClassId = start+"-"+end;
			layerItemIds.add(subClassId  );
		}
		return layerItemIds;
	}

}
