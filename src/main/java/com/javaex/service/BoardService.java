package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> exeList() {
		System.out.println("BoardService.exeList()");
		
		List<BoardVo> boardList = boardDao.boardSelectList();
		
		return boardList;
	}
	
	//1개 불러오기
	public BoardVo exeRead(int no) {
		System.out.println("BoardService.exeRead()");
		BoardVo bVo = boardDao.selectOne(no);
		
		return bVo;
	}
	
	//수정
	public int exeModify(BoardVo boardVo) {
		System.out.println("BoardService.exeModify()");
		
		int count = boardDao.modify(boardVo);
		
		return count;
	}
	
	//등록
	public int exeWrite(BoardVo boardVo) {
		System.out.println("BoardService.exeWrite()");
		
		return boardDao.boardWrite(boardVo);
	}

}
