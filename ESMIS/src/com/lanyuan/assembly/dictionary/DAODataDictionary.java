package com.lanyuan.assembly.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanyuan.assembly.basic.appexception.DAOException;
import com.lanyuan.assembly.basic.baseclasses.DataHandle;
import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.basic.database.ConnectionUtils;
import com.lanyuan.assembly.basic.database.DataUtils;
import com.lanyuan.assembly.basic.sqlstring.EntitySql;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.QueryResolver;
import com.lanyuan.assembly.dictionary.EntityDataDictionary.FieldList;

/**
 * 
 * 数据字典数据的额DAO类
 *
 * @author qinye
 * @date 2014-11-22 下午08:17:39
 */
class DAODataDictionary extends DataHandle
{
    public QueryResolver objectResolver;
    
    //、根据字典content查询
    String sSchByContentSql = "select  code  from " + EntityDataDictionary.s_tableName
            + " where dictionaryid = ? and content like ?";
    
    // 增加字典数据的记录
    String sAddSql = "insert  into " + EntityDataDictionary.s_tableName
            + " values (?,?,?,?,?,?,?,?)";

    // 删除字典数据记录
    String sDelSql = "delete from " + EntityDataDictionary.s_tableName
            + " where id=? and  dictionaryid = ?";

    // 删除字典数据记录
    String sDelByIdSql = "delete from " + EntityDataDictionary.s_tableName + " where id=?";

    // 修改字典数据
    String sUpSql = "update "
            + EntityDataDictionary.s_tableName
            + " set  code=?,content=?,customfield1=?,showNum=?,serviceClass=?,fid=? where id=? and dictionaryid=?";

    // //1、分页查询
    String sSchSql = "select  *  from " + EntityDataDictionary.s_tableName + " limit ?,?";

    // //1、分页查询字典id下的数据
    String sSchDicSql = "select  *  from " + EntityDataDictionary.s_tableName
            + " where dictionaryid=? ";

    // //2、根据字典ID查询
    String sSchBydicIDSql = "select  *  from " + EntityDataDictionary.s_tableName
            + " where dictionaryid = ? order by id asc";

    // //3、根据字典ID以及对应父ID
    String sSchBydicIDAndIDSql = "select *  from " + EntityDataDictionary.s_tableName
            + " where dictionaryid = ? and  id = ?";

    // //4、根据字典ID以及编号 findBydicID_CodeSql
    String sSchBydicIDAndCodeSql = "select  *  from " + EntityDataDictionary.s_tableName
            + " where dictionaryid = ? and  code = ?";

    // 查询总页数sql
    String sTotalRowsAllSql = "select count(*) from " + EntityDataDictionary.s_tableName;

    // 查询总页数sql
    String sTotalRowsSql = "select count(*) from " + EntityDataDictionary.s_tableName
            + " where dictionaryid = ?";
    // 获取最大id并+1
    String getMaxIdSql = "select max(id) max from  " + EntityDataDictionary.s_tableName;

    // 获取最大dictionaryId
    String getMaxDictionaryId = "SELECT max(code+0) FROM " + EntityDataDictionary.s_tableName
            + " WHERE dictionaryid = ? and code REGEXP '[1-9][0-9]*'";

    /**
     * end------
     */

    // 根据id和dictionaryID判断字典数据记录是否存在
    String sIsExistDataSql = "select count(*) from " + EntityDataDictionary.s_tableName
            + " where id = ? and dictionaryid = ?";

    /**
     * 构造函数
     * 
     */
    public DAODataDictionary()
    {
        super();
        this.objectResolver = new QueryResolver();
    }
    
    /**
     *（条件查询）根据字典dictionaryid,Content获取对应记录对象
     * 
     * @param iDictionaryID 字典ID
     * @return 返回字典数据对象数组
     * @throws ConnException
     */
    public List<Map<String, String>> searchByContent(int dictionaryid,String content)
    {   
        content = "%"+content+"%";
        // 对象数组，用于存储查询到的对象并返回
        Object[] param = {dictionaryid, content };
        List<Map<String, String>> listObject = this.executeSelect(sSchByContentSql, param);
        return listObject;
    }
    
