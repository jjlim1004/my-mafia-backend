package org.mymafia.mafiaGame.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList();

    //웹소켓 클라이언트가 텍스트 메시지를 전송할 때 호출이 된다.
    //TextMessage 객체는 텍스트 메세지 정보를 가지고 있다.
    //또 char 배열을 매개변수로 하는 생성자를 가지고 있다
    //TextMessage.getPayload() 를 통해 TextMessage에 담긴 텍스트 값을 얻을 수 있다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //payload 가 뭐지
        String payload = message.getPayload();

        log.info("페이로드: " +  payload);

        //WebSocket 세션 이라는 게 있는 듯
        for(WebSocketSession sess: list){
            //세션에 메세지를 담아서 전송
            sess.sendMessage(message);
        }
    }

    //client가 접속 시 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info(session + " afterConnectionEstablished 의 session 클라이언트 접속");
    }

    //client가 접속 해제 시 호출되는 메서드


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + "afterConnectionClosed의 session  클라이언트 접속 해제 ");
        list.remove(session);
    }
}
