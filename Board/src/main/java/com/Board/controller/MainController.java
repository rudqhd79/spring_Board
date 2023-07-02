package com.Board.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Board.dto.MemberDto;
import com.Board.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService memberService;

	@GetMapping(value="/")
	public String profileGenerate(Model model, Principal principal) {
		if (principal != null) {
			MemberDto memberFormDto = memberService.getIdImgUrl(principal.getName());
			System.out.println(principal.getName());
			model.addAttribute("member", memberFormDto);
		}
		return "main";
	}
	
	@GetMapping(value="/member/login")
	public String login() {
		return "login";
	}
}
