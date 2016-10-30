package com.lanyuan.actionapi.sst.face;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.lanyuan.assembly.lawslibrary.ServiceLaws.MappingList;
import com.opensymphony.xwork2.Action;
/**
 * 查看企业  贯彻/适用 的法律法规接口
 * @author PBW
 *
 */
public class ISearchLawsAction extends ResultSearchAction{

	private static final long serialVersionUID = 1L;
	
	 @Autowired
	 private ServiceLaws lawsService;
	/**
	 * 查看类型
	 * case 1: 贯彻执行法律法规
	 * case 2: 适用法律法规
	 */
	 private String style;
	 
	 private String lawsName;
	 
	 
	/**
	 * 进入法律法规页面方法
	 */
	public String execute(){
		return Action.SUCCESS;
	}
	/**
	 * 企业适用的法律法规
	 * lawsType  1:贯彻执行,2.适用
	 *   
	 * @return
	 */
	public String laws_fit(){
		 // 调用父类方法,条件查询和排序
        ConditionGroup cond = this.getConditionGroupObject();
        OrderGroup order = this.getOrderGroupObject();
       
        if(lawsName != null && lawsName != ""){
            try
            {
                lawsName = URLDecoder.decode(lawsName,"UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        
        // 如果条件查询和排序对象为空,实例化对象
        if (cond == null)
        {
            cond = new ConditionGroup();
            if (lawsName !=null && !lawsName.isEmpty()){
            	cond.addWithAnd(new ConditionNormal(MappingList.standardName.name(), lawsName, enumOperator.Like));
            }
            if (style !=null && !style.isEmpty()){
                cond.addWithAnd(new ConditionNormal(MappingList.standardCategory.name(), style));
            }
        }
        if (order == null)
        {
            order = new OrderGroup();
            // 按法规id排序
            order.Add(MappingList.standardId.name(), true);
        }
        int recordCount = lawsService.getTotalCount(cond);
        
        if (recordCount <= 0)
        {
            printString(null, 0, "2", "暂无数据！");
            return Action.SUCCESS;
        }
        
        List<SingleLaws> rtnList = null;
        if (this.isBreakPage())
        {
            rtnList = lawsService.selectPageList(getCurrentPage(),getLinesOfPage(), cond, order);
        }
        else
        {
            rtnList = lawsService.selectList(cond, order);
        }
        
        List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        for (SingleLaws single : rtnList)
        {
            HashMap<String, String> returnData = new HashMap<String, String>();
            returnData.put(MappingList.standardId.name(), single.getStandardId());
            returnData.put(MappingList.standardNo.name(), single.getStandardNo());
            returnData.put(MappingList.standardName.name(), single.getStandardName());
            returnData.put(MappingList.browseVolume.name(), single.getBrowseVolume()+ "");
            returnData.put(MappingList.filePath.name(), single.getFilePath());
            returnData.put(MappingList.createTime.name(), single.getCreateTime());
            datalist.add(returnData);
        }
        printString(datalist, recordCount, "1", null);
		return Action.SUCCESS;
	}
	/**
	 * 企业贯彻执行的法律法规
	 *  lawsType  1:贯彻执行,2.适用
	 * @return
	 */
	public String laws_carry(){
		
		
		
		
		return Action.SUCCESS;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getLawsName() {
		return lawsName;
	}
	public void setLawsName(String lawsName) {
		this.lawsName = lawsName;
	}
	
	
}
