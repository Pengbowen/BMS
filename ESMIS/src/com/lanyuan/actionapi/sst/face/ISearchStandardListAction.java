package com.lanyuan.actionapi.sst.face;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.util.EncryptUtil;
import com.lanyuan.assembly.util.StrUtil;

public class ISearchStandardListAction  extends ResultSearchAction{

		private static final long serialVersionUID = 1L;
	    @Autowired
		 private ServiceSSTStandardList standardService;
	    @Autowired
		 private ServiceSSTLayerItems layerItemService;
	    @Autowired
		 private ServiceSSTSubClassify serviceSubClassify;
	    @Autowired 
	   private ServiceStandardLibrary standardLibraryService;
	    
	     private Integer SSTId;
	     private String layerItemId;
	     private String subClassId;
	     private String standardNo; 
	     private String standardName; 
	     private String standardCategory;
	     private String oldStandardNo;
	     private String adoptIS;
	     private String dataStr;
	     //设置变量层项目名称和子分类名称,默认为-1
	     private String title="-1";
	     private Integer includeInvalid;//是否包含作废 "0" 不包含作废."1"--包含作废
	    
	     
	     
	     public String execute(){
	    	if(SSTId!=null){
	    		dataStr ="SSTId="+SSTId;
	    	}
	    	if(layerItemId!=null){
	    		dataStr+="&layerItemId="+layerItemId;
	    	SingleSSTLayerItems item  =	layerItemService.selectById(layerItemId);
	    	title = layerItemId+" "+item.getLayerItemName();
	    	}
	    	if(subClassId!=null){
	    		dataStr+="&subClassId="+subClassId;
	    		SingleSSTSubClassify subClass = serviceSubClassify.selectById(subClassId);
	    		String s_id = subClass.getLayerItemId();
	    		SingleSSTLayerItems s_item  =	layerItemService.selectById(s_id);
	    		String s_layerName = s_item.getLayerItemName();
	    		title = subClassId+" "+s_layerName+"/"+subClass.getSubClassName();
	    	}
	    	
	    	return this.SUCCESS;
	    }
	    	
	    	
	    public String searchStandardList() throws UnsupportedEncodingException{
	    	 // 调用父类方法,条件查询和排序
	        ConditionGroup cond = this.getConditionGroupObject();
	        OrderGroup order = this.getOrderGroupObject();
	        // 如果条件查询和排序对象为空,实例化对象
	        if (cond == null)
	        {
	        	
	            cond = new ConditionGroup();
	            if(standardNo !=null && !standardNo.isEmpty()){
	            	standardNo = new String(standardNo.getBytes("iso8859-1"),"utf-8");
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.standardNo.name(), standardNo, enumOperator.Like));
	            }
	            if(standardName !=null && !standardName.isEmpty()){
	            	try {
						standardName = new String(standardName.getBytes("iso8859-1"),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.standardName.name(), standardName, enumOperator.Like));
	            }
	            if(standardCategory !=null && !standardCategory.isEmpty()){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.standardCategory.name(), standardCategory, enumOperator.Equal));
	            }
	            if(oldStandardNo !=null && !oldStandardNo.isEmpty()){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.oldStandardNo.name(), oldStandardNo, enumOperator.Like));
	            }
	            if(adoptIS !=null && !adoptIS.isEmpty()){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.adoptIS.name(), adoptIS, enumOperator.Like));
	            }
	            if(SSTId !=null ){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.SSTId.name(), SSTId+"", enumOperator.Equal));
	            }
	            if(layerItemId !=null && !layerItemId.isEmpty()){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.layerItemId.name(), layerItemId, enumOperator.Equal));
	            }
	            if(subClassId !=null && !subClassId.isEmpty()){
	            	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.subClassId.name(), subClassId, enumOperator.Equal));
	            }
	            if(includeInvalid !=null){
	            	if(includeInvalid == 0){
	            		cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.effectiveState.name(), "1", enumOperator.Equal));
	            	}
	            }else{
            		cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.effectiveState.name(), "1", enumOperator.Equal));
	            }
	        }
	        if (order == null)
	        {
	            order = new OrderGroup();
	            // 按法规id排序
	           order.Add(MappingList.standardId.name(), true);
	        }
	        int recordCount = standardService.getTotalCount(cond);
	        if (recordCount <= 0)
	        {
	            printString(null, 0, "2", "没有数据！");
	            return this.SUCCESS;
	        }
	        List<SingleSSTStandardList> rtnList = null;
	        if (this.isBreakPage())
	        {
	            rtnList = standardService.selectPageList(getCurrentPage(),getLinesOfPage(), cond, order);
	        }
	        else
	        {
	            rtnList = standardService.selectList(cond, order);
	        }
	        
	        List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
	        for (SingleSSTStandardList single : rtnList)
	        {
	        	String oldStandardNo = single.getOldStandardNo();
	        	String oldStandardId = single.getOldStandardId();
	        	String newStandardId = single.getNewStandardId();
	        	String newStandardNo = single.getNewStandardNo();
	            HashMap<String, String> returnData = new HashMap<String, String>();
	            returnData.put(MappingList.standardNo.name(), single.getStandardNo());//标准编号
	            returnData.put(MappingList.standardId.name(), single.getStandardId());//标准Id
	            //将id加密,得出加密字符串和秘钥,用来下载
	           Map<String,String> lock =  EncryptUtil.encrypt(single.getStandardId());
	           String lockStr = lock.get("str");
	           String key = lock.get("key");
	            returnData.put("lockStr", lockStr);
	            returnData.put("key", key);
	            returnData.put(MappingList.standardName.name(), single.getStandardName());//标准名称
	            returnData.put(MappingList.standardCategory.name(), single.getStandardCategory());//标准类别
	            oldStandardNo= StrUtil.isEmpty(oldStandardNo)?"0":oldStandardNo;//旧标准编号
	            newStandardNo= StrUtil.isEmpty(newStandardNo)?"0":newStandardNo;//新标准编号
	            oldStandardId= StrUtil.isEmpty(oldStandardId)?"0":oldStandardId;//旧标准编号
	            newStandardId= StrUtil.isEmpty(newStandardId)?"0":newStandardId;//旧标准编号
	            
	            returnData.put(MappingList.oldStandardNo.name(), oldStandardNo);//旧标准编号 
	            returnData.put(MappingList.oldStandardId.name(), oldStandardId);//旧标准id
	            returnData.put(MappingList.newStandardId.name(), newStandardId);//新标准编号
	            returnData.put(MappingList.newStandardNo.name(), newStandardNo);//新标准id
	            
	            
	           // returnData.put(MappingList.oldStandardId.name(), data.getOldStandardId());
	            returnData.put(MappingList.documentType.name(), single.getDocumentType());
	           //标准有效状态 有效为1,作废为0
	            Integer state = single.getEffectiveState();
	            if(state==null){
	            	state=0;
	            }
	            returnData.put(MappingList.effectiveState.name(), state+"");
	            
	            /**
	             * 如果数据库中没有浏览量,则默认为0
	             */
	            Integer browseVolume = single.getBrowseVolume();
	            if(browseVolume == null){
	            	browseVolume =0;
	            }
	            returnData.put(MappingList.browseVolume.name(), browseVolume+ "");//浏览量
	            
	            String sstLocation = single.getSSTLoaction();
	            if(sstLocation==null){
	            	sstLocation="暂无";
	            }
	            returnData.put(MappingList.SSTLoaction.name(), sstLocation);//体系表位置
	            
	            //发布时间 
	            String approvedDate = "0";
	            if(single.getApprovedDate() !=null && !StrUtil.isEmpty(single.getApprovedDate())){
	            	approvedDate = single.getApprovedDate();
	            	
	            }
	            returnData.put(MappingList.approvedDate.name(), approvedDate);
	            //实施时间
	            String effectiveDate = "0";
	            if(single.getEffectiveDate() !=null && !StrUtil.isEmpty(single.getEffectiveDate())){
	            	effectiveDate = single.getEffectiveDate();
	            	
	            }
	            returnData.put(MappingList.effectiveDate.name(), effectiveDate);
	            
	            datalist.add(returnData);
	        }
	        this.printString(datalist, recordCount, "1", null);
	        return this.SUCCESS;
	    }


		public String getStandardNo() {
			return standardNo;
		}


		public void setStandardNo(String standardNo) {
			this.standardNo = standardNo;
		}


		public String getStandardName() {
			return standardName;
		}


		public void setStandardName(String standardName) {
			this.standardName = standardName;
		}


		public String getStandardCategory() {
			return standardCategory;
		}


		public void setStandardCategory(String standardCategory) {
			this.standardCategory = standardCategory;
		}


		public String getOldStandardNo() {
			return oldStandardNo;
		}


		public void setOldStandardNo(String oldStandardNo) {
			this.oldStandardNo = oldStandardNo;
		}


		public Integer getSSTId() {
			return SSTId;
		}


		public void setSSTId(Integer sSTId) {
			SSTId = sSTId;
		}


		public String getLayerItemId() {
			return layerItemId;
		}


		public void setLayerItemId(String layerItemId) {
			this.layerItemId = layerItemId;
		}


		public String getSubClassId() {
			return subClassId;
		}


		public void setSubClassId(String subClassId) {
			this.subClassId = subClassId;
		}


		public String getAdoptIS() {
			return adoptIS;
		}


		public void setAdoptIS(String adoptIS) {
			this.adoptIS = adoptIS;
		}


		public String getDataStr() {
			return dataStr;
		}
		public void setDataStr(String dataStr) {
			this.dataStr = dataStr;
		}
		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public Integer getIncludeInvalid() {
			return includeInvalid;
		}


		public void setIncludeInvalid(Integer includeInvalid) {
			this.includeInvalid = includeInvalid;
		}
		
	}
	
