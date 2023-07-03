package com.Board.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Board.dto.PostDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {

	// 게시글 작성 페이지
	@GetMapping(value="/write")
	public String writePost (Model model, Principal principal) {
		PostDto postDto = new PostDto();
		return "post/write";
	}
	
	// 게시글 작성 submit 경로
	@PostMapping(value = "/new")
	public String writePost() {
		return "redirect:/post/list";
	}
}
