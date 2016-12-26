package com.z.mif.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
	
	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		@SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = super.getParameterMap();
	        Set<String> keySet = paramMap.keySet();
	        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
	            String key = (String) iterator.next();
	            String[] str = paramMap.get(key);
	          for(int i=0; i<str.length; i++) {
	              str[i] = stripXSS(str[i]);
	          	}
	        }
	        return paramMap ;
	    }

	private String stripXSS(String parameter) {
		if(StringUtils.isEmpty(parameter)){
			parameter = StringEscapeUtils.escapeHtml(parameter);
	//		parameter = StringEscapeUtils.escapeJavaScript(parameter); 
			parameter = StringEscapeUtils.escapeSql(parameter);
			}
		return parameter;
	}
}
