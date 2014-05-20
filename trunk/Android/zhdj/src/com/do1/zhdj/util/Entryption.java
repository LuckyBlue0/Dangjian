package com.do1.zhdj.util;

import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.json.JSONObject;

import cn.com.do1.component.util.Log;

import com.do1.zhdj.widght.Base64;

/**
 * 获取密文
 * auth:YanFangqin
 * data:2013-4-17
 * thzhd
 */
public class Entryption {

	public static String secretKey = "Do1_Info_thParty";
	public static String iv = "01234567";
	public final static String encoding = "utf-8";
	
	/**
	 * 将参数转换成密文
	 * @param listMap
	 * @return
	 */
	public static String getJsonKey(Map<String, Object> map){
		JSONObject json = new JSONObject(map);
		try {
			return encode(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将参数转换成密文
	 * @param listMap
	 * @return
	 */
	public static String getJsonKey2(Map<String, String> map){
		JSONObject json = new JSONObject(map);
		return json.toString();
	}
	
	
	/**
	 * 加密
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) throws Exception {
		int lenKey = 24 - secretKey.getBytes(encoding).length;
		StringBuffer sbKey = new StringBuffer(secretKey);
		if(lenKey > 0){
			for (int i = 0; i < lenKey; i++) {
				sbKey.append("a");
			}
			secretKey = sbKey.toString();
		}else{
			secretKey =  secretKey.substring(0, 24);
		}
		
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes(encoding));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		SecretKey deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		
		int lenIv = 8 - iv.getBytes(encoding).length;
		StringBuffer sbIv = new StringBuffer(iv);
		if(lenIv > 0){
			for (int i = 0; i < lenIv; i++) {
				sbIv.append("a");
			}
			iv = sbIv.toString();
		}else{
			iv =  iv.substring(0, 8);
		}
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}
	
	/**
	 * 解密
	 * @param encryptText
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) throws Exception {
		SecretKey deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes(encoding));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		
		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
		if(cipher == null){
			Log.e("============================解密为空===========================");
		}

		return new String(decryptData, encoding);
	}
}
