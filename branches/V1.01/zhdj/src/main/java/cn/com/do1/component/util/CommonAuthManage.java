package cn.com.do1.component.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright ? 广州市道一信息技术有限公司 All rights reserved.
 * 参数摘要加密
 */
public class CommonAuthManage {
	private final static transient Logger log = LoggerFactory.getLogger(CommonAuthManage.class);
	public static String digest_hash = "qwdhop%k";

	/**
	 * 摘要验证
	 * 
	 * @param vo
	 * @param digest
	 * @param fields
	 * @return
	 */
	public static boolean authDigest(BaseEncryptionVO vo, String[] fields) {
		if (fields == null || fields.length == 0 || vo == null || vo.getDigest() == null)
			return false;
		boolean result = false;
		log.debug("要检验的加密摘要值:" + vo.getDigest());
		try {
			if (vo.getDigest().equals(genDigest(vo, fields)))
				result = true;
		} catch (Exception e) {
			log.error("检验出现错误"+e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 摘要验证
	 * 
	 * @param vo
	 * @param digest
	 * @param fields
	 * @return
	 */
	public static Object authDigestRetObj(BaseEncryptionVO vo, String[] fields) {
		if (fields == null || fields.length == 0 || vo == null || vo.getDigest() == null)
			return null;
		try {
			String requestDigest = vo.getDigest();
			String genDigest = genDigest(vo, fields);
			if (requestDigest.equals(genDigest))
					log.debug("检验成功");
					return decryptRequestObj(vo);
		} catch (Exception e) {
			log.error("检验出现错误"+e);
			e.printStackTrace();
			
		}
		return null;
	}

	/**
	 * 获取摘要加密值
	 * 
	 * @param vo
	 * @param fields
	 * @return
	 * @throws Exception 
	 */
	public static String genDigest(BaseEncryptionVO vo, String[] fields) throws Exception {
		String digest = "";
		StopWatch sw = new StopWatch();
		sw.start();
		if (fields != null && fields.length != 0 && vo != null) {
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(vo.getClass());
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				Method method = null;
				StringBuffer sb = new StringBuffer();
				Object object = null;
				for (int i = 0; i < fields.length; i++) {
					for (int j = 0; j < props.length; j++) {
						if (fields[i].equalsIgnoreCase(props[j].getName())) {
							method = props[j].getReadMethod();
							if (method != null) {
								object = method.invoke(vo);

								if (method.getReturnType().isArray()) {
									// 二维数组
									if (object != null && object.getClass().getName().toString().substring(0, 2).equals("[[")) {
										sb.append(object == null ? "0" : ((String[][]) object).length);
									}
									// 一维数组
									else {
										sb.append(object == null ? "0" : ((String[]) object).length);
									}
								} else {
									sb.append(object == null ? "" : SecurityDes3Util.decode(object.toString()));
								}
							}
						}
					}
				}
				//sb.append(ConfigMgr.get("system", "digestHash",digest_hash));
				digest = SecurityUtil.encryptToMD5(sb.toString().toLowerCase()).toLowerCase();
				log.debug(vo.getClass().getName() + "生成的摘要明文:" + sb.toString().toLowerCase());
				log.debug(vo.getClass().getName() + "生成的加密摘要:" + digest);
			} catch (IntrospectionException e) {
				log.error(e.getMessage());
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
			}
		}
		sw.stop();
		log.debug("加密摘要耗费的时间为" + sw.toString());
		return digest;
	}
	
	
	/**
	 * 将对象中加密的属性解密
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public static Object decryptRequestObj(Object obj) throws Exception {
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.getName().contains("serialVersionUID") || field.getName().contains("digest")) {
			} else {
				field.setAccessible(true);
				if(field.getType().isArray()){
					String[] array = (String[]) field.get(obj);
					if(array != null){
						for (int i = 0; i < array.length; i++) {
							array[i] = SecurityDes3Util.decode(array[i]);
						}
						field.set(obj, array);
					}
				}else{
					Object value = getFieldValueByName(field.getName(),obj);
					String val = value == null ? "" : SecurityDes3Util.decode(value.toString());
					field.set(obj,val);
				}
			}
		}
		if (obj.getClass().getSuperclass() != null)// 获取父类中的属性
		{
			for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
				if (field.getName().contains("serialVersionUID") || field.getName().contains("digest")) {

				} else {
					field.setAccessible(true);
					Object value = getFieldValueByName(field.getName(),obj);
					String val = value == null ? "" : SecurityDes3Util.decode(value.toString());
					field.set(obj,val);
				}
			}
		}
		return obj;
	}
	
	private static Object getFieldValueByName(String fieldName, Object o)   
	{      
	   try   
	   {      
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();      
	       String getter = "get" + firstLetter + fieldName.substring(1);      
	       Method method = o.getClass().getMethod(getter, new Class[] {});      
	       Object value = method.invoke(o, new Object[] {});      
	       return value;      
	   } catch (Exception e)   
	   {      
	       System.out.println("属性不存在");      
	       return null;      
	   }      
	} 
}
