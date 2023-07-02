package com.Board.entity;

import java.util.List; 

import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
public class Post extends RegistDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private Long id;	// 게시글 식별자
	private String post_detail;	// 게시글 내용
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	@OneToMany(mappedBy = "post")
	private List<PostImg> postImgs;
}
