package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//리스트
	@GetMapping(value="/api/guest/addlist")
	public List<GuestbookVo> list(Model model){
		System.out.println("GuestbookController.list()");
		List<GuestbookVo> guestbookList= guestbookService.exeGuestList();
		model.addAttribute("guestbookList", guestbookList);
		
		return guestbookList;
	}
	
	//등록
	@PostMapping(value="/api/guest/addlist")
	public GuestbookVo addList(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.addList()");
		System.out.println(guestbookVo);
		
		GuestbookVo gbookList = guestbookService.exeAddList(guestbookVo);
		System.out.println(gbookList);
		
		return gbookList;
	}	
	
	//삭제
	@DeleteMapping(value="/api/guest/delform/{no}")
	public String remove(@RequestBody GuestbookVo guestbookVo,
						 @PathVariable(value="no")int no) {
		System.out.println("GuestbookController.remove()");
		System.out.println(guestbookVo);
		
		guestbookVo.setNo(no);
		System.out.println(guestbookVo);
		
		int count = guestbookService.exeRemove(guestbookVo);
		
		String result = "{\"count\":"+count+"}";
		
		return result;
	}

}
