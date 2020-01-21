package com.bridgelabz.fundooNotes.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bridgelabz.fundooNotes.service.IUsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final IUsersService usersService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(IUsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

//	@Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//      //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//      return bCryptPasswordEncoder;
//  }

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll().
		anyRequest().authenticated();
		http.headers().frameOptions().disable();
	}

}
