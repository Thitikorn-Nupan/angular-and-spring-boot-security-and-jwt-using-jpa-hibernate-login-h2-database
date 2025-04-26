package com.ttknpdev.understandjwth2databasehelloworld.configuration;

import com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt.JwtAuthenticationEntryPoint;
import com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt.JwtRequestFilter;
import com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt.JwtTokenUtil;

import com.ttknpdev.understandjwth2databasehelloworld.service.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfiguration {

    @Bean(name = "entryPoint")
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean(name = "requestFilter")
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUserDetailsService(),jwtTokenUtil());
    }

    @Bean(name = "tokenUtil")
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean(name = "detailsService") // use @Bean instead @Service if you create bean this way
    public JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

    @Bean(name = "bcryptEncoder")
    public PasswordEncoder getBcryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}
