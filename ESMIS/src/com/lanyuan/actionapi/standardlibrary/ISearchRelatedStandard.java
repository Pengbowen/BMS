package com.lanyuan.actionapi.standardlibrary;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList.MappingList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.util.EncryptUtil;
import com.lanyuan.assembly.util.LawAndStandardUtil;
import com.lanyuan.assembly.util.WebSitePro;
import com.lanyuan.assembly.util.ZipUtil;

import test.EpubContent;

public class ISearchRelatedStandard extends ResultSearchAction {

	private static final long serialVersionUID = 1L;
	// 标准编号
	private String standardNo;
	// 体系表ID
	private Integer SSTId;
	// 标题
	private String title;
	// 分享人
	private String shareBy;
	// 体系表位置
	private String sstLocation;
	// 标准id
	private String id;
	// 加密字符串
	private String lockStr;
	// 秘钥
	private String key;
	// 文件是否存在 -1 :不存在 1:存在epub 2:存在pdf
	private Integer haveFile = -1;
	// epub文件
	//private List<HashMap<String, String>> epubFile;

	// 设置封面路径
	private String coverPagePath;
	
	private String epubFilePath;

	@Autowired
	private ServiceStandardLibrary standardLibraryService;

	@Autowired
	private ServiceSSTStandardList standardService;

	public String execute() throws UnsupportedEncodingException {
		Map<String, String> map = EncryptUtil.encrypt(id);
		lockStr = map.get("str");
		key = map.get("key");
		SingleSSTStandardList single = standardService.selectById(id);
		// 如果没有则转到错误页面
		if (single != null) {
			SSTId = single.getsSTId();
		} else {
			SSTId = -1;
		}
		SingleStandardLibraryData data = standardLibraryService.selectById(id);
		// 获取浏览量,并+1,更新浏览量
		Integer browseVolume = data.getBrowseVolume();
		if (browseVolume == null) {
			browseVolume = 0;
		}
		browseVolume += 1;
		// 更新浏览量
		standardLibraryService.updateBrowseVolume(id);

		standardNo = data.getStandardNo();
		String rootPath = WebSitePro.getWebRoot();
		//String rootPath = WebSitePro.getStandard_filePath();

		if (data != null) {
			// 获取解压后的epub文件夹
			String filePath = LawAndStandardUtil.getPath(id, "epub");
			filePath = rootPath + filePath;
			filePath=ZipUtil.getPath(filePath);
			
			
			File dataFile = new File(filePath);
			// 如果存在已经解压后的文件,就不用再解压
			if (dataFile.exists()) {
				try {
					EpubContent epub = new EpubContent(filePath);
					//coverPagePath = epub.getCoverPagePath();
					haveFile = 1;
					//epubFile = getList(filePath);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}

			} else {
				// 是否存在epub文件
				// 获取epub文件路径
				String path = LawAndStandardUtil.getPath(id, "epub");
				path = rootPath +path;
				File file = new File(path);
				if (file.exists()) {
					haveFile = 1;
					// 解压文件
				path = 	ZipUtil.unZip(path);
					try {
						EpubContent epub = new EpubContent(path);
						coverPagePath = epub.getCoverPagePath();
						//epubFile = getList(path);
//						for(int i=0;i<epubFile.size();i++){
//							HashMap<String,String> hashmap = epubFile.get(i); 
//							String iPath = hashmap.get("path");
//							String  code = URLEncoder.encode(iPath,"utf-8");
//							hashmap.put("path", code);
//							epubFile.set(i, hashmap);
//						}
						
					} catch (DocumentException e) {
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
				} else {
					// 如果不存在epub文件,那就再判断是否存在pdf文件
					path = LawAndStandardUtil.getPath(id, "pdf");
					path=rootPath+path;
					file = new File(path);
					if (file.exists()) {
						haveFile = 2;
					}

				}

			}
			title = data.getStandardName();
			shareBy = data.getSharePeople();
			if (shareBy == null) {
				shareBy = "-1";
			}
			sstLocation = data.getSSTLoaction();
			if (sstLocation == null) {
				sstLocation = "-1";
			}
		}

		return this.SUCCESS;
	}

	/**
	 * 查询替代/被替代标准
	 */
	public String searchReplaceStandard() {
		if (id != null) {
			SingleStandardLibraryData single = standardLibraryService.selectById(id);
			String newStandardNo = single.getNewStandardNo();
			String oldStandardNo = single.getOldStandardNo();
			List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
			if (newStandardNo != null && !newStandardNo.isEmpty()) {
				HashMap<String, String> map = new HashMap<String, String>();
				SingleStandardLibraryData newSingle = standardLibraryService.getDataByStandardNo(newStandardNo);
				map.put(MappingList.standardName.name(), newSingle.getStandardName());
				map.put(MappingList.standardId.name(), newSingle.getStandardId());
				map.put(MappingList.standardNo.name(), newSingle.getStandardNo());
				map.put(MappingList.standardCategory.name(), newSingle.getStandardCategory());
				datalist.add(map);
			}
			if (oldStandardNo != null && !oldStandardNo.isEmpty()) {
				HashMap<String, String> map = new HashMap<String, String>();
				SingleStandardLibraryData oldSingle = standardLibraryService.getDataByStandardNo(oldStandardNo);
				map.put(MappingList.standardName.name(), oldSingle.getStandardName());
				map.put(MappingList.standardId.name(), oldSingle.getStandardId());
				map.put(MappingList.standardNo.name(), oldSingle.getStandardNo());
				map.put(MappingList.standardCategory.name(), oldSingle.getStandardCategory());
				datalist.add(map);
			}
			printString(datalist, datalist.size(), "1", "成功");
		}
		return this.SUCCESS;
	}

	/**
	 * 查询相关标准
	 * 
	 * @return
	 */
	public String searchSeriesStandard() {
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		if (id != null) {
			SingleStandardLibraryData data = standardLibraryService.selectById(id);
			standardNo = data.getStandardNo();
			int endIndex = 5;
			if (standardNo.contains(".")) {
				endIndex = standardNo.indexOf(".");
			}
			if (standardNo.contains("-")) {
				endIndex = standardNo.indexOf("-");
			}

			String likeNo = standardNo.substring(0, endIndex);
			ConditionGroup cond = new ConditionGroup();
			cond.addWithAnd(new ConditionNormal(MappingList.standardNo.name(), likeNo, enumOperator.Like));
			List<SingleStandardLibraryData> listData = standardLibraryService.selectPageList(1, 3, cond, null);

			if (listData.size() > 0) {
				for (SingleStandardLibraryData single : listData) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(MappingList.standardName.name(), single.getStandardName());
					map.put(MappingList.standardId.name(), single.getStandardId());
					map.put(MappingList.standardNo.name(), single.getStandardNo());
					map.put(MappingList.standardCategory.name(), single.getStandardCategory());
					datalist.add(map);
				}
				printString(datalist, datalist.size(), "0", "ok");
			}
		} else {
		}
		return this.SUCCESS;
	}

	/**
	 * 查询体系标准
	 * 
	 * @return
	 */
	public String searchSSTStandard() {
		SingleStandardLibraryData data = standardLibraryService.selectById(id);

		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		if (id != null) {
			SingleSSTStandardList singleStandard = standardService.selectById(id);
			Integer SSTId = singleStandard.getsSTId();

			ConditionGroup cond = new ConditionGroup();
			cond.addWithAnd(new ConditionNormal(MappingList.SSTId.name(), SSTId.toString(), enumOperator.Equal));
			List<SingleSSTStandardList> listData = standardService.selectPageList(1, 3, cond, null);

			if (listData.size() > 0) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (SingleSSTStandardList single : listData) {
					map.put(MappingList.standardName.name(), single.getStandardName());
					map.put(MappingList.standardId.name(), single.getStandardId());
					map.put(MappingList.standardNo.name(), single.getStandardNo());
					map.put(MappingList.standardCategory.name(), single.getStandardCategory());
					datalist.add(map);
					if (datalist.size() == 3) {
						break;
					}
				}
				printString(datalist, datalist.size(), "0", "ok");
			}
		} else {
		}
		return this.SUCCESS;
	}

