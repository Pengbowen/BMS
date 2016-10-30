
package com.lanyuan.actionapi.commonmodule.unit;


import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UnitEditAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String no;
    private SingleUnit unit;

    //新增页面
    public String add()
    {  
        return Action.SUCCESS;
    }

    //修改页面
    public String modify()
    {    
    	ServiceUnit g_unitService =new ServiceUnit();
        unit = g_unitService.selectById(id);
        return Action.SUCCESS;
    }
    
    //设置企业信息
    public String toEnterpriseUpdate()
    {    
        ServiceUnit g_unitService =new ServiceUnit();
        unit = g_unitService.selectById("10");
        return Action.SUCCESS;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public SingleUnit getUnit()
    {
        return unit;
    }

    public void setUnit(SingleUnit unit)
    {
        this.unit = unit;
    }
    /**
     * @return 返回 no
     */
    public String getNo()
    {
        return no;
    }

    /**
     * @param no 设置
     */
    public void setNo(String no)
    {
        this.no = no;
    }
}
