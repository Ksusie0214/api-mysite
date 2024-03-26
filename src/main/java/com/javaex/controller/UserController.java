package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	//회원가입
	@PostMapping("/api/user/join")
	public int join(@RequestBody UserVo userVo) {
		System.out.println("UserController.join()");
		int count = userService.exeJoin(userVo);
		System.out.println(userVo);
		return count;
	}

	// 로그인
	@PostMapping("/api/user/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");

		UserVo authUser = userService.exeLogin(userVo);

		if (authUser != null) {
			// 토큰발급 헤더에 실어 보내기
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getNo());
			return JsonResult.success(authUser);
		
		}else {
			return JsonResult.fail("로그인실패");
		}
	}

	// 회원정보 수정(1명 데이터 가져오기)
	@GetMapping("/api/user/modify")
	public JsonResult modifyForm(HttpServletRequest request) {
		System.out.println("UserController.modifyForm()");

		/*
		 * //토큰 꺼내기 String token = JwtUtil.getTokenByHeader(request);
		 * System.out.println("token = "+ token);
		 * 
		 * //검증 boolean check = JwtUtil.checkToken(token); System.out.println(check);
		 * 
		 * //이상이 없다면 if(check==true) { System.out.println("정상"); String no
		 * =JwtUtil.getSubjectFromToken(token); System.out.println(no); }
		 */
		// 위의 토큰 검증 과정을 메소드로 만든 것
		int no = JwtUtil.getNoFromHeader(request);
		if (no != -1) {
			
			UserVo userVo = userService.exeModifyForm(no);
			
			return JsonResult.success(userVo);
		} else {
			// 토큰이 없거나 변조된 경우
			return JsonResult.fail("실패");
		}
	}

	// 수정된 회원정보 보내기
	@PutMapping("/api/user/modify")
	public JsonResult modify(@RequestBody UserVo userVo, HttpServletRequest request) {
		System.out.println("UserController.modifyUser()");
		System.out.println(userVo);

		int no = JwtUtil.getNoFromHeader(request);
		if (no != -1) {
			// db에 수정시킨다
			userService.exeModify(userVo);

			return JsonResult.success(userVo.getName());
		} else {
			return JsonResult.fail("로그인하지 않음");
		}
	}

}
