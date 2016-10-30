package com.lanyuan.actionapi.commonmodule.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lanyuan.actionapi.PinYinUtils;
import com.lanyuan.actionapi.basic.baseclasses.ResultCommonSearchAction;
import com.lanyuan.assembly.dictionary.CollectionDictionary;
/**
 * 数据字典
 * 根据ID获取 字典数据接口
 * 
 * @author ly-one
 *
 */
public class ISelectDictionary extends ResultCommonSearchAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;// 字典ID
	
	public void getData()
	{
		if (this.id <= 0)
        {
            this.printString(null,0,"3","字典ID错误");
            return;
        }
		
        List<HashMap<String, String>> datalist=new ArrayList<HashMap<String,String>>();
        CollectionDictionary service = new CollectionDictionary(id);
        Map<String, String> map = service.getDictionary();

        if(null != map)
        {
            for (String key : map.keySet())
            {
            	LinkedHashMap<String, String> hash = new LinkedHashMap<String, String>();
            	hash.put("id", key);
                hash.put("text", map.get(key));
                hash.put("pinYin", this.removeSpecialChar(PinYinUtils.getPinYinHeadChar(map.get(key))));
                datalist.add(hash);
            }
        }
        else
        {
            printString(null,0,"2","没有数据");
            return;
        }
        printString(datalist,map.size(),"1",null);
	}
	
	private String removeSpecialChar(String str)
	{
		String regex = "[^a-z0-9A-Z]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	public static void main(String[] args) {
		String str = "mvkf%%*&()_   +%jj$$  jj";
		String regex = "[^a-z0-9A-Z]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		str = m.replaceAll("").trim();
		System.out.println(str);
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
