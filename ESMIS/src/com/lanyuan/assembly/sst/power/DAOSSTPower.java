package com.lanyuan.assembly.sst.power;

import java.util.List;

import com.lanyuan.assembly.common.DaoManager;

public interface DAOSSTPower extends DaoManager<EntitySSTPower> {
	/**
	 * 根据用户ID和体系表ID查询权限
	 * @param userId
	 * @return
	 */
	public  List<EntitySSTPower> selectByUserIdAndSSTId(String userId,int SSTId);
	/**
	 * 根据用户ID查询权限
	 */
	public List<EntitySSTPower> selectByUserId(String userId);
	/**
	 * 根据用户id删除权限
	 */
	public Integer deleteByUserId(String userId,int SSTId);
}
