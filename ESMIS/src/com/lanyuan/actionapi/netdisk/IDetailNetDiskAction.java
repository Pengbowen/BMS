package com.lanyuan.actionapi.netdisk;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;
/**
 * @Description:根据对象编号查看文件详情
 * @author WYL
 * @date 2016年8月26日 上午10:45:14
 */
public class IDetailNetDiskAction extends ResultDetailAction
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
	 * 包含子项目数量
	 */
	private Integer chirldItemCount;
	/**
	 * 对象名称
	 */
	private String objectName;
	/**
	 * 对象类别
	 */
	private Integer objectType;
	/**
	 * 文件后缀
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
	
    public String detailNetDisk(){
    	// 判断传值是否为空
    	HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                this.printString(null, "-4", "非法访问！");
            }
        }
        if (StringUtils.isBlank(objectNo))
        {
            this.printString(null, "2", "项目编号为空！");
        }
        SingleNetdisk single = new SingleNetdisk();
        single=netdiskService.selectParentObjectByNo(objectNo);
        // 判断single对象是否为空
        if (single != null)
        {
        	setInfoData(single);
        }else{
        	this.printString(null, "-1", "查询结果为空！");
        }
        return Action.SUCCESS;
    }
        // 定义HashMap对象用来存放查询到的数据
    public void setInfoData(SingleNetdisk single){
        this.objectNo = single.getObjectNo();
        this.parentobjectNo = single.getParentobjectNo();
        this.chirldItemCount = single.getChirldItemCount();
        this.objectName = single.getObjectName();
        this.objectType = single.getObjectType();
        this.objectSuffix = single.getObjectSuffix();
        this.objectSavePath = single.getObjectSavePath();
        this.keyWords = single.getKeyWords();
        this.explains = single.getexplains();
        this.remark = single.getRemark();
        this.modifyer = single.getModifyer();
        this.modifyerName = single.getModifyerName();
        this.modifyTime = single.getModifyTime();
        this.modifyIP = single.getModifyIP();
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
	public Integer getChirldItemCount() {
		return chirldItemCount;
	}
	public void setChirldItemCount(Integer chirldItemCount) {
		this.chirldItemCount = chirldItemCount;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
}
