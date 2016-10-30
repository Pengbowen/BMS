package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 条件集合实体类
 * 
 * @author qinye
 * 
 */
public class ConditionGroup
{
    public enum QueryType
    {
        normal, range, group, binaryBit
    }

    /**
     * 条件连接符，一般有:and,or
     */
    public enum enumConnector
    {
        Null("", "无"), And("and", "并且"), Or("or", "或者");

        private String value;
        private String name;

        private enumConnector(String value, String name)
        {
            this.value = value;
            this.name = name;
        }

        public String toString()
        {
            return super.toString() + ",value=" + this.value + ",name=" + this.name;
        }

        public String getValue()
        {
            return this.value;
        }

        public String getName()
        {
            return this.name;
        }

        public static enumConnector getKey(String value)
        {
            enumConnector key;
            if (value.equals(And.getValue()))
            {
                key = And;
            }
            else if (value.equals(Or.getValue()))
            {
                key = Or;
            }
            else
            {
                key = Null;
            }
            return key;
        }

        public static LinkedHashMap<String, String> getListForControl()
        {
            LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
            list.put(And.getValue(), And.getName());
            list.put(Or.getValue(), Or.getName());
            return list;
        }
    }

    /**
     * 条件操作符，一般有:=,!=,>=,>,<,<=,like,not like
     */
    public enum enumOperator
    {
        Equal("=", "等于"), NoEqual("!=", "不等于"), More(">", "大于"), MoreAndEqual(">=", "大于等于"),
        Less("<", "小于"), LessAndEqual("<=", "小于等于"), Like("like", "包含"), NoLike("not like", "不包含"),
        LeftPercent("%like", "左%"), RightPercent("like%", "右%"), NoPercent("likeno%", "无%"),
        BitAnd("&", "位与运算"), BitOr("|", "位或运算"), IsNull("is null", "是空"),
        IsNotNull("is not null", "不是空");

        private String value;
        private String name;

        private enumOperator(String value, String name)
        {
            this.value = value;
            this.name = name;
        }

        public String toString()
        {
            return super.toString() + ",value=" + this.value + ",name=" + this.name;
        }

        public String getValue()
        {
            return this.value;
        }

        public String getName()
        {
            return this.name;
        }

        public static enumOperator getKey(String value)
        {
            enumOperator key;
            value = value.toLowerCase();
            if (value.equals(Equal.getValue()))
            {
                key = Equal;
            }
            else if (value.equals(NoEqual.getValue()))
            {
                key = NoEqual;
            }
            else if (value.equals(More.getValue()))
            {
                key = More;
            }
            else if (value.equals(MoreAndEqual.getValue()))
            {
                key = MoreAndEqual;
            }
            else if (value.equals(Less.getValue()))
            {
                key = Less;
            }
            else if (value.equals(LessAndEqual.getValue()))
            {
                key = LessAndEqual;
            }
            else if (value.equals(Like.getValue()))
            {
                key = Like;
            }
            else if (value.equals(NoLike.getValue()))
            {
                key = NoLike;
            }
            else if (value.equals(LeftPercent.getValue()))
            {
                key = LeftPercent;
            }
            else if (value.equals(RightPercent.getValue()))
            {
                key = RightPercent;
            }
            else if (value.equals(NoPercent.getValue()))
            {
                key = NoPercent;
            }
            else if (value.equals(BitAnd.getValue()))
            {
                key = BitAnd;
            }
            else if (value.equals(BitOr.getValue()))
            {
                key = BitOr;
            }
            else
            {
                key = Equal;
            }
            return key;
        }

        public static LinkedHashMap<String, String> getListForControl()
        {
            LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
            for (enumOperator item : enumOperator.values())
            {
                list.put(item.getValue(), item.getName());
            }
            return list;
        }
    }

    /**
     * 条件定界符，一般有:(,),%,'
     */
    public enum enumDelimiter
    {
        LeftBracket("("), RightBracket(")"), Like("%"), Char("'"), TableField(".");

        private String value;

        private enumDelimiter(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return super.toString() + ",value=" + this.value;
        }

        public String getValue()
        {
            return this.value;
        }

