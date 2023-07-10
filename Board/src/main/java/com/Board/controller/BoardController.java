package com.Board.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Board.dto.BoardListDto;
import com.Board.dto.PostSearchDto;
import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.entity.Member;
import com.Board.entity.Post;
import com.Board.repository.BoardRepository;
import com.Board.service.BoardService;
import com.Board.service.MemberService;
import com.Board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

	private final BoardService boardService;
	
	// 게시판 검색 (작성자(닉네임), 제목)
	@GetMapping(value="/list")
	public String boardList(@ModelAttribute("postSearch") PostSearchDto postSearchDto ,Model model) {
		// post ID를 통해 select
		List<Post> posts = boardService.findPosts(postSearchDto);
		model.addAttribute("posts", posts);
		return "board/boardList";
	}
}