    /**
     * 字典数据的增加， 关联字典表，需要排除三种情况 (1)判断这条记录是否存在，根据数据ID和字典ID确保唯一性isExistData()
     * (2)判断字典id在字典中不存在，用 if( ! isExistById(int id))方法 (3)判断父id小于等于0或者不存在
     * if(id<=0 || ! isExistById(id)) 添加字典数据
     * 
     * @param data_info 字典数据的基本类对象
     * @return 成功返回空字符串，失败返回错误信息
     * @throws ConnException
     */
    public int add(EntityDataDictionary data_info) throws ConnException
    {
        data_info.setId(getMaxId());
        DAODictionary basic = new DAODictionary();

        // 排除三种情况
        // 1、判断这条记录是否存在，根据数据ID和字典ID确保唯一性isExistData()
        this.assertNotNull(data_info, "字典数据项不能为空！");

        if (isExistData(data_info.getId(), data_info.getDictionaryid()))
        {
            this.assertStringValid("违反了数据ID和字典ID联合主键的唯一性，请重新添加。");
        }

        // 2、根据字典id判断对应的记录是否存在
        if (!basic.isExistById(data_info.getDictionaryid()))
        {
            this.assertStringValid("字典ID不存在，请重新添加。");
        }

        // 3、根据父id判断 ：无效或者不存在
        if (data_info.getFid() <= 0 || !basic.isExistById(data_info.getFid()))
        {
            this.assertStringValid("父ID输入错误或者不存在，请重新添加。");
        }
        EntitySql temp = data_info.getInsertIntoSql();
        int iN = this.executeUpdate(temp.getSql(), temp.getParameter());
        return iN;
    }

    public int getMaxId()
    {
        int num = this.getCountBySql(getMaxIdSql, new Object[] {});
        if (num < 1)
        {
            return -1;
        }
        return num + 1;
    }

    /**
     * 获取最大dictionaryId并+1
     */
    public int getMaxDictionaryId(Integer dictionaryId)
    {
        int num = this.getCountBySql(getMaxDictionaryId, new Object[] { dictionaryId });
        if (num < 1)
        {
            return -1;
        }
        return num + 1;
    }

    /**
     * 字典数据的删除
     * 
     * @param iDictionaryID 字典ID
     * @param id 序号id
     * @return 删除成功：返回空值；失败：返回错误信息
     * @throws ConnException
     */
    public int delete(int id, int iDictionaryID) throws ConnException
    {
        Object param[] = { id, iDictionaryID };
        return this.executeUpdate(sDelSql, param);
    }

    /**
     * 字典数据的删除
     * 
     * @param iDictionaryID 字典ID
     * @param id 序号id
     * @return 删除成功：返回空值；失败：返回错误信息
     * @throws ConnException
     */
    public int deleteByDictionary(int iDictionaryID) throws ConnException
    {
        String sql = "delete from " + EntityDataDictionary.s_tableName + " where dictionaryid = ?";
        Object param[] = { iDictionaryID };
        return this.executeUpdate(sql, param);
    }

    /**
     * 字典数据的修改 　 (1)id, dictionaryID不能修改
     * 　　　(2)根据id,dictionaryID判断记录是否存在，存在修改不存在则不修改 　　　(3)判断父id是否相同，不相同修改父id 　　　
     * (4)判断父id不为null并且存在 if(id != null &&isExist(id))
     * 根据id获取原对象记录，判断新纪录与原纪录字段是否相同， 不同则修改，相同就不改
     * 
     * @param data_info 字典数据实体对象
     * @throws ConnException
     */
    public int update(EntityDataDictionary data_info) throws ConnException
    {
        // DAOBasicDictionary basic = new DAOBasicDictionary();
        // //根据父ID判断在字典表中ID是否存在
        // if(! basic.isExistById(data_info.getfID())){
        // return "父ID在字典表中不存在，无法修改父ID，请确认再操作！";
        // }
        // 判断要修改的id是否存在
        if (!isExistData(data_info.getId(), data_info.getDictionaryid()))
        {
            this.assertStringValid("数据ID或者字典ID不存在，无法修改，请确认再操作！");
        }

        Object param[] = { data_info.getCode(), data_info.getContent(),
                data_info.getCustomfield1(), data_info.getShowNum(), data_info.getServiceClass(),
                data_info.getFid(), data_info.getId(), data_info.getDictionaryid() };
        return this.executeUpdate(sUpSql, param);
    }

