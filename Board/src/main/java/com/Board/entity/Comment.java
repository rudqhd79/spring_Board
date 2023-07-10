package com.Board.entity;

import lombok.ToString;

import org.modelmapper.ModelMapper;

import com.Board.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Comment extends RegistDate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="comment_id")
	private Long id;	// 댓글 식별자
	private String comment_detail;	// 댓글 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id")
	private Post post;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public CommentDto updateDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}
}
