package com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver;

import java.util.ArrayList;
import java.util.List;

import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumDelimiter;
/**
 * 排序实体类
 * @author qinye
 * 
 */
public class OrderGroup {
	private List<String> mapOrder;
	
	public OrderGroup(){
		this.mapOrder = new ArrayList<String>();
	}
	
	/**
	 * 增加排序方式
	 * @param mappingId 映射id
	 * @param order 排序方式,true代表升序,false代表降序
	 */
	public void Add(String mappingId,boolean order){
		String item = mappingId;
		item += getOrderSplitChar();
		if(order){
		    item += ConditionGroup.enumOrder.Asc.getValue();
		}else{
		    item += ConditionGroup.enumOrder.Desc.getValue();
		}
		this.mapOrder.add(item);	
		
	}	
	
	/**
	 * 增加排序方式
	 * @param setId 集合id
	 * @param mappingId 映射id
	 * @param order 排序方式,true代表升序,false代表降序
	 */
	public void Add(String setId,String mappingId,boolean order){
		String item = ("".equals(setId)?"":mappingId + enumDelimiter.TableField.getValue()) + mappingId;
        item += getOrderSplitChar();
        if(order){
            item += ConditionGroup.enumOrder.Asc.getValue();
        }else{
            item += ConditionGroup.enumOrder.Desc.getValue();
        }
        this.mapOrder.add(item);
	}
	
	public static String getOrderSplitChar()
	{
	    return "||";
	}
	
	/**
	 * 清除所有排序条件
	 */
	public void Clear(){
		this.mapOrder.clear();
	}
	
	public List<String> getOrderList(){
		return this.mapOrder;
	}

	@Override
	public String toString() {
		return "EntityOrder [mapOrder=" + mapOrder.toString() + "]";
	}	
}
