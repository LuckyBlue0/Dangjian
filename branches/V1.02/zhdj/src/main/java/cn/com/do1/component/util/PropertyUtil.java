package cn.com.do1.component.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class PropertyUtil {
	
	  
    /**
     * 将配置文件的内容保存在map中  
     * @param filePath
     */
    public PropertyUtil(String filePath,Map<String, String> propertyMap) {  
        if (null == filePath || "".equals(filePath.trim())) {  
            System.out.println("The file path is null,return");  
            return;  
        }  
        filePath = filePath.trim();  
        
        // 获取资源文件  
        InputStream is = getClass().getResourceAsStream(filePath);  
  
        // 属性列表  
        Properties prop = new Properties();  
  
        try {  
            // 从输入流中读取属性列表  
            prop.load(is);  
        } catch (IOException e) {  
            System.out.println("load file faile." + e);  
            return;  
        } catch (Exception e) {  
            System.out.println("load file faile." + e);  
            return;  
        }  
  
        // 返回Properties中包含的key-value的Set视图  
        Set<Entry<Object, Object>> set = prop.entrySet();  
        // 返回在此Set中的元素上进行迭代的迭代器  
        Iterator<Map.Entry<Object, Object>> it = set.iterator();  
        String key = null, value = null;  
        // 循环取出key-value  
        while (it.hasNext()) {  
  
            Entry<Object, Object> entry = it.next();  
  
            key = String.valueOf(entry.getKey());
            value = String.valueOf(entry.getValue());  
  
            key = key == null ? key : key.trim().toUpperCase();  
            value = value == null ? value : value.trim().toUpperCase();  
            // 将key-value放入map中  
            propertyMap.put(key, value);  
        }  
    }  
}
