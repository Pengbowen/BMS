package com.lanyuan.assembly.lawslibrary;

import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.common.DaoManager;

/**
 * DAO类
 * 
 * @ClassName:DAOLogisticsCompany
 * @Description: 增删改查的操作
 * @author sh
 * @date Tue Jul 19 16:50:16 CST 2016
 */
interface DaoLaws extends DaoManager<EntityLaws> {
    
    /**
     * 作废
     * 
     * @param lawsId
     * @return
     */
    public int cancellation(String lawsId);
    
    /**
     * 更新浏览量
     * 
     * @param lawsId
     * @return
     */
    public int updateBrowseVolume(String lawsId);
    
    /**
     * 根据法规编号查询一条记录
     * @param LawsNo
     * @return singleLaws
     */
    //public EntityLaws selectByLawsNo(String lawsNo);
    /**
     * 通过法规编号删除标准
     * @param lawsNo
     * @return
     */
    //public int deleteByLawsNo(String lawsNo);
    
    /**
     * 获取lawsId最大值
     * 
     * @return
     */
    public String getMaxstandardId();
    /**
     * 获取统计法律法规类别数量
     * 
     * @return
     */
    public List<HashMap<String, String>> selectLawsStatement();
    
}