package com.lanyuan.assembly.standardrepor;
import java.util.List;

import com.lanyuan.assembly.common.DaoManager;
/**
 * DAO类
 * 
 * @ClassName:DAOStandardReport
 * @Description: 增删改查的操作
 * @author sh
 * @date Thu Sep 08 18:56:22 CST 2016
 */
interface DaoStandardReport extends DaoManager<EntityStandardReport> 
{
    //批量添加
    public int insertbatch(List<SingleStandardReport> list);
    
    //通过reportId查询所属统计信息
    public List<EntityStandardReport> selectByReportId(String reportId);
 }