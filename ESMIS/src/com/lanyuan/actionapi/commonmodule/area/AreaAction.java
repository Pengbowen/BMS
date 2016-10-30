package com.lanyuan.actionapi.commonmodule.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.commonmodule.area.ServiceArea;
import com.lanyuan.assembly.commonmodule.area.SingleArea;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 省市县区域选择的action类
 * 
 * @author zhaohaisheng
 * @date 2014-7-10 下午4:01:13
 */
public class AreaAction extends ActionSupport
{
    private static final long serialVersionUID = 9201113602852644483L;
    /**
     * 父级区域的节点编号<br/>
     * 参数取值：区域编号,null。当为null时，按照target原则获取区域信息<br/>
     * 参数用途：通过该参数，获取其下子区域集合。
     */
    private String ncode;
    /**
     * 区域节点的目标层级。<br/>
     * 参数取值：province,city,null。当为null时，获取ncode的下级区域信息<br/>
     * 参数用途：通过该参数，获取指定node下的省级/市级等的区域信息
     */
    private String target;
    
    /**
     * 返回指定的区域信息集合<br/>
     * Map<String, String>格式说明：{"text":"河南省","value":"4100000"}
     */
    private List<Map<String, String>> data;
    
    /**
     * 定义全局的区域Service对象
     */
    private ServiceArea svcArea;

    public AreaAction()
    {
        svcArea = new ServiceArea();
        data = new ArrayList<Map<String, String>>();
    }
    
    public String execute()
    {
        List<SingleArea> areas = null;
        //当ncode和target均为空时，将返回null
        if (null == ncode || ncode.trim().isEmpty())
        {
            if (null == target || target.trim().isEmpty())
            {
                this.data = null;
                return Action.SUCCESS;
            }
        }
        else
        {
            ServiceArea.EnumAreaType atype;
            atype = this.svcArea.checkAreaTypeByCode(ncode);

            if (null == target || target.trim().isEmpty())
            {
                switch (atype)
                {
                    case province:
                        target = ServiceArea.EnumAreaType.city.name();
                        break;
                    case city:
                        target = ServiceArea.EnumAreaType.county.name();
                        break;
                    default:
                        break;
                }
            }
        }
        
        if (target.equalsIgnoreCase(ServiceArea.EnumAreaType.province.name()))
        {
            areas = this.svcArea.selectAllProvince();
        }
        else if (target.equalsIgnoreCase(ServiceArea.EnumAreaType.city.name()))
        {
            if (null == ncode || ncode.trim().isEmpty())
            {
                areas = this.svcArea.selectAllCity();
            }
            else
            {
                areas = this.svcArea.selectChild(ncode);
            }
        }
        else if (target.equalsIgnoreCase(ServiceArea.EnumAreaType.county.name()))
        {
            if (null == ncode || ncode.trim().isEmpty())
            {
                areas = this.svcArea.selectAllCounty();
            }
            else
            {
                areas = this.svcArea.selectCountyByNCode(ncode);
            }
        }
        if (areas != null)
        {
            Map<String, String> val = null;
            for (SingleArea ar : areas)
            {
                val = new HashMap<String, String>();
                val.put("text", ar.getName());
                val.put("value", ar.getNcode());
                this.data.add(val);
            }
        }
        else
        {
            this.data = null;
        }

        return Action.SUCCESS;
    }

    /**
     * @param 设置 父级区域编号
     */
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    /**
     * @param 设置 获取数据的区域层级
     */
    public void setTarget(String target)
    {
        this.target = target;
    }
    
    /**
     * @return 返回特定区域集合信息
     */
    public List<Map<String, String>> getData()
    {
        return data;
    }
}
