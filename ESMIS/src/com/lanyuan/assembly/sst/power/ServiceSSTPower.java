package com.lanyuan.assembly.sst.power;

import java.util.List;

import com.lanyuan.assembly.common.Services;

public interface ServiceSSTPower extends Services<SingleSSTPower> {
	/**
	 * 获取权限 需要用户id和体系表id
	 * @param userId
	 * @param SSTId
	 * @return
	 */
	public List<SingleSSTPower> getPower(String userId,int SSTId);
	/**
	 * 根据用户id获取权限
	 * @param userId
	 * @return
	 */
	public List<SingleSSTPower> getPowerByUserId(String userId);
	/**
	 * 根据用户id删除权限
	 * @param userId
	 * @return
	 */
	public Integer deleteByUserId(String userId,int SSTId);
	
	public boolean batchAddPower(List<SingleSSTPower> powerList,String userId,int SSTId);
	
	
	
	public enum MappingList {
		/**
		 * id
		 */
		id(" id "),
		/**
		 * 体系表编号
		 */
		SSTId(" SSTId "),
		/**
		 * 层级
		 */
		layerNo(" layerNo "),
		/**
		 * 所属项目编号
		 */
		layerItemId(" layerItemId "),
		/**
		 * 所属子分类编号
		 */
		subClassId(" subClassId "),
		/**
		 * 用户编号
		 */
		userId(" userId ");
		private String value;

		MappingList(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
