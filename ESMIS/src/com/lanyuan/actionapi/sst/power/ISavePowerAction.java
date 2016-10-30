package com.lanyuan.actionapi.sst.power;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.sst.power.ServiceSSTPower;
import com.lanyuan.assembly.sst.power.SingleSSTPower;
import com.lanyuan.assembly.util.StrUtil;
import com.lanyuan.web.LoginAuthentication.LoginUser;
/**
 * 保存用户权限
 * @author PBW
 *
 */
public class ISavePowerAction extends ResultOperateAction {
	private static final long serialVersionUID = 1L;
	//体系表id
	private Integer SSTId;
	//所选取的项目或者子分类id,用逗号隔开  入 1201,1203,1204
	private String connectno;
	@Autowired
	private ServiceSSTPower powerService;
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){printString("-4", "非法访问！");return null;}
        }
        else
        {
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        String userId = user.getUnitNo();
        List<SingleSSTPower> powerList = new ArrayList<SingleSSTPower>();
		if(!StrUtil.isEmpty(connectno)){
			//分割所有的id
			String[] ids = connectno.split(",");
			for(String powerId :ids){
			SingleSSTPower power = new SingleSSTPower();
				power.setSSTId(SSTId);
				power.setPowerId(powerId);
				power.setUserId(userId);
				powerList.add(power);
			}
		}
		System.out.println(powerList.size());
		if(powerService.batchAddPower(powerList, userId,SSTId)){
			printString("1", "保存成功");
		}else{
			printString("1", "保存失败");
		}
		return this.SUCCESS;
	}
	public Integer getSSTId() {
		return SSTId;
	}
	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}
	public String getConnectno() {
		return connectno;
	}
	public void setConnectno(String connectno) {
		this.connectno = connectno;
	}
	
	

}
