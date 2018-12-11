package org.gorany.ws;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.gorany.domain.websocket.MsgInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class GreetingController extends TextWebSocketHandler {

	// List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	Map<String, WebSocketSession> monitorMap = new ConcurrentHashMap<>();

	public List<String> id;
	public String getid, host;
	public String raspId;
	
	

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
		System.out.println("message------------------------>" + message);
		
		MsgInfo msgObj = new Gson().fromJson(message.getPayload(), MsgInfo.class);
		
		System.out.println(msgObj);
		
		if(msgObj.getMyDevice() != null) {
			monitorMap.put(msgObj.getMyDevice(), session);
			return;
		}
		
		if(msgObj.getFromDevice() != null) {
			
			WebSocketSession monitorSession = monitorMap.get(msgObj.getFromDevice());
			
			if(monitorSession != null) {
				monitorSession.sendMessage(new TextMessage(msgObj.getFromDevice() + ": " + session.getRemoteAddress().getHostName()) );
			}
			
		}
		
		
		
		

	}

	// 세션이 끊길 경우
	@Override
	public void afterConnectionClosed(final WebSocketSession session, CloseStatus status) throws Exception {

		System.out.println("--------------------------------종료");
		System.out.println(session.getId() + "연결 끊어짐");
		sessions.remove(session.getId());
		System.out.println("제거 후 SIZE: " + sessions.size());

	}

	// 연결이 되면
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// sessions.add(session.getId());
		sessions.put(session.getId(), session);
		System.out.println("session: " + session);
		System.out.println("id: " + session.getId());
		System.out.println("handshakeHeaders: " + session.getHandshakeHeaders());
		System.out.println("extensions: " + session.getExtensions());
		System.out.println("지금은 SIZE: " + sessions.size());

	}
	/*
	@OnWebSocketConnect
    public void onConnect(WebSocketSession session) {
		System.out.println(" connected"+ session.getRemoteAddress().getHostName());
        logger.debug(" connected", session.getRemoteAddress().getHostName());
    }
	

	
	 * @Override public void handleMessage(WebSocketSession session,
	 * WebSocketMessage<?> message) throws Exception { Map<String, Object> map =
	 * session.getAttributes(); String userId = (String)map.get("name");
	 * System.out.println("전송자 아이디: " + userId); System.out.println(map);
	 * System.out.println(session.getAttributes()); System.out.println(session);
	 * 
	 * 
	 * }
	 */

}
