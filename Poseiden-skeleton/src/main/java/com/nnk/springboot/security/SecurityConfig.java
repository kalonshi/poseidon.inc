package com.nnk.springboot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import com.nnk.springboot.auth.CustomOAuth2UserService;
import com.nnk.springboot.repositories.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private CustomOAuth2UserService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder passwordEncoder = passwordEncoder();
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/", "/login")
		.permitAll().anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutUrl("/app-logout").logoutSuccessUrl("/")
		.and().oauth2Login().userInfoEndpoint().userService(userService);
		
		/* http.formLogin(); */
		/*
		 * http.authorizeRequests() .antMatchers("/admin/**").hasAuthority("ADMIN")
		 * .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER","ROLE_USER")
		 * .anyRequest().authenticated() // All request have to be authenticated
		 * .and().formLogin() // access login form .defaultSuccessUrl("/bidList/list")
		 * .and().logout() // logout .logoutUrl("/app-logout").logoutSuccessUrl("/")
		 * .and().oauth2Login().defaultSuccessUrl("/trade/list").userInfoEndpoint().
		 * userService(userService);
		 * 
		 * http.exceptionHandling().accessDeniedPage("/app/error");// page for deny
		 * access
		 * 
		 */
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	 * @Bean public ClientRegistrationRepository clientRegistrationRepository() {
	 * List<ClientRegistration> registrations = new ArrayList<>();
	 * registrations.add(githubClientRegistration());
	 * 
	 * return new InMemoryClientRegistrationRepository(registrations); }
	 * 
	 * private ClientRegistration githubClientRegistration() { return
	 * ClientRegistration.withRegistrationId("github") .clientId("github-client-id")
	 * .clientSecret("github-client-secret")
	 * .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
	 * .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	 * .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
	 * .authorizationUri("https://github.com/login/oauth/authorize")
	 * .tokenUri("https://github.com/login/oauth/access_token")
	 * .userInfoUri("https://api.github.com/user") .clientName("GitHub").build(); }
	 */
}
