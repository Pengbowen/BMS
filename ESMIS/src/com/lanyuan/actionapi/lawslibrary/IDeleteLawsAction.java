package com.lanyuan.actionapi.lawslibrary;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.opensymphony.xwork2.Action;

/**
 * @Description: 删除法律法规
 * @author
 * @date 2016-8-25 下午2:00:07
 */
public class IDeleteLawsAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceLaws lawsService;
    /**
     * 法规编号
     */
    private String lawsId;

    public String deleteLaws()
    {
        if (StringUtils.isBlank(lawsId))
        {
            this.printString("2", "参数为空！");
            return Action.SUCCESS;
        }
        int flag = lawsService.deleteById(lawsId);
        if (flag > 0)
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

    public String getLawsId()
    {
        return lawsId;
    }

    public void setLawsId(String lawsId)
    {
        this.lawsId = lawsId;
    }
}
