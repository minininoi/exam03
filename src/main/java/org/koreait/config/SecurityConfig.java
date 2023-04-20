package org.koreait.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return  http.build(); //인증을 하지 않고
    }
}
