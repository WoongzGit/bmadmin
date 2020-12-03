package com.bmadmin.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmadmin.member.entity.MemberEntity;
import com.bmadmin.member.service.MemberService;

@Controller
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	/*
	 * 회원 관리 페이지
	 */
	@GetMapping(value="/admin/member")
	public String index () {
		logger.info("index");
		return "member/member";
	}
	
	/*
	 * 회원 목록 조회
	 */
	@PostMapping(value="/admin/member", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<MemberEntity>> list (@RequestParam int pageNum, @RequestParam int pageSize) {
		logger.info("list");
		logger.info("pageNum : " + pageNum);
		logger.info("pageSize : " + pageSize);
		return new ResponseEntity<Page<MemberEntity>>(memberService.findAll(PageRequest.of(pageNum, pageSize)), HttpStatus.OK);
	}
	
	/*
	 * 회원 1인 상세 검색
	 */
	@GetMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> getOne (@PathVariable String id) {
		logger.info("getOne");
		return new ResponseEntity<MemberEntity>(memberService.findById(id), HttpStatus.OK);
	}
	
	/*
	 * 회원 1인 신규 등록
	 */
	@PostMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> insertOne (@PathVariable String id) {
		logger.info("insertOne");
		return new ResponseEntity<MemberEntity>(memberService.findById(id), HttpStatus.OK);
	}
	
	/*
	 * 회원 1인 수정
	 */
	@PutMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> updateOne (@PathVariable String id, MemberEntity member) {
		logger.info("updateOne");
		return new ResponseEntity<MemberEntity>(memberService.findById(id), HttpStatus.OK);
	}
	
	/*
	 * 회원 1인 삭제
	 */
	@DeleteMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> deleteOne (@PathVariable String id, MemberEntity member) {
		logger.info("deleteOne");
		return new ResponseEntity<MemberEntity>(memberService.findById(id), HttpStatus.OK);
	}
	
//	@PostMapping(value="/email", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<MemberEntity> email (MemberEntity member) {
//		logger.info("email");
//		logger.info("=============================");
//		logger.info(member.toString());
//		logger.info("=============================");
//		return new ResponseEntity<MemberEntity>(memberService.findByEmail(member), HttpStatus.OK);
//	}
}
