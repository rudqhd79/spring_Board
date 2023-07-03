package com.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
