package com.z.mif.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.z.mif.constants.Constants;
import com.z.mif.entity.UserEObj;


public class UserInterceptor implements HandlerInterceptor {

	//当request处理完成后被调用
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根
		
	}

	// 在handler被执行后被调用
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	//在实际的handler被执行前被调用
	@SuppressWarnings("all")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		// TODO  不设置session过期时间
		UserEObj user = (UserEObj)request.getSession().getAttribute(Constants.SESSION_USER);
		if(user == null){
			Map map = new HashMap();
			Enumeration paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName  = (String) paramNames.nextElement();
				String[] paramValues  = request.getParameterValues(paramName);
				if(paramValues.length == 1){
					String paramValue = paramValues[0];
					if(paramValue.length() != 0){
						map.put(paramName, paramValue);
					}
				}
			}
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_URI, request.getRequestURI());
			session.setAttribute(Constants.SESSION_URI_PARAMS, map);
			 Set<Map.Entry<String, String>> set = map.entrySet();  
		        System.out.println("------------------------------");  
		        for (Map.Entry entry : set) {  
		            System.out.println(entry.getKey() + ":" + entry.getValue());  
		        }  
		        System.out.println("------------------------------");
	        request.getRequestDispatcher("/94071001/login").forward(request, response);
			return false;
		}else{
			return true;
		}
	}

}