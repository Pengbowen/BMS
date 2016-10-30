package com.lanyuan.actionapi.sst.standardlist;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.util.StrUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;

/**
 * TODO 添加标准信息
 * @author PBW
 *
 */
public class IAddStandardAction extends ResultOperateAction {
	
	private static final long serialVersionUID = 1L;
	
	private Integer SSTId;
	private String SSTName;
	private String layerItemId;
	private String subClassId;
	private String standardId;
	private String layerItemName = "无";
	private String subClassName ="无";
	private String documentType;//文件类型  1-标准  2-法律法规

	@Autowired
	private ServiceSSTLayerItems layerItemService;
	@Autowired
	private ServiceSSTSubClassify serviceSubClassify;
	@Autowired
	private ServiceSSTStandardList standardService;
	@Autowired
	private ServiceStandardLibrary standardLibraryService;
	
	public String execute(){
		switch (SSTId){
		case 1:
			SSTName = "技术标准体系表";
			break;
		case 2:
			SSTName = "管理标准体系表";
			break;
		default:
			SSTName = "工作标准体系表";
		}
		if(layerItemId != null && !layerItemId.isEmpty()){
			layerItemName = layerItemService.selectById(layerItemId).getLayerItemName();
		}
		if(subClassId != null && !subClassId.isEmpty()){
			subClassName = serviceSubClassify.selectById(subClassId).getSubClassName();
		}
		return this.SUCCESS;
	}
	
	public String saveStandard(){
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
		SingleSSTStandardList s = new SingleSSTStandardList();
		s.setSSTId(SSTId);
		int displayOrder = 1;
		if(!StrUtil.isEmpty(layerItemId)){
			s.setLayerItemId(layerItemId);
			displayOrder = standardService.getMaxDisplayOrder(layerItemId)+1;
			s.setDisplayOrder(displayOrder);
		}
		if(!StrUtil.isEmpty(subClassId)){
			s.setSubClassId(subClassId);
			displayOrder  = standardService.getMaxDisplayOrderBySubClassId(subClassId);
			s.setDisplayOrder(displayOrder);
		}
		s.setStandardId(standardId);
		OperatorInfo operator = this.getCurrentOperator();
		String modifyer = operator.getOperator();
		String modifyerName = operator.getOperatorName();
		String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
		String modifyIp = operator.getOperateIp();
		s.setModifyer(modifyer);
		s.setModifyIP(modifyIp);
		s.setModifyerName(modifyerName);
		s.setModifyTime(modifyTime);
		
	int i= 	standardService.insert(s);
		if(i<1){
			printString("-1","添加失败");
		}else{
			//TODO 将体系表位置反馈给标准总库
			if(!StrUtil.isEmpty(layerItemId)){
				int y =standardLibraryService.addSSTLoaction(standardId,layerItemId+"/"+s.getDisplayOrder());
				if(y==-1){
					printString("-1","添加体系表位置失败");
				}
			}
			if(!StrUtil.isEmpty(subClassId)){
				int y = standardLibraryService.addSSTLoaction( standardId,subClassId+"/"+s.getDisplayOrder());
				if(y==-1){
					printString("-1","添加体系表位置失败");
				}
			}
			printString("1","添加成功");
		}
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

	public String getLayerItemName() {
		return layerItemName;
	}

	public void setLayerItemName(String layerItemName) {
		this.layerItemName = layerItemName;
	}

	public String getSubClassName() {
		return subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}

	public String getSSTName() {
		return SSTName;
	}

	public void setSSTName(String sSTName) {
		SSTName = sSTName;
	}

	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	

}
