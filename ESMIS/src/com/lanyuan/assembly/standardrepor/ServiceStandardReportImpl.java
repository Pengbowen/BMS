package com.lanyuan.assembly.standardrepor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.lawslibrary.EntityLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;

@Service("standardReportService")
public class ServiceStandardReportImpl extends
        ServersManager<SingleStandardReport, EntityStandardReport, DaoStandardReport> implements
        ServiceStandardReport
{
    @Autowired
    public void setDao(DaoStandardReport dao)
    {
        this.dao = dao;
    }

    public SingleStandardReport getNewSingleObj()
    {
        return new SingleStandardReport();
    }

    public EntityStandardReport getNewEntityObj()
    {
        return new EntityStandardReport();
    } 

    @Transactional
    public int insertbatch(List<SingleStandardReport> list,String reportId)
    {
        //发布之前删除表中所有数据
        deleteById(reportId);       
        return dao.insertbatch(list);
    }
    /**
     * 查出统计数据
     */
	public List<SingleStandardReport> selectByReportId(String reportId) {
		List<SingleStandardReport> datalist = new ArrayList<SingleStandardReport>();
		List<EntityStandardReport> list=dao.selectByReportId(reportId);
		for (EntityStandardReport entity : list) {
			SingleStandardReport single = new SingleStandardReport();
			BeanUtils.copyProperties(entity, single);
			datalist.add(single);
		}
		return datalist;
	}
}
