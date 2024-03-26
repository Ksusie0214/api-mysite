package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GuestbookVo> guestbookSelectList(){
		System.out.println("GuestbookDao.guestbookSelectList()");
		
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		
		System.out.println(guestbookList);
		return guestbookList;
	}
	
	//등록
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.insertSelectKey");
		
		int count = sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		return count;
	}
	
	//1개 정보 가져오기
	public GuestbookVo guestbookSelectOne(int no) {
		System.out.println("GuestbookDao.guestbookSelectOne()");
		
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectOne", no);
		
		return guestbookVo;
	}
	
	//삭제
	
	public int removeGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.removeGuest()");
		int count =sqlSession.delete("guestbook.delete", guestbookVo);
		System.out.println(count);
		return count;
	}
}
