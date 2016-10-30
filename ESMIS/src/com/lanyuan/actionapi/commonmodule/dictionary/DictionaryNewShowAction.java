package com.lanyuan.actionapi.commonmodule.dictionary;

import java.util.TreeMap;

import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DictionaryNewShowAction extends ActionSupport
{
    private static final long serialVersionUID = 1L;

    private TreeMap<String, SingleDictionaryData> data;
    private String id;
    
    
	public String execute()
    {
        return Action.SUCCESS;
    }
    

	public TreeMap<String, SingleDictionaryData> getData() {
		return data;
	}

	public void setData(TreeMap<String, SingleDictionaryData> data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
