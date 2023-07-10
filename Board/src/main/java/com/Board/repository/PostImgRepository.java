package com.Board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.PostImg;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {

	List<PostImg> findByPostImg(Long postId);
	
}
