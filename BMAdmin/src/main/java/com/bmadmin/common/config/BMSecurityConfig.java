package com.bmadmin.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bmadmin.common.handler.BMLogoutHandler;
import com.bmadmin.common.handler.BMLoginFailHandler;
import com.bmadmin.common.handler.BMLoginSuccessHandler;
import com.bmadmin.common.provider.BMAuthenticationProvider;

@Configuration
@EnableWebSecurity(debug=true)
public class BMSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BMAuthenticationProvider authProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/admin/css/**", "/admin/js/**", "/admin/vender/**", "/admin/js/**", "/admin/images/**", "/*.ico");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/login").permitAll()
			.antMatchers("/admin/member/**").hasRole("ROLE_ADMIN")
			.antMatchers("/admin/board/**").hasRole("ROLE_ADMIN")
			.antMatchers("/admin/post/**").hasRole("ROLE_ADMIN")
			.antMatchers("/admin/comment/**").hasRole("ROLE_ADMIN")
		.and()
			.formLogin()
			.loginPage("/admin/login")
			.loginProcessingUrl("/admin/loginProcess")
			.failureHandler(failureHandler())
			.successHandler(successHandler())
			.permitAll()
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
			.addLogoutHandler(logoutHandler())
			.logoutSuccessUrl("/admin/login")
			.invalidateHttpSession(true)
		.and()
		.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
			.exceptionHandling().accessDeniedPage("/admin/denied.html");
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new BMLoginFailHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new BMLoginSuccessHandler();
	}
	
	@Bean
	public LogoutHandler logoutHandler() {
		return new BMLogoutHandler();
	}
}
