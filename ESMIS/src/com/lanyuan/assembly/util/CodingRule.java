package com.lanyuan.assembly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 编码规则
 *
 * @author yt
 * @date 2016-4-27 下午7:22:41
 */
public class CodingRule {
	/**
     * 订单明细ID
     * 编码规则是：年月日(6)+五位数字(4)
     * @return BillId
     */
	public static String getBillId(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String prefix = format.format(new Date());
        return prefix+String.format("%06d", (int)(Math.random()*10000));
	}
	
	/**
     * 获取订单编号
     * 编码规则是：业务编号+门店编号(4)+年月日(6)+六位随机数字(6) 如：010001160427139722
     * @return OrderNo
     */
	public static String getOrderNoByStoreNo(String StoreNo)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String prefix = String.format("%02d", 10)+String.format("%04d", Integer.parseInt(StoreNo))+format.format(new Date());
		return prefix+String.format("%06d", (int)(Math.random()*1000000));
    }
}
