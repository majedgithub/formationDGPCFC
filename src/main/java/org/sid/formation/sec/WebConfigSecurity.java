package org.sid.formation.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("ADMIN","USER","VALIDATEUR");
		auth.inMemoryAuthentication().withUser("user").password("{noop}user123").roles("USER");
		auth.inMemoryAuthentication().withUser("majed").password("{noop}majed123").roles("VALIDATEUR","USER");*/
		
		auth.jdbcAuthentication().dataSource(datasource).
		usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, roles as role from users_roles where username=?")
		.rolePrefix("ROLE_").passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/home","/consulterAction","/actionadda","actionaddb").hasRole("USER");
		http.authorizeRequests().antMatchers("/Recherche","/Attestation").hasRole("VALIDATEUR");
		http.authorizeRequests().antMatchers("/Formateur","/deleteformateur","/Employes").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}
