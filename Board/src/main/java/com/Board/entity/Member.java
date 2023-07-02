package com.Board.entity;

import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Board.constant.MemberRole;
import com.Board.dto.MemberDto;

@Entity
@Getter
@Setter
@ToString
public class Member extends RegistDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id; // 회원 식별자
    private String name; // 회원 실명
    private String password; // 회원 로그인 비밀번호
    private String hintA; // 회원 힌트에 대한 답변
    
    @Column(unique = true, nullable = false)
    private String loginId; // 회원 로그인 아이디
    
    @Column(unique = true, nullable = true)
    private String nickName; // 회원 닉네임	(중복 방지)
    
    @Column(unique = true, nullable = false)
    private String email; // 회원 이메일
    
    @Column(unique = true, nullable = false)
    private String phone; // 회원 전화번호
    
    @Enumerated(EnumType.STRING)
    private MemberRole role;	// 권한s

    // mappedBy는 해당 객체의 관계도 주인의 entity에 생성한다
    @OneToMany(mappedBy = "member")
    private List<Hint> hints;
    
    @OneToMany(mappedBy = "member")
    private List<ProfileImg> pro_imgs;
    
    @OneToMany(mappedBy = "member")
    private List<Board> boards;
    
    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    // DB와 연결지점인 Entity를 view 연결지점인 Dto로 변환하는 메소드
    public static Member updateDto(MemberDto memberDto) {
    	return modelMapper.map(memberDto, Member.class);
    }

    // 회원 생성 메소드
    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder, MemberRole role) {
    	Member member = new Member();
    	member.setName(memberDto.getName());	// 회원 이름
    	member.setNickName(memberDto.getNickName());	// 회원 닉네임
    	member.setLoginId(memberDto.getLoginId());	// 회원 로그인 아이디
    	member.setEmail(memberDto.getEmail());	// 회원 이메일
    	member.setPhone(memberDto.getPhone());	// 회원 전화번호
    	member.setHintA(memberDto.getHintA());	// 회원 전화번호
    	member.setRole(role);	// 회원 권한 (권한은 페이지로 나누어 만든다)
    	
    	// 비밀번호 암호화
    	String password = passwordEncoder.encode(memberDto.getPassword());
    	member.setPassword(password);
    	
    	return member;
    }
    
    // 메소드 체이닝으로 profileImg 추가
    public Member addProfileImg(String ori_img_name, String img_name, String img_url) {
    	ProfileImg profileImg = ProfileImg.createProfileImg(ori_img_name, img_name, img_url);
    	this.addProfileImg(profileImg);
    	return this;
    }
    
    public void addProfileImg(ProfileImg profileImg) {
        this.pro_imgs.add(profileImg);
        profileImg.setMember(this);
    }
    


}
