package com.lanyuan.actionapi.dictionary;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchMapAction;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.opensymphony.xwork2.Action;

public class ISearchContentAction extends ResultSearchMapAction{
	private static final long serialVersionUID = 1L;
	public String code;
	public String content;
	
	public String execute()
	{
		ServiceDictionaryData sdd = new ServiceDictionaryData();
		content=sdd.selectByCode(code);
        return Action.SUCCESS;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
