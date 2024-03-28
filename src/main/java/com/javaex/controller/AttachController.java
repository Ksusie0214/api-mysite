package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.vo.UserVo;

@RestController
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	//파일은 json 말고 파라미터로 보냄
	@PostMapping(value = "/api/attach")
	public JsonResult upload(@RequestParam MultipartFile file, @ModelAttribute UserVo userVo) {
		System.out.println("AttachController.upload()");
		System.out.println("fileName: " + file.getOriginalFilename());
		System.out.println(userVo);
		
		String saveName= attachService.exeUpload(file);
		
		return JsonResult.success(saveName);
	}

}
