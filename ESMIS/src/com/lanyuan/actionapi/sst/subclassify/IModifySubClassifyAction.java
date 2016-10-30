package com.lanyuan.actionapi.sst.subclassify;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

public class IModifySubClassifyAction extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;
    // 子分类Id
    private String subClassId;
    // 所属项目
    private String layerItemId;
    // 所属体系表
    private Integer SSTId;
    // 子分类名称
    private String subClassName;
    // 显示顺序
    private Integer displayOrder;
    // 可用状态
    private Integer isEnabled;
    // 显示状态
    private Integer isVisible;

    @Autowired
    private ServiceSSTSubClassify serviceSubClassify;

    /**
     * 跳转到编辑页面
     */
    public String execute()
    {
        SingleSSTSubClassify e = serviceSubClassify.selectById(subClassId);
        if (e != null)
        {
            layerItemId = e.getLayerItemId();
            subClassName = e.getSubClassName();
            displayOrder = e.getDisplayOrder();
            isEnabled = e.getIsEnabled();
            isVisible = e.getIsVisible();
            SSTId = e.getSSTId();
        }
        else
        {
            printString(null, 0, "200", "未找到子分类信息");
        }
        return Action.SUCCESS;
    }

    public void modifySubClassify()
    {
        // 验证非法登录
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                printString(null, 0, "-4", "非法访问！");
                return;
            }
        }
        SingleSSTSubClassify e = serviceSubClassify.selectById(subClassId);
        e.setLayerItemId(layerItemId);
        e.setSubClassName(subClassName);
        e.setDisplayOrder(displayOrder);
        e.setIsEnabled(isEnabled);
        e.setIsVisible(isVisible);

        OperatorInfo operator = this.getCurrentOperator();
        String modifyer = operator.getOperator();
        String modifyerName = operator.getOperatorName();
        String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
        String modifyIp = operator.getOperateIp();

        e.setModifyer(modifyer);
        e.setModifyerName(modifyerName);
        e.setModifyTime(modifyTime);
        e.setModifyIP(modifyIp);

        int i = serviceSubClassify.update(e);
        if (i > 0)
        {
            printString(null, 0, "1", "修改成功");
        }
        else
        {
            printString(null, 0, "0", "修改失败");
        }

    }

    public String getSubClassId()
    {
        return subClassId;
    }

    public void setSubClassId(String subClassId)
    {
        this.subClassId = subClassId;
    }

    public String getLayerItemId()
    {
        return layerItemId;
    }

    public void setLayerItemId(String layerItemId)
    {
        this.layerItemId = layerItemId;
    }

    public Integer getSSTId()
    {
        return SSTId;
    }

    public void setSSTId(Integer sSTId)
    {
        SSTId = sSTId;
    }

    public String getSubClassName()
    {
        return subClassName;
    }

    public void setSubClassName(String subClassName)
    {
        this.subClassName = subClassName;
    }

    public Integer getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    public Integer getIsEnabled()
    {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public Integer getIsVisible()
    {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible)
    {
        this.isVisible = isVisible;
    }

}
