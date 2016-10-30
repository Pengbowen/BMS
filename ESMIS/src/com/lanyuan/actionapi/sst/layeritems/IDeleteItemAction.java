package com.lanyuan.actionapi.sst.layeritems;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.opensymphony.xwork2.Action;

/**
 * 删除子项目，并更新父级项目
 * @author ly-two
 * @date 2016-9-21 下午12:26:14
 */
public class IDeleteItemAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;
    private String layerItemId;
    private String belongItemId;
    @Autowired
    private ServiceSSTLayerItems layerItemService;

    public String execute()
    {
        int i = layerItemService.deleteById(layerItemId);
        if (i > 0)
        {
            // 级联修改父级,子项目数量
            if (!StringUtils.isBlank(belongItemId))
            {
                SingleSSTLayerItems singleSSTLayerItems = layerItemService.selectById(belongItemId);
                if (singleSSTLayerItems != null)
                {
                    i = layerItemService.update(singleSSTLayerItems);
                }
            }
            if (i > 0)
            {
                printString("1", "删除成功");
            }
            else
            {
                printString("0", "删除异常");
            }
        }
        else
        {
            printString("0", "删除异常");
        }
        return Action.SUCCESS;
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
}
