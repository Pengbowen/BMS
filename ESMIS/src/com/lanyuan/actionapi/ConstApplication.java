package com.lanyuan.actionapi;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.lanyuan.assembly.dictionary.CollectionDictionary;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;

/**
 * 
 * 项目常量类<br/>
 * 说明:项目使用的常量均在此文件中声明，一般包含有：权限，角色，数据字典等。
 *
 * @author qinye
 * @date 2015年8月14日 下午6:50:44
 */
public class ConstApplication
{
    /**
     * 
     * 权限常量集合<br/>
     * 说明:项目存在多处引用权限的地方，要在此处声明，然后引用声明的常量。
     *
     * @author qinye
     * @date 2015年8月14日 下午6:50:17
     */
    public static class Permission
    {
        /**
         * 新闻列表页
         */
        //public static final String newsList = "news/list.action";       
    }

    /**
     * 
     * 角色枚举
     *
     * @author qinye
     * @date 2015年8月14日 下午6:50:07
     */
    public enum RoleList
    {
        administrator("01", "超级管理员"),
        studentstrator("00","学生");

        /**
         * 角色编号
         */
        private String id;
        /**
         * 角色名称
         */
        private String name;

        /**
         * 
         * 角色枚举的构造函数
         * 
         * @param role 角色编号
         * @param name 角色名称
         */
        RoleList(String role, String name)
        {
            this.id = role;
            this.name = name;
        }

        /**
         * @return 返回 角色编号
         */
        public String getId()
        {
            return id;
        }

        /**
         * @return 返回 角色名称
         */
        public String getName()
        {
            return name;
        }
    }

    /**
     * 
     * 数据字典枚举常量集合
     *
     * @author qinye
     * @date 2015年8月17日 上午11:09:47
     */
    public static enum Dictionary
    {
        CertificatesType(1001,"证件类型");

        private int key;
        private String name;

        Dictionary(int key, String name)
        {
            this.key = key;
            this.name = name;
        }

        /**
         * @return 返回 字典的key值
         */
        public int getKey()
        {
            return key;
        }

        /**
         * @return 返回 字典名称
         */
        public String getName()
        {
            return name;
        }
    }
    /**
     * 
     * 网页导航常量类
     *
     * @author qinye
     * @date 2015年9月2日 下午3:58:20
     */
    public static enum Navigation
    {        
        SuveryIndex("1903","调查问卷");
        
        private String id;
        private String name;
        Navigation(String id,String name)
        { 
            this.id = id;
            this.name = name;
        }

        /**
         * @return 返回 导航项id
         */
        public String getId()
        {
            return id;
        }

        /**
         * @return 返回 导航项名称
         */
        public String getName()
        {
            return name;
        }
    }
    
    /**
     * 
     * 单个文本的常量类
     *
     * @author qinye
     * @date 2015年8月27日 下午2:33:01
     */
    public static enum SingleContent{
        /**
         * 学校简介
         */
        SchoolProfile("1001");
        
        private String id;
        SingleContent(String id)
        {
            this.id = id;
        }
        /**
         * @return 返回 单个文本编号
         */
        public String getId()
        {
            return id;
        }         
    }
    
    /**
     * 获取数据字典
     * 
     * @param dId 数据字典id
     * @return
     */
    public final static LinkedHashMap<String, String> getDicDataList(int dId)
    {
        CollectionDictionary cc = new CollectionDictionary(dId);
        LinkedHashMap<String, String> hs = new LinkedHashMap<String, String>();
        if(cc!=null)
        for (SingleDictionaryData en : cc)
        {
            hs.put(en.getCode(), en.getContent());
        }
        return hs;
    }
    /**
     * 获取数据字典键值对列表
     * 
     * @param dId
     * @return
     */
    public final static TreeMap<String, String> getDicMapList(int dId)
    {
        int dicId = dId;
        CollectionDictionary cc = new CollectionDictionary(dicId);
        TreeMap<String, String> hs = new TreeMap<String, String>();
        if(cc!=null)
        for (SingleDictionaryData en : cc)
        {
            hs.put(en.getCode(), en.getContent());
        }
        return hs;
    }
}
