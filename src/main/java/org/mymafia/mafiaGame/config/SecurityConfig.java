package org.mymafia.mafiaGame.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
//웹 보안에 대한 기본적인 기능 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //HttpSecurity를 이용한 configure : 특정 http 요청에 대해 웹 기반 보안을 구성할 수 있다.
    //기본적으로 모든 요청에 적용된다만 requestMatcher를 사용해 사용을 제한 할 수 있다.
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic().disable()
        //403 에러를 방지하기 위해 csrf 해제
                    .csrf().disable()
                    //세션 관리.세션생성정책(스프링시큐리티가 생성하지도 않고 기존것을 사용하지도 않음 == jwt 방식시 사용)
                    //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/script/**","image/**");
    }


}


