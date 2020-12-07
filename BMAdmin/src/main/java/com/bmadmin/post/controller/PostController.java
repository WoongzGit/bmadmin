package com.bmadmin.post.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmadmin.common.handler.MessageHandler;
import com.bmadmin.post.entity.PostEntity;
import com.bmadmin.post.entity.PostVo;
import com.bmadmin.post.service.PostService;

@Controller
public class PostController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private MessageHandler messageHandler;
	
	/*
	 * 게시물 관리 페이지
	 */
	@GetMapping(value="/admin/post/list.html")
	public String index () {
		logger.info("index");
		return "post/post";
	}
	
	/*
	 * 게시물 목록 조회
	 */
	@PostMapping(value="/admin/posts", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<PostEntity>> list (@RequestParam int pageNum, @RequestParam int pageSize, PostEntity post) {
		logger.info("list");
		return new ResponseEntity<Page<PostEntity>>(postService.findByBoardIdx(PageRequest.of(pageNum, pageSize), post), HttpStatus.OK);
	}
	
	/*
	 * 게시물 상세 검색
	 */
	@GetMapping(value="/admin/post/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostVo> getOne (@PathVariable Long id) {
		logger.info("getOne");
		PostVo retObj = new PostVo();
		Optional<PostEntity> optionalPostEntity = postService.findById(id);
		
		if(optionalPostEntity.isPresent()) {
			retObj.setPost(optionalPostEntity.get());
			retObj.setResultVo(messageHandler.getResultVo("result.code.OK"));
		}else {
			retObj.setResultVo(messageHandler.getResultVo("result.code.NOT.FOUND.POST"));
		}
		
		return new ResponseEntity<PostVo>(retObj, HttpStatus.OK);
	}
	
	/*
	 * 게시물 수정
	 */
	@PutMapping(value="/admin/post/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostVo> updateOne (@PathVariable Long id, PostEntity post) {
		logger.info("updateOne");
		PostVo retObj = new PostVo();
		PostEntity postEntity = postService.updateById(id, post);
		if(postEntity == null){
			retObj.setResultVo(messageHandler.getResultVo("result.code.UPDATE.FAIL.BOARD"));
		}else {
			retObj.setPost(postEntity);
			retObj.setResultVo(messageHandler.getResultVo("result.code.OK"));
		}
		
		return new ResponseEntity<PostVo>(retObj, HttpStatus.OK);
	}
	
	/*
	 * 게시물 목록 조회
	 */
	@GetMapping(value="/test", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<PostEntity>> test (@RequestParam int pageNum, @RequestParam int pageSize, PostEntity post) {
		logger.info("test");
		
		logger.info("post.getBoardIdx() : " + post.getBoardIdx());
//		logger.info("post.getBoardName() : " + post.getBoardName());
//		logger.info("post.getEmail() : " + post.getEmail());
//		logger.info("post.getMemberIdx() : " + post.getMemberIdx());
//		logger.info("post.getModAdmin() : " + post.getModAdmin());
//		logger.info("post.getModDate() : " + post.getModDate());
//		logger.info("post.getPostContent() : " + post.getPostContent());
//		logger.info("post.getPostIdx() : " + post.getPostIdx());
//		logger.info("post.getPostState() : " + post.getPostState());
//		logger.info("post.getPostTitle() : " + post.getPostTitle());
//		logger.info("post.getRegDate() : " + post.getRegDate());
		
		Page<PostEntity> returnObj = postService.findByBoardIdx(PageRequest.of(pageNum, pageSize), post);
		
		for(PostEntity postEntity : returnObj) {
			logger.info("=========================================================");
			logger.info("postEntity.getBoardIdx() : " + postEntity.getBoardIdx());
			logger.info("postEntity.getBoardName() : " + postEntity.getBoardName());
			logger.info("postEntity.getEmail() : " + postEntity.getEmail());
			logger.info("postEntity.getMemberIdx() : " + postEntity.getMemberIdx());
			logger.info("postEntity.getModAdmin() : " + postEntity.getModAdmin());
			logger.info("postEntity.getModDate() : " + postEntity.getModDate());
			logger.info("postEntity.getPostContent() : " + postEntity.getPostContents());
			logger.info("postEntity.getPostIdx() : " + postEntity.getPostIdx());
			logger.info("postEntity.getPostState() : " + postEntity.getPostState());
			logger.info("postEntity.getPostTitle() : " + postEntity.getPostTitle());
			logger.info("postEntity.getRegDate() : " + postEntity.getRegDate());
		}
		

		return new ResponseEntity<Page<PostEntity>>(postService.findByBoardIdx(PageRequest.of(pageNum, pageSize), post), HttpStatus.OK);
	}
}