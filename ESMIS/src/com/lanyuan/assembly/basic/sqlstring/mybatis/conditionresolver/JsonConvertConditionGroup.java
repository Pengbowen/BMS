/**
 * @file EntityConditionList.java
 * @package com.lanyuan.assembly.basic.customquery
 * @description ConditionGroup转json的转换器
 * @copyright Copyright(c)2014
 * @company lanyuan
 * @author qinye
 * @date 2014-6-7 下午14:17:32
 * @version V1.0
 */
package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionBinaryBit;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.QueryType;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumConnector;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionRange;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * ConditionGroup转json的转换器
 * 
 * @classname EntityConditionList.java
 * @description ConditionGroup转json的转换器
 * @author qinye
 * @date 2014-6-7 下午14:17:32
 */
public class JsonConvertConditionGroup
{
    private String error;
    private ConditionGroup condition;

    public String getError()
    {
        return error;
    }

    public ConditionGroup getCondition()
    {
        return condition;
    }

    public void setCondition(ConditionGroup condition)
    {
        this.condition = condition;
    }

    /**
     * 获取json字符串
     * 
     * @return 返回json字符串
     */
    public String getJsonString()
    {
        List<Object> result = null;
        result = GroupConvertList(this.condition);

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONArray json = JSONArray.fromObject(result, jsonConfig);
        return json.toString();
    }

    /**
     * 构造函数
     */
    public JsonConvertConditionGroup()
    {
        this.condition = new ConditionGroup();
    }

    /**
     * 带json字符串的构造函数
     */
    public JsonConvertConditionGroup(String json)
    {
        this.condition = exchangeJsonToCondition(json);
    }

    /**
     * 通过json字符串转成ConditionGroup
     * 
     * @param json 字符串
     * @return ConditionGroup对象
     */
    private ConditionGroup exchangeJsonToCondition(String json)
    {
        int type = 0;
        ConditionGroup group = new ConditionGroup();
        JSONArray jsonArr = JSONArray.fromObject(json);

        for (int k = 0, iCount = jsonArr.size(); k < iCount; k++)
        {
            JSONObject item = JSONObject.fromObject(jsonArr.get(k).toString());
            type = item.getInt("type");
            if (QueryType.normal.ordinal() == type)
            {
                JSONObjectConvertNormal(group, item);
            }
            if (QueryType.binaryBit.ordinal() == type)
            {
                JSONObjectConvertBinaryBit(group, item);
            }
            if (QueryType.range.ordinal() == type)
            {
                JSONObjectConvertRange(group, item);
            }
            if (QueryType.group.ordinal() == type)
            {
                JSONObjectConvertGroup(group, item);
            }
        }
        return group;
    }

    /**
     * 通过ConditionNormal转HashMap<String, Object>
     * 
     * @param ConditionNormal
     * @return HashMap<String, Object>对象
     */
    @SuppressWarnings("deprecation")
    private HashMap<String, Object> NormalConvertMap(ConditionNormal data)
    {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("mappingid", data.getMappingId());
        item.put("value", URLEncoder.encode(data.getValue()));
        item.put("operator", data.getOperator().getValue());
        item.put("setid", "");
        item.put("connector", data.getConnector().getValue());
        item.put("type", QueryType.normal.ordinal());
        return item;
    }

