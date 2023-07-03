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
	private String post_detail;
	private Board board;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post updaetEntity() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostDto updateDto (Post post) {
		return modelMapper.map(post, PostDto.class);
	}
}
