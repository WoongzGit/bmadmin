package com.bmadmin.comment.controller;

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

import com.bmadmin.comment.entity.CommentEntity;
import com.bmadmin.comment.entity.CommentVo;
import com.bmadmin.comment.service.CommentService;
import com.bmadmin.common.handler.MessageHandler;

@Controller
public class CommentController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MessageHandler messageHandler;
	
	/*
	 * 댓글 관리 페이지
	 */
	@GetMapping(value="/admin/comment/list.html")
	public String index () {
		logger.info("index");
		return "comment/comment";
	}
	
	/*
	 * 댓글 목록 조회
	 */
	@PostMapping(value="/admin/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<CommentEntity>> list (@RequestParam int pageNum, @RequestParam int pageSize, CommentEntity comment) {
		logger.info("list");
		logger.info("pageNum : " + pageNum);
		logger.info("pageSize : " + pageSize);
		logger.info("comment.getPostIdx() : " + comment.getPostIdx());
		logger.info("comment.getCommentContents() : " + comment.getCommentContents());
		logger.info("comment.getCommentIdx() : " + comment.getCommentIdx());
		logger.info("comment.getCommentOrder() : " + comment.getCommentOrder());
		logger.info("comment.getCommentState() : " + comment.getCommentState());
		logger.info("comment.getMemberIdx() : " + comment.getMemberIdx());
		logger.info("comment.getModAdmin() : " + comment.getModAdmin());
		logger.info("comment.getModDate() : " + comment.getModDate());
		logger.info("comment.getRegDate() : " + comment.getRegDate());

		return new ResponseEntity<Page<CommentEntity>>(commentService.findByPostIdx(PageRequest.of(pageNum, pageSize), comment), HttpStatus.OK);
	}
	
	/*
	 * 댓글 수정
	 */
	@PutMapping(value="/admin/comment/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CommentVo> updateOne (@PathVariable Long id, CommentEntity comment) {
		logger.info("updateOne");
		CommentVo retObj = new CommentVo();
		CommentEntity commentEntity = commentService.updateById(id, comment);
		if(commentEntity == null){
			retObj.setResultVo(messageHandler.getResultVo("result.code.UPDATE.FAIL.COMMENT"));
		}else {
			retObj.setComment(commentEntity);
			retObj.setResultVo(messageHandler.getResultVo("result.code.OK"));
		}
		
		return new ResponseEntity<CommentVo>(retObj, HttpStatus.OK);
	}
	
	/*
	 * 댓글 삭제
	 */
	@DeleteMapping(value="/admin/comment/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CommentVo> deleteOne (@PathVariable Long id, CommentEntity comment) {
		logger.info("deleteOne");
		CommentVo retObj = new CommentVo();
		CommentEntity commentEntity = commentService.deleteById(id, comment);
		if(commentEntity == null){
			retObj.setResultVo(messageHandler.getResultVo("result.code.DELETE.FAIL.COMMENT"));
		}else {
			retObj.setComment(commentEntity);
			retObj.setResultVo(messageHandler.getResultVo("result.code.OK"));
		}
		
		return new ResponseEntity<CommentVo>(retObj, HttpStatus.OK);
	}
}
