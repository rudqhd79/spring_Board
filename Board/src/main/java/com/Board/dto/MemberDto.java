package com.Board.dto;


import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.Board.constant.MemberRole;
import com.Board.entity.Member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	private Long id;

	@NotEmpty(message = "실명을 입력해 주세요.")
	@Length(min = 2, max = 10, message = "이름은 2자 ~ 10자 입니다.")
	@Pattern(regexp = "^[가-힣]+$", message = "이름을 제대로 입력해 주세요.")
	private String name;

	// null 가능
	private String nickName;
	
	// 대문자 1개이상, 소문자 1개이상, 숫자 1개이상, 특수문자 1개이상을 포함한 8자이상
	@NotEmpty(message = "비밀번호를 입력해 주세요.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", message = "비밀번호를 형식대로 입력해 주세요.")
	private String password;
	
	// - 영문 대문자 또는 소문자로 시작하는 아이디, 길이는 4 ~ 20자
	@NotEmpty(message = "로그인 아이디를 입력해 주세요.")
	@Pattern(regexp = "^[A-Za-z]{4, 20}", message = "아이디를 형식대로 입력해 주세요")
	private String loginId;
	
	// 이메일 \-\.(슬러시같은 내용들을 의미한다.), +(반복 얼마나 나오든) 
    // - 영문 대문자 또는 소문자 또는 숫자로 시작하고 @포함 .포함
	@NotEmpty(message = "이메일을 입력해 주세요.")
	@Pattern(regexp = "^[A-Za-z-0-9\\-\\.]+@[A-Ja-z-0-9\\-\\.]+\\.[A-Ja-z-0-9]+$", message = "이메일을 형식대로 입력해 주세요.")
	private String email;
	
	@NotEmpty(message = "전화번호를 입력해 주세요.")
	@Length(min = 13, max = 13, message = "-포함 13자리를 입력해주세요.")
	@Pattern(regexp = "^(010|011|016|017|018|019)[-]?\\d{3,4}[-]?\\d{4}$", message = "전화번호를 형식에 맞게 입력해 주세요")
	private String phone;
	
	@NotEmpty(message = "힌트 답변을 입력해 주세요.")
	private String hintA;
	
	private MemberRole role;
	
	private LocalDateTime regTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// view와 연결지점인 dto를 DB와 연결지점인 Entity 정보로 변환하는 메소드
	public static MemberDto updateEntity (Member member) {
		return modelMapper.map(member, MemberDto.class);
	}

}
