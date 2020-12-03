package com.bmadmin.member.service;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmadmin.member.entity.MemberEntity;
import com.bmadmin.member.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MemberEntity findByEmail(MemberEntity member) {
		return memberRepository.findByEmail(member.getEmail());
	}
	
	public MemberEntity findByEmail(String memberEmail) {
		return memberRepository.findByEmail(memberEmail);
	}
	
	public MemberEntity findById(String memberId) {
		return memberRepository.findById(memberId);
	}
	
	public Page<MemberEntity> findAll(PageRequest pageable) {
		return memberRepository.findAll(pageable);
	}
	
	public MemberEntity save(MemberEntity member) {
		memberRepository.save(member);
		return member;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails = findByEmail(username);
		return userDetails;
	}
	
	@PostConstruct
	public void initAdminMember() {
		MemberEntity member;
		
		for (int i = 0; i < 100; i++) {
			member = new MemberEntity();
			member.setName("관리자");
			member.setEmail("seouldnd" + i + "@naver.com");
			member.setId("admin" + i);
			member.setPassword(passwordEncoder.encode("AdminP@ssw0rd"));
			member.setAuth("ROLE_ADMIN");
			member.setMemberState("NORMAL");
			member.setRanking(0);
			member.setCreateDate(LocalDateTime.now());
			member.setModDate(LocalDateTime.now());
			
			memberRepository.save(member);
		}
	}
}
