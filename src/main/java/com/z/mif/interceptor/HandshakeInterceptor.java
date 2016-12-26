package com.z.mif.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.z.mif.constants.Constants;
import com.z.mif.entity.UserEObj;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		System.out.println("8");
		  //解决The extension [x-webkit-deflate-frame] is not supported问题  
        if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {  
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");  
        }  
        
		ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		HttpSession session = servletRequest.getServletRequest().getSession();
		UserEObj u = (UserEObj) session.getAttribute(Constants.SESSION_USER);
		if(u != null) // )_(妈的360浏览器获取不到session   导致 这里为null
			attributes.put("web-socket-id",u.getId()); 
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		System.out.println("9");
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
