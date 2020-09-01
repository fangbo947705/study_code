package com.andrew.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**利用MD5进行加密
     * @param source  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
	 public static String getMD5(String source) {
	        StringBuilder sb = new StringBuilder();
	        MessageDigest md5;
	        try {  
	            md5 = MessageDigest.getInstance("MD5");
	            md5.update(source.getBytes());  
	            for (byte b : md5.digest()) {  
	                sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
	            }  
	            return sb.toString();  
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();  
	        }  
	        return null;  
	    }

	public static void main(String[] args) {
		System.out.println(getMD5("accountpwd20181109231202").equalsIgnoreCase("ec971c60092c2514826a3d64f53356e2"));
		System.out.println(getMD5("123456"));
	}
}
