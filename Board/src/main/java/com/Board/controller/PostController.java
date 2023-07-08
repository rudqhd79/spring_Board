package com.Board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Board.dto.BoardDto;
import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.entity.Member;
import com.Board.service.BoardService;
import com.Board.service.MemberService;
import com.Board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;
	private final MemberService memberService;
	// private final BoardService boardService;
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
	  public String writePostPage(Model model) {
		  // postDto 객체로 정보를 담는다
		  model.addAttribute("postDto", new PostDto());
	    return "post/write";
	  }

	  // 게시글 작성
	  @PostMapping("/write")
	  public String writePost(@Valid PostDto postDto, @RequestParam("postImg")List<MultipartFile> multipartFiles, BindingResult bindingResult, Model model, Principal principal) {
		 // 에러가 있으면 게시판으로 돌아감
		  if (bindingResult.hasErrors()) {
			  return "redirect:/board/list";
		  }
		  try {
			  postService.savePost(postDto, multipartFiles);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "게시물 등록에 실패했습니다.");
			return "redirect:/board/list";
		}
		  return "post/write";
	  }
}
