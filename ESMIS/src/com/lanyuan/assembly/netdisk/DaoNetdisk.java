package com.lanyuan.assembly.netdisk;

import java.util.List;

import com.lanyuan.assembly.common.DaoManager;

interface DaoNetdisk extends DaoManager<EntityNetdisk> {
    /**
     * 批量删除文件
     * 
     * @param listNos
     * @return
     */
    public int batchDelectObjects(List<EntityNetdisk> listNos);
    /**
     * 根据文件或者文件夹父级编号查询其父级的父级编号及名称
     */
    public EntityNetdisk  searchParentNo(String parentobjectNo);
}