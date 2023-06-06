package com.example.demo.Config;

import com.example.demo.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

     private final MyUserDetailsService myUserDetailsService;
        @Bean
        public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
            daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());//tp hash password
            return daoAuthenticationProvider;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authenticationProvider(authenticationProvider())//authorization
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                    .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/auth/user").hasAuthority("USER")
                    .requestMatchers("/api/v1/auth/login").hasAuthority("USER")
                    .anyRequest().authenticated()
                    .and()
                    .logout().logoutUrl("/api/v1/auth/logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .and()
                    .httpBasic();
            return http.build();
        }


    }

