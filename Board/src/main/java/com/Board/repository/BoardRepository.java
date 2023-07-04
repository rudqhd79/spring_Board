package com.Board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.dto.BoardListDto;
import com.Board.dto.BoardSearchDto;
import com.Board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// QueryDSL이 pom.xml 파일에서 도저히 안맞는다
	// Board에 post리스트들을 띄운다
	//Page<BoardListDto> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable);

	
}
