package com.lanyuan.actionapi.sst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.ResultDetailAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.opensymphony.xwork2.Action;

/**
 * 体系表框图
 * @author yt
 */
public class ILoadSSTViewAction extends ResultDetailAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceSSTLayerItems layerItemService;
    @Autowired
    private ServiceSSTSubClassify serviceSubClassify;
    /**
     * 子项目List集合
     */
    List<SingleSSTLayerItems> sstLayerItemsList = null;
    /**
     * 子分类HashMap集合<子项目,子分类List集合>
     */
    private HashMap<String, List<SingleSSTSubClassify>> sstSubClassifyMap = null;
    /**
     * 子分类结点Map
     */
    private HashMap<String, SingleSSTLayerItems> sstLayerItemsMap = null;
    /**
     * 体系表框图数据结构集合
     */
    private HashMap<String, List<String>> sstNodeMap = null;
    /**
     * 每层子项目个数
     */
    private HashMap<String, Object> layerNoCountMap = null;
    /**
     * 每层是否存在子分类
     */
    private HashMap<String, Object> subClassifyCountMap = null;
    /**
     * 体系表样式
     */
    private HashMap<String, List<Object>> sstStyleMap = null;
    /**
     * 体系表高度
     */
    private int sstHeight;
    /**
     * 前端OR后台
     */
    private String manage = "";
    /**
     * 工作标准体系表,第二层子项目最大子项目数
     */
    private int count = 0;

    /**
     *转向技术标准体系表
     */
    public String toTechnicalStandard()
    {
        return Action.SUCCESS;
    }

    /**
     *技术标准体系表
     */
    public String technicalStandard() throws IOException
    {
        loadData(1);
        getSSTStyle(1);
        HashMap<String, Object> detailData = new HashMap<String, Object>();
        // 子项目集合
        detailData.put("sstLayerItemsList", sstLayerItemsList);
        // 子分类集合
        detailData.put("sstSubClassifyMap", sstSubClassifyMap);
        // 每层子项目个数
        detailData.put("layerNoCountMap", layerNoCountMap);
        // 最大子项目数
        detailData.put("layerNoMaxCount", getMaxKey(layerNoCountMap));
        // 每层是否有子分类
        detailData.put("subClassifyCountMap", subClassifyCountMap);
        // 每层体系表样式
        detailData.put("sstStyleMap", sstStyleMap);
        this.printString(detailData, "1", "");
        return Action.SUCCESS;
    }

    /**
     * 转向管理标准体系表
     */
    public String toManageStandard()
    {
        return Action.SUCCESS;
    }

    /**
     *管理标准体系表
     */
    public String manageStandard() throws IOException
    {
        loadData(2);
        getSSTStyle(2);
        HashMap<String, Object> detailData = new HashMap<String, Object>();
        detailData.put("SSTId", 2101);
        detailData.put("sstSubClassifyMap", sstSubClassifyMap);
        detailData.put("sstLayerItemsMap", sstLayerItemsMap);
        // 子结点集合
        detailData.put("sstNodeMap", sstNodeMap);
        detailData.put("layerNoCountMap", layerNoCountMap);
        detailData.put("layerNoMaxCount", getMaxKey(layerNoCountMap));
        detailData.put("subClassifyCountMap", subClassifyCountMap);
        detailData.put("sstStyleMap", sstStyleMap);
        detailData.put("sstHeight", sstHeight);

        this.printString(detailData, "1", "");
        return Action.SUCCESS;
    }

    /**
     *转向工作标准体系表
     */
    public String toWorkStandard()
    {
        return Action.SUCCESS;
    }

    /**
     *工作标准体系表
     */
    public String workStandard() throws IOException
    {
        loadData(3);
        getSSTStyle(3);
        HashMap<String, Object> detailData = new HashMap<String, Object>();
        detailData.put("SSTId", 3101);
        detailData.put("sstSubClassifyMap", sstSubClassifyMap);
        detailData.put("sstLayerItemsMap", sstLayerItemsMap);
        // 子结点集合
        detailData.put("sstNodeMap", sstNodeMap);
        detailData.put("layerNoCountMap", layerNoCountMap);
        detailData.put("layerNoMaxCount", getMaxKey(layerNoCountMap));
        detailData.put("subClassifyCountMap", subClassifyCountMap);
        detailData.put("sstStyleMap", sstStyleMap);
        detailData.put("sstHeight", sstHeight);
        detailData.put("subClassifyCount", count);

        this.printString(detailData, "1", "");
        return Action.SUCCESS;
    }

    /**
     * 加载数据
     */
    private void loadData(int SSTId)
    {
        sstLayerItemsList = layerItemService.selectBySSTId(SSTId);
        List<SingleSSTSubClassify> sstSubClassifyList = serviceSubClassify.selectBySSTId(SSTId);
        if (sstLayerItemsList != null)
        {
            sstNodeMap = new HashMap<String, List<String>>();
            sstLayerItemsMap = new HashMap<String, SingleSSTLayerItems>();
            layerNoCountMap = new HashMap<String, Object>();
            for (int i = 0; i < sstLayerItemsList.size(); i++)
            {
                if (layerNoCountMap.containsKey(sstLayerItemsList.get(i).getLayerNo() + ""))
                {
                    int count = Integer.parseInt(layerNoCountMap.get(sstLayerItemsList.get(i).getLayerNo()
                                                                             + "").toString());
                    layerNoCountMap.put(sstLayerItemsList.get(i).getLayerNo() + "", ++count);
                }
                else
                {
                    layerNoCountMap.put(sstLayerItemsList.get(i).getLayerNo() + "", 1);
                }
                List<String> nodeList = new ArrayList<String>();
                sstLayerItemsMap.put(sstLayerItemsList.get(i).getLayerItemId(),
                                     sstLayerItemsList.get(i));
                for (int j = 0; j < sstLayerItemsList.size(); j++)
                {
                    if (sstLayerItemsList.get(i).getLayerItemId().equals(sstLayerItemsList.get(j).getBelongItemId()))
                    {
                        nodeList.add(sstLayerItemsList.get(j).getLayerItemId());
                    }
                }
                if (SSTId == 3 && sstLayerItemsList.get(i).getLayerNo() == 2)
                {
                    if (nodeList.size() > count)
                    {
                        count = nodeList.size();
                    }
                }
                sstNodeMap.put(sstLayerItemsList.get(i).getLayerItemId(), nodeList);
            }
        }
        if (sstSubClassifyList != null)
        {
            sstSubClassifyMap = new LinkedHashMap<String, List<SingleSSTSubClassify>>();
            subClassifyCountMap = new HashMap<String, Object>();
            for (SingleSSTSubClassify sstSubClassify : sstSubClassifyList)
            {
                if (sstSubClassifyMap.containsKey(sstSubClassify.getLayerItemId()))
                {
                    List<SingleSSTSubClassify> list = sstSubClassifyMap.get(sstSubClassify.getLayerItemId());
                    list.add(sstSubClassify);
                    sstSubClassifyMap.put(sstSubClassify.getLayerItemId(), list);
                }
                else
                {
                    List<SingleSSTSubClassify> list = new ArrayList<SingleSSTSubClassify>();
                    list.add(sstSubClassify);
                    sstSubClassifyMap.put(sstSubClassify.getLayerItemId(), list);
                }
                if (!subClassifyCountMap.containsKey(sstSubClassify.getLayerNo()))
                {
                    subClassifyCountMap.put(sstSubClassify.getLayerNo() + "", true);
                }
            }
        }
    }

    private Object getMaxKey(Map<String, Object> map)
    {
        if (map == null) return null;
        Collection<Object> set = map.values();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[obj.length - 1];
    }

    /**
     * 根据SSTId获取相关样式
     */
    private void getSSTStyle(int SSTId) throws IOException
    {
        JSONArray styleArray = JSONArray.fromObject(loadStyleData());
        if (styleArray.size() > 0)
        {
            Object object = styleArray.get(SSTId - 1);
            if (object != null)
            {
                JSONObject styleObject = JSONObject.fromObject(object);
                if (styleObject != null)
                {
                    object = styleObject.get("style");
                    styleArray = JSONArray.fromObject(styleObject.get("style").toString());
                    sstStyleMap = new HashMap<String, List<Object>>();
                    for (int i = 0; i < styleArray.size(); i++)
                    {
                        styleObject = JSONObject.fromObject(styleArray.getString(i));
                        List<Object> styleList = new ArrayList<Object>();
                        styleList.add(styleObject.getInt("width"));
                        styleList.add(styleObject.getInt("height"));
                        styleList.add(styleObject.getInt("subTypeWidth"));
                        styleList.add(styleObject.getString("textDirection"));
                        sstStyleMap.put(styleObject.getString("level"), styleList);
                    }
                }
            }
        }
    }

    /**
     * 从配置文件读取样式数据
     */
    private String loadStyleData() throws IOException
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        String realPath = request.getSession().getServletContext().getRealPath("/"
                                                                                       + "conf/SSTStyle.json");
        StringBuffer contentBuffer = new StringBuffer();
        InputStream inputStream = new FileInputStream(new File(realPath));
        InputStreamReader file = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(file);
        String tempString = "";
        while ((tempString = reader.readLine()) != null)
        {
            contentBuffer.append(tempString);
        }
        reader.close();
        return contentBuffer.toString();
    }

    public String getManage()
    {
        return manage;
    }

    public void setManage(String manage)
    {
        this.manage = manage;
    }
}
