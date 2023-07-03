package com.Board.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.service.BoardService;
import com.Board.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;
	private final BoardService boardService;
/*
	// 게시글 작성 페이지
	@GetMapping(value="/write")
	public String writePost (Model model, Principal principal) {
		PostDto postDto = postService.getMemberInfo(principal.getName());
		model.addAttribute("postDto", postDto);
		return "post/write";
	}
	*/
	/*
	// 게시글 작성 submit 경로
	@PostMapping(value = "/new")
	public String writePost() {
		return "redirect:/post/list";
	}
	*/
}
