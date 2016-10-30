
package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup.enumDelimiter;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;

import net.sf.json.JSONArray;


public class JsonConvertOrderGroup
{

    private List<String> listOrder;
    private OrderGroup objectOrder;

    /**
     * 构造函数
     */
    public JsonConvertOrderGroup()
    {}

    /**
     * 构造函数（带json字符串）
     * 
     * @param json json格式的字符串
     */
    public JsonConvertOrderGroup(String json)
    {
        exchangeJson(json);
    }
    
    /**
     * 根据先已经设置好的排序，获取json字符串
     * @return 返回json字符串
     * @throws NullPointerException 排序条件未初始化，将会引发该异常
     */
    public String getJsonString() throws NullPointerException
    {        
        if(null == this.listOrder) {
            throw new NullPointerException("排序条件未初始化！"); 
        }       
        
        int iPos = 0;
        StringBuffer temp = new StringBuffer();
        String setId = "", mappingId = "", order = "";
        temp.append("[");
        
        String key = "" , delimiterChar = enumDelimiter.TableField.getValue();
        
        for (int index = 0; index < this.listOrder.size() ; index++)
        {
            setId = "";
            mappingId = "";
            key = this.listOrder.get(index);
            
            iPos = key.lastIndexOf(OrderGroup.getOrderSplitChar());
            if (key.lastIndexOf(delimiterChar) >= 0)
            {
                mappingId = key.substring(key.lastIndexOf(delimiterChar) + 1 , iPos);
                setId = key.substring(0,key.lastIndexOf(delimiterChar) ); 
                order = key.substring(iPos + OrderGroup.getOrderSplitChar().length());
            }else{
                mappingId = key.substring(0 , iPos);
                order = key.substring(iPos + OrderGroup.getOrderSplitChar().length());                
            }
            //System.out.println("key::"+key);
            //System.out.println("order::"+order);

            temp.append("{\"setid\":\"" + setId + "\",");
            temp.append("\"mappingid\":\"" + mappingId + "\",");
            temp.append("\"order\":\"" + order + "\"}");
            
            if (index + 1 != this.listOrder.size())
            {
                temp.append(",");
            }
        }        
        temp.append("]");
        return temp.toString();
    }

    /**
     * 获取排序的实体类对象
     */
    public OrderGroup getOrderList()
    {
        return objectOrder;
    }

    /**
     * 获取排序的实体类对象
     */
    public void setOrderList(OrderGroup clsOrder)
    {
        String setId = "" , key = "";
        String mappingId = "" , order = "";

        List<String> item = clsOrder.getOrderList();
        
        objectOrder = new OrderGroup();
        listOrder = clsOrder.getOrderList();
        
        Integer iPos = 0;
        String delimiterChar = enumDelimiter.TableField.getValue();
        for (int index = 0; index < item.size() ; index ++)
        {
            setId = "";
            mappingId = item.get(index);
            setId = "";
            mappingId = "";
            key = this.listOrder.get(index);
            
            iPos = key.lastIndexOf(OrderGroup.getOrderSplitChar());
            if (key.lastIndexOf(delimiterChar) >= 0)
            {
                mappingId = key.substring(key.lastIndexOf(delimiterChar) + 1 , iPos);
                setId = key.substring(0,key.lastIndexOf(delimiterChar) ); 
                order = key.substring(iPos + OrderGroup.getOrderSplitChar().length());
            }else{
                mappingId = key.substring(0 , iPos);
                order = key.substring(iPos + OrderGroup.getOrderSplitChar().length());                
            }
            objectOrder.Add(setId, mappingId,
                            (order.equals(ConditionGroup.enumOrder.Asc.getValue())));
        }
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void exchangeJson(String json)
    {
        this.objectOrder = new OrderGroup();
        this.listOrder = new ArrayList<String>();
        String setid = "", mappingid = "", order = "";

        /**
         * [ {"setid":"member","mappingid":"realname","order":"desc"},
         * {"setid":"member","mappingid":"logincount","order":"asc"} ]
         */
        
        JSONArray jsonArr = JSONArray.fromObject(json);        
        List<Map<String, String>> mapListJson = (List) jsonArr;
        
        for (int i = 0; i < mapListJson.size(); i++)
        {
            Map<String, String> obj = mapListJson.get(i);
            setid = obj.get("setid");
            mappingid = obj.get("mappingid");
            order = obj.get("order");
            
            String item = ("".equals(setid)?"":setid + enumDelimiter.TableField.getValue()) + mappingid;
            item += OrderGroup.getOrderSplitChar();
            item += order;
            
            listOrder.add(item);
            objectOrder.Add(mappingid, (order.equals(ConditionGroup.enumOrder.Asc.getValue())));
            
        }
    }
    
    public static void main(String[] args)
    {
        JsonConvertOrderGroup temp = new JsonConvertOrderGroup();
        OrderGroup clsOrder = new OrderGroup();
        clsOrder.Add("realname", false);
        clsOrder.Add("loginout", true);
        
        temp.setOrderList(clsOrder);
        //System.out.println(clsOrder.getOrderList());
        System.out.println(temp.getJsonString());
        
        JsonConvertOrderGroup test1 = new JsonConvertOrderGroup(temp.getJsonString());
        System.out.println(test1.getJsonString());
    }
}
