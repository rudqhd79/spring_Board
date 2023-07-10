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
import com.Board.dto.CommentDto;
import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.entity.Member;
import com.Board.service.BoardService;
import com.Board.service.CommentService;
import com.Board.service.MemberService;
import com.Board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;
	private final BoardService boardService;
	private final CommentService commentService;
	
	// 게시글 작성 페이지
	  @GetMapping("/write")
	  public String writePostPage(Model model) {
		  // postDto 객체로 정보를 담는다
		model.addAttribute("postDto", new PostDto());
	    return "post/write";
	  }

	  // 게시글 작성
	  @PostMapping("/write")
	  public String writePost(@Valid PostDto postDto, @RequestParam("postImg")List<MultipartFile> multipartFiles, Model model, Principal principal) {
		  try {
			  postService.savePost(postDto, multipartFiles, principal.getName());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "게시물 등록에 실패했습니다.");
			return "redirect:/board/list";
		}
		  return "redirect:/board/list";
	  }
	  
	  // 게시글 상세
	  @GetMapping(value="/{postId}")
	  public String postDetail(@PathVariable("postId")Long postId, Model model, Principal principal) {
		  try {
			  // 게시글 글 가져오기
			  PostDto postDto = postService.getPostDetail(postId);
			  model.addAttribute("postDto", postDto);
			  // 제목은 boardDto에서
			  BoardDto boardDto = boardService.getPostTitle(postId);
			  model.addAttribute("boardDto", boardDto);
			  // 게시글 댓글 가져오기
			  List<CommentDto> commentDtoList = commentService.getCommentList(postId, principal.getName());
			  model.addAttribute("commentDtoList", commentDtoList);
		  } catch (Exception e) {
			  e.printStackTrace();
			  model.addAttribute("failLoading", "페이지 에러");
			  return "redirect:/board/list";
		  }
		  return "post/detail";
	  }
	  
	  // 게시글 댓글 작성
	  @PostMapping(value="/{postId}/commentWrite")
	  public String writeComment(@Valid CommentDto commentDto, @PathVariable("postId") Long postId, Model model, Principal principal, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors()) {
			  model.addAttribute("commentFail", "댓글 작성 중 문제가 발생하였습니다.");
			  return "redirect:/board/list";
		  }
		  try {
			  commentService.saveComment(commentDto);
		  } catch (Exception e) {
			  model.addAttribute("commentFail", "댓글 작성 중 문제가 발생하였습니다.");
			  e.printStackTrace();
		  }
		  return "redirect:/post/detail";
	  }
}
