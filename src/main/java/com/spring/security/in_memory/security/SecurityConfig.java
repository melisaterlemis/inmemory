package com.spring.security.in_memory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users(){
        UserDetails user1 = User.builder()
                .username("mls")
                .password(bCryptPasswordEncoder().encode("pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,admin);
    }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity security)throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x.requestMatchers("/public/**","/auth/**").permitAll())
                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return security.build();

        }



}
