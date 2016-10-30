package com.lanyuan.actionapi.standardlibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.opensymphony.xwork2.Action;

public class ISearchStandardDynamicToPhone extends ResultSearchAction
{
    private static final long serialVersionUID = 1L;

    public String execute(){
        List<HashMap<String , String>> datalist = new ArrayList<HashMap<String,String>>();
        
        HashMap<String , String> map0 = new HashMap<String , String>();
        map0.put("title", "(已作废)煤的可磨性指数测定方法(哈德格罗夫法)");
        map0.put("brief", "ICS 73.040D  21中 华 人 民 共 和 国 国 家 标准GB/T  2565—1998煤的可磨性指数测定方法(哈德格罗夫法)Determination of grindability index of coal(Hardgrove method)1998-12-08发布                                                      1999-05-01实施国 家 质 量 技 术 监 督 局  发 布前   言本标准是根据国际标准ISO 5074：1");
        map0.put("url", "www.baidu.com");
        map0.put("approvedDate", "2015-10-01");
        map0.put("type", "0");
        datalist.add(map0);
        
        HashMap<String , String> map1 = new HashMap<String , String>();
        map1.put("title", "电工术语 电力电容器");
        map1.put("brief", "ICS 29.020K 04中 华 人 民 共 和 国 国 家 标 准GB/T2900.16—1996电 工 术 语   电 力 电 容 器Electrotechnical  terminologyPower capacitors1996-06-17发布                                                      1997-07-01实施国 家 技 术 监 督 局  发 布中 华 人 民 共 和 国 国 家 标 淮GB/T  2900.16—1996电 工 术");
        map1.put("url", "www.baidu.com");
        map1.put("approvedDate", "2016-10-01");
        map1.put("type", "1");
        datalist.add(map1);
        
        HashMap<String , String> map2 = new HashMap<String , String>();
        map2.put("title", "光伏发电站接入电力系统设计规范");
        map2.put("brief", "ICS 29.020K 04中 华 人 民 共 和 国 国 家 标 准GB/T2900.16—1996电 工 术 语   电 力 电 容 器Electrotechnical  terminologyPower capacitors1996-06-17发布                                                      1997-07-01实施国 家 技 术 监 督 局  发 布中 华 人 民 共 和 国 国 家 标 淮GB/T  2900.16—1996光伏发电");
        map2.put("url", "www.baidu.com");
        map2.put("approvedDate", "2016-10-01");
        map2.put("type", "2");
        datalist.add(map2);
        
        HashMap<String , String> map3 = new HashMap<String , String>();
        map3.put("title", "防止电力生产重大事故的二十五项重点要求");
        map3.put("brief", "防止电力生产重大事故的二十五项重点要求国家电力公司2000—9—28发布关于印发《防止电力生产重大事故的二十五项重点要求》的通知国电发[2000]589号各分公司，华北电力集团公司，各省(自治区、直辖市)电力公司，华能集团公司，华能国际，中电国际，国电电力，乌江公司，电规总院，水规总院，东北、华北、华东、西北、西南、中南电力设计院，电力科学研究院，武汉高压研究所，苏州热工所，各水电工程局，各水电勘测设计院，安能总公司：为进一步落实《中共中央关于国有企业改革和发展若干重大问题的决定》中关于“坚持预防为主，落");
        map3.put("url", "www.baidu.com");
        map3.put("approvedDate", "2016-10-01");
        map3.put("type", "3");
        datalist.add(map3);
        
        HashMap<String , String> map4 = new HashMap<String , String>();
        map4.put("title", "演变过程");
        map4.put("brief", "电工术语 电力电容器的演变过程");
        map4.put("url", "www.baidu.com");
        map4.put("approvedDate", "2016-10-01");
        map4.put("type", "4");
        datalist.add(map4);
        
        HashMap<String , String> map5 = new HashMap<String , String>();
        map5.put("title", "年报");
        map5.put("brief", "电工术语 的年报");
        map5.put("url", "www.baidu.com");
        map5.put("approvedDate", "2016-10-01");
        map5.put("type", "5");
        datalist.add(map5);
        
        this.printString(datalist, 6, "1", "");
        return Action.SUCCESS;
    }
}
