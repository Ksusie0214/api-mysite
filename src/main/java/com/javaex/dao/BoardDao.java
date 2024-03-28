package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<BoardVo> boardSelectList() {
		System.out.println("boardDao.boardSelectList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		System.out.println(boardList);
		
		return boardList;
	}
	
	//1개 불러오기
	public BoardVo selectOne(int no) {
		System.out.println("BoardDao.selectOne()");
		
		BoardVo bVo = sqlSession.selectOne("board.selectOne", no);
		System.out.println(bVo);
		
		return bVo;
	}
	
	//수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardDao.modify()");
		
		int count = sqlSession.update("board.modify", boardVo);
		return count;
	}
	
	//등록
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardDao.boardWrite");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	

}
