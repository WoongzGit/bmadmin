package com.bmadmin.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.bmadmin.member.entity.MemberEntity;
import com.bmadmin.member.service.MemberService;

@Service
public class BMLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("onAuthenticationSuccess");
		
		MemberEntity member = memberService.findByEmail(request.getParameter("username"));
		
		member.setAdminTry(0);
		
		memberService.save(member);
		response.sendRedirect("/admin/member/list.html");
	}
}
