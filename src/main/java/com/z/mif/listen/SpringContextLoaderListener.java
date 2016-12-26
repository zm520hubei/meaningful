package com.z.mif.listen;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.z.mif.util.SpringBeanUtils;

public class SpringContextLoaderListener extends ContextLoaderListener {

	private ApplicationContext applicationContext = null;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		applicationContext  = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		SpringBeanUtils.setContext(applicationContext);
	}
}