    /**
     * 通过JSONObject转ConditionNormal
     * 
     * @param group 要添加到条件的ConditionGroup对象
     * @param JSONObject 条件的JSON对象
     * @return ConditionNormal对象
     */
    private void JSONObjectConvertNormal(ConditionGroup group, JSONObject item)
    {
        String mappingId = item.getString("mappingid");
        String value = "";
        try
        {
            value = URLDecoder.decode(item.getString("value"), "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ConditionNormal data = new ConditionNormal(mappingId, value);
        data.setOperator(enumOperator.getKey(item.getString("operator")));

        enumConnector temp = enumConnector.getKey(item.getString("connector"));
        if (temp.getValue().equals(enumConnector.And.getValue()))
        {
            group.addWithAnd(data);
        }
        else if (temp.getValue().equals(enumConnector.Or.getValue()))
        {
            group.addWithOr(data);
        }else
        {
            group.addWithAnd(data);
        }
    }

    /**
     * 通过ConditionBinaryBit转HashMap<String, Object>
     * 
     * @param ConditionBinaryBit
     * @return HashMap<String, Object>对象
     */
    @SuppressWarnings("deprecation")
    private HashMap<String, Object> BinaryBitConvertMap(ConditionBinaryBit data)
    {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("mappingid", data.getMappingId());
        item.put("value", URLEncoder.encode(data.getValue()));
        item.put("operator", "");
        item.put("connector", data.getConnector().getValue());
        item.put("type", QueryType.binaryBit.ordinal());
        return item;
    }

    /**
     * 通过JSONObject转ConditionBinaryBit
     * 
     * @param group 要添加到条件的ConditionGroup对象
     * @param JSONObject 条件的JSON对象
     * @return ConditionBinaryBit对象
     */
    private void JSONObjectConvertBinaryBit(ConditionGroup group, JSONObject item)
    {
        String mappingId = item.getString("mappingid");
        String value = "";
        try
        {
            value = URLDecoder.decode(item.getString("value"), "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ConditionBinaryBit data = new ConditionBinaryBit(mappingId, value);

        enumConnector temp = enumConnector.getKey(item.getString("connector"));
        if (temp.getValue().equals(enumConnector.And.getValue()))
        {
            group.addWithAnd(data);
        }
        else if (temp.getValue().equals(enumConnector.Or.getValue()))
        {
            group.addWithOr(data);
        }else
        {
            group.addWithAnd(data);
        }
    }

    /**
     * 通过ConditionRange转HashMap<String, Object>
     * 
     * @param ConditionRange
     * @return HashMap<String, Object>对象
     */
    @SuppressWarnings("deprecation")
    private HashMap<String, Object> RangeConvertMap(ConditionRange data)
    {
        HashMap<String, Object> value = new HashMap<String, Object>();
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("mappingid", data.getMappingId());
        value.put("start", URLEncoder.encode(data.getMinValue()));
        value.put("end", URLEncoder.encode(data.getMaxValue()));
        item.put("value", value);
        item.put("operator", "");
        item.put("connector", data.getConnector().getValue());
        item.put("type", QueryType.range.ordinal());
        return item;
    }

    /**
     * 通过JSONObject转ConditionRange
     * 
     * @param group 要添加到条件的ConditionGroup对象
     * @param JSONObject 条件的JSON对象
     * @return ConditionRange对象
     */
    private void JSONObjectConvertRange(ConditionGroup group,JSONObject item)
    {
        JSONObject value = JSONObject.fromObject(item.getString("value"));
        String mappingId = item.getString("mappingid");
        String minValue = "", maxValue = "";
        try
        {
            minValue = URLDecoder.decode(value.getString("start"), "utf-8");
            maxValue = URLDecoder.decode(value.getString("end"), "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ConditionRange data = new ConditionRange(mappingId, maxValue, minValue);

        enumConnector temp = enumConnector.getKey(item.getString("connector"));
        if (temp.getValue().equals(enumConnector.And.getValue()))
        {
            group.addWithAnd(data);
        }
        else if (temp.getValue().equals(enumConnector.Or.getValue()))
        {
            group.addWithOr(data);
        }else
        {
            group.addWithAnd(data);
        }
    }

    /**
     * 通过ConditionGroup转List<Object>
     * 
     * @param ConditionGroup
     * @return List<Object>对象
     */
    private List<Object> GroupConvertList(ConditionGroup data)
    {
        List<Object> value = new ArrayList<Object>();

        Object temp = null;
        for (int i = 0; i < data.getCount(); i++)
        {
            temp = data.getValue(i);
            if (temp instanceof ConditionNormal)
            {
                value.add(NormalConvertMap((ConditionNormal) temp));
            }
            else if (temp instanceof ConditionBinaryBit)
            {
                value.add(BinaryBitConvertMap((ConditionBinaryBit) temp));
            }
            else if (temp instanceof ConditionRange)
            {
                value.add(RangeConvertMap((ConditionRange) temp));
            }
            else if (temp instanceof ConditionGroup)
            {
                value.add(GroupConvertMap((ConditionGroup) temp));
            }
        }
        return value;
    }

    /**
     * 通过ConditionGroup转HashMap<String, Object>
     * 
     * @param ConditionGroup
     * @return HashMap<String, Object>对象
     */
    private HashMap<String, Object> GroupConvertMap(ConditionGroup data)
    {
        HashMap<String, Object> item = new HashMap<String, Object>();
        List<Object> value = GroupConvertList(data);
        item.put("mappingid", "");
        item.put("value", value);
        item.put("operator", "");
        item.put("connector", data.getConnector().getValue());
        item.put("type", QueryType.group.ordinal());
        return item;
    }

    /**
     * 通过JSONObject转ConditionGroup
     * 
     * @param group 要添加到条件的ConditionGroup对象
     * @param JSONObject 条件的JSON对象
     * @return ConditionGroup对象
     */
    private void JSONObjectConvertGroup(ConditionGroup group,JSONObject item)
    {
        ConditionGroup data = exchangeJsonToCondition(item.getString("value"));

        enumConnector temp = enumConnector.getKey(item.getString("connector"));
        if (temp.getValue().equals(enumConnector.And.getValue()))
        {
            group.addWithAnd(data);
        }
        else if (temp.getValue().equals(enumConnector.Or.getValue()))
        {
            group.addWithOr(data);
        }else
        {
            group.addWithAnd(data);
        }
    }
}
