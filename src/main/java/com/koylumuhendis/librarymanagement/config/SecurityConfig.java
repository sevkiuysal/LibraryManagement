package com.koylumuhendis.librarymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koylumuhendis.librarymanagement.security.JwtAccessDeniedHandler;
import com.koylumuhendis.librarymanagement.security.JwtAuthenticationEntryPoint;
import com.koylumuhendis.librarymanagement.security.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	private final JwtFilter filter;
	private final JwtAccessDeniedHandler accessDeniedHandler;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	public SecurityConfig(JwtFilter filter,JwtAccessDeniedHandler accessDeniedHandler,JwtAuthenticationEntryPoint authenticationEntryPoint) {
		this.filter = filter;
		this.accessDeniedHandler = accessDeniedHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
			throws Exception {
		return configuration.getAuthenticationManager();
	}
	@Bean
	@SuppressWarnings({ "removal", "deprecation" })
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security
				.csrf().disable()
				.cors().and()
				.authorizeRequests((auth) ->{
				auth.requestMatchers("api/admin").hasAuthority("ADMIN");
				auth.requestMatchers("api/user").hasAnyAuthority("ADMIN","USER");
				auth.anyRequest().authenticated();
				})
				.formLogin().disable()
				.httpBasic().disable()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web-> web.ignoring().requestMatchers("/api/images/**","api/auth/login"));
	}
	
	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		 return new WebMvcConfigurer() {
			 @Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");//bizdeki isteklerin diğer sayfalarla iletişimine izin ver 
			}
		};
	}
}
