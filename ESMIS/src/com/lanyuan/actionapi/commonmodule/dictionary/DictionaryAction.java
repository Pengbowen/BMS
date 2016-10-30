
package com.lanyuan.actionapi.commonmodule.dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.dictionary.CollectionDictionary;
import com.lanyuan.assembly.dictionary.ServiceDictionary;
import com.lanyuan.assembly.dictionary.SingleDictionary;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.lanyuan.assembly.view.WebSite;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 后台的数据字典 的 
 * 新增、修改、删除、查询的接口
 * @author ly-one
 *
 */
public class DictionaryAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String did;
    private TreeMap<String, SingleDictionaryData> data;
    private String id;
    private String status;
    private String faultInfo;

    private String mydata;

    private String addUrl;
    private String delUrl;
    private String searchUrl;
    private String htmlTitle;

    /**
     * 生成页面
     */
    public String initPage() throws ConnException
    {
        ServiceDictionary clsManage = new ServiceDictionary();
        SingleDictionary clsData = clsManage.selectById(Integer.parseInt(did));
        
        this.htmlTitle = clsData.getsName();

        String url = WebSite.getManageUrl(ServletActionContext.getRequest());
        this.addUrl = url + "dictionary/addDictionaryData.action";
        this.delUrl = url + "dictionary/delDictionaryData.action";
        this.searchUrl = url + "dictionary/selectDictionaryData.action";
        return Action.SUCCESS;
    }

    /**
     * 增加字典，返回结果状态
     * 
     * @param request
     * @param response
     * @throws ConnException
     * @throws ServletException
     * @throws IOException
     */
    public void addDictionary(HttpServletRequest request, HttpServletResponse response)
            throws ConnException, ServletException, IOException
    {
        String data = request.getParameter("data") == null ? "" : request.getParameter("data");
        String status = "success";
        String faultInfo = "操作成功!";
        String msg = jsonToStringDic(data);
        if (!msg.equals(""))
        {
            status = "fault";
            faultInfo = msg;
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("status", status);
        map.put("faultInfo", faultInfo);
        response.getWriter().print(JSONObject.fromObject(map).toString());
    }

    /**
     * 处理页面传来的数据，解析json字符串(字典管理)
     * 
     * @param data
     * @return
     */
    public String jsonToStringDic(String data)
    {
        String msg = null;
        JSONObject jsonObj = JSONObject.fromObject(data);
        for (Iterator<?> iter = jsonObj.keys(); iter.hasNext();)
        {
            String key = (String) iter.next();
            String ke = key.substring(0, 3);

            JSONObject jsonObject = jsonObj.getJSONObject(key);
            String dname = (String) jsonObject.get("dname");
            String dexplain = (String) jsonObject.get("dexplain");
            String dtablename = (String) jsonObject.get("dtablename");
            String dcode = "" + jsonObject.get("dcode");
            String dmutidic = "" + jsonObject.get("dmutidic");

            boolean ddcode = false;
            boolean ddmutidic = false;
            if (dcode.equals("true"))
            {
                ddcode = true;
            }
            if (dmutidic.equals("true"))
            {
                ddmutidic = true;
            }
            SingleDictionary basicDictionary = new SingleDictionary();
            basicDictionary.setsName(dname);
            basicDictionary.setsExplain1(dexplain);
            basicDictionary.setsDataTableName(dtablename);
            basicDictionary.setBlnCustomCodeFieldTag(ddcode);
            basicDictionary.setBlnMultistageTag(ddmutidic);
            // key 说明是已有的数据，现在进行修改
            if (ke.equals("key"))
            {
                String id = (String) jsonObject.get("id");
                basicDictionary.setDictionaryId(Integer.parseInt(id));
                ServiceDictionary serviceDictionary = new ServiceDictionary();
                try
                {
                    msg = serviceDictionary.modify(basicDictionary);
                }
                catch (ConnException e)
                {
                    e.printStackTrace();
                }
            }
            // new 说明是新增的数据，现在进行添加
            if (ke.equals("new"))
            {
                ServiceDictionary serviceDictionary = new ServiceDictionary();
                try
                {
                    msg = serviceDictionary.add(basicDictionary);
                }
                catch (ConnException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }

    /**
     * 删除字典
     */
    public void delDictionary(HttpServletRequest request, HttpServletResponse response)
            throws ConnException, ServletException, IOException
    {
        String status = "success";
        String faultInfo = "操作成功!";
        String did = request.getParameter("id") == null ? "" : request.getParameter("id");
        ServiceDictionary serviceDictionary = new ServiceDictionary();
        int result = serviceDictionary.delete(Integer.parseInt(did));
        if (result <= 0)
        {
            status = "fault";
            faultInfo = "删除数据字典对象失败！";
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("status", status);
        map.put("faultInfo", faultInfo);
        response.getWriter().print(JSONObject.fromObject(map).toString());
    }

    /**
     * 字典数据的查询，查询后返回json字符串
     */
    public String selectDictionaryData()
    {
        String did = id == null ? "" : id;

        TreeMap<String, SingleDictionaryData> dictionaryDataMap = new TreeMap<String, SingleDictionaryData>();
        CollectionDictionary cc = new CollectionDictionary(Integer.parseInt(did));
        for (SingleDictionaryData en : cc)
        {
            dictionaryDataMap.put(en.getId() + "", en);
        }

        // 处理数据，转换成json，并输出json字符串
        this.setData(dictionaryDataMap);
        return Action.SUCCESS;
    }

    /**
     * 增加字典数据，返回结果状态（字典数据）
     * 
     */
    public String addDictionaryData() throws ConnException
    {
        String status = "success";
        String faultInfo = "操作成功!";
        String msg = jsonToString(mydata);
        if (null != msg)
        {
            status = "fault";
            faultInfo = msg;
        }
        this.setStatus(status);
        this.setFaultInfo(faultInfo);
        return Action.SUCCESS;
    }

    /**
     * 处理页面传来的数据，解析json字符串
     * 
     * @param data
     * @return
     * @throws ConnException
     */
    public String jsonToString(String data) throws ConnException
    {
        String msg = null;
        JSONObject jsonObj = JSONObject.fromObject(data);

        for (Iterator<?> iter = jsonObj.keys(); iter.hasNext();)
        {
            String key = (String) iter.next();
            String ke = key.substring(0, 3);
            JSONObject jsonObject = jsonObj.getJSONObject(key);
            String iid = (String) jsonObject.get("iid");
            String dictionaryid = (String) jsonObject.get("dictionaryid");
            String code = (String) jsonObject.get("code");
            String content = (String) jsonObject.get("content");
            String visible = (String) jsonObject.get("visible");

            SingleDictionaryData temp = new SingleDictionaryData();
            temp.setId(Integer.parseInt(iid));
            temp.setCode(code);
            temp.setContent(content);
            temp.setCustomfield1(visible);
            temp.setFid(Integer.parseInt(dictionaryid));

            CollectionDictionary cc = new CollectionDictionary(Integer.parseInt(dictionaryid));

            int result = 0;
            // key 说明是已有的数据，现在进行修改
            if (ke.equals("key"))
            {
                result = cc.update(temp);
                if (result <= 0)
                {
                    msg = "修改失败";
                }
            }
            // new 说明是新增的数据，现在进行添加
            if (ke.equals("new"))
            {
                result = cc.add(temp);
                if (result <= 0)
                {
                    msg = "新增失败";
                }
            }
        }
        return msg;
    }

    /**
     * 删除字典数据
     */
    public String delDictionaryData() throws NumberFormatException, ConnException
    {
        String status = "success";
        String faultInfo = "操作成功!";
        id = id == null ? "" : id;
        did = did == null ? "" : did;

        CollectionDictionary cc = new CollectionDictionary(Integer.parseInt(did));
        boolean flag = cc.delete(id);
        if (!flag)
        {
            status = "fault";
            faultInfo = "删除失败";
        }

        this.setStatus(status);
        this.setFaultInfo(faultInfo);
        this.setId(id);
        return Action.SUCCESS;
    }

    /**
     * @return 返回mydata
     */
    public String getMydata()
    {
        return mydata;
    }

    /**
     * @param 设置mydata
     */
    public void setMydata(String mydata)
    {
        this.mydata = mydata;
    }

    /**
     * @return 返回status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param 设置status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return 返回faultInfo
     */
    public String getFaultInfo()
    {
        return faultInfo;
    }

    /**
     * @param 设置faultInfo
     */
    public void setFaultInfo(String faultInfo)
    {
        this.faultInfo = faultInfo;
    }

    public TreeMap<String, SingleDictionaryData> getData()
    {
        return data;
    }

    public void setData(TreeMap<String, SingleDictionaryData> data)
    {
        this.data = data;
    }

    /**
     * @return 返回id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param 设置id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return 返回did
     */
    public String getDid()
    {
        return did;
    }

    /**
     * @param 设置did
     */
    public void setDid(String did)
    {
        this.did = did;
    }
    public String getAddUrl()
    {
        return addUrl;
    }

    public String getDelUrl()
    {
        return delUrl;
    }

    public String getSearchUrl()
    {
        return searchUrl;
    }

    public String getHtmlTitle()
    {
        return htmlTitle;
    }

}
