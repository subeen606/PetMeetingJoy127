package com.petmeeting.joy.websocket;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.petmeeting.joy.login.model.MemberDto;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocket extends TextWebSocketHandler{
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	private Map<WebSocketSession, String> chatMemEmails = new HashMap<>(); //실제session의 아이디정보, web소켓정보
	
	private List<WebSocketSession> chatMemList = new ArrayList<WebSocketSession>();	
	
	public WebSocket() {
		System.out.println("EchoHandler 생성됨 " + new Date());
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결된 후에 실행
		
		//1. 들어온 사람의 실제 로그인 아이디 정보를 가져온다.
    	Map<String, Object> map = session.getAttributes();
    	MemberDto mem = (MemberDto)map.get("login"); 
    	String userEmail = mem.getEmail();
    	chatMemList.add(session);
    	chatMemEmails.put(session, userEmail); //세션:key, 유저아이디:value
    	//chatMemList.add(userEmail);
		System.out.println("연결 성공 " + new Date());
		users.put(session.getId(), session);	// map에 접속 된 user추가	일반세션x 웹소켓세션임	
		
		
		Iterator<WebSocketSession> sessionIds = chatMemEmails.keySet().iterator();
		while(sessionIds.hasNext()) {
    		WebSocketSession sessionId = sessionIds.next();
    		String value = chatMemEmails.get(sessionId); //실제 아이디값   		
    			System.out.println(JsonUser(value));
    			System.out.println("채팅맴 "+value);
    		   	
    			for (int i = 0; i < chatMemList.size(); i++) {
    				
    				chatMemList.get(i).sendMessage(new TextMessage(JsonUser(value)));    	
				}
    	}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 연결종료
		System.out.println("연결 종료 " + new Date());
		users.remove(session.getId());		// 접속끊긴 user삭제해중		
		chatMemList.remove(session);
		chatMemEmails.remove(session);
		
		Iterator<WebSocketSession> sessionIds = chatMemEmails.keySet().iterator();
		while(sessionIds.hasNext()) {
    		WebSocketSession sessionId = sessionIds.next();
    		String value = chatMemEmails.get(sessionId); //실제 아이디값   		
    			System.out.println(JsonUser(value));
    			System.out.println("채팅맴 "+value);
    		
    			for (int i = 0; i < chatMemList.size(); i++) {
    				chatMemList.get(i).sendMessage(new TextMessage("나감"));
					chatMemList.get(i).sendMessage(new TextMessage(JsonUser(value)));    	
				}
    	}
    	
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 수신
		System.out.println("메시지 수신 " + new Date());
		
		for (WebSocketSession s : users.values()) {
					// 파라미터로 넘어온 data(message)
			s.sendMessage(message);
			System.out.println(s.getId() + "에 메시지 발송");
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 예외발생
		System.out.println(session.getId() + "에서 Exception 발생");
	}
	
	 public String JsonUser(String id) {
	    	JsonObject jsonObject = Json.createObjectBuilder().add("list", id).build();
	    	StringWriter write = new StringWriter();
	    	
	    	try(JsonWriter jsonWriter = Json.createWriter(write)){
	    		jsonWriter.write(jsonObject);
	    	};
	    	
	    return write.toString();
	 }
	
	 /*
	//유저리스트
    private List<String> chatMemberList(Map<WebSocketSession,String> users) throws Exception {
    	//맵을 이용해서 세션을 통해 아이디값을 value로 가져와서 list에 담기
    	
    	//1.담을 리스트 껍데기 선언
    	List<String> list = new ArrayList<>();
    	
    	//2. 존재하는 웹소켓아이디, 로그인아이디 만큼 while문을 돌려준다.
    	Iterator<WebSocketSession> sessionIds = users.keySet().iterator();
		while(sessionIds.hasNext()) {
    		WebSocketSession sessionId = sessionIds.next();
    		String value = users.get(sessionId); //실제 아이디값
  
				list.add(value);			
    	}
    	return list;
    }
	*/
}
