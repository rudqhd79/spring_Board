package com.Board.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDto {

	private Long id;
	private String title;
	private String writer;
	private int views;
	
	// QClass가 사용할 메소드
	@QueryProjection
	public BoardListDto(Long id, String boardTitle, String writer, int views) {
		this.id = id;
		this.title = boardTitle;
		this.writer = writer;
		this.views = views;
	}
}
