package com.Board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Board.entity.Member;
import com.Board.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	// Spring Security에 담은 사용자의 정보를 담는 것이 UserDetails이다 (VO 역할)
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		
		// userName의 매개변수는 스프링 시큐리티의 로그인 파라메터 값이 memberRepository를 통해 해당 회원을 가져온다
		Member member = memberRepository.findByLoginId(loginId);
		
		// 해당 유저가 없으면 로그인 아이디를 포함한 에러가 발생
		if (member == null) {
			throw new UsernameNotFoundException(loginId);
		}
		// User객체는 UserDetails 메소드 내에 있는 객체라고 할 수 있다.
		// User에 관한 정보를 ID Password Role을 이용하여 유저를 만든다
		return User.builder().username(member.getLoginId()).password(member.getPassword())
				  .roles(member.getRole().toString()).build();
	}
	
	public Member save (Member member) {
		validateDuplication(member);
		return memberRepository.save(member);
	}
	
	private void validateDuplication(Member member) {
		Member findMember = memberRepository.findByLoginId(member.getLoginId());
		if (findMember != null) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	
}
