package cn.com.do1.component.util;
import java.security.Key;  

import javax.crypto.Cipher;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESedeKeySpec;  
import javax.crypto.spec.IvParameterSpec; 

import cn.com.do1.dqdp.core.ConfigMgr;
/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class SecurityDes3Util {
    // 密钥  
    private final static String secretKey = "890oiujkioj@lx100$#365#$";  
    // 向量  
    private final static String iv = "01234567";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";  
    
    public static void main(String[] args) {
		try {
			//http://183.63.138.179:2013/zhdj/interface/userinfo/userinfo!login.action?requestJson=
			//{"platformType":"KaGqR43k6cQ=","username":"O\/WA2T4oVEw=","userPwd":"ldl4iddbDY4=","digest":"78ac926717b009f11bc58a165ccfeefa"}
			//0
			//940e56cd-7623-4e27-bced-a24bd9fb4112
			//e131eed0-ab8f-4805-9bb3-ac477c8a1a1f
			//1
			//你好
			String text = "bf390235-bff7-4663-9e34-0adfe088a20c";
			System.out.println("明文=="+text);
			System.out.println("密文："+encode(text));
			System.out.println(SecurityUtil.encryptToMD5("b34fe976-40f5-47e2-9f79-0f98fc7322abbf390235-bff7-4663-9e34-0adfe088a20c").toLowerCase());
//			51596103f385101ce16bcc58b3c3b85b
//			System.out.println("解密后的明文："+decode(encode(text)));
//			System.out.println(decode("NPvPS7573X6o/VAQcaw9mgJZjqdSVsmyGh2DsHQIlJ2wkLXBg7dZAw=="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 
     * 3DES加密 
     *  
     * @param plainText 普通文本 
     * @return 
     * @throws Exception  
     */  
    public static String encode(String plainText) throws Exception { 
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(ConfigMgr.get("system", "secretKey",secretKey).getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
        return Base64.encode(encryptData);  
    }  
  
    /** 
     * 3DES解密 
     *  
     * @param encryptText 加密文本 
     * @return 
     * @throws Exception 
     */  
    public static String decode(String encryptText) throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(ConfigMgr.get("system", "secretKey",secretKey).getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        return new String(decryptData, encoding);  
    }  
}
