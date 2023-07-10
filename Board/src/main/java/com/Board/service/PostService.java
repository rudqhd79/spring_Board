package com.Board.service;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Board.dto.BoardDto;
import com.Board.dto.PostDto;
import com.Board.dto.PostImgDto;
import com.Board.entity.Board;
import com.Board.entity.Member;
import com.Board.entity.Post;
import com.Board.entity.PostImg;
import com.Board.repository.BoardRepository;
import com.Board.repository.MemberRepository;
import com.Board.repository.PostImgRepository;
import com.Board.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final PostImgRepository postImgRepository;
	private final PostImgService postImgService;

	@Transactional
	public PostDto getMemberInfo(String memberId) {
		// 로그인한 사용자
		Member member = memberRepository.findByLoginId(memberId);
		
		PostDto postDto = new PostDto();
		postDto.setMemberName(member.getName());
		return postDto;
	}
	
	@Transactional
	public Long savePost(PostDto postDto, List<MultipartFile> multipartFiles, String loginUser) throws Exception {
		// 게시판은 title
		BoardDto boardDto = BoardDto.createPostTitle(postDto.getPost_detail());
		Board board = boardDto.createTitleToEntity();
		board.setMember(memberRepository.findByLoginId(loginUser));
		boardRepository.save(board);
		
		// principal로 접속된 사용자, 내용을 post에 저장
		 Post post = postDto.updateEntity();
		 post.setBoard(board);
         post.getBoard().setMember(memberRepository.findByLoginId(loginUser));
         postRepository.save(post);

		// for문으로 List에서 이미지를 하나씩 등록
		for (int i = 0; i < multipartFiles.size(); i++) {
			PostImg postImg = new PostImg();
			postImg.setPost(post);
			
			postImgService.savePostImg(postImg, multipartFiles.get(i));
		}
		return post.getId();
	}
	
	@Transactional(readOnly = true)
	public PostDto getPostDetail(Long postId) throws Exception {
			// 이미지 불러오기
			List<PostImg> postImgList = postImgRepository.findByPostImg(postId);
			List<PostImgDto> postImgDtoList = new ArrayList<>();
			
			for (PostImg postImg : postImgList) {
				PostImgDto postImgDto = PostImgDto.updateEntity(postImg);
				postImgDtoList.add(postImgDto);
			}
			
			// 해당 게시글의 이미지 불러오기
			Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
			PostDto postDto = post.updateDto(post);
			
			postDto.setPostImgDtoList(postImgDtoList);
			
		return postDto;
	}
}
