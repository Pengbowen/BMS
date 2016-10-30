package com.lanyuan.assembly.standardrepor;
import java.util.List;

import com.lanyuan.assembly.common.Services;


public interface ServiceStandardReport extends Services<SingleStandardReport>
{
    //批量添加
    public int insertbatch(List<SingleStandardReport> list,String reportId);
    
    //查询统计数据
    public List<SingleStandardReport> selectByReportId(String reportId);
    
    
        public enum MappingList
	    {
	        /**
	         * 报表编号
	         */
	        reportId(" reportId ") ,
	        /**
	         * 标准类别
	         */
	        standardCategory(" standardCategory ") ,
	        /**
	         * 显示顺序
	         */
	        displayOrder(" displayOrder ") ,
	        /**
	         * 项目编号
	         */
	        itemId(" itemId ") ,
	        /**
	         * 数量
	         */
	        quantity(" quantity ") ;
	        private String value;
	
	        MappingList(String value)
	        {
	            this.value = value;
	        }
	
	        public String getValue()
	        {
	            return this.value;
	        }
	    }
}

