package com.Board.dto;

import com.Board.entity.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 게시글 검색 DTO
public class PostSearchDto {
	
	// 날짜순으로 검색
	private String searchDateType;
	private BoardDto boardDto;
	// 제목 (쿼리는 like %${paramData}%로 찾는다)
	private String title;
	// 작성자
	private String writer;
}
