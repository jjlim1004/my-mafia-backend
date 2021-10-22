package org.mymafia.mafiaGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.PostConstruct;
import javax.websocket.server.ServerEndpoint;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		MafiaGameApplication.class,
		Jsr310JpaConverters.class})
public class MafiaGameApplication {

	//Date/Time 사용을 위한 메서드
	@PostConstruct
	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(MafiaGameApplication.class, args);
	}


}
