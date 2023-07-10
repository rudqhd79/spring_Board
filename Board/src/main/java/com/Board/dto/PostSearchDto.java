package com.Board.dto;

import com.Board.entity.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 게시글 검색 DTO
// 검색은 작성자(닉네임)
// 제목 (쿼리는 like %${paramData}%로 찾는다)
// queryDSL을 사용하고 싶었으나 q클래스 오류가 많아 JPQL로 작성한다
public class PostSearchDto {
	
	/*
	// 날짜순으로 검색
	private String searchDateType;
	private BoardDto boardDto;
	*/
	// 전체 조회용
	private BoardDto boardDto;
	
	// 제목
	private String title;
	// 작성자
	private String writer;
	
	public boolean isNull() {
		return title == null && writer == null;
	}
}
