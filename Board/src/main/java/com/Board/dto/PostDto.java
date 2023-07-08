package com.Board.dto;

import org.modelmapper.ModelMapper;

import com.Board.entity.Board;
import com.Board.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private Long id;
	private Long memberId;	// 로그인한 사용자의 정보 담기용
	private String post_detail;
	private String memberName;
	private String title;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post updateEntity() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostDto updateDto (Post post) {
		return modelMapper.map(post, PostDto.class);
	}
	
	public void inputInfo(Long memberId, String detail, String title, String memberName) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.post_detail = detail;
		this.title = title;
	}
}
