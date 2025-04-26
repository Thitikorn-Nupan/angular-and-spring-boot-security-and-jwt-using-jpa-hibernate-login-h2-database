package com.ttknpdev.understandjwth2databasehelloworld.configuration.secure;

import com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt.JwtAuthenticationEntryPoint;
import com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt.JwtRequestFilter;
import com.ttknpdev.understandjwth2databasehelloworld.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**

*/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final PasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public SecurityConfiguration(
                               @Qualifier("entryPoint") JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                               @Qualifier("requestFilter") JwtRequestFilter jwtRequestFilter,
                               @Qualifier("detailsService") JwtUserDetailsService jwtUserDetailsService,
                               @Qualifier("bcryptEncoder") PasswordEncoder passwordEncoder  ) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load user for matching credentials. Use BCryptPasswordEncoder
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config
                .getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authenticate) ->
                        authenticate
                                // it won't authenticate this particular request => .permitAll()
                                /**
                                    If you’ve enabled Spring Security in your Spring Boot application,
                                    you will not be able to access the H2 database console.
                                    With its default settings under Spring Boot,
                                    Spring Security will block access to H2 database console.
                                    (don't forget to change h2-console default path)
                                    *** you have to set httpSecurity.headers().frameOptions().disable();
                                */
                                .requestMatchers("/console/**").permitAll()
                                .requestMatchers("/jwt/**").permitAll()
                                // it will authenticate this particular request who has Role like below it can access
                                .requestMatchers(HttpMethod.GET,"/api/test").hasAnyRole("NORMAL","USER")
                                .requestMatchers(HttpMethod.GET,"/api/book-store/programing/reads").hasRole("USER")
                                // and all other requests need to be authenticated
                                .anyRequest()
                                .authenticated()
                )
                // make sure we use stateless session;
                // session won't be used to
                // store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter , UsernamePasswordAuthenticationFilter.class);
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.headers().frameOptions().disable();
        /**
           when you need to use console of h2 you just specify  httpSecurity.headers().frameOptions().disable();
         */
        return httpSecurity.build();
    }
}