	/**
	 * 加载epub文件的目录
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 * @throws DocumentException
	 */
	public String getList() throws DocumentException, ParserConfigurationException {
		String path = LawAndStandardUtil.getPath(id,"epub");
		path = path.substring(0,path.length()-5);
		path = WebSitePro.getWebRoot()+path;
		EpubContent test = new EpubContent(path);
		List<HashMap<String, String>> data = test.getData();
		printString(data,data.size(),"1","ok");
		return this.SUCCESS;
	}

	public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	public Integer getSSTId() {
		return SSTId;
	}

	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShareBy() {
		return shareBy;
	}

	public void setShareBy(String shareBy) {
		this.shareBy = shareBy;
	}

	public String getSstLocation() {
		return sstLocation;
	}

	public void setSstLocation(String sstLocation) {
		this.sstLocation = sstLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLockStr() {
		return lockStr;
	}

	public void setLockStr(String lockStr) {
		this.lockStr = lockStr;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getHaveFile() {
		return haveFile;
	}

	public void setHaveFile(Integer haveFile) {
		this.haveFile = haveFile;
	}

	public String getCoverPagePath() {
		return coverPagePath;
	}

	public void setCoverPagePath(String coverPagePath) {
		this.coverPagePath = coverPagePath;
	}

//	public List<HashMap<String, String>> getEpubFile() {
//		return epubFile;
//	}
//
//	public void setEpubFile(List<HashMap<String, String>> epubFile) {
//		this.epubFile = epubFile;
//	}

}
