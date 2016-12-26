package com.z.mif.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {
	
	private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
	
	private static Properties ConfigurationProperties = new Properties();
	
	static{
		try {
			ConfigurationProperties.load(FileContentTypeUtils.class.getResourceAsStream("/mif.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return ConfigurationProperties.getProperty(key);
	}
	
	
}
