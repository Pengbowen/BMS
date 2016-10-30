package com.lanyuan.actionapi.sst.layeritems;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;

/**
 * 添加子项目action
 * @author PBW
 *
 */
public class IAddSubItemAction extends ResultSearchAction{
	//状态表示 1-添加根项目 2-修改 3,添加子项目
		private String state;
		private String layerId;
		private int SSTId;
		private String belongItemId;
		private Integer layerNo1;
		private String SSTType;
		@Autowired
		private ServiceSSTLayerItems layerItemService;
	public String execute(){
		state="3";
		SingleSSTLayerItems s = layerItemService.selectById(layerId);
		belongItemId = s.getBelongItemId();
		layerNo1 = s.getLayerNo()+1;
		return this.SUCCESS;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}



	public String getLayerId() {
		return layerId;
	}


	public void setLayerId(String layerId) {
		this.layerId = layerId;
	}


	public int getSSTId() {
		return SSTId;
	}


	public void setSSTId(int sSTId) {
		SSTId = sSTId;
	}


	public String getBelongItemId() {
		return belongItemId;
	}


	public void setBelongItemId(String belongItemId) {
		this.belongItemId = belongItemId;
	}


	public Integer getLayerNo1() {
		return layerNo1;
	}


	public void setLayerNo1(Integer layerNo1) {
		this.layerNo1 = layerNo1;
	}


	public String getSSTType() {
		return SSTType;
	}


	public void setSSTType(String sSTType) {
		SSTType = sSTType;
	}







}
