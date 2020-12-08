package com.bmadmin.comment.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bmadmin.comment.entity.CommentEntity;
import com.bmadmin.comment.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentsRepository;
	
	public Optional<CommentEntity> findById(Long commentsIdx) {
		return commentsRepository.findById(commentsIdx);
	}
	
	public Page<CommentEntity> findAll(PageRequest pageable) {
		return commentsRepository.findAll(pageable);
	}
	
	public Page<CommentEntity> findByBoardIdx(PageRequest pageable, CommentEntity comments) {
		return commentsRepository.findByBoardIdx(pageable, comments.getBoardIdx());
	}
	
	public CommentEntity updateById(Long commentsIdx, CommentEntity comments) {
		Optional<CommentEntity> commentsEntity = commentsRepository.findById(commentsIdx);
		CommentEntity retObj = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(commentsEntity.isPresent()) {
			retObj = commentsEntity.get();
			retObj.setCommentState((retObj.getCommentState().equals(comments.getCommentState()))?retObj.getCommentState():comments.getCommentState());
			retObj.setModAdmin(auth.getName());
			retObj.setModDate(LocalDateTime.now());
			retObj = commentsRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
//	@PostConstruct
//	public void initComments() {
//		CommentEntity comments;
//		LocalDateTime localDateTime = LocalDateTime.now();
//		
//		comments = new CommentEntity();
//		comments.setBoardIdx((long)1);
//		comments.setCommentIdx((long)1);
//		comments.setCommentOrder(1);
//		comments.setCommentState("NORMAL");
//		comments.setMemberIdx((long)1);
//		comments.setModDate(localDateTime);
//		comments.setRegDate(localDateTime);
//		comments.setCommentContents("테스트 게시판 01 테스트 게시물 01 테스트 댓글 01");
//		
//		commentsRepository.save(comments);
//	}
}
