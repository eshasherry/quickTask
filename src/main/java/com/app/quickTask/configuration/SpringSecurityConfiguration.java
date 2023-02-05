package com.app.quickTask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //instead of using LDAP or a database, I am using in memory manager
    Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        UserDetails userdetails = getUserDetails( "esha",  "esha");
        UserDetails userdetails2 = getUserDetails( "kiran",  "kiran");
        return new InMemoryUserDetailsManager(userdetails, userdetails2);

    }

    private UserDetails getUserDetails(String username, String password) {
        UserDetails userdetails = User.builder()
                                    .passwordEncoder(passwordEncoder)
                                    .username(username)
                                    .password(password)
                                    .roles("USER", "ADMIN")
                                    .build();
        return userdetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /*
    By default all urls are protected and a login form is shown for unauthorized request.
    For h2 db we need to disable CSRF(Cross Site Request Forgery) and frames.
    To do that we need to configure our own filter chain.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    return httpSecurity.build();
    }
}
