package com.Board.dto;

import org.modelmapper.ModelMapper;

import com.Board.entity.Post;
import com.Board.entity.PostImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostImgDto {

	private Long id;
	private Post post;
	private String post_img_name; // 이미지명
	private String post_ori_name; // 원본 이미지명
	private String post_img_url; // 이미지 경로

	private static ModelMapper modelMapper = new ModelMapper();

	public static PostImgDto updateEntity(PostImg postImg) {
		return modelMapper.map(postImg, PostImgDto.class);
	}
}
