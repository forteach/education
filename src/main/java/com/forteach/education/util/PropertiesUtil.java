package com.forteach.education.util;

import lombok.extern.slf4j.Slf4j;

import javax.jws.WebResult;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {

    public static Properties getProperties() {
        Properties prop = new Properties();
        InputStream in = WebResult.class.getClassLoader().getResourceAsStream("webResult.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            log.error("返回信息时，获取配置文件出错", e.getMessage(), e);
        }
        return prop;
    }

    public static Map<String, String> getMapForProperties() {
        Properties prop = new Properties();
        InputStream in = WebResult.class.getClassLoader().getResourceAsStream("webResult.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            log.error("返回信息时，获取配置文件出错", e.getMessage(), e);
        }
        return propertiesToMap(prop);
    }

    private static Map<String, String> propertiesToMap(Properties properties) {
        return new HashMap<String, String>((Map) properties);
    }

}
