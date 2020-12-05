package com.bmadmin.board.entity;

import java.util.List;

import com.bmadmin.common.vo.ResultVo;

public class BoardVo {
	List<BoardEntity> boards;
	
	BoardEntity board;
	
	ResultVo resultVo;

	public List<BoardEntity> getBoards() {
		return boards;
	}

	public void setBoards(List<BoardEntity> boards) {
		this.boards = boards;
	}

	public BoardEntity getBoard() {
		return board;
	}

	public void setBoard(BoardEntity board) {
		this.board = board;
	}

	public ResultVo getResultVo() {
		return resultVo;
	}

	public void setResultVo(ResultVo resultVo) {
		this.resultVo = resultVo;
	}
}
