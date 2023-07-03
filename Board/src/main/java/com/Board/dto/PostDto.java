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
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post updaetEntity() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostDto updateDto (Post post) {
		return modelMapper.map(post, PostDto.class);
	}
	
	public void postInfoFromBoard(Post post) {
		this.id = post.getId();
		this.memberId = post.getBoard().getMember().getId();
		this.post_detail = post.getPost_detail();
		this.memberName = post.getBoard().getMember().getName();
	}
}
