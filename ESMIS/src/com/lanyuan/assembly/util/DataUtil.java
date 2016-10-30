package com.lanyuan.assembly.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.springframework.transaction.interceptor.TransactionAspectSupport;


public class DataUtil {

	/**
	 * 获取固定前缀id
	 * @param id 传进来的最大id
	 * @param prefix 固定前缀
	 * @param length id长度
	 * @return 最大id
	 */
	public static String createNewIdByFixPrefix(String id,String prefix,int length){
		int lastId = 1;
		if(id!=null){
			String sLast = id.substring(prefix.length());
			lastId = Integer.parseInt(sLast)+1;
		}
		return prefix+String.format("%0"+(length-prefix.length())+"d", lastId);
	}
	
	/**
	 * 手动执行回滚
	 */
	public static void DataRollBack(){
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	
	public static void main(String[] args) {
		System.out.println(createNewIdByFixPrefix("F201604010001", "F20160401", 13));
	}
    
	/**
	 * 将double格式化为两位小数
	 * @param money double类型小数
	 * @return 两位小数
	 */
    public static String formatMoney(Double money)
    {
            NumberFormat nf = new DecimalFormat("######0.00");
            return nf.format(money);
    }
}
