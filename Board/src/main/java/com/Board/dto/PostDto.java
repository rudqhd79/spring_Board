package com.Board.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.Board.entity.Board;
import com.Board.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	
	private Long id;
	private String post_detail;	// 글 내용
	private String memberName;	// 작성자
	private List<PostImgDto> postImgDtoList = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post updateEntity() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostDto updateDto (Post post) {
		return modelMapper.map(post, PostDto.class);
	}
	
	public PostDto createPost(String detail, String loginUser) {
		PostDto postDto = new PostDto();
		postDto.post_detail = detail;
		postDto.memberName = loginUser;
		return postDto;
	}
}
