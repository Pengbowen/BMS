package com.lanyuan.actionapi.netdisk;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.netdisk.ServiceNetdisk;
import com.opensymphony.xwork2.Action;

/**
 * @Description:删除文件，文件夹
 * @author WYL
 * @date 2016年8月26日 上午10:44:52
 */
public class IDeleteNetDiskAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNetdisk netdiskService;
    private String objectNo;
    
    public String deleteNetDisk(){
    	if (StringUtils.isBlank(objectNo))
        {
            this.printString("2", "参数为空！");
            return Action.SUCCESS;
        }
        int flag = netdiskService.deleteById(objectNo);
        if (flag == 1)
        {
            this.printString("1", "删除成功！");
            return Action.SUCCESS;
        }
        else
        {
            this.printString("2", "删除失败！");
            return Action.SUCCESS;
        }
    }

	public String getObjectNo() {
		return objectNo;
	}

	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
    
}
