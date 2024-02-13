package com.gl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.gl.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorizeHttpRequests) -> {
			authorizeHttpRequests.requestMatchers("/403").permitAll().requestMatchers("/h2-console").permitAll()
			.anyRequest().authenticated();
		}).headers(((headers) -> headers.disable()))
		.formLogin(formLogin -> formLogin.defaultSuccessUrl("/employees/list", true))
		.logout(logout -> logout.logoutSuccessUrl("/login"))
		.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/403"));

		return http.build();
	}

}