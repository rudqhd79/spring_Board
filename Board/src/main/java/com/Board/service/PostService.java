package com.Board.service;

import org.springframework.stereotype.Service;

import com.Board.dto.PostDto;
import com.Board.entity.Member;
import com.Board.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	
	private final MemberRepository memberRepository;

	@Transactional
	public PostDto getMemberInfo(String memberId) {
		// 로그인한 사용자
		Member member = memberRepository.findByLoginId(memberId);
		
		PostDto postDto = new PostDto();
		return postDto;
	}
	
}
