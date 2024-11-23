package org.springboot.CustomerAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeHttpRequests().antMatchers("/").permitAll().and().httpBasic
	  ();
	  
	  }
	 
	
	@Value("${spring.security.user.name}")
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            auth
            // enable in memory based authentication with a user named "user" and "admin"
            .inMemoryAuthentication().withUser(userName).password(encoder.encode(password)).roles("USER")
                            .and().withUser("admin").password("password").roles("USER", "ADMIN");
    }

}
