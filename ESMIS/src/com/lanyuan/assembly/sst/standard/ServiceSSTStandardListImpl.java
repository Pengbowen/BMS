package com.lanyuan.assembly.sst.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.basic.baseclasses.ResultMessage;
import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.sst.SingleStandardCensus;
@Service("standardService")
public class ServiceSSTStandardListImpl extends ServersManager<SingleSSTStandardList,EntitySSTStandardList,DAOStandard> implements ServiceSSTStandardList {
	@Autowired
	public void setDao(DAOStandard dao) {
		this.dao = dao;
	}
	
	/**
	 * 根据体系表ID查询标准列表
	 * 
	 */
	public Map<Integer, List<EntitySSTStandardList>> selectStandardBySSTId(String SSTId) {
		 Map<Integer,List<EntitySSTStandardList>>  map = new  HashMap<Integer,List<EntitySSTStandardList>> ();
			List<EntitySSTStandardList> list = dao.findStandardListBySSTId(SSTId);
			/**
			 * 获取所有的层级
			 */
			Set<Integer> level = new HashSet<Integer>();
			for(EntitySSTStandardList e:list){
				int  i = e.getLayerNo();
				level.add(i);
			}
			for(int i :level){
				List<EntitySSTStandardList> li = new ArrayList<EntitySSTStandardList>();
				for(EntitySSTStandardList e:list){
					if(e.getLayerNo()==i){
						li.add(e);
					}
				}
				map.put(i, li);
			}
			return map;
	}
	
	public List<SingleSSTStandardList> selectStandardBySubClassId(String subClassId) {
		List<EntitySSTStandardList> list = dao.findStandardBySubClassId(subClassId);
		if(list ==null || list.isEmpty()){
			return null;
		}
		List<SingleSSTStandardList> singleList = new ArrayList<SingleSSTStandardList>();
		for(EntitySSTStandardList e:list){
			SingleSSTStandardList s = entityToSingle(e);
			singleList.add(s);
		}
		return singleList;
	}
//	public ResultMessage moveUpStandard(String standardId,int displayOrder) {
//		
//		if(displayOrder<=1){
//			return new ResultMessage(0,"不能再上移了"); 
//		}
//		
//		int i =dao.moveUpStandard(standardId);
//		if(i>0){
//			return new ResultMessage(1,"成功");
//		}else{
//			return new ResultMessage(0,"失败");
//		}
//		
//		
//	}
//	public ResultMessage moveDownStandard(String standardId) {
//
//		int i =dao.moveDownStandard(standardId);
//		if(i>0){
//			return new ResultMessage(1,"成功");
//		}else{
//			return new ResultMessage(0,"失败");
//		}
//	}
	public SingleSSTStandardList getNewSingleObj() {
		return new SingleSSTStandardList();
	}
	public EntitySSTStandardList getNewEntityObj() {
		return new EntitySSTStandardList();
	}
	public List<SingleSSTStandardList> selectByLayerItemId(String layerItemId) {
		List<EntitySSTStandardList> list = dao.findByLayerItemId(layerItemId);
		if(list ==null || list.isEmpty()){
			return null;
		}
		List<SingleSSTStandardList> singleList = new ArrayList<SingleSSTStandardList>();
		for(EntitySSTStandardList e:list){
			SingleSSTStandardList s = entityToSingle(e);
			singleList.add(s);
		}
		return singleList;
	}

	public int selectMaxDisplayOrderByItemId(String layerItemId) {
		return dao.selectMaxDisplayOrderByItemId(layerItemId);
	}

	public int selectMaxDisplayOrderBySubClassId(String subClassId) {
		return dao.selectMaxDisplayOrderBySubClassId(subClassId);
	}

	public List<HashMap<String, String>> totalCount(int SSTId) {
		List<HashMap<String,String>> datalist = new ArrayList<HashMap<String,String>>();
		List<SingleStandardCensus> itemCount = dao.totalItemStandardCount(SSTId);
		List<SingleStandardCensus> subClassCount =dao.totalSubClassStandardCount(SSTId);
		if(subClassCount !=null){
			for(SingleStandardCensus s :subClassCount){
				HashMap<String,String> map = new HashMap<String,String>();
				String subClassId = s.getSubClassId();
				if(subClassId ==null){
					continue;
				}
				String start = subClassId.substring(0,4);
				String end = subClassId.substring(4);
				subClassId = start+"/"+end;
				map.put("standardCategory", s.getStandardCategory());
				map.put("id", s.getSubClassId());
				map.put("count", s.getCount().toString());
				map.put("standardCategoryName", s.getStandardCategoryName());
				datalist.add(map);
			}
		}
		if(itemCount !=null){
			for(SingleStandardCensus s :itemCount){
				HashMap<String,String> map = new HashMap<String,String>();
				if(s.getLayerItemId()==null){
					continue;
				}
				map.put("standardCategory", s.getStandardCategory());
				map.put("id", s.getLayerItemId());
				map.put("count", s.getCount().toString());
				map.put("standardCategoryName", s.getStandardCategoryName());
				datalist.add(map);
			}
			
		}
		
		return datalist;
	}

	@Override
	public int getMaxDisplayOrder(String layerItemId) {
		Integer d =dao.getMaxDisplayOrderByLayerItemId(layerItemId);
		if(d==null) d=0;
		return d;
	}

	public int getMaxDisplayOrderBySubClassId(String subClassId) {
		Integer d = dao.getMaxDisplayOrderBySubClassId(subClassId);
		if(d==null) d=0;
		return d;
	}

	public int setDisplayOrder(String standardId, int displayOrder) {
		EntitySSTStandardList e = dao.selectById(standardId);
		e.setDisplayOrder(displayOrder);
		int i = dao.update(e);
		return i;
	}

	/**
	 * 在某项目下是某标准是否存在
	 */
	public boolean isExistInLayerItem(String standardId, String layerItemId) {
	int count =	dao.getCountByLayerItemId(standardId, layerItemId);
	System.out.println("数量是:"+count);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 在某子分类下某标准是否存在
	 */
	public boolean isExistInSubClassId(String standardId, String subClassId) {
		int count =	dao.getCountBySubClassId(standardId, subClassId);
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}   
}
