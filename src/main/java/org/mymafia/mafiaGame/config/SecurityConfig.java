package org.mymafia.mafiaGame.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //HttpSecurity를 이용한 configure : 특정 http 요청에 대해 웹 기반 보안을 구성할 수 있다.
    //기본적으로 모든 요청에 적용된다만 requestMatcher를 사용해 사용을 제한 할 수 있다.
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable();
        //403 에러를 방지하기 위해 csrf 해제
        httpSecurity.cors().and();
        httpSecurity.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/script/**","image/**");
    }

}


