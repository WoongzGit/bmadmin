package com.bmadmin.board.controller;

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

import com.bmadmin.board.entity.BoardEntity;
import com.bmadmin.board.service.BoardService;

@Controller
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardservice;
	
	/*
	 * 게시판 관리 페이지
	 */
	@GetMapping(value="/admin/board/list.html")
	public String index () {
		logger.info("index");
		return "board/board";
	}
	
	/*
	 * 게시판 목록 조회
	 */
	@PostMapping(value="/admin/boards", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<BoardEntity>> list (@RequestParam int pageNum, @RequestParam int pageSize) {
		logger.info("list");
		return new ResponseEntity<Page<BoardEntity>>(boardservice.findAll(PageRequest.of(pageNum, pageSize)), HttpStatus.OK);
	}
	
	/*
	 * 게시판 상세 검색
	 */
	@GetMapping(value="/admin/board/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BoardEntity> getOne (@PathVariable Long id) {
		logger.info("getOne");
		BoardEntity retObj = null;
		HttpStatus retCode = HttpStatus.OK;
		Optional<BoardEntity> optionalBoardEntity = boardservice.findById(id);
		
		if(optionalBoardEntity.isPresent()) {
			retObj = optionalBoardEntity.get();
		}else {
			retCode = HttpStatus.NO_CONTENT;
		}
		
		return new ResponseEntity<BoardEntity>(retObj, retCode);
	}
	
	/*
	 * 게시판 신규 등록
	 */
	@PostMapping(value="/admin/board", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BoardEntity> insertOne (BoardEntity board) {
		logger.info("insertOne");
		return new ResponseEntity<BoardEntity>(boardservice.save(board), HttpStatus.OK);
	}
	
	/*
	 * 게시판 수정
	 */
	@PutMapping(value="/admin/board/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BoardEntity> updateOne (@PathVariable Long id, BoardEntity board) {
		logger.info("updateOne");
		return new ResponseEntity<BoardEntity>(boardservice.UpdateById(id, board), HttpStatus.OK);
	}
	
	/*
	 * 게시판 삭제
	 */
	@DeleteMapping(value="/admin/board/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BoardEntity> deleteOne (@PathVariable Long id) {
		logger.info("deleteOne");
		return new ResponseEntity<BoardEntity>(boardservice.deleteById(id), HttpStatus.OK);
	}
}
