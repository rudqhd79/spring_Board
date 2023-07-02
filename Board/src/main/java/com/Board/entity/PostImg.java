package com.Board.entity;

import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class PostImg extends RegistDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="postImg_id")
	private Long id;	// 게시글 식별자
	private String post_ori_img_name;	// 원본 게시글 이미지 이름
	private String post_use_img_name;	// 사용할 게시글 이미지 이름
	private String post_path_img;			// 경로 게시글 이미지
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
}
