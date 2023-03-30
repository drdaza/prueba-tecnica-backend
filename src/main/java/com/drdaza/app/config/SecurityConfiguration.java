package com.drdaza.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.drdaza.app.services.securityService.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
    MyAuthtenticationEntryPoint authtenticationEntryPoint;

    JpaUserDetailsService service;

    public SecurityConfiguration(JpaUserDetailsService service) {
        this.service = service;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .cors()
            .and()
            .headers(csrf -> csrf.frameOptions().sameOrigin())
            .formLogin(form -> form
                       .loginPage("/api/login")
                       .disable() )
            .logout(out -> out
                    .logoutUrl("/api/logout")
                    .deleteCookies("JSESSIONID"))
            .authorizeHttpRequests(auth -> auth
                                   .requestMatchers("/api/register").permitAll()
                                   .anyRequest()
                                   .authenticated())
            .userDetailsService(service)
            .sessionManagement(session -> session
                               .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .httpBasic(basic -> basic
                       .authenticationEntryPoint(authtenticationEntryPoint));      

            return http.build();
    }
}
