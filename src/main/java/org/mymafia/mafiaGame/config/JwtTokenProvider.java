package org.mymafia.mafiaGame.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey = "jwtkey";

    private long tokenValidTime = 30 * 60 * 1000L;

    //JWT token 생성
    public String createToken(String user, List<String> roles){
        //claims 생성 및 payload 설정
        Claims claims = Jwts.claims().setSubject(user);

        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims) // 발행 유저 정보 저장
                .setIssuedAt(date)  // 발행 시간 저장
                //토큰 유효 시간 저장
                .setExpiration(new Date(date.getTime() + tokenValidTime))
                // 해싱 알고리즘 및 키 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
