package com.bmadmin.member.controller;

import java.util.Optional;

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

import com.bmadmin.common.vo.ResultVo;
import com.bmadmin.member.entity.MemberEntity;
import com.bmadmin.member.entity.MemberVo;
import com.bmadmin.member.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	/*
	 * 회원 관리 페이지
	 */
	@GetMapping(value="/admin/member/list.html")
	public String index () {
		logger.info("index");
		return "member/member";
	}
	
	/*
	 * 회원 목록 조회
	 */
	@PostMapping(value="/admin/members", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<MemberEntity>> list (@RequestParam int pageNum, @RequestParam int pageSize) {
		logger.info("list");
		return new ResponseEntity<Page<MemberEntity>>(memberService.findAll(PageRequest.of(pageNum, pageSize)), HttpStatus.OK);
	}
	
	/*
	 * 회원 상세 검색
	 */
	@GetMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> getOne (@PathVariable Long id) {
		logger.info("getOne");
		MemberEntity retObj = new MemberEntity();
		Optional<MemberEntity> optionalMemberEntity = memberService.findById(id);
		
		if(optionalMemberEntity.isPresent()) {
			retObj = optionalMemberEntity.get();
		}
		
		return new ResponseEntity<MemberEntity>(retObj, HttpStatus.OK);
	}
	
	/*
	 * 회원 신규 등록 전 이메일 중복체크
	 */
	@PutMapping(value="/admin/member", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberVo> checkOne (MemberEntity member) {
		logger.info("checkOne");
		MemberVo retVo = new MemberVo();
		ResultVo resultVo = new ResultVo("OK", "000");
		MemberEntity memberEntity = memberService.findByEmail(member.getEmail());
		logger.info("memberEntity == null : " + (memberEntity == null));
		if(memberEntity != null){
			resultVo.setMsg("DUPLICATE");
			resultVo.setCode("001");
		}
		
		retVo.setResultVo(resultVo);
		
		return new ResponseEntity<MemberVo>(retVo, HttpStatus.OK);
	}
	
	/*
	 * 회원 신규 등록
	 */
	@PostMapping(value="/admin/member", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> insertOne (MemberEntity member) {
		logger.info("insertOne");
		return new ResponseEntity<MemberEntity>(memberService.save(member), HttpStatus.OK);
	}
	
	/*
	 * 회원 수정
	 */
	@PutMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> updateOne (@PathVariable Long id, MemberEntity member) {
		logger.info("updateOne");
		return new ResponseEntity<MemberEntity>(memberService.UpdateById(id, member), HttpStatus.OK);
	}
	
	/*
	 * 회원 삭제
	 */
	@DeleteMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> deleteOne (@PathVariable Long id) {
		logger.info("deleteOne");
		return new ResponseEntity<MemberEntity>(memberService.deleteById(id), HttpStatus.OK);
	}
	
	/*
	 * 회원 비밀번호 초기화
	 */
	@PostMapping(value="/admin/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MemberEntity> initPassword (@PathVariable Long id) {
		logger.info("initPassword");
		return new ResponseEntity<MemberEntity>(memberService.InitPasswordById(id), HttpStatus.OK);
	}
}
