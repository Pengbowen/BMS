package com.lanyuan.actionapi.commonmodule.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup;
import com.lanyuan.assembly.commonmodule.unit.ServiceUnit;
import com.lanyuan.assembly.commonmodule.unit.SingleUnit;
import com.opensymphony.xwork2.ActionSupport;

public class LoadUnitMap extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// 新增人员时加载所有部门信息
	public void loadUnitMap() {
		ServiceUnit service = new ServiceUnit();

		OrderGroup order = new OrderGroup();
		order.Add(ServiceUnit.MappingList.unitNo.name(), true);

		int countTotal = service.getTotalCount(null);
		if (countTotal <= 0) {
			return;
		}

		List<SingleUnit> listData = service.selectByCondition(null, order);
		if (listData != null && !listData.isEmpty()) {
			List<HashMap<String, Object>> datalist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> hash = null;
			for (SingleUnit cls : listData) {
					hash = new HashMap<String, Object>();
					hash.put("id", cls.getUnitNo());
					hash.put("text",cls.getUnitName());
					datalist.add(hash);
			}

			JSONArray jsonArr = JSONArray.fromObject(datalist);
			HttpServletResponse response =ServletActionContext.getResponse();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(jsonArr.toString());
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
		}
		
	}
	
}