    /**
     * 1、分页查询字典数据
     * 
     * @param iPage 要查询的页数
     * @param iPageSize 每页显示的条数
     * @return 将JAVA对象转换成JSON字符串后返回
     * @throws ConnException
     */
    public List<EntityDataDictionary> search(int iPage, int iPageSize) throws ConnException
    {
        // 在结果集中，第page页的记录显示pageSize条
        int iBegin = (iPage - 1) * iPageSize; // mysql条数从0开始
        Object[] param = { iBegin, iPageSize };
        List<Map<String, String>> listObject = this.executeSelect(sSchSql, param);
        return setPropertyData(listObject);
    }

    /**
     * 1、分页查询字典数据
     * 
     * @param iPage 要查询的页数
     * @param iPageSize 每页显示的条数
     * @return 将JAVA对象转换成JSON字符串后返回
     * @throws ConnException
     */
    public List<EntityDataDictionary> search(int iPage, int iPageSize, int iDictionaryID,
            String field1)
    {
        // 在结果集中，第page页的记录显示pageSize条
        int iBegin = -1;
        List<Object> param = new ArrayList<Object>();
        param.add(iDictionaryID);
        if (!"".equals(field1) && !"-1".equals(field1))
        {
            sSchDicSql += "  and customfield1=? ";
            param.add(field1);
        }
        if (iPage != -1 && iPageSize != -1)
        {
            sSchDicSql += "  limit ?,? ";
            iBegin = (iPage - 1) * iPageSize;
            param.add(iBegin);
            param.add(iPageSize);
        }
        List<Map<String, String>> listObject = this.executeSelect(sSchDicSql, param.toArray());
        return setPropertyData(listObject);
    }

    /**
     * 2、（条件查询）根据字典ID获取对应记录对象
     * 
     * @param iDictionaryID 字典ID
     * @return 返回字典数据对象数组
     * @throws ConnException
     */
    public List<EntityDataDictionary> searchBydicID(int iDictionaryID)
    {
        // 对象数组，用于存储查询到的对象并返回
        Object[] param = { iDictionaryID };
        List<Map<String, String>> listObject = this.executeSelect(sSchBydicIDSql, param);
        return setPropertyData(listObject);
    }

    /**
     * 3、根据字典ID以及对应父ID获取结果
     * 
     * @param iDictionaryID 字典ID
     * @param iFID 父ID
     * @return 返回字典数据对象数组
     * @throws ConnException
     */
    public EntityDataDictionary searchBydicIdAndID(int iDictionaryID, int id)
    {

        Object[] param = { iDictionaryID, id };
        List<Map<String, String>> listObject = this.executeSelect(sSchBydicIDAndIDSql, param);

        if (listObject == null)
        {
            return null;
        }
        List<EntityDataDictionary> listData = setPropertyData(listObject);
        return listData.get(0);
    }

    /**
     * 4、根据字典ID以及编号获取
     * 
     * @param iDictionaryID 字典ID
     * @param sCode 编码
     * @return 返回字典数据对象数组
     */
    public List<EntityDataDictionary> searchBydicIdCode(int iDictionaryID, String sCode)
    {
        Object[] param = { iDictionaryID, sCode };
        List<Map<String, String>> listObject = this.executeSelect(sSchBydicIDAndCodeSql, param);
        return setPropertyData(listObject);
    }

