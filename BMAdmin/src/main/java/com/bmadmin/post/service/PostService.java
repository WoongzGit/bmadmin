package com.bmadmin.post.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bmadmin.post.entity.PostEntity;
import com.bmadmin.post.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public Optional<PostEntity> findById(Long postIdx) {
		return postRepository.findById(postIdx);
	}
	
	public Page<PostEntity> findAll(PageRequest pageable) {
		return postRepository.findAll(pageable);
	}
	
	public Page<PostEntity> findByBoardIdx(PageRequest pageable, PostEntity post) {
		return postRepository.findByBoardIdx(pageable, post.getBoardIdx());
	}
	
	public PostEntity updateById(Long postIdx, PostEntity post) {
		Optional<PostEntity> postEntity = postRepository.findById(postIdx);
		PostEntity retObj = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(postEntity.isPresent()) {
			retObj = postEntity.get();
			retObj.setPostState((retObj.getPostState().equals(post.getPostState()))?retObj.getPostState():post.getPostState());
			retObj.setModAdmin(auth.getName());
			retObj.setModDate(LocalDateTime.now());
			retObj = postRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
	public PostEntity deleteById(Long id, PostEntity post) {
		Optional<PostEntity> postEntity = postRepository.findById(id);
		PostEntity retObj = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(postEntity.isPresent()) {
			retObj = postEntity.get();
			retObj.setModAdmin(auth.getName());
			retObj.setPostState((retObj.getPostState().equals(post.getPostState()))?retObj.getPostState():post.getPostState());
			retObj.setModDate(LocalDateTime.now());
			retObj = postRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
}
