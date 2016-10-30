package com.lanyuan.actionapi.netdisk;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.lanyuan.assembly.netdisk.SingleNetdisk;
import com.opensymphony.xwork2.Action;
/**
 * @Description:根据对象编号查找文件名称
 * @author WYL
 * @date 2016年8月26日 上午10:45:14
 */
public class ISearchNameByNoAction extends ResultDetailAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNetdisk netdiskService;
    /**
	 * 对象编号
	 */
	private String objectNo;
	
	private String objectName;
	
	private Integer objectType;
	
	
    public String execute(){
    	
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
        this.objectName = single.getObjectName();
        this.objectType = single.getObjectType();
    }
    
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}
	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	/**
	 * @return the objectType
	 */
	public Integer getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

}
