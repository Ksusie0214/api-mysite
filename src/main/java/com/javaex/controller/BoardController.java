package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.BoardVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//리스트
	@GetMapping(value="/api/board/list")
	public List<BoardVo> list(Model model) {
		System.out.println("BoardController.list()");
		List<BoardVo> boardList = boardService.exeList();
		model.addAttribute("boardList" ,boardList);
		
		return boardList;
		
	}
	
	//1개 읽어오기(읽기용)
	@GetMapping(value="/api/board/read/{no}")
	public BoardVo read(@PathVariable("no")int no) {
		System.out.println("BoardController.read()");
		System.out.println(no);
		BoardVo bVo = boardService.exeRead(no);
		
		return bVo;
	}
	
	//1개 읽어오기(수정용)
	@GetMapping(value="/api/board/modify/{no}")
	public BoardVo modifyForm(@PathVariable("no")int no) {
		System.out.println("BoardController.read()");
		System.out.println(no);
		BoardVo bVo = boardService.exeRead(no);
		
		return bVo;
	}
	
	//수정
	@PutMapping(value="/api/board/modify/{no}")
	public String modify(@PathVariable("no")int no, @RequestBody BoardVo boardVo) {
		System.out.println("BoardController.modify()");
		
		int count = boardService.exeModify(boardVo);
		String result = "{\"count\":"+count+"}";
		return result;
		
	}
	
	//등록
	@PostMapping(value="/api/board/addlist")
	public JsonResult write(@RequestBody BoardVo boardVo, HttpServletRequest request) {
		System.out.println("BoardController.write()");
		int no = JwtUtil.getNoFromHeader(request);
		
		if(no != -1) {
			boardVo.setNo(no);
			int count = boardService.exeWrite(boardVo);
			
			return JsonResult.success(count);
		}
		else {
			return JsonResult.fail("실패");
		}
		
	}
	
}
