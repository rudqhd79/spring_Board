package com.Board.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.Board.entity.Board;
import com.Board.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

	private Long id;
	
	private String title;
	
	private int views;
	
	private Member member;
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
	
private static ModelMapper modelMapper = new ModelMapper();
	
	// view와 연결지점인 dto를 DB와 연결지점인 Entity 정보로 변환하는 메소드
	public Board createTitleToEntity () {
		Board board = modelMapper.map(this, Board.class);
		board.setTitle(title);
		return board;
	}
	
	public Board updateEntity () {
		return modelMapper.map(this, Board.class);
	}
	
	public static BoardDto updateDto(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}

	public static BoardDto createPostTitle(String postTitle) {
		BoardDto boardDto = new BoardDto();
		boardDto.setTitle(postTitle);
		return boardDto;
	}
}
