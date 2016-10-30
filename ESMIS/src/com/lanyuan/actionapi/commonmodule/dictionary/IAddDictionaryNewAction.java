package com.lanyuan.actionapi.commonmodule.dictionary;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.database.ConnException;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.dictionary.ServiceDictionaryData;
import com.lanyuan.assembly.dictionary.SingleDictionaryData;
import com.opensymphony.xwork2.Action;

public class IAddDictionaryNewAction extends ResultOperateAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 等级名称
	 */
	private Integer dictionaryid;
	private String content;
	private String customfield1;
	private Integer showNum;
	private String serviceClass;

	private String code;
	private Integer fid;
	ServiceDictionaryData service = new ServiceDictionaryData();
	SingleDictionaryData data = new SingleDictionaryData();

	public String  add() {
		if (dictionaryid == null) {
			this.printString("2", "添加失败！");
			return null;
		}
	    ConditionGroup cond = new ConditionGroup();
		if (!StringUtils.isBlank(dictionaryid + "")) {
			cond.addWithAnd(new ConditionNormal(
					ServiceDictionaryData.MappingList.dictionaryid.name(),
					dictionaryid + ""));
		}
		if (!StringUtils.isBlank(code)) {
			cond.addWithAnd(new ConditionNormal(
					ServiceDictionaryData.MappingList.code.name(), code));
		}
		List<SingleDictionaryData> selectByCondition = service
				.selectByCondition(cond, null);
		if (selectByCondition!=null&&!selectByCondition.isEmpty()) {
			this.printString("2", "关键值存在,请重新输入");
			return null;
		}
		data.setDictionaryid(dictionaryid);
		data.setContent(content);
		data.setShowNum(showNum);
		data.setServiceClass(serviceClass);

		data.setCustomfield1("1");
		data.setCode(code);
		data.setFid(dictionaryid);

		boolean flag = false;
		try {
			flag = service.add(data);
		} catch (ConnException e) {
			e.printStackTrace();
		}
		if (flag) {
			this.printString("1", "添加成功！");
		} else {
			this.printString("2", "添加失败！");
		}
		return null;
	}

	public void checkCode() {
		ConditionGroup cond = new ConditionGroup();
		if (!StringUtils.isBlank(dictionaryid + "")) {
			cond.addWithAnd(new ConditionNormal(
					ServiceDictionaryData.MappingList.dictionaryid.name(),
					dictionaryid + ""));
		}
		if (!StringUtils.isBlank(code)) {
			cond.addWithAnd(new ConditionNormal(
					ServiceDictionaryData.MappingList.code.name(), code));
		}
		List<SingleDictionaryData> selectByCondition = service
				.selectByCondition(cond, null);
		if (selectByCondition!=null&&!selectByCondition.isEmpty()) {
			this.printString("1", "关键值存在,请重新输入");
			return;
		}else
		{
			this.printString("0", "关键值不存在");
			return;
		}
	}

	public Integer getDictionaryid() {
		return dictionaryid;
	}

	public void setDictionaryid(Integer dictionaryid) {
		this.dictionaryid = dictionaryid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustomfield1() {
		return customfield1;
	}

	public void setCustomfield1(String customfield1) {
		this.customfield1 = customfield1;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

}
