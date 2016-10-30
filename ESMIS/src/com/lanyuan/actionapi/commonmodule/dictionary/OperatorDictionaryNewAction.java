package com.lanyuan.actionapi.commonmodule.dictionary;

import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 数据字典的操作接口
 */
public class OperatorDictionaryNewAction extends ActionSupport
{

    private static final long serialVersionUID = 1L;
    // content，dictionaryid,customfield1，showNum，serviceClass，fid
    private Integer id;
    private Integer dictionaryid;
    private String content;
    private String customfield1;
    private Integer showNum;
    private String serviceClass;

    private Integer fid;
    private String code;

    // 跳转价格等级列表页
    public String execute()
    {
        return Action.SUCCESS;
    }

    // 价格等级的新增、修改
    public String showInfo()
    {
        ServiceDictionaryData service = new ServiceDictionaryData();
        if (id != null)
        {
            customfield1 = "1";
            SingleDictionaryData data = new SingleDictionaryData();

            data = service.selectById(id);

            if (data != null)
            {
                setInfo(data);
            }
        }
        else
        { 
            this.code = service.getMaxDictionaryId(dictionaryid)+"";
        }
        return Action.SUCCESS;
    }

    // 给定义的属性赋值
    public void setInfo(SingleDictionaryData data)
    {
        this.id = data.getId();
        this.dictionaryid = data.getDictionaryid();
        this.content = data.getContent();
        this.customfield1 = data.getCustomfield1();
        this.showNum = data.getShowNum();
        this.serviceClass = data.getServiceClass();

        this.fid = data.getDictionaryid();
        this.code = data.getCode();

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getDictionaryid()
    {
        return dictionaryid;
    }

    public void setDictionaryid(Integer dictionaryid)
    {
        this.dictionaryid = dictionaryid;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getCustomfield1()
    {
        return customfield1;
    }

    public void setCustomfield1(String customfield1)
    {
        this.customfield1 = customfield1;
    }

    public Integer getShowNum()
    {
        return showNum;
    }

    public void setShowNum(Integer showNum)
    {
        this.showNum = showNum;
    }

    public String getServiceClass()
    {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass)
    {
        this.serviceClass = serviceClass;
    }

    public Integer getFid()
    {
        return fid;
    }

    public void setFid(Integer fid)
    {
        this.fid = fid;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
