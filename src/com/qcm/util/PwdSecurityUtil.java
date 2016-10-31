package com.qcm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class PwdSecurityUtil {
	
	/**
	 * MD5加密
	 * @param str 要加密的字符串
	 * @param count 加密循环次数
	 * @return 加密结果
	 */
	public static String transMd5(String str,int count){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
		int num = 0;
		for(int k=0;k<count;k++){
			try {
				// 获取MessageDigest的实例
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 更新摘要
				// System.out.println("****************test********************");
				// System.out.println(str.getBytes().toString());
				md.update(str.getBytes());
				
				// 获得密文
				byte[] b = md.digest();
				// 把密文转换成十六进制的字符串形式
				int a = 0;
				char str1[] = new char[b.length * 2];
				for(byte byte0 : b){
	                str1[a++] = hexDigits[byte0 >>> 4 & 0xf];
	                str1[a++] = hexDigits[byte0 & 0xf];
				}
				str = new String(str1);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			num++;
			// 加密count次跳出
			if(num==count){
				break;
			}
			// 递归
			str = transMd5(str,1);
		}
		return str;
	}
	
	public static void main(String[] args) {
		String code = "123";
		System.out.println(transMd5(code,3));
	}
}
