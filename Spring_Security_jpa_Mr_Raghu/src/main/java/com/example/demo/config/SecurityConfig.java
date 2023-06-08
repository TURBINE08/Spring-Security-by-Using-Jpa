package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(pwdEncoder);	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/home","/reg","/insert","/save").permitAll() //for all users  no security required
		.antMatchers("/welcome").authenticated() // after login
		.antMatchers("/admin").hasAuthority("ADMIN") // only for admin
		.antMatchers("/emp").hasAuthority("EMPLOYEE") // only for employee
		.antMatchers("/std").hasAnyAuthority("EMPLOYEE","ADMIN") // only for student
		.anyRequest().authenticated()
		
		//after login
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome",true)
		
		// for logout
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		// for exceptional handle
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		
		;
			
		
	}

}