        public static enumDelimiter getKey(String value)
        {
            enumDelimiter key;
            if (value.equals(LeftBracket.getValue()))
            {
                key = LeftBracket;
            }
            else if (value.equals(RightBracket.getValue()))
            {
                key = RightBracket;
            }
            else if (value.equals(Like.getValue()))
            {
                key = Like;
            }
            else if (value.equals(Char.getValue()))
            {
                key = Char;
            }
            else
            {
                key = null;
            }
            return key;
        }
    }

    /**
     * 条件排序方式操作符，有:asc,desc
     * 
     * @author qinye
     * 
     */
    public enum enumOrder
    {
        Asc("asc"), Desc("desc");
        private String value;

        private enumOrder(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return super.toString() + ",value=" + this.value;
        }

        public String getValue()
        {
            return this.value;
        }

        public static enumOrder getKey(String value)
        {
            enumOrder key;
            if (value.equals(Asc.getValue()))
            {
                key = Asc;
            }
            else if (value.equals(Desc.getValue()))
            {
                key = Desc;
            }
            else
            {
                key = null;
            }
            return key;
        }
    }

    public enum enumFiledType
    {
        Number, Char;
    }

    private ArrayList<Object> queryList; // 条件集合
    private enumConnector connector; // 条件连接符

    private String getMappingIdOf(Object condition)
    {
        String eMappingId = null;
        try
        {
            Method getMappingId = condition.getClass().getMethod("getMappingId");
            eMappingId = getMappingId.invoke(condition).toString();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return eMappingId;
    }

    private void setConnectorOf(Object condition,enumConnector connector)
    {
        try
        {
            Method setConnector = condition.getClass().getMethod("setConnector",enumConnector.class);
            setConnector.invoke(condition,connector);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    private String getValueFromGroupWithMappingId(ConditionGroup group, String mappingid)
    {
        String eMappingId = null;
        String targetValue = null;
        int iCount = group.getCount();
        for (int i = 0; i < iCount; i++)
        {
            Object condition = group.getValue(i);
            if (condition instanceof ConditionGroup)
            {
                // targetValue =
                // this.getValueFromGroupWithMappingId((ConditionGroup)
                // condition,
                // mappingid);
                // if (targetValue != null) break;
            }
            else
            {
                eMappingId = this.getMappingIdOf(condition);
                if (eMappingId.equalsIgnoreCase(mappingid))
                {
                    if (condition instanceof ConditionRange)
                    {
                        targetValue = ((ConditionRange) condition).getMinValue() + ","
                                + ((ConditionRange) condition).getMaxValue();
                    }
                    else if (condition instanceof ConditionBinaryBit)
                    {
                        targetValue = ((ConditionBinaryBit) condition).getValue();
                    }
                    else if (condition instanceof ConditionNormal)
                    {
                        targetValue = ((ConditionNormal) condition).getValue();
                    }
                    break;
                }
            }
        }
        return targetValue;
    }

    /**
     * 设置条件的连接符
     */
    public void setConnector(enumConnector connector)
    {
        this.connector = connector;
    }

    /**
     * 获取条件的连接符
     */
    public enumConnector getConnector()
    {
        return connector;
    }

    public ConditionGroup()
    {
        this.queryList = new ArrayList<Object>();
        this.connector = enumConnector.Null;
    }

//    /**
//     * 增加普通条件
//     * 
//     * @param clsQuery 条件对象
//     * @return 成功返回true，失败返回false
//     */
//    public boolean addForFirst(ConditionNormal clsQuery)
//    {
//        if (null != clsQuery)
//        {
//            if (this.queryList.size() > 0)
//            {
//                throw new IllegalArgumentException("已经存在条件，addForFirst失败！");
//            }
//            this.queryList.add(clsQuery);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    /**
     * 增加普通条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithAnd(ConditionNormal clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.And);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 增加普通条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithOr(ConditionNormal clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.Or);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

//    /**
//     * 增加范围条件
//     * 
//     * @param clsQuery 条件对象
//     * @return 成功返回true，失败返回false
//     */
//    public boolean addForFirst(ConditionRange clsQuery)
//    {
//        if (null != clsQuery)
//        {
//            if (this.queryList.size() > 0)
//            {
//                throw new IllegalArgumentException("已经存在条件，addForFirst失败！");
//            }
//            this.queryList.add(clsQuery);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    /**
     * 增加范围条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithAnd(ConditionRange clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.And);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 增加范围条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithOr(ConditionRange clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.Or);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

//    /**
//     * 增加二进制位运算条件
//     * 
//     * @param clsQuery 条件对象
//     * @return 成功返回true，失败返回false
//     */
//    public boolean addForFirst(ConditionBinaryBit clsQuery)
//    {
//        if (null != clsQuery)
//        {
//            if (this.queryList.size() > 0)
//            {
//                throw new IllegalArgumentException("已经存在条件，addForFirst失败！");
//            }
//            this.queryList.add(clsQuery);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    /**
     * 增加二进制位运算条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithAnd(ConditionBinaryBit clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.And);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 增加二进制位运算条件
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithOr(ConditionBinaryBit clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.Or);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

//    /**
//     * 增加条件组
//     * 
//     * @param clsQuery 条件对象
//     * @return 成功返回true，失败返回false
//     */
//    public boolean addForFirst(ConditionGroup clsQuery)
//    {
//        if (null != clsQuery)
//        {
//            if (this.queryList.size() > 0)
//            {
//                throw new IllegalArgumentException("已经存在条件，addForFirst失败！");
//            }
//            this.queryList.add(clsQuery);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    /**
     * 增加条件组
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithAnd(ConditionGroup clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.And);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 增加条件组
     * 
     * @param clsQuery 条件对象
     * @return 成功返回true，失败返回false
     */
    public boolean addWithOr(ConditionGroup clsQuery)
    {
        if (null != clsQuery)
        {
            if (this.queryList.size() > 0)
            {
                clsQuery.setConnector(enumConnector.Or);
            }
            this.queryList.add(clsQuery);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 清空条件对象
     */
    public void clear()
    {
        this.queryList.clear();
    }

    /**
     * 获取当前条件队列中的条件对象个数
     * 
     * @return 返回条件个数
     */
    public int getCount()
    {
        return this.queryList.size();
    }

    public Object getValue(int index)
    {
        return this.queryList.get(index);
    }
    
    /**
     * 获取指定conditionName的条件的值,搜索不包括子条件,
     * 
     * @param conditionName 要查找的第一个条件名
     * @return 返回搜索到的第一个条件的值,conditionRange类型返回为"minvalue,maxvalue" 字符串
     */
    public String getConditionValueWithConditionName(String conditionName)
    {
        return this.getValueFromGroupWithMappingId(this, conditionName);
    }
    
    /**
     * 删除指定索引的条件对象
     * 
     * @param index 删除对象的索引值
     * @return 删除的对象 
     */
    public Object removeConditionWithIndex(int index)
    {
        return this.queryList.remove(index);
    }

    /**
     * 删除指定的条件，不能删除ConditionGroup类型对象
     * 
     * @param conditionName 条件名
     * @return 删除成功返回true,否则返回false 
     */
    public boolean removeConditionWithConditionName(String conditionName)
    {
        int i = 0;
        for (Object cond : this.queryList)
        {
            if (!(cond instanceof ConditionGroup))
            {
                String mappingId = this.getMappingIdOf(cond);
                if (mappingId.equalsIgnoreCase(conditionName))
                {
                   if (i == 0 &&  queryList.size() > 1 ) //第一个元素
                   {
                       Object two = queryList.get(i + 1);
                       setConnectorOf(two, enumConnector.Null);
                   }
                   this.queryList.remove(i);
                   return true;
                }
            }
            i++;
        }
        return false;
    }
    
    /**
     * 获取条件组中的所有子条件对象
     * 
     * @return 返回包含所有子条件对象的数组,未找到返回null
     */
    public ArrayList<ConditionGroup> getAllConditionGroup()
    {
        ArrayList<ConditionGroup> arrayGroup = new ArrayList<ConditionGroup>();
        for ( Object cond : queryList )
        {
            if ( cond instanceof ConditionGroup )
            {
                arrayGroup.add( (ConditionGroup)cond );
            }
        }
        return arrayGroup;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ConditionGroup [connector=" + connector + ", queryList=" + queryList + "]";
    }

    // public static void main(String[] args)
    // {
    // ConditionGroup temp = new ConditionGroup();
    // temp.addWithAnd(new ConditionNormal("", ""));
    // temp.addWithAnd(new ConditionNormal("", ""));
    // temp.addWithAnd(new ConditionNormal("", ""));
    //
    // ConditionGroup clsQuery = new ConditionGroup();
    // temp.addWithAnd(clsQuery);
    // }
}
