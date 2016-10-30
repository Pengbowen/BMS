package com.lanyuan.actionapi.sst.subclassify;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.opensymphony.xwork2.Action;

/**
 * 删除子分类
 *
 * @author ly-two
 * @date 2016-9-21 下午12:14:43
 */
public class IDeleteSubClassAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;

    private String subClassId;
    private String layerItemId;
    @Autowired
    private ServiceSSTSubClassify serviceSubClassify;
    @Autowired
    private ServiceSSTLayerItems layerItemService;

    public String execute()
    {
        int i = serviceSubClassify.deleteById(subClassId);
        if (i > 0)
        {
            // 级联修改子分类信息
            SingleSSTLayerItems singleSSTLayerItems = layerItemService.selectById(layerItemId);
            i = layerItemService.update(singleSSTLayerItems);
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
}
