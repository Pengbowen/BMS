package com.lanyuan.assembly.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.mail.util.BASE64EncoderStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * 功能描述 
 * 加密常用类 
 */  
public class EncryptUtil {  
    // 密钥是16位长度的byte[]进行Base64转换后得到的字符串  
    public static String constant = "LmMGStGtOpF4xNyvYt54EQ==";  
    /** 
     * <li> 
     * 方法名称:encrypt</li> <li> 
     * 加密方法 
     * @param xmlStr 
     *            需要加密的消息字符串 
     * @return Map<"str":加密后的字符串,"key":秘钥>
     */  
    public static Map<String,String> encrypt(String xmlStr) { 
    	Map<String,String> map = new HashMap<String,String>();
    	//取得5为随机字符串作为key
    	String keyStr = getCharAndNumr(5);
    	map.put("key", keyStr);
    	xmlStr="lysoft"+xmlStr+keyStr;
    	byte[] b = xmlStr.getBytes();
    	if(b!=null){
    		String s = new BASE64Encoder().encode(b);
    		 map.put("str", s);
    	}
    		
       
        return map;
    }  
  
    /** 
     * <li> 
     * 方法名称:encrypt</li> <li> 
     * 功能描述: 
     *  
     * <pre> 
     * 解密方法 
     * </pre> 
     *  
     * </li> 
     *  
     * @param xmlStr 
     *            需要解密的消息字符串 
     * @return 解密后的字符串 ,若keyStr不符,则返回null
     * @throws Exception 
     */  
    public static String decrypt(String xmlStr,String keyStr) throws Exception {
    	BASE64Decoder decoder = new BASE64Decoder();
    	byte[] b = null;
    	b = decoder.decodeBuffer(xmlStr);
    	String str = new String(b,"utf-8");
        int length = str.length();
        String sub = str.substring(length-5);
        if(sub.equals(keyStr)){
        	int start = 6;
        	String data = str.substring(start,length-5);
        	return data;
        }else{
        	return null;
        }
        // 返回解密后的数组，其中前16位MD5Hash码要除去。  
       
    }  
//    public static void main(String[] args) throws Exception {  
//    	Map<String,String> map = encrypt("uploadfiles/c.txt");
//    	System.out.println(map.get("str"));
//    	System.out.println(map.get("key"));
//       String s = decrypt("bHlzb2Z0dXBsb2FkZmlsZXMvYy50eHRTcUc2cQ==", "SqG6q");
//        System.out.println(s);
//    } 
    /**
     * java生成随机数字和字母组合
     * @param length[生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
     String val = "";
     Random random = new Random();
     for (int i = 0; i < length; i++) {
      // 输出字母还是数字
      String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
      // 字符串
      if ("char".equalsIgnoreCase(charOrNum)) {
       // 取得大写字母还是小写字母
       int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
       val += (char) (choice + random.nextInt(26));
      } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
       val += String.valueOf(random.nextInt(10));
      }
     }
     return val;
    }
}  
