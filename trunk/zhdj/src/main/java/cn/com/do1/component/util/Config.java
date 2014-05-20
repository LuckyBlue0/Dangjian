/**
 * 文件名：Config.java
 * 创建日期：2009-7-25
 * 版权： 广州市道一信息技术有限公司 2009
 */
package cn.com.do1.component.util;

import cn.com.do1.component.util.AbstractConfig;

/**
 * 类描述：
 * 统一支付组件配置类
 * @author 作者：曹春城
 * @version 版本：V0.1
 */
public class Config extends AbstractConfig{
	
	public static final String MPS_ACCOUNT_SPID = "mps.account.spid";
	public static final String MPS_ACCOUNT_SPPIN = "mps.account.sppin";
	public static final String MPS_ACCOUNT_SERVICEID = "mps.account.serviceid";
	
	private static Config instance;
	
	public synchronized static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	
	protected String getConfigId() {
		return "mpspay";
	}
	/**
	 * 小额支付订单状态编码描述
	 * @param code 编码
	 * @return 返回小额支付订单状态编码描述
	 */
	public String getMpsOrderSatusCodeDesc(String code){
		return instance.getProperty("mps.orderStatus.desc." + code);
	}
	/**
	 * DPay统一支付资源编码描述
	 * @param code 编码
	 * @return 返回DPay统一支付资源编码描述
	 */
	public String getDPayResourcesCodeDesc(String code){
		return instance.getProperty("dpay.resources.code." + code);
	}
	/**
	 * 获取DPay统一支付资源编码描述
	 * @param code 编码
	 * @param defaultValue 默认值
	 * @return 返回DPay统一支付资源编码描述
	 */
	public String getDPayResourcesCodeDesc(String code,String defaultValue){
		return instance.getProperty("dpay.resources.code." + code,defaultValue);
	}
	/**
	 * 获取MPS返回编码对应的描述
	 * <ul>code
	 *  <li>传入的编码，将自动的安一定的前缀进行转换，即 0 -> mps.resultCode.desc.0</li>
	 * </ul>
	 * @param code 编码
	 * @return 返回MPS返回编码对应的描述
	 */
	public String getMPSResultCodeDesc(String code){
		return instance.getProperty("mps.resultCode.desc." + code, "未知错误代码【"+code+"】");
	}
	/**
	 * 获取MPS返回编码对应的描述
	 * <ul>code
	 *  <li>传入的编码，将自动的安一定的前缀进行转换，即 0 -> mps.resultCode.desc.0</li>
	 * </ul>
	 * @param code 编码
	 * @param defaultValue 默认值
	 * @return 返回MPS返回编码对应的描述
	 */
	public String getMPSResultCodeDesc(String code,String defaultValue){
		return instance.getProperty("mps.resultCode.desc." + code, defaultValue);
	}
	
	/**获取小额支付的帐户相关信息
	 * @param code
	 * @param defaultValue
	 * @return
	 */
	public String getMPSAccountCodeDesc(String code, String defaultValue){
		return instance.getProperty("mps.accountCode." + code, defaultValue);
	}
	
	/**获取小额支付的帐户相关信息
	 * @param code
	 * @return
	 */
	public String getMPSAccountCodeDesc(String code){
		return instance.getProperty("mps.accountCode." + code);
	}
}
