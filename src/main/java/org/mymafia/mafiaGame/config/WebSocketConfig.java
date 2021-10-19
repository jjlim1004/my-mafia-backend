package org.mymafia.mafiaGame.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    //메세지를 주고 받는 ChatHandler 사용을 위해
    private final ChatHandler chatHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //addHandler(핸들러, 엔드포인트).setAllowedOrigins(오리진);
        // 엔드포인트가 ws/chat 경우 ws://localhost:8080/chat 으로 커넥션을 연결하고 통신을 한다.
        //엔드포인트는 API가 서버에서 접근할 수 있도록 하는 URL
        registry.addHandler(chatHandler,"ws/chat").setAllowedOrigins("*");
    }




}
