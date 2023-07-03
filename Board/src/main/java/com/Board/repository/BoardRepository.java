package com.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	
}
