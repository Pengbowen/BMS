package com.lanyuan.assembly.standardlibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.QueryResolver;
import com.lanyuan.assembly.common.Page;
import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.util.LawAndStandardUtil;

@Service("standardLibraryService")
public class ServiceStandardLibraryImpl extends ServersManager<SingleStandardLibraryData,EntityStandardLibrary,DAOStandardLibrary> 
implements ServiceStandardLibrary {
	@Autowired
	public void setDao(DAOStandardLibrary dao) {
		this.dao = dao;
	}
	/**
	 * 分页查询
	 */
	public List<SingleStandardLibraryData> selectPageList(int page, int pagecount,ConditionGroup cond, OrderGroup order){
		cond = super.exchangeMappingToField(cond);
        order =super.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page Page=new Page();
        Page.setPageNo(page);
        Page.setPageSize(pagecount);
        Page.setSql(sql);
        List<EntityStandardLibrary> entityStandardLibraryDataList=this.dao.selectPageList(Page);
        List<SingleStandardLibraryData> singleStandardLibraryDataList=new ArrayList<SingleStandardLibraryData>();
        if(entityStandardLibraryDataList!=null){
        	SingleStandardLibraryData singleStandardLibraryData=null;
        	for (EntityStandardLibrary entityStandardLibrary : entityStandardLibraryDataList){
        		singleStandardLibraryData=new SingleStandardLibraryData();
        		 BeanUtils.copyProperties(entityStandardLibrary, singleStandardLibraryData);
        		singleStandardLibraryData.setStandardId(entityStandardLibrary.getStandardId());
        		singleStandardLibraryDataList.add(singleStandardLibraryData);
        	}
        }
		return singleStandardLibraryDataList;
	}
	/**
	 * 条件查询
	 */
	public List<SingleStandardLibraryData> selectList(ConditionGroup cond, OrderGroup order){
		cond = super.exchangeMappingToField(cond);
        order =super.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page Page=new Page();
        Page.setSql(sql);
        List<EntityStandardLibrary> entityStandardLibraryDataList=this.dao.selectPageList(Page);
        List<SingleStandardLibraryData> singleStandardLibraryDataList=new ArrayList<SingleStandardLibraryData>();
        if(entityStandardLibraryDataList!=null){
        	SingleStandardLibraryData singleStandardLibraryData=null;
        	for (EntityStandardLibrary entityStandardLibrary : entityStandardLibraryDataList){
        		singleStandardLibraryData=new SingleStandardLibraryData();
        		 BeanUtils.copyProperties(entityStandardLibrary, singleStandardLibraryData);
        		singleStandardLibraryData.setStandardId(entityStandardLibrary.getStandardId());
        		singleStandardLibraryDataList.add(singleStandardLibraryData);
        	}
        }
		return singleStandardLibraryDataList;
	}
	/**手工入库实现*/

	public int addByMananl(String standardNo ,SingleStandardLibraryHandworkData single) {
		EntityStandardLibrary data=dao.selectByStandardNo(standardNo);
		if(data!=null){
			return -1;
		}
		String standardId=dao.selectMaxId();
		if(standardId==null||"".equals(standardId)){
			standardId="910000000";
		}else {
			int id=Integer.parseInt(standardId)+1;
			standardId=id+"";
		}
		String file=LawAndStandardUtil.getPath(standardId, "pdf");
		LawAndStandardUtil.removeFile(single.getFilePath(), file);
		single.setFilePath(file);
		EntityStandardLibrary  entityStandardLibrary= new EntityStandardLibrary();
		String oldStandardNo=single.getOldStandardNo();
		String newStandardNo=single.getStandardNo();
		if(!StringUtils.isBlank(oldStandardNo)){
			EntityStandardLibrary old= dao.selectByStandardNo(oldStandardNo);
			if(old!=null){
				old.setNewStandardId(standardId);
				old.setNewStandardNo(newStandardNo);
				dao.update(old);
			}
		}
		 BeanUtils.copyProperties(single, entityStandardLibrary);
		entityStandardLibrary.setStandardId(standardId);
		return dao.insert(entityStandardLibrary);
	}
	   /**修改手工入库实现*/
	public int update(String standardId,SingleStandardLibraryHandworkData single)
	{
		String oldStandardNo = single.getOldStandardNo();
		String newStandardNo=single.getStandardNo();
		if(!StringUtils.isBlank(oldStandardNo)){
			EntityStandardLibrary old= dao.selectByStandardNo(oldStandardNo);
			if(old==null){
				return 0;
			}
			old.setNewStandardId(standardId);
			old.setNewStandardNo(newStandardNo);
			dao.update(old);
		}
	    EntityStandardLibrary  entityStandardLibrary= new EntityStandardLibrary();
	    BeanUtils.copyProperties(single, entityStandardLibrary);
	    entityStandardLibrary.setStandardId(standardId);
	    return dao.update(entityStandardLibrary); 
	}
	   /**修改手工入库实现*/
		public int update(String standardId,SingleStandardLibraryData single)
		{
			String oldStandardNo = single.getOldStandardNo();
			String newStandardNo=single.getStandardNo();
			if(!StringUtils.isBlank(oldStandardNo)){
				EntityStandardLibrary old= dao.selectByStandardNo(oldStandardNo);
				if(old==null){
					return 0;
				}
				old.setNewStandardId(standardId);
				old.setNewStandardNo(newStandardNo);
				dao.update(old);
			}
		    EntityStandardLibrary  entityStandardLibrary= new EntityStandardLibrary();
		    BeanUtils.copyProperties(single, entityStandardLibrary);
		    entityStandardLibrary.setStandardId(standardId);
		    return dao.update(entityStandardLibrary); 
		}
	/**升级入库实现*/
	public int addByUpgrade(SingleStandardLibraryUpdateData single) {
		EntityStandardLibrary  entityStandardLibrary= new EntityStandardLibrary();
		 BeanUtils.copyProperties(single, entityStandardLibrary);

		return dao.insert(entityStandardLibrary);
	}
	/** 通过标准编号删除标准 */
	public int deleteByStandardNo(String standardNo) {
		return dao.deleteByStandardNo(standardNo);
	}
	/** 通过标准编号获取标准详情*/
	public SingleStandardLibraryData getDataByStandardNo(String standardNo) {
		EntityStandardLibrary  entityStandardLibrary=dao.selectByStandardNo(standardNo);
		if (entityStandardLibrary==null) {
			return null;
		}
		String Id=entityStandardLibrary.getStandardId();
		SingleStandardLibraryData single = new SingleStandardLibraryData();
		 BeanUtils.copyProperties(entityStandardLibrary, single);
		single.setStandardId(Id);
		return single;
	}

	/** 增加标准所属的体系表位置 <br>
	 *  SStl1原体系表位置，SStl2需要增加的体系表位置<br>
	 * 	0为失败 <br>
	 * 	大于0为成功 <br>
	 * */
	public int addSSTLoaction(String standardId,String SStl) {
		EntityStandardLibrary entityStandardLibrary = dao.selectById(standardId);
		if(entityStandardLibrary==null){
			return -1;
		}
		if(SStl==null){
			SStl="";
		}
		String SStl1 = entityStandardLibrary.getSSTLoaction();
		SStl1+=SStl+",";
		entityStandardLibrary.setSSTLoaction(SStl1);
		return dao.updateStandardNo(entityStandardLibrary);
	}
	/** 取消标准所属的体系表位置 
	 *  /** 修改或增加标准所属的体系表位置 <br>
	 *  SStl1原体系表位置，SStl2需要取消的体系表位置<br>
	 * 	0为失败<br>
	 * 	大于0为成功<br>
	 * */
	public int deleteSSTLoaction(String standardId,String SStl) {
		EntityStandardLibrary entityStandardLibrary =dao.selectById(standardId);
		if(entityStandardLibrary==null){
			return -1;
		}
		String SStl1 = entityStandardLibrary.getSSTLoaction();
		SStl=SStl+",";
		SStl1.replace(SStl, "");
		entityStandardLibrary.setSSTLoaction(SStl1);
		return dao.updateStandardNo(entityStandardLibrary);
	}
	/** 修改标准所属的体系表位置 <br>
	 *  SStl需要修改的体系表位置<br>
	 * 	0为失败 <br>
	 * 	大于0为成功 <br>
	 * */
	public int updateSSTLoaction(String standardId, String SStl) {
		EntityStandardLibrary entityStandardLibrary =dao.selectById(standardId);
		if(entityStandardLibrary==null){
			return -1;
		}
		String SStl1 = entityStandardLibrary.getSSTLoaction();
		SStl=","+SStl+",";
		SStl1.replace(SStl1, SStl);
		entityStandardLibrary.setSSTLoaction(SStl1);
		return dao.updateStandardNo(entityStandardLibrary);
	}
	public SingleStandardLibraryData getNewSingleObj() {
		return new SingleStandardLibraryData();
	}
	public EntityStandardLibrary getNewEntityObj() {
		return new EntityStandardLibrary();
	}
	
	public List<SingleStandardLibraryData> selectBySSTLoaction(int page,
			int pagecount, ConditionGroup cond, OrderGroup order) {
		cond = super.exchangeMappingToField(cond);
        order =super.exchangeMappingToField(order);
        String sql =new QueryResolver().getWhereSql(cond,order).toString();
        Page Page=new Page();
        Page.setPageNo(page);
        Page.setPageSize(pagecount);
        Page.setSql(sql);
        List<EntityStandardLibrary> entityStandardLibraryDataList=this.dao.selectPageList(Page);
        List<SingleStandardLibraryData> singleStandardLibraryDataList=new ArrayList<SingleStandardLibraryData>();
        if(entityStandardLibraryDataList!=null){
        	SingleStandardLibraryData singleStandardLibraryData=null;
        	for (EntityStandardLibrary entityStandardLibrary : entityStandardLibraryDataList){
        		singleStandardLibraryData=new SingleStandardLibraryData();
        		 BeanUtils.copyProperties(entityStandardLibrary, singleStandardLibraryData);
        		singleStandardLibraryData.setStandardId(entityStandardLibrary.getStandardId());
        		singleStandardLibraryDataList.add(singleStandardLibraryData);
        	}
        }
		return singleStandardLibraryDataList;
	}
	public List<HashMap<String, String>> searchStatement() {
		List<HashMap<String, String>> list=dao.selectStatement();
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	public int update(SingleStandardLibraryData single) {
		 EntityStandardLibrary  entityStandardLibrary= new EntityStandardLibrary();
		    BeanUtils.copyProperties(single, entityStandardLibrary);
		return dao.update(entityStandardLibrary);
	}
	public List<HashMap<String, String>> searchMoreStandard(String str) {
		String[] arr=str.split(",");
		List<EntityStandardLibrary> list=dao.selectMoreId(arr);
		List<HashMap<String,String>> datalist=new ArrayList<HashMap<String,String>>();
		for (EntityStandardLibrary entity : list) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id", entity.getStandardId());
			map.put("standardNo", entity.getStandardNo());
			map.put("filePath", entity.getFilePath());
			map.put("oldStandardNo", entity.getOldStandardNo());
			map.put("standardName", entity.getStandardName());
			datalist.add(map);
		}
		return datalist;
	}
	/**
	 * 更新浏览量
	 */
	public int updateBrowseVolume(String standardId){
	    return dao.updateBrowseVolume(standardId);
	}
}
