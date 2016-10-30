package com.lanyuan.actionapi.sst.standardlist;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;

public class IDeleteStandardAction extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	
	private String standardId;
	@Autowired
	private ServiceSSTStandardList standardService;
	public String execute(){
		int i = standardService.deleteById(standardId);
		if(i>0){
			printString("1","删除成功");
		}else{
			printString("2","删除失败");
		}
		
		return this.SUCCESS;
	}
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	
}
