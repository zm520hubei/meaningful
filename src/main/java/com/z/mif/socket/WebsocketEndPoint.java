package com.z.mif.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEndPoint extends TextWebSocketHandler {

	private static final Map<String, WebSocketSession> users;// 这个会出现性能问题，最好用Map来存储，key用userid

	static {
		users = new HashMap<String, WebSocketSession>();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		sendMessageToUser(message.getPayload(),new TextMessage("你有新信件哦！"));
	}

	/**
	 * 连接成功时候，会触发页面上onopen方法
	 */
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		users.put(session.getHandshakeAttributes().get("web-socket-id").toString(), session);
		// 这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
		// TextMessage returnMessage = new TextMessage("你将收到的离线");
		// session.sendMessage(returnMessage);
	}

	public void sendMessageToUser(String webSocketId, TextMessage message)
			throws IOException {
		WebSocketSession webSocketSession = users.get(webSocketId);
		if (webSocketSession != null && webSocketSession.isOpen()){
			webSocketSession.sendMessage(message);
			System.out.println(message.getPayload()+"=============");
		}
	}

}
