package com.lanyuan.actionapi.sst.layeritems;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 设置层项目信息接口
 * 
 * @author PBW
 * @date 2016年8月25日09:17:02
 *
 */
public class IModifyLayerItemAction extends ResultOperateAction{
	private static final long serialVersionUID = 1L;
	//体系表编号
	private Integer SSTId;
	//项目编号
	private String layerItemId;
	//项目名称
//	private String layerItemName;
	//所属父级
	private String belongItemId;
	//所属层级
	//private String layerNo;
	//显示顺序
	private String displayOrder;
	//可用状态
	private String isEnabled;
	//显示状态
	private String isVisible;
	//项目号码
	private String layerItemNo;
	
	//项目名称
	private String layerItemName1;
	//所属层级
	private String layerNo1;
	//状态表示 1-添加根项目 2-修改 3,添加子项目
	private String state;


	@Autowired
	private ServiceSSTLayerItems layerItemService;
	/**
	 * 执行方法,跳转到设置页面
	 */
	public String execute(){
		state="2";
		SingleSSTLayerItems e = layerItemService.selectById(layerItemId);
		System.out.println("可用状态"+e.getIsEnabled());
		SSTId = e.getSSTId();
		layerItemId = e.getLayerItemId();
		layerItemName1 = e.getLayerItemName();
		belongItemId = e.getBelongItemId();
		layerNo1 = e.getLayerNo().toString();
		displayOrder = e.getDisplayOrder().toString();
		isEnabled = e.getIsEnabled().toString();
		isVisible = e.getIsVisible().toString();
		layerItemNo = e.getLayerItemNo();
		return Action.SUCCESS;
	}

	
	
	/**
	 * 修改项目信息
	 */
	public void  modifyLayerItem(){
		//验证非法登录
		 HttpServletRequest request = ServletActionContext.getRequest();
	        LoginUser user = null;
	        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
	        {
	            if (user == null)
	            {
	                printString( "-4", "非法访问！");
	                return ;
	            }
	        }
		SingleSSTLayerItems e = layerItemService.selectById(layerItemId);
//		所属体系表	SSTId                   Y
//		项目编号	layerItemId				Y
//		项目名称	layerItemName			Y
//		所属层级	layerNo					Y
//		所属父级	belongItemId			Y
//		显示顺序	displayOrder			Y
//		包含子项目数量	subItemCount		
//		包含子分类数量	subClassifyCount	
//		包含标准数量	standardCount		
//		可用状态	isEnabled				Y
//		显示状态	isVisible				Y
//		修改人	modifyer				Y
//		修改人姓名	modifyerName			X
//		修改时间	modifyTime				X
//		修改IP	modifyIP				X
		e.setLayerItemId(layerItemId);
		e.setLayerItemName(layerItemName1);
		e.setLayerNo(Integer.parseInt(layerNo1));
		e.setBelongItemId(belongItemId);
		e.setDisplayOrder(Integer.parseInt(displayOrder));
		e.setIsEnabled(Integer.parseInt(isEnabled));
		e.setIsVisible(Integer.parseInt(isVisible));
		//TODO  添加非法登录验证
		OperatorInfo operator = this.getCurrentOperator();
		String modifyer = operator.getOperator();
		String modifyerName = operator.getOperatorName();
		String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
		String modifyIp = operator.getOperateIp();
		
		e.setModifyer(modifyer);
		e.setModifyerName(modifyerName);
		e.setModifyTime(modifyTime);
		e.setModifyIP(modifyIp);
		
		int i = layerItemService.update(e);
		if(i>0){
			printString( "1","修改成功");
		}else{
			printString("0","修改失败");
		}
		
		
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






	public String getBelongItemId() {
		return belongItemId;
	}



	public void setBelongItemId(String belongItemId) {
		this.belongItemId = belongItemId;
	}


	public String getDisplayOrder() {
		return displayOrder;
	}



	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}



	public String getIsEnabled() {
		return isEnabled;
	}



	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}



	public String getIsVisible() {
		return isVisible;
	}



	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}



	public String getLayerItemName1() {
		return layerItemName1;
	}



	public void setLayerItemName1(String layerItemName1) {
		this.layerItemName1 = layerItemName1;
	}



	public String getLayerNo1() {
		return layerNo1;
	}



	public void setLayerNo1(String layerNo1) {
		this.layerNo1 = layerNo1;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getLayerItemNo() {
		return layerItemNo;
	}



	public void setLayerItemNo(String layerItemNo) {
		this.layerItemNo = layerItemNo;
	}

	
	
	
	
}
