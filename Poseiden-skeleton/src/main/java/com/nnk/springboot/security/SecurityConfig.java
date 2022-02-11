package com.nnk.springboot.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder passwordEncoder = passwordEncoder();
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**").hasAnyAuthority("ADMIN", "USER")
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/user/**").hasAuthority("USER")
		.anyRequest().authenticated() // All request have to be authenticated
		.and()
		.formLogin() // access login form
		.defaultSuccessUrl("/bidList/list")  
		.and()
		.logout() // logout
		.logoutUrl("/app-logout")
		.logoutSuccessUrl("/");

		http.exceptionHandling().accessDeniedPage("/app/error");// page for deny access

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

}
