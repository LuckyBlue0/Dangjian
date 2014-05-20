package cn.com.do1.component.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换properties文件内的变量${xxxx}.
 * 
 * @author 刘宇明
 *
 */
public class PropertyExpander {

	/**
	 * 匹配变量的正则表达式
	 */
	public static final Pattern EXPANSION_PATTERN = Pattern.compile("[$][{]([^}]*)[}]");

	/**
	 * 替换input内的变量, props定义初始的变量和变量值.
	 * 
	 * @param input 要替换其中变量的字符串.
	 * @param props 定义初始的变量和变量值.
	 * @return 变量被对应的值替换, 如果变量没定义或者其值为null, 变量原样输出.
	 */
	public static String expandProperties(String input, Map<String,String> props) {
		if (!StringUtil.isNotEmpty(input))
			return input;
		
		Matcher matcher = EXPANSION_PATTERN.matcher(input);
		StringBuffer expanded = new StringBuffer(input.length());
		while (matcher.find()) {
			String propName = matcher.group(1).trim();
			String value = props.get(propName);
			if (value == null)
				value = matcher.group();
			matcher.appendReplacement(expanded, "");
			expanded.append(value);
		}
		matcher.appendTail(expanded);
		return expanded.toString();
	}
	/**
	 * 替换input内的变量, 初始的变量和变量值使用系统属性.
	 * 
	 * @param input 替换其中变量的字符串.
	 * @return 变量被对应的值替换, 如果变量没定义或者其值为null, 变量原样输出.
	 */
	@SuppressWarnings("unchecked")
	public static String expandSystemProperties(String input) {
		Map map =System.getProperties();
		return expandProperties(input, (Map<String,String>)map);
	}
}