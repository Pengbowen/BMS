
package com.lanyuan.actionapi.commonmodule.dictionary;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;

/**
 * 删除价格等级的接口
 * 
 * @ClassName:IDeletePriceLevelAction
 * @Description: 删除价格等级的操作
 * @author gyy
 * @date Sat Nov 14 15:15:43 CST 2016
 */
public class IDeleteDictionaryNewAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;

    /**
     * 等级编号
     */
    private Integer id;

    public void delete()
    {
        // 判断传值是否为空
        if (id == null)
        {
            this.printString("2", "编号为空！");
            return;
        }
        ServiceDictionaryData service = new ServiceDictionaryData();

        boolean flag = service.delete(id);
        if (flag)
        {
            this.printString("1", "删除成功！");
        }
        else
        {
            this.printString("2", "删除失败！");
        }
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
