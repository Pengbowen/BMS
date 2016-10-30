package com.lanyuan.actionapi.sst.standardlist;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.actionapi.sst.FileResolve;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
/**
 * 批量调整顺序
 * @author Administrator
 *
 */
public class ISetPositionAction extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceSSTStandardList standardService;

	private String savedpath;
	
	
	/**
	 * 读取用户上传的xls.xlsx文件,解析出数据,并按照顺序修改显示顺序
	 */
	public void setDisplayOrder(){
		List<List<Map<String, String>>> data;
		//将传来的savedpath相对路径转换为绝对路径
		ServletContext sctx = ServletActionContext.getServletContext();
	     String  filepath = sctx.getRealPath(savedpath);
		try {
			//读取数据
			data = FileResolve.readExcelWithTitle(filepath);
			//如果有数据
			if(data!=null){
				//取sheet1
				List<Map<String,String>> items = data.get(0);
				for(int i=0;i<items.size();i++){
					//取得一行数据
					Map<String,String> map = items.get(i);
					//读取标准Id
					String standardId = map.get("标准Id");
					//查询到这个标准
					SingleSSTStandardList standard = standardService.selectById(standardId);
					//给标准设置显示顺序
					standard.setDisplayOrder(i+1);
					standardService.update(standard);
				}
				this.printString("1", "调整成功");
			}else{
				this.printString("0", "调整失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			File file = new File(filepath);
			file.delete();
		}
	}


	public String getSavedpath() {
		return savedpath;
	}


	public void setSavedpath(String savedpath) {
		this.savedpath = savedpath;
	}

	
}
