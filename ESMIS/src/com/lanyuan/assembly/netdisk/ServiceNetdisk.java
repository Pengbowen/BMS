package com.lanyuan.assembly.netdisk;
import java.util.List;

import com.lanyuan.assembly.common.Services;

public interface ServiceNetdisk extends Services<SingleNetdisk>
{
    /**新增文件，文件夹**/
    public int addParentObject(SingleNetdisk singleNetdisk);
    /**修改文件，文件夹**/
    public int modifyParentObject(SingleNetdisk singleNetdisk);
    /**判断文件，文件夹夹名称是否唯一**/
    public boolean isOnlyParentObjectByName(String objectNo,String parentobjectNo,String objectName);
    /**根据对象编号查询文件，文件夹，包括查看详情**/
    public SingleNetdisk selectParentObjectByNo(String objectNo);
    /**批量删除文件**/
    public int batchDelectObjects(List<EntityNetdisk> objectNos);
    /****/
    public int deleteById(String id);
    /**根据父级目录编号求其上一层父级目录编号及名称*/
    public SingleNetdisk searchParentNo(String parentobjectNo);
    
    public enum MappingList
    {
        /**
         * 对象编号
         */
        objectNo(" objectNo ") ,
        /**
         * 所属目录
         */
        parentobjectNo(" parentobjectNo ") ,
        /**
         * 包含子项目数量
         */
        chirldItemCount(" chirldItemCount ") ,
        /**
         * 对象名称
         */
        objectName(" objectName ") ,
        /**
         * 对象类别
         */
        objectType(" objectType ") ,
        /**
         * 文件后缀
         */
        objectSuffix(" objectSuffix ") ,
        /**
         * 存放位置
         */
        objectSavePath(" objectSavePath ") ,
        /**
         * 关键字
         */
        keyWords(" keyWords ") ,
        /**
         * 说明
         */
        explains(" explains ") ,
        /**
         * 备注
         */
        remark(" remark ") ,
        /**
         * 最后修改人
         */
        modifyer(" modifyer ") ,
        /**
         * 最后修改人姓名
         */
        modifyerName(" modifyerName ") ,
        /**
         * 最后修改时间
         */
        modifyTime(" modifyTime ") ,
        /**
         * 最后修改IP
         */
        modifyIP(" modifyIP ") ;
        private String value;

        MappingList(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return this.value;
        }
    }

    
}

