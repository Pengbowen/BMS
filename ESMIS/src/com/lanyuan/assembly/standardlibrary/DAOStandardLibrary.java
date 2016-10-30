package com.lanyuan.assembly.standardlibrary;

import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.common.DaoManager;

/**
 * 标准总库接口
 * 
 * @author afl
 * @date Mon Aug 22 16:20:48 CST 2016
 */
interface DAOStandardLibrary extends DaoManager<EntityStandardLibrary> 
 {
	 /**
     * 根据标准编号查询一条记录
     * @param LawsNo
     * @return SingleLawsLibrary
     */
    public EntityStandardLibrary selectByStandardNo(String standardNo);
    /**
     * 通过标准编号删除标准
     * @param lawsNo
     * @return
     */
    public int deleteByStandardNo(String standardNo);
	 /**
     * 根据标准编号修改一条记录
     * @param LawsNo
     * @return SingleLawsLibrary
     */
    public int updateStandardNo(EntityStandardLibrary entityStandardLibrary);
    /**
     * 查询统计标准语句的方法
     */
    public List<HashMap<String, String>> selectStatement();
    /**
     * 查询9开头最大的Id
     */
    public String  selectMaxId();
    /**
     * 通过大量Id批量查询标准
     */
    public List<EntityStandardLibrary> selectMoreId(String[] arrStr);
    /**
     * 更新浏览量
     * 
     * @param lawsId
     * @return
     */
    public int updateBrowseVolume(String standardId);
 }