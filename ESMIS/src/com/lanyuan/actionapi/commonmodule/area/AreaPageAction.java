package com.lanyuan.actionapi.commonmodule.area;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.commonmodule.area.ServiceArea;
import com.lanyuan.assembly.commonmodule.area.SingleArea;
import com.lanyuan.assembly.view.WebSite;
import com.lanyuan.assembly.view.control.SelectBox;
// import com.lanyuan.lip.basic.web.LoaderPage;
// import com.lanyuan.lip.basic.web.TableMianTest;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 区域信息管理页面<br/>
 * 包含功能：区域信息的展示页面，区域的增加/删除/保存区域(包含新增/修改)
 * 
 * @author qinye
 * @date 2015年8月6日 下午5:33:49
 */
public class AreaPageAction extends ActionSupport
{

    private static final long serialVersionUID = 1L;

    private List<SingleArea> data;
    private String cid;
    private String level;
    private String status;
    private String faultInfo;
    private String mydata;

    private String sjson;
    
    /**
     * 区域中文名
     */
    private String areaname;
    private String addUrl;
    private String delUrl;
    private String searchUrl;
    private String htmlTitle;
    private HashMap<String, String> searchArea;

    /**
     * @return 返回sjson
     */
    public String getSjson()
    {
        return sjson;
    }

