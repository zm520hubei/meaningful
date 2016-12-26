package com.z.mif.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Map<String, WebSocketSession> users;

	static {
		users = new HashMap<String, WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		users.put(session.getHandshakeAttributes().get("web-socket-id").toString(), session);
		System.out.println("1");
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		System.out.println("连接数：" + users.size());
		sendMessageToUser(message.getPayload().toString(), new TextMessage("你有新信件哦！"));
		System.out.println("2");
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		System.out.println("3");
//		if (session.isOpen()) {
//			session.close();
//		}
		System.out.println(exception.getMessage() + " error msg ");
		users.remove(session.getHandshakeAttributes().get("web-socket-id").toString());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("4");
		users.remove(session.getHandshakeAttributes().get("web-socket-id").toString());
	}
	

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendMessageToUser(String webSocketId, TextMessage message)
			throws IOException {
		System.out.println("6");
		WebSocketSession webSocketSession = users.get(webSocketId);
		if (webSocketSession != null && webSocketSession.isOpen()) {
			webSocketSession.sendMessage(message);
		}
	}

}
