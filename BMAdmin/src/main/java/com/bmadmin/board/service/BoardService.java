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
	
	public BoardEntity findByBoardName(String boardName) {
		return boardRepository.findByBoardName(boardName);
	}
	
	public Optional<BoardEntity> findById(Long boardIdx) {
		return boardRepository.findById(boardIdx);
	}
	
	public Page<BoardEntity> findAll(PageRequest pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public Page<BoardEntity> findByBoardState(PageRequest pageable) {
		return boardRepository.findByBoardState(pageable, "NORMAL");
	}
	
	public BoardEntity save(BoardEntity board) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		board.setRegDate(LocalDateTime.now());
		board.setModDate(LocalDateTime.now());
		board.setRegAdmin(auth.getName());
		board.setModAdmin(auth.getName());
		
		return boardRepository.save(board);
	}
	
	public BoardEntity deleteById(Long id, BoardEntity board) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(id);
		BoardEntity retObj = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(boardEntity.isPresent()) {
			retObj = boardEntity.get();
			retObj.setModAdmin(auth.getName());
			retObj.setBoardState((retObj.getBoardState().equals(board.getBoardState()))?retObj.getBoardState():board.getBoardState());
			retObj.setModDate(LocalDateTime.now());
			retObj = boardRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
	public BoardEntity updateById(Long boardIdx, BoardEntity board) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(boardIdx);
		BoardEntity retObj = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(boardEntity.isPresent()) {
			retObj = boardEntity.get();
			retObj.setBoardDesc((retObj.getBoardDesc().equals(board.getBoardDesc()))?retObj.getBoardDesc():board.getBoardDesc());
			retObj.setBoardState((retObj.getBoardState().equals(board.getBoardState()))?retObj.getBoardState():board.getBoardState());
			retObj.setModAdmin(auth.getName());
			retObj.setModDate(LocalDateTime.now());
			retObj = boardRepository.save(retObj);
		}else {
			retObj = null;
		}
		return retObj;
	}
	
	@PostConstruct
	public void initBoard() {
		BoardEntity board;
		LocalDateTime localDateTime = LocalDateTime.now();
		
		board = new BoardEntity();
		board.setBoardName("최초의 게시판");
		board.setBoardDesc("테스트 게시판입니다.");
		board.setRegDate(localDateTime);
		board.setModDate(localDateTime);
		board.setRegAdmin("admin@test.com");
		board.setModAdmin("admin@test.com");
		board.setBoardState("NORMAL");
		
		boardRepository.save(board);
	}
}
