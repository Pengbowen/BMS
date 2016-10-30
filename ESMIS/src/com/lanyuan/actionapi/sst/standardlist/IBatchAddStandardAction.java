package com.lanyuan.actionapi.sst.standardlist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.actionapi.sst.FileResolve;
import com.lanyuan.assembly.basic.baseclasses.ResultMessage;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.lanyuan.web.LoginAuthentication.LoginUser;
/**
 * 批量添加标准接口
 * @author PBW
 *
 */
public class IBatchAddStandardAction extends ResultOperateAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceSSTSubClassify serviceSubClassify;
	@Autowired
	private ServiceSSTLayerItems layerItemService;
	@Autowired
	private ServiceSSTStandardList standardService;

	private String savedpath;
	
	public String execute(){
		
		return this.SUCCESS;
	}
	public void batchAdd(){
		List<List<Map<String, String>>> data;
		 ServletContext sctx = ServletActionContext.getServletContext();
	     String  filepath = sctx.getRealPath(savedpath);
		//ResultMessage msg = new ResultMessage(1, "添加成功");
		//ResultMessage errorMsg = new ResultMessage(0, "添加失败");
		try {
			data = FileResolve.readExcelWithTitle(filepath);
			if(data!=null){
				List<Map<String,String>> items = data.get(0);
				for(int i=0;i<items.size();i++){
					Map<String,String> map = items.get(i);
					String belongItemId = map.get("所属项目/分类编号(2选1)");
					int index = belongItemId.indexOf(".");
					belongItemId = belongItemId.substring(0, index);
					SingleSSTStandardList s = generateBean(belongItemId);
					s.setStandardNo(map.get("标准编号"));
					int j = standardService.insert(s);
					if(j<=0){
						this.printString("0", "添加失败");
					}
				}
			}
			this.printString("1", "添加成功");
			System.out.println("成功成功1111111111");
		} catch (Exception e) {
			e.printStackTrace();
			this.printString("0", "添加失败");
		}finally{
			File file = new File(filepath);
			file.delete();
		}
	
	}
	/**
	 * 根据所属父级编号,生成子项目实体
	 * @return
	 */
	private SingleSSTStandardList generateBean(String belongItemId){
		//验证非法登录
		 HttpServletRequest request = ServletActionContext.getRequest();
	        LoginUser user = null;
	        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
	        {
	            if (user == null)
	            {
	                printString( "-4", "非法访问！");
	                return null;
	            }
	        }
	        
	     int SSTId = 0;
	     int layerNo = 0;
	     int displayOrder= 0;
	        
	        
		//判断是项目编号还是子分类编号
		if(!belongItemId.contains("/")){
		//如果是项目编号
			SingleSSTLayerItems s = new SingleSSTLayerItems();
			s = layerItemService.selectById(belongItemId);
			if(s==null){//父项目编号不存在
				return null;
			}
			  SSTId = s.getSSTId(); //所属体系表编号与父项目相同
			 layerNo = s.getLayerNo()+1;//所属层级 = 父项目层级+1
			 int maxDisplayOrder =   standardService.selectMaxDisplayOrderByItemId(belongItemId);
				 displayOrder = maxDisplayOrder+1;
			
		}else{
			String subClassId = belongItemId.replace("/","");
		//如果是子分类编号
			SingleSSTSubClassify s = serviceSubClassify.selectById(subClassId);
			if(s==null){//父项目编号不存在
				return null;
			}
			  SSTId = s.getSSTId(); //所属体系表编号与父项目相同
			 layerNo = s.getLayerNo()+1;//所属层级 = 父项目层级+1
			 int maxDisplayOrder =   standardService.selectMaxDisplayOrderBySubClassId(belongItemId);
				displayOrder = maxDisplayOrder+1;
		}
		
			OperatorInfo operator = this.getCurrentOperator();
			String modifyer = operator.getOperator();	//修改人
			String modifyerName = operator.getOperatorName();//修改人名称
			String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");//修改时间
			String modifyIp = operator.getOperateIp();//修改人IP
			SingleSSTStandardList x = new SingleSSTStandardList();
			x.setSSTId(SSTId);
			x.setLayerNo(layerNo);
			x.setDisplayOrder(displayOrder);
			x.setModifyer(modifyer);
			x.setModifyerName(modifyerName);
			x.setModifyTime(modifyTime);
			x.setModifyIP(modifyIp);
			
		return x;
	}
	public String getSavedpath() {
		return savedpath;
	}
	public void setSavedpath(String savedpath) {
		this.savedpath = savedpath;
	}
	
	
}