package com.Board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Board.repository.BoardRepository;
import com.Board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

	/*
	 * private final BoardRepository boardRepository; private final BoardService
	 * boardService;
	 */
	
	// 게시판 목록화면
	// post create에서 제목이랑 같이 생성된다
	@GetMapping(value = "/list")
	public String boardList(Model model) {
		model.addAttribute("", "");
		return "board/list";
	}
	
}
