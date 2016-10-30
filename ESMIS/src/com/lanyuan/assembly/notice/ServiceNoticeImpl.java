package com.lanyuan.assembly.notice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.common.ServersManager;

@Service("noticeService")
public class ServiceNoticeImpl extends ServersManager<SingleNotice, EntityNotice, DaoNotice> implements ServiceNotice
{

    @Autowired
    public void setDao(DaoNotice dao)
    {
        this.dao =dao;
    }    
    
    /**
     * 添加通知公告
     * 
     * @param single
     * @param operator
     * @return 操作数据库行数
     */
    public int add(SingleNoticeToOperate single,OperatorInfo operator)
    {   
        EntityNotice en = new EntityNotice();
        en = SingleToEntity(single);
        en.setPublisher(operator.getOperator());
        en.setPublisherrName(operator.getOperatorName());
        en.setPublishIP(operator.getOperateIp());
        en.setPublishTime(operator.getOperateTime("yyyy-MM-dd HH:mm:ss"));
        return dao.insert(en);
    }
    
    /**
     * 修改通知公告
     * 
     * @param single
     * @param operator
     * @param id
     * @return 操作数据库行数
     */
    public int update(SingleNoticeToOperate single,OperatorInfo operator,Integer id)
    {
        EntityNotice en = new EntityNotice();
        en = SingleToEntity(single);
        en.setId(id);
        if(operator != null){
            en.setModifyer(operator.getOperator());
            en.setModifyerName(operator.getOperatorName());
            en.setModifyIP(operator.getOperateIp());
            en.setModifyTime(operator.getOperateTime("yyyy-MM-dd HH:mm:ss"));
        }
        return dao.update(en);
    }
    
    /**
     * SingleToEntity
     * 
     * @param single
     * @return EntityNotice
     */
    private EntityNotice SingleToEntity(SingleNoticeToOperate single)
    {
        EntityNotice en = new EntityNotice();
        BeanUtils.copyProperties(single, en);
        return en;
    }
    
    @Override
    public SingleNotice getNewSingleObj()
    {
        return new SingleNotice();
    }

    @Override
    public EntityNotice getNewEntityObj()
    {
        return new EntityNotice();
    }

    
}
