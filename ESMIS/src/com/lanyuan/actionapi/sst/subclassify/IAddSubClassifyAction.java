package com.lanyuan.actionapi.sst.subclassify;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 添加子分类
 * 
 * @author PBW
 * 
 */
public class IAddSubClassifyAction extends ResultOperateAction
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
    @Autowired
    private ServiceSSTLayerItems layerItemService;

    /**
     * 执行方法,跳转到添加子分类界面
     */
    public String execute()
    {
        return ActionSupport.SUCCESS;
    }

    /**
     * 添加子分类方法
     */
    public void addSubClassify()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                this.printString("-4", "非法访问！");
                return;
            }
        }
        // 找到项目的所属层级
        int itemLayerNo = Integer.parseInt(String.valueOf(layerItemId.charAt(1)));
        int layerNo = itemLayerNo+1;
        String temp = "";
        Integer maxOrder = serviceSubClassify.selectMaxLayerDisOrder(layerItemId);
        if(maxOrder==null || maxOrder==0){
        	maxOrder=1;
        	temp="01";
        }else{
        	maxOrder+=1;
        	 if (maxOrder < 10)
             {
                 temp = "0" + maxOrder;
             }
             else
             {
                 temp = maxOrder + "";
             }
        	
        	
        }
        
        String subClassId = layerItemId+temp;
        // 判断这个subClassId是否存在
        SingleSSTSubClassify x = serviceSubClassify.selectById(subClassId);
        if (x != null)
        {
            printString("0", "添加失败，此显示顺序已存在");
            return;
        }
        SingleSSTSubClassify e = new SingleSSTSubClassify();
        e.setLayerItemId(layerItemId);
        e.setSubClassName(subClassName);
        e.setDisplayOrder(displayOrder);
        e.setIsEnabled(isEnabled);
        e.setIsVisible(isVisible);
        e.setSSTId(SSTId);
        e.setLayerNo(layerNo);
        e.setSubClassId(subClassId);
        // 添加标准数量stanardCount pbw
        OperatorInfo operator = this.getCurrentOperator();
        String modifyer = operator.getOperator();
        String modifyerName = operator.getOperatorName();
        String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");
        String modifyIp = operator.getOperateIp();

        e.setModifyer(modifyer);
        e.setModifyerName(modifyerName);
        e.setModifyTime(modifyTime);
        e.setModifyIP(modifyIp);

        int i = serviceSubClassify.insert(e);
        if (i > 0)
        {
            // 级联修改子分类信息
            SingleSSTLayerItems singleSSTLayerItems = layerItemService.selectById(layerItemId);
            i = layerItemService.update(singleSSTLayerItems);
            if (i > 0)
            {
                printString("1", "添加成功");
            }
            else
            {
                printString("0", "添加失败");
            }
        }
        else
        {
            printString("0", "添加失败");
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
