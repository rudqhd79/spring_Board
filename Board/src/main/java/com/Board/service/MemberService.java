package com.Board.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Board.dto.MemberDto;
import com.Board.entity.Member;
import com.Board.entity.ProfileImg;
import com.Board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	private final FileService fileService;
	
	@Value("{userProfileImgLocation}")
	private String userProfileImgLocation;
	
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
	
	// 회원가입
	public Member save (Member member) {
		validateDuplication(member);
		return memberRepository.save(member);
	}
	
	// 이미 가입되어있는 회원 판별
	private void validateDuplication(Member member) {
		Member findMember = memberRepository.findByLoginId(member.getLoginId());
		if (findMember != null) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 프로필 이미지를 entity로 넘긴다
	public Member saveProfileImg(Member member, MultipartFile profileImgs) throws Exception {
		String ori_img_name = profileImgs.getOriginalFilename();	// 원본 이름
		String img_name = "";	// 프로그래밍 이름
		String img_url = "";		// 경로
		
		// StringUtil에서 isEmpty가 deprecated라서 hasText로 바꿨다(조건은 반대)
		if (StringUtils.hasText(ori_img_name)) {
			img_name = fileService.uploadFile(userProfileImgLocation, ori_img_name, profileImgs.getBytes());
			img_url = "/images/profile/" + img_name;
		} else {
			ori_img_name = "";
		}
		return  member.addProfileImg(ori_img_name, img_name, img_url);
	}
	
	@Transactional(readOnly = true)
	public MemberDto getIdImgUrl(String loginId) {
		// 현재 로그인한 사용자 정보
		Member member = memberRepository.findByLoginId(loginId);
		// dto로 변환
		MemberDto memberDto = MemberDto.updateEntity(member);
		return memberDto;
	}
	
	// 게시글 쓴 회원
	@Transactional(readOnly = true)
	public Member findMember(String memberId) {
		Member member = memberRepository.findByLoginId(memberId);
		return member;
	}
}
