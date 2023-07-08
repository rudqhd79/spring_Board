package com.Board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findByPost();
}
