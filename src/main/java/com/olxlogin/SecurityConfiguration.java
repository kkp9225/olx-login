package com.olxlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//To override default Authentication
		
		auth.userDetailsService(userDetailsService);
		
		/* InMemoryAuthentication
		 * auth.inMemoryAuthentication().withUser("TOM").password(getPasswordEncoded().
		 * encode("TOM123")).roles("USER") .and()
		 * .withUser("JERRY").password(getPasswordEncoded().encode("JERRY123")).roles(
		 * "ADMIN");
		 */
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//To override Authorization
		http.csrf().disable().authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/all", "/authenticate", "/token/validate", "/hi","/user", "/user/*", "/actuator", "/actuator/refresh",
				"/read-property").permitAll()
		.and()
		.formLogin();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoded() {
		return NoOpPasswordEncoder.getInstance();
	}
}
