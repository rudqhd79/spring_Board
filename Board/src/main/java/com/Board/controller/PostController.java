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

	// 글쓰기 메뉴를 눌러 진입 경로
	@GetMapping(value="")
	public String postPage (Model model, Principal principal) {
		PostDto postDto = new PostDto();
		return "post/write";
	}
	
	// 글쓰기 게시물 누를 때
	@PostMapping(value = "/write")
	public String writePost() {
		return "redirect:/post/list";
	}
}
