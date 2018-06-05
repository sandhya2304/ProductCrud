package com.sandy;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		/*
		auth.inMemoryAuthentication()
		  .withUser("admin").password("1234").roles("USER","ADMIN");
		auth.inMemoryAuthentication()
		  .withUser("user").password("user").roles("USER");  */
		
		auth.jdbcAuthentication()
	     .dataSource(dataSource)
	        .usersByUsernameQuery("select username as principal,pass as credentials,active from userss where username=?")  
	          .authoritiesByUsernameQuery("select user_username as principal,role_name as role from users_roles where user_username=?")
	             //.passwordEncoder(new Md5PasswordEncoder())
	                 .rolePrefix("ROLE_");
	
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.formLogin().loginPage("/login");
	    http.csrf().disable();
		http.authorizeRequests().antMatchers("/index").hasRole("user");
		http.authorizeRequests().antMatchers("/form","/edit","/save","/delete","/index").hasRole("admin");
		
		http.exceptionHandling().accessDeniedPage("/403");
		
	}
	
	

}
