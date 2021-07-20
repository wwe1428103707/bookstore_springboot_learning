package com.yufangjian.fenix_learning.bookstore.infrastructure.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import java.util.Optional;

@Named
public class Encryption {

    //Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String encode(CharSequence rawPassword){
        return passwordEncoder().encode(Optional.ofNullable(rawPassword).orElse(""));
    }
}
