package com.app.quickTask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //instead of using LDAP or a database, I am using in memory manager
    Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        UserDetails userdetails = User.builder()
                                    .passwordEncoder(passwordEncoder)
                                    .username("esha")
                                    .password("esha")
                                    .roles("USER", "ADMIN")
                                    .build();
        return new InMemoryUserDetailsManager(userdetails);

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
