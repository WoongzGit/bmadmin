package com.bmadmin.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bmadmin.board.entity.BoardEntity;
import com.bmadmin.board.repository.BoardRepository;

@Service
public class BoardService{
	@Autowired
	private BoardRepository boardRepository;
	
	public Optional<BoardEntity> findById(Long boardIdx) {
		return boardRepository.findById(boardIdx);
	}
	
	public Page<BoardEntity> findAll(PageRequest pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public BoardEntity save(BoardEntity board) {
		LocalDateTime localDateTime = LocalDateTime.now();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		board.setModAdmin(auth.getName());
		board.setRegAdmin(auth.getName());
		board.setRegDate(localDateTime);
		board.setModDate(localDateTime);
		board.setBoardState("NORMAL");
		return boardRepository.save(board);
	}
	
	public BoardEntity deleteById(Long id) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(id);
		BoardEntity retObj = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(boardEntity.isPresent()) {
			retObj = boardEntity.get();
			retObj.setModDate(LocalDateTime.now());
			retObj.setBoardState("BLOCK");
			retObj.setModAdmin(auth.getName());
			retObj = boardRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
	public BoardEntity UpdateById(Long boardIdx, BoardEntity board) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(boardIdx);
		BoardEntity retObj = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(boardEntity.isPresent()) {
			retObj = boardEntity.get();
			retObj.setBoardName((retObj.getBoardName().equals(board.getBoardName()))?retObj.getBoardName():board.getBoardName());
			retObj.setBoardDesc((retObj.getBoardDesc().equals(board.getBoardDesc()))?retObj.getBoardDesc():board.getBoardDesc());
			retObj.setBoardState((retObj.getBoardState().equals(board.getBoardState()))?retObj.getBoardState():board.getBoardState());
			retObj.setModAdmin(auth.getName());
			retObj.setModDate(LocalDateTime.now());
			boardRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
	@PostConstruct
	public void initAdminMember() {
		BoardEntity board = new BoardEntity();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		
		board.setBoardDesc("테스트 게시판01 설명");
		board.setBoardName("테스트 게시판01");
		board.setBoardState("NORMAL");
		board.setModAdmin("seouldnd1@naver.com");
		board.setModDate(localDateTime);
		board.setRegAdmin("seouldnd1@naver.com");
		board.setRegDate(localDateTime);
	}
}
