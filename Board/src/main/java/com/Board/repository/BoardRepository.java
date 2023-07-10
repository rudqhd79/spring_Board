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

}
