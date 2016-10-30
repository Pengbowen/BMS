package com.lanyuan.actionapi.sst.mobile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.sst.standard.ServiceSSTStandardList;
import com.lanyuan.assembly.sst.standard.SingleSSTStandardList;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary.MappingList;
import com.lanyuan.assembly.util.LawAndStandardUtil;
/**
 * 移动端获取标准列表接口
 * @author Administrator
 *
 */
public class IGetStandardList extends ResultSearchAction {
	
	private static final long serialVersionUID = 1L;
    @Autowired
	 private ServiceSSTStandardList standardService;
    
    private Integer SSTId;
    private String layerItemId;
    private String subClassId;
	
	public String execute(){

		ConditionGroup cond = new ConditionGroup();
		OrderGroup order = new  OrderGroup();
		 // 按法规id排序
        order.Add(MappingList.standardId.name(), true);
       // 如果条件查询和排序对象为空,实例化对象
			//转码
           if(SSTId !=null ){
           	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.SSTId.name(), SSTId+"", enumOperator.Equal));
           }
           if(layerItemId !=null && !layerItemId.isEmpty()){
           	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.layerItemId.name(), layerItemId, enumOperator.Equal));
           }
           if(subClassId !=null && !subClassId.isEmpty()){
           	cond.addWithAnd(new ConditionNormal(ServiceSSTStandardList.MappingList.subClassId.name(), subClassId, enumOperator.Equal));
           }
          
       int recordCount = standardService.getTotalCount(cond);
       if (recordCount <= 0)
       {
           printString(null, 0, "2", "没有数据！");
           return this.SUCCESS;
       }
       List<SingleSSTStandardList> rtnList = null;
       if (this.isBreakPage())
       {
           rtnList = standardService.selectPageList(getCurrentPage(),getLinesOfPage(), cond, order);
       }
       else
       {
           rtnList = standardService.selectList(cond, order);
       }
       
       List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
       for (SingleSSTStandardList single : rtnList)
       {
           HashMap<String, String> returnData = new HashMap<String, String>();
          //判断是否存在
         /*  接口调整。    关于列表查询的接口返回参数增加:
        	   fileExists     0不存在 、1存在epub文件  2、存在pfd文件。

        	   文件是否存在优先判断epub文件，不存在时再判断是否有pdf文件。

        	   判断方法
        	    File file = new File(filePath);
        	   file.exists()   
        	   true 文件存在  false 文件不存在*/
           String fileExists = "0";
         String epubPath =   LawAndStandardUtil.getPath(single.getStandardId(), "epub");
         String pdfPath = LawAndStandardUtil.getPath(single.getStandardId(), "pdf");
         File e_file = new File(epubPath);
         File p_file = new File(pdfPath);
         if(e_file.exists()){
        	 fileExists="1";
         }else if(p_file.exists()){
        	 fileExists="2";
         }
           //将文件是否存在添加入返回数据  0-不存在,1-存在epub文件 2- 存在pdf文件
           returnData.put("fileExists", fileExists);
           returnData.put(MappingList.standardNo.name(), single.getStandardNo());//标准编号
           returnData.put(MappingList.standardId.name(), single.getStandardId());//标准Id
           returnData.put(MappingList.standardName.name(), single.getStandardName());//标准名称
           returnData.put(MappingList.standardCategory.name(), single.getStandardCategory());//标准类别
           String words = "无";
           if(single.getSubjectWords()!=null){
        	   words = single.getSubjectWords();
           }
           returnData.put(MappingList.subjectWords.name(), words);
           
          //标准有效状态 有效为1,作废为0
           Integer state = single.getEffectiveState();
           if(state==null){
           	state=0;
           }
           returnData.put(MappingList.effectiveState.name(), state+"");
           
           String sstLocation = single.getSSTLoaction();
           if(sstLocation==null){
           	sstLocation="暂无";
           }
           returnData.put(MappingList.SSTLoaction.name(), sstLocation);//体系表位置
           datalist.add(returnData);
       }
       this.printString(datalist, recordCount, "1", null);
       return this.SUCCESS;
	}
}
