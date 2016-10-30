package com.lanyuan.assembly.lawslibrary;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.common.ServersManager;

@Service("lawsService")
public class ServiceLawsImpl extends ServersManager<SingleLaws,EntityLaws, DaoLaws>
		implements ServiceLaws {
	@Autowired
	public void setDao(DaoLaws dao) {
		this.dao = dao;
	}
	
	/**
	 * 更新浏览量
	 */
	public int updateBrowseVolume(String lawsId){
	    return dao.updateBrowseVolume(lawsId);
	}
	
	/**
	 * 作废
	 */
	public int cancellation(String lawsId){
	    return dao.cancellation(lawsId);
	}
	
	/**
	 * 获取最大lawsId
	 * 
	 * @return
	 */
	public String getMaxLawsId()
    {
	    String maxId = dao.getMaxstandardId();
	    if(maxId != null && "9".equals(maxId.substring(0,1))){
	        Integer lawsId = Integer.valueOf(maxId);
	        lawsId += 1;
	        return lawsId.toString();
	    }
        return "920000001";
    }
	
    @Override
    public SingleLaws getNewSingleObj()
    {
        return new SingleLaws();
    }
    @Override
    public EntityLaws getNewEntityObj()
    {
        return new EntityLaws();
    }

    /**
     * 获取统计法律法规类别数量
     * 
     * @return
     */
	public List<HashMap<String, String>> selectLawsStatement() {
		List<HashMap<String, String>> list=dao.selectLawsStatement();
		if(list==null||list.isEmpty()){
			return null;
		}
		return list;
	}

}