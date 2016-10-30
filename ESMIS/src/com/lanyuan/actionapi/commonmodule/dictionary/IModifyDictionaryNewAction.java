package com.lanyuan.actionapi.commonmodule.dictionary;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;

/**
 * 修改价格等级的接口
 * 
 * @ClassName:IModifyPriceLevelAction
 * @Description: 修改价格等级的操作
 * @author gyy
 * @date Sat Nov 14 15:15:43 CST 2016
 */
public class IModifyDictionaryNewAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;


    private Integer id;
    private Integer dictionaryid;
    
    private String content;
    private String customfield1;
    private Integer showNum;
    private String serviceClass;

    private String code;
    private Integer fid;
    public void modify()
    {
        // 判断传值是否为空
        if (id == null)
        {
        	this.printString("2", "编号为空！");
            return;
        }

        ServiceDictionaryData service = new ServiceDictionaryData();
        SingleDictionaryData data = new SingleDictionaryData();

        data.setId(id);
        data.setDictionaryid(dictionaryid);
        data.setContent(content);
        data.setCustomfield1(customfield1);
        data.setShowNum(showNum);
        data.setServiceClass(serviceClass);
        
        data.setCode(code);
        data.setFid(dictionaryid);

        boolean flag = service.modify(id, data);
        if (flag)
        {
            this.printString("1", "修改成功！");
        }
        else
        {
            this.printString("2", "修改失败！");
        }
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDictionaryid() {
		return dictionaryid;
	}

	public void setDictionaryid(Integer dictionaryid) {
		this.dictionaryid = dictionaryid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustomfield1() {
		return customfield1;
	}

	public void setCustomfield1(String customfield1) {
		this.customfield1 = customfield1;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
}
