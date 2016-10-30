
package com.lanyuan.actionapi.commonmodule.unit;

import java.util.Map;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.lanyuan.assembly.dictionary.CollectionDictionary;

public class UnitSaveAction extends ResultOperateAction
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
   
    private SingleUnit unit;
    
    private ServiceUnit g_service;

    public UnitSaveAction()
    {
         this.g_service=new ServiceUnit();
    }
    public void add()
    {
    	String str = g_service.add(unit);
        if (!"".equals(str))
        {
        	this.printString("1", "信息添加成功!");
        }
        else
        {
        	this.printString("2", "信息添加失败!");
        }
    }

    public void modify()
    {
    	boolean flag=g_service.modify(unit,this.id);
        if(flag)
        {
        	this.printString("1", "信息修改成功!");
        }
        else
        {
        	this.printString("2", "信息修改失败!");
        }
    }

    public void delete()
    {
        boolean boo= g_service.delete(id);
        if(boo)
        {   
        	this.printString("1", "删除成功");
        }else
        {
        	this.printString("2", "删除失败");
        }
    }

    /* 开启 */
    public void start()
    {
        boolean boo = g_service.modifyStateById(1, id);
        if (boo)
        {
        	this.printString("1", "已开启");
        }
        else
        {
        	this.printString("2", "操作失败");
        }
    }

    /* 停用 */
    public void end()
    {
        boolean boo = g_service.modifyStateById(0, id);
        if (boo)
        {
        	this.printString("1", "已停用");
        }else{
        	this.printString("2", "操作失败");
        }
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


}
