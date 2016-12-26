package com.z.mif.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.z.mif.interceptor.HandshakeInterceptor;

@Configuration  
@EnableWebMvc  
@EnableWebSocket 
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {
	public WebSocketConfig() {
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		  registry.addHandler(systemWebSocketHandler(), "/websocket").addInterceptors(new HandshakeInterceptor()); 
		  
	        System.out.println("registed!");  
	        registry.addHandler(systemWebSocketHandler(), "/sockjs/websocket/info").addInterceptors(new HandshakeInterceptor()).withSockJS();  
	}

	@Bean  
    public WebSocketHandler systemWebSocketHandler() {  
		System.out.println("7");
        return new SystemWebSocketHandler();  
    }  
}
