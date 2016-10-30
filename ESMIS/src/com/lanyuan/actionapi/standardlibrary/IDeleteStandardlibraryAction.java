package com.lanyuan.actionapi.standardlibrary;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;

public class IDeleteStandardlibraryAction extends ResultOperateAction{
	 private static final long serialVersionUID = 1L;
	    @Autowired
	    private ServiceStandardLibrary standardLibraryService;
	    /**
		 * 标准id
		 */
		private String standardId;
		/**
		 * 标准编号
		 */
		private String standardNo;
		public  void deleteStandardlibrary(){
			System.out.println(standardNo);
			System.out.println(standardId);
			 if (StringUtils.isBlank(standardNo) && StringUtils.isBlank(standardId))
		        {
		            this.printString("2", "参数为空！");
		            return;
		        }
		        int flag = 0;
		        // 如果standardId为空，则根据编号删除
		        if (StringUtils.isBlank(standardId))
		        {
		            flag = standardLibraryService.deleteByStandardNo(standardNo);
		        }
		        else
		        {
		            // 如果standardId不为空，则根据standardId删除
		            flag = standardLibraryService.deleteById(standardId);
		        }
		        if (flag > 0)
		        {
		            this.printString("1", "删除成功！");
		        }
		        else
		        {
		            this.printString("2", "删除失败！");
		        }
		}
		public String getStandardId() {
			return standardId;
		}
		public void setStandardId(String standardId) {
			this.standardId = standardId;
		}
		public String getStandardNo() {
			return standardNo;
		}
		public void setStandardNo(String standardNo) {
			this.standardNo = standardNo;
		}
		
}
