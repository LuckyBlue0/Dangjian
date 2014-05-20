package cn.com.do1.component.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置参数管理类的抽象父类.
 * 
 * <p>
 * 程序中,可配置的参数通常是这样管理的:
 * <blockquote>
 * 	<ul>
 * 		<li>把配置参数及其默认值存储于一个properties(xml)文件中</li>
 * 		<li>建立一个配置参数管理类,提供接口方法取得这些配置参数的值</li>
 * 		<li>程序运行前,允许使用者修改properties(xml)文件,为参数指定新的值;
 *          或不允许使用者修改此properties(xml)文件,但提供其它方式让其能覆盖参数的默认值.</li>
 * 	</ul>
 * </blockquote>
 * </p>
 * 
 * <p>如果你的配置参数需要用上面描述的方式来管理,可以让你的配置参数管理类继承此类, 并提供一个ID(用于唯一标识你的配置参数管理类), 则你的配置参数管理类默认地具备以下行为:
 * <blockquote>
 * 	<ul>
 * 		<li>从类路径下的文件:/META-INF/config/{ID}.xml加载配置参数及其默认值.(如果此文件不存在,则加载类路径下的文件/META-INF/config/{ID}.properties)</li>
 * 		<li>从另一文件(下列三个可能文件列表中第一个存在的文件)加载配置参数及其配置值, 
 *          配置参数的配置值对应此文件中配置参数"{ID}.配置参数"的值
 * 			<ol>
 * 				<li>系统属性<code>config.custom.file</code>指定的xml或properties文件(路径相对于文件系统).</li>
 * 				<li>类路径下的文件:/META-INF/config/custom.xml</li>
 * 				<li>类路径下的文件:/META-INF/config/custom.properties</li>
 * 				<li>类路径下的文件:/custom.xml</li>
 * 				<li>类路径下的文件:/custom.properties</li>
 * 			</ol>
 * 		</li>
 * 		<li>配置参数如果存在配置值, 其配置值会覆盖其默认值.</li>
 * 		<li>允许配置参数的值通过方式"${另一配置参数}"引用同一配置文件内其他配置参数的值.</li>
 * 	</ul>
 * <p>定义的properties文件和xml文件必须符合{@link Properties}定义的文件格式. XML文档的话必须具有以下 DOCTYPE 声明:{@literal <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">}</p>
 * </blockquote>
 * </p>
 * 
 * <p>
 * 你的配置参数管理类(继承此类)的示例实现如下:
 * <blockquote><pre>
import cn.com.do1.dcore.config.AbstractConfig;

public class Config extends AbstractConfig {
	private static Config instance;
	//配置ID
	public static final String CONFIG_ID = "test";
	private Config(){}
	public static synchronized Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	{@literal @Override}
	protected String getConfigId() {
		return CONFIG_ID;
	}
}
 * </pre>
 * 则对应的配置文件(位于类路径下)是:/META-INF/config/test.xml.
 * <pre>
{@literal <?xml version="1.0" encoding="GBK"?>}
{@literal <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">}
{@literal <properties version="1.0">}
{@literal 	<entry key="code">Hello</entry>}
{@literal 	<entry key="display.name">World</entry>}
{@literal </properties>}
 * </pre>
 * 
 * 程序发布后, 你可以允许使用者修改此xml文件,为参数指定新的值, 
 * 或不允许使用者修改此xml文件,但因为你继承了这个配置文件管理类的父类, 所以其可以通过如下方式覆盖参数的默认值:
 * <ol>
 * 	<li>建立文件(位于类路径下):/custom.properties</li>
 * 	<li>在此文件内配置:test.display.name=New Value</li>
 * </ol>
 * 则
 * <blockquote><pre>
Config config = Config.getInstance();
System.out.println(config.getProperty("code"));//输出Hello
System.out.println(config.getProperty("display.name"));//输出New Value
 * </pre></blockquote> 
 * <p>我们这里演示了xml配置文件的写法,但实际应用中,更推荐用properties文件的写法, 因为更简洁些.</p>
 * </blockquote>
 * </p>
 * @author 刘宇明
 *
 */
public abstract class AbstractConfig {
	private static Logger logger = LoggerFactory
			.getLogger(AbstractConfig.class);
	private String configId;
	private Map<String, String> config;
	private static Map<String, Map<String, String>> allCustomConfig;
	/**
	 * 配置文件在类路径下的默认路径.
	 */
	public static final String CONFIG_BASE_PATH = "/META-INF/config";
	/**
	 * 自定义配置文件的文件名.
	 */
	public static final String CUSTOM_CONFIG_XML_FILE_NAME = "custom.xml";
	/**
	 * 自定义XML配置文件的文件名.
	 */
	
	public static final String CUSTOM_CONFIG_FILE_NAME = "custom.properties";

	protected AbstractConfig() {
		init();
	}

	private void init() {
		configId = getConfigId();
		config = getDefaultConfig();
		if (config == null) {
			config = new HashMap<String, String>();
		}
		Map<String, String> customConfig = getCustomConfig();
		if (customConfig != null)
			for (String key : customConfig.keySet()) {
				config.put(key, customConfig.get(key));
			}
	}

	/**
	 * 获得配置ID,可唯一标识此配置参数管理类.
	 * 
	 * @return 配置ID
	 */
	protected abstract String getConfigId();

	/**
	 * 获得配置参数及其默认值. 
	 * 
	 * 默认处理如下: 
	 * 尝试加载<code>CONFIG_BASE_PATH + "/" + getConfigId() + ".xml"</code>文件,
	 * 此文件不存在的话,加载<code>CONFIG_BASE_PATH + "/" + getConfigId() + ".properties"</code>文件.
	 * 
	 * @return 配置参数及其默认值
	 */
	protected Map<String, String> getDefaultConfig() {
		Properties config = new Properties();
		String configFile = CONFIG_BASE_PATH + "/" + configId + ".xml";
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(configFile);
			if(is!=null){
				config.loadFromXML(is);
			}
			else {
				configFile = CONFIG_BASE_PATH + "/" + CUSTOM_CONFIG_FILE_NAME;
				is = getClass().getResourceAsStream(configFile);
				config.load(is);
			}
		} catch (Exception e) {
			throw new RuntimeException("未找到配置文件或配置文件有错:" + configFile, e);
		}
		finally{
			IOUtil.close(is);
		}

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map) config;
		expandProperty(config);
		return map;
	}

	private Map<String, String> getCustomConfig() {
		synchronized (CONFIG_BASE_PATH) {
			if (allCustomConfig == null) {
				allCustomConfig = new HashMap<String, Map<String, String>>();
				InputStream input = null;
				boolean isXmlConfigFile = false;
				try{
					String customFilePath = System.getProperty("config.custom.file");
					if(customFilePath!=null ){
						File customFile  = new File(customFilePath);
						if(customFile.isFile()){
							try {
								input = new FileInputStream(customFile);
								if(StringUtil.equalsIgnoreCase("xml", IOUtil.getFileExtension(customFile))){
									isXmlConfigFile=true;
								}
								logger.info("在文件系统中找到自定义配置文件:"+customFile.getAbsolutePath());
							} catch (FileNotFoundException e) {
							}
						}
						else {
							logger.error("未找到自定义配置文件:"+customFile.getAbsolutePath());
						}
					}
					else if((input =getClass().getResourceAsStream(CONFIG_BASE_PATH + "/" + CUSTOM_CONFIG_XML_FILE_NAME) )!=null){
						logger.info("在类路径下找到自定义配置文件:"+CONFIG_BASE_PATH + "/" + CUSTOM_CONFIG_XML_FILE_NAME);
						isXmlConfigFile=true;
					}
					else if((input =getClass().getResourceAsStream(CONFIG_BASE_PATH + "/" + CUSTOM_CONFIG_FILE_NAME) )!=null){
						logger.info("在类路径下找到自定义配置文件:"+CONFIG_BASE_PATH + "/" + CUSTOM_CONFIG_FILE_NAME);
					}
					else if((input =getClass().getResourceAsStream("/"+CUSTOM_CONFIG_XML_FILE_NAME) )!=null){
						logger.info("在类路径下找到自定义配置文件:"+"/" + CUSTOM_CONFIG_XML_FILE_NAME);
						isXmlConfigFile=true;
					}
					else if((input =getClass().getResourceAsStream("/"+CUSTOM_CONFIG_FILE_NAME) )!=null){
						logger.info("在类路径下找到自定义配置文件:"+"/" + CUSTOM_CONFIG_FILE_NAME);
					}
					
					if(input==null){
						logger.info("未应用自定义配置文件.");
					}
					else {
						Properties config = new Properties();
						try {
							if(isXmlConfigFile){
								config.loadFromXML(input);
							}
							else {
								config.load(input);
							}
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						expandProperty(config);
						for (Object obj : config.keySet()) {
							String key = (String) obj;
							int dotIndex = key.indexOf(".");
							if (dotIndex > 0 && dotIndex != key.length() - 1) {
								String configId = key.substring(0, dotIndex);
								String subKey = key.substring(dotIndex + 1);
								Map<String, String> configMap = allCustomConfig
										.get(configId);
								if (configMap == null) {
									configMap = new HashMap<String, String>();
									allCustomConfig.put(configId, configMap);
								}
								configMap.put(subKey, (String) config.get(key));
							} else {
								logger.warn("忽略key:" + key);
							}
						}
					}
					
				}
				finally {
					IOUtil.close(input);
				}
			}
		}
		return allCustomConfig.get(configId);
	}

	
	private void expandProperty(Properties properties){
		@SuppressWarnings("unchecked")
		Map temp =properties;
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>)temp;
		for(String key : map.keySet()){
			String value = PropertyExpander.expandProperties(map.get(key), map);
			value = PropertyExpander.expandSystemProperties(value);
			map.put(key, value);
		}
	}
	
	/**
	 * 动态设置配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param value 配置参数的新值
	 */
	protected void setProperty(String key, String value) {
		config.put(key, value);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @return 配置参数值, 不存在此配置参数的话, 返回<code>null</code>
	 */
	public String getProperty(String key) {
		return config.get(key);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 */
	public String getProperty(String key, String defaultValue) {
		String val = getProperty(key);
		return (val == null) ? defaultValue : val;
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是<code>true</code>或<code>false</code>
	 */
	public boolean getProperty(String key, boolean defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Boolean.valueOf(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数
	 */
	public int getProperty(String key, int defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Integer.parseInt(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数
	 */
	public Integer getProperty(String key, Integer defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Integer.valueOf(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数
	 */
	public long getProperty(String key, long defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Long.parseLong(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数
	 */
	public Long getProperty(String key, Long defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Long.valueOf(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数或小数
	 */
	public float getProperty(String key, float defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Float.parseFloat(val);
	}

	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数或小数
	 */
	public Float getProperty(String key, Float defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Float.valueOf(val);
	}
	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数或小数
	 */
	public double getProperty(String key, double defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Double.parseDouble(val);
	}
	/**
	 * 获得指定配置参数的值.
	 * 
	 * @param key 配置参数
	 * @param defaultValue 默认值
	 * @return 配置参数值, 不存在此配置参数的话, 返回默认值.
	 * @throws RuntimeException 如果配置参数值为空或值的字符串形式不是整数或小数
	 */
	public Double getProperty(String key, Double defaultValue) {
		String val = getProperty(key);
		return val == null ? defaultValue : Double.valueOf(val);
	}

	/**
	 * 返回只读的所有配置参数Map.
	 * 
	 * @return 只读的所有配置参数Map
	 */
	public Map<String, String> getAllProperties() {
		return Collections.unmodifiableMap(config);
	}
}
