package com.bridgelabz.fundooNotes.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
      //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      return new BCryptPasswordEncoder();
    }

}
