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
	
	//private final PostService postService;
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
	// 게시글 작성 페이지
	  @GetMapping("/write")
	  public String writePostPage(Model model, Principal principal) {
	    PostDto postDto = boardService.getMemberInfo(principal.getName());
	    model.addAttribute("postDto", postDto);
	    return "post/write";
	  }

	  // 게시글 작성 처리
	  @PostMapping("/write")
	  public String writePost(@RequestParam("memberId") String memberId,
	                          @RequestParam("boardTitle") String boardTitle,
	                          @RequestParam("postDetail") String postDetail) {
	    boardService.writePost(memberId, boardTitle, postDetail);
	    return "redirect:/board/list"; // 작성 후 목록 페이지로 리다이렉트
	  }

	  // 게시글 상세 페이지
	  @GetMapping("/post/{postId}")
	  public String viewPost(@PathVariable("postId") Long postId, Model model) {
	    Board board = boardService.getBoardById(postId);
	    model.addAttribute("board", board);
	    return "post/view";
	  }

	  // 게시글 삭제
	  @PostMapping("/post/{postId}/delete")
	  public String deletePost(@PathVariable("postId") Long postId) {
	    //boardService.deletePost(postId);
	    return "redirect:/board/list"; // 삭제 후 목록 페이지로 리다이렉트
	  }
}
