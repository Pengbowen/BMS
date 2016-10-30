package com.lanyuan.assembly.netdisk;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.common.ServersManager;
import com.lanyuan.assembly.util.StrUtil;

@Service("netdiskService")
public class ServiceNetdiskImpl extends ServersManager<SingleNetdisk,EntityNetdisk, DaoNetdisk>
		implements ServiceNetdisk {
	@Autowired
	public void setDao(DaoNetdisk dao) {
		this.dao = dao;
	}
	/**
	 * 新增文件夹实现类
	 */
    public int addParentObject(SingleNetdisk singleNetdisk)
    {
    	/**定义实体类*/
        EntityNetdisk entityNetdisk = new EntityNetdisk();
        BeanUtils.copyProperties(singleNetdisk,entityNetdisk);
        return dao.insert(entityNetdisk);
    }
    /**
     * 修改文件，文件夹实现类
     */
    public int modifyParentObject(SingleNetdisk singleNetdisk)
    {
    	/**定义实体类*/
        EntityNetdisk entityNetdisk = new EntityNetdisk();
        if(singleNetdisk==null){
        	return 0;
        }
        BeanUtils.copyProperties(singleNetdisk,entityNetdisk);
        return dao.update(entityNetdisk);
    }
    /**
     * 根据对象名称判断文件，文件夹是否唯一
     */
    public boolean isOnlyParentObjectByName(String objectNo,String parentobjectNo,String objectName)
    {
    	/**定义实体类*/
        ConditionGroup cond = new ConditionGroup();
        if (!StrUtil.isEmpty(parentobjectNo))
        {   
        	cond.addWithAnd(new ConditionNormal(MappingList.parentobjectNo.name(),parentobjectNo));
        }
        if (!StringUtils.isEmpty(objectNo))
        {
        	cond.addWithAnd(new ConditionNormal(MappingList.objectNo.name(),objectNo,enumOperator.NoEqual));
        }
        if (!StrUtil.isEmpty(objectName))
        {   
        	cond.addWithAnd(new ConditionNormal(MappingList.objectName.name(),objectName));
        }
        List<SingleNetdisk> singlenetDisk = this.selectList(cond,null);
        if(singlenetDisk!=null){
        	return false;
        }
        return true;
    }
    /**
     * 根据对象编号查询文件，文件夹（删除时使用）
     */
    public SingleNetdisk selectParentObjectByNo(String objectNo)
    {
    	/**定义实体类*/
        EntityNetdisk entityNetdisk = new EntityNetdisk();
        entityNetdisk = dao.selectById(objectNo);
        if(entityNetdisk==null){
        	return null;
        }
        SingleNetdisk singleNetdisk = new SingleNetdisk();
        BeanUtils.copyProperties(entityNetdisk,singleNetdisk);
        return singleNetdisk;
    }
    /**
     * 批量删除文件
     */
    public int batchDelectObjects(List<EntityNetdisk> objectNos)
    {
        return dao.batchDelectObjects(objectNos);
    }
    /**
     * 根据父级编号倒叙查找父级以上父级编号及名称
     */
    public SingleNetdisk searchParentNo(String parentobjectNo) {
    	EntityNetdisk entityNetdisk = dao.searchParentNo(parentobjectNo);
    	SingleNetdisk singleNetdisk = new SingleNetdisk();
        BeanUtils.copyProperties(entityNetdisk,singleNetdisk);
        return singleNetdisk;
	}
	@Override
	public SingleNetdisk getNewSingleObj() {
		return new  SingleNetdisk();
	}
	@Override
	public EntityNetdisk getNewEntityObj() {
		return null;
	}

}