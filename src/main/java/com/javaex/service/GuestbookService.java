package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;
	
	//리스트 가져오기
	public List<GuestbookVo> exeGuestList(){
		System.out.println("GuestbookService.exeGuestList()");
		
		List<GuestbookVo> guestbookList = guestbookDao.guestbookSelectList();
		
		return guestbookList;
	}
	
	//리스트 등록
	public GuestbookVo exeAddList(GuestbookVo guestbookVo) {
		System.out.println("GuestService.exeAddList");
		
		//등록
		int count = guestbookDao.insertSelectKey(guestbookVo);
		//no값 가져오기
		int no = guestbookVo.getNo();
		
		GuestbookVo gVo = guestbookDao.guestbookSelectOne(no);
		
		return gVo;
	}
	
	//삭제
	
	public int exeRemove(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeRemove()");
		System.out.println(guestbookVo);
		
		int count = guestbookDao.removeGuest(guestbookVo);
		System.out.println(count);
		return count;
	}

}
