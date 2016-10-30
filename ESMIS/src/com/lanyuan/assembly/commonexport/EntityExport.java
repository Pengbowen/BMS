package com.lanyuan.assembly.commonexport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityExport {
	//模板id
	private String  modelId;
	 //下载的文件名称
	private String fileName;
	 //用到的excel模板文件
	private String path;
	//从第excel第几行开始插入数据
	private Integer starLine;
	 //excel文件列头格式及名称字段
	private List<HashMap<String, String>> columnTitle = new ArrayList<HashMap<String, String>>();
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getStarLine() {
		return starLine;
	}
	public void setStarLine(Integer starLine) {
		this.starLine = starLine;
	}
	public List<HashMap<String, String>> getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(List<HashMap<String, String>> columnTitle) {
		this.columnTitle = columnTitle;
	}
	
}
