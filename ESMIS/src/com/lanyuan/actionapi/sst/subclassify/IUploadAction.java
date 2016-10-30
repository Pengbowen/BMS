package com.lanyuan.actionapi.sst.subclassify;

import com.lanyuan.actionapi.basic.baseclasses.ResultBaseAction;

public class IUploadAction extends ResultBaseAction{
	
	private static final long serialVersionUID = 1L;
	
	String savedpath;

	public String execute(){
		return this.SUCCESS;
	}
	public void batchAdd(){
		
		
		
		
		
	}
	public String getSavedpath() {
		return savedpath;
	}
	public void setSavedpath(String savedpath) {
		this.savedpath = savedpath;
	}
	
}
