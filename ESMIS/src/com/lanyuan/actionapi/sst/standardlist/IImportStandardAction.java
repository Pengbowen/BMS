package com.lanyuan.actionapi.sst.standardlist;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.util.StrUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;
/**
 * 导入标准接口
 * @author pbw
 *
 */
public class IImportStandardAction extends ResultOperateAction {

	private static final long serialVersionUID = 1L;
	
	//体系表id
	private Integer SSTId;
	
	//层项目Id
	private String layerItemId;
	
	//子分类id
	private String subClassId;
	
	//数据串
	private String dataStr;
	
	@Autowired
	private ServiceSSTStandardList standardService;
	@Autowired
	private ServiceStandardLibrary standardLibraryService;
	
	
	@SuppressWarnings("unused")
	public String execute(){
		//验证非法登录
		 HttpServletRequest request = ServletActionContext.getRequest();
	        LoginUser user = null;
	        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
	        {
	            if (user == null)
	            {
	                printString( "-4", "非法访问！");
	                return this.SUCCESS;
	            }
	        }
		//获取登录人信息
	    	OperatorInfo operator = this.getCurrentOperator();
			String modifyer = operator.getOperator();
			String modifyerName = operator.getOperatorName();
			String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
			String modifyIp = operator.getOperateIp();
		
		
		if(StrUtil.isEmpty(dataStr)){
			printString("-1", "缺少数据,导入失败!");
			  return this.SUCCESS;
		}
		//验证SSTId,layerItemId,subClassId是否为空
		if(SSTId == null){
			printString("-1", "缺少体系表Id,导入失败!");
			return this.SUCCESS;
		}
		if(StrUtil.isEmpty(layerItemId) && StrUtil.isEmpty(subClassId)){
			printString("-1", "数据异常,导入失败!");
			return this.SUCCESS;
		}
		//数据量
		int count =0;
		//如果层项目编号不为空且子分类编号为空
		if(!StrUtil.isEmpty(layerItemId)  && StrUtil.isEmpty(subClassId)){
			//将数据串分割,并转存入list集合中
			List<String> standardIdList = Arrays.asList(dataStr.split(","));
			for(String standardId:standardIdList){
				//判断此标准是否存在,如果存在则直接进行下一次循环
				if(standardService.isExistInLayerItem(standardId, layerItemId)){
					continue;
				}
				SingleSSTStandardList standard = new SingleSSTStandardList();
				Integer displayOrder = 0;
				//设置体系表Id
				standard.setsSTId(SSTId);
				//设置层项目Id
				standard.setLayerItemId(layerItemId);
				//获取本层项目下最大显示序号
				Integer max = standardService.getMaxDisplayOrder(layerItemId);
				if(max==null){
					displayOrder =1;
				}else{
					displayOrder=max+1;
				}
				//设置标准id
				standard.setStandardId(standardId);
				//设置标准编号/标准类别
				SingleStandardLibraryData data = standardLibraryService.selectById(standardId);
				String standardNo = data.getStandardNo();
				String standardCategory = data.getStandardCategory();
				
				standard.setStandardNo(standardNo);
				standard.setStandardCategory(standardCategory);
				//设置显示顺序
				standard.setDisplayOrder(displayOrder);
				//设置最后修改人
				standard.setModifyer(modifyer);
				//设置最后修改人名称
				standard.setModifyerName(modifyerName);
				//设置最后修改时间
				standard.setModifyTime(modifyTime);
				//设置最后修改Ip
				standard.setModifyIP(modifyIp);
				
				int  i = standardService.insert(standard);
				//给标准添加体系表位置
				standardLibraryService.addSSTLoaction(standardId,layerItemId);
				
				if(i<1){
					printString("-1", "保存数据错误");
					  return this.SUCCESS;
				}else{
					count+=i;
				}
			}
		}
		//当子分类编号不为空且层项目编号为空
		if(!StrUtil.isEmpty(subClassId) && StrUtil.isEmpty(layerItemId)){

			//将数据串分割,并转存入list集合中
			List<String> standardIdList = Arrays.asList(dataStr.split(","));
			for(String standardId:standardIdList){
				//判断此标准是否存在,如果存在则直接进行下一次循环
				if(standardService.isExistInSubClassId(standardId, layerItemId)){
					continue;
				}
				
				SingleSSTStandardList standard = new SingleSSTStandardList();
				Integer displayOrder = 0;
				//设置体系表Id
				standard.setsSTId(SSTId);
				//设置子分类id
				if(!StrUtil.isEmpty(subClassId)){
				standard.setStandardId(subClassId);
				//获取本子分类下最大显示序号
				Integer max = standardService.getMaxDisplayOrderBySubClassId(subClassId);
				if(max==null){
					displayOrder =1;
				}else{
					displayOrder=max+1;
				}
				}
				//设置标准id
				standard.setStandardId(standardId);
				//设置标准编号/标准类别
				SingleStandardLibraryData data = standardLibraryService.selectById(standardId);
				String standardNo = data.getStandardNo();
				String standardCategory = data.getStandardCategory();
				
				standard.setStandardNo(standardNo);
				standard.setStandardCategory(standardCategory);
				//设置显示顺序
				standard.setDisplayOrder(displayOrder);
				//设置最后修改人
				standard.setModifyer(modifyer);
				//设置最后修改人名称
				standard.setModifyerName(modifyerName);
				//设置最后修改时间
				standard.setModifyTime(modifyTime);
				//设置最后修改Ip
				standard.setModifyIP(modifyIp);
				
				int  i = standardService.insert(standard);
				standardLibraryService.addSSTLoaction(standardId,subClassId);
				if(i<1){
					printString("-1", "保存数据错误");
					  return this.SUCCESS;
				}else{
					count+=i;
				}
			}
		}
	//循环完成
		printString("1", "成功导入"+count+"条标准");
		return this.SUCCESS;
	}
	
	public Integer getSSTId() {
		return SSTId;
	}




	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}




	public String getLayerItemId() {
		return layerItemId;
	}




	public void setLayerItemId(String layerItemId) {
		this.layerItemId = layerItemId;
	}




	public String getSubClassId() {
		return subClassId;
	}




	public void setSubClassId(String subClassId) {
		this.subClassId = subClassId;
	}




	public String getDataStr() {
		return dataStr;
	}




	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
	
	
	
	
}
