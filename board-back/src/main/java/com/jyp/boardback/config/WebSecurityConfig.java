package com.jyp.boardback.config;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import com.jyp.boardback.filter.JwtAuthenticationFilter;

@Configurable
@EnableWebSecurity

public class WebSecurityConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        
        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
            .cors().and()
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement()
            .authorizeRequests()
            .antMatchers(...antPatterns:"/", "/api/v1/board/**", "/api/v1/search/**").permitAll();
            .antMatchers(HttpMethod.GET,...antPatterns:"/api/v1/board/**", "/api/v1/user/*").permitAll();
            .anyRequest().authenticated();
            
            httpSecurity.addFilterBefore(jwtAuthenticationFilter, UserNamePasswordAuthenticationFiled.class);
            return httpSecurity.build();
    }
}

class FaildAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
    throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("{\"code\": \}");
    }
}
