package com.z.mif.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TelWebSocketHandler extends TextWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TelWebSocketHandler.class);

	/**
	 * 建立连接
	 * 
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String inquiryId = (String) session.getHandshakeAttributes().get("inquiryId");
		int empNo = (Integer) session.getHandshakeAttributes().get("empNo");
		TelSocketSessionUtils.add(inquiryId, empNo, session);
	}

	/**
	 * 收到客户端消息
	 * 
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		String inquiryId = (String) session.getHandshakeAttributes().get("inquiryId");
		int empNo = (Integer) session.getHandshakeAttributes().get("empNo");
		TelSocketSessionUtils.sendMessage(inquiryId, empNo, "【来自服务器的复读机】："+ message.getPayload().toString());
	}

	/**
	 * 出现异常
	 * 
	 * @param session
	 * @param exception
	 * @throws Exception
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		String inquiryId = (String) session.getHandshakeAttributes().get("inquiryId");
		int empNo = (Integer) session.getHandshakeAttributes().get("empNo");

		LOGGER.error("websocket connection exception: "
				+ TelSocketSessionUtils.getKey(inquiryId, empNo));
		LOGGER.error(exception.getMessage(), exception);

		TelSocketSessionUtils.remove(inquiryId, empNo);
	}

	/**
	 * 连接关闭
	 * 
	 * @param session
	 * @param closeStatus
	 * @throws Exception
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		String inquiryId = (String) session.getHandshakeAttributes().get("inquiryId");
		int empNo = (Integer) session.getHandshakeAttributes().get("empNo");
		TelSocketSessionUtils.remove(inquiryId, empNo);
	}

	/**
	 * 是否分段发送消息
	 * 
	 * @return
	 */
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
