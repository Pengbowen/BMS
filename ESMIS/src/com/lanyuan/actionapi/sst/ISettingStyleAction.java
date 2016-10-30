package com.lanyuan.actionapi.sst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.opensymphony.xwork2.Action;

/**
 * 设置体系样式表
 * @author ly-two
 * @date 2016-9-1 上午10:12:36
 */
public class ISettingStyleAction extends ResultOperateAction
{
    @Autowired
    private ServiceSSTLayerItems layerItemService;
    private static final long serialVersionUID = 1L;
    /**
     * 所属体系表ID
     */
    public String SSTId;
    /**
     * 样式数据
     */
    public String styleData;
    /**
     * 装载全部样式
     */
    private static String sstStyle;
    /**
     * 最大层级
     */
    private int maxLayer;

    /**
     * 保存设置数据
     * @throws UnsupportedEncodingException 
     */
    public String saveStyleSetting() throws UnsupportedEncodingException
    {
        if (StringUtils.isBlank(SSTId))
        {
            this.printString("0", "保存失败");
            return Action.SUCCESS;
        }
        if (!StringUtils.isBlank(styleData))
        {
            styleData = java.net.URLDecoder.decode(styleData, "UTF-8");
            HttpServletRequest request = ServletActionContext.getRequest();
            String realPath = request.getSession().getServletContext().getRealPath("/"
                                                                                           + "conf/SSTStyle.json");
            // 所有样式JSONArray对象
            JSONArray sstStyleArray = JSONArray.fromObject(sstStyle);
            JSONArray newJsonArray = new JSONArray();
            JSONObject newObject = new JSONObject();
            JSONObject styleDataJSON = JSONObject.fromObject(styleData);
            for (int i = 1; i < styleDataJSON.size() + 1; i++)
            {
                newJsonArray.add(styleDataJSON.get(i + ""));
            }
            sstStyleArray.remove(Integer.parseInt(SSTId) - 1);
            newObject.put("name", ("1".equals(SSTId) ? "技术标准体系表" : "")
                    + ("2".equals(SSTId) ? "管理标准体系表" : "") + ("3".equals(SSTId) ? "工作标准体系表" : ""));
            newObject.put("style", newJsonArray);
            sstStyleArray.add((Integer.parseInt(SSTId) - 1), newObject);
            if (writeFile(realPath, sstStyleArray.toString()))
            {
                this.printString("1", SSTId + "");
            }
            else
            {
                this.printString("0", "保存失败");
            }
        }
        else
        {
            this.printString("0", "保存失败");
        }
        return Action.SUCCESS;
    }

    /**
     * 转向样式设置页面
     */
    public String toStyleSetting()
    {
        maxLayer = layerItemService.selectMaxlayerItemId(SSTId);
        if (maxLayer > 0)
        {
            maxLayer = Integer.parseInt((maxLayer + "").substring(1, 2));
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String realPath = request.getSession().getServletContext().getRealPath("/"
                                                                                       + "conf/SSTStyle.json");
        try
        {
            loadData(realPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        try
        {
            response.getWriter().write(getSSTStyle());
            response.getWriter().close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    /**
     * 根据SSTId获取相关样式
     */
    private String getSSTStyle()
    {
        String result = "";
        if (StringUtils.isBlank(SSTId) || StringUtils.isBlank(sstStyle))
        {
            return result;
        }
        JSONArray styleArray = JSONArray.fromObject(sstStyle);
        if (styleArray.size() > 0)
        {
            Object object = styleArray.get(Integer.parseInt(SSTId) - 1);
            if (object != null)
            {
                JSONObject styleObject = JSONObject.fromObject(object);
                if (styleObject != null)
                {
                    object = styleObject.get("style");
                    styleArray = JSONArray.fromObject(styleObject.get("style").toString());
                    JSONArray styleArrayNew = new JSONArray();
                    for (int i = 0; i < styleArray.size(); i++)
                    {
                        styleArrayNew.add(styleArray.get(i));
                    }
                    // 添加默认值
                    if (styleArray.size() + 1 < maxLayer)
                    {
                        for (int i = styleArray.size(); i < maxLayer; i++)
                        {
                            JSONObject defaulJSONObject = new JSONObject();
                            defaulJSONObject.put("level", i+1);
                            defaulJSONObject.put("width", "120");
                            defaulJSONObject.put("height", "42");
                            defaulJSONObject.put("subTypeWidth", "120");
                            defaulJSONObject.put("textDirection", "横向");
                            styleArrayNew.add(defaulJSONObject);
                        }
                    }

                    result = styleArrayNew.toString();
                }
            }
        }
        return result;
    }

    /**
     * 从配置文件读取样式数据
     */
    private void loadData(String path) throws IOException
    {
        StringBuffer contentBuffer = new StringBuffer();
        InputStream inputStream = new FileInputStream(new File(path));
        InputStreamReader file = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(file);
        String tempString = "";
        while ((tempString = reader.readLine()) != null)
        {
            contentBuffer.append(tempString);
        }
        reader.close();
        sstStyle = contentBuffer.toString();
    }

    /**
     * 写入json文件
     */
    private boolean writeFile(String realPath, String content)
    {
        boolean result = false;
        FileOutputStream fop = null;
        File file;
        try
        {
            file = new File(realPath);
            fop = new FileOutputStream(file);
            if (!file.exists())
            {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            result = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fop != null)
                {
                    fop.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getSSTId()
    {
        return SSTId;
    }

    public void setSSTId(String sSTId)
    {
        SSTId = sSTId;
    }

    public String getStyleData()
    {
        return styleData;
    }

    public void setStyleData(String styleData)
    {
        this.styleData = styleData;
    }
}
