package com.callor.book.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
지금부터 이 클래스는 Config 설정을 하는 곳이다
 */
@Slf4j
@Configuration
public class SecurityConfig {

    // 비밀번호 암호화를 하기 위한 도구
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean method 는 프로젝트가 시작될때 자동으로 실행하라 라는 표식
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/").permitAll() // root 접속 아무나
                .antMatchers("/user/login").permitAll() // user/login 아무나
                .antMatchers("/user/join").permitAll() // user/join 아무나

                // book/** 으로 시작되는 모든 요청은 login 한 후에
                .antMatchers("/book/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .and()
                .logout();

        return http.build();
    }



}