    /**
     * 根据数据ID和字典ID 判断记录在字典数据中的唯一性
     * 
     * @param iId 数据ID
     * @param iDictionaryID 字典ID
     * @return 返回true：已存在；false：不存在
     * @throws ConnException
     */
    public boolean isExistData(int iId, int iDictionaryID) throws ConnException
    {
        boolean flag = false; // 用于返回是否存在记录
        Object param[] = { iId, iDictionaryID }; // 参数数组
        int iN = this.getCountBySql(sIsExistDataSql, param);
        // 根据条数的总和大于0，说明记录存在
        if (iN > 0)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取分页显示时的总页数
     * 
     * @param iPageSize 每页显示的条数
     * @return 返回分页时的总页数
     * @throws ConnException
     */
    public int totalPages(int iPageSize) throws ConnException
    {
        int iTotal = ConnectionUtils.totalPages(sTotalRowsAllSql, iPageSize); // 用于返回数据，总页数
        return iTotal;
    }

    public int totalRows(int dicId) throws ConnException
    {
        Object[] param = { dicId };
        int totalRows = this.getCountBySql(sTotalRowsSql, param); // 用于返回当前字典id下的总记录数
        return totalRows;
    }

    /**
     * 为实体类对象添加属性
     * 
     * @param listObject list集合中存放着Map集合，Map中以键值对属性名和属性值的方式封装着对象的所有属性
     * @return 返回获得实例对象集合
     */
    public List<EntityDataDictionary> setPropertyData(List<Map<String, String>> listObject)
    {
        // 存储对象集合用于返回
        List<EntityDataDictionary> mapObj = new ArrayList<EntityDataDictionary>();

        // 1、遍历对象集合，n个对象
        if (null != listObject)
        {
            for (int i = 0; i < listObject.size(); i++)
            {
                Map<String, String> map = listObject.get(i); // 从对象集合中遍历每一个对象
                EntityDataDictionary data = new EntityDataDictionary();
                // 2、遍历每一个对象数组 ，m个属性
                if (map.get("id") != null)
                {
                    data.setId(Integer.parseInt((String) map.get("id")));
                }
                if (map.get("dictionaryid") != null)
                {
                    data.setDictionaryid(Integer.parseInt((String) map.get("dictionaryid")));
                }
                if (map.get("code") != null)
                {
                    data.setCode((String) map.get("code"));
                }
                if (map.get("content") != null)
                {
                    data.setContent((String) map.get("content"));
                }
                if (map.get("customfield1") != null)
                {
                    data.setCustomfield1((String) map.get("customfield1"));
                }
                if (map.get("showNum") != null)
                {
                    data.setShowNum(Integer.parseInt((String) map.get("showNum")));
                }
                if (map.get("serviceClass") != null)
                {
                    data.setServiceClass((String) map.get("serviceClass"));
                }
                if (map.get("fid") != null)
                {
                    data.setFid(Integer.parseInt((String) map.get("fid")));
                }
                // 把对象添加到集合中，用于返回对象集合
                mapObj.add(data);
            }
        }
        return mapObj;
    }

    /**
     * 计算查询结果的记录总数
     * 
     * @param clsQuery 查询条件
     * @return 成功返回总数 失败返回0
     * @throws DAOException 数据库执行异常
     */
    public int getTotalCount(ConditionGroup clsQuery) throws DAOException
    {
        int ireturn = 0;
        StringBuffer sql = new StringBuffer();
        EntitySql objSql = objectResolver.getWhereSql(clsQuery, null, false);
        sql.append("select count(*) from " + EntityDataDictionary.s_tableName);
        if (objSql != null)
        {
            sql.append(objSql.getSql());
            ireturn = this.getCountBySql(sql.toString(), objSql.getParameter());
        }
        else
        {
            ireturn = this.getCountBySql(sql.toString(), new Object[] {});
        }
        return ireturn;
    }

    /**
     * 条件查询
     * 
     * @Title selectBill
     * @Description 条件查询
     * @param tags
     * @return List<EntityComment>
     * @throws 空
     */
    public List<EntityDataDictionary>
            selectByCondition(ConditionGroup clsQuery, OrderGroup clsOrder) throws DAOException
    {

        this.error = "";
        EntitySql clsData = objectResolver.getWhereSql(clsQuery, clsOrder, false);
        if (null == clsData) return null;

        StringBuffer sql = new StringBuffer();
        sql.append("select * from ");
        sql.append(EntityDataDictionary.s_tableName);
        sql.append(" ");

        List<EntityDataDictionary> listSelect = null;
        sql.append(clsData.getSql());
        listSelect = this.executeSelect(sql.toString(), clsData.getParameter(),
                                        EntityDataDictionary.class);
        if (null == listSelect)
        {
            return null;
        }

        return listSelect;
    }

    /**
     * 条件查询（分页）
     * 
     * @Title selectBillAndPage
     * @Description 条件查询（分页）
     * @param tags
     * @return List<EntityDataDictionary>
     * @throws DAOException 数据库执行异常
     */
    public List<EntityDataDictionary> selectByConditionAndPage(int page, int pagecount,
            ConditionGroup clsQuery, OrderGroup clsOrder) throws DAOException
    {
        this.error = "";
        EntitySql clsData = null;
        clsData = objectResolver.getWhereSqlByPage(page, pagecount, clsQuery, clsOrder, false);
        if (null == clsData) return null;

        StringBuffer sql = new StringBuffer();
        sql.append("select * from ");
        sql.append(EntityDataDictionary.s_tableName);
        sql.append(" ");

        List<EntityDataDictionary> listSelect = null;
        sql.append(clsData.getSql());
        listSelect = this.executeSelect(sql.toString(), clsData.getParameter(),
                                        EntityDataDictionary.class);

        if (null == listSelect)
        {
            return null;
        }

        return listSelect;
    }

    public int getNewId()
    {
        // TODO Auto-generated method stub
        return DataUtils.createNewIdByNaturalNumber(EntityDataDictionary.s_tableName,
                                                    FieldList.id.getValue());
    }

    // 新框架的增删改查
    public Integer delete(int id) throws NullPointerException, DAOException
    {

        this.assertNotNull(id, "关键值不能为空");
        String delSpaceSql = "delete from " + EntityDataDictionary.s_tableName + " where "
                + FieldList.id.getValue() + " = ? ";
        Object param[] = { id };
        int iN = this.executeUpdate(delSpaceSql, param);
        return iN;
    }

    public EntityDataDictionary selectById(Integer id)
    {
        // System.out.println("1111");

        String key = FieldList.id.getValue();
        // System.out.println("key的值为："+key);

        ConditionGroup clsQuery = new ConditionGroup();
        clsQuery.addWithAnd(new ConditionNormal(key, id + "", enumOperator.Equal));
        EntitySql clsData = objectResolver.getWhereSql(clsQuery, null, false);
        // System.out.println("clsData为："+clsData);

        if (null == clsData) return null;

        StringBuffer sql = new StringBuffer();
        sql.append("select * from ");
        sql.append(EntityDataDictionary.s_tableName);
        sql.append(" ");
        sql.append(clsData.getSql());

        // System.out.println("sql为："+sql);
        List<EntityDataDictionary> listSelect = null;
        listSelect = this.executeSelect(sql.toString(), clsData.getParameter(),
                                        EntityDataDictionary.class);
        if (null == listSelect) return null;
        // System.out.println("listSelect的值："+listSelect);
        return listSelect.get(0);
    }

    /**
     * 修改
     * 
     * @param cls 的实体对象
     * @param levelId
     * @return 修改数
     * @throws DAOException 数据库操作异常的抛出
     * 
     */
    public Integer modify(int id, EntityDataDictionary cls) throws NullPointerException,
            DAOException
    {
        this.assertNotNull(id, "关键值不能为空");
        this.assertNotNull(cls, "实体对象不能为空！");
        String sql = " where " + FieldList.id.getValue() + " = ? ";
        Object[] param = { id };
        EntitySql objSql = cls.getUpdateSql(sql, param);
        int iN = this.executeUpdate(objSql.getSql(), objSql.getParameter());
        return iN;
    }

    /**
     * 4 月 19 日晚新增
     */
    /**
     * 增加
     * 
     * @param cls 的实体对象
     * @return 增加数值
     * @throws DAOException 数据库操作异常的抛出
     * 
     */
    public Integer add1(EntityDataDictionary cls) throws NullPointerException, DAOException
    {
        // System.out.println("执行add1方法");
        this.assertNotNull(cls, "实体对象不能为空！");
        if (isHasData(cls.getId()))
        {
            return -1;
        }
        // System.out.println("cls.getId()的值为"+cls.getId());
        EntitySql objSql = cls.getInsertIntoSql();
        // System.out.println("objSql的值为："+objSql);
        int iN = this.executeUpdate(objSql);
        // System.out.println(iN+"in的值为");
        return iN;
    }

    private boolean isHasData(Integer id)
    {
        String sql = "select count(*) from " + EntityDataDictionary.s_tableName + " where "
                + FieldList.id.getValue() + " = ? ";
        Object[] param = { id };
        int count = this.getCountBySql(sql, param);
        if (count > 0)
        {
            return true;
        }
        return false;
    }
    public List<String> selectByCode(String code){
        if(code==null||"".equals(code)) return null;
        String[] arr = code.split(",");
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ");
        sql.append(EntityDataDictionary.s_tableName);
        sql.append(" where code in (");
        for(int i=0;i<arr.length;i++){
        	sql.append("'");
        	sql.append(arr[i].trim());
        	sql.append("'");
        	if(i+1!=arr.length) {
        	sql.append(",");
        	}
        }
        sql.append(")");
        // System.out.println("sql为："+sql);
        List<EntityDataDictionary> listSelect = null;
        listSelect = this.executeSelect(sql.toString(), null,EntityDataDictionary.class);
        if (null == listSelect) return null;
        List<String> list = new ArrayList<String>();
        for (EntityDataDictionary entity : listSelect) {
			list.add(entity.getContent());
		}
        return list;
    }
}
