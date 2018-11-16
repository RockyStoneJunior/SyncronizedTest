package com.learnandresearch.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//@Configuration
//@ComponentScan(basePackages = {"com.learnandresearch.security"})
//@EnableWebSecurity
public class SecSecurityConfig {//extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//	public SecSecurityConfig() {
//		super();
//	}
//	
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
//		auth.authenticationProvider(authProvider());
//	}
//	
//	@Override
//	public void configure(final WebSecurity web) throws Exception{
//		web.ignoring().antMatchers("/resources/**","/index");
//	}
//	
//	@Override
//	protected void configure(final HttpSecurity http) throws Exception{
//		http
//			.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/login*","/index","/user/registration*").permitAll()
//				.anyRequest().hasAnyAuthority("READ_PRIVILEGE")
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/home")
//				.successHandler(myAuthenticationSuccessHandler);
//	}
//	
//	public DaoAuthenticationProvider authProvider() {
//		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		return authProvider;
//	}
}

