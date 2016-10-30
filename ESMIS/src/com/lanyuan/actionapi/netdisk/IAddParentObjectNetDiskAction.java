 package com.lanyuan.actionapi.netdisk;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;

import javax.servlet.http.HttpServletRequest;

import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;
import com.lanyuan.web.LoginAuthentication.LoginUser;

/**
 * @Description:新增文件目录
 * @author WYL
 * @date 2016年8月26日 上午10:44:19
 */
public class IAddParentObjectNetDiskAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNetdisk netdiskService;
	/**
	 * 所属目录
	 */
	private String parentobjectNo;
	/**
	 * 对象名称
	 */
	private String objectName;
	/**
	 * 对象属性
	 */
	private Integer objectType;
	/**
	 * 对象后缀
	 */
	private String objectSuffix;
	/**
	 * 存放位置
	 */
	private String objectSavePath;
	/**
	 * 关键字
	 */
	private String keyWords;
	/**
	 * 说明
	 */
	private String explains;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 最后修改人
	 */
	private String modifyer;
	/**
	 * 最后修改人姓名
	 */
	private String modifyerName;
	/**
	 * 最后修改时间
	 */
	private String modifyTime;
	/**
	 * 最后修改IP
	 */
	private String modifyIP;
    
    public void addParentObject(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	LoginUser user = null;
    	OperatorInfo operator = this.getCurrentOperator();
        /**
         * 判断是否成功登陆系统
         */
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){printString("-4", "非法访问！");return;}
        }
        else
        {
            operator = this.getCurrentOperator();
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        /**
         * 判断目录名称是否为空
         */
    	if (StringUtils.isBlank(objectName))
        {
            this.printString("2", "目录名称为空！");
            return ;
        }
    	/**
         * 判断类型是否为空
         */
    	if (objectType== null)
        {
            this.printString("2", "对象类型为空！");
            return ;
        }
    	SingleNetdisk singleNetdisk = new SingleNetdisk();
        /* 最后修改人 */
        String modifyer = operator.getOperator();
        /* 最后修改人姓名 */
        String modifyerName = operator.getOperatorName();
        /* 最后修改时间 */
        String modifyTime = operator.getOperateTime("yyyy-MM-dd HH:mm:ss");// new Date()为获取当前系统时间
        /* 最后修改IP */
        String modifyIP = operator.getOperateIp();
        /**objectNo 编码规则是：文件夹（D）+年月日(6)+六位随机数字(6) 如：D160823000001**/
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String times = sdf.format(new Date());
        int n = 0;
        String str = "";
        Integer chirldItemCount = 0; 
        for(int i = 0;i<6;i++){
            n = (int)(0+Math.random()*10);
            str += n + "";
        }
        String objectNo="";
        /**当对象类型为0时即为文件夹D开头**/
        objectNo = "D" + times + str;
        singleNetdisk.setObjectNo(objectNo);
        singleNetdisk.setChirldItemCount(chirldItemCount);
        singleNetdisk.setObjectName(objectName);
        singleNetdisk.setParentobjectNo(parentobjectNo);
        singleNetdisk.setObjectType(objectType);
        singleNetdisk.setObjectSavePath(objectSavePath);
        singleNetdisk.setObjectSuffix(objectSuffix);
        singleNetdisk.setKeyWords(keyWords);
        singleNetdisk.setexplains(explains);
        singleNetdisk.setRemark(remark);
        
        singleNetdisk.setModifyer(modifyer);
        singleNetdisk.setModifyerName(modifyerName);
        singleNetdisk.setModifyTime(modifyTime);
        singleNetdisk.setModifyIP(modifyIP);
        int data = netdiskService.addParentObject(singleNetdisk);
        if (data > 0)
        {
            this.printString("1", "添加成功！");
        }
        else
        {
            this.printString("2", "添加失败！");
        }
    }

	public ServiceNetdisk getNetdiskService() {
		return netdiskService;
	}

	public void setNetdiskService(ServiceNetdisk netdiskService) {
		this.netdiskService = netdiskService;
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


	public String getObjectSavePath() {
		return objectSavePath;
	}

	public void setObjectSavePath(String objectSavePath) {
		this.objectSavePath = objectSavePath;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

	public String getModifyerName() {
		return modifyerName;
	}

	public void setModifyerName(String modifyerName) {
		this.modifyerName = modifyerName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyIP() {
		return modifyIP;
	}

	public void setModifyIP(String modifyIP) {
		this.modifyIP = modifyIP;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getObjectSuffix() {
		return objectSuffix;
	}

	public void setObjectSuffix(String objectSuffix) {
		this.objectSuffix = objectSuffix;
	}
    
}
