package com.lanyuan.actionapi;
/**
 * 常量类
 * 数据字典
 * 权限
 * 角色类型
 *
 */
public class ConstWebData 
{
	
	/**
	 * 酒店的单位类型
	 */
	public final static Integer H_CATEGORY = 1;
	/**
	 * 布草送货单列表接口路径
	 */
	public final static String BILL_DELIVERY = "linenbusiness/deliverySearch.action";
	/**
	 * 布草收货单列表接口路径
	 */
	public final static String BILL_RECEIPT = "linenbusiness/receiptSearch.action";
	/**
	 * 布草报损单列表接口路径
	 */
	public final static String BILL_LOSS = "linenbusiness/lossSearch.action";
	/**
     * 数据字典枚举常量集合
     */
    public static enum Dictionary
    {   
    	/*** 衣物品牌*/
    	ClothesBrand(1000,"衣物品牌"),
    	/**衣物颜色*/
    	ClothesColor(1001,"衣物颜色"),
    	/**衣物瑕疵*/
    	ClothesFault(1002,"衣物瑕疵"),
    	/**洗衣档次**/
    	ClothesLevel(1003,"洗衣档次"),
    	/**快慢服务*/
    	ServiceSpeed(1004,"快慢服务"),
    	/**衣物附件*/
    	Attanchment(1005,"衣物附件"),
    	/**协议单位**/
    	Hotel(1010,"协议单位");
    	
        private int key;
        private String name;

        Dictionary(int key, String name)
        {
            this.key = key;
            this.name = name;
        }

        public int getKey()
        {
            return key;
        }
        
        public String getName()
        {
            return name;
        }
    }
    
    /**
     * 权限
     */
    public static enum EnumLimit
    {
    	Goto("LPMS.GuestLaundry.Goto","去渍"),
    	DWashing("LPMS.GuestLaundry.DWashing","干洗"),
    	WWashing("LPMS.GuestLaundry.WWashing","水洗"),
    	Ironing("LPMS.GuestLaundry.Ironing","熨烫"),
    	QualityCheck("LPMS.GuestLaundry.Qcheck","质检"),
    	Inventory("LPMS.GuestLaundry.Inventory","出厂");
    	
    	String limitId;
    	String value;
    	
    	EnumLimit(String key,String value)
    	{
    		this.limitId=key;
    		this.value=value;
    	}

		public String getLimitId() {
			return limitId;
		}

		public String getValue() {
			return value;
		}
    }
    
    /**
     *  角色类型
     */
    public static enum RoleType
    {
    	Administrator("1","管理员"),
    	Employee("2","员工");
    	
    	String key;
    	String value;
    	
    	RoleType(String key,String value)
    	{
    		this.key=key;
    		this.value=value;
    	}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
    }
    
}
