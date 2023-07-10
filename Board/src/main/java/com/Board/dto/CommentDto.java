package com.Board.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.Board.entity.Comment;
import com.Board.entity.Member;
import com.Board.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Long id;
	private Member member;
	private Post post;
	private String comment_detail;	// 댓글 내용
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Comment updateEntity() {
		return modelMapper.map(this, Comment.class);
	}
}
