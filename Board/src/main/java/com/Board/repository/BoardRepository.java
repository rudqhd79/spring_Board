package com.Board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.Board.dto.BoardListDto;
import com.Board.dto.PostSearchDto;
import com.Board.entity.Board;
import com.Board.entity.Post;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// QueryDSL이 pom.xml 파일에서 도저히 안맞는다
	// Board에 post리스트들을 띄운다
	//Page<BoardListDto> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable);
	
	// 작성자는 nickName, 제목으로 검색한다
	@Query("select b.title, b.views, m.nickName  from post p, member m, board b where b.title = :title and m.nickName = :nickName")
	public List<Post> findPosts(@Param("nickName")String nickName, @Param("title")String title);

	
}
