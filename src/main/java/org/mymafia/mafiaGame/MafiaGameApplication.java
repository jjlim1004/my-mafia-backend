package org.mymafia.mafiaGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@SpringBootApplication
public class MafiaGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MafiaGameApplication.class, args);
	}

	//메세지를 내보내는 메소드(???)
	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}
}
