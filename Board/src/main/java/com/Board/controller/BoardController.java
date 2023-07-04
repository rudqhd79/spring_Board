package com.Board.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Board.dto.BoardListDto;
import com.Board.dto.BoardSearchDto;
import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.repository.BoardRepository;
import com.Board.service.BoardService;
import com.Board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

	//private final BoardService boardService;
	//private final PostService postService;
	/*
	 * private final BoardRepository boardRepository; private final BoardService
	 * boardService;
	 */
	
	// 게시판 화면 진입
	// 페이징은 QueryDSL이 안되는 관계로 일단 보류
	@GetMapping(value = "")
	public String boardList(BoardSearchDto boardSearchDto, Optional<Integer> page, Model model) {
		model.addAttribute("boardSearchDto", boardSearchDto);
		// Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);
		// Page<BoardListDto> posts = boardService.getPostListPage(boardSearchDto, pageable);
		return "";
	}
	
	@GetMapping(value="/list/{postId}")
	public String postInfo(@PathVariable("postId") Long postId, @Valid PostDto postDto, Model model, Principal principal) {
		
		return "";
	}
	
}