    /**
     * @param 设置sjson
     */
    public void setSjson(String sjson)
    {
        this.sjson = sjson;
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

    private String id;

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

    /**
     * @return 返回data
     */
    public List<SingleArea> getData()
    {
        return data;
    }

    /**
     * @param 设置data
     */
    public void setData(List<SingleArea> data)
    {
        this.data = data;
    }

    /**
     * @return 返回cid
     */
    public String getCid()
    {
        return cid;
    }

    /**
     * @param 设置cid
     */
    public void setCid(String cid)
    {
        this.cid = cid;
    }

    /**
     * @return 返回level
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * @param 设置level
     */
    public void setLevel(String level)
    {
        this.level = level;
    }

    /**
     * 查询area，根据模板生成相应页面
     * 
     * @return Action.SUCCESS
     * @throws ConnException
     */
    public String selectArea() throws ConnException
    {
        String url = WebSite.getManageUrl(ServletActionContext.getRequest());
        this.htmlTitle = "区域管理";
        this.addUrl = url + "area/addArea.action";
        this.delUrl = url + "area/areaDel.action";
        this.searchUrl = url + "area/selectAreaJson.action";

        this.searchArea = new LinkedHashMap<String, String>();
        // 省份查询
        LinkedHashMap<String, String> typeItems = new LinkedHashMap<String, String>();
        typeItems.put("000000", "-省份-");
        SelectBox ctlFormName = new SelectBox("province");
        ctlFormName.setItems(typeItems);
        ctlFormName.setValue("000000");
        ctlFormName.setClassName("boxText text01");
        ctlFormName.setOnchange("getArea(this.value,iLevel_Province);");
        this.searchArea.put("province", ctlFormName.fCreate());

        // 城市查询
        LinkedHashMap<String, String> typeItems2 = new LinkedHashMap<String, String>();
        typeItems2.put("0", "-城市-");
        SelectBox ctlFormName2 = new SelectBox("city");
        ctlFormName2.setItems(typeItems2);
        ctlFormName2.setValue("0");
        ctlFormName2.setClassName("boxText text01");
        ctlFormName2.setOnchange("getArea(this.value,iLevel_City);");
        this.searchArea.put("city", ctlFormName2.fCreate());
        return Action.SUCCESS;
    }

    String createControlHtml(String title, String ctrlHtml, boolean bHidden)
    {
        String findInfo = "";
        findInfo += "<div class=\"inputBox\" style=\"display:" + (bHidden ? "none" : "") + "\">";
        findInfo += "<span>" + title + "：</span>";
        findInfo += "<i>" + ctrlHtml + "</i>";
        findInfo += "</div>";

        return findInfo;
    }

    /**
     * 查询area，以json格式返回数据
     * 
     * @param request
     * @param response
     * @throws ConnException
     * @throws ServletException
     * @throws IOException
     */
    public String selectAreaJson()
    {
        ServiceArea areaSer = new ServiceArea();
        cid = cid == null ? "000000" : cid;
        level = level == null ? "1" : level;

        this.setData(areaSer.selectChild(cid));
        this.setCid(cid);
        this.setLevel(level);
        return Action.SUCCESS;
    }

    /**
     * 新增area，返回结果状态
     */
    public String addArea()
    {
        String status = "success";
        String faultInfo = "操作成功!";
        cid = cid == null ? "000000" : cid;
        level = level == null ? "1" : level;
        String msg = jsonToString(mydata);
        if (null != msg)
        {
            status = "fault";
            faultInfo = msg;
        }
        this.setStatus(status);
        this.setFaultInfo(faultInfo);
        this.setCid(cid);
        this.setLevel(level);

        return Action.SUCCESS;
    }

    /**
     * 保存区域信息<br/>
     * 说明：处理页面传来的数据，解析json字符串。<br/>
     * 存在区域编码，修改区域信息。不存在，新增区域信息
     * 
     * @param data
     *        json格式的字符串。json格式：{"key1":{"name":"河南省","cid":"410000","letter":
     *        "HNS","codes":"","fid":"","orders":"0"}}
     * @return
     */
    public String jsonToString(String data)
    {
        String msg = null;
        JSONObject jsonObj = JSONObject.fromObject(data);
        for (Iterator<?> iter = jsonObj.keys(); iter.hasNext();)
        {
            String key = (String) iter.next();
            String ke = key.substring(0, 3);

            JSONObject jsonObject = jsonObj.getJSONObject(key);
            String iOrder = (String) jsonObject.get("orders");
            String sNCode = (String) jsonObject.get("cid");
            String sName = (String) jsonObject.get("name");
            String sLetter = (String) jsonObject.get("letter");
            String sCCode = (String) jsonObject.get("codes");
            String sFNCode = (String) jsonObject.get("fid");
            
            
            SingleArea entityArea = new SingleArea();
            entityArea.setOrder1(new Integer(iOrder));
            entityArea.setCcode(sCCode);
            entityArea.setFncode(sFNCode);
            entityArea.setLetter(sLetter);
            entityArea.setName(sName);
            entityArea.setNcode(sNCode);
            
            
            // key 说明是已有的数据，现在进行修改
            if (ke.equals("key"))
            {
                ServiceArea areaSer = new ServiceArea();
                boolean flag = areaSer.update(sNCode, entityArea);
                if (!flag)
                {
                    msg = "修改数据失败!";
                    break;
                }
            }
            
            
            // new 说明是新增的数据，现在进行添加
            if (ke.equals("new"))
            {
                ServiceArea areaSer = new ServiceArea();
                int i = areaSer.add(entityArea);
                if (i != 0)
                {
                    msg = "新增数据出错!";
                    break;
                }
            }
        }
        return msg;
    }

    /**
     * 删除区域信息
     */
    public String areaDel()
    {
        String status = "success";
        String faultInfo = "操作成功!";
        String sNCode = id == null ? "" : id;
        cid = cid == null ? "000000" : cid;
        level = level == null ? "1" : level;
        ServiceArea areaSer = new ServiceArea();
        boolean flag = areaSer.delete(sNCode);
        if (!flag)
        {
            status = "fault";
            faultInfo = "删除失败";
        }

        this.setStatus(status);
        this.setFaultInfo(faultInfo);
        this.setCid(cid);
        this.setLevel(level);
        this.setId(sNCode);
        return Action.SUCCESS;
    }

    /**
     * 判断当前传入区域编号是否是城市，是则返回1以及对应的父级区域信息，不是则返回0以及所属省、市信息 code
     * 传入的区域编号（code的值只能是市、县编号）
     */
    public String isArea()
    {
        ServiceArea sa = new ServiceArea();
        String code = ServletActionContext.getRequest().getParameter("code");
        JSONArray array = new JSONArray();
        SingleArea ea = new SingleArea();
        ea = sa.getAreaByNCode(code);// 通过编号查询对应区域信息
        if (null == ea)
        {
            sjson = "";
            return Action.SUCCESS;
        }
        boolean iscity = sa.isCity(code);// 判断当前编号是否属于市级
        String istag = "";// 标注当前编号 1：市，0：县
        if (iscity)
        {
            istag = "1";
            JSONObject obj = new JSONObject();
            obj.put("code", ea.getNcode());
            obj.put("name", ea.getName());
            obj.put("fcode", ea.getFncode());

            SingleArea ea_pro = new SingleArea();
            String fc = ea.getFncode();
            if (fc == null)
            {
                fc = ea.getNcode().substring(0, 2) + "0000";
            }
            
            if (!fc.equals("000000"))
            {
                ea_pro = sa.getAreaByNCode(fc);// 根据父级编号查询信息
                if (ea_pro == null)
                {
                    obj.put("fname", "");
                }
                else
                {
                    obj.put("fname", ea_pro.getName());
                }
            }
            else
            {
                obj.put("fname", "");
            }
            obj.put("tag", istag);
            array.add(obj);
        }
        else
        {
            istag = "0";
            JSONObject obj = new JSONObject();
            obj.put("code", ea.getNcode());
            obj.put("name", ea.getName());

            SingleArea ea_city = new SingleArea();
            ea_city = sa.getAreaByNCode(ea.getFncode());
            obj.put("ccode", ea_city.getNcode());
            obj.put("cname", ea_city.getName());

            SingleArea ea_pro = new SingleArea();
            ea_pro = sa.getAreaByNCode(ea_city.getFncode());// 根据父级编号查询信息
            obj.put("fname", ea_pro.getName());
            obj.put("fcode", ea_pro.getNcode());
            obj.put("tag", istag);
            array.add(obj);
        }
        sjson = array.toString();
        return Action.SUCCESS;
    }

    public String searchAreaByName()
    {
        String firstarea = "";
        String twoarea = "";
        String threearea = "";
        ServiceArea sa = new ServiceArea();
        if (!"".equals(areaname))
        {
            if (areaname.indexOf("-") > 0)
            {
                String[] arr = areaname.split("-");
                if (arr.length == 2)
                {
                    firstarea = sa.getAreaCodeByName(arr[0]);
                    if (null == firstarea)
                    {
                        faultInfo = "";
                    }
                    else
                    {
                        twoarea = sa.getNcodeByNameAndFncode(arr[1], firstarea);
                        if (null == twoarea)
                        {
                            faultInfo = "";
                        }
                        else
                        {
                            faultInfo = twoarea;
                        }
                    }
                }
                else
                {
                    firstarea = sa.getAreaCodeByName(arr[0]);
                    if (null == firstarea)
                    {
                        faultInfo = "";
                    }
                    else
                    {
                        twoarea = sa.getNcodeByNameAndFncode(arr[1], firstarea);
                        if (null == twoarea)
                        {
                            faultInfo = "";
                        }
                        else
                        {
                            threearea = sa.getNcodeByNameAndFncode(arr[2], twoarea);
                            if (null == threearea)
                            {
                                faultInfo = "";
                            }
                            else
                            {
                                faultInfo = threearea;
                            }
                        }
                    }
                }
            }
            else
            {
                firstarea = sa.getAreaCodeByName(areaname);
                if (null == firstarea)
                {
                    faultInfo = "";
                }
                else
                {
                    faultInfo = firstarea;
                }
            }
        }
        else
        {
            this.faultInfo = "";
        }

        return Action.SUCCESS;
    }

    /**
     * @return 返回 areaname
     */
    public String getAreaname()
    {
        return areaname;
    }

    /**
     * @param areaname 设置
     */
    public void setAreaname(String areaname)
    {
        this.areaname = areaname;
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

    public HashMap<String, String> getSearchArea()
    {
        return searchArea;
    }

}
