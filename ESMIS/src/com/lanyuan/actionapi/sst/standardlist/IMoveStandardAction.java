package com.lanyuan.actionapi.sst.standardlist;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.actionapi.basic.baseclasses.ResultSearchAction;
import com.lanyuan.assembly.basic.baseclasses.ResultMessage;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;

public class IMoveStandardAction extends ResultOperateAction {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	//标准编号
	private String standardId1;
	//交换标准的编号
	private String standardId2;
	//现在的显示顺序
	private int orderNow;
	//移动后的显示顺序
	private int orderMove;
	@Autowired
	private ServiceSSTStandardList standardService;
	
	public String move(){
		//交换两个标准的显示顺序
		int i = standardService.setDisplayOrder(standardId1, orderMove);
		int j = standardService.setDisplayOrder(standardId2, orderNow);
		if(i>0 && j>0){
			printString("1", "移动成功");
		}else{
			printString("2","移动失败");
		}
		return this.SUCCESS;
	}
	public String getStandardId1() {
		return standardId1;
	}
	public void setStandardId1(String standardId1) {
		this.standardId1 = standardId1;
	}
	public String getStandardId2() {
		return standardId2;
	}
	public void setStandardId2(String standardId2) {
		this.standardId2 = standardId2;
	}
	public int getOrderNow() {
		return orderNow;
	}
	public void setOrderNow(int orderNow) {
		this.orderNow = orderNow;
	}
	public int getOrderMove() {
		return orderMove;
	}
	public void setOrderMove(int orderMove) {
		this.orderMove = orderMove;
	}
	

	
	
}
