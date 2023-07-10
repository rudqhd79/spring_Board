package com.Board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Board.dto.CommentDto;
import com.Board.entity.Comment;
import com.Board.entity.Member;
import com.Board.entity.Post;
import com.Board.repository.CommentRepository;
import com.Board.repository.MemberRepository;
import com.Board.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Service
public class CommentService {

	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	
	/*
	@Transactional
	public List<CommentDto> getCommentList(Long postId, String loginUser) {
		// member
		Member member = memberRepository.findByLoginId(loginUser);
		// post
		Post post = postRepository
		// comment
		return;
	}
	*/
	// 게시글 댓글 불러오기
	@Transactional(readOnly = true)
	public List<CommentDto> getCommentList(Long postId, String loginUser) throws Exception {
		List<Comment> commentList = commentRepository.findByPostId(postId);
		List<CommentDto> commentDtoList = new ArrayList<>();
		
		for (Comment comment : commentList) {
			CommentDto commentDto = comment.updateDto(comment);
			commentDtoList.add(commentDto);
		}
		if (commentDtoList.isEmpty()) {
			throw new EntityNotFoundException("No comments found for the post.");
		}
		return commentDtoList;
	}
	
	// 게시글 댓글 저장
	@Transactional
	public Long saveComment(CommentDto commentDto) {
		Comment comment = commentDto.updateEntity();
		commentRepository.save(comment);
		return comment.getId();
	}
}
