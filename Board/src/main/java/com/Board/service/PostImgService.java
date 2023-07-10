package com.Board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Board.entity.Member;
import com.Board.entity.PostImg;
import com.Board.repository.PostImgRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostImgService {
	
	@Value("{userPostImgLocation}")
	private String userPostImgLocation;
	
	private final FileService fileService;
	private final PostImgRepository postImgRepository;
	
	// 프로필 이미지를 entity로 넘긴다
		public void savePostImg(PostImg postImg, MultipartFile postImgs) throws Exception {
			String ori_img_name = postImgs.getOriginalFilename();	// 원본 이름
			String img_name = "";	// 프로그래밍 이름
			String img_url = "";		// 경로
			
			// StringUtil에서 isEmpty가 deprecated라서 hasText로 바꿨다(조건은 반대)
			if (StringUtils.hasText(ori_img_name)) {
				img_name = fileService.uploadFile(userPostImgLocation, ori_img_name, postImgs.getBytes());
				img_url = "/images/profile/" + img_name;
			} else {
				ori_img_name = "";
			}
			postImg.addPostImg(ori_img_name, img_name, img_url);
			postImgRepository.save(postImg);
		}
		
		// 프로필 이미지를 entity로 넘긴다
		public void updatePostImg(Long postImgId, MultipartFile postImgs) throws Exception {
			if (!postImgs.isEmpty()) {	// 파일이 존재하면
				PostImg savedPostImg = postImgRepository.findById(postImgId).orElseThrow(EntityNotFoundException::new);;
				
				// 기존 이미지 파일 삭제
				if (!StringUtils.hasText(savedPostImg.getPost_use_img_name())) {
					fileService.deleteFile(userPostImgLocation + "/" + savedPostImg.getPost_use_img_name());
				}
				String ori_img_name = postImgs.getOriginalFilename();	// 원본 이름
				String img_name = "";	// 프로그래밍 이름
				String img_url = "";		// 경로
				savedPostImg.addPostImg(ori_img_name, img_name, img_url);
			}
		}

}
