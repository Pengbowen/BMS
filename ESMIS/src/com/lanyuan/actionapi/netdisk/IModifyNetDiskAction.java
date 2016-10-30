package com.lanyuan.actionapi.netdisk;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * @Description: 根据唯一对象编号，修改文件夹，文件名称
 * @author WYL
 * @date 2016-8-25 下午2:00:07
 */
public class IModifyNetDiskAction extends ResultOperateAction
{
	private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNetdisk netdiskService;
    /**
     * 对象编号
     */
    private String objectNo;
    /**
	 * 所属目录
	 */
	private String parentobjectNo;
	/**
	 * 对象名称
	 */
	private String objectName;
    
    public String modifyNetDisk(){
    	if (StringUtils.isBlank(objectNo))
        {
            this.printString("2", "对象编号为空！");
            return Action.SUCCESS;
        }
        if (StringUtils.isBlank(objectName))
        {
            this.printString("2", "对象名称为空！");
            return Action.SUCCESS;
        }
        boolean flag=netdiskService.isOnlyParentObjectByName(objectNo,parentobjectNo,objectName);
        if(!flag){
        	this.printString("3", "对象名称相同！");
            return Action.SUCCESS;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                this.printString( "-4", "非法访问！");
                return Action.SUCCESS;
            }
        }
        SingleNetdisk singleNetdisk = new SingleNetdisk();
        // 获取当前操作人信息
        OperatorInfo operater = this.getCurrentOperator();
        /* 最后修改人 */
        String modifyer = operater.getOperator();
        /* 最后修改人姓名 */
        String modifyerName = operater.getOperatorName();
        /* 最后修改时间*/
        String modifyTime = operater.getOperateTime("yyyy-MM-dd HH:mm:ss");
        /* 最后修改IP */
        String modifyIP = operater.getOperateIp();
        singleNetdisk.setObjectNo(objectNo);
        singleNetdisk.setParentobjectNo(parentobjectNo);
        singleNetdisk.setObjectName(objectName);
        singleNetdisk.setModifyer(modifyer);
        singleNetdisk.setModifyerName(modifyerName);
        singleNetdisk.setModifyTime(modifyTime);
        singleNetdisk.setModifyIP(modifyIP);
        int data = netdiskService.modifyParentObject(singleNetdisk);
        if (data > 0)
        {
            this.printString("1", "修改成功！");
            return Action.SUCCESS;
        }
        else
        {
            this.printString("2", "修改失败！");
            return Action.SUCCESS;
        }
    	
    }

	public String getObjectNo() {
		return objectNo;
	}

	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}

	public String getParentobjectNo() {
		return parentobjectNo;
	}

	public void setParentobjectNo(String parentobjectNo) {
		this.parentobjectNo = parentobjectNo;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
   
    
}
