package com.z.mif.util;

import org.springframework.context.ApplicationContext;

public class SpringBeanUtils {
	
	private static ApplicationContext context;

	public static void setContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	public static Object getBean(String beanName,Object... args){
		return context.getBean(beanName, args);
	}
	
	public static Object getBean(Class<?> requiredType){
		return context.getBean(requiredType);
	}
}
