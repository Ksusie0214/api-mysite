package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//데이터 넣기
	public int userJoin(UserVo userVo) {
		System.out.println("UserDao.userJoin");
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	//id, pw로 한명 데이터 가져오기(로그인)
	public UserVo userSelectByIdPw(UserVo userVo) {
		System.out.println("UserDao.userSelectByIdPw");
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		System.out.println(authUser);

		return authUser;
	}
	
	// no로 데이터 가져오기
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");

		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}
	
	public int userModify(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		System.out.println(count);
		
		return count;
	}
}
