package com.Board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Board.dto.BoardDto;
import com.Board.dto.BoardListDto;
import com.Board.dto.PostSearchDto;
import com.Board.dto.PostDto;
import com.Board.entity.Board;
import com.Board.entity.Member;
import com.Board.entity.Post;
import com.Board.repository.BoardRepository;
import com.Board.repository.MemberRepository;
import com.Board.repository.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	private final PostRepository postRepository;
	
	public List<Post> findPosts(PostDto postDto) {
		List<Post> post = postRepository.findByPost(postDto.getId());
		return post;
	}
}
