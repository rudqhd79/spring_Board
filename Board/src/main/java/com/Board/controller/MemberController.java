package com.Board.controller;

import java.security.Principal;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Board.constant.MemberRole;
import com.Board.dto.MemberDto;
import com.Board.entity.Member;
import com.Board.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final PasswordEncoder passwordEncoder;
	private final MemberService memberService;
	
	// 로그인 페이지
	@GetMapping(value = "/member/login")
	public String login() {
		return "member/memberLogin";
	}
	
	// 회원가입 페이지 버튼
	@GetMapping(value = "/member/join")
	public String joinPage(Model model) {
		model.addAttribute("MemberDto", new MemberDto());
		return "member/memberJoin";
	}
	
	//회원가입 버튼을 눌렀을때 실행되는 메소드
		@PostMapping(value = "/member/new")
		public String memberJoin(@Valid MemberDto memberFormDto, BindingResult bindingResult, Model model, @RequestParam("profileImgFile") MultipartFile profileImgFile, Principal principal) throws Exception {
			if(bindingResult.hasErrors()) {
				return "member/memberJoin";
			}
			try {
				Member memberNotImg = Member.createMember(memberFormDto, passwordEncoder, MemberRole.USER);
				Member member = memberService.saveProfileImg(memberNotImg, profileImgFile, principal.getName());
				
				memberService.save(member);
			} catch (IllegalStateException e) {
				model.addAttribute("errorMessage", e.getMessage());
				return "member/memberJoin";
			}
			return "redirect:/";
		}

}
