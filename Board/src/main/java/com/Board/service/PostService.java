package com.Board.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Board.dto.PostDto;
import com.Board.entity.Member;
import com.Board.entity.Post;
import com.Board.entity.PostImg;
import com.Board.repository.MemberRepository;
import com.Board.repository.PostImgRepository;
import com.Board.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final PostImgService postImgService;

	@Transactional
	public PostDto getMemberInfo(String memberId) {
		// 로그인한 사용자
		Member member = memberRepository.findByLoginId(memberId);
		
		PostDto postDto = new PostDto();
		return postDto;
	}
	
	@Transactional
	public Long savePost(PostDto postDto, List<MultipartFile> multipartFiles) throws Exception {
		Post post = postDto.updateEntity();
		postRepository.save(post);
		
		// for문으로 List에서 이미지를 하나씩 등록
		for (int i = 0; i < multipartFiles.size(); i++) {
			PostImg postImg = new PostImg();
			postImg.setPost(post);
			
			postImgService.savePostImg(postImg, multipartFiles.get(i));
		}
		return post.getId();
	}
	
}
