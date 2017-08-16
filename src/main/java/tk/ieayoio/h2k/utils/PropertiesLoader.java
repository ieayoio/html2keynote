package tk.ieayoio.h2k.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * 属性文件加载
 *
 * @author longh
 */
public class PropertiesLoader {
    private final Properties properties;

    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return "";
    }

    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    private Properties loadProperties(String... resourcesPaths) {
        Properties props = new Properties();

        for (String location : resourcesPaths) {

            InputStream is = null;
            try {
//                Resource resource = resourceLoader.getResource(location);
//                is = resource.getInputStream();
                is = DSResourceLoader.getDSResourceLoader(location).getInputStream();
                props.load(is);
            } catch (IOException ex) {
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return props;
    }

    private PropertiesLoader(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
    }

    public static PropertiesLoader getPropertiesLoader(String... resourcesPaths) {
        return new PropertiesLoader(resourcesPaths);
    }
}
