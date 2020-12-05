package com.bmadmin.common.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.bmadmin.member.entity.MemberEntity;
import com.bmadmin.member.service.MemberService;

@Service
public class BMLoginFailHandler implements AuthenticationFailureHandler{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
		
		logger.info("onAuthenticationFailure");
		String cnt = messageSource.getMessage("pw.try.max.cnt", null, LocaleContextHolder.getLocale());
		
		if(exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.no.name", null, LocaleContextHolder.getLocale()));
		} else if (exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.not.match", null, LocaleContextHolder.getLocale()));
			
			MemberEntity member = memberService.findByEmail(request.getParameter("username"));
			
			member.setAdminTry(member.getAdminTry() + 1);
			
			if(member.getAdminTry() >= Integer.parseInt(cnt)) {
				member.setAdminState("PWLOCK");
			}
			
			memberService.loginTryUp(member);
		} else if(exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.pw.lock", new String[] {cnt}, LocaleContextHolder.getLocale()));
		} else if(exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.lock.by.admin", null, LocaleContextHolder.getLocale()));
		} else if(exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.expired.id", null, LocaleContextHolder.getLocale()));
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", messageSource.getMessage("msg.expired.pw", null, LocaleContextHolder.getLocale()));
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login");
		dispatcher.forward(request, response);
	}
}
