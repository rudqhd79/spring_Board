package com.Board.service;

import org.springframework.stereotype.Service;

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
  
  private final BoardRepository boardRepository;
  
  private final PostRepository postRepository;
  
  private final MemberRepository memberRepository;
  
  // 작성자
  public PostDto getMemberInfo(String memberId) {
	  Member member = memberRepository.findByLoginId(memberId);
	  PostDto postDto = new PostDto();
	  // 여기부터 시작 postDto.
	  return postDto;
  }
  
  // 게시글 작성
  public Board writePost(String memberId, String boardTitle, String postDetail) {
    Member member = memberRepository.findByLoginId(memberId);
    
    // 새로운 게시판 생성
    Board board = new Board();
    board.setTitle(boardTitle);
    board.setMember(member);
    
    // 게시글 작성
    Post post = Post.createPost(postDetail);
    board.addPosts(post);
    
    // 게시글 저장
    boardRepository.save(board);
  
    return board;
  }
  
  // 게시글 조회
  public Board getBoardById(Long boardId) {
    return boardRepository.findById(boardId).orElse(null);
  }
  
  /*
  // 게시글 삭제
  public void deletePost(Long postId) {
    Post post = postRepository.findById(postId).orElse(null);
    
    if (post != null) {
      Board board = post.getBoard();
      board.getPosts().remove(post);
      postRepository.delete(post);
    }
    */
  }
  
