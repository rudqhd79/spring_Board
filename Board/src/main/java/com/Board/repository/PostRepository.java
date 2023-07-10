package com.Board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Board.entity.Post;

// QueryDsl을 통해 쿼리문을 만들자
public interface PostRepository extends JpaRepository<Post, Long>{
	
	// 전체 조회
	List<Post> findByBoardPost(Long id);
	
	// 검색을 통하여 특정 게시물 조회
	List<Post> findByBoardTitleAndMemberNickName(String title, String nickName);

}
