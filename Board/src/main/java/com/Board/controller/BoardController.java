package com.Board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Board.dto.PostSearchDto;
import com.Board.entity.Post;
import com.Board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

	private final BoardService boardService;
	
	// 게시판 검색 (작성자(닉네임), 제목) || 게시글 목록 띄우기
	@GetMapping(value="/list")
	public String boardList(@ModelAttribute("postSearch") PostSearchDto postSearchDto ,Model model) {
		// post ID를 통해 select
		List<Post> posts = boardService.findPosts(postSearchDto);
		model.addAttribute("posts", posts);
		return "board/list";
	}
}
