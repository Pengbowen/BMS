package com.lanyuan.actionapi.sst.layeritems;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * 新增层项目接口
 * @author PBW
 *
 */
public class IAddLayerItemAction extends ResultOperateAction
{

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceSSTLayerItems layerItemService;

    private Integer SSTId;
    // 项目编号
    private String layerItemId;
    // 项目名称
    // private String layerItemName;
    // 所属父级
    private String belongItemId;
    // 所属层级
    // private String layerNo;
    // 显示顺序
    private String displayOrder;
    // 可用状态
    private String isEnabled;
    // 显示状态
    private String isVisible;

    // 项目名称
    private String layerItemName1;
    // 所属层级
    private String layerNo1;
    
    private String layerItemNo;

    // 状态表示 1-添加根项目 2-修改 3,添加子项目
    private String state;
    //体系表类型,0代表无从属关系,1代表有从属关系
    private String SSTType;

    public String execute()
    {
        state = "1";
        return Action.SUCCESS;
    }

    public void saveLayerItem()
    {
        // 验证非法登录
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                printString("-4", "非法访问！");
                return;
            }
        }
        SingleSSTLayerItems e = new SingleSSTLayerItems();
        e.setSSTId(SSTId);
        e.setLayerItemId(layerItemId);
        e.setLayerItemName(layerItemName1);
        e.setLayerNo(Integer.parseInt(layerNo1));
        e.setBelongItemId(belongItemId);
        e.setDisplayOrder(Integer.parseInt(displayOrder));
        e.setIsEnabled(Integer.parseInt(isEnabled));
        e.setIsVisible(Integer.parseInt(isVisible));
        e.setLayerItemNo(layerItemNo);//设置项目号码
        OperatorInfo operator = this.getCurrentOperator();
        String modifyer = operator.getOperator();
        String modifyerName = operator.getOperatorName();
        String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
        String modifyIp = operator.getOperateIp();

        e.setModifyer(modifyer);
        e.setModifyerName(modifyerName);
        e.setModifyTime(modifyTime);
        e.setModifyIP(modifyIp);
        int i = layerItemService.insert(e);
        if (i > 0)
        {
            if (!StringUtils.isBlank(belongItemId))
            {
                // 级联修改父级,子项目数量
                e = layerItemService.selectById(belongItemId);
                i = layerItemService.update(e);
            }
            if (i > 0)
            {
                printString("1", "增加成功");
            }
            else
            {
                printString("0", "增加失败");
            }
        }
        else
        {
            printString("0", "增加失败");
        }
    }
    
    
    public Integer getSSTId()
    {
        return SSTId;
    }

    public void setSSTId(Integer sSTId)
    {
        SSTId = sSTId;
    }

    public String getLayerItemId()
    {
        return layerItemId;
    }

    public void setLayerItemId(String layerItemId)
    {
        this.layerItemId = layerItemId;
    }

    public String getBelongItemId()
    {
        return belongItemId;
    }

    public void setBelongItemId(String belongItemId)
    {
        this.belongItemId = belongItemId;
    }

    public String getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    public String getIsEnabled()
    {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public String getIsVisible()
    {
        return isVisible;
    }

    public void setIsVisible(String isVisible)
    {
        this.isVisible = isVisible;
    }

    public String getLayerItemName1()
    {
        return layerItemName1;
    }

    public void setLayerItemName1(String layerItemName1)
    {
        this.layerItemName1 = layerItemName1;
    }

    public String getLayerNo1()
    {
        return layerNo1;
    }

    public void setLayerNo1(String layerNo1)
    {
        this.layerNo1 = layerNo1;
    }
    
    public String getLayerItemNo() {
		return layerItemNo;
	}

	public void setLayerItemNo(String layerItemNo) {
		this.layerItemNo = layerItemNo;
	}

	public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

	public String getSSTType() {
		return SSTType;
	}

	public void setSSTType(String sSTType) {
		SSTType = sSTType;
	}
    
}
