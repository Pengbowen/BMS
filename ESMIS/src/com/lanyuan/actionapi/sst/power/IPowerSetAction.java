package com.lanyuan.actionapi.sst.power;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.actionapi.basic.baseclasses.mybatis.ResultSearchAction;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.power.ServiceSSTPower;
import com.lanyuan.assembly.sst.power.SingleSSTPower;
import com.lanyuan.assembly.sst.subclassify.ServiceSSTSubClassify;
import com.lanyuan.assembly.sst.subclassify.SingleSSTSubClassify;
import com.lanyuan.web.LoginAuthentication.LoginUser;

public class IPowerSetAction extends ResultSearchAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ServiceSSTLayerItems layerItemService;
	@Autowired
	private ServiceSSTSubClassify serviceSubClassify;
	@Autowired
	private ServiceSSTPower powerService;
	
	private Integer SSTId;
	
	private String userId;
	public String execute(){
		return this.SUCCESS;
	}
	/**
	 * 加载数据方法
	 */
	public void loadData(){
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String,String>>();
		//获取所有的层项目
		List<SingleSSTLayerItems> layerItems = layerItemService.selectBySSTId(SSTId);
		//获取所有的子分类
		List<SingleSSTSubClassify> subClassifys = serviceSubClassify.selectBySSTId(SSTId);
		
		HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null){
            	printString(null,0,"-4", "非法访问！");
            	return;
            	}
        }
        else
        {
            user = (LoginUser) request.getSession().getAttribute(LoginUser.SESSIONID);
        }
        userId = user.getUnitNo();
		//根据体系表Id和用户id查找用户的所有权限
		List<SingleSSTPower> powerList = powerService.getPower(userId, SSTId);
		List<String> powers = new ArrayList<String>();
		if(powerList!=null && !powerList.isEmpty()){
		for(SingleSSTPower power:powerList){
			String powerId = power.getPowerId();
			powers.add(powerId);
		}
		}
		if(layerItems !=null && !layerItems.isEmpty()){
			for(SingleSSTLayerItems s:layerItems){
			HashMap<String,String> map= new HashMap<String,String>();
			map.put("id", s.getLayerItemId());
			map.put("name",s.getLayerItemName());
			map.put("SSTId",SSTId.toString());
			String layerNo = s.getLayerNo().toString();
			map.put("layerNo", layerNo);
			if(powers.contains(s.getLayerItemId())){
				map.put("state", "1");//如果权限集合里包含,则状态为1,表示有 
			}else{
				map.put("state", "0");//如果权限集合里不包含,则状态为0,表示无
			}
			
			datalist.add(map);
			}
		}
		if(subClassifys !=null && !subClassifys.isEmpty()){
			for(SingleSSTSubClassify s:subClassifys){
				HashMap<String,String> map= new HashMap<String,String>();
				map.put("id", s.getSubClassId());
				map.put("name",s.getSubClassName());
				map.put("SSTId",SSTId.toString());
				String layerNo = s.getLayerNo().toString();
				map.put("layerNo", layerNo);
				if(powers.contains(s.getSubClassId())){
					map.put("state", "1");//如果权限集合里包含,则状态为1,表示有 
				}else{
					map.put("state", "0");//如果权限集合里不包含,则状态为0,表示无
				}
				datalist.add(map);	
			}
			
		}
		//按照层级进行升序排序
		Collections.sort(datalist,new Comparator<HashMap<String,String>>(){
			
			 /*  
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，  
             * 返回负数表示：o1 小于o2，  
             * 返回0 表示：o1和o2相等，  
             * 返回正数表示：o1大于o2。  
             */  
			public int compare(HashMap<String, String> arg0,
					HashMap<String, String> arg1) {
				int a = Integer.parseInt(arg0.get("layerNo"));
				int b = Integer.parseInt(arg1.get("layerNo"));
				if(a>b){
					return 1;
				}
				if(a<b){
					return -1;
				}
				return 0;
			}
		});
		printString(datalist, datalist.size(), "1", "加载数据成功！");
	}
	
	public Integer getSSTId() {
		return SSTId;
	}
	public void setSSTId(Integer sSTId) {
		SSTId = sSTId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
