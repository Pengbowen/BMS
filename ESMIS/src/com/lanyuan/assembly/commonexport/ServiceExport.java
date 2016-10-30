package com.lanyuan.assembly.commonexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;

/**
 * json文件读取，获取导出数据
 */
public class ServiceExport extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 读取WebRoot的路径下文件
	 */
	public String readConf(String path) throws IOException {
		InputStream inputStream = new FileInputStream(new File(path));
		InputStreamReader file = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader reader = new BufferedReader(file);
		String fileContent = "";
		String tempString = null;
		while ((tempString = reader.readLine()) != null) {
			fileContent += tempString;
		}
		reader.close();
		System.out.println(fileContent);
		return fileContent;
	}

	/**
	 * 获取WebRoot的路径
	 */
	public String getWebRoot() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}
		java.net.URL url = classLoader.getResource("");
		String ROOT_CLASS_PATH = url.getPath() + "/";
		File rootFile = new File(ROOT_CLASS_PATH);
		String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
		File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";

		return SERVLET_CONTEXT_PATH.replaceAll("%20", " ");
	}

	/**
	 * 解析json文件
	 */
	public List<EntityExport> dataResult(String modelId) throws IOException {
		File file = new File(getWebRoot()+"exportJson/" + modelId + ".json");
		if (file.exists()) {
			String jsonString = readConf(file+"");
			JSONObject  jsonObject = JSONObject.fromObject(jsonString);
			List<EntityExport> entityExportList = new ArrayList<EntityExport>();
			List<HashMap<String, String>> columnTitleList = null;
			EntityExport entityExport = null;
			if (jsonObject.size() > 0) {
				for (int i = 0; i < jsonObject.size(); i++) {
					entityExport = new EntityExport();
					columnTitleList = new ArrayList<HashMap<String, String>>();
					entityExport.setFileName(jsonObject.getString("fileName"));
					entityExport.setModelId(jsonObject.getString("modelId"));
					entityExport.setPath(jsonObject.getString("path"));
					entityExport.setStarLine(jsonObject.getInt("starLine"));
					JSONArray columnTitles = jsonObject
							.getJSONArray("columnTitle");
					for (int j = 0; j < columnTitles.size(); j++) {
						HashMap<String, String> map = new HashMap<String, String>();
						JSONObject columnTitle = columnTitles.getJSONObject(j);
						map.put("field", columnTitle.getString("field"));
						map.put("title", columnTitle.getString("title"));
						map.put("width", columnTitle.getString("width"));
						map.put("align", columnTitle.getString("align"));
						map.put("startcolumn",
								columnTitle.getString("startcolumn"));
						columnTitleList.add(map);
					}
					entityExport.setColumnTitle(columnTitleList);
					entityExportList.add(entityExport);
				}
			}
			return entityExportList;
		}
		this.printString("2","文件不存在！");
		return  null;
	}
}
