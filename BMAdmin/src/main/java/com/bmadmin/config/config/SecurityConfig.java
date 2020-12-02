package com.bmadmin.config.config;

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

import com.bmadmin.config.handler.BMLogoutHandler;
import com.bmadmin.config.handler.LoginFailHandler;
import com.bmadmin.config.handler.LoginSuccessHandler;
import com.bmadmin.member.provider.MemberAuthenticationProvider;

@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MemberAuthenticationProvider authProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "*.html", "/h2-console/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/member/**").permitAll()
			.antMatchers("/admin/board/**").hasRole("ADMIN")
			.antMatchers("/admin/comment/**").hasRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/admin/member")
			.loginProcessingUrl("/admin/member/login")
			.failureHandler(failureHandler())
			.successHandler(successHandler())
			.permitAll()
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/admin/member/logout"))
			.addLogoutHandler(logoutHandler())
			.logoutSuccessUrl("/admin/member")
			.invalidateHttpSession(true)
		.and()
		.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
			.exceptionHandling().accessDeniedPage("/admin/member/denied");
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new LoginFailHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public LogoutHandler logoutHandler() {
		return new BMLogoutHandler();
	}
}
