package com.lanyuan.assembly.sst.power;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.util.DataUtil;


@Service("powerService")
public class ServiceSSTPowerImpl extends ServersManager<SingleSSTPower,EntitySSTPower,DAOSSTPower> implements ServiceSSTPower{
	
	@Autowired
	public void setDao(DAOSSTPower dao) {
		this.dao = dao;
	}
	public SingleSSTPower getNewSingleObj() {
		SingleSSTPower s = new SingleSSTPower();
		return s;
	}

	public EntitySSTPower getNewEntityObj() {
		EntitySSTPower e = new EntitySSTPower();
		return e;
	}
	public List<SingleSSTPower> getPower(String userId,int SSTId) {
		List<EntitySSTPower> entityList = dao.selectByUserIdAndSSTId(userId,SSTId);
		List<SingleSSTPower> singleList = new ArrayList<SingleSSTPower>();
		if(entityList!=null && !entityList.isEmpty()){
			for(EntitySSTPower e : entityList){
				SingleSSTPower s = entityToSingle(e);
				singleList.add(s);
			}
		}else{
			return null;
		}
		return singleList;
	}
	
	public List<SingleSSTPower> getPowerByUserId(String userId) {
		
		List<EntitySSTPower> entityList = dao.selectByUserId(userId);
		List<SingleSSTPower> singleList = new ArrayList<SingleSSTPower>();
		if(entityList!=null && !entityList.isEmpty()){
			for(EntitySSTPower e : entityList){
				SingleSSTPower s = entityToSingle(e);
				singleList.add(s);
			}
		}else{
			return null;
		}
		return singleList;
	
	}
	public Integer deleteByUserId(String userId,int SSTId) {
		Integer i  = dao.deleteByUserId(userId,SSTId);
		return i;
	}
	
	/**
	 * 批量添加权限
	 * 1.先删除原有权限
	 * 2.添加权限
	 */
	@Transactional
	public boolean batchAddPower(List<SingleSSTPower> powerList,String userId,int SSTId) {
		deleteByUserId(userId,SSTId);
		int powerCount =0;
		if(powerList!=null && !powerList.isEmpty()){
		for(SingleSSTPower power:powerList){
			System.out.println(power);
			int i = insert(power);
			powerCount++;
		}
		if(powerCount <powerList.size()){
			//回滚
			DataUtil.DataRollBack();
			return false;
		}
		}
		return true;
	}
	
}
